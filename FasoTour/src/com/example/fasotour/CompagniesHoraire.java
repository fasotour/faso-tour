package com.example.fasotour;

public class CompagniesHoraire {
	
	String compagnie;
	String heure;
	
	public String getCompagnie() {
		return compagnie;
	}
	public void setCompagnie(String compagnie ) {
		this.compagnie = compagnie;
	}
	
	public String getHeure() {
		return heure;
	}
	public void setHeure(String heure) {
		this.heure = heure;
	}
	public CompagniesHoraire() {
		super();
	}
	public CompagniesHoraire(String compagnie, String heure) {
		super();
		this.compagnie=compagnie;
		this.heure = heure;
	}

}
