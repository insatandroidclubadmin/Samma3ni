package com.iac.hackathon.utils;

import java.util.ArrayList;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.iac.hackathon.R;
import com.iac.hackathon.manager.EmergencyCallManager;

public class MyExpandableAdapter extends BaseExpandableListAdapter {

	private Activity activity;
	private ArrayList<Object> childtems;
	private LayoutInflater inflater;
	private ArrayList<String> parentItems, child;
	private EmergencyCallManager manager = new EmergencyCallManager();

	public MyExpandableAdapter(ArrayList<String> parents,
			ArrayList<Object> childern) {
		this.parentItems = parents;
		this.childtems = childern;
	}

	public void setInflater(LayoutInflater inflater, Activity activity) {
		this.inflater = inflater;
		this.activity = activity;
	}

	@SuppressWarnings("unchecked")
	@Override
	public View getChildView(int groupPosition, final int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {

		child = (ArrayList<String>) childtems.get(groupPosition);

		TextView textView = null;

		if (convertView == null) {
			convertView = inflater.inflate(R.layout.expandable_group, null);
		}

		textView = (TextView) convertView.findViewById(R.id.expandable_emergency_call);
		textView.setText(child.get(childPosition));

		convertView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				Toast.makeText(activity, "Waiting for GPS location",
						Toast.LENGTH_SHORT).show();
				
				Toast.makeText(activity, "SMS sent",
						Toast.LENGTH_SHORT).show();
			}
		});

		return convertView;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {

		if (convertView == null) {
			convertView = inflater.inflate(R.layout.expandable_row, null);
		}
		
		CheckedTextView categoryName = (CheckedTextView)((LinearLayout) convertView).getChildAt(1);
		categoryName.setText(parentItems.get(groupPosition));
		categoryName.setChecked(isExpanded);

		ImageView categoryImage = (ImageView)((LinearLayout) convertView).getChildAt(0);
		categoryImage.setImageResource(manager.getEmergencyCallCategoryByName(parentItems.get(groupPosition)).getImage());
		return convertView;
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return null;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int getChildrenCount(int groupPosition) {
		return ((ArrayList<String>) childtems.get(groupPosition)).size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return null;
	}

	@Override
	public int getGroupCount() {
		return parentItems.size();
	}

	@Override
	public void onGroupCollapsed(int groupPosition) {
		super.onGroupCollapsed(groupPosition);
	}

	@Override
	public void onGroupExpanded(int groupPosition) {
		super.onGroupExpanded(groupPosition);
	}

	@Override
	public long getGroupId(int groupPosition) {
		return 0;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return false;
	}

}