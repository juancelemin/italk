package com.appmyproyect.jcelemin.com.italk;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.Locale;

public class SaludoActivity extends AppCompatActivity implements View.OnClickListener, TextToSpeech.OnInitListener {

    ImageButton hola,buenosDias,buenasNoches,adios,hastaManana,addSaludo,atras,home;
    TextToSpeech tts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saludo);

        hola = (ImageButton)findViewById(R.id.btnHi);
        buenosDias = (ImageButton)findViewById(R.id.btnGoodMorning);
        buenasNoches = (ImageButton)findViewById(R.id.btnGoodNight);
        adios = (ImageButton)findViewById(R.id.btnBye);
        hastaManana = (ImageButton)findViewById(R.id.btnTomorrow);
        addSaludo = (ImageButton)findViewById(R.id.btnAddGreeds);
        atras = (ImageButton)findViewById(R.id.btnBack);
        home = (ImageButton)findViewById(R.id.btnHome);

        hola.setOnClickListener(this);
        buenosDias.setOnClickListener(this);
        buenasNoches.setOnClickListener(this);
        adios.setOnClickListener(this);
        hastaManana.setOnClickListener(this);
        addSaludo.setOnClickListener(this);
        atras.setOnClickListener(this);
        home.setOnClickListener(this);

        tts = new TextToSpeech(this,this);
    }

    @Override
    public void onClick(View v) {
        String p;
        switch (v.getId()){
            case R.id.btnHi:
                p ="hola. como estas";
                hablar(p);
                //holaComoEstas.start();
                break;
            case R.id.btnGoodMorning:
                p ="Buenos dias";
                hablar(p);
                //buendia.start();
                break;
            case R.id.btnGoodNight:
                p ="Buenas noches";
                hablar(p);
               // buenaNoche.start();
                break;
            case R.id.btnBye:
                p ="Hasta luego. que estes bien";
                hablar(p);
                //hastaluego.start();
                break;
            case R.id.btnTomorrow:
                p ="hasta mañana";
                hablar(p);
                //hastamanana.start();
                break;
            case R.id.btnAddGreeds:
                p ="Buenas tardes";
                hablar(p);
                break;
            case R.id.btnBack:
                p ="mas";
                hablar(p);
                Intent inten = new Intent(SaludoActivity.this,EditarSaludoActivity.class);
                startActivity(inten);
                break;
            case R.id.btnHome:
                p = "regresar ha. ahi tolk";
                hablar(p);
                finish();
                break;
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
    public void onInit(int i) {
        if (i == TextToSpeech.SUCCESS) {
            //Setting speech Language
            Locale P = new Locale("ES");
            tts.setLanguage(P);
            tts.setPitch(1);
        }
    }
}
