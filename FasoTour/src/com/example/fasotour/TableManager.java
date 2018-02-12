 package com.example.fasotour;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class TableManager {
	
	DataBaseManager dbm;
	SQLiteDatabase db;
	
	public TableManager (Context ctx){
		dbm = new DataBaseManager(ctx);
	}
	
	public void open(){
		db = dbm.getWritableDatabase();
	}
	
	public void close(){
		db.close();
	}
	
	//==================================================================================================================================
	
	/*----------------------------/
   / Gestion de la table Agence  /
  /----------------------------*/
	
	
	public long AddAgence(Agence agence){//ajout d'Agences
		
		ContentValues vals = new ContentValues();
		vals.put("id_agence", agence.getId());
		vals.put("nom_agence", agence.getNom());
		return db.insert("Agence", null, vals);
		
	}
	
	public int DeleteAgence(int id){//suppression d'Agences
		
		return db.delete("Agence", "id_agence="+id, null);
		
	}
	
	public Agence FindAgence(int id){//recherche d'Agences
		
		Agence agence = null;
		Cursor cur = db.query("Agence", new String[] {"id_agence", "nom_agence"}, "id_agence="+id, null, null, null, null);
		if(cur.moveToFirst()){
			agence = new Agence();
			agence.setId(cur.getInt(0));
			agence.setNom(cur.getString(1));
			
		}
		return agence;
		
	}
	
public ArrayList<Agence> AllAgence(){//toutes les Agences
		
	ArrayList<Agence> agenceListe = new ArrayList<Agence>();
		Cursor cur = db.query("Agence", new String[] {"id_agence", "nom_agence"}, null, null, null, null, null);
		cur.moveToFirst();
		while(!cur.isAfterLast()){
			Agence agence = new Agence();
			agence.setId(cur.getInt(0));
			agence.setNom(cur.getString(1));
			agenceListe.add(agence);
			cur.moveToNext();
			
		}
		return agenceListe;
		
	}
public String[] AllNomAgence(){//tous les noms d'agence
	
	String[] noms;
	
	Cursor cur = db.query("Agence", new String[] {"id_agence", "nom_agence"}, null, null, null, null, null);
	int dimen = cur.getCount();
	noms=new String[dimen];
	cur.moveToFirst();
	int i=0;
	while(!cur.isAfterLast()){
		
		noms[i]=cur.getString(1);
		i++;
		cur.moveToNext();
		
	}
	return noms; 
}



//==================================================================================================================================

  /*----------------------------/
 / Gestion de la table Ligne   /
/----------------------------*/
	
	
	public long AddLigne(Ligne ligne){//ajout de ligne
		
		ContentValues vals = new ContentValues();
		vals.put("id_ligne", ligne.getId());
		vals.put("nom_ligne", ligne.getNom());
		return db.insert("Ligne", null, vals);
		
	}
	
	public int DeleteLigne(int id){//suppression de ligne
		
		return db.delete("Ligne", "id_ligne="+id, null);
		
	}
	
	public Ligne FindLigne(int id){//recherche de ligne
		
		Ligne ligne = null;
		Cursor cur = db.query("Ligne", new String[] {"id_ligne", "nom_ligne"}, "id_ligne="+id, null, null, null, null);
		if(cur.moveToFirst()){
			ligne = new Ligne();
			ligne.setId(cur.getInt(0));
			ligne.setNom(cur.getString(1));
			
		}
		return ligne;
		
	}
	
public ArrayList<Ligne> AllLigne(){//toutes les Lignes
		
	ArrayList<Ligne> ligneListe = new ArrayList<Ligne>();
		Cursor cur = db.query("Ligne", new String[] {"id_ligne", "nom_ligne"}, null, null, null, null, null);
		cur.moveToFirst();
		while(!cur.isAfterLast()){
			Ligne ligne = new Ligne();
			ligne.setId(cur.getInt(0));
			ligne.setNom(cur.getString(1));
			ligneListe.add(ligne);
			cur.moveToNext();
			
		}
		return ligneListe;
		
	}
public String[] AllNomLigne(){//tous les noms des lignes
	
	String[] noms;
	
	Cursor cur = db.query("Ligne", new String[] {"id_ligne", "nom_ligne"}, null, null, null, null, null);
	int dimen = cur.getCount();
	noms=new String[dimen];
	cur.moveToFirst();
	int i=0;
	while(!cur.isAfterLast()){
		
		noms[i]=cur.getString(1);
		i++;
		cur.moveToNext();
		
	}
	return noms; 
}


public String Ligne_de_Voyage(String depart, String destination){//Methode pour trouver les lignes de voyage
	
	String result;
	
	Cursor cur = db.query("Ligne", new String[] {"id_ligne", "nom_ligne"}, null, null, null, null, null);
	result="";
	cur.moveToFirst();
	while(!cur.isAfterLast()){
		
		//if(cur.getString(1).contains(depart) && cur.getString(1).contains(destination)){// si ligne contient les chaines depart et destination
		if(cur.getString(1).equals(depart+"-"+destination)){
		result=cur.getString(0);// on recupère la ligne
		
		}
	    
	    cur.moveToNext();
	}
	return result; 
}



//==================================================================================================================================

/*------------------------------/
/ Gestion de la table Exploite /
/----------------------------*/


public String[] Exploite_Voyage(int id){//Methode pour trouver les agences et les heures en fonction de la ligne
	
	String []result;
	
	Cursor cur = db.query("Exploite as Exp inner join Agence as Ag on Exp.id_agence=Ag.id_agence", new String[] {"Ag.nom_agence","Exp.heure"}, "Exp.id_ligne="+id, null, null, null, null);
	int dimen = cur.getCount();
	result=new String[dimen];
	cur.moveToFirst();
	int i=0;
	while(!cur.isAfterLast()){
		
		result[i]="       "+cur.getString(0)+"         "+cur.getString(1);
		i++;
		cur.moveToNext();
	}
	return result; 
}



//------------------------------------------------------------------------------------------------------------------------

public ArrayList<CompagniesHoraire> Compagnie_et_Horaire(int id){//toutes les compagnies et leurs heures
	
	ArrayList<CompagniesHoraire> CompagnieHoraireListe = new ArrayList<CompagniesHoraire>();
	Cursor cur = db.query("Exploite as Exp inner join Agence as Ag on Exp.id_agence=Ag.id_agence", new String[] {"Ag.nom_agence","Exp.heure"}, "Exp.id_ligne="+id, null, null, null, null);
		cur.moveToFirst();
		while(!cur.isAfterLast()){
			CompagniesHoraire compHor = new CompagniesHoraire();
			compHor.setCompagnie("    "+cur.getString(0));
			compHor.setHeure("          "+cur.getString(1));
			CompagnieHoraireListe.add(compHor);
			cur.moveToNext();
			
			
		}
		return CompagnieHoraireListe;
		
	}



public ArrayList<CompagniesHoraire> Tri_Compagnie_et_Horaire(int id, String heure){//toutes les compagnies et leurs heures triées en fonction de l'heure
	
	ArrayList<CompagniesHoraire> CompagnieHoraireListe = new ArrayList<CompagniesHoraire>();
	Cursor cur = db.query("Exploite as Exp inner join Agence as Ag on Exp.id_agence=Ag.id_agence", new String[] {"Ag.nom_agence","Exp.heure"}, "Exp.id_ligne="+id, null, null, null, null);
	
	int heur=Integer.parseInt(heure.substring(0,2));//extraction des deux premieres lettres
	
	cur.moveToFirst();
		while(!cur.isAfterLast()){
			CompagniesHoraire compHor = new CompagniesHoraire();
			if(Integer.parseInt(cur.getString(1).substring(0, 2))>=heur){//Comparaison
			compHor.setCompagnie("    "+cur.getString(0));
			compHor.setHeure("          "+cur.getString(1));
			CompagnieHoraireListe.add(compHor);
			}
			cur.moveToNext();
			
			
		}
		return CompagnieHoraireListe;
		
	}



public ArrayList<CompagniesHoraire> Vide(){//rien afficher
	
	ArrayList<CompagniesHoraire> CompagnieHoraireListe = new ArrayList<CompagniesHoraire>();
	
			CompagniesHoraire compHor = new CompagniesHoraire();
			compHor.setCompagnie("    ");
			CompagnieHoraireListe.add(compHor);

		return CompagnieHoraireListe;
		
	}



}
