package com.example.sudoku;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Layout.Directions;
import android.view.FocusFinder;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TableLayout.LayoutParams;
import android.widget.TextView;

public class Levels extends Activity implements RadioGroup.OnCheckedChangeListener{

	private String[] level_Name={"Y_Wing","X_Wing","Jelly Fish","Easy",
			                     "Moderate","Diabolical","Gentle","Naked Triples",
			                     "Tough","Hidden Triple","Escargot",
			                     "Intersection",
			                     "Riddle Of Sho","Easy 17 Clue","Hard 17 Clue"
			                     
			                     };
	int level=1;
	int back=1;
	//=========================================================//
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.levels_layout);
		RadioGroup rg=(RadioGroup) findViewById(R.id.radioGroupLevel);
		rg.setOnCheckedChangeListener(this);
		//=============================================//
		level=getIntent().getExtras().getInt("LEVEL");
		back=getIntent().getExtras().getInt("back");
		Typeface face=Typeface.createFromAsset(getAssets(),"Fonts/Nasa.ttf");
		if(level>3)
		{
			level=3;
		}
		for(int i=1;i<=level_Name.length;i++)
		{
			RadioButton rb=new RadioButton(rg.getContext());
			rb.setText(level_Name[i-1]);
			
			rb.setLayoutParams(new LayoutParams(158,50));
			rb.setTypeface(face);
			rb.setTextColor(Color.BLACK);
			rb.setTextSize(20);
			if(i>level)
			{
				rb.setEnabled(false);
				rb.setTextColor(Color.GRAY);
			}
			rg.addView(rb);
		}
		
		//=============================================//
		WindowManager.LayoutParams params = getWindow().getAttributes();
		params.x = 0;
		params.height = 460;
		params.width = 420;
		params.y = 0;
		//setTitle("Choose A Game : ");
		//===================================//
		
	}
	//==============================================//
	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		// TODO Auto-generated method stub
		RadioButton rb=(RadioButton) findViewById(checkedId);
		String s=rb.getText().toString();
		int filled;
		String board,boardAdapt;
		if(s.equals("Y_Wing"))
		{
			filled=Integer.parseInt(getResources().getString(R.string.Y_WingFilled));
			board=getResources().getString(R.string.Y_Wing);
			boardAdapt=getResources().getString(R.string.Y_WingAdapt);
			Board.setBoard(filled,back,board,boardAdapt,level,1);
			
		}
		else if(s.equals("X_Wing"))
		{
			filled=Integer.parseInt(getResources().getString(R.string.X_WingFilled));
			board=getResources().getString(R.string.X_Wing);
			boardAdapt=getResources().getString(R.string.X_WingAdapt);
			Board.setBoard(filled,back,board,boardAdapt,level,2);
		}
		else if(s.equals("Jelly Fish"))
		{
			filled=Integer.parseInt(getResources().getString(R.string.Jelly_FishFilled));
			board=getResources().getString(R.string.Jelly_Fish);
			boardAdapt=getResources().getString(R.string.Jelly_FishAdapt);
			Board.setBoard(filled,back,board,boardAdapt,level,3);
			
		}
		else if(s.equals("Easy"))
		{
			filled=Integer.parseInt(getResources().getString(R.string.EasyFilled));
			board=getResources().getString(R.string.Easy);
			boardAdapt=getResources().getString(R.string.EasyAdapt);
			Board.setBoard(filled,back,board,boardAdapt,level,4);
		}
		else if(s.equals("Moderate"))
		{
			filled=Integer.parseInt(getResources().getString(R.string.ModerateFilled));
			board=getResources().getString(R.string.Moderate);
			boardAdapt=getResources().getString(R.string.ModerateAdapt);
			Board.setBoard(filled,back,board,boardAdapt,level,5);
		}
		else if(s.equals("Diabolical"))
		{
			filled=Integer.parseInt(getResources().getString(R.string.DiabolicalFilled));
			board=getResources().getString(R.string.Diabolical);
			boardAdapt=getResources().getString(R.string.DiabolicalAdapt);
			Board.setBoard(filled,back,board,boardAdapt,level,6);
		}
		else if(s.equals("Gentle"))
		{
			filled=Integer.parseInt(getResources().getString(R.string.GentleFilled));
			board=getResources().getString(R.string.Gentle);
			boardAdapt=getResources().getString(R.string.GentleAdapt);
			Board.setBoard(filled,back,board,boardAdapt,level,7);
		}
		else if(s.equals("Naked Triples"))
		{
			filled=Integer.parseInt(getResources().getString(R.string.Naked_TriplesFilled));
			board=getResources().getString(R.string.Naked_Triples);
			boardAdapt=getResources().getString(R.string.Naked_TriplesAdapt);
			Board.setBoard(filled,back,board,boardAdapt,level,8);
		}
		else if(s.equals("Tough"))
		{
			filled=Integer.parseInt(getResources().getString(R.string.ToughFilled));
			board=getResources().getString(R.string.Tough);
			boardAdapt=getResources().getString(R.string.ToughAdapt);
			Board.setBoard(filled,back,board,boardAdapt,level,9);
		}
		else if(s.equals("Hidden Triple"))
		{
			filled=Integer.parseInt(getResources().getString(R.string.Hidden_TripleFilled));
			board=getResources().getString(R.string.Hidden_Triple);
			boardAdapt=getResources().getString(R.string.Hidden_TripleAdapt);
			Board.setBoard(filled,back,board,boardAdapt,level,10);
		}
		else if(s.equals("Escargot"))
		{
			filled=Integer.parseInt(getResources().getString(R.string.EscargotFilled));
			board=getResources().getString(R.string.Escargot);
			boardAdapt=getResources().getString(R.string.EscargotAdapt);
			Board.setBoard(filled,back,board,boardAdapt,level,11);
		}
		else if(s.equals("Intersection"))
		{
			filled=Integer.parseInt(getResources().getString(R.string.Intersection_RemovalFilled));
			board=getResources().getString(R.string.Intersection_Removal);
			boardAdapt=getResources().getString(R.string.Intersection_RemovalAdapt);
			Board.setBoard(filled,back,board,boardAdapt,level,12);
		}
		else if(s.equals("Riddle Of Sho"))
		{
			filled=Integer.parseInt(getResources().getString(R.string.Riddle_Of_ShoFilled));
			board=getResources().getString(R.string.Riddle_Of_Sho);
			boardAdapt=getResources().getString(R.string.Riddle_Of_ShoAdapt);
			Board.setBoard(filled,back,board,boardAdapt,level,13);
		}
		else if(s.equals("Easy 17 Clue"))
		{
			filled=Integer.parseInt(getResources().getString(R.string.Easy_17_ClueFilled));
			board=getResources().getString(R.string.Easy_17_Clue);
			boardAdapt=getResources().getString(R.string.Easy_17_ClueAdapt);
			Board.setBoard(filled,back,board,boardAdapt,level,14);
		}
		else if(s.equals("Hard 17 Clue"))
		{
			filled=Integer.parseInt(getResources().getString(R.string.Hard_17_ClueFilled));
			board=getResources().getString(R.string.Hard_17_Clue);
			boardAdapt=getResources().getString(R.string.Hard_17_ClueAdapt);
			Board.setBoard(filled,back,board,boardAdapt,level,15);
		}
			
	    Intent intent=new Intent(this,Board.class);
		startActivity(intent);
		this.finish();
		
	}

}
