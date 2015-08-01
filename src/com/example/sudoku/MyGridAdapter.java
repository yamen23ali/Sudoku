package com.example.sudoku;

import com.example.sudoku.R.color;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class MyGridAdapter extends ArrayAdapter<String> {

	private final String[] values;
	private final Context context;
	Activity parentAct;
	
	public MyGridAdapter(Context context,String[] values,Activity act) {
		super(context,R.layout.cell_layout, values);
		this.values=values;
		this.context=context;
		this.parentAct=act;
		
		// TODO Auto-generated constructor stub
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		LayoutInflater inflator= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View v=inflator.inflate(R.layout.cell_layout,parent,false);
		
          Button b=(Button) v.findViewById(R.id.button1);
           b.setText(values[position]);
           b.setId(position);
           String s=(new Integer(position)).toString();
           b.setHint(s);
           //parentAct.registerForContextMenu(b);
           b.setOnClickListener((Board)parentAct);
           
		char[] elem = values[position].toCharArray();
		if (elem[0] == '_')
		{
			String temp=""; temp+=elem[1];
			b.setText(temp);
			LinearLayout ll = (LinearLayout) b.getParent();
			ll.setBackgroundColor(Color.argb(110,0,100,200));
		}
		else
		{
			if (values[position].equals("-")) 
			{
				b.setText("?");
				LinearLayout ll = (LinearLayout) b.getParent();
				ll.setBackgroundColor(Color.argb(110, 255, 0, 0));

			}
		}
           
        
           
		return v;
	}


	
	
	
	
	

}
