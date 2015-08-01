package com.example.sudoku;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.example.sudoku.R.color;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.database.Cursor;
import android.database.DataSetObserver;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;

public class Load_Board extends Activity implements Button.OnClickListener {

	
	private Spinner sp;
	private Cursor iterator;
	private SQLiteDatabase db;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.load_board);
		
		//================================================//
		WindowManager.LayoutParams params = getWindow().getAttributes();
		params.x = 0;
		params.height = 469;
		params.width = 360;
		params.y = 0;

		this.getWindow().setAttributes(params);
		
		TextView tv=(TextView) findViewById(R.id.textViewLoadBoard);
		Typeface face=Typeface.createFromAsset(getAssets(), "Fonts/ARDARLING.ttf"); 
		tv.setTextColor(color.black);
		tv.setTypeface(face,Typeface.BOLD); 
		
		
		Button b=(Button) findViewById(R.id.buttonLoadBoard);
		Typeface face1=Typeface.createFromAsset(getAssets(), "Fonts/rotondosilver.ttf"); 
		b.setTypeface(face1); 
		b.setOnClickListener(this);
		//==============================================//
		sp=(Spinner) findViewById(R.id.spinnerLoadBoard);
		List<String> items=new ArrayList<String>();
		//========================================//
		db = openOrCreateDatabase("BoardsDataBase",
				MODE_PRIVATE, null);
		db.execSQL("CREATE TABLE IF NOT EXISTS FullBoardData"
				+ "(Board VARCHAR , BoardAdapt VARCHAR , Filled INT(3) "
				+ ",Background INT(2),User VARCHAR ,TopLevel INT(3),currentLevel INT(3));");
		 iterator=db.rawQuery("SELECT * FROM FullBoardData",null);
		 //=======================================//
		 int index=iterator.getColumnIndex("User");
		 while(iterator.moveToNext())
		 {
			 String s=iterator.getString(index);
			 items.add(s);
		 }
		 
		 ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
					android.R.layout.simple_spinner_item, items);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		sp.setAdapter(dataAdapter);	
	}

	@Override
	public void onClick(View button) 
	{
		// TODO Auto-generated method stub
		String s=(String)sp.getSelectedItem();
		iterator=db.rawQuery("SELECT * FROM FullBoardData WHERE User='"+s+"';",null);
		iterator.moveToFirst();
		Board.setBoard(Integer.parseInt(iterator.getString(iterator.getColumnIndex("Filled"))),
				Integer.parseInt(iterator.getString(iterator.getColumnIndex("Background"))),
				iterator.getString(iterator.getColumnIndex("Board")),
				iterator.getString(iterator.getColumnIndex("BoardAdapt")),
				Integer.parseInt(iterator.getString(iterator.getColumnIndex("TopLevel"))),
				Integer.parseInt(iterator.getString(iterator.getColumnIndex("currentLevel"))));
		Intent intent=new Intent(this,Board.class);
		startActivity(intent);
		this.finish();
		
	}

}
