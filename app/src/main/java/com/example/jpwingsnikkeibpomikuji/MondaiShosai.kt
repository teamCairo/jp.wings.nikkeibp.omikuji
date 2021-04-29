package com.example.jpwingsnikkeibpomikuji

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.RadioButton
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

//コミットできてますか？

class MondaiShosai : AppCompatActivity() {

    var kensakuKey:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mondai_shosai)


        val intent: Intent = getIntent()
        val id: String = intent.getStringExtra("ID").toString()
        kensakuKey = intent.getStringExtra(kensakuKey).toString()

        val kensakuKeyText: TextView = findViewById(R.id.textView8) as TextView

        Log.d("MyAppget",id)

        val mHMondaiDatabase = M_H_MondaiDatabase.getInstance(this)
        val mhMondaiDao = mHMondaiDatabase.M_H_MondaiDao()


        val mMMondaiDatabase = M_M_MondaiDatabase.getInstance(this)
        val mmMondaiDao = mMMondaiDatabase.M_M_MondaiDao()

        Log.d("MyAppId",id)
        val mhMondai: M_H_Mondai = mhMondaiDao.getTaishoMHMondai(id.toInt())
        val mmMondai1:M_M_Mondai = mmMondaiDao.getByKey(id.toInt(),1)
        val mmMondai2:M_M_Mondai = mmMondaiDao.getByKey(id.toInt(),2)
        val mmMondai3:M_M_Mondai = mmMondaiDao.getByKey(id.toInt(),3)
        val mmMondai4:M_M_Mondai = mmMondaiDao.getByKey(id.toInt(),4)

        val no: TextView = findViewById(R.id.textView6) as TextView
        val naiyoText: EditText = findViewById(R.id.editTextTextPersonName2) as EditText
        val sentakushi1: EditText = findViewById(R.id.editTextTextPersonName3) as EditText
        val sentakushi2: EditText = findViewById(R.id.editTextTextPersonName4) as EditText
        val sentakushi3: EditText = findViewById(R.id.editTextTextPersonName5) as EditText
        val sentakushi4: EditText = findViewById(R.id.editTextTextPersonName6) as EditText
        val flg1: RadioButton = findViewById(R.id.radioButton) as RadioButton
        val flg2: RadioButton = findViewById(R.id.radioButton2) as RadioButton
        val flg3: RadioButton = findViewById(R.id.radioButton3) as RadioButton
        val flg4: RadioButton = findViewById(R.id.radioButton4) as RadioButton

        naiyoText.setText(mhMondai.mondai_naiyo)
        sentakushi1.setText(mmMondai1.sentaku_naiyo)
        sentakushi2.setText(mmMondai2.sentaku_naiyo)
        sentakushi3.setText(mmMondai3.sentaku_naiyo)
        sentakushi4.setText(mmMondai4.sentaku_naiyo)
        no.setText(mhMondai.id.toString())
        flg1.isChecked=mmMondai1.seikai_Flg
        flg2.isChecked=mmMondai2.seikai_Flg
        flg3.isChecked=mmMondai3.seikai_Flg
        flg4.isChecked=mmMondai4.seikai_Flg

    }

    fun sakujo_shori(view: View){

        val no: TextView = findViewById(R.id.textView6) as TextView
        val naiyoText: EditText = findViewById(R.id.editTextTextPersonName2) as EditText
        val sentakushi1: EditText = findViewById(R.id.editTextTextPersonName3) as EditText
        val sentakushi2: EditText = findViewById(R.id.editTextTextPersonName4) as EditText
        val sentakushi3: EditText = findViewById(R.id.editTextTextPersonName5) as EditText
        val sentakushi4: EditText = findViewById(R.id.editTextTextPersonName6) as EditText


        val mHMondaiDatabase = M_H_MondaiDatabase.getInstance(this)
        val mhMondaiDao = mHMondaiDatabase.M_H_MondaiDao()

        val mMMondaiDatabase = M_M_MondaiDatabase.getInstance(this)
        val mMMondaiDao = mMMondaiDatabase.M_M_MondaiDao()


        val mhMondai = M_H_Mondai(no.text.toString().toInt(), naiyoText.text.toString())

        //upadte
        val rowId = mhMondaiDao.delete(mhMondai)

        val flg1: RadioButton = findViewById(R.id.radioButton) as RadioButton
        val flg2: RadioButton = findViewById(R.id.radioButton2) as RadioButton
        val flg3: RadioButton = findViewById(R.id.radioButton3) as RadioButton
        val flg4: RadioButton = findViewById(R.id.radioButton4) as RadioButton

        val mMondai1 = M_M_Mondai(no.text.toString().toInt(), 1, sentakushi1.text.toString(), flg1.isChecked)
        val mMondai2 = M_M_Mondai(no.text.toString().toInt(), 2, sentakushi2.text.toString(), flg2.isChecked)
        val mMondai3 = M_M_Mondai(no.text.toString().toInt(), 3, sentakushi3.text.toString(), flg3.isChecked)
        val mMondai4 = M_M_Mondai(no.text.toString().toInt(), 4, sentakushi4.text.toString(), flg4.isChecked)

        mMMondaiDao.delete(mMondai1)
        mMMondaiDao.delete(mMondai2)
        mMMondaiDao.delete(mMondai3)
        mMMondaiDao.delete(mMondai4)

        AlertDialog.Builder(this)
                .setTitle("confirmation")
                .setMessage("削除しました！")
                .setPositiveButton("OK",{dialog,which ->

                })
                .show()

    }



    fun koshin_shori(view: View){

        val no: TextView = findViewById(R.id.textView6) as TextView
        val naiyoText: EditText = findViewById(R.id.editTextTextPersonName2) as EditText
        val sentakushi1: EditText = findViewById(R.id.editTextTextPersonName3) as EditText
        val sentakushi2: EditText = findViewById(R.id.editTextTextPersonName4) as EditText
        val sentakushi3: EditText = findViewById(R.id.editTextTextPersonName5) as EditText
        val sentakushi4: EditText = findViewById(R.id.editTextTextPersonName6) as EditText


        val mHMondaiDatabase = M_H_MondaiDatabase.getInstance(this)
        val mhMondaiDao = mHMondaiDatabase.M_H_MondaiDao()

        val mMMondaiDatabase = M_M_MondaiDatabase.getInstance(this)
        val mMMondaiDao = mMMondaiDatabase.M_M_MondaiDao()


        val mhMondai = M_H_Mondai(no.text.toString().toInt(), naiyoText.text.toString())

        //upadte
        val rowId = mhMondaiDao.update(mhMondai)

        val flg1: RadioButton = findViewById(R.id.radioButton) as RadioButton
        val flg2: RadioButton = findViewById(R.id.radioButton2) as RadioButton
        val flg3: RadioButton = findViewById(R.id.radioButton3) as RadioButton
        val flg4: RadioButton = findViewById(R.id.radioButton4) as RadioButton

        val mMondai1 = M_M_Mondai(no.text.toString().toInt(), 1, sentakushi1.text.toString(), flg1.isChecked)
        val mMondai2 = M_M_Mondai(no.text.toString().toInt(), 2, sentakushi2.text.toString(), flg2.isChecked)
        val mMondai3 = M_M_Mondai(no.text.toString().toInt(), 3, sentakushi3.text.toString(), flg3.isChecked)
        val mMondai4 = M_M_Mondai(no.text.toString().toInt(), 4, sentakushi4.text.toString(), flg4.isChecked)

        mMMondaiDao.update(mMondai1)
        mMMondaiDao.update(mMondai2)
        mMMondaiDao.update(mMondai3)
        mMMondaiDao.update(mMondai4)

        AlertDialog.Builder(this)
            .setTitle("confirmation")
            .setMessage("更新しました！")
            .setPositiveButton("OK",{dialog,which ->

            })
            .show()
    }


    fun modoru_shori(view: View) {

        val intent = Intent(this, QuizListView::class.java)
        startActivity(intent)
    }
}