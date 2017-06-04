package com.appmyproyect.jcelemin.com.italk;

import android.content.Intent;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.Locale;

public class NecesidadesActivity extends AppCompatActivity implements View.OnClickListener, TextToSpeech.OnInitListener {

    ImageButton hambre,sed,calor,frio,bano,addnecesidad,atras,home;
    TextToSpeech tts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_necesidades);

        hambre = (ImageButton)findViewById(R.id.btnHungry);
        sed = (ImageButton)findViewById(R.id.btnThirsty);
        calor = (ImageButton)findViewById(R.id.btnHot);
        frio = (ImageButton)findViewById(R.id.btnCold);
        bano = (ImageButton)findViewById(R.id.btnBath);
        addnecesidad = (ImageButton)findViewById(R.id.btnAddNeeds);
        atras = (ImageButton)findViewById(R.id.btnBack);
        home = (ImageButton)findViewById(R.id.btnHome);

        hambre.setOnClickListener(this);
        sed.setOnClickListener(this);
        calor.setOnClickListener(this);
        frio.setOnClickListener(this);
        bano.setOnClickListener(this);
        addnecesidad.setOnClickListener(this);
        atras.setOnClickListener(this);
        home.setOnClickListener(this);

        tts = new TextToSpeech(this,this);

    }

    @Override
    public void onClick(View v) {
        String p;
        switch (v.getId()){
            case R.id.btnHungry:
                p = "Tengo Hambre";
                hablar(p);
                break;
            case R.id.btnThirsty:
                p = "Tengo Sed";
                hablar(p);
                break;
            case R.id.btnCold:
                p = "tengo frio";
                hablar(p);
                break;
            case R.id.btnHot:
                p = "Tengo Calor";
                hablar(p);
                break;
            case R.id.btnBath:
                p = "necesito ir al baño";
                hablar(p);
                break;
            case R.id.btnAddNeeds:
                p = "quiero salir";
                hablar(p);
                break;
            case R.id.btnBack:
                p = "mas";
                hablar(p);
                Intent inten = new Intent(NecesidadesActivity.this,EditarNesecidadActivity.class);
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
