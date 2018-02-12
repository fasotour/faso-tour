package com.example.fasotour;

public class Ligne {
	
	int id_ligne;
	String nom_ligne;
	public int getId() {
		return id_ligne;
	}
	public void setId(int id_ligne) {
		this.id_ligne = id_ligne;
	}
	public String getNom() {
		return nom_ligne;
	}
	public void setNom(String nom_ligne) {
		this.nom_ligne = nom_ligne;
	}
	public Ligne() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Ligne(int id_ligne, String nom_ligne) {
		super();
		this.id_ligne = id_ligne;
		this.nom_ligne = nom_ligne;
	}

}
