package com.example.jpwingsnikkeibpomikuji

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.AlertDialog


class shinki_toroku : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shinki_toroku)

        val radioGroup: RadioGroup = findViewById(R.id.radioGroup)
        radioGroup.check(R.id.radioButton)
    }


    fun modoru_shori(view: View) {

        finish()
    }


    fun all_sakujo_shori(view: View) {

        try {

            val mHMondaiDatabase = M_H_MondaiDatabase.getInstance(this)
            val mhMondaiDao = mHMondaiDatabase.M_H_MondaiDao()



            Log.d("MyApp", "will start delete")
            mhMondaiDao.deleteAll()
        }catch(e:NumberFormatException){//must be number but actually character

            AlertDialog.Builder(this)
                    .setTitle("confirmation1")
                    .setMessage("start delete ")
                    .setPositiveButton("OK", { dialog, which ->
                    })
        }finally{

        }
        AlertDialog.Builder(this)
                .setTitle("confirmation1")
                .setMessage("end delete ")
                .setPositiveButton("OK",{dialog,which ->
                })

        val mMMondaiDatabase = M_M_MondaiDatabase.getInstance(this)
        val mMMondaiDao = mMMondaiDatabase.M_M_MondaiDao()
        mMMondaiDao.deleteAll()
    }


        fun troku_shori(view: View) {

        val mHMondaiDatabase = M_H_MondaiDatabase.getInstance(this)
        val mhMondaiDao = mHMondaiDatabase.M_H_MondaiDao()

        val mMMondaiDatabase = M_M_MondaiDatabase.getInstance(this)
        val mMMondaiDao = mMMondaiDatabase.M_M_MondaiDao()

        val naiyoText: EditText = findViewById(R.id.editTextTextPersonName2) as EditText
        val meisaiText1: EditText = findViewById(R.id.editTextTextPersonName3) as EditText
        val meisaiText2: EditText = findViewById(R.id.editTextTextPersonName4) as EditText
        val meisaiText3: EditText = findViewById(R.id.editTextTextPersonName5) as EditText
        val meisaiText4: EditText = findViewById(R.id.editTextTextPersonName6) as EditText

        //create Entity
        val newHMondai = M_H_Mondai(0, naiyoText.text.toString())

        //insert User
        val rowId = mhMondaiDao.insert(newHMondai)

            Log.d("rowId", "rowIdis "+rowId)
            Log.d("Id", "Idis "+newHMondai.id)

        val radioButton: RadioButton = findViewById(R.id.radioButton)
        val radioButton2: RadioButton = findViewById(R.id.radioButton2)
        val radioButton3: RadioButton = findViewById(R.id.radioButton3)
        val radioButton4: RadioButton = findViewById(R.id.radioButton4)

        val mhMondaiInserted=mhMondaiDao.getRowIdMHMondai(rowId)

        val newMMondai1 = M_M_Mondai(mhMondaiInserted.id, 1, meisaiText1.text.toString(), radioButton.isChecked)
        val newMMondai2 = M_M_Mondai(mhMondaiInserted.id, 2, meisaiText2.text.toString(), radioButton2.isChecked)
        val newMMondai3 = M_M_Mondai(mhMondaiInserted.id, 3, meisaiText3.text.toString(), radioButton3.isChecked)
        val newMMondai4 = M_M_Mondai(mhMondaiInserted.id, 4, meisaiText4.text.toString(), radioButton4.isChecked)

        mMMondaiDao.insert(newMMondai1)
        mMMondaiDao.insert(newMMondai2)
        mMMondaiDao.insert(newMMondai3)
        mMMondaiDao.insert(newMMondai4)

            AlertDialog.Builder(this)
                    .setTitle("confirmation")
                    .setMessage("登録しました！")
                    .setPositiveButton("OK",{dialog,which ->

                    })
                    .show()


            naiyoText.setText("")
            meisaiText1.setText("")
            meisaiText2.setText("")
            meisaiText3.setText("")
            meisaiText4.setText("")


    }

}
