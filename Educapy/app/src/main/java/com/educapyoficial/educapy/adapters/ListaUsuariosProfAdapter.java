package com.educapyoficial.educapy.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.educapyoficial.educapy.EducapyModelUser;
import com.educapyoficial.educapy.EducapyModelUserProfesor;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Locale;

public class ListaUsuariosProfAdapter extends ArrayAdapter<EducapyModelUserProfesor> {

	private Context context;
	private ArrayList<EducapyModelUserProfesor> usuariosList;

	public static int position;
	Locale locale = new Locale("en", "UK");
	DecimalFormatSymbols symbols = new DecimalFormatSymbols(locale);

	String pattern = "###,###.########";
	DecimalFormat decimalFormat;

	public ListaUsuariosProfAdapter(Context context, ArrayList<EducapyModelUserProfesor> usuariosList) {
		super(context, android.R.layout.simple_list_item_1, usuariosList);
		this.context = context;
		this.usuariosList = usuariosList;

	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		symbols.setDecimalSeparator(',');
		symbols.setGroupingSeparator('.');
		decimalFormat = new DecimalFormat(pattern, symbols);
		View vi = convertView;

		if (convertView == null) {
			LayoutInflater inflater = (LayoutInflater) context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			vi = inflater.inflate(android.R.layout.simple_list_item_1, null);
		}

		EducapyModelUserProfesor educapyModelUser =usuariosList.get(position);

		if (educapyModelUser != null){
			TextView textView = (TextView) vi.findViewById(android.R.id.text1);
			if (educapyModelUser.getNombre() != null && !educapyModelUser.getNombre().equals("")) {
				textView.setText(String.valueOf(educapyModelUser.getNombre()));
			} else {
				textView.setText(String.valueOf(educapyModelUser.getCorreo()));
			}
		}
		return vi;

	}

	public ArrayList<EducapyModelUserProfesor> getUsuariosList() {
		return usuariosList;
	}

	public void setUsuariosList(ArrayList<EducapyModelUserProfesor> usuariosList) {
		this.usuariosList = usuariosList;
	}

}
