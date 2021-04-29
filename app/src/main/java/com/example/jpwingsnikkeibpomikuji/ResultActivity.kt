package com.example.jpwingsnikkeibpomikuji

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.TextView

class ResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val TextView: TextView = findViewById<TextView>(R.id.resultLabel)
        var score = intent.getIntExtra(
                "RIGHT_ANSWER_COUNT", 0
        )

        //追加（出題数）
        //var Qquantity = intent.getIntExtra("QQUANTITY", 0)

        val resultLabel = findViewById<TextView>(R.id.resultLabel)


        //正解数を取得
        score = intent.getIntExtra("RIGHT_ANSWER_COUNT", 0)

        //追加　出題数を取得
        //Qquantity = intent.getIntExtra("QQUANTITY", 0)
        val prefs = getSharedPreferences("quizApp", Context.MODE_PRIVATE)
        //var totalScore = prefs.getInt("totalScore", 0)


        //テキスト表示
        resultLabel.text = "$score / 5"
        //↑/ $Qquantity

    }

    //追加　トップ画面への遷移
    fun moveToMainAct(view: View) {
        val intent: Intent = Intent(this@ResultActivity,
                MainActivity::class.java)
        startActivity(intent)

    }

    fun moveToMain(view: View) {
        val intent: Intent = Intent(this@ResultActivity,
                OmikujiActivity::class.java)
        startActivity(intent)
    }
}


