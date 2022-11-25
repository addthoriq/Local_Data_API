package com.thoriq.modul05

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ListView
import okhttp3.*
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException

class KonekAPI : AppCompatActivity() {

    private lateinit var listViewDetails: ListView
    var arrayListDetails: ArrayList<Model> = ArrayList()

    private val client = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_konek_api)
        listViewDetails = findViewById<ListView>(R.id.listView)
        run("https://ermaweb.com/praktekmobile/index.html")
    }

    private fun run(url: String) {
        val req = Request.Builder()
            .url(url)
            .build()
        client.newCall(req).enqueue(object: Callback {
            override fun onFailure(call: Call, e: IOException){
                TODO("Not Yet Implementation")
            }

            override fun onResponse(call: Call, res: Response) {
                val strResponse = res.body()!!.string()
                val jsonContact = JSONObject(strResponse)
                val jsonarrayInfo: JSONArray = jsonContact.getJSONArray("info")
                var i = 0
                val size: Int = jsonarrayInfo.length()
                arrayListDetails = ArrayList()
                for (i in 0 until size){
                    val jsonObjectDetail: JSONObject = jsonarrayInfo.getJSONObject(i)
                    val model = Model()
                    model.id = jsonObjectDetail.getString("id")
                    model.name = jsonObjectDetail.getString("name")
                    model.email = jsonObjectDetail.getString("email")
                    arrayListDetails.add(model)
                }

                runOnUiThread{
                    val objAdapter: CustomAdapter
                    objAdapter = CustomAdapter(applicationContext, arrayListDetails)
                    listViewDetails.adapter = objAdapter
                }
            }
        })
    }
}