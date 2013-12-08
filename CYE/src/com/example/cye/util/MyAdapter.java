package com.example.cye.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;

import com.example.cye.R;

public class MyAdapter extends BaseAdapter {

	Context context;
	List<ItemInfo> title;

	public MyAdapter(Context context, List<ItemInfo> list) {
		this.context = context;
		title = list;
	}

	public int getCount() {
		return title.size();
	}

	public Object getItem(int i) {
		return title.get(i);
	}

	public long getItemId(int i) {
		return (long) i;
	}

	public View getView(int position, View convertView, ViewGroup viewgroup) {
		LayoutInflater layoutinflater = (LayoutInflater) context
		        .getSystemService("layout_inflater");
		Holder holder;

		if (convertView == null) {
			holder = new Holder();
			convertView = layoutinflater.inflate(R.layout.text_title_custom, null);
			holder.textTitle = (TextView) convertView.findViewById(R.id.text_title);
			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
		}

		holder.textTitle.setText(((ItemInfo) title.get(position)).getTitle());

		return convertView;
	}

	private static class Holder {

		private TextView textTitle;
	}
}
