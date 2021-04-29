package com.example.jpwingsnikkeibpomikuji

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
//import org.apache.poi.ss.usermodel.WorkbookFactory

import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.FileOutputStream


class ExcelMaintenance : AppCompatActivity() {


    private val CHOSE_FILE_CODE: Int = 12345

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_excel_maintenance)
    }

    fun excelToroku(view: View){

        val intent = Intent()
            .setType("*/*")
            .setAction(Intent.ACTION_GET_CONTENT)
        startActivityForResult(Intent.createChooser(intent, "Select a file"), 111)


    }


    fun excelShutsuryoku(view: View){

        val workBook = XSSFWorkbook()
        val sheet = workBook.createSheet()

        //セルを指定
        val cell = sheet.createRow(0).createCell(0)
        cell.setCellValue("test")

        //エクセルファイルを保存
        val fileOutputStream = FileOutputStream("test.xlsx")
        workBook.write(fileOutputStream)
        fileOutputStream.close()

    }


    fun excelKoshin(view: View){

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 111 && resultCode == RESULT_OK ) {
            val selectedFile:String? = data?.data?.path //The uri with the location of the file
        }
    }

}