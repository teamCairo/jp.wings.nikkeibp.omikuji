package com.example.jpwingsnikkeibpomikuji

//import org.apache.poi.ss.usermodel.WorkbookFactory

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import org.apache.poi.ss.usermodel.*
import org.apache.poi.ss.util.CellUtil
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.nio.file.Paths


class ExcelMaintenance : AppCompatActivity() {


    private val CHOSE_FILE_CODE: Int = 12345

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_excel_maintenance)
    }

    fun excelToroku(view: View){
        verifyStoragePermissions(this)
        /*
        val intent = Intent()
            .setType("★アスタリスクに置き換え/★アスタリスクに置き換え")
            .setAction(Intent.ACTION_GET_CONTENT)
        startActivityForResult(Intent.createChooser(intent, "Select a file"), 111)

        */

        try {
            val uploadPath: String = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath()
            val file = Paths.get(uploadPath + File.separator + "torokutest.xlsx").toFile()
            Log.d("MyApp", file.exists().toString())
            val book: Workbook = WorkbookFactory.create(file)
        }catch(e:Exception){
            Log.d("Myapp",e.stackTrace.toString())
            Log.d("Myapp",e.message.toString())
        }
        /*
        val sheet = book.getSheet("mondai")

        sheet.rowIterator().asSequence().forEach { row ->
            val values = row.cellIterator().asSequence().map { cell ->
                when (cell.cellType) {
                    CellType.NUMERIC -> cell.numericCellValue.toString()
                    CellType.STRING -> cell.stringCellValue
                    else -> throw RuntimeException("CellType=${cell.cellType}]")
                }
            }
            values.toList().forEach{
            Log.d("MyApp",it)
            }
        }*/

    }


    fun excelShutsuryoku(view: View){
        verifyStoragePermissions(this)

        val workbook: Workbook =XSSFWorkbook()
        val sheet  = workbook.createSheet("Mondai")

        //output mondai
        val mHMondaiDatabase=M_H_MondaiDatabase.getInstance(this)
        val mhMondaiDao=mHMondaiDatabase.M_H_MondaiDao()
        val mMMondaiDatabase=M_M_MondaiDatabase.getInstance(this)
        val mmMondaiDao=mMMondaiDatabase.M_M_MondaiDao()

        val mhMondaiList:List<M_H_Mondai> = mhMondaiDao.getAll()

        var mmMondai1:M_M_Mondai
        var mmMondai2:M_M_Mondai
        var mmMondai3:M_M_Mondai
        var mmMondai4:M_M_Mondai
        var sentaku_naiyo1:String
        var sentaku_naiyo2:String
        var sentaku_naiyo3:String
        var sentaku_naiyo4:String
        val seikai_flg:Boolean
        var i:Int=0


        //create header
        var row = sheet.createRow(i)

        var cell = row.createCell(0)
        cell.setCellValue("ID")
        cell = row.createCell(1)
        cell.setCellValue("問題内容")
        cell = row.createCell(2)
        cell.setCellValue("選択肢内容1")
        cell = row.createCell(3)
        cell.setCellValue("選択肢内容2")
        cell = row.createCell(4)
        cell.setCellValue("選択肢内容3")
        cell = row.createCell(5)
        cell.setCellValue("選択肢内容4")
        cell = row.createCell(6)
        cell.setCellValue("正解No")

        i++

        mhMondaiList.forEach{
            mmMondai1 = mmMondaiDao.getByKey(it.id,1)
            mmMondai2 = mmMondaiDao.getByKey(it.id,2)
            mmMondai3 = mmMondaiDao.getByKey(it.id,3)
            mmMondai4 = mmMondaiDao.getByKey(it.id,4)

            row = sheet.createRow(i)

            cell = row.createCell(0)
            cell.setCellValue(it.id.toString())
            cell = row.createCell(1)
            cell.setCellValue(it.mondai_naiyo)
            cell = row.createCell(2)
            cell.setCellValue(mmMondai1.sentaku_naiyo)
            cell = row.createCell(3)
            cell.setCellValue(mmMondai2.sentaku_naiyo)
            cell = row.createCell(4)
            cell.setCellValue(mmMondai3.sentaku_naiyo)
            cell = row.createCell(5)
            cell.setCellValue(mmMondai4.sentaku_naiyo)
            cell = row.createCell(6)

            if(mmMondai1.seikai_Flg==true) {
                cell.setCellValue("1")
            }else if(mmMondai2.seikai_Flg==true){
                cell.setCellValue("2")

            }else if(mmMondai3.seikai_Flg==true){
                cell.setCellValue("3")

            }else{
                cell.setCellValue("4")

            }

            i++
        }

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



    }

    fun exceltempShutsuryoku(view: View){
        verifyStoragePermissions(this)

        val workbook: Workbook =XSSFWorkbook()
        val sheet  = workbook.createSheet("Mondai")

        var row = sheet.createRow(0)

        var cell = row.createCell(0)
        cell.setCellValue("問題内容")
        cell = row.createCell(1)
        cell.setCellValue("選択肢内容1")
        cell = row.createCell(2)
        cell.setCellValue("選択肢内容2")
        cell = row.createCell(3)
        cell.setCellValue("選択肢内容3")
        cell = row.createCell(4)
        cell.setCellValue("選択肢内容4")
        cell = row.createCell(5)
        cell.setCellValue("正解No")

        val downloadPath:String = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getAbsolutePath()
        val fileOutputStream:FileOutputStream
        try {
            val fileOutputStream = FileOutputStream(downloadPath + File.separator + "torokutesttemplate.xlsx")
            workbook.write(fileOutputStream)
            fileOutputStream.close()

        }catch(e : FileNotFoundException){
            val yourFile = File(downloadPath + File.separator + "torokutesttemplate.xlsx")
            yourFile.createNewFile() // if file already exists will do nothing
            val fileOutputStream = FileOutputStream(yourFile, false)
            workbook.write(fileOutputStream)
            fileOutputStream.close()
        }finally{

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