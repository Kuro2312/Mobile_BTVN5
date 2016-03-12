package com.example.btvn5;

import java.util.ArrayList;

import com.example.btvn5.HobbyAdapter;
import com.example.btvn5.HobbyAdapter.ViewHolder;
import com.example.btvn5.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends Activity {

	EditText etxUser;
	EditText etxPass;
	EditText etxRetype;
	EditText etxDate;
	RadioGroup radioGroup;
	
	String[] _items = {"Tennis", "Fulbal", "Others"}; 
	HobbyAdapter _adapter;
	GridView _grid;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registerform);
		
		etxUser = (EditText) findViewById(R.id.UsernameEText);
		etxPass = (EditText) findViewById(R.id.PassEText);
		etxRetype = (EditText) findViewById(R.id.RetypeEText);
		etxDate = (EditText) findViewById(R.id.DateEText);
		radioGroup = (RadioGroup) findViewById(R.id.radioGroup1);	
		_grid = (GridView) findViewById(R.id.gridHobbies);
		_adapter = new HobbyAdapter(this, _items);
		_grid.setAdapter(_adapter);
			
		Button btnReset = (Button) findViewById(R.id.btnReset);
		btnReset.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ResetInfo();			
			}
		});
		
		Button btnSelect = (Button) findViewById(R.id.btnSelect);
		btnSelect.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SelectDate();			
			}
		});

		Button btnSignUp = (Button) findViewById(R.id.btnSignup);
		btnSignUp.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				SignUp();			
			}
		});
		
		
	}

	public View getViewByPosition(int pos, GridView listView) 
	{
	    final int firstListItemPosition = listView.getFirstVisiblePosition();
	    final int lastListItemPosition = firstListItemPosition + listView.getChildCount() - 1;

	    if (pos < firstListItemPosition || pos > lastListItemPosition ) 
	    {
	        return listView.getAdapter().getView(pos, null, listView);
	    } else 
	    {
	        final int childIndex = pos - firstListItemPosition;
	        return listView.getChildAt(childIndex);
	    }
	}
	
	public String GetHobbies()
	{
		String hobbies = "";
		
		int count = _adapter.getCount();
		
		for (int i = 0; i < count; i++)
		{		
			View view = getViewByPosition(i, _grid);
			
			ViewHolder holder = (ViewHolder) view.getTag();
			
			if (holder.box.isChecked())
			{
				if (hobbies.equals("") == false)
					hobbies += ", ";
				hobbies += holder.name.getText().toString();
			}
		}
		
		return hobbies;
	}
	
	public String GetGender()
	{
		int count = radioGroup.getChildCount();
        for (int i = 0; i < count; i++) 
        {
            View o = radioGroup.getChildAt(i);
            if (o instanceof RadioButton) 
            {
                RadioButton r = (RadioButton) o;
                if (r.isChecked())
                	return r.getText().toString();
            }
        }
        
        return null;
	}
	
	public void SignUp()
	{			 	        
		String gender = GetGender();		
		String hobbies = GetHobbies();
 	
		String pass = etxPass.getText().toString();
		if (pass.equals(etxRetype.getText().toString()) == false)
		{
			Toast.makeText(this, "Passwords do not match! Try again!", Toast.LENGTH_SHORT).show();
			return;		
		}
		
		if (gender == null)
		{
			Toast.makeText(this, "Please choose gender!", Toast.LENGTH_SHORT).show();
			return;
		}
		
		Intent myIntent = new Intent(this, Resultform.class);
		myIntent.putExtra("Username", etxUser.getText().toString());
		myIntent.putExtra("Password", etxPass.getText().toString());
		myIntent.putExtra("Birthday", etxDate.getText().toString());
		myIntent.putExtra("Gender", gender);
		myIntent.putExtra("Hobbies", hobbies);
		startActivity(myIntent);
	}
	
	public void ResetInfo()
	{
		etxUser.getText().clear();
		etxPass.getText().clear();
		etxRetype.getText().clear();
		etxDate.getText().clear();
		radioGroup.clearCheck();
	}
	
	public void SelectDate()
	{
		
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
