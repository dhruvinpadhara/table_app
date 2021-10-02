package com.example.table_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class MainActivity2 extends AppCompatActivity{

    private TextView textView;
    private Button btnSpeack, btnStop;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private TextToSpeech textToSpeech;
    String languageGu="", languageEng="";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);


        //getId from layout
        textView = findViewById(R.id.textView);
        btnSpeack = findViewById(R.id.btnSpeack);
        btnStop = findViewById(R.id.btnStop);
        radioGroup = findViewById(R.id.speckGroup);

        radioGroup.clearCheck();
        //get the name and number form previous activity
        Intent intent = getIntent();
        String name = intent.getStringExtra(MainActivity.EXTRA_NAME);

        int number = Integer.parseInt(name); // conversion int
        String table = "";
        for(int i=1; i<=10; i++){
            long ans = number * i;
            //add string from view to table in textView
            table += number + " X " + i + " = "+ ans + "\n\n";
            //add String for Speak
        }

        System.out.println("your Number is : "+name);

        String tableIs = "\nTable of " + number + " is : "+"\n\n"+table;
        textView.setText(tableIs);

        //add listener to radio button
        radioGroup.setOnCheckedChangeListener(
                new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        radioButton = (RadioButton)group.findViewById(checkedId);
                    }
                }
        );



        btnSpeack.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                int selectedId = radioGroup.getCheckedRadioButtonId();

                if(selectedId == -1){
                    Toast.makeText(MainActivity2.this , "pl. select the language",Toast.LENGTH_LONG).show();
                }else{
                    String language = radioButton.getText().toString();
                    //set the language
                    btnSpeack.setEnabled(false);
                    Toast.makeText(MainActivity2.this,radioButton.getText(),Toast.LENGTH_LONG).show();
                    speackLanguage(language,number);
                }
                //Speck the table

            }
        });

        //stop the speack
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textToSpeech.stop();
                btnSpeack.setEnabled(true);
            }
        });


    }

    private TextToSpeech speackLanguage(String language, int number) {
        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status!=TextToSpeech.ERROR){
                    if(language.equals("Gujarati")){
                        String gujarati = setGujaratiLanguage(number);
                        textToSpeech.setLanguage(new Locale("gu"));
                        textToSpeech.speak(gujarati,TextToSpeech.QUEUE_FLUSH,null);
                    }else{
                        String english = setEnglishLanguage(number);
                        textToSpeech.setLanguage(Locale.US);
                        textToSpeech.speak(english,TextToSpeech.QUEUE_FLUSH,null);
                    }
                    System.out.println("Language Gujarati " + languageGu);
                    System.out.println("Language English " + languageEng);
                    textToSpeech.setOnUtteranceProgressListener(new UtteranceProgressListener() {
                        @Override
                        public void onStart(String utteranceId) {

                        }

                        @Override
                        public void onDone(String utteranceId) {
                            System.out.println("finished speaking");
                            Log.i("textToSpeech","finished");
                        }

                        @Override
                        public void onError(String utteranceId) {

                        }
                    });
                }
            }
        });
        return textToSpeech;
    }

    private String setGujaratiLanguage(int number) {
        for (int i=1; i<=10; i++){
            long ans = number * i;
            if(i == 1){
                languageGu += number + " aeka "+ans+"\n";
            }else if(i == 2){
                languageGu += number + " doo "+ans+"\n";
            }else if(i == 3){
                languageGu += number + " tari "+ans+"\n";
            }else if(i == 4){
                languageGu += number + " chok "+ans+"\n";
            }else if(i == 5){
                languageGu += number + " pancha "+ans+"\n";
            }else if(i == 6){
                languageGu += number + " chhang "+ans+"\n";
            }else if(i == 7){
                languageGu += number + " satta "+ans+"\n";
            }else if(i == 8){
                languageGu += number + " aththaa "+ans+"\n";
            }else if(i == 9){
                languageGu += number + " navva "+ans+"\n";
            }else if(i == 10){
                languageGu += number + " dan "+ans+"\n";
            }
        }
        return languageGu;
    }

    private String setEnglishLanguage(int number) {
        for (int i=1; i<=10; i++){
            long ans = number * i ;
            languageEng += number +" "+ i+" za "+ans+"\n";

        }
        System.out.println(languageEng);
        return  languageEng;
    }
}