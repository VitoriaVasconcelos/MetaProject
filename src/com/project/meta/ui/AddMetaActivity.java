package com.project.meta.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.project.meta.R;
import com.project.meta.model.Meta;
import com.project.meta.repository.MetaRepository;

public class AddMetaActivity extends Activity{
	Meta meta;
	
	private Button saveButton;
	private EditText name, description;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_meta);
		
		name = (EditText) findViewById(R.id.nameField);
        description = (EditText) findViewById(R.id.descriptionField);
        saveButton = (Button) findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
					handleBtnClick();
				}
			});

	}
	
    public void handleBtnClick() throws NullPointerException {
    	
    	meta = new Meta(name.getText().toString(), description.getText().toString());
    	
    	if(!meta.getName().isEmpty()) {
       		MetaRepository.getInstance(AddMetaActivity.this).addMeta(meta);
    		Toast.makeText(this, "New Meta saved!", Toast.LENGTH_LONG).show();
    		
    		Intent intent = new Intent(this, ListMetasActivity.class);
    		startActivity(intent);
    		
    		name.setText("");
    		description.setText("");
    	} else {
    		Toast.makeText(this, "Meta name can't be blank!", Toast.LENGTH_LONG).show();
    	}
    }
}
