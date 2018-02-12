package com.example.fasotour;

import java.util.ArrayList;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity implements OnClickListener {
	
	//Declaration des objets
	public ArrayList<SpinnerModel> ListValue = new ArrayList<SpinnerModel>();
	public ArrayList<FiltreModel> Liste_des_valeurs = new ArrayList<FiltreModel>();
	TextView output=null;
	ListView liste;
	AdapterModel adapterModel;
	FiltreAdapter filtreAdapter;
	MainActivity activity=null;
	Spinner spinnerDepart;
	Spinner spinnerDestination;
	Spinner spinnerFiltre;
	Button valider;
	String texteDepart="";
	String texteDestination="";
	String texteHeure="";
	
	CompagniesHoraireAdapter compaAdapter;
	TableManager table_manager;
	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        activity=this;
        
        spinnerDepart=(Spinner)findViewById(R.id.spinner_depart);
        spinnerDestination=(Spinner)findViewById(R.id.spinner_destination);
        spinnerFiltre=(Spinner)findViewById(R.id.filtre_spinner);
         valider = (Button) findViewById(R.id.valider);
         liste = (ListView) findViewById(R.id.liste);
        
        setListData();
        setListValeur();
        Resources res=getResources();
        adapterModel=new AdapterModel(activity, R.layout.spinner, ListValue, res);
        filtreAdapter=new FiltreAdapter(activity, R.layout.item_filtre, Liste_des_valeurs, res);
        
        spinnerDepart.setAdapter(adapterModel);
        spinnerDestination.setAdapter(adapterModel);
        spinnerFiltre.setAdapter(filtreAdapter);
        
        
        spinnerDepart.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parentView, View v,
					int position, long id) {
				// TODO Auto-generated method stub
				
				//recuperation du texte dans le spinner depart
				texteDepart=((TextView)v.findViewById(R.id.villes)).getText().toString();
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
        
        
        
        spinnerDestination.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parentView, View v,
					int position, long id) {
				// TODO Auto-generated method stub
				
				//recuperation du texte dans le spinner destination
				texteDestination=((TextView)v.findViewById(R.id.villes)).getText().toString();
				
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
        
        
        
        
        
        spinnerFiltre.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parentView, View v,
					int position, long id) {
				// TODO Auto-generated method stub
				
				//recuperation du texte dans le spinner
				
				texteHeure=((TextView)v.findViewById(R.id.filtre_heure)).getText().toString();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
        
        
        
        
        
       //Ecouteur du bouton
        valider.setOnClickListener(this);
        
        
    }
    
    //============= Function to set data in ArrayList =================
    
    public void setListData(){
    	
    	String principalesVilles[]={"les differentes villes","Abidjan","Accra","Bamako","Banfora","Batié","Bobo Dioulasso","Boromo","Bouaké"
    			,"Cotonou","Dakar","Dédougou","Diébougou","Djibo","Dori","Fada Ngourma","Gaoua","Houndé","Kaya","Koudougou"
    			,"Lagos","Lomé","Mangodara","Niamey","Ndjamena","Niangoloko","Nouna","Orodara","Ouagadougou","Ouayigouya",
    			"Pâ","Pô","Sindou","Sinkansé","Tenkodogo","Yamoussokro","Ziniaré"};
    	
    	for(int i=0;i<principalesVilles.length;i++){
    		final SpinnerModel SM=new SpinnerModel();
    		
    		//======= Firstly take data in model object ===========
    		
    		SM.setVilleName(principalesVilles[i]);
    		SM.setImage("image"+i);
    		
    		//======== take model object in ArrayList ==========
    		
    		ListValue.add(SM);
    	}
    }
    
    
    
    
public void setListValeur(){
    	
    	String heures[]={"les differentes heures","06h","07h","08h","09h","10h","11h","12h","13h","14h","15h","16h","17h","18h","19h","20h"
    			,"21h","22h","23h"};
    	
    	for(int i=0;i<heures.length;i++){
    		final FiltreModel FM=new FiltreModel();
    		
    		//======= Firstly take data in model object ===========
    		
    		FM.setHeure(heures[i]);
    		
    		//======== take model object in ArrayList ==========
    		
    		Liste_des_valeurs.add(FM);
    	}
    }
    
    

	@Override
	public void onClick(View arg0) {//Click sur le bouton
		// TODO Auto-generated method stub
		
		//String depart=spinnerDepart.getSelectedItem().toString();//Recuperation des informations dans le spinner depart
		//String destination=spinnerDestination.getSelectedItem().toString();//Recuperation des informations dans le spinner destination
		
		
		if(texteDepart.equals(texteDestination))
		{
			Toast.makeText(this," Le départ et la destination ne peuvent pas correspondre", Toast.LENGTH_LONG).show();
			
		}else{
			
			//Insertion de données
			  
			table_manager = new TableManager(this);
	        table_manager.open();
	        /*Agence agence = new Agence();
	        agence.setId(1);
	        agence.setNom("Rakieta");
	        long lg = agence_manager.AddAgence(agence);
	        agence_manager.close();*/
	        
            //Lecture des données de la table ligne
			
			//String[] noms = table_manager.AllNomLigne();
			
	        String id_ligne = table_manager.Ligne_de_Voyage(texteDepart, texteDestination);
	        
	        if(!id_ligne.equals("")&&(texteHeure.contains("Choisir"))){
	        
	        int id = Integer.parseInt(id_ligne);//convertion en int
	        
			//ArrayAdapter<String> adap=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,id_ligne);
			//liste.setAdapter(adap);
	        //Toast.makeText(this," La ligne "+id_ligne, Toast.LENGTH_LONG).show();
	        
	        //String[] resultats = table_manager.Exploite_Voyage(id);
	        //ArrayAdapter<String> adap=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,resultats);
			
	        compaAdapter=new CompagniesHoraireAdapter(getApplicationContext(), table_manager.Compagnie_et_Horaire(id));
	        liste.setAdapter(compaAdapter);
	        
	        table_manager.close();
	        }else{
	        	
	        	if(!id_ligne.equals("")&&(!texteHeure.contains("Choisir"))){
	        		
	        		int id = Integer.parseInt(id_ligne);
	        		
	        		compaAdapter=new CompagniesHoraireAdapter(getApplicationContext(), table_manager.Tri_Compagnie_et_Horaire(id,texteHeure));
	    	        liste.setAdapter(compaAdapter);
	        	}else{
	        		
	        		compaAdapter=new CompagniesHoraireAdapter(getApplicationContext(), table_manager.Vide());
	    	        liste.setAdapter(compaAdapter);
	    	        Toast.makeText(this,"Aucun resultat ne correspond a votre requête", Toast.LENGTH_LONG).show();
	        	}
	        	
	        }
			
		}
		
	}
}
