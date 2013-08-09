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

public class TesteActivity extends FragmentActivity{
	GoogleMap map;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.teste);
		map = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map)).getMap();
//		if(meta.getOrigin() != null && !meta.getOrigin().isEmpty()) {
			traceRouter();
//		}
			
		
	}
	
	protected void traceRouter() {
		if(map == null) {
			try {
				
				Geocoder gc = new Geocoder(this, new Locale("pt", "BR"));
				List<Address> listOrigin = gc.getFromLocationName("Rua Joao Fernandes Vieira, Recife", 1);
				List<Address> listDestination = gc.getFromLocationName("Rua Bione, Recife", 1);
				
				String origin = String.valueOf(listOrigin.get(0).getLatitude())+","+String.valueOf(listOrigin.get(0).getLongitude());
				String destination = String.valueOf(listDestination.get(0).getLatitude())+","+String.valueOf(listDestination.get(0).getLongitude());
				
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
