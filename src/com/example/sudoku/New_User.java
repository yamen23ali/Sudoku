package com.example.sudoku;

import com.example.sudoku.R.color;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class New_User extends Activity implements OnClickListener ,DialogInterface.OnClickListener {

	private String board;
	private String boardAdapt;
	private int background;
	private int filled_cells;
	private int level;
	private String name;
	private SQLiteDatabase db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.new_user);

		
		filled_cells =Integer.parseInt(getResources().getString(R.string.Y_WingFilled));
		background =1;
		board = getResources().getString(R.string.Y_Wing);
		boardAdapt =getResources().getString(R.string.Y_WingAdapt);
		level=1;

		Button b = (Button) findViewById(R.id.buttonNewUser);
		b.setOnClickListener(this);
		Typeface face=Typeface.createFromAsset(getAssets(), "Fonts/rotondosilver.ttf"); 
		b.setTypeface(face); 
		
		TextView tv=(TextView) findViewById(R.id.textViewSave);
		Typeface face1=Typeface.createFromAsset(getAssets(), "Fonts/ARDARLING.ttf"); 
		tv.setTextColor(color.Black);
		tv.setTypeface(face1); 
		
		//====================================//
	    WindowManager.LayoutParams params = getWindow().getAttributes();  
        params.x =0;  
        params.height = 469;  
        params.width = 360;  
        params.y =0;  
   
        this.getWindow().setAttributes(params);  
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

		EditText et = (EditText) findViewById(R.id.editTextSave);
		name = et.getText().toString();
		Board.userName=name;
		boolean found = false;

		db = openOrCreateDatabase("BoardsDataBase",
				MODE_PRIVATE, null);
		db.execSQL("CREATE TABLE IF NOT EXISTS FullBoardData"
				+ "(Board VARCHAR , BoardAdapt VARCHAR , Filled INT(3) "
				+ ",Background INT(2),User VARCHAR ,TopLevel INT(3),currentLevel INT(3));");

		Cursor iterator = db.rawQuery("SELECT * FROM FullBoardData", null);
		int index = iterator.getColumnIndex("User");
		while (iterator.moveToNext()) {
			if (iterator.getString(index).equals( name)) {
				found = true;
				break;
			}
		}

		if (!found) 
		{
			db.execSQL("INSERT INTO FullBoardData VALUES('" + board + "','"
					+ boardAdapt + "'," + filled_cells + "," + background + ",'"
					+ name + "'," + level +","+level+");");
			Intent intent=new Intent(this,Levels.class);
			intent.putExtra("LEVEL",1);
			intent.putExtra("back",1);
			startActivity(intent);
			db.close();
			this.finish();
		}
		else
		{
			AlertDialog alert = new AlertDialog.Builder(this).create();
			alert.setMessage("Already There , Do you Want to overwrite it ?!");
			alert.setButton(DialogInterface.BUTTON_POSITIVE,"Yes",this);
			alert.setButton(DialogInterface.BUTTON_NEGATIVE,"No",this);
			alert.show();
		}
		
		//this.finish();
	}

	@Override
	public void onClick(DialogInterface dialog, int which) {
		// TODO Auto-generated method stub
		if(which==DialogInterface.BUTTON_POSITIVE)
		{
			db.execSQL("UPDATE FullBoardData SET Board='"+ board + "' ," +
			        "BoardAdapt='"+boardAdapt+"'," +
			        "Filled="+filled_cells+"," +
			        "Background="+background+"," +
			        "TopLevel="+level+","+
			        "currentLevel="+level +
					" WHERE User='"+name+"';");
			Intent intent=new Intent(this,Levels.class);
			intent.putExtra("LEVEL",1);
			intent.putExtra("back",1);
			startActivity(intent);
			db.close();
			this.finish();
		}
		
	}
}
/*protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.new_user);
		
	}

}*/
