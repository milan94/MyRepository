package com.example.milan.deaftelephone;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Random;

public class Ac5 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences storage = getSharedPreferences("storage", MODE_PRIVATE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ac5);

        SharedPreferences.Editor edit=storage.edit();
        edit.putString("word","testing");
        edit.commit();
        String word = storage.getString("word", "default");

        word=swapNeighborLetters(word);
        word=removeLetter(word);
        word=addLetter(word);
        TextView text= (TextView) findViewById(R.id.textView);
        text.setText(word);
     }
    public void onClick(View v) {
        SharedPreferences storage = getSharedPreferences("storage", MODE_PRIVATE);
        EditText input = (EditText) findViewById(R.id.editText);
        SharedPreferences.Editor edit = storage.edit();
        if (input.getText().toString() != "")
        {
        edit.putString("word", input.getText().toString());
        edit.commit();
       Intent intent = new Intent(this, Ac6.class);
        startActivity(intent);
        finish();
        }
        else {
            Toast toast = Toast.makeText(getApplicationContext(), "you have to put a word", Toast.LENGTH_SHORT);
            toast.show();
        }

    }
    protected String swapNeighborLetters(String word)
    {
        Random r= new Random();
        char array[]=word.toCharArray();
        if(word.length()<2) {
            int position = r.nextInt(word.length() - 2);
            char temp;

            temp = array[position];
            array[position] = array[position + 1];
            array[position + 1] = temp;
        }
        return new String (array);
    }

    protected String removeLetter(String word){
        Random r= new Random();
        int position = r.nextInt(word.length() - 1);
        StringBuilder strBuilder = new StringBuilder(word);
        return(strBuilder.deleteCharAt(position).toString());
    }

    protected String addLetter(String word)
    {
        Random r= new Random();
        int position = r.nextInt(word.length() - 1);
        char c = (char) (r.nextInt(26) + 'a');
        StringBuilder strBuilder = new StringBuilder(word);
        return(strBuilder.insert(position, c).toString());
    }
    public void onClear(View v)
    {
        SharedPreferences storage = getSharedPreferences("storage", MODE_PRIVATE);
        SharedPreferences.Editor edit=storage.edit();
        edit.remove("word");
        edit.commit();
    }
}
