package com.example.taoying.utils

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import java.util.*
import com.example.taoying.models.Descriptions

class SqliteUtils(context: Context) : SQLiteOpenHelper(context, SqliteUtils.DB_NAME, null, SqliteUtils.DB_VERSION) {

    companion object {
        private val DB_VERSION = 1
        private val DB_NAME = "MyDB"
        private val TABLE_NAME = "Descriptions"
        private val ID = "Id"
        private val TITLE = "title"
        private val PLACE = "place"
        private val DATE = "date"
    }
    override fun onCreate(db: SQLiteDatabase) {
        val sql = "CREATE TABLE $TABLE_NAME ($ID INTEGER PRIMARY KEY, $TITLE TEXT,$PLACE TEXT,$DATE TEXT);"
        db.execSQL(sql)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        val sql = "DROP TABLE IF EXISTS $TABLE_NAME"
        db.execSQL(sql)
        onCreate(db)
    }

    fun description(): List<Descriptions> {
        val descriptionList = ArrayList<Descriptions>()
        val db = writableDatabase
        val sql = "SELECT * FROM $TABLE_NAME"
        val cursor = db.rawQuery(sql, null)
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    val descriptions = Descriptions()
                    descriptions.id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(ID)))
                    descriptions.title = cursor.getString(cursor.getColumnIndex(TITLE))
                    descriptions.place = cursor.getString(cursor.getColumnIndex(PLACE))
                    descriptions.date = cursor.getString(cursor.getColumnIndex(DATE))
                    descriptionList.add(descriptions)
                } while (cursor.moveToNext())
            }
        }
        cursor.close()
        return descriptionList
    }

    fun addDescription(descriptions: Descriptions): Boolean {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(TITLE, descriptions.title)
        values.put(PLACE, descriptions.place)
        values.put(DATE, descriptions.date)
        val success = db.insert(TABLE_NAME, null, values)
        db.close()
        Log.v("InsertedId", "$success")
        return (Integer.parseInt("$success") != -1)
    }

    fun getDescription(_id: Int): Descriptions {
        val descriptionList = Descriptions()
        val db = writableDatabase
        val sql = "SELECT  * FROM $TABLE_NAME WHERE $ID = $_id"
        val cursor = db.rawQuery(sql, null)

        cursor?.moveToFirst()
        descriptionList.id = Integer.parseInt(cursor.getString(cursor.getColumnIndex(ID)))
        descriptionList.title = cursor.getString(cursor.getColumnIndex(TITLE))
        descriptionList.place = cursor.getString(cursor.getColumnIndex(PLACE))
        descriptionList.date = cursor.getString(cursor.getColumnIndex(DATE))
        cursor.close()
        return descriptionList
    }

    fun updateDescription(descriptions: Descriptions): Boolean {
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(TITLE, descriptions.title)
        values.put(PLACE, descriptions.place)
        values.put(DATE, descriptions.date)
        val success = db.update(TABLE_NAME, values, ID + "=?", arrayOf(descriptions.id.toString())).toLong()
        db.close()
        return Integer.parseInt("$success") != -1
    }

    fun deleteDescription(_id: Int): Boolean {
        val db = this.writableDatabase
        val success = db.delete(TABLE_NAME, ID + "=?", arrayOf(_id.toString())).toLong()
        db.close()
        return Integer.parseInt("$success") != -1
    }

    fun deleteAllDescriptions(): Boolean {
        val db = this.writableDatabase
        val success = db.delete(TABLE_NAME, null, null).toLong()
        db.close()
        return Integer.parseInt("$success") != -1
    }
}