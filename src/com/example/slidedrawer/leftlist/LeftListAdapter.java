package com.example.slidedrawer.leftlist;

import java.util.ArrayList;

import com.example.slidedrawer.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class LeftListAdapter extends BaseAdapter{
	
	
	private Context context;
	private ArrayList<LeftListModel> arrayList;

	public LeftListAdapter(Context context, ArrayList<LeftListModel> arrayList) {
		this.context = context;
		this.arrayList = arrayList;
	}

	@Override
	public int getCount() {
		if (arrayList != null) {
			return arrayList.size();
		}
		return 0;
	}

	@Override
	public Object getItem(int position) {
		if (arrayList != null) {
			return arrayList.get(position);
		}
		return null;
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHold hold;
		if (convertView == null) {
			hold = new ViewHold();
			convertView = LayoutInflater.from(context).inflate(R.layout.left_list_item, null);
			convertView.setTag(hold);
		}else {
			hold=(ViewHold) convertView.getTag();
		}
		
		hold.imageView=(ImageView) convertView.findViewById(R.id.iv_left_list_image);
		hold.textView=(TextView) convertView.findViewById(R.id.tv_left_list_text);
		
		hold.imageView.setImageResource(arrayList.get(position).getImageView());
		hold.textView.setText(arrayList.get(position).getText());
		return convertView;
	}

	static class ViewHold {
		public ImageView imageView;
		public TextView textView;
	}

}
