package com.example.sudoku;

import com.example.sudoku.R.color;

import android.os.Bundle;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.text.Spannable;
import android.text.style.BackgroundColorSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.view.View.OnClickListener;

public class MainActivity extends ListActivity implements OnItemClickListener{

	static final String[] options = { " New User", "Load User", "   About", "    Exit" };

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	    setListAdapter(new MyListAdapter(this, options));
	    ListView lv=(ListView) findViewById(android.R.id.list);
	    lv.setOnItemClickListener(this);
	   //==========================================//    
	    Board.levelsAdapt.put(1,getResources().getString(R.string.Y_WingAdapt));
	    Board.levelsAdapt.put(2,getResources().getString(R.string.X_WingAdapt));
	    Board.levelsAdapt.put(3,getResources().getString(R.string.Jelly_FishAdapt));
	    Board.levelsAdapt.put(4,getResources().getString(R.string.EasyAdapt));
	    Board.levelsAdapt.put(5,getResources().getString(R.string.ModerateAdapt));
	    Board.levelsAdapt.put(6,getResources().getString(R.string.DiabolicalAdapt));
	    Board.levelsAdapt.put(7,getResources().getString(R.string.GentleAdapt));
	    Board.levelsAdapt.put(8,getResources().getString(R.string.Naked_TriplesAdapt));
	    Board.levelsAdapt.put(9,getResources().getString(R.string.ToughAdapt));
	    Board.levelsAdapt.put(10,getResources().getString(R.string.Hidden_TripleAdapt));
	    Board.levelsAdapt.put(11,getResources().getString(R.string.EscargotAdapt));
	    Board.levelsAdapt.put(12,getResources().getString(R.string.Intersection_RemovalAdapt));
	    Board.levelsAdapt.put(13,getResources().getString(R.string.Riddle_Of_ShoAdapt));
	    Board.levelsAdapt.put(14,getResources().getString(R.string.Easy_17_ClueAdapt));
	    Board.levelsAdapt.put(15,getResources().getString(R.string.Hard_17_ClueAdapt));
	   
	}
	
	


@Override
public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
	// TODO Auto-generated method stub
	TextView tv=(TextView) arg1.findViewById(R.id.textView1);
	String s=tv.getText().toString();
	
	if(s==" New User")
	{
		Intent intent=new Intent(this,New_User.class);
		startActivity(intent);
		
	}
	else if(s=="Load User")
	{
		Intent intent=new Intent(MainActivity.this,Load_Board.class);
		startActivity(intent);
		
	}
	else if(s== "   About")
	{
		Intent intent=new Intent(MainActivity.this,AboutBox.class);
		startActivity(intent);
	}
	else if(s=="    Exit")
	{
		this.finish();
	
	}

}

	

}
