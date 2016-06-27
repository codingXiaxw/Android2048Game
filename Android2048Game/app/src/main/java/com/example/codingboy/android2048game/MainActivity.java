package com.example.codingboy.android2048game;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView tvScore;
    private static MainActivity mainActivity=null;
    private int score=0;

    public MainActivity()
    {
        mainActivity=this;
    }
    public static MainActivity getMainActivity()
    {
        return mainActivity;
    }
    public void clearScore()
    {
        score=0;
        showScore();
    }
    public void addScore(int s)
    {
        score+=s;
        showScore();
    }
    public void showScore()
    {
        tvScore.setText(score+"");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvScore= (TextView) findViewById(R.id.tvScore);


    }


}
