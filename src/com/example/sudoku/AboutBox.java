package com.example.sudoku;

import android.app.Activity;
import android.app.ListActivity;
import android.os.Bundle;

public class AboutBox extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.about_box);
		
		/*
		 * For Debugging private static final String[][] forDebug=
		 * {{"8","9","4","6","2","5","1","7","3"},
		 * {"3","7","6","1","4","9","8","5","2"},
		 * {"2","1","5","3","7","8","6","9","4"},
		 * 
		 * {"2","4","6","9","5","8","7","3","1"},
		 * {"7","1","8","4","2","3","9","6","5"},
		 * {"5","3","9","7","6","1","4","8","2"},
		 * 
		 * {"5","1","2","3","6","9","4","8","7"},
		 * {"6","9","7","2","8","4","5","3","1"},
		 * {"8","4","3","1","5","7","9","2","0"}};
		 * 
		 * 
		 * int[][] board2= {{8,9,4,3,7,6,2,1,5}, {6,2,5,1,4,9,3,7,8},
		 * {1,7,3,8,5,2,6,9,4}, {2,4,6,7,1,8,5,3,9}, {9,5,8,4,2,3,7,6,1},
		 * {7,3,1,9,6,5,4,8,2}, {5,1,2,6,9,7,8,4,3}, {3,6,9,2,8,4,1,5,7},
		 * {4,8,7,5,3,1,9,2,0}}; for (int i = 0; i < gridviewsId.size(); i++) {
		 * MyGridAdapter adapter = new MyGridAdapter(this,forDebug[i], this);
		 * GridView gv = (GridView) findViewById(gridviewsId.get(i));
		 * gv.setAdapter(adapter); }
		 * 
		 * 
		 * for(int i=0;i<=8;i++) { for(int j=0;j<=8;j++) {
		 * board[i+1][j+1]=board2[i][j]; } }
		 */
	}

}
