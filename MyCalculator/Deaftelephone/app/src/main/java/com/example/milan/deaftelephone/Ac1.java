package com.example.milan.deaftelephone;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class Ac1 extends AppCompatActivity {

    SharedPreferences storage;
    SharedPreferences.Editor edit;
    TextView display;
    EditText insert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ac1);
        storage = getSharedPreferences("storage", MODE_PRIVATE);
        edit = storage.edit();
        display = (TextView)findViewById(R.id.textView);
        insert = (EditText) findViewById(R.id.insert);
        String input = storage.getString("word", "");
//        String input = "testing";
        if(input.equals("")){
            //NEW GAME
            display.setText("Game over, type a new word:");
        }else{
            input=swapNeighborLetters(input);
//            System.out.println("XXXXXXXXXXXXX: "+ input);
            input=removeLetter(input);
//            System.out.println("XXXXXXXXXXXXX: "+ input);
            input=addLetter(input);
//            System.out.println("XXXXXXXXXXXXX: "+ input);

            display.setText(input);
        }
    }
    public void clearWord(View v) {
        edit.remove("word");
        edit.apply();
        Intent startNext = new Intent(Ac1.this, Ac2.class);                     // ACTIVITY CHANGES
        Ac1.this.finish();
        startActivity(startNext);
    }
    public void nextPlayer(View v) {
        String newWord = insert.getText().toString();
        if (newWord.equals("")) {
            Toast.makeText(Ac1.this, "You have to type something!", Toast.LENGTH_SHORT).show();    // ACTIVITY CHANGES
        } else {
            edit.putString("word", newWord);
            edit.apply();
            Intent startNext = new Intent(Ac1.this, Ac2.class);                 // ACTIVITY CHANGES
            Ac1.this.finish();
            startActivity(startNext);
        }
    }
    // STRING EDITING
    public String swapNeighborLetters(String in){
        Random r = new Random();

        char[] out=in.toCharArray();
        if(in.length()>3){
            int removeAt=r.nextInt(in.length()-2);
            char tmp=out[removeAt];
            out[removeAt]=out[removeAt+1];
            out[removeAt+1]=tmp;
        }
        return new String(out);
    }
    public String removeLetter(String in){
        Random r = new Random();
        int removeAt=r.nextInt(in.length());
        return in.substring(0,removeAt)+in.substring(removeAt+1);
    }
    public String addLetter(String in){
        Random r = new Random();
        char c = (char)(r.nextInt(26) + 'a');
        int removeAt=r.nextInt(in.length());
        return in.substring(0,removeAt+1)+c+in.substring(removeAt+1);
    }
}
