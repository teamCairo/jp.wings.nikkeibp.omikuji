package com.example.jpwingsnikkeibpomikuji

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class QuizListView : AppCompatActivity() {

    var kensakuKey:String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_list_view)

        // Activity開始時にIntentを取得し、文字列をセットする
        val intent: Intent = getIntent()
        kensakuKey = intent.getStringExtra("naiyo").toString()


        Log.d("MyAppget",kensakuKey)

        val mHMondaiDatabase = M_H_MondaiDatabase.getInstance(this)
        val mhMondaiDao = mHMondaiDatabase.M_H_MondaiDao()

        Log.d("MyApp",kensakuKey)
        val mhMondaiList: List<M_H_Mondai> = mhMondaiDao.getbyNaiyo(kensakuKey)
        Log.d("MyAppSize",mhMondaiList.size.toString())

        val mhMondais = M_H_MondaiList(mhmondais = mhMondaiList)

        val listView:ListView = findViewById(R.id.list_view)


        if(mhMondais.mhmondaiCount()==0){

            Log.d("MyApp","no result")
            AlertDialog.Builder(this)
                .setTitle("alert")
                .setMessage("検索結果がありませんでした")
                .setPositiveButton("OK",{dialog,which ->
                }).show()

        }else{

            if(listView != null){
                Log.d("MyApp","null です")
            }else{
                Log.d("MyApp","null じゃないです")

            }
            Log.d("MyApp","yes result")
            listView.adapter = ListBindingAdapter(this, mhMondais)
        }


        listView.setOnItemClickListener { adapterView, _, position, _ ->
            val hmmondai = adapterView.getItemAtPosition(position) as M_H_Mondai

            val intent: Intent = Intent(
                    this@QuizListView,
                    MondaiShosai::class.java
            )

            Log.d("MyAppput",kensakuKey)

            Log.d("MyAppputId",hmmondai.id.toString())
            intent.putExtra("ID",hmmondai.id.toString())
            intent.putExtra("kensakuKey",kensakuKey)
            startActivity(intent)

        }


    }


    fun modoru_shori(view: View) {

        finish()
    }

}