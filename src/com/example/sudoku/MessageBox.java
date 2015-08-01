package com.example.sudoku;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MessageBox extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.message_box);
		TextView tv=(TextView) findViewById(R.id.textView1);
		tv.setText(getIntent().getExtras().getString("Message"));
	}
	

}
