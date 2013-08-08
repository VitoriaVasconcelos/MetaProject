package com.project.meta.ui;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.project.meta.R;
import com.project.meta.model.Meta;
//import com.google.android.gms.maps.MapFragment;

public class ListMetaItemActivity extends android.support.v4.app.FragmentActivity{
	Meta meta;
	TextView textName;
	TextView textDescription;
	private SupportMapFragment mapFragment;
	GoogleMap map;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_meta_item);

		meta = (Meta) getIntent().getSerializableExtra("meta");
		
		if(meta != null) {
			textName = (TextView)findViewById(R.id.text_name);
			textDescription = (TextView)findViewById(R.id.text_description);
			
			textName.setText(meta.getName());
			textDescription.setText(meta.getDescription());
			
			if(meta.getOrigin() != null && !meta.getOrigin().isEmpty()) {
				mapFragment = (SupportMapFragment)getSupportFragmentManager().findFragmentByTag("tag");
				if(mapFragment != null) {
					mapFragment = SupportMapFragment.newInstance();
					android.support.v4.app.FragmentTransaction t = getSupportFragmentManager().beginTransaction();
					t.add(R.id.mapFragment, mapFragment, "tag");
					t.commit();
					
				}
			}
		}
	}
	
	protected void onResume() {
		super.onResume();
		if(map == null && mapFragment!= null) {
			try {
				map = mapFragment.getMap();
				Geocoder gc = new Geocoder(this, new Locale("pt", "BR"));
				List<Address> listOrigin = gc.getFromLocationName(meta.getOrigin(), 1);
				List<Address> listDestination = gc.getFromLocationName(meta.getDestination(), 1);
				
				String origin = String.valueOf(listOrigin.get(0).getLatitude())+String.valueOf(listOrigin.get(0).getLongitude());
				String destination = String.valueOf(listDestination.get(0).getLatitude())+String.valueOf(listDestination.get(0).getLongitude());
				
				String url = "http://map.google.com/maps?f=d&saddr="+origin+"&daddr="+destination+"&hl=pt";
				startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
				
				LatLng latLng = new LatLng(listDestination.get(0).getLatitude(), listDestination.get(0).getLongitude());
			    configuraPosicao(map, latLng);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	private void configuraPosicao(GoogleMap map, LatLng latLng) {

		map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
		map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17.0f));
	}

}
