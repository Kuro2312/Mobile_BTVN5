package com.example.btvn5;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends Activity {

	EditText etxUser;
	EditText etxPass;
	EditText etxRetype;
	EditText etxDate;
	RadioGroup radioGroup;
	CheckBox cbxTennis;
	CheckBox cbxFulbal;
	CheckBox cbxOthers;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		etxUser = (EditText) findViewById(R.id.UsernameEText);
		etxPass = (EditText) findViewById(R.id.PassEText);
		etxRetype = (EditText) findViewById(R.id.RetypeEText);
		etxDate = (EditText) findViewById(R.id.DateEText);
		radioGroup = (RadioGroup) findViewById(R.id.radioGroup1);
		cbxTennis = (CheckBox) findViewById(R.id.checkBox1);
		cbxFulbal = (CheckBox) findViewById(R.id.checkBox2);
		cbxOthers = (CheckBox) findViewById(R.id.checkBox3);
		
		//int id = radioGroup.getCheckedRadioButtonId();
		
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
		myIntent.putExtra("Hobbies", etxPass.getText().toString());
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
