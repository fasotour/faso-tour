package com.example.fasotour;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CompagniesHoraireAdapter extends BaseAdapter{
	
	private Context monContext;
	private List<CompagniesHoraire> mesCompagniesHoraire;
	
	//Constructor
	
	public CompagniesHoraireAdapter(Context monContext, List<CompagniesHoraire> mesCompagniesHoraire){
		
		this.monContext = monContext;
		this.mesCompagniesHoraire = mesCompagniesHoraire;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mesCompagniesHoraire.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mesCompagniesHoraire.get(position) ;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		
		View v= View.inflate(monContext, R.layout.item_liste, null);
		TextView textCompagnie=(TextView)v.findViewById(R.id.compagnie);
		TextView textHoraire=(TextView)v.findViewById(R.id.horaire);
		
		//set test for TextView
		
		textCompagnie.setText(mesCompagniesHoraire.get(position).getCompagnie());
		textHoraire.setText(mesCompagniesHoraire.get(position).getHeure());
		
		return v;
	}

}
