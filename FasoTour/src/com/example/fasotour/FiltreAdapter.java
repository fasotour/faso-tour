package com.example.fasotour;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class FiltreAdapter extends ArrayAdapter<String>{

	private Activity activity=null;
	private ArrayList data;
	public Resources res;
	FiltreModel filtre_model=null;
	LayoutInflater inflater;
	
	//======= Adapter constructor =======
	
	public FiltreAdapter(MainActivity activitySpinner,
			int textViewRessourceId,
			ArrayList objects,
			Resources resLocal) {
		super(activitySpinner,textViewRessourceId,objects);
		
		//====== Take passed values ======
		
		activity=activitySpinner;
		data=objects;
		res= resLocal;
		
		//========= Layout inflator to call external xml layout() =========
		
		inflater=(LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	
public View getDropDownView(int position,View convertView,ViewGroup parent){
		
		return getCustomView(position,convertView,parent); 
	}
	
public View getView(int position,View convertView,ViewGroup parent){
		
		return getCustomView(position,convertView,parent); 
	}

public View getCustomView(int position,View convertView,ViewGroup parent){
	
	//======== inflate spinner.xml file for each row (Defined belov) =============
	
	View row=inflater.inflate(R.layout.item_filtre, parent,false);
	
	//========= Get each Model object from Arraylist ============
	
	filtre_model=(FiltreModel) data.get(position);
	
	TextView label=(TextView)row.findViewById(R.id.filtre_heure);
	
	if(position==0){
		//====== Default selected spinner item =======
		label.setText("Choisir l'heure");
	}else{
		label.setText(filtre_model.getHeure());
	}
	return row;
}

}
