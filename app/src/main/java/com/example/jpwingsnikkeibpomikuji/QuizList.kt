package com.example.jpwingsnikkeibpomikuji

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ListView

class QuizList : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_quiz_list)
    }


    fun movetoQuizListView(view: View) {
        val intent: Intent = Intent(
            this@QuizList,
            QuizListView::class.java
        )

        val editText: EditText = findViewById(R.id.editTextTextPersonName) as EditText
        val naiyo: String = editText.text.toString()
        Log.d("MyappPutしてる",naiyo)
        intent.putExtra("naiyo",naiyo)
        startActivity(intent)
    }

    fun movetoShinkiToroku(view: View) {
        val intent: Intent = Intent(
            this@QuizList,
            shinki_toroku::class.java
        )

        startActivity(intent)
    }


    fun modoru_shori(view: View) {

        finish()
    }
}