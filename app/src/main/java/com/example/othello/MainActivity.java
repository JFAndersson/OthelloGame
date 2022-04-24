package com.example.othello;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentTransaction;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.transition.ChangeBounds;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.google.android.material.switchmaterial.SwitchMaterial;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //region declarations

    public static Instant timeStart;
    public static Instant timeEnd;
    public static long timeElapsed;

    public static String gameWinner;
    public static int gameCount;
    public static float menuViewY;
    public static float pickViewY;

    private boolean firstLoad = false;
    private boolean settingsActive = false;
    public static boolean onePlayer = false;
    public static boolean colorWhite = false;
    public static boolean placementHelp = false;
    public static boolean gameWon = false;
    private boolean playAgain = false;

    public static CardView clearGameData;
    public static CardView endScreen;
    private CardView menuView;
    private CardView scoreBoard;
    private CardView settingsMenu;
    private CardView onePlayerBtn;
    private CardView twoPlayerBtn;
    private CardView settingsBtn;
    private CardView returnToMenuBtn;
    private CardView playAgainBtn;

    public static ConstraintLayout game1;
    public static ConstraintLayout game2;
    public static ConstraintLayout game3;

    public static TextView wonHeader;

    private TextView gameIdHeader1;
    private TextView gameIdHeader2;
    private TextView gameIdHeader3;
    private TextView timeHeader1;
    private TextView timeHeader2;
    private TextView timeHeader3;
    private TextView winnerHeader1;
    private TextView winnerHeader2;
    private TextView winnerHeader3;

    public static TextView scoreBoardHeader;
    private TextView settingsBtnText;
    private TextView activateHelpHeader;
    private TextView pickHeader;

    private SwitchMaterial toggleHelpSwitch;

    private RadioButton difficultyBtn1;
    private RadioButton difficultyBtn2;
    private RadioButton difficultyBtn3;

    public static ObjectAnimator animateMenuViewUp;
    public static ObjectAnimator animateScoreBoardUp;
    public static ObjectAnimator animateEndScreenUp;
    public static ObjectAnimator animateEndScreenDown;
    private ObjectAnimator animateMenuViewDown;
    private ObjectAnimator animateScoreBoardDown;
    private ObjectAnimator animatePickMenuDown;
    private ObjectAnimator animatePickMenuUp;

    public static ObjectAnimator animateSettingsMenuUp;
    public static ObjectAnimator animateSettingsMenuDown;

    public static Drawable oppositeColor;
    public static Drawable sameColor;
    public static Drawable emptyColor;

    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //region initialization

        menuView = findViewById(R.id.menuView);
        endScreen = findViewById(R.id.endScreen);
        pickHeader = findViewById(R.id.pickHeader);
        activateHelpHeader = findViewById(R.id.activateHelpHeader);
        scoreBoardHeader = findViewById(R.id.scoreBoardHeader);
        toggleHelpSwitch = findViewById(R.id.toggleHelpBtn);
        settingsMenu = findViewById(R.id.settingsMenu);
        scoreBoard = findViewById(R.id.scoreBoard);
        onePlayerBtn = findViewById(R.id.onePlayer);
        twoPlayerBtn = findViewById(R.id.twoPlayer);
        settingsBtn = findViewById(R.id.settings);
        settingsBtnText = findViewById(R.id.settingsBtnHeader);
        returnToMenuBtn = findViewById(R.id.returnToMenu);
        playAgainBtn = findViewById(R.id.playAgain);

        game1 = findViewById(R.id.game1);
        game2 = findViewById(R.id.game2);
        game3 = findViewById(R.id.game3);

        wonHeader = findViewById(R.id.wonHeader);
        clearGameData = findViewById(R.id.clearGameData);

        gameIdHeader1 = findViewById(R.id.gameIdHeaderA1);
        gameIdHeader2 = findViewById(R.id.gameIdHeaderA2);
        gameIdHeader3 = findViewById(R.id.gameIdHeaderA3);
        timeHeader1 = findViewById(R.id.timeHeaderA1);
        timeHeader2 = findViewById(R.id.timeHeaderA2);
        timeHeader3 = findViewById(R.id.timeHeaderA3);
        winnerHeader1 = findViewById(R.id.winnerHeaderA1);
        winnerHeader2 = findViewById(R.id.winnerHeaderA2);
        winnerHeader3 = findViewById(R.id.winnerHeaderA3);

        difficultyBtn1 = findViewById(R.id.difficultyBtn1);
        difficultyBtn2 = findViewById(R.id.difficultyBtn2);
        difficultyBtn3 = findViewById(R.id.difficultyBtn3);

        CardView pickColor = findViewById(R.id.pickColor);
        CardView pickWhite = findViewById(R.id.pickWhite);
        CardView pickBlack = findViewById(R.id.pickBlack);

        animateMenuViewUp = ObjectAnimator.ofFloat(menuView, "translationY", menuViewY, menuViewY - 970);
        animateEndScreenUp = ObjectAnimator.ofFloat(endScreen, "translationY", menuViewY, menuViewY - 970);
        animateScoreBoardUp = ObjectAnimator.ofFloat(scoreBoard, "translationY", menuViewY, menuViewY - 970);
        animatePickMenuUp = ObjectAnimator.ofFloat(pickColor, "translationY", menuViewY, menuViewY - 970);

        animateMenuViewDown = ObjectAnimator.ofFloat(menuView,"translationY",menuViewY - 970, menuViewY);
        animateEndScreenDown = ObjectAnimator.ofFloat(endScreen, "translationY", menuViewY - 970, menuViewY);
        animateScoreBoardDown = ObjectAnimator.ofFloat(scoreBoard,"translationY",menuViewY - 970, menuViewY);
        animatePickMenuDown = ObjectAnimator.ofFloat(pickColor, "translationY", menuViewY - 970, menuViewY);

        animateSettingsMenuUp = ObjectAnimator.ofFloat(settingsMenu, "translationY", menuViewY, menuViewY - 970);
        animateSettingsMenuDown = ObjectAnimator.ofFloat(settingsMenu, "translationY", menuViewY - 970, menuViewY);

        emptyColor = Objects.requireNonNull(AppCompatResources.getDrawable(this, R.drawable.transparent_circle78));

        //endregion

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(animateMenuViewUp, animateScoreBoardUp);
        animatorSet.setDuration(500);
        animatorSet.setStartDelay(500);
        animatorSet.start();

        if (!firstLoad){
            menuViewY = menuView.getY();
            pickViewY = menuViewY;

            Computer.initializeVariables(this);
            difficultyBtn1.toggle();
            Computer.difficulty1 = true;

            getSupportFragmentManager()
                    .beginTransaction()
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .replace(R.id.fragment_container, new BoardLayout())
                    .commit();

            firstLoad = true;
        }

        onePlayerBtn.setOnClickListener(this);
        twoPlayerBtn.setOnClickListener(this);
        settingsBtn.setOnClickListener(this);
        toggleHelpSwitch.setOnClickListener(this);
        pickWhite.setOnClickListener(this);
        pickBlack.setOnClickListener(this);
        returnToMenuBtn.setOnClickListener(this);
        playAgainBtn.setOnClickListener(this);
        clearGameData.setOnClickListener(this);

        difficultyBtn1.setOnClickListener(this);
        difficultyBtn2.setOnClickListener(this);
        difficultyBtn3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case (R.id.onePlayer):
                onePlayer = true;
                pickHeader.setText(R.string.pick_color);
            case (R.id.twoPlayer):
                AnimatorSet animatorSet = new AnimatorSet();

                if (settingsActive){
                    animatorSet.playTogether(animateMenuViewDown, animateSettingsMenuDown);
                }
                else {
                    animatorSet.playTogether(animateMenuViewDown, animateScoreBoardDown);
                }

                animatorSet.setDuration(300);
                animatorSet.start();

                AnimatorSet animatorSetEnd = new AnimatorSet();
                animatorSetEnd.play(animatePickMenuUp);
                animatorSetEnd.setDuration(300).setStartDelay(300);
                animatorSetEnd.start();
                break;
            case (R.id.settings):
                setSettingsActive();
                break;
            case (R.id.toggleHelpBtn):
                placementHelp = !placementHelp;

                if (placementHelp){
                    TransitionManager.beginDelayedTransition(settingsMenu, new TransitionSet().addTransition(new ChangeBounds()));
                    activateHelpHeader.setText("Avaktivera hjälp");
                }
                else{
                    TransitionManager.beginDelayedTransition(settingsMenu, new TransitionSet().addTransition(new ChangeBounds()));
                    activateHelpHeader.setText("Aktivera hjälp");
                }
                break;
            case (R.id.pickWhite):
                colorWhite = true;
            case (R.id.pickBlack):
                AnimatorSet animatorSet3 = new AnimatorSet();
                animatorSet3.play(animatePickMenuDown);
                animatorSet3.setDuration(300).setStartDelay(50);
                animatorSet3.start();

                if (MainActivity.colorWhite){
                    sameColor = Objects.requireNonNull(AppCompatResources.getDrawable(clearGameData.getContext(), R.drawable.white_circle78));
                    oppositeColor = Objects.requireNonNull(AppCompatResources.getDrawable(clearGameData.getContext(), R.drawable.black_circle78));
                }
                else{
                    sameColor = Objects.requireNonNull(AppCompatResources.getDrawable(clearGameData.getContext(), R.drawable.black_circle78));
                    oppositeColor = Objects.requireNonNull(AppCompatResources.getDrawable(clearGameData.getContext(), R.drawable.white_circle78));
                }

                BoardLayout.activateStartingView(this);
                break;
            case (R.id.clearGameData):
                game1.animate().alpha(0).setDuration(200).withEndAction(() -> game1.setVisibility(View.GONE));
                game2.animate().alpha(0).setDuration(200).withEndAction(() -> game2.setVisibility(View.GONE));
                game3.animate().alpha(0).setDuration(200).withEndAction(() -> game3.setVisibility(View.GONE));
                clearGameData.animate().alpha(0).setDuration(200).withEndAction(() -> clearGameData.setVisibility(View.GONE));
                scoreBoardHeader.animate().alpha(1).setDuration(200).withStartAction(() -> {
                    scoreBoardHeader.setVisibility(View.VISIBLE);
                });
                gameCount = 0;
                break;
            case (R.id.playAgain):
                playAgain = true;
            case (R.id.returnToMenu):

                for (ArrayList<ImageView> row : BoardLayout.store_circles){
                    for (ImageView image : row){
                        image.setImageDrawable(AppCompatResources.getDrawable(this, R.drawable.transparent_circle78));
                        image.setClickable(true);
                    }
                }

                timeElapsed = 0;
                colorWhite = false;

                AnimatorSet animatorSet_2 = new AnimatorSet();

                animatorSet_2.play(animateEndScreenDown);
                animatorSet_2.setDuration(300);
                animatorSet_2.start();

                scoreBoardHeader.animate().alpha(0).setDuration(0).withEndAction(() -> {
                    scoreBoardHeader.setVisibility(View.GONE);
                });

                clearGameData.animate().alpha(1).setDuration(200).withStartAction(() -> {
                    clearGameData.setVisibility(View.VISIBLE);
                });

                switch (gameCount){
                    case 1:
                        game1.setVisibility(View.VISIBLE);
                        game1.setAlpha(1);
                        gameIdHeader1.setText("1");
                        timeHeader1.setText(timeElapsed + " s");
                        winnerHeader1.setText(gameWinner);
                        break;
                    case 2:
                        game2.setVisibility(View.VISIBLE);
                        game2.setAlpha(1);
                        gameIdHeader2.setText("2");
                        timeHeader2.setText(timeElapsed + " s");
                        winnerHeader2.setText(gameWinner);
                        break;
                    case 3:
                        game3.setVisibility(View.VISIBLE);
                        game3.setAlpha(1);
                        gameIdHeader3.setText("3");
                        timeHeader3.setText(timeElapsed + " s");
                        winnerHeader3.setText(gameWinner);
                        break;
                    default:
                }

                if (!playAgain){

                    if (settingsActive){
                        animatorSet_2.playTogether(animateMenuViewUp, animateSettingsMenuUp);
                    }
                    else {
                        animatorSet_2.playTogether(animateMenuViewUp, animateScoreBoardUp);
                    }

                    animatorSet_2.setDuration(300);
                    animatorSet_2.start();
                }
                else{
                    BoardLayout.activateStartingView(this);
                    playAgain = false;
                }
                break;
            case (R.id.difficultyBtn1):
                Computer.difficulty2 = false;
                Computer.difficulty3 = false;

                Computer.difficulty1 = true;
                break;
            case (R.id.difficultyBtn2):
                Computer.difficulty1 = false;
                Computer.difficulty3 = false;

                Computer.difficulty2 = true;
                break;
            case (R.id.difficultyBtn3):
                Computer.difficulty1 = false;
                Computer.difficulty2 = false;

                Computer.difficulty3 = true;
                break;
            default:
        }
    }

    private void setSettingsActive(){

        TransitionManager.beginDelayedTransition(settingsBtn, new TransitionSet().addTransition(new ChangeBounds()));

        ObjectAnimator animateScoreboardUpTop = ObjectAnimator.ofFloat(scoreBoard, "translationY", menuViewY - 970,  menuViewY - 1940);
        ObjectAnimator animateScoreboardDownCentre = ObjectAnimator.ofFloat(scoreBoard, "translationY", menuViewY - 1940, menuViewY - 970);

        ObjectAnimator animateSettingsMenuUpCentre = ObjectAnimator.ofFloat(settingsMenu, "translationY", menuViewY, menuViewY - 970);
        ObjectAnimator animateSettingsMenuDownBot = ObjectAnimator.ofFloat(settingsMenu, "translationY", menuViewY - 970, menuViewY);

        AnimatorSet animatorSet = new AnimatorSet();

        if (!settingsActive){

            animatorSet.playTogether(animateScoreboardUpTop, animateSettingsMenuUpCentre);
            animatorSet.setDuration(300);
            animatorSet.start();

            settingsBtnText.setText("Poängtavla");

            settingsActive = true;
        }
        else{

            animatorSet.playTogether(animateScoreboardDownCentre, animateSettingsMenuDownBot);
            animatorSet.setDuration(300);
            animatorSet.start();

            settingsBtnText.setText("Inställningar");

            settingsActive = false;
        }
    }

    public static boolean areDrawablesIdentical(Drawable drawableA, Drawable drawableB){

        Drawable.ConstantState stateA = drawableA.getConstantState();
        Drawable.ConstantState stateB = drawableB.getConstantState();

        // If the constant state is identical, they are using the same drawable resource.
        // However, the opposite is not necessarily true.
        return (stateA != null && stateB != null && stateA.equals(stateB))
                || getBitmap(drawableA).sameAs(getBitmap(drawableB));
    }

    public static Bitmap getBitmap(Drawable drawable) {

        Bitmap result;

        if (drawable instanceof BitmapDrawable) {
            result = ((BitmapDrawable) drawable).getBitmap();
        } else {
            int width = drawable.getIntrinsicWidth();
            int height = drawable.getIntrinsicHeight();
            // Some drawables have no intrinsic width - e.g. solid colours.
            if (width <= 0) {
                width = 1;
            }
            if (height <= 0) {
                height = 1;
            }

            result = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(result);
            drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
            drawable.draw(canvas);
        }

        return result;
    }
}