package com.example.fasotour;

public class SpinnerModel {
	
	private String VilleNames="";
	private String Image="";
	
	//======== Set Methods ========
	
	public void setVilleName(String VilleNames){ this.VilleNames=VilleNames; }
	public void setImage(String Image){ this.Image=Image; }

	
	//========= get Methods =======
	
	public String getVilleName(){ return this.VilleNames; }
	public String getImage(){ return this.Image; }
}
