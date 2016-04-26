package com.example.milan.deaftelephone;


import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Random;

public class Ac3 extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ac3);

        SharedPreferences storage = getSharedPreferences("storage", MODE_PRIVATE);
        String s = storage.getString("word", "");

        EditText received = (EditText) findViewById(R.id.editText);
        received.setText(addLetter(removeLetter(swapNeighbourLetters(s))));

    }


    public void onNextClick(View v){


        Button next = (Button) findViewById(R.id.button);
        EditText send = (EditText) findViewById(R.id.editText2);

        if(!send.getText().equals("")){

            SharedPreferences storage = getSharedPreferences("storage", MODE_PRIVATE);
            SharedPreferences.Editor edit =  storage.edit();
            edit.putString("word", send.getText().toString());
            edit.commit();
            Intent intent = new Intent(this, Ac4.class);
            startActivity(intent);
        }

        else{
            Toast.makeText(Ac3.this, "You must enter a word", Toast.LENGTH_SHORT).show();

        }

    }

    public String swapNeighbourLetters(String word){

        char array[] = word.toCharArray();
        Random random = new Random();

        int position1 = random.nextInt(word.length()-2);
        int position2 = position1 + 1;

        char temp = array[position1] ;
        array[position1] = array[position2];
        array[position2] = temp;
        String string = new String(array);
        return string;
    }

    public String removeLetter(String word){

        Random random = new Random();
        int position = random.nextInt(word.length());
        StringBuilder builder = new StringBuilder(word);

        return builder.deleteCharAt(position).toString();
    }


    public  String addLetter(String word){

        Random random = new Random();
        int position = random.nextInt(word.length());
        StringBuilder builder = new StringBuilder(word);

        return builder.insert(position, "g").toString();
    }


    public void onClearClick(View v){

        Button clear = (Button) findViewById(R.id.button2);
        EditText send = (EditText) findViewById(R.id.editText2);

        if(!send.getText().equals("")){

            SharedPreferences storage = getSharedPreferences("storage",MODE_PRIVATE);
            SharedPreferences.Editor edit =  storage.edit();
            edit.putString("word", send.getText().toString());
            edit.commit();
            send.setText("");
            Intent intent = new Intent(this, Ac4.class);
            startActivity(intent);
        }

        else{
            Toast.makeText(Ac3.this, "You must enter a word", Toast.LENGTH_SHORT).show();
        }
    }






}
