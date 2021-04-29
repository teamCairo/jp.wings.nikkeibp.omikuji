package com.example.jpwingsnikkeibpomikuji

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.jpwingsnikkeibpomikuji.databinding.ActivityQuizListViewBinding


class ListBindingAdapter(val context: Context, val mhmondais:M_H_MondaiList): BaseAdapter() {
    var inflater: LayoutInflater
    val mhmondai = mhmondais
//コメントテスト
    init{
        Log.d("MyApp","init start")
        inflater = LayoutInflater.from(context)
    }

    override fun getCount():Int{
        return mhmondais.count
    }


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?):View?{

        Log.d("MyApp","getView start")
        val view: View=LayoutInflater.from(context).inflate(R.layout.list_item,null)
        val mondaiId = view.findViewById<TextView>(R.id.mondaiId)
        val mondaiNaiyo = view.findViewById<TextView>(R.id.mondaiNaiyo)

        val mondai = mhmondais.mhmondaiAt(position)

        mondaiId.text = mondai.id.toString()
        mondaiNaiyo.text = mondai.mondai_naiyo

        return view

    }

/*
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?):View?{
        var binding: ActivityQuizListViewBinding?
        if(convertView==null){
            binding=ActivityQuizListViewBinding.inflate(inflater,parent,false)
            binding.root.tag=binding

        }else{
            binding=convertView.tag as ActivityQuizListViewBinding
        }
        binding?.mhmondai = getItem(position)as M_H_Mondai
        return binding?.root
    }
    */

    override fun getItem(position: Int):Any?{
        return mhmondais.mhmondaiAt(position)
    }

    override fun getItemId(position: Int):Long{
        return position.toLong()
    }


}