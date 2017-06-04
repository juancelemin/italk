package com.appmyproyect.jcelemin.com.italk;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Locale;

public class SeleccionarActivity extends AppCompatActivity implements View.OnClickListener, TextToSpeech.OnInitListener {

    ImageButton SeleccionarConversacion, SeleccionarSaludos,SeleccionarNecesidades,salir;
    TextToSpeech tts;
    String frase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seleccionar);

        Intent intent = getIntent();
        Bundle extra = intent.getExtras();

        if( extra != null){
            frase = extra.getString("FRASE");
        }
        
        SeleccionarConversacion = (ImageButton)findViewById(R.id.btnConver);
        SeleccionarNecesidades = (ImageButton)findViewById(R.id.btnNecesi);
        SeleccionarSaludos = (ImageButton)findViewById(R.id.btnSalu);
        salir = (ImageButton)findViewById(R.id.btnSeleccionarSalir);

        SeleccionarConversacion.setOnClickListener(this);
        SeleccionarSaludos.setOnClickListener(this);
        SeleccionarNecesidades.setOnClickListener(this);
        salir.setOnClickListener(this);


        tts = new TextToSpeech(this,this);

    }

    @Override
    public void onClick(View v) {
        String p;
        switch (v.getId()){
            case R.id.btnConver:
                p = "Gestionar conversacion";
                hablar(p);
                Intent i = new Intent(SeleccionarActivity.this,EditarConversacionActivity.class);
                i.putExtra("FRASE",frase);
                startActivity(i);

                break;
            case R.id.btnNecesi:
                p = "Gestionar necesidades";
                hablar(p);
                Intent ii = new Intent(SeleccionarActivity.this,EditarNesecidadActivity.class);
                ii.putExtra("FRASE",frase);
                startActivity(ii);
                break;
            case R.id.btnSalu:
                p = "Gestionar saludos";
                hablar(p);
                Intent iii = new Intent(SeleccionarActivity.this,EditarSaludoActivity.class);
                iii.putExtra("FRASE",frase);
                startActivity(iii);
                break;
            case R.id.btnSeleccionarSalir:
                p = "regresar";
                hablar(p);
                finish();
                break;
        }
    }

    @Override
    public void onInit(int i) {
        if (i == TextToSpeech.SUCCESS) {
            //Setting speech Language
            Locale P= new Locale("ES");
            tts.setLanguage(P);
            tts.setPitch(1);
        }
    }

    public void hablar (String p){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            tts.speak(p, TextToSpeech.QUEUE_FLUSH, null, null);

            //Toast.makeText(this, "5.0", Toast.LENGTH_SHORT).show();
        }
        else {
            tts.speak(p, TextToSpeech.QUEUE_ADD, null);
            //Toast.makeText(this, "4.4.4", Toast.LENGTH_SHORT).show();
        }
    }


}
