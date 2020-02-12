package com.houssam.trollat;

import android.content.Context;
import android.content.Intent;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.time.LocalDate;
import java.util.Locale;

public class txttspi implements TextToSpeech.OnInitListener{
    TextToSpeech toSpeech;
    Context context;

    txttspi(Context context){
        context=context;
        toSpeech =new TextToSpeech(context,this);
    }

    public  void stratspeak(){
        Intent intent=new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"hello");
        ((AppCompatActivity)context).startActivityForResult(intent,1000);
        // result

    }


    public  void getspich(){

    }
    @Override
    public void onInit(int status) {

    }

}
