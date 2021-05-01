package com.example.jpwingsnikkeibpomikuji

import androidx.room.DatabaseView

@DatabaseView(
        viewName="MondaiDataforExcel",
                value = """ SELECT m_h_mondai.id
                    ,m_h_mondai.mondai_naiyo
                    FROM m_h_mondai LEFT OUTER JOIN m_m_mondai m1 on m_h_mondai.id = m1.id and m1.sentaku_id =1
                    LEFT OUTER JOIN m_m_mondai m2 on m_h_mondai.id = m2.id and m2.sentaku_id =2
                    LEFT OUTER JOIN m_m_mondai m3 on m_h_mondai.id = m3.id and m3.sentaku_id =3
                    LEFT OUTER JOIN m_m_mondai m4 on m_h_mondai.id = m4.id and m4.sentaku_id =4"""

)
/*

                    ,m1.sentaku_naiyo
                    ,m2.sentaku_naiyo
                    ,m3.sentaku_naiyo
                    ,m4.sentaku_naiyo
                    ,CASE
                        WHEN m1.seikai_Flg='true' THEN 1
                        WHEN m2.seikai_Flg='true' THEN 2
                        WHEN m3.seikai_Flg='true' THEN 3
                        WHEN m4.seikai_Flg='true' THEN 4
                    END


                    ,
        val sentaku_naiyo1: String?,
        val sentaku_naiyo2: String?,
        val sentaku_naiyo3: String?,
        val sentaku_naiyo4: String?,
        val seikai_no: Int


 */
data class MondaiDataforExcel(
        val id: Int,
        val mondai_Naiyo: String?
)