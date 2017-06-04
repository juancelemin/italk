package com.appmyproyect.jcelemin.com.italk;

import android.content.Intent;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.speech.tts.Voice;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.Locale;

public class ConversacionActivity extends AppCompatActivity implements View.OnClickListener, TextToSpeech.OnInitListener {

    ImageButton tqm,eAcuerdo,noLoSe,horas,idea,noMegusta,atras,home;
    TextToSpeech tts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conversacion);

        tqm = (ImageButton)findViewById(R.id.btnLove);
        eAcuerdo =  (ImageButton)findViewById(R.id.btnAgree);
        noLoSe = (ImageButton)findViewById(R.id.btnUnknow);
        horas = (ImageButton)findViewById(R.id.btnTime);
        idea = (ImageButton)findViewById(R.id.btnIdea);
        noMegusta =(ImageButton)findViewById(R.id.btnDontAgree);
        atras = (ImageButton)findViewById(R.id.btnBack);
        home = (ImageButton)findViewById(R.id.btnHome);

        tqm.setOnClickListener(this);
        eAcuerdo.setOnClickListener(this);
        noLoSe.setOnClickListener(this);
        horas.setOnClickListener(this);
        idea.setOnClickListener(this);
        noMegusta.setOnClickListener(this);
        atras.setOnClickListener(this);
        home.setOnClickListener(this);

        tts = new TextToSpeech(this,this);

    }

    @Override
    public void onClick(View v) {
        String p;
        switch (v.getId()){
            case R.id.btnLove:
                p = "te quiero";
                hablar(p);
                break;
            case R.id.btnAgree:
                p = "estoy de acuerdo";
                hablar(p);
                break;
            case R.id.btnUnknow:
                p = "no lo se";
                hablar(p);
                break;
            case R.id.btnTime:
                p = "que bonito es saludar y ser saludado";
                hablar(p);
                break;
            case R.id.btnIdea:
                p = "tengo una idea";
                hablar(p);
                break;
            case R.id.btnDontAgree:
                p = "No estoy de acuerdo";
                hablar(p);
                break;
            case R.id.btnBack:
                p = "mas";
                hablar(p);
                Intent inten = new Intent(ConversacionActivity.this,EditarConversacionActivity.class);
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
