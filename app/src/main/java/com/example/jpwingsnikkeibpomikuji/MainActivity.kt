package com.example.quizapplication

import android.content.DialogInterface
import android.content.Intent
import android.app.AlertDialog
import android.app.Dialog
import android.view.View
import android.widget.Button
import android.widget.TextView
import java.util.*
import android.R.id as id1
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    //ラベル・ボタンプロパティの定義
    private var countLabel: TextView? = null
    private var questionLabel: TextView? = null
    private var answerBtn1: Button? = null
    private var answerBtn2: Button? = null
    private var answerBtn3: Button? = null
    private var answerBtn4: Button? = null
    private var rightAnswer: String ? = null
    private var rightAnswerCount = 0
    private var quizCount = 1


    //空配列定義（文字列）
    var quizArray = ArrayList<ArrayList<String?>>()
    //arrayOf(arrayOf("","","",""),arrayOf("","","",""),...)
    var quizData = arrayOf(arrayOf("北海道", "札幌市", "長崎市", "福島市", "前橋市"), arrayOf("青森県", "青森市", "広島市", "甲府市", "岡山市"), arrayOf("岩手県", "盛岡市", "大分市", "秋田市", "福岡市"), arrayOf("宮城県", "仙台市", "水戸市", "岐阜市", "福井市"), arrayOf("秋田県", "秋田市", "横浜市", "鳥取市", "仙台市"), arrayOf("山形県", "山形市", "青森市", "山口市", "奈良市"), arrayOf("福島県", "福島市", "盛岡市", "新宿区", "京都市"), arrayOf("茨城県", "水戸市", "金沢市", "名古屋市", "奈良市"), arrayOf("栃木県", "宇都宮市", "札幌市", "岡山市", "奈良市"), arrayOf("群馬県", "前橋市", "福岡市", "松江市", "福井市"))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("MyApp", "遷移しました")

        // Activity開始時にIntentを取得し、文字列をセットする
        val intent: Intent = getIntent()


        //intent intent = getIntent()
        //クイズ出題数を受信
        var QUIZ_COUNT = intent.getIntExtra("QUIZ_QUANTITY", 5)
        //上で定義したラベル・ボタンに中身をidで紐付け
        countLabel = this.findViewById<Button>(R.id.countLabel)
        questionLabel = this.findViewById<Button>(R.id.questionLabel)
        answerBtn1 = this.findViewById<Button>(R.id.answerBtn1)
        answerBtn2 = this.findViewById<Button>(R.id.answerBtn2)
        answerBtn3 = this.findViewById<Button>(R.id.answerBtn3)
        answerBtn4 = this.findViewById<Button>(R.id.answerBtn4)

        //データからクイズ出題用配列を設定
        for (i in quizData.indices) {
            val tmpArray = ArrayList<String?>()

            tmpArray.add(quizData[i][0]) //問題文
            tmpArray.add(quizData[i][1]) //正答
            tmpArray.add(quizData[i][2]) //選択肢1
            tmpArray.add(quizData[i][3]) //選択肢2
            tmpArray.add(quizData[i][4]) //選択肢3

            //問題解答セットを空配列へ
            quizArray.add(tmpArray)
        }
        //次の問題へ
        showNextQuiz()
    }

    private fun showNextQuiz() {

        //クイズをカウント
        countLabel!!.text = "Q$quizCount"

        //ランダムに数字を取得
        val random = Random()
        val randomNum = random.nextInt(quizArray.size)

        //↑を用いてクイズをランダムに取り出す
        val quiz = quizArray[randomNum]

        //問題文を表示
        questionLabel!!.text = quiz[0]

        //正解をrightAnswerに設定
        rightAnswer = quiz[1]

        //問題一覧から既出の問題文を削除
        quiz.removeAt(0)

        //正解と選択肢をシャッフル
        quiz.shuffle()

        //正解を含む選択肢を表示
        answerBtn1!!.text = quiz[0]
        answerBtn2!!.text = quiz[1]
        answerBtn3!!.text = quiz[2]
        answerBtn4!!.text = quiz[3]

        //出題した問題の選択肢セットを削除
        quizArray.removeAt(randomNum)
    }

    fun checkAnswer(view: View) {

        //どの選択肢が選ばれたか
        val answerBtn = findViewById<Button>(view.id)
        val btnText = answerBtn.text.toString()
        val alertTitle: String
        if (btnText == rightAnswer) {
            alertTitle = "正解！"
            rightAnswerCount++
        } else {
            alertTitle = "不正解!"
        }

        //ダイアログ設定
        val builder: AlertDialog.Builder = AlertDialog.Builder (this@MainActivity)
        builder.setTitle(alertTitle)
        builder.setMessage("答え： $rightAnswer")
        builder.setPositiveButton("OK", DialogInterface.OnClickListener { dialogInterface, i ->
            if (quizCount == 5) {
                val intent = Intent(applicationContext, ResultActivity::class.java)
                intent.putExtra("RIGHT_ANSWER_COUNT", rightAnswerCount)
                startActivity(intent)
            } else {
                quizCount++
                showNextQuiz()
            }
        })
        builder.setCancelable(false)
        builder.show()
    }
    //companion object {
        //private const val  QUIZ_COUNT = message
    //}
}
