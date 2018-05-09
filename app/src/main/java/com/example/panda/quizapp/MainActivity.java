package com.example.panda.quizapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private static final int REQ_CODE_ADD_WORD = 1234;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener authListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();

        authListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user == null) {
                    // user auth state is changed - user is null
                    // launch login activity
                    startActivity(new Intent(MainActivity.this, LoginActivity.class));
                    finish();
                }
            }
        };
    }

    public void play(View view) {
        Intent i1= new Intent(this,PlayGame.class);
        startActivity(i1);
    }

    public void AddNew(View view) {
        Intent i2= new Intent(this,AddWords.class);
        startActivityForResult(i2,REQ_CODE_ADD_WORD);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQ_CODE_ADD_WORD)
        {
            String newWord = data.getStringExtra("newword");
            String newDefn = data.getStringExtra("newdefn");

            Toast.makeText(MainActivity.this,"You added the word: " + newWord,Toast.LENGTH_SHORT).show();
        }
    }

    public void AddNewBook(View view) {

        Intent i5= new Intent(this,SQL_Main.class);
        startActivity(i5);

    }

    public void LogOut(View view) {
        mAuth.signOut();

    }

    public void BookNearYou(View view) {
        Intent i6=new Intent(this,MapsActivity.class);
        startActivity(i6);
    }
}
