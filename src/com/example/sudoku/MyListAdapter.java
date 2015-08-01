package com.example.sudoku;

import java.util.List;

import com.example.sudoku.R.color;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class MyListAdapter extends ArrayAdapter<String>  {

	private final Context context;
	private final String[] values;
	public MyListAdapter(Context context,String[] objects) {
		super(context,R.layout.list_view_layout, objects);
		this.context=context;
		this.values = objects;
		// TODO Auto-generated constructor stub
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
	
		LayoutInflater inflator= (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View v=inflator.inflate(R.layout.list_view_layout, parent,false);
		
		ImageView iv= (ImageView) v.findViewById(R.id.imageView1);
		TextView tv=  (TextView) v.findViewById(R.id.textView1);
		tv.setText(values[position]);
		
		if(values[position].equals(" New User"))
		{
			iv.setBackgroundResource(R.drawable.game5);
		}
		else if(values[position].equals("    Exit"))
		{
			iv.setBackgroundResource(R.drawable.exit2);
		}
		else if(values[position].equals("   About"))
		{
			iv.setBackgroundResource(R.drawable.about2);
			
		}
		else if(values[position].equals("Load User"))
		{
			iv.setBackgroundResource(R.drawable.load1);
			
		}
		
		Typeface face=Typeface.createFromAsset(context.getAssets(), "Fonts/Nasa.ttf"); 
		tv.setTextColor(color.black);
		tv.setTypeface(face); 
		return v;
	}
	

}
