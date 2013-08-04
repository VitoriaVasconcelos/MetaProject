package com.project.meta.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.project.meta.R;
import com.project.meta.model.Meta;

public class MetasAdapter extends BaseAdapter{
	
	private Context context;
	private List<Meta> metas;
	private LinearLayout ll;
	private TextView textName;
	private TextView textDescription;
	
	public MetasAdapter(Context context, List<Meta> metas) {
		this.context = context;
		this.metas = metas;
	}

	@Override
	public int getCount() {
		if(metas == null) {
			return 0;
		}
		return metas.size();
	}

	@Override
	public Object getItem(int position) {
		if(metas == null) {
			return position;
		}
		return metas.get(position);
	}

	@Override
	public long getItemId(int position) {
		
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ll = (LinearLayout) LayoutInflater.from(this.context).inflate(
				R.layout.list_meta_item, null);

		textName = (TextView) ll.findViewById(R.id.text_name);
		textDescription = (TextView) ll.findViewById(R.id.text_description);
		
		textName.setText(metas.get(position).getName());
		textDescription.setText(metas.get(position).getDescription());

		ll.setTag(metas.get(position));
		
		return ll;
	}

}
