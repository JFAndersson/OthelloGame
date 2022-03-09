package com.example.othello;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentTransaction;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static float menuViewY = 0;
    private boolean firstLoad = false;
    private CardView menuView;
    private CardView pickColor;
    private CardView scoreBoard;
    private ObjectAnimator animateMenuViewDown;
    private ObjectAnimator animateScoreBoardDown;
    private ObjectAnimator animatePickMenuDown;
    private ObjectAnimator animateMenuViewUp;
    private ObjectAnimator animateScoreBoardUp;
    private ObjectAnimator animatePickMenuUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        menuView = findViewById(R.id.menuView);
        pickColor = findViewById(R.id.pickColor);
        scoreBoard = findViewById(R.id.scoreBoard);
        CardView onePlayer = findViewById(R.id.onePlayer);
        CardView twoPlayer = findViewById(R.id.twoPlayer);
        CardView settings = findViewById(R.id.settings);
        CardView pickWhite = findViewById(R.id.pickWhite);
        CardView pickBlack = findViewById(R.id.pickBlack);

        if (!firstLoad){
            menuViewY = menuView.getY();
            firstLoad = true;
        }

        animateMenuViewUp = ObjectAnimator.ofFloat(menuView,"translationY",menuViewY, menuViewY - 970);
        animateScoreBoardUp = ObjectAnimator.ofFloat(scoreBoard,"translationY",menuViewY, menuViewY - 970);
        animatePickMenuUp = ObjectAnimator.ofFloat(pickColor, "translationY", menuViewY, menuViewY - 970);
        animateMenuViewDown = ObjectAnimator.ofFloat(menuView,"translationY",menuViewY - 970, menuViewY);
        animateScoreBoardDown = ObjectAnimator.ofFloat(scoreBoard,"translationY",menuViewY - 970, menuViewY);
        animatePickMenuDown = ObjectAnimator.ofFloat(pickColor, "translationY", menuViewY - 970, menuViewY);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(animateMenuViewUp, animateScoreBoardUp);
        animatorSet.setDuration(300);
        animatorSet.start();

        onePlayer.setOnClickListener(this);
        twoPlayer.setOnClickListener(this);
        settings.setOnClickListener(this);
        pickWhite.setOnClickListener(this);
        pickBlack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case (R.id.onePlayer):
                BoardLayout.onePlayer = true;
            case (R.id.twoPlayer):
                AnimatorSet animatorSet = new AnimatorSet();
                animatorSet.playTogether(animateMenuViewDown, animateScoreBoardDown);
                animatorSet.setDuration(300);
                animatorSet.start();

                AnimatorSet animatorSet2 = new AnimatorSet();
                animatorSet2.play(animatePickMenuUp);
                animatorSet2.setDuration(300).setStartDelay(300);
                animatorSet2.start();
                break;
            case (R.id.settings):
                //TODO: Skapa inställningsmeny
                break;
            case (R.id.pickWhite):
                BoardLayout.colorWhite = true;
            case (R.id.pickBlack):
                AnimatorSet animatorSet3 = new AnimatorSet();
                animatorSet3.play(animatePickMenuDown);
                animatorSet3.setDuration(300).setStartDelay(300);
                animatorSet3.start();

                getSupportFragmentManager()
                        .beginTransaction()
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .replace(R.id.fragment_container, new BoardLayout())
                        .commit();
                break;
            default:
        }
    }

    public static float convertDpToPx(float dp, Activity activity) {
        if (activity != null){
            return dp * activity.getResources().getDisplayMetrics().density;
        }
        else{
            return 0;
        }
    }
}