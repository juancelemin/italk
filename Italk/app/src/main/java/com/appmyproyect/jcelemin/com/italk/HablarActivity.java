package com.appmyproyect.jcelemin.com.italk;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Build;
import android.sax.StartElementListener;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class HablarActivity extends AppCompatActivity implements View.OnClickListener, TextToSpeech.OnInitListener {

    ImageButton escuchar, hablar,salir,guardar,borrar;
    public EditText speech_output;
    TextToSpeech tts;
    private ImageButton btn_output;
    private final int SPEECH_REQUEST_CODE = 123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hablar);

        speech_output = (EditText)findViewById(R.id.txtTexto);

        escuchar =(ImageButton)findViewById(R.id.btnEscuchar);
        hablar =(ImageButton)findViewById(R.id.btnHablar);
        salir =(ImageButton)findViewById(R.id.btnSalir);
        guardar =(ImageButton)findViewById(R.id.btnGuardar);
        borrar = (ImageButton)findViewById(R.id.btnBorrar);

        escuchar.setOnClickListener(this);
        hablar.setOnClickListener(this);
        salir.setOnClickListener(this);
        guardar.setOnClickListener(this);
        borrar.setOnClickListener(this);

        tts = new TextToSpeech(this,this);


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
    @Override
    public void onClick(View v) {
        String p = speech_output.getText().toString();
        switch (v.getId()){
            case R.id.btnEscuchar:
                hablar(p);
                break;
            case R.id.btnGuardar:
                Intent intent = new Intent(HablarActivity.this,SeleccionarActivity.class);
                intent.putExtra("FRASE",speech_output.getText().toString());
                startActivity(intent);
                break;
            case R.id.btnHablar:
                showGoogleInputDialog();
                break;
            case R.id.btnBorrar:
                Intent i = new Intent(HablarActivity.this,SeleccionarActivity.class);
                i.putExtra("FRASE","delete");
                startActivity(i);
                break;
            case R.id.btnSalir:
                p = "regresar";
                hablar(p);
                finish();
                break;
        }

    }
    public void showGoogleInputDialog() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        try {
            startActivityForResult(intent, SPEECH_REQUEST_CODE);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(), "Your device is not supported!",
                    Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case SPEECH_REQUEST_CODE: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    speech_output.setText(result.get(0));
                }
                break;
            }

        }
    }

}
