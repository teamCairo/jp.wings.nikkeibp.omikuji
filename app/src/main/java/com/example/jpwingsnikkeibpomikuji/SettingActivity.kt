package com.example.jpwingsnikkeibpomikuji

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.example.jpwingsnikkeibpomikuji.databinding.ActivitySettingBinding

class SettingActivity : AppCompatActivity() {

    private val spinnerItems = arrayOf("5", "10", "15", "20", "25", "30")

    private lateinit var binding: ActivitySettingBinding
    var selectedItem: String = ""



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        //Spinnerの取得
        val spinner = findViewById<Spinner>(R.id.spinner)

        //Adapterの生成
        val arrayAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, spinnerItems)

        //View binding
        binding.spinner.adapter = arrayAdapter

        //リスナーを登録
        binding.spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            //アイテムが選択されたとき
            override fun onItemSelected(parent: AdapterView<*>?,
                                        view: View?, positionn: Int, id: Long) {
                val spinnerParent = parent as Spinner
                val item = spinnerParent.selectedItem as String
                selectedItem = item
                //View Binding
                //binding.textView.text = item
            }

            //アイテムが選択されなかった時
            override fun onNothingSelected(parent: AdapterView<*>?) = Unit
        }

        //選択肢の各項目のレイアウト
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        //AdapterをSpinnerのAdapterとして設定
        spinner.adapter = arrayAdapter




        Log.d("MyApp", "これから遷移")
        binding.button.setOnClickListener {
            val intent = Intent(application, MainActivity::class.java)
            val str = selectedItem
            //
            //

            intent.putExtra("QUIZ_QUANTITY", str)
            startActivity(intent)
        }


    }
}


