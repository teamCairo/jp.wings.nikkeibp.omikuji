package com.example.jpwingsnikkeibpomikuji

//import org.apache.poi.ss.usermodel.WorkbookFactory

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import org.apache.poi.ss.usermodel.Workbook
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.File
import java.io.FileNotFoundException
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
        verifyStoragePermissions(this)

        val workbook: Workbook =XSSFWorkbook()
        val sheet  = workbook.createSheet("Ripon")
        val row = sheet.createRow(0)
        val cell = row.createCell(0)

        cell.setCellValue("テスト")

        val downloadPath:String = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath()
        val fileOutputStream:FileOutputStream
        try {
            val fileOutputStream = FileOutputStream(downloadPath + File.separator + "test.xlsx")
            workbook.write(fileOutputStream)
            fileOutputStream.close()

        }catch(e : FileNotFoundException){
            val yourFile = File(downloadPath + File.separator + "test.xlsx")
            yourFile.createNewFile() // if file already exists will do nothing
            val fileOutputStream = FileOutputStream(yourFile, false)
            workbook.write(fileOutputStream)
            fileOutputStream.close()
        }finally{

        }






/*
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
        }*/

    }


    fun excelKoshin(view: View){

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 111 && resultCode == RESULT_OK ) {
            val selectedFile:String? = data?.data?.path //The uri with the location of the file
        }
    }

    private val REQUEST_EXTERNAL_STORAGE_CODE = 0x01
    private val mPermissions = arrayOf<String>(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE)


    private fun verifyStoragePermissions(activity: Activity) {
        val readPermission = ContextCompat.checkSelfPermission(activity, mPermissions[0])
        val writePermission = ContextCompat.checkSelfPermission(activity, mPermissions[1])
        if (writePermission != PackageManager.PERMISSION_GRANTED ||
                readPermission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    activity,
                    mPermissions,
                    REQUEST_EXTERNAL_STORAGE_CODE
            )
        }
    }

}