package com.example.jpwingsnikkeibpomikuji

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View


class OmikujiActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main)

    }

    fun moveToShutudaiSetting(view: View){
        val intent: Intent = Intent( this@OmikujiActivity,
            shutsudai::class.java)
        startActivity(intent)
    }

    fun moveQuizList(view: View){
        val intent: Intent = Intent( this@OmikujiActivity,
            QuizList::class.java)
        startActivity(intent)
    }
}