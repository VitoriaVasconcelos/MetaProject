package com.project.meta.ui;

import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;

import com.project.meta.R;
import com.project.meta.adapter.MetasAdapter;
import com.project.meta.model.Meta;
import com.project.meta.repository.MetaRepository;

public class ListMetasActivity extends Activity{
	private ProgressDialog mProgressDialog;
	private List<Meta> metas;
	private ListView listMetas;
	private BaseAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_metas);

	}
	
	private void initViews() {
		listMetas = (ListView) findViewById(R.id.lv_metas);

		adapter = new MetasAdapter(this, metas);
		listMetas.setAdapter(adapter);

		listMetas.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> adapter, View v,
					int position, long arg3) {
				Intent it = new Intent("ITEM_META");
				it.putExtra("meta", (Meta)v.getTag());
				startActivity(it);
			}
		});
	}
	
	@Override
	protected void onResume() {
		new SyncDataTask().execute();
		super.onResume();
	}
	
	private class SyncDataTask extends AsyncTask<Object, Object, Object> {

		@Override
		protected void onPreExecute() {
			mProgressDialog = new ProgressDialog(ListMetasActivity.this);
			mProgressDialog.setTitle("Aguarde...");
			mProgressDialog.setMessage("Carregando informações");
			mProgressDialog.show();
			super.onPreExecute();
		}

		@Override
		protected Object doInBackground(Object... params) {
			metas = MetaRepository.getInstance(ListMetasActivity.this).getAllMetas();
			return null;
		}

		@Override
		protected void onPostExecute(Object result) {
			if (mProgressDialog != null && mProgressDialog.isShowing()) {
				mProgressDialog.dismiss();
			}
			initViews();
			super.onPostExecute(result);

		}

	}
}


