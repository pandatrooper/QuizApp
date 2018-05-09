package com.example.panda.quizapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class  PlayGame extends AppCompatActivity {

    private Map<String,String> Dictionary;
    ListView l1;
    TextView score,HighSc;
    int sc=0,HighScore=0;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);

        Dictionary=new HashMap<>();
        readFile();
        HighSc=(TextView) findViewById(R.id.HighSc);
        score=(TextView) findViewById(R.id.score);
        l1=(ListView) findViewById(R.id.word_list);
        prefs = getSharedPreferences("myprefs", Context.MODE_PRIVATE);
        HighScore=prefs.getInt("HighScore",0);
        HighSc.setText("Highscore :" + HighScore + " points");
        selectWords();
        l1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                String defnClicked = l1.getItemAtPosition(i).toString();
                TextView t1=(TextView) findViewById(R.id.the_word);
                String theWord = t1.getText().toString();
                String correctDefn = Dictionary.get(theWord);
                if (defnClicked.equals(correctDefn)) {

                    Toast.makeText(PlayGame.this,"Correct Answer",Toast.LENGTH_SHORT).show();
                    sc++;
                    score.setText("Score : " + sc + " points ");

                    if (sc > HighScore) {
                        HighSc.setText("Highscore :" + sc + " points");
                        HighScore = sc;

                        SharedPreferences prefs = getSharedPreferences(
                                "myprefs", MODE_PRIVATE);
                        SharedPreferences.Editor prefsEditor = prefs.edit();
                        prefsEditor.putInt("HighScore", HighScore);
                        prefsEditor.apply();

                    }

                } else {
                    Toast.makeText(PlayGame.this,"Wrong Answer",Toast.LENGTH_SHORT).show();
                    sc--;
                    score.setText("Score : " + sc + " points ");
                }
                selectWords();
            }
        });
    }




    private void readFile() {

        Scanner scan = new Scanner(
                getResources().openRawResource(R.raw.grewords));
        readFileHelper(scan);

        // read from added_words.txt (try/catch in case file is not found)
        try {
            Scanner scan2 = new Scanner(openFileInput("added_words.txt"));
            readFileHelper(scan2);
        } catch (Exception e) {
            // do nothing
        }
    }

    private void readFileHelper(Scanner scan) {

        while (scan.hasNextLine()) {

            String line = scan.nextLine();
            String[] parts = line.split("\t");
            if (parts.length < 2) continue;
            Dictionary.put(parts[0], parts[1]);
        }
    }

    private void selectWords() {

        List<String> words = new ArrayList<>(Dictionary.keySet());
        Random randy = new Random();
        int randomIndex = randy.nextInt(words.size());
        String theWord = words.get(randomIndex);
        String theDefn = Dictionary.get(theWord);

        List<String> defns = new ArrayList<>(Dictionary.values());
        defns.remove(theDefn);
        Collections.shuffle(defns);
        defns = defns.subList(0, 4);
        defns.add(theDefn);
        Collections.shuffle(defns);

        // display everything on screen
        TextView t2=(TextView) findViewById(R.id.the_word);
        t2.setText(theWord);
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1, defns);
        l1.setAdapter(adapter);
    }
}
