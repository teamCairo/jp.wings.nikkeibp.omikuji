package com.example.jpwingsnikkeibpomikuji

class M_H_MondaiList(private val mhmondais:List<M_H_Mondai> ){
    val count: Int = mhmondais.count()

    fun mhmondaiAt(index: Int): M_H_Mondai{
        return mhmondais[index]
    }


    fun mhmondaiCount(): Int{
        return mhmondais.count()
    }

}