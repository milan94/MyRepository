package com.example.milan.mycalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button one, two, three, four, five, six, seven, eight, nine, zero, add, sub, equal, mul, div, cancel;
    EditText display;
    double op1, op2,temp;
    String text;
    boolean isAdd, isMul, isDiv, isSub,op2Exist;
    double result;
    int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        one = (Button) findViewById(R.id.one);
        two = (Button) findViewById(R.id.two);
        three = (Button) findViewById(R.id.three);
        four = (Button) findViewById(R.id.four);
        five = (Button) findViewById(R.id.five);
        six = (Button) findViewById(R.id.six);
        seven = (Button) findViewById(R.id.seven);
        eight = (Button) findViewById(R.id.eight);
        nine = (Button) findViewById(R.id.nine);
        zero = (Button) findViewById(R.id.zero);
        mul = (Button) findViewById(R.id.mul);
        div = (Button) findViewById(R.id.div);
        add = (Button) findViewById(R.id.add);
        sub = (Button) findViewById(R.id.sub);
        one = (Button) findViewById(R.id.one);
        cancel = (Button) findViewById(R.id.cancel);
        equal = (Button) findViewById(R.id.equal);

        display = (EditText) findViewById(R.id.display);

    }

    public void onClickButton(View v) {

        Button digit = (Button) v;
        if(display.getText().toString().contains("esult")) {
        display.setText("");
        }
        text=display.getText()+digit.getText().toString();
        display.setText(text);

    }

    public void onClickOperation(View v) {

        if(display.getText().toString().contains("esult")) {
            display.setText(String.valueOf(result));
        }
        if(display.getText().toString().equals("")) {
            display.setText("0");
        }
        Button operation = (Button) v;
        temp = Double.parseDouble(display.getText().toString());
        if (operation.getText().toString().equals("+")) {
            isAdd = true;
            isSub=isDiv=isMul=false;
            display.setText("");
            op1+=temp;
        } else if (operation.getText().toString().equals("-")) {
            isSub = true;
            isAdd =isDiv=isMul=false;
            display.setText("");
            if (counter!=0)
            op1-=temp;
            else op1=temp;
        } else if (operation.getText().toString().equals("*")) {
            isMul = true;
            isSub=isDiv=isAdd=false;
            display.setText("");
            if (counter!=0)
                op1*=temp;
            else op1=temp;
        }
        else if(operation.getText().toString().equals("/"))
        {   isDiv=true;
            isSub=isDiv=isAdd=false;
            display.setText("");
            if (counter!=0)
                op1/=temp;
            else op1=temp;
        }
        else if(operation.getText().toString().equals("C")) {
            op1 = op2 = 0;
            display.setText("");
            temp=0;
        }
        counter++;

    }
    public void onClickEquals(View v)
    {
        Button equals=(Button) v;
        op2=Double.parseDouble(display.getText().toString());

        if(isAdd) {
            result=op1+op2;
            display.setText("Result: "+result);
            isAdd=false;
        }
        if(isSub){
            result=op1-op2;
            display.setText("Result: "+(result));
            isSub=false;
        }
        if(isDiv){
            try{
            result=op1/op2;
            display.setText("Result: " + (result));
            isDiv=false;}
            catch(NullPointerException e)
            {
                display.setText("Error");
            }
        }
        if(isMul){
            result=op1*op2;
            display.setText("Result: "+(result));
            isMul=false;
        }

    }



    }



