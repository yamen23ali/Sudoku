package com.example.sudoku;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.example.sudoku.R.color;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.FragmentManager.BackStackEntry;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Path.Direction;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Board extends Activity implements View.OnClickListener,DialogInterface.OnClickListener { 

	
	private LinkedList<Integer> gridviewsId = new LinkedList<Integer>();
	private static int[][] board = new int[10][10];
	private static String[][] boardAdapt = new String[10][9];
	private static String[][] currentLevelAdapt = new String[10][9];
	private static String[][]  modifiedBoardAdapt = new String[10][9];
	private static int filled_cells = 0;
	private static int backGround = 1;
	public static boolean loaded=false;
	private static int top_level=1;
	private static int curr_level=1;
	private int messageBoxNum=1;
	public static String userName="Default";
	Map<Integer, Integer> backGroundNum = new HashMap<Integer, Integer>();
	static public Map<Integer, String> levelsAdapt = new HashMap<Integer,String>();

	//========================Set The Current Board===============//
	/* This function will set the board information ( board - filled cells number - background -
	 * current level - top level the user reaches )
	 */
	public static void setBoard(int filled, int back, String board_S,String boardAdapt_S,
			                    int board_level,int current_Level) 
	{ 
		
		filled_cells = filled;
		backGround = back;
		top_level=board_level;
		curr_level=current_Level;
		// Fill The board Matrix from the board_S String
		int k = 1, v = 1;
		char[] temp = board_S.toCharArray();
		for (int i = 0; i < board_S.length() ; i++) {
			String t = "";
			t += temp[i];
			board[k][v] = Integer.parseInt(t);
			v++;
			if (v > 9) {
				k++;
				v = 1;
			}
		}
		// Fill The boardAdapt Matrix from the board_S String
		k = 1;
		v = 0;
		temp = boardAdapt_S.toCharArray();
		for (int i = 0; i < board_S.length(); i++) {
			String t = "";
			t += temp[i];
			boardAdapt[k][v] = t;
			v++;
			if (v > 8) {
				k++;
				v = 0;
			}
		}
		// Fill The currentLevelAdapt Matrix from the board_S String
		k = 1;
		v = 0;
		temp = levelsAdapt.get(current_Level).toCharArray();
		for (int i = 0; i < board_S.length(); i++) {
			String t = "";
			t += temp[i];
			currentLevelAdapt[k][v] = t;
			v++;
			if (v > 8) {
				k++;
				v = 0;
			}
		}
		//Modify The boardAdapt 
		for(int i=1;i<=9;i++)
		{
			for(int j=0;j<=8;j++)
			{
				if(currentLevelAdapt[i][j].equals("-") && !boardAdapt[i][j].equals("-"))
				{
					modifiedBoardAdapt[i][j]="_"+boardAdapt[i][j];
				}
				else
				{
					modifiedBoardAdapt[i][j]=boardAdapt[i][j]; 
				}
			}
		}

	}
	//========================Set The Current Board===============//
	
	//=========================Some Main Events :))===============//
	/* This Function will be called when the activity is created */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.my_board_layout);
		//Fill The List of gridViews
		gridviewsId.add(R.id.gridView1);
		gridviewsId.add(R.id.gridView2);
		gridviewsId.add(R.id.gridView3);
		gridviewsId.add(R.id.gridView4);
		gridviewsId.add(R.id.gridView5);
		gridviewsId.add(R.id.gridView6);
		gridviewsId.add(R.id.gridView7);
		gridviewsId.add(R.id.gridView8);
		gridviewsId.add(R.id.gridView9);
		// Fill The backGrounds Map 
		backGroundNum.put(R.drawable.back1, 1);
		backGroundNum.put(R.drawable.back2, 2);
		backGroundNum.put(R.drawable.back3, 3);
		backGroundNum.put(R.drawable.back4, 4);
		backGroundNum.put(R.drawable.back5, 5);
		backGroundNum.put(R.drawable.back6, 6);
		//Set The Adapter of the main GridView
		for (int i = 0; i < gridviewsId.size(); i++) {
			MyGridAdapter adapter = new MyGridAdapter(this, modifiedBoardAdapt[i + 1],
					this);
			GridView gv = (GridView) findViewById(gridviewsId.get(i));
			gv.setAdapter(adapter);
		}

		//Find The background corresponding to the current background number 
		Integer[] keys = (Integer[]) backGroundNum.keySet().toArray(
				new Integer[backGroundNum.size()]);
		for (int i = 0; i < keys.length; i++) {
			if (backGroundNum.get(keys[i]).equals(backGround)) {
				changeBackGround(keys[i]);
			}
		}
		//========================================//
		
	}
	
	/* This Function will be called when the user click
	 *  a button in the board
	 * 
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		// Get The clicked button 
		View vi = (View) v.getParent().getParent();
		Button b = (Button) v;
        // Call the chooseNumber activity
		Intent intent = new Intent(Board.this, ChooseNumber.class);
		intent.putExtra("gridViewId", vi.getId());
		intent.putExtra("buttonId", b.getId());
		startActivity(intent);
	}
	
	
	/* This function will be called to create the option Menu
	 * 
	 */
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflator = getMenuInflater();
		inflator.inflate(R.menu.options_menu, menu);
		return true;
	}
	
	/* This function will be called when the user 
	 * select an option from the option menu
	 */
	public boolean onOptionsItemSelected(MenuItem item) {
		if (item.getTitle().equals("BackGround"))
		{

			Intent intent = new Intent(this, BackGrounds.class);
			startActivity(intent);

		}
		else if (item.getTitle().equals("Change Level")) 
		{
			changeLevel();

		} 
		else if (item.getTitle().equals("Save")) 
		{
			saveBoard();
		}
		return super.onOptionsItemSelected(item);
	}

	/* This Function will be called when the user returns to 
	 * the board from another activity
	 */
	@Override
	protected void onNewIntent(Intent intent) {
		// TODO Auto-generated method stub
		super.onNewIntent(intent);

		int backGroundTemp = intent.getExtras().getInt("BackId", -1);
		// If it's called from choose number activity
		if (backGroundTemp == -1 ) {
			
			// Get The Choosed Number
			String number = intent.getExtras().getString("ChoosedNumber");
			//Get The cell position
			int gridViewId = intent.getExtras().getInt("gridViewId");
			int buttonId = intent.getExtras().getInt("buttonId");

			GridView gv = (GridView) this.findViewById(gridViewId);
			Button b = (Button) gv.findViewById(buttonId);
			
			b.setText(number);

			
     	        	   
			int gridNum = gridviewsId.lastIndexOf(gridViewId) + 1;
			int buttonNum = Integer.parseInt(b.getHint().toString()) + 1;

            // Calculate The corresponding position in the matrix  
			int row = ((getRow(gridNum) * 3) - 2) + (getRow(buttonNum) - 1);
			int column = ((getCol(gridNum) * 3) - 2) + (getCol(buttonNum) - 1);
			// If this cell is empty 
			if (board[row][column] == 0)
			{
				filled_cells++;
				LinearLayout ll=(LinearLayout) b.getParent();
		     	ll.setBackgroundColor(Color.argb(110,0,100,200));

			}
			// Fill the corresponding cell in the matrix
			board[row][column] = Integer.parseInt(number);
			boardAdapt[gridNum][buttonNum-1]=number;
			// if the board is full calculate the result
			if (filled_cells == 81) {
				boolean res = check_res();
				if (res)
				{
					if (curr_level == top_level)
						top_level=top_level+1;
					showMessageBox(1);
				}
				else
				{
					showMessageBox(2);
				}
			}
		} 
		//If it's called from change background activity
		else
		{
			changeBackGround(backGroundTemp);
			backGround=backGroundNum.get(backGroundTemp);
		} 
	}

    /* This Function is called when the user interact with the dialog box
     * 
     */
	@Override
	public void onClick(DialogInterface arg0, int which) {
		// TODO Auto-generated method stub
		//If The user pass the level Successfully 
		if (messageBoxNum == 1) 
		{
			saveBoard();
			if (which == DialogInterface.BUTTON_POSITIVE) 
			{
				changeLevel();
			}
			else if(which==DialogInterface.BUTTON_NEGATIVE)
			{
				this.finish();
			}
		}
		else if(messageBoxNum==2)
		{
			if (which == DialogInterface.BUTTON_POSITIVE) 
			{
				Intent newLevelIntent = new Intent(this, Levels.class);
				newLevelIntent.putExtra("LEVEL", top_level);
				newLevelIntent.putExtra("back", backGround);
				startActivity(newLevelIntent);
				this.finish();
			}
			else if(which==DialogInterface.BUTTON_NEGATIVE)
			{
				//Do Nothing
			}
		
		}
		else if(messageBoxNum==3)
		{
			if (which == DialogInterface.BUTTON_POSITIVE) 
			{
				this.finish();
			}
			else if(which==DialogInterface.BUTTON_NEGATIVE)
			{
				//Do Nothing
			}
		}
		
	}
	
	//=========================Some Main Events :))===============//
	
	//========================Change BackGround===================//
	/* This function will change the background of the board
	 * 
	 */
	void changeBackGround(int background) {
		
		LinearLayout ll = (LinearLayout) findViewById(R.id.mainLayout);
		ll.setBackgroundResource(background);

		View v = findViewById(R.id.horizentalView1);
		v.setBackgroundResource(background);

		v = findViewById(R.id.horizentalView2);
		v.setBackgroundResource(background);

		v = findViewById(R.id.horizentalView3);
		v.setBackgroundResource(background);

		v = findViewById(R.id.horizentalView4);
		v.setBackgroundResource(background);

		v = findViewById(R.id.verticalView1);
		v.setBackgroundResource(background);

		v = findViewById(R.id.verticalView2);
		v.setBackgroundResource(background);

		v = findViewById(R.id.verticalView3);
		v.setBackgroundResource(background);

		v = findViewById(R.id.verticalView4);
		v.setBackgroundResource(background);

		v = findViewById(R.id.verticalView5);
		v.setBackgroundResource(background);

		v = findViewById(R.id.verticalView6);
		v.setBackgroundResource(background);

		v = findViewById(R.id.verticalView7);
		v.setBackgroundResource(background);

		v = findViewById(R.id.verticalView8);
		v.setBackgroundResource(background);

		v = findViewById(R.id.verticalView9);
		v.setBackgroundResource(background);

		v = findViewById(R.id.verticalViewLeftPad1);
		v.setBackgroundResource(background);

		v = findViewById(R.id.verticalViewLeftPad2);
		v.setBackgroundResource(background);

		v = findViewById(R.id.verticalViewLeftPad3);
		v.setBackgroundResource(background);

	}
	//========================Change BackGround===================//
	
	//========================Calculate The Board=================//
	
	/* This function will return the row of a number 
	 * between 1-9 in 3*3 matrix
	 */
	int getRow(int current) {
		if (current == 7)
			return 3;
		else
			return (current / 4) + 1;
	}
	
	/* This function will return the column of a number 
	 * between 1-9 in 3*3 matrix
	 */
	int getCol(int current) {
		int temp = current % 3;
		if (temp == 0)
			return 3;
		else
			return temp;
	}

	/* This function will check the board and return the result
	 * 
	 */
	private boolean check_res()
	{
		boolean rows, columns, threeBythree;
		// ======= Check For Rows Correctness ======= //
		rows = true;
		for (int i = 1; i <= 9; i++) {
			boolean numbers[] = { false, false, false, false, false, false,
					false, false, false, false };
			for (int j = 1; j <= 9; j++) {
				if (numbers[board[i][j]]) {
					return false;
				} else {
					numbers[board[i][j]] = true;
				}
			}
		}
		// ======= Check For Columns Correctness ======= //
		columns = true;
		for (int i = 1; i <= 9; i++) {
			boolean numbers[] = { false, false, false, false, false, false,
					false, false, false, false };
			for (int j = 1; j <= 9; j++) {
				if (numbers[board[j][i]]) {
					return false;
				} else {
					numbers[board[j][i]] = true;
				}
			}
		}

		// ======= Check For 3*3 Correctness ======= //

		for (int i = 1; i <= 9; i += 3) {
			for (int j = 1; j <= 9; j += 3) {
				boolean numbers[] = { false, false, false, false, false, false,
						false, false, false, false };

				for (int k = 0; k < 3; k++) {
					for (int v = 0; v < 3; v++) {
						if (numbers[board[i + k][j + v]]) {
							return false;
						} else {
							numbers[board[i + k][j + v]] = true;
						}
					}
				}
			}
		}
		return true;
	}
	//========================Calculate The Board==================//
	
	//========================Messages Boxes==================//

	/* This Function will show a dialog box according to (num) 
	 * 
	 */
	void showMessageBox(int num )
	{
		messageBoxNum=num;
		LayoutInflater inflator=(LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View v=inflator.inflate(R.layout.message_box,null);
		TextView tv=(TextView)  v.findViewById(R.id.textViewMessage);
		
		if(messageBoxNum==1)
		{
			
			AlertDialog alert = new AlertDialog.Builder(this).create();
			alert.setMessage("Congratulations !! You Have Complete This Level Successfully :) ");
			alert.setButton(DialogInterface.BUTTON_POSITIVE,"Next Level",this);
			alert.setButton(DialogInterface.BUTTON_NEGATIVE,"Exit",this);
			alert.show();
			
		}
		else if(messageBoxNum==2)
		{
			AlertDialog alert = new AlertDialog.Builder(this).create();
			alert.setMessage("Sorry !! Your Solution Is Wrong  :( ");
			alert.setButton(DialogInterface.BUTTON_POSITIVE,"Change Level",this);
			alert.setButton(DialogInterface.BUTTON_NEGATIVE,"Continue",this);
			alert.show();
			
		}
		else if(messageBoxNum==3)
		{
			AlertDialog alert = new AlertDialog.Builder(this).create();
			//alert.setMessage("Are You Sure You Wanna Exit The Board !! ");
			alert.setButton(DialogInterface.BUTTON_POSITIVE,"Yes",this);
			alert.setButton(DialogInterface.BUTTON_NEGATIVE,"No",this);
			tv.setText("Are You Sure You Wanna Exit The Board !! ");
			alert.setView(v);
			alert.show();
		}
		
	}
	//========================Messages Boxes==================//
	
	//========================Save Board ====================//
	/* Save the current board with it's information
	 */
	public void saveBoard()
	{
		// Convert the matrix to a string to save it in the database
		String board_S="",boardAdapt_S="";
		for (int i = 1; i <= 9; i++) {
			for (int j = 1; j <= 9; j++) {
				board_S += board[i][j];
				boardAdapt_S += boardAdapt[i][j - 1];
			}
		}
		// Connect to the database and save current information
		SQLiteDatabase db;
		db = openOrCreateDatabase("BoardsDataBase",
				MODE_PRIVATE, null);
		
		db.execSQL("UPDATE FullBoardData SET Board='"+ board_S + "' ," +
		        "BoardAdapt='"+boardAdapt_S+"'," +
		        "Filled="+filled_cells+"," +
		        "Background="+backGround+"," +
		        "TopLevel="+top_level+","+
		        "currentLevel="+curr_level +
				" WHERE User='"+userName+"';");
		
	}
	//========================Save Board ====================//
	
	//========================Change Level ==================//
	/* This Function will launch the levels activity 
	 * 
	 */
	void changeLevel()
	{
		// launch levels activity
		Intent newLevelIntent = new Intent(this, Levels.class);
		newLevelIntent.putExtra("LEVEL", top_level);
		newLevelIntent.putExtra("back", backGround);
		startActivity(newLevelIntent);
		// Close the current activity
		this.finish();
	
	}
	//========================Change Level ==================//
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    if (keyCode == KeyEvent.KEYCODE_BACK) {
	    	saveBoard();
			showMessageBox(3);
	    }
	    return super.onKeyDown(keyCode, event);
	}
	

}
