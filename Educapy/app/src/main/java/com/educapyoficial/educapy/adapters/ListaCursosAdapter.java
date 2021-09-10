package com.educapyoficial.educapy.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.educapyoficial.educapy.models.CursosModel;
import com.educapyoficial.educapy.models.EducapyModelUser;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.Locale;

public class ListaCursosAdapter extends ArrayAdapter<CursosModel> {

    private Context context;
    private ArrayList<CursosModel> list;

    public static int position;
    Locale locale = new Locale("en", "UK");
    DecimalFormatSymbols symbols = new DecimalFormatSymbols(locale);

    String pattern = "###,###.########";
    DecimalFormat decimalFormat;

    public ListaCursosAdapter(Context context, ArrayList<CursosModel> list) {
        super(context, android.R.layout.simple_list_item_1, list);
        this.context = context;
        this.list = list;

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
            vi = inflater.inflate(android.R.layout.simple_list_item_2, null);
        }

        CursosModel cursosModel = list.get(position);

        if (cursosModel != null) {
            TextView textView = (TextView) vi.findViewById(android.R.id.text1);
            if (cursosModel.getCursos() != null && !cursosModel.getCursos().equals("")) {
                textView.setText(String.valueOf(cursosModel.getCursos()));
            }




        }
        return vi;

    }

    public ArrayList<CursosModel> getList() {
        return list;
    }

    public void setUsuariosList(ArrayList<CursosModel> list) {
        this.list = list;
    }

}
