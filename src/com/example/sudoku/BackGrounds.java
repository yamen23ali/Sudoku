package com.example.sudoku;

import java.lang.reflect.Array;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

public class BackGrounds extends Activity implements OnClickListener{

	
	Map<Integer,Integer> buttonImage=new HashMap<Integer,Integer>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.backgrounds);
	
		buttonImage.put(R.id.buttonBack1,R.drawable.back1);
		buttonImage.put(R.id.buttonBack2,R.drawable.back2);
		buttonImage.put(R.id.buttonBack3,R.drawable.back3);
		buttonImage.put(R.id.buttonBack4,R.drawable.back4);
		buttonImage.put(R.id.buttonBack5,R.drawable.back5);
		buttonImage.put(R.id.buttonBack6,R.drawable.back6);
		
		Object[] buttons=buttonImage.keySet().toArray();
		for(int i=0;i<buttonImage.size();i++)
		{
			Button b=(Button) findViewById((Integer)buttons[i]);
			b.setOnClickListener(this);
		}
	}

	@Override
	public void onClick(View vv) {
		// TODO Auto-generated method stub
		Button b=(Button)vv;
		int background=buttonImage.get(b.getId());
		 Intent intent=new Intent(this,Board.class);
		 intent.putExtra("BackId",background);
		 startActivity(intent);
		this.finish();
		
	}

}
