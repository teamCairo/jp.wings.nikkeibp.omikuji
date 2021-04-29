package com.example.jpwingsnikkeibpomikuji

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import org.apache.poi.ss.usermodel.CellType
import org.apache.poi.ss.usermodel.WorkbookFactory
//import org.apache.poi.ss.usermodel.WorkbookFactory

import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.FileOutputStream
import java.nio.file.Paths


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

        //取込処理なのでEXCELファイルがないからエラーになる。

        val file = Paths.get("PoiSampleWorkbook.xlsx").toFile()
        val book = WorkbookFactory.create(file)
        val sheet = book.getSheet("Sheet1")
        sheet.rowIterator().asSequence().forEach { row ->
            val values = row.cellIterator().asSequence().map { cell ->
                when (cell.cellType) {
                    CellType.NUMERIC -> cell.numericCellValue.toString()
                    CellType.STRING -> cell.stringCellValue
                    else -> throw RuntimeException("CellType=${cell.cellType}]")
                }
            }
            println(values.toList())
        }

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