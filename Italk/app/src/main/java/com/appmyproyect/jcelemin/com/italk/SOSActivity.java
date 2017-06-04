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

public class SOSActivity extends AppCompatActivity implements View.OnClickListener, TextToSpeech.OnInitListener {

    ImageButton sonido, msm, cancelar;
    TextToSpeech tts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sos);

        sonido = (ImageButton) findViewById(R.id.btnSounds);
        msm = (ImageButton)findViewById(R.id.btnMsm);
        cancelar =(ImageButton)findViewById(R.id.btnBack);

        sonido.setOnClickListener(this);
        msm.setOnClickListener(this);
        cancelar.setOnClickListener(this);

        tts = new TextToSpeech(this,this);
    }

    @Override
    public void onClick(View v) {
        String p;
        switch (v.getId()){
            case R.id.btnSounds:
                p = "ayuda vecinos";
                hablar(p);
                break;
            case R.id.btnMsm:
                break;
            case R.id.btnBack:
                p="regresar";
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
