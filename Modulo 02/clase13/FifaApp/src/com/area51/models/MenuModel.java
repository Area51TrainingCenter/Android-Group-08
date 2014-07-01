package com.area51.models;

public class MenuModel {

	protected int imagen;
	protected String titulo;

	public MenuModel(int imagen, String titulo) {
		super();
		this.imagen = imagen;
		this.titulo = titulo;
	}

	public int getImagen() {
		return imagen;
	}

	public void setImagen(int imagen) {
		this.imagen = imagen;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

}
