package com.example.milan.deaftelephone;


import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Ac6 extends AppCompatActivity {

    Random rand = new Random();

    SharedPreferences storage;
    SharedPreferences.Editor edit;
    TextView display;
    EditText insert;

    //String input = storage.getString("word", "...");
    //TextView tv = (TextView) findViewById(R.id.textView);
    //tv.setText(input);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ac6);
        storage     =   getSharedPreferences("storage", MODE_PRIVATE);
        edit        =   storage.edit();
        display     =   (TextView) findViewById(R.id.textView);
        insert      =   (EditText) findViewById(R.id.insert);
        String input = storage.getString("word", "");
//        String input = "testing";
        if(input.equals("")){
            //NEW GAME
            display.setText("Game over, type a new word:");
        }else{
            display.setText(addLetter(removeLetter(swapNeighbourLetters(input))).toUpperCase());
        }
    }

    public String swapNeighbourLetters(String s){
        if(s.length() == 1) return s;
        if(s.length() == 2) return "" + s.charAt(1) + s.charAt(0);
        int index = rand.nextInt(s.length()-1)+1;
        return "" + s.substring(0, index-1) + s.charAt(index) + s.charAt(index-1) + s.substring(index+1, s.length());
    }

    public String removeLetter(String s){
        if(s.length() == 1) return "";
        int index = rand.nextInt(s.length());
        return "" + s.substring(0, index) + s.substring(index+1, s.length());
    }

    public String addLetter(String s){
        int index = rand.nextInt(s.length());
        return "" + s.substring(0, index) + (char)(rand.nextInt(26) + 65) + s.substring(index, s.length());
    }

    public void onClickNext(View v){
        TextView tv = (TextView) findViewById(R.id.insert);
        if(tv.getText().equals("")){
            Toast.makeText(Ac6.this, "You have to type something...", Toast.LENGTH_SHORT).show();
        }
        else {
            String s = insert.getText().toString();
            edit.putString("word", s);
            edit.apply();
            Intent nextPlayer = new Intent(Ac6.this, Ac1.class);
            startActivity(nextPlayer);
            Ac6.this.finish();
        }
    }

    public void onClickClear(View v){
        edit.remove("word");
        edit.apply();

        Intent nextPlayer = new Intent(Ac6.this, Ac1.class);
        startActivity(nextPlayer);
        Ac6.this.finish();
    }

}