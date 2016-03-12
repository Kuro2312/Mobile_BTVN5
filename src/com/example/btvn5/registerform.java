package com.example.btvn5;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.example.btvn5.HobbyAdapter;
import com.example.btvn5.HobbyAdapter.ViewHolder;
import com.example.btvn5.R;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class registerform extends Activity {

	private EditText _etxUser;
	private EditText _etxPass;
	private EditText _etxRetype;
	private EditText _etxDate;
	private RadioGroup _radioGroup;
	
	private String[] _items = {"Tennis", "Futbal", "Others"}; 
	private HobbyAdapter _adapter;
	private GridView _grid;
	
	private DatePicker _datePicker;
	private Calendar _calendar;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registerform);
		
		_etxUser = (EditText) findViewById(R.id.UsernameEText);
		_etxPass = (EditText) findViewById(R.id.PassEText);
		_etxRetype = (EditText) findViewById(R.id.RetypeEText);
		_etxDate = (EditText) findViewById(R.id.DateEText);
		_radioGroup = (RadioGroup) findViewById(R.id.radioGroup1);	
		_grid = (GridView) findViewById(R.id.gridHobbies);
		_adapter = new HobbyAdapter(this, _items);
		_grid.setAdapter(_adapter);
		_calendar = Calendar.getInstance();
			
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

	public boolean CheckDate()
	{
		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		try
		{
			format.parse(_etxDate.getText().toString());			
		}
		catch (ParseException e)
		{
			return false;
		}
		return true;		
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
		int count = _radioGroup.getChildCount();
        for (int i = 0; i < count; i++) 
        {
            View o = _radioGroup.getChildAt(i);
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
 	
		String pass = _etxPass.getText().toString();
		if (pass.equals(_etxRetype.getText().toString()) == false)
		{
			Toast.makeText(this, "Passwords do not match! Try again!", Toast.LENGTH_SHORT).show();
			return;		
		}
		
		if (CheckDate() == false)
		{
			Toast.makeText(this, "Invalid date!", Toast.LENGTH_SHORT).show();
			return;
		}
		
		if (gender == null)
		{
			Toast.makeText(this, "Please choose gender!", Toast.LENGTH_SHORT).show();
			return;
		}
		
		Intent myIntent = new Intent(this, resultform.class);
		myIntent.putExtra("Username", _etxUser.getText().toString());
		myIntent.putExtra("Password", _etxPass.getText().toString());
		myIntent.putExtra("Birthday", _etxDate.getText().toString());
		myIntent.putExtra("Gender", gender);
		myIntent.putExtra("Hobbies", hobbies);
		startActivity(myIntent);
	}
	
	public void ResetInfo()
	{
		_etxUser.getText().clear();
		_etxPass.getText().clear();
		_etxRetype.getText().clear();
		_etxDate.getText().clear();
		_radioGroup.clearCheck();
	}
	
	public void SelectDate()
	{
		int year = _calendar.get(Calendar.YEAR);
		int month = _calendar.get(Calendar.MONTH);
		int day = _calendar.get(Calendar.DAY_OF_MONTH);
		DatePickerDialog datePickerDialog = new DatePickerDialog(this, myDateSetListener, year, month, day);
		
		datePickerDialog.show();
	}
	
	private DatePickerDialog.OnDateSetListener 	myDateSetListener = new DatePickerDialog.OnDateSetListener() {
		
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
			// TODO Auto-generated method stub
			_etxDate.setText(new StringBuilder().append(String.format("%02d", dayOfMonth)).append("/")
					.append(String.format("%02d", monthOfYear + 1)).append("/").append(year));
		}
	};
	
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
