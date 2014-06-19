package com.area51.utils;

public class Constants {

	public static String DB_NAME = "usuariosg8.db";
	public static int DB_VERSION = 1;

	public static String TABLE_USUARIOS = "tblusuario";
	public static String COL_USUARIO_ID = "id";
	public static String COL_USUARIO_NOMBRE = "nombre";
	public static String COL_USUARIO_USUARIO = "usuario";
	public static String COL_USUARIO_CLAVE = "clave";

	//PARA SELECCIONAR UNA COLUMNA
	public static int COL_USUARIO_ID_INDEX = 0;
	public static int COL_USUARIO_NOMBRE_INDEX = 1;
	public static int COL_USUARIO_USUARIO_INDEX = 2;
	public static int COL_USUARIO_CLAVE_INDEX = 3;

	
	public static String CREATE_TABLE = "CREATE TABLE " + TABLE_USUARIOS 
			+ "( " + COL_USUARIO_ID + " int primary key autoincrement , " 
			+ COL_USUARIO_NOMBRE + " text , " + 
			COL_USUARIO_USUARIO	+ " text , " +
			COL_USUARIO_CLAVE	+ " text  )";
	
	public static String DROP_TABLE = "DROP TABLE " + TABLE_USUARIOS;
	
	
		

}
