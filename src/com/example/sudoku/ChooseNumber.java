package com.example.sudoku;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.GridView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class ChooseNumber extends Activity implements RadioButton.OnCheckedChangeListener {

	int gridViewId;
	int buttonId;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.choose_number);
		
		WindowManager.LayoutParams params = getWindow().getAttributes();  
        params.x =0;  
        params.height = 360;  
        params.width = 360;  
        params.y =0;  
   
        this.getWindow().setAttributes(params);  
		
		RadioButton rb=(RadioButton) findViewById(R.id.radioButton1);
		rb.setOnCheckedChangeListener(this);
		rb.setTextColor(Color.BLACK);
		
		rb=(RadioButton) findViewById(R.id.radioButton2);
		rb.setOnCheckedChangeListener(this);
		rb.setTextColor(Color.BLACK);
		
		rb=(RadioButton) findViewById(R.id.radioButton3);
		rb.setOnCheckedChangeListener(this);
		rb.setTextColor(Color.BLACK);
		
		rb=(RadioButton) findViewById(R.id.radioButton4);
		rb.setOnCheckedChangeListener(this);
		rb.setTextColor(Color.BLACK);
		
		rb=(RadioButton) findViewById(R.id.radioButton5);
		rb.setOnCheckedChangeListener(this);
		rb.setTextColor(Color.BLACK);
		
		rb=(RadioButton) findViewById(R.id.radioButton6);
		rb.setOnCheckedChangeListener(this);
		rb.setTextColor(Color.BLACK);
		
		rb=(RadioButton) findViewById(R.id.radioButton7);
		rb.setOnCheckedChangeListener(this);
		rb.setTextColor(Color.BLACK);
		
		rb=(RadioButton) findViewById(R.id.radioButton8);
		rb.setOnCheckedChangeListener(this);
		rb.setTextColor(Color.BLACK);
		
		rb=(RadioButton) findViewById(R.id.radioButton9);
		rb.setOnCheckedChangeListener(this);
		rb.setTextColor(Color.BLACK);
		
		
		gridViewId=getIntent().getExtras().getInt("gridViewId");
		buttonId=getIntent().getExtras().getInt("buttonId");
		
		
	}
	


	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		// TODO Auto-generated method stub
		Intent intent =new Intent(ChooseNumber.this,Board.class);
		
		intent.putExtra("ChoosedNumber",buttonView.getText());
		intent.putExtra("gridViewId",gridViewId);
		intent.putExtra("buttonId",buttonId);
		
		startActivity(intent);
		finish();
		
	}
	
	

}
