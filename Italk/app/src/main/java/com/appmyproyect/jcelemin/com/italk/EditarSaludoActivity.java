package com.appmyproyect.jcelemin.com.italk;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.util.Locale;

public class EditarSaludoActivity extends AppCompatActivity implements View.OnClickListener, TextToSpeech.OnInitListener {

    ImageButton con1, con2, con3, con4, con5, con6, con7,salir;
    String fraseUno,fraseDos,fraseTres,fraseCuatro,fraseCinco,fraseSeis,fraseSiete,fraseNueva;
    TextToSpeech tts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_saludo);


        fraseUno = "vacio";
        fraseDos = "vacio";
        fraseTres = "vacio";
        fraseCuatro = "vacio";
        fraseCinco = "vacio";
        fraseSeis = "vacio";
        fraseSiete = "vacio";



        Intent i = getIntent();
        Bundle extra = i.getExtras();
        if( extra != null){

            fraseNueva = extra.getString("FRASE");
            /// asignar();
            guardarFraseNueva();
        }else{
            cargar();
        }


        con1 = (ImageButton)findViewById(R.id.btnsalu1);
        con2 = (ImageButton)findViewById(R.id.btnsalu2);
        con3 = (ImageButton)findViewById(R.id.btnsalu3);
        con4 = (ImageButton)findViewById(R.id.btnsalu4);
        con5 = (ImageButton)findViewById(R.id.btnsalu5);
        con6 = (ImageButton)findViewById(R.id.btnsalu6);
        con7 = (ImageButton)findViewById(R.id.btnsalu7);
        salir = (ImageButton)findViewById(R.id.btnEsaluBack);


        con1.setOnClickListener(this);
        con2.setOnClickListener(this);
        con3.setOnClickListener(this);
        con4.setOnClickListener(this);
        con5.setOnClickListener(this);
        con6.setOnClickListener(this);
        con7.setOnClickListener(this);
        salir.setOnClickListener(this);

        tts = new TextToSpeech(this,this);
    }

    public String fraseActual() {

        if (fraseUno == "vacio") {
            return "fraseuno";
        } else if (fraseDos == "vacio") {
            return  "frasedos";
        } else if (fraseTres == "vacio") {
            return "frasetres";
        } else if (fraseCuatro == "vacio") {
            return "frasecuatro";
        } else if (fraseCinco == "vacio") {
            return "frasecinco";
        } else if (fraseSeis == "vacio") {
            return "fraseseis";
        } else if (fraseSiete == "vacio") {
            return "frasesiete";
        }

        return "lleno";

    }

    public void guardarFraseNueva (){
        SharedPreferences settings = getSharedPreferences("saludo", 0);
        SharedPreferences.Editor editor = settings.edit();

        //Toast.makeText(getBaseContext(), fraseNueva, Toast.LENGTH_SHORT).show();
        cargar();
        String fraseA = fraseActual();
        // Toast.makeText(getBaseContext(), fraseA, Toast.LENGTH_SHORT).show();
        if(fraseA != "lleno"){
            editor.putString(fraseA, fraseNueva);
            editor.commit();
            cargar();
        }

    }
    public void cargar (){
        SharedPreferences settings = getSharedPreferences("saludo", 0);
        SharedPreferences.Editor editor = settings.edit();

        fraseUno = settings.getString("fraseuno","vacio");
        fraseDos = settings.getString("frasedos","vacio");
        fraseTres = settings.getString("frasetres","vacio");
        fraseCuatro = settings.getString("frasecuatro","vacio");
        fraseCinco = settings.getString("frasecinco","vacio");
        fraseSeis = settings.getString("fraseseis","vacio");
        fraseSiete = settings.getString("frasesiete","vacio");

    }
    public void borrar (String frase){
        SharedPreferences settings = getSharedPreferences("conversacion", 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(frase, "vacio");
        editor.commit();
        cargar();
        hablar("La frase ha sido elminada");
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnsalu1:
                if(fraseNueva != null && fraseNueva.equals("delete")){
                    borrar("fraseuno");
                }
                else{
                    hablar(fraseUno);
                }
                break;
            case R.id.btnsalu2:
                if(fraseNueva != null && fraseNueva.equals("delete")){
                    borrar("frasedos");
                }
                else{
                    hablar(fraseDos);
                }
                break;
            case R.id.btnsalu3:
                if(fraseNueva != null && fraseNueva.equals("delete")){
                    borrar("frasetres");
                }
                else{
                    hablar(fraseTres);
                }
                break;
            case R.id.btnsalu4:
                if(fraseNueva != null && fraseNueva.equals("delete")){
                    borrar("frasecuatro");
                }
                else{
                    hablar(fraseCuatro);
                }
                break;
            case R.id.btnsalu5:
                if(fraseNueva != null && fraseNueva.equals("delete")){
                    borrar("frasecinco");
                }
                else{
                    hablar(fraseCinco);
                }
                break;
            case R.id.btnsalu6:
                if(fraseNueva != null && fraseNueva.equals("delete")){
                    borrar("fraseseis");
                }
                else{
                    hablar(fraseSeis);
                }
                break;
            case R.id.btnsalu7:
                if(fraseNueva != null && fraseNueva.equals("delete")){
                    borrar("frasesiete");
                }
                else{
                    hablar(fraseSiete);
                }
                break;
            case R.id.btnEsaluBack:
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
