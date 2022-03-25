package com.example.othello;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
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
import android.view.ViewGroup;
import android.widget.Switch;
import android.widget.TextView;

import com.google.android.material.switchmaterial.SwitchMaterial;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //region declarations

    public static float menuViewY;
    private float settingsBtnWidth;
    private float settingsBtnHeight;
    private float settingsBtnX;
    private float settingsBtnY;
    private float menuWidth;
    private float scoreboardWidth;

    private boolean firstLoad = false;
    private boolean settingsActive = false;
    public static boolean onePlayer = false;
    public static boolean colorWhite = false;
    public static boolean placementHelp = false;

    private CardView menuView;
    private CardView pickColor;
    private CardView scoreBoard;
    private CardView onePlayerBtn;
    private CardView twoPlayerBtn;
    private CardView settingsBtn;

    private TextView gameHeader;
    private TextView pickHeader;
    private TextView settingsBtnHeader;
    private TextView scoreBoardHeader;

    private SwitchMaterial toggleHelpSwitch;

    private ObjectAnimator animateMenuViewDown;
    private ObjectAnimator animateScoreBoardDown;
    private ObjectAnimator animatePickMenuDown;
    private ObjectAnimator animateSettingBtnDown;

    private ObjectAnimator animateMenuViewUp;
    private ObjectAnimator animateScoreBoardUp;
    private ObjectAnimator animatePickMenuUp;
    private ObjectAnimator animateSettingBtnUp;

    private ObjectAnimator animateSettingBtnLeft;
    private ObjectAnimator animateSettingBtnRight;

    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        menuView = findViewById(R.id.menuView);
        gameHeader = findViewById(R.id.gameHeader);
        pickHeader = findViewById(R.id.pickHeader);
        settingsBtnHeader = findViewById(R.id.settingsBtnHeader);
        scoreBoardHeader = findViewById(R.id.scoreBoardHeader);
        toggleHelpSwitch = findViewById(R.id.toggleHelpBtn);
        pickColor = findViewById(R.id.pickColor);
        scoreBoard = findViewById(R.id.scoreBoard);
        onePlayerBtn = findViewById(R.id.onePlayer);
        twoPlayerBtn = findViewById(R.id.twoPlayer);
        settingsBtn = findViewById(R.id.settings);

        CardView pickWhite = findViewById(R.id.pickWhite);
        CardView pickBlack = findViewById(R.id.pickBlack);

        if (!firstLoad){
            menuViewY = menuView.getY();
            menuWidth = menuView.getWidth();
            scoreboardWidth = scoreBoard.getWidth();
            settingsBtnWidth = settingsBtn.getWidth();
            settingsBtnHeight = settingsBtn.getHeight();
            settingsBtnX = settingsBtn.getX();
            settingsBtnY = settingsBtn.getY();

            firstLoad = true;
        }

        animateMenuViewUp = ObjectAnimator.ofFloat(menuView,"translationY",menuViewY, menuViewY - 970);
        animateScoreBoardUp = ObjectAnimator.ofFloat(scoreBoard,"translationY",menuViewY, menuViewY - 970);
        animatePickMenuUp = ObjectAnimator.ofFloat(pickColor, "translationY", menuViewY, menuViewY - 970);
        animateSettingBtnUp = ObjectAnimator.ofFloat(settingsBtn, "translationY", settingsBtnY + 27, settingsBtnY);

        animateMenuViewDown = ObjectAnimator.ofFloat(menuView,"translationY",menuViewY - 970, menuViewY);
        animateScoreBoardDown = ObjectAnimator.ofFloat(scoreBoard,"translationY",menuViewY - 970, menuViewY);
        animatePickMenuDown = ObjectAnimator.ofFloat(pickColor, "translationY", menuViewY - 970, menuViewY);
        animateSettingBtnDown = ObjectAnimator.ofFloat(settingsBtn, "translationY", settingsBtnY, settingsBtnY + 27);

        animateSettingBtnLeft = ObjectAnimator.ofFloat(settingsBtn, "translationX", settingsBtnX, settingsBtnX - 315);
        animateSettingBtnRight = ObjectAnimator.ofFloat(settingsBtn, "translationX", settingsBtnX - 315, settingsBtnX);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(animateMenuViewUp, animateScoreBoardUp);
        animatorSet.setDuration(300);
        animatorSet.start();

        onePlayerBtn.setOnClickListener(this);
        twoPlayerBtn.setOnClickListener(this);
        settingsBtn.setOnClickListener(this);
        toggleHelpSwitch.setOnClickListener(this);
        pickWhite.setOnClickListener(this);
        pickBlack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case (R.id.onePlayer):
                onePlayer = true;
                pickHeader.setText(R.string.pick_color);
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
                setSettingsActive();
                break;
            case (R.id.toggleHelpBtn):
                placementHelp = !placementHelp;
                break;
            case (R.id.pickWhite):
                colorWhite = true;
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

    private void setSettingsActive(){
        TransitionManager.beginDelayedTransition(menuView, new TransitionSet().addTransition(new ChangeBounds()));
        TransitionManager.beginDelayedTransition(scoreBoard, new TransitionSet().addTransition(new ChangeBounds()));

        ViewGroup.LayoutParams menuParams = menuView.getLayoutParams();
        ViewGroup.LayoutParams settingsBtnParams = settingsBtn.getLayoutParams();
        ViewGroup.LayoutParams scoreBoardParams = scoreBoard.getLayoutParams();

        AnimatorSet animatorSettingsBtn = new AnimatorSet();

        if (!settingsActive){
            settingsActive = true;

            scoreBoard.animate().alpha(0).setDuration(200).withStartAction(() -> {
                scoreBoardHeader.setVisibility(View.GONE);
                scoreBoardParams.width = 10;
                scoreBoard.setLayoutParams(scoreBoardParams);
            }).withEndAction(() -> {
                scoreBoard.setVisibility(View.GONE);
            });

            onePlayerBtn.animate().alpha(0).setDuration(200).withEndAction(() -> {
                onePlayerBtn.setVisibility(View.GONE);
            });

            toggleHelpSwitch.setVisibility(View.VISIBLE);

            twoPlayerBtn.animate().alpha(0).setDuration(200).withEndAction(() -> {
                twoPlayerBtn.setVisibility(View.GONE);
                toggleHelpSwitch.animate().alpha(1).setDuration(100);
            });

            animatorSettingsBtn.playTogether(animateSettingBtnDown, animateSettingBtnLeft);
            animatorSettingsBtn.setDuration(300);
            animatorSettingsBtn.setStartDelay(100);
            animatorSettingsBtn.start();

            settingsBtnParams.width = 120;
            settingsBtnParams.height = 70;
            settingsBtn.setLayoutParams(settingsBtnParams);

            settingsBtnHeader.setText(R.string.returnHeader);

            gameHeader.setText(R.string.settingsHeader);

            menuParams.width = 835;
            menuView.setLayoutParams(menuParams);
        }
        else{
            settingsActive = false;

            scoreBoard.animate().alpha(1).setDuration(200).withStartAction(() -> {
                scoreBoardHeader.setVisibility(View.VISIBLE);
                scoreBoard.setVisibility(View.VISIBLE);

                scoreBoardParams.width = 300;
                scoreBoard.setLayoutParams(scoreBoardParams);
            });

            onePlayerBtn.animate().alpha(1).setDuration(200).withStartAction(() -> {
                onePlayerBtn.setVisibility(View.VISIBLE);
            });

            twoPlayerBtn.animate().alpha(1).setDuration(200).withStartAction(() -> {
                twoPlayerBtn.setVisibility(View.VISIBLE);
            });

            animatorSettingsBtn.playTogether(animateSettingBtnUp, animateSettingBtnRight);
            animatorSettingsBtn.setDuration(300);
            animatorSettingsBtn.setStartDelay(100);
            animatorSettingsBtn.start();

            settingsBtnParams.width = 375;
            settingsBtnParams.height = 103;
            settingsBtn.setLayoutParams(settingsBtnParams);

            settingsBtnHeader.setText(R.string.settingsHeader);

            gameHeader.setText(R.string.gameHeader);
            toggleHelpSwitch.animate().alpha(0).setDuration(80).withEndAction(() -> {
                toggleHelpSwitch.setVisibility(View.GONE);
            });

            menuParams.width = 512;
            menuView.setLayoutParams(menuParams);
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