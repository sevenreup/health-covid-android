package com.skybox.seven.covid.data

import android.database.sqlite.SQLiteDatabase
import android.content.*
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import android.widget.Toast
import com.skybox.seven.covid.model.SelfTestModel

class databaseHandler2(context: Context, name: String?, factory: SQLiteDatabase.CursorFactory?, version: Int ): SQLiteOpenHelper(context
        ,DB_NAME, factory, DB_VERSION){

    companion object{
        private var DB_NAME = "Selftest.db"
        private var DB_VERSION = 1

        val TABLE_NAME = "Results"
        val COLUMN_ID = "ColumnId"
        val COLUMN_RESPONSE = "Reply"
        val COLUMN_STATUS = "Status"


    }

    override fun onCreate(db: SQLiteDatabase?) {

        val QUERY_CREATE: String = ("CREATE TABLE $TABLE_NAME (" + "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT," +
                "$COLUMN_RESPONSE TEXT," +
                "$COLUMN_STATUS TEXT)")
        db?.execSQL(QUERY_CREATE)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun getCurrentTest(ctx: Context): ArrayList<SelfTestResult>{
        val QUERY_GET = "SELECT * FROM $TABLE_NAME"
        val db = this.readableDatabase
        val cursor = db.rawQuery(QUERY_GET, null)
        val tests = ArrayList<SelfTestResult>()

        if(cursor.count == 0){
            Toast.makeText(ctx, "No previous test records found", Toast.LENGTH_LONG).show()
        }else{
            while (cursor.moveToNext()){
                val self = SelfTestResult()

                self.id = cursor.getInt(cursor.getColumnIndex(COLUMN_ID))
                self.reply = cursor.getString(cursor.getColumnIndex(COLUMN_RESPONSE))
                self.status = cursor.getString(cursor.getColumnIndex(COLUMN_STATUS))



                tests.add(self)

            }
            Toast.makeText(ctx, "${cursor.count.toString()} test records found", Toast.LENGTH_LONG).show()
        }
        cursor.close()
        db.close()
        return tests
    }

    fun addTest(mCtx: Context, self: SelfTestResult){
        val values = ContentValues()
        values.put(COLUMN_RESPONSE, self.reply)
        values.put(COLUMN_STATUS, self.status)

        val db = this.writableDatabase
        try {
            db.insert(TABLE_NAME, null, values)
            Toast.makeText(mCtx, "Self test successfully perfomed!", Toast.LENGTH_LONG).show()
        }
        catch (e: Exception){
            Toast.makeText(mCtx, e.message, Toast.LENGTH_LONG).show()
        }
        db.close()
    }

    fun deleteTest(columnId: Int): Boolean{
        val query = "DELETE FROM $TABLE_NAME WHERE $COLUMN_ID = $columnId"
        val db = this.writableDatabase
        var result: Boolean = false
        try {
            val cursor = db.execSQL(query)
            result = true
        }catch (e: Exception){
            Log.d(ContentValues.TAG, e.message)
        }
        db.close()
        return result
    }

}