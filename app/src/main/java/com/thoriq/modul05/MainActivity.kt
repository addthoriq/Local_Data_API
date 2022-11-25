package com.thoriq.modul05

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.thoriq.modul05.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var dbHelper = DatabaseHelper(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        handleInserts()
        handleUpdates()
        handleDeletes()
        handleViewing()
    }

    private fun showToast(text: String){
        Toast.makeText(this@MainActivity, text, Toast.LENGTH_SHORT).show()
    }

    private fun showDialog(title: String, Message: String){
        val builder = AlertDialog.Builder(this)
        builder.setCancelable(true)
        builder.setTitle(title)
        builder.setMessage(Message)
        builder.show()
    }

    private fun clearEditTexts(){
        binding.nameTxt.setText("")
        binding.typeTxt.setText("")
        binding.idTxt.setText("")
    }

    private fun handleInserts(){
        binding.insertBtn.setOnClickListener{
            try {
                dbHelper.insertData(binding.nameTxt.text.toString(), binding.typeTxt.text.toString())
                clearEditTexts()
            } catch (e: Exception){
                e.printStackTrace()
                showToast(e.message.toString())
            }
        }
    }

    private fun handleUpdates() {
        binding.updateBtn.setOnClickListener{
            try {
                val isUpdate = dbHelper.updateData(binding.idTxt.text.toString(), binding.nameTxt.text.toString(), binding.typeTxt.text.toString())
                if (isUpdate)
                    showToast("Data Updated Successfully")
                else
                    showToast("Data Not Updated")
            }catch (e: Exception){
                e.printStackTrace()
                showToast(e.message.toString())
            }
        }
    }

    private fun handleDeletes(){
        binding.deleteBtn.setOnClickListener{
            try {
                dbHelper.deleteData(binding.idTxt.text.toString())
                clearEditTexts()
            }catch (e: Exception){
                e.printStackTrace()
                showToast(e.message.toString())
            }
        }
    }

    private fun handleViewing(){
        binding.viewBtn.setOnClickListener(
            View.OnClickListener {
                val res = dbHelper.allData
                if (res.count == 0){
                    showDialog("Error", "No Data Found")
                    return@OnClickListener
                }

                val buffer = StringBuffer()

                while (res.moveToNext()){
                    buffer.append("ID :"+ res.getString(0) + "\n")
                    buffer.append("NAME :"+ res.getString(1) + "\n")
                    buffer.append("TYPE :"+ res.getString(2) + "\n")
                }
                showDialog("Data Listing", buffer.toString())
            }
        )
    }


}