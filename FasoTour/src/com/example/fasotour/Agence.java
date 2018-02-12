package com.example.fasotour;

public class Agence {
	
	int id_agence;
	String nom_agence;
	public int getId() {
		return id_agence;
	}
	public void setId(int id_agence) {
		this.id_agence = id_agence;
	}
	public String getNom() {
		return nom_agence;
	}
	public void setNom(String nom_agence) {
		this.nom_agence = nom_agence;
	}
	public Agence() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Agence(int id_agence, String nom_agence) {
		super();
		this.id_agence = id_agence;
		this.nom_agence = nom_agence;
	}

}
