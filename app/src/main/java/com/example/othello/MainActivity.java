package com.example.othello;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private float menuViewY = 0;
    private CardView menuView;
    private CardView startBackground;
    private Layout boardLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        menuView = findViewById(R.id.menuView);
        startBackground = findViewById(R.id.startBackground);
        CardView onePlayer = findViewById(R.id.onePlayer);
        CardView twoPlayer = findViewById(R.id.twoPlayer);
        CardView settings = findViewById(R.id.settings);

        menuViewY = menuView.getY();

        menuView.animate().translationYBy(-1580).setDuration(400);

        onePlayer.setOnClickListener(this);
        twoPlayer.setOnClickListener(this);
        settings.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.onePlayer:
            case R.id.twoPlayer:
            case R.id.settings:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new BoardLayout()).commit();
                menuView.animate().translationY(menuViewY).setDuration(400).withStartAction(() -> {
                    startBackground.animate().alpha(1.0f).setDuration(400);
                });
                break;
        }
    }
}