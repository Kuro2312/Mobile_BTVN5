package com.example.btvn5;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView;

public class HobbyAdapter extends ArrayAdapter
{
	String[] _items;
	Context _context;
	
	public HobbyAdapter(Context context, String[] resource) 
	{
		 super(context, R.layout.hobby, resource);
		 // TODO Auto-generated constructor stub
		 this._context = context;
		 this._items = resource;
	}
			
	public class ViewHolder {
		   TextView name;
		   CheckBox box;
	}
	
	 @Override
	 public View getView(int position, View convertView, ViewGroup parent) 
	 {
		 // TODO Auto-generated method stub
		 LayoutInflater inflater = ((Activity)_context).getLayoutInflater();
		 convertView = inflater.inflate(R.layout.hobby, parent, false); 
		 
		 ViewHolder holder = new ViewHolder();
		 holder.name = (TextView) convertView.findViewById(R.id.textView1);
		 holder.box = (CheckBox) convertView.findViewById(R.id.checkBox1);		
		 	   
		 holder.name.setText(_items[position]);
		 
		 convertView.setTag(holder);
		 return convertView;
	 }
}

