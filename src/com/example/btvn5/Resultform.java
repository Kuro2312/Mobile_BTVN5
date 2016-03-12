package com.example.btvn5;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class resultform extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_resultform);
		
		Intent intent = getIntent();

	    String username = intent.getStringExtra("Username");
	    String password = intent.getStringExtra("Password");
	    int l = password.length();
	    password = "";
	    for (int i = 0; i < l; i++)
	    	password += "*";
	    
	    String birthday = intent.getStringExtra("Birthday");
	    String gender = intent.getStringExtra("Gender");
	    String hobbies = intent.getStringExtra("Hobbies");
	    
	    TextView textUser = (TextView) findViewById(R.id.textView2);
	    TextView textPass = (TextView) findViewById(R.id.textView3);
	    TextView textBirthday = (TextView) findViewById(R.id.textView4);
	    TextView textGender = (TextView) findViewById(R.id.textView5);
	    TextView textHobbies = (TextView) findViewById(R.id.textView6);
	    
	    textUser.setText("UserName: " + username);
	    textPass.setText("Password: " + password);
	    textBirthday.setText("Birthday: " + birthday);
	    textGender.setText("Gender: " + gender);
	    textHobbies.setText("Hobbies: " + hobbies);
	    
	    Button btn = (Button) findViewById(R.id.btnExit);
	    btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//finish();
				//System.exit(0);
				resultform.this.finishAffinity();
			}
		});
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.resultform, menu);
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
