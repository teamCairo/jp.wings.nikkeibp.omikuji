package com.example.jpwingsnikkeibpomikuji

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Context
import android.widget.TextView

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val TextView: TextView = findViewById<TextView>(R.id.resultLabel)
        var score = intent.getIntExtra(
            "RIGHT_ANSWER_COUNT", 0
        )
        //resultLabel.setText(score.toString()+ "/5")

        val resultLabel = findViewById<TextView>(R.id.resultLabel)
        //val totalScoreLabel = findViewById<TextView>(R.id.totalScoreLabel)

        //正解数を取得
        score = intent.getIntExtra("RIGHT_ANSWER_COUNT", 0)

        val prefs = getSharedPreferences("quizApp", Context.MODE_PRIVATE)
        //var totalScore = prefs.getInt("totalScore", 0)

        //トータルスコアに今回のスコアを加算
        //totalScore += score

       //テキスト表示
        resultLabel.text = "$score /"
        //totalScoreLabel.text = ""

        //トータルスコアを保存
        //val editor = prefs.edit()
        //editor.putInt("totalScore", totalScore)
        //editor.apply()
    }
}


//最終スコアのデータ受け取り→表示