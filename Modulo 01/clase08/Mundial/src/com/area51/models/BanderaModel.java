package com.area51.models;

public class BanderaModel {

	protected int idBandera;
	protected int imagenBandera;

	public BanderaModel(int idBandera, int imagenBandera) {
		super();
		this.idBandera = idBandera;
		this.imagenBandera = imagenBandera;
	}

	public int getIdBandera() {
		return idBandera;
	}

	public void setIdBandera(int idBandera) {
		this.idBandera = idBandera;
	}

	public int getImagenBandera() {
		return imagenBandera;
	}

	public void setImagenBandera(int imagenBandera) {
		this.imagenBandera = imagenBandera;
	}

}
