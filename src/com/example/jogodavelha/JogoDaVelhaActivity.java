package com.example.jogodavelha;

import java.util.ArrayList;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class JogoDaVelhaActivity extends Activity {

	private View view;
	private Boolean jogador = true;
	private int pos;
	private int[] vencedor = {0, 0, 0}; //0 - j2, 1 - j1, 2 - empate.

	private ArrayList<ImageButton> listX = new ArrayList<ImageButton>();
	private ArrayList<ImageButton> listCirculo = new ArrayList<ImageButton>();
	private ArrayList<ImageButton> listVazio = new ArrayList<ImageButton>();
	@SuppressWarnings("unused")
	private Button btnLimpar;
	@SuppressWarnings("unused")
	private TextView txtVitoria;
	private TextView txtViewJogador1;
	private TextView txtViewJogador2;
	private TextView txtViewEmpate;
	private String jog1;
	private String jog2;

	@SuppressLint("InflateParams")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		view = getLayoutInflater().inflate(R.layout.activity_jogo_da_velha, null);
		setContentView(view);

		Intent intent = getIntent();
		jog1 = intent.getStringExtra("JOG1");
		jog2 = intent.getStringExtra("JOG2");
		
		if (jog1 == null || jog1.equals("")){
			jog1 = "Jogador 1";
		}
		if (jog2 == null || jog2.equals("")) {
			jog2 = "Jogador 2";
		}
		
		listVazio.add((ImageButton) findViewById(R.id.vazio1));
		listCirculo.add((ImageButton) findViewById(R.id.circulo1));
		listX.add((ImageButton) findViewById(R.id.x1));
		listVazio.add((ImageButton) findViewById(R.id.vazio2));
		listCirculo.add((ImageButton) findViewById(R.id.circulo2));
		listX.add((ImageButton) findViewById(R.id.x2));
		listVazio.add((ImageButton) findViewById(R.id.vazio3));
		listCirculo.add((ImageButton) findViewById(R.id.circulo3));
		listX.add((ImageButton) findViewById(R.id.x3));
		listVazio.add((ImageButton) findViewById(R.id.vazio4));
		listCirculo.add((ImageButton) findViewById(R.id.circulo4));
		listX.add((ImageButton) findViewById(R.id.x4));
		listVazio.add((ImageButton) findViewById(R.id.vazio5));
		listCirculo.add((ImageButton) findViewById(R.id.circulo5));
		listX.add((ImageButton) findViewById(R.id.x5));
		listVazio.add((ImageButton) findViewById(R.id.vazio6));
		listCirculo.add((ImageButton) findViewById(R.id.circulo6));
		listX.add((ImageButton) findViewById(R.id.x6));
		listVazio.add((ImageButton) findViewById(R.id.vazio7));
		listCirculo.add((ImageButton) findViewById(R.id.circulo7));
		listX.add((ImageButton) findViewById(R.id.x7));
		listVazio.add((ImageButton) findViewById(R.id.vazio8));
		listCirculo.add((ImageButton) findViewById(R.id.circulo8));
		listX.add((ImageButton) findViewById(R.id.x8));
		listVazio.add((ImageButton) findViewById(R.id.vazio9));
		listCirculo.add((ImageButton) findViewById(R.id.circulo9));
		listX.add((ImageButton) findViewById(R.id.x9));

		btnLimpar = (Button) findViewById(R.id.play);
		txtVitoria = (TextView) findViewById(R.id.textViewVitoria);
		txtViewJogador1 = (TextView) findViewById(R.id.textViewJ1);
		txtViewJogador2 = (TextView) findViewById(R.id.textViewJogador2);
		txtViewEmpate = (TextView) findViewById(R.id.textViewEmpate);

		txtViewJogador1.setText(jog1+": 0");
		txtViewJogador2.setText(jog2+": 0");
		invisivel();
	}

	private void invisivel(){
		for(int i = 0; i < 9; i++){
			listX.get(i).setVisibility(ImageButton.INVISIBLE);
			listCirculo.get(i).setVisibility(ImageButton.INVISIBLE);
		}
		System.out.println("Saiu");
	}

	//metodo que ativado pelo ImageButton Vazio após clicado
	public void mudar(View v) {
		System.out.println("Tag "+v.getTag().toString());
		pos = Integer.valueOf(v.getTag().toString());
		listVazio.get(pos).setVisibility(ImageButton.INVISIBLE);
		if (jogador) {
			listX.get(pos).setVisibility(ImageButton.VISIBLE);
			listCirculo.get(pos).setVisibility(ImageButton.INVISIBLE);
			jogador = false;
		}
		else {
			listX.get(pos).setVisibility(ImageButton.INVISIBLE);
			listCirculo.get(pos).setVisibility(ImageButton.VISIBLE);
			jogador = true;
		}
		vencedor();
	}

	//metodo que o botão 'Limpar' aciona
	public void play(View v){
		System.out.println("Play");
		AlertDialog.Builder alerta = new AlertDialog.Builder(this);
		alerta.setTitle("Limpar");
		alerta.setMessage("O que você deseja fazer?");
		alerta.setPositiveButton("Reiniciar", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(JogoDaVelhaActivity.this, JogadoresActivity.class);
				startActivity(intent);
				JogoDaVelhaActivity.this.finish();
			}
		});
		alerta.setNegativeButton("Limpar", new OnClickListener() {

			@Override
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				limpar();
				for (int j = 0; j < vencedor.length; j++) {
					vencedor[j] = 0;
					System.out.println("j "+vencedor[j]);
					nVitoria();
				}
			}
		});
		alerta.create();
		alerta.show();
	}

	private void limpar (){
		for (int i = 0; i < 9; i++){
			jogador = true;
			listVazio.get(i).setVisibility(ImageButton.VISIBLE);
			listCirculo.get(i).setVisibility(ImageButton.INVISIBLE);
			listX.get(i).setVisibility(ImageButton.INVISIBLE);
		}
	}

	private void vencedor (){
		int cont = 0;
		if (((listCirculo.get(0).getVisibility() == ImageButton.VISIBLE) && 
				(listCirculo.get(1).getVisibility() == ImageButton.VISIBLE) &&
				(listCirculo.get(2).getVisibility() == ImageButton.VISIBLE))||
				((listCirculo.get(3).getVisibility() == ImageButton.VISIBLE) && 
						(listCirculo.get(4).getVisibility() == ImageButton.VISIBLE) &&
						(listCirculo.get(5).getVisibility() == ImageButton.VISIBLE))||
				((listCirculo.get(6).getVisibility() == ImageButton.VISIBLE) && 
						(listCirculo.get(7).getVisibility() == ImageButton.VISIBLE) &&
						(listCirculo.get(8).getVisibility() == ImageButton.VISIBLE))||
				((listCirculo.get(0).getVisibility() == ImageButton.VISIBLE) && 
						(listCirculo.get(4).getVisibility() == ImageButton.VISIBLE) &&
						(listCirculo.get(8).getVisibility() == ImageButton.VISIBLE))||
				((listCirculo.get(2).getVisibility() == ImageButton.VISIBLE) && 
						(listCirculo.get(4).getVisibility() == ImageButton.VISIBLE) &&
						(listCirculo.get(6).getVisibility() == ImageButton.VISIBLE))||
				((listCirculo.get(0).getVisibility() == ImageButton.VISIBLE) && 
						(listCirculo.get(3).getVisibility() == ImageButton.VISIBLE) &&
						(listCirculo.get(6).getVisibility() == ImageButton.VISIBLE))||
				((listCirculo.get(1).getVisibility() == ImageButton.VISIBLE) && 
						(listCirculo.get(4).getVisibility() == ImageButton.VISIBLE) &&
						(listCirculo.get(7).getVisibility() == ImageButton.VISIBLE))||
				((listCirculo.get(2).getVisibility() == ImageButton.VISIBLE) && 
						(listCirculo.get(5).getVisibility() == ImageButton.VISIBLE) &&
						(listCirculo.get(8).getVisibility() == ImageButton.VISIBLE))) {

			vencedor[0]++;
			AlertDialog.Builder alerta = new AlertDialog.Builder(this);
			alerta.setTitle("Vencedor!");
			alerta.setMessage(jog2+" ganhou!");
			alerta.setNeutralButton("OK", new OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					limpar();
				}
			});
			alerta.setCancelable(false);
			alerta.create();
			alerta.show();
		}
		else if (((listX.get(0).getVisibility() == ImageButton.VISIBLE) && 
				(listX.get(1).getVisibility() == ImageButton.VISIBLE) &&
				(listX.get(2).getVisibility() == ImageButton.VISIBLE))||
				((listX.get(3).getVisibility() == ImageButton.VISIBLE) && 
						(listX.get(4).getVisibility() == ImageButton.VISIBLE) &&
						(listX.get(5).getVisibility() == ImageButton.VISIBLE))||
				((listX.get(6).getVisibility() == ImageButton.VISIBLE) && 
						(listX.get(7).getVisibility() == ImageButton.VISIBLE) &&
						(listX.get(8).getVisibility() == ImageButton.VISIBLE))||
				((listX.get(0).getVisibility() == ImageButton.VISIBLE) && 
						(listX.get(4).getVisibility() == ImageButton.VISIBLE) &&
						(listX.get(8).getVisibility() == ImageButton.VISIBLE))||
				((listX.get(2).getVisibility() == ImageButton.VISIBLE) && 
						(listX.get(4).getVisibility() == ImageButton.VISIBLE) &&
						(listX.get(6).getVisibility() == ImageButton.VISIBLE))||
				((listX.get(0).getVisibility() == ImageButton.VISIBLE) && 
						(listX.get(3).getVisibility() == ImageButton.VISIBLE) &&
						(listX.get(6).getVisibility() == ImageButton.VISIBLE))||
				((listX.get(1).getVisibility() == ImageButton.VISIBLE) && 
						(listX.get(4).getVisibility() == ImageButton.VISIBLE) &&
						(listX.get(7).getVisibility() == ImageButton.VISIBLE))||
				((listX.get(2).getVisibility() == ImageButton.VISIBLE) && 
						(listX.get(5).getVisibility() == ImageButton.VISIBLE) &&
						(listX.get(8).getVisibility() == ImageButton.VISIBLE))) {

			vencedor[1]++;
			AlertDialog.Builder alerta = new AlertDialog.Builder(this);
			alerta.setCancelable(false);
			alerta.setTitle("Vencedor!");
			alerta.setMessage(jog1+" ganhou");
			alerta.setNeutralButton("OK", new OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					limpar();
				}
			});
			alerta.create();
			alerta.show();
		}
		else {
			for (ImageButton imageButton : listVazio) {
				if (imageButton.getVisibility() == ImageButton.INVISIBLE) {
					cont++;
				}
			}
		}

		if (cont >= 9) {
			vencedor[2]++;
			cont = 0;
			AlertDialog.Builder alerta = new AlertDialog.Builder(this);
			alerta.setCancelable(false);
			alerta.setTitle("Deu Velha!");
			alerta.setMessage("Houve um empate");
			alerta.setNeutralButton("OK", new OnClickListener() {

				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					limpar();
				}
			});
			alerta.create();
			alerta.show();
		}
		nVitoria();
	}

	private void nVitoria(){
		txtViewJogador1.setText(jog1+": "+vencedor[1]);
		txtViewJogador2.setText(jog2+": "+vencedor[0]);
		txtViewEmpate.setText("Empates: "+vencedor[2]);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.jogo_da_velha, menu);
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