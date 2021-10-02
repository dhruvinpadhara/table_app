package com.example.table_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    public static final String EXTRA_NAME="package com.example.table_app.extra.name";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void openActivity(View view){

        Intent intent = new Intent(this,MainActivity2.class);
        editText = findViewById(R.id.Input_number);
        String number = editText.getText().toString();
        int num1 = Integer.parseInt(number);
        int checkNum = num1>10000 ? 1 : 0;
        if(checkNum==1){
            Toast.makeText(this, "pl. enter the number less than 10000", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Your Table is Ready in few seconds", Toast.LENGTH_SHORT).show();
            intent.putExtra(EXTRA_NAME,number);
            startActivity(intent);
        }

    }
}