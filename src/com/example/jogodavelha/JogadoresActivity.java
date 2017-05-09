package com.example.jogodavelha;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class JogadoresActivity extends Activity {
		
	private TextView[] avisos = new TextView[3];
	private EditText[] jogadores = new EditText[2];
	private Button ok;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_jogadores);
		
		jogadores[0] = (EditText) findViewById(R.id.editTextJ1);
		jogadores[1] = (EditText) findViewById(R.id.editTextJ2);
		ok = (Button) findViewById(R.id.buttonOK);
		
		ok.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(JogadoresActivity.this, JogoDaVelhaActivity.class);
				intent.putExtra("JOG1", jogadores[0].getText().toString());
				intent.putExtra("JOG2", jogadores[1].getText().toString());
				
				startActivity(intent);
				JogadoresActivity.this.finish();
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.jogadores, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
