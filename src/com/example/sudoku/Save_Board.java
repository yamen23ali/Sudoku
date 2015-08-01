package com.example.sudoku;

import org.w3c.dom.Text;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Save_Board extends Activity implements OnClickListener ,DialogInterface.OnClickListener {

	private String board;
	private String boardAdapt;
	private int background;
	private int filled_cells;
	private int level;
	private SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.save_board);

		Intent intent = getIntent();
		filled_cells = getIntent().getExtras().getInt("filled", 0);
		background = getIntent().getExtras().getInt("background", 0);
		board = getIntent().getExtras().getString("board");
		boardAdapt = getIntent().getExtras().getString("boardAdapt");
		level=getIntent().getExtras().getInt("level",0);

		Button b = (Button) findViewById(R.id.buttonSave);
		b.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		EditText et = (EditText) findViewById(R.id.editTextSave);
		String name = et.getText().toString();
		boolean found = false;

		db = openOrCreateDatabase("BoardsDataBase",
				MODE_PRIVATE, null);
		db.execSQL("CREATE TABLE IF NOT EXISTS BoardData"
				+ "(Board VARCHAR , BoardAdapt VARCHAR , Filled INT(3) "
				+ ",Background INT(2),User VARCHAR ,Level INT(3));");

		Cursor iterator = db.rawQuery("SELECT * FROM BoardData", null);
		int index = iterator.getColumnIndex("User");
		while (iterator.moveToNext()) {
			if (iterator.getString(index).equals( name)) {
				found = true;
				break;
			}
		}

		if (!found) 
		{
			db.execSQL("INSERT INTO BoardData VALUES(" + board + ","
					+ boardAdapt + "," + filled_cells + "," + background + ",'"
					+ name + "'," + level +");");

		}
		else
		{
			AlertDialog alert = new AlertDialog.Builder(this).create();
			alert.setMessage("Already There , Do you Want to overwrite it ?!");
			alert.setButton(DialogInterface.BUTTON_POSITIVE,"Yes",this);
			alert.setButton(DialogInterface.BUTTON_NEGATIVE,"No",this);
			alert.show();
		}
		db.close();
		//this.finish();
	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		// TODO Auto-generated method stub
		if(which==DialogInterface.BUTTON_POSITIVE)
		{
			
		}
		
	}

}
