package com.area51.sqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import com.area51.utils.Constants;

public class ManageSqlite extends SQLiteOpenHelper {

	public ManageSqlite(Context context) {
		super(context, Constants.DB_NAME, null, Constants.DB_VERSION);

	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		db.execSQL(Constants.CREATE_TABLE);

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

		// PARA DISTINTAS VERSIONES HACER UNA MIGRACIÓN DE DATOS
		// COMPARANDO LAS DISTINTAS VERSIONES

		db.execSQL(Constants.DROP_TABLE);
		db.execSQL(Constants.CREATE_TABLE);

	}

}
