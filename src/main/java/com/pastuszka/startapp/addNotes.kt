package com.pastuszka.startapp

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_notes.*

class addNotes : AppCompatActivity() {


    val dbTable ="Notes"
    var id=0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_notes)



            try {
                var bundle:Bundle ?= intent.extras
                id=bundle!!.getInt("ID",0)
                if(id!=0) {
                    etTitle.setText(bundle.getString("name"))
                    etDes.setText(bundle.getString("des"))
                }
            }catch (ex:Exception){

            }

    }

    fun buAdd(view:View){

        var dbManager =DbManager(this)

        var values = ContentValues()
        values.put("Title",etTitle.text.toString())
        values.put("Description",etDes.text.toString())

        if(id==0) {

            val ID = dbManager.Insert(values)

            if (ID > 0) {
                Toast.makeText(this, "note is added", Toast.LENGTH_LONG)
                finish()

            } else {
                Toast.makeText(this, "cannot add notes", Toast.LENGTH_LONG)
            }
        }else{
            var selectionArs = arrayOf(id.toString())
            val ID = dbManager.Update(values,"ID=?",selectionArs)
        }

    }
}
