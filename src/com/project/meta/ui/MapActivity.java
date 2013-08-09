package com.project.meta.ui;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.project.meta.R;

public class MapActivity extends FragmentActivity{
	GoogleMap map;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.map);
		
		if(getIntent() != null) {
			String origin = getIntent().getStringExtra(ListMetaItemActivity.ORIGIN);
			String destination = getIntent().getStringExtra(ListMetaItemActivity.DESTINATION);
			map = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
			if(origin != null && !origin.isEmpty() && !destination.isEmpty() && destination != null) {
				traceRouter(origin, destination);
			}
		}
		
		
			
		
	}
	
	protected void traceRouter(String origin, String destination) {
			try {
				
				Geocoder gc = new Geocoder(this, new Locale("pt", "BR"));
				List<Address> listOrigin = gc.getFromLocationName(origin, 1);
				List<Address> listDestination = gc.getFromLocationName(destination, 1);
				
				String originMap = String.valueOf(listOrigin.get(0).getLatitude())+","+String.valueOf(listOrigin.get(0).getLongitude());
				String destinationMap = String.valueOf(listDestination.get(0).getLatitude())+","+String.valueOf(listDestination.get(0).getLongitude());
				
				String url = "http://map.google.com/maps?f=d&saddr="+originMap+"&daddr="+destinationMap+"&hl=pt";
				startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
				
				LatLng latLng = new LatLng(listDestination.get(0).getLatitude(), listDestination.get(0).getLongitude());
			    configuraPosicao(map, latLng);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
	}
	
	private void configuraPosicao(GoogleMap map, LatLng latLng) {

		map.setMapType(GoogleMap.MAP_TYPE_SATELLITE);
		map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17.0f));
	}
}
