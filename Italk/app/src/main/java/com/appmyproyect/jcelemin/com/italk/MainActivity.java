package com.appmyproyect.jcelemin.com.italk;

import android.content.Intent;
import android.os.Build;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;


public class MainActivity extends AppCompatActivity implements View.OnClickListener, TextToSpeech.OnInitListener {

    ImageButton saludo,conversacion,necesidad,SOS,personalizacion,hora,fecha,home;
    int today,month,ano;
    TextToSpeech tts;
    int hour,min,sec;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        saludo = (ImageButton)findViewById(R.id.btnGreet);
        conversacion = (ImageButton)findViewById(R.id.btnConversation);
        necesidad = (ImageButton)findViewById(R.id.btnNeeds);
        SOS =(ImageButton) findViewById(R.id.btnSOS);
        personalizacion =(ImageButton) findViewById(R.id.btnPersonalitation);
        hora =(ImageButton)findViewById(R.id.btnHora);
        fecha = (ImageButton)findViewById(R.id.btnDate);
        home =(ImageButton) findViewById(R.id.btnHome);

        saludo.setOnClickListener(this);
        conversacion.setOnClickListener(this);
        necesidad.setOnClickListener(this);
        SOS.setOnClickListener(this);
        personalizacion.setOnClickListener(this);
        hora.setOnClickListener(this);
        fecha.setOnClickListener(this);
        home.setOnClickListener(this);

        tts = new TextToSpeech(this,this);
    }

    @Override
    public void onClick(View v) {
        String p;
        switch (v.getId()){
            case R.id.btnGreet:
                p = "saludos";
                hablar(p);
                Intent intent = new Intent(MainActivity.this,SaludoActivity.class);
                startActivity(intent);
                break;
            case R.id.btnConversation:
                p= "conversacion";
                hablar(p);
                Intent intentt = new Intent(MainActivity.this,ConversacionActivity.class);
                startActivity(intentt);
                break;
            case R.id.btnNeeds:
                p = "necesidades";
                hablar(p);
                Intent intens = new Intent(MainActivity.this,NecesidadesActivity.class);
                startActivity(intens);
                break;
            case R.id.btnSOS:
                p="llamada de emergencia";
                hablar(p);
                Intent intenp = new Intent(MainActivity.this,SOSActivity.class);
                startActivity(intenp);
                break;
            case R.id.btnPersonalitation:
                p= "personalizacion";
                hablar(p);
                Intent inten = new Intent(MainActivity.this,HablarActivity.class);
                startActivity(inten);
                break;
            case R.id.btnHora:
                p = hora();
                //hora.setText(hour+" : "+min);
                hablar(p);
                break;
            case R.id.btnDate:
                p = fecha();
                // fecha.setText(today+"/ "+month+"/ "+ano +"");
                hablar(p);
                break;
            case R.id.btnHome:
                p="salir de. ahi tolk";
                hablar(p);
                finish();
                break;
        }

    }


    public String hora (){
        Calendar calendarNow = new GregorianCalendar();
        hour = calendarNow.get(Calendar.HOUR);
        min = calendarNow.get(Calendar.MINUTE);
        sec = calendarNow.get(Calendar.SECOND);
        if(hour ==0)
            hour= 12;
        return "son las "+hour+" horas y "+min+"minutos" +" con "+sec+" segundos";
    }
    public String fecha(){
        Calendar calendarNow = new GregorianCalendar();
        today = calendarNow.get(Calendar.DAY_OF_MONTH);
        month = calendarNow.get(Calendar.MONTH)+1;
        ano   = calendarNow.get(Calendar.YEAR);
        return "hoy es el"+ today+"de  "+mes(month) + " del" +ano;
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
    public String  mes (int diames){

        if(diames == 1){
            return "enero";
        }
        else if(diames == 2){
            return "febrero";
        }
        else if(diames == 3){
            return "marzo";
        }
        else if(diames == 4){
            return "abril";
        }
        else if(diames == 5){
            return "mayo";
        }
        else if(diames == 6){
            return "junio";
        }
        else if(diames == 7){
            return "julio";
        }
        else if(diames == 8){
            return "agosto";
        }
        else if(diames == 9){
            return "septiembre";
        }
        else if(diames == 10){
            return "octubre";
        }
        else if(diames == 11){
            return "noviembre";
        }
        else if(diames == 12){
            return "diciembre";
        }
        return "";
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

    @Override protected void onStart() {
        super.onStart();
        //Toast.makeText(this, "onStart", Toast.LENGTH_SHORT).show();
    }

    @Override protected void onResume() {
        super.onResume();
        //Toast.makeText(this, "onResume", Toast.LENGTH_SHORT).show();
    }

    @Override protected void onPause() {
        //Toast.makeText(this, "onPause", Toast.LENGTH_SHORT).show();
        super.onPause();
        //onPause();
    }

    @Override protected void onStop() {
        super.onStop();
        onPause();
        //Toast.makeText(this, "onStop", Toast.LENGTH_SHORT).show();
    }

    @Override protected void onRestart() {
        super.onRestart();
        //Toast.makeText(this, "onRestart", Toast.LENGTH_SHORT).show();
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        //Toast.makeText(this, "onDestroy", Toast.LENGTH_SHORT).show();
    }
}

