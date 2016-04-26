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

public class Ac2 extends AppCompatActivity {

    String newWord;
    SharedPreferences test;
    SharedPreferences.Editor edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ac2);
        test=getSharedPreferences("storage", MODE_PRIVATE);
        edit=test.edit();
        Ac2 ac2=new Ac2();
        String sharedWord=test.getString("word", "default");
        TextView textView=(TextView)findViewById(R.id.textView);
        newWord=ac2.swapNeighbourLetters(sharedWord);
        newWord=ac2.addLetter(newWord);
        newWord=ac2.removeLetter(newWord);
        textView.setText(newWord);


    }
    public String swapNeighbourLetters(String input){
        int randomIndex=(new Random()).nextInt(input.length()-1);
        char[] c = input.toCharArray();
        char temp = c[randomIndex];
        c[randomIndex] = c[randomIndex+1];
        c[randomIndex+1] = temp;
        String swappedString = new String(c);
        return swappedString;
    }
    public String removeLetter(String input){
        int randomIndex=(new Random()).nextInt(input.length());
        StringBuilder sb=new StringBuilder(input);
        sb.replace(randomIndex,randomIndex+1,"").toString();
        return sb.replace(randomIndex, randomIndex + 1, "").toString();
    }
    public String addLetter(String input){
        int randomIndex=(new Random()).nextInt(input.length());
        StringBuilder sb=new StringBuilder(input);
        return sb.insert(randomIndex, (char) (65 + (new Random()).nextInt(27))).toString();
    }
    public void onClickNext(View v){
        SharedPreferences.Editor edit=test.edit();
        EditText editText=(EditText)findViewById(R.id.editText);
        if(editText.getText().toString()!=""){
        edit.putString("word",editText.getText().toString());
        edit.apply();
        Intent ac2Toac3=new Intent(Ac2.this,Ac3.class);
        startActivity(ac2Toac3);
        finish();}
        else {
            Toast toast = Toast.makeText(getApplicationContext(), "You have to put a word", Toast.LENGTH_SHORT);
            toast.show();
        }
    }
    public void onClickClear(View v){
        edit.remove("word");
        edit.apply();
        Intent ac2Toac3=new Intent(Ac2.this,Ac3.class);
        startActivity(ac2Toac3);
        finish();
    }
}
