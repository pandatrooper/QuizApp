package com.example.panda.quizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.PrintStream;

public class AddWords extends AppCompatActivity {

    EditText newWord,newDefn;
    String t1,t2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_words);
    }

    public void AddWord(View view) throws FileNotFoundException {
        newDefn=(EditText) findViewById(R.id.NewDefn);
        newWord=(EditText) findViewById(R.id.NewWord);

        t1=newDefn.getText().toString();
        t2=newWord.getText().toString();

        PrintStream output = new PrintStream(openFileOutput("added_words.txt", MODE_APPEND));
        output.println(t1 + "\t" + t2);
        output.close();

        Intent goBack = new Intent();
        setResult(RESULT_OK, goBack);
        finish();
    }


    }

