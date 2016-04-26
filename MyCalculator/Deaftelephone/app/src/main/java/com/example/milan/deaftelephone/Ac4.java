package com.example.milan.deaftelephone;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


import java.util.Random;

public class Ac4 extends AppCompatActivity {

    SharedPreferences storage;
    SharedPreferences.Editor editor;
    String text = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ac4);
        storage = getSharedPreferences("storage", MODE_PRIVATE);
        editor = storage.edit();
        text = storage.getString("word", "");
        TextView display = (TextView)findViewById(R.id.inputTextView);

        text = swapNeighbourLetters(text);
        text = addLetter(text);
        text = removeLetter(text);
        display.setText(text);

    }

    public String swapNeighbourLetters(String str){
        Random random = new Random();
        if(str.length()<2){
            return str;
        }
        int randomInt = random.nextInt(str.length() - 1);
        char randomChar = str.charAt(randomInt);
        char secondChar = str.charAt(randomInt + 1);
        str = str.substring(0,randomInt) + secondChar + randomChar + str.substring(randomInt+2);
        return str;
    }
    public String removeLetter(String str){
        Random random = new Random();
        int randomInt = random.nextInt(str.length());
        str = str.substring(0,randomInt) + str.substring(randomInt + 1);
        return str;
    }
    public String addLetter(String str){
        Random random = new Random();
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
        int randomInt = random.nextInt(alphabet.length());
        char randomChar = alphabet.charAt(randomInt);
        randomInt = random.nextInt(str.length());
        str = str.substring(0,randomInt) + randomChar + str.substring(randomInt);
        return str;
    }

    public void changeString(View v){
        EditText edit = (EditText)findViewById(R.id.editText);
        text = edit.getText().toString();

        editor.putString("word", text);
        editor.commit();

        Intent setIntent = new Intent(Ac4.this, Ac5.class);
        Ac4.this.finish();
        startActivity(setIntent);
    }

    public void clearString(View v){

        editor.remove("word");
        editor.apply();

        Intent setIntent = new Intent(Ac4.this, Ac5.class);
        Ac4.this.finish();
        startActivity(setIntent);
    }

}
