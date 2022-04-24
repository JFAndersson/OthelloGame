package com.example.othello;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.appcompat.content.res.AppCompatResources;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.transition.ChangeBounds;
import android.transition.TransitionManager;
import android.transition.TransitionSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class BoardLayout extends Fragment implements View.OnClickListener {

    //region declarations

    public static ArrayList<ArrayList<ImageView>> store_circles = new ArrayList<>();
    public static ArrayList<ArrayList<ImageView>> store_circles_future = new ArrayList<>();

    private View view;
    public static View blackTint;

    public static boolean finalConversionResult = false;

    public static CardView whiteVisualizer;
    public static CardView blackVisualizer;
    public static CardView boardArea;

    private ImageView clickedVariable;
    public static ImageView userWhiteImage;
    public static ImageView userBlackImage;

    private static int loopCount = 0;

    private ImageView circleA1; private ImageView circleB1;
    private ImageView circleA2; private ImageView circleB2;
    private ImageView circleA3; private ImageView circleB3;
    private ImageView circleA4; private ImageView circleB4;
    private ImageView circleA5; private ImageView circleB5;
    private ImageView circleA6; private ImageView circleB6;
    private ImageView circleA7; private ImageView circleB7;
    private ImageView circleA8; private ImageView circleB8;

    private ImageView circleC1; private ImageView circleD1;
    private ImageView circleC2; private ImageView circleD2;
    private ImageView circleC3; private ImageView circleD3;
    private ImageView circleC4; private ImageView circleD4;
    private ImageView circleC5; private ImageView circleD5;
    private ImageView circleC6; private ImageView circleD6;
    private ImageView circleC7; private ImageView circleD7;
    private ImageView circleC8; private ImageView circleD8;

    private ImageView circleE1; private ImageView circleF1;
    private ImageView circleE2; private ImageView circleF2;
    private ImageView circleE3; private ImageView circleF3;
    private ImageView circleE4; private ImageView circleF4;
    private ImageView circleE5; private ImageView circleF5;
    private ImageView circleE6; private ImageView circleF6;
    private ImageView circleE7; private ImageView circleF7;
    private ImageView circleE8; private ImageView circleF8;

    private ImageView circleG1; private ImageView circleH1;
    private ImageView circleG2; private ImageView circleH2;
    private ImageView circleG3; private ImageView circleH3;
    private ImageView circleG4; private ImageView circleH4;
    private ImageView circleG5; private ImageView circleH5;
    private ImageView circleG6; private ImageView circleH6;
    private ImageView circleG7; private ImageView circleH7;
    private ImageView circleG8; private ImageView circleH8;

    //endregion

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_board_layout, container, false);

        //region instantiations

        blackTint = view.findViewById(R.id.blackTint);
        whiteVisualizer = view.findViewById(R.id.whiteVisualizer);
        blackVisualizer = view.findViewById(R.id.blackVisualizer);
        boardArea = view.findViewById(R.id.shadowColor);

        userWhiteImage = view.findViewById(R.id.userWhiteImage);
        userBlackImage = view.findViewById(R.id.userBlackImage);

        circleA1 = view.findViewById(R.id.circleA1); circleB1 = view.findViewById(R.id.circleB1);
        circleA2 = view.findViewById(R.id.circleA2); circleB2 = view.findViewById(R.id.circleB2);
        circleA3 = view.findViewById(R.id.circleA3); circleB3 = view.findViewById(R.id.circleB3);
        circleA4 = view.findViewById(R.id.circleA4); circleB4 = view.findViewById(R.id.circleB4);
        circleA5 = view.findViewById(R.id.circleA5); circleB5 = view.findViewById(R.id.circleB5);
        circleA6 = view.findViewById(R.id.circleA6); circleB6 = view.findViewById(R.id.circleB6);
        circleA7 = view.findViewById(R.id.circleA7); circleB7 = view.findViewById(R.id.circleB7);
        circleA8 = view.findViewById(R.id.circleA8); circleB8 = view.findViewById(R.id.circleB8);

        circleC1 = view.findViewById(R.id.circleC1); circleD1 = view.findViewById(R.id.circleD1);
        circleC2 = view.findViewById(R.id.circleC2); circleD2 = view.findViewById(R.id.circleD2);
        circleC3 = view.findViewById(R.id.circleC3); circleD3 = view.findViewById(R.id.circleD3);
        circleC4 = view.findViewById(R.id.circleC4); circleD4 = view.findViewById(R.id.circleD4);
        circleC5 = view.findViewById(R.id.circleC5); circleD5 = view.findViewById(R.id.circleD5);
        circleC6 = view.findViewById(R.id.circleC6); circleD6 = view.findViewById(R.id.circleD6);
        circleC7 = view.findViewById(R.id.circleC7); circleD7 = view.findViewById(R.id.circleD7);
        circleC8 = view.findViewById(R.id.circleC8); circleD8 = view.findViewById(R.id.circleD8);

        circleE1 = view.findViewById(R.id.circleE1); circleF1 = view.findViewById(R.id.circleF1);
        circleE2 = view.findViewById(R.id.circleE2); circleF2 = view.findViewById(R.id.circleF2);
        circleE3 = view.findViewById(R.id.circleE3); circleF3 = view.findViewById(R.id.circleF3);
        circleE4 = view.findViewById(R.id.circleE4); circleF4 = view.findViewById(R.id.circleF4);
        circleE5 = view.findViewById(R.id.circleE5); circleF5 = view.findViewById(R.id.circleF5);
        circleE6 = view.findViewById(R.id.circleE6); circleF6 = view.findViewById(R.id.circleF6);
        circleE7 = view.findViewById(R.id.circleE7); circleF7 = view.findViewById(R.id.circleF7);
        circleE8 = view.findViewById(R.id.circleE8); circleF8 = view.findViewById(R.id.circleF8);

        circleG1 = view.findViewById(R.id.circleG1); circleH1 = view.findViewById(R.id.circleH1);
        circleG2 = view.findViewById(R.id.circleG2); circleH2 = view.findViewById(R.id.circleH2);
        circleG3 = view.findViewById(R.id.circleG3); circleH3 = view.findViewById(R.id.circleH3);
        circleG4 = view.findViewById(R.id.circleG4); circleH4 = view.findViewById(R.id.circleH4);
        circleG5 = view.findViewById(R.id.circleG5); circleH5 = view.findViewById(R.id.circleH5);
        circleG6 = view.findViewById(R.id.circleG6); circleH6 = view.findViewById(R.id.circleH6);
        circleG7 = view.findViewById(R.id.circleG7); circleH7 = view.findViewById(R.id.circleH7);
        circleG8 = view.findViewById(R.id.circleG8); circleH8 = view.findViewById(R.id.circleH8);

        ArrayList<ImageView> rowA = new ArrayList<>();
        rowA.add(circleA1); rowA.add(circleA2); rowA.add(circleA3); rowA.add(circleA4);
        rowA.add(circleA5); rowA.add(circleA6); rowA.add(circleA7); rowA.add(circleA8);

        ArrayList<ImageView> rowB = new ArrayList<>();
        rowB.add(circleB1); rowB.add(circleB2); rowB.add(circleB3); rowB.add(circleB4);
        rowB.add(circleB5); rowB.add(circleB6); rowB.add(circleB7); rowB.add(circleB8);

        ArrayList<ImageView> rowC = new ArrayList<>();
        rowC.add(circleC1); rowC.add(circleC2); rowC.add(circleC3); rowC.add(circleC4);
        rowC.add(circleC5); rowC.add(circleC6); rowC.add(circleC7); rowC.add(circleC8);

        ArrayList<ImageView> rowD = new ArrayList<>();
        rowD.add(circleD1); rowD.add(circleD2); rowD.add(circleD3); rowD.add(circleD4);
        rowD.add(circleD5); rowD.add(circleD6); rowD.add(circleD7); rowD.add(circleD8);

        ArrayList<ImageView> rowE = new ArrayList<>();
        rowE.add(circleE1); rowE.add(circleE2); rowE.add(circleE3); rowE.add(circleE4);
        rowE.add(circleE5); rowE.add(circleE6); rowE.add(circleE7); rowE.add(circleE8);

        ArrayList<ImageView> rowF = new ArrayList<>();
        rowF.add(circleF1); rowF.add(circleF2); rowF.add(circleF3); rowF.add(circleF4);
        rowF.add(circleF5); rowF.add(circleF6); rowF.add(circleF7); rowF.add(circleF8);

        ArrayList<ImageView> rowG = new ArrayList<>();
        rowG.add(circleG1); rowG.add(circleG2); rowG.add(circleG3); rowG.add(circleG4);
        rowG.add(circleG5); rowG.add(circleG6); rowG.add(circleG7); rowG.add(circleG8);

        ArrayList<ImageView> rowH = new ArrayList<>();
        rowH.add(circleH1); rowH.add(circleH2); rowH.add(circleH3); rowH.add(circleH4);
        rowH.add(circleH5); rowH.add(circleH6); rowH.add(circleH7); rowH.add(circleH8);

        store_circles.add(rowA); store_circles.add(rowB);
        store_circles.add(rowC); store_circles.add(rowD);
        store_circles.add(rowE); store_circles.add(rowF);
        store_circles.add(rowG); store_circles.add(rowH);

        //endregion

        //region setListeners

        for (ArrayList<ImageView> row : store_circles){
            for (ImageView image : row){
                image.setOnClickListener(this);
            }
        }

        //endregion

        return view;
    }

    public static void activateStartingView(Activity getActivity){

        for (ArrayList<ImageView> row : store_circles){
            for (ImageView image : row){
                image.setImageAlpha(0);
            }
        }

        //ImageView circleA1 = getActivity.findViewById(R.id.circleA1);
        ImageView circleD4 = getActivity.findViewById(R.id.circleD4);
        ImageView circleD5 = getActivity.findViewById(R.id.circleD5);
        ImageView circleE4 = getActivity.findViewById(R.id.circleE4);
        ImageView circleE5 = getActivity.findViewById(R.id.circleE5);
        //ImageView circleH8 = getActivity.findViewById(R.id.circleH8);

        ObjectAnimator returnScaleY = ObjectAnimator.ofFloat(boardArea, "scaleY", 0.7f, 1.0f);
        ObjectAnimator returnScaleX = ObjectAnimator.ofFloat(boardArea, "scaleX", 0.7f, 1.0f);

        AnimatorSet animatorSet = new AnimatorSet();

        for (ArrayList<ImageView> row : store_circles){
            for (ImageView image : row){

                /*
                image.setImageAlpha(255);

                if (!image.equals(circleA1) && !image.equals(circleH8)){
                    image.setImageDrawable(AppCompatResources.getDrawable(getActivity, R.drawable.white_circle78));
                    image.setClickable(false);
                }
                else if (image.equals(circleA1)){
                    image.setImageDrawable(AppCompatResources.getDrawable(getActivity, R.drawable.black_circle78));
                    image.setClickable(false);
                }

                 */

                switch (image.getId()){
                    case (R.id.circleD4):
                    case (R.id.circleD5):
                    case (R.id.circleE4):
                    case (R.id.circleE5):
                        image.setImageAlpha(255);
                        break;
                    default:
                        if (MainActivity.gameCount > 0){
                            image.setImageDrawable(AppCompatResources.getDrawable(getActivity, R.drawable.transparent_circle78));
                            image.setImageAlpha(0);
                        }
                }
            }
        }


        try{
            circleD4.setImageDrawable(AppCompatResources.getDrawable(getActivity, R.drawable.white_circle78));
            circleD5.setImageDrawable(AppCompatResources.getDrawable(getActivity, R.drawable.black_circle78));
            circleE4.setImageDrawable(AppCompatResources.getDrawable(getActivity, R.drawable.black_circle78));
            circleE5.setImageDrawable(AppCompatResources.getDrawable(getActivity, R.drawable.white_circle78));

            circleD4.setClickable(false);
            circleD5.setClickable(false);
            circleE4.setClickable(false);
            circleE5.setClickable(false);
        }
        catch (Exception ignored){}

        //blackTint.animate().alpha(0).setDuration(500);

        animatorSet.playTogether(returnScaleX, returnScaleY);
        animatorSet.setDuration(500);
        animatorSet.start();

        if (MainActivity.onePlayer && MainActivity.colorWhite){
            userBlackImage.setImageDrawable(AppCompatResources.getDrawable(getActivity, R.drawable.computerblack));
        }
        else if (MainActivity.onePlayer){
            userWhiteImage.setImageDrawable(AppCompatResources.getDrawable(getActivity, R.drawable.computerwhite));
        }

        boardArea.animate().alpha(1).setDuration(200);
        whiteVisualizer.animate().alpha(1).setDuration(200);
        blackVisualizer.animate().alpha(1).setDuration(200);

        TransitionManager.beginDelayedTransition(whiteVisualizer, new TransitionSet());
        TransitionManager.beginDelayedTransition(blackVisualizer, new TransitionSet());

        alterVisualiser();

        //TODO: Help indicators
        if (MainActivity.placementHelp){
            Indicators.activateIndicators(false);
        }

        MainActivity.gameCount++;
        MainActivity.timeStart = Instant.now();
    }

    @Override
    public void onClick(View v) {
        if (getActivity() != null){
            switch (v.getId()){
                case (R.id.circleA1):
                    clickedVariable = view.findViewById(R.id.circleA1);
                    break;
                case (R.id.circleA2):
                    clickedVariable = view.findViewById(R.id.circleA2);
                    break;
                case (R.id.circleA3):
                    clickedVariable = view.findViewById(R.id.circleA3);
                    break;
                case (R.id.circleA4):
                    clickedVariable = view.findViewById(R.id.circleA4);
                    break;
                case (R.id.circleA5):
                    clickedVariable = view.findViewById(R.id.circleA5);
                    break;
                case (R.id.circleA6):
                    clickedVariable = view.findViewById(R.id.circleA6);
                    break;
                case (R.id.circleA7):
                    clickedVariable = view.findViewById(R.id.circleA7);
                    break;
                case (R.id.circleA8):
                    clickedVariable = view.findViewById(R.id.circleA8);
                    break;
                case (R.id.circleB1):
                    clickedVariable = view.findViewById(R.id.circleB1);
                    break;
                case (R.id.circleB2):
                    clickedVariable = view.findViewById(R.id.circleB2);
                    break;
                case (R.id.circleB3):
                    clickedVariable = view.findViewById(R.id.circleB3);
                    break;
                case (R.id.circleB4):
                    clickedVariable = view.findViewById(R.id.circleB4);
                    break;
                case (R.id.circleB5):
                    clickedVariable = view.findViewById(R.id.circleB5);
                    break;
                case (R.id.circleB6):
                    clickedVariable = view.findViewById(R.id.circleB6);
                    break;
                case (R.id.circleB7):
                    clickedVariable = view.findViewById(R.id.circleB7);
                    break;
                case (R.id.circleB8):
                    clickedVariable = view.findViewById(R.id.circleB8);
                    break;
                case (R.id.circleC1):
                    clickedVariable = view.findViewById(R.id.circleC1);
                    break;
                case (R.id.circleC2):
                    clickedVariable = view.findViewById(R.id.circleC2);
                    break;
                case (R.id.circleC3):
                    clickedVariable = view.findViewById(R.id.circleC3);
                    break;
                case (R.id.circleC4):
                    clickedVariable = view.findViewById(R.id.circleC4);
                    break;
                case (R.id.circleC5):
                    clickedVariable = view.findViewById(R.id.circleC5);
                    break;
                case (R.id.circleC6):
                    clickedVariable = view.findViewById(R.id.circleC6);
                    break;
                case (R.id.circleC7):
                    clickedVariable = view.findViewById(R.id.circleC7);
                    break;
                case (R.id.circleC8):
                    clickedVariable = view.findViewById(R.id.circleC8);
                    break;
                case (R.id.circleD1):
                    clickedVariable = view.findViewById(R.id.circleD1);
                    break;
                case (R.id.circleD2):
                    clickedVariable = view.findViewById(R.id.circleD2);
                    break;
                case (R.id.circleD3):
                    clickedVariable = view.findViewById(R.id.circleD3);
                    break;
                case (R.id.circleD4):
                    clickedVariable = view.findViewById(R.id.circleD4);
                    break;
                case (R.id.circleD5):
                    clickedVariable = view.findViewById(R.id.circleD5);
                    break;
                case (R.id.circleD6):
                    clickedVariable = view.findViewById(R.id.circleD6);
                    break;
                case (R.id.circleD7):
                    clickedVariable = view.findViewById(R.id.circleD7);
                    break;
                case (R.id.circleD8):
                    clickedVariable = view.findViewById(R.id.circleD8);
                    break;
                case (R.id.circleE1):
                    clickedVariable = view.findViewById(R.id.circleE1);
                    break;
                case (R.id.circleE2):
                    clickedVariable = view.findViewById(R.id.circleE2);
                    break;
                case (R.id.circleE3):
                    clickedVariable = view.findViewById(R.id.circleE3);
                    break;
                case (R.id.circleE4):
                    clickedVariable = view.findViewById(R.id.circleE4);
                    break;
                case (R.id.circleE5):
                    clickedVariable = view.findViewById(R.id.circleE5);
                    break;
                case (R.id.circleE6):
                    clickedVariable = view.findViewById(R.id.circleE6);
                    break;
                case (R.id.circleE7):
                    clickedVariable = view.findViewById(R.id.circleE7);
                    break;
                case (R.id.circleE8):
                    clickedVariable = view.findViewById(R.id.circleE8);
                    break;
                case (R.id.circleF1):
                    clickedVariable = view.findViewById(R.id.circleF1);
                    break;
                case (R.id.circleF2):
                    clickedVariable = view.findViewById(R.id.circleF2);
                    break;
                case (R.id.circleF3):
                    clickedVariable = view.findViewById(R.id.circleF3);
                    break;
                case (R.id.circleF4):
                    clickedVariable = view.findViewById(R.id.circleF4);
                    break;
                case (R.id.circleF5):
                    clickedVariable = view.findViewById(R.id.circleF5);
                    break;
                case (R.id.circleF6):
                    clickedVariable = view.findViewById(R.id.circleF6);
                    break;
                case (R.id.circleF7):
                    clickedVariable = view.findViewById(R.id.circleF7);
                    break;
                case (R.id.circleF8):
                    clickedVariable = view.findViewById(R.id.circleF8);
                    break;
                case (R.id.circleG1):
                    clickedVariable = view.findViewById(R.id.circleG1);
                    break;
                case (R.id.circleG2):
                    clickedVariable = view.findViewById(R.id.circleG2);
                    break;
                case (R.id.circleG3):
                    clickedVariable = view.findViewById(R.id.circleG3);
                    break;
                case (R.id.circleG4):
                    clickedVariable = view.findViewById(R.id.circleG4);
                    break;
                case (R.id.circleG5):
                    clickedVariable = view.findViewById(R.id.circleG5);
                    break;
                case (R.id.circleG6):
                    clickedVariable = view.findViewById(R.id.circleG6);
                    break;
                case (R.id.circleG7):
                    clickedVariable = view.findViewById(R.id.circleG7);
                    break;
                case (R.id.circleG8):
                    clickedVariable = view.findViewById(R.id.circleG8);
                    break;
                case (R.id.circleH1):
                    clickedVariable = view.findViewById(R.id.circleH1);
                    break;
                case (R.id.circleH2):
                    clickedVariable = view.findViewById(R.id.circleH2);
                    break;
                case (R.id.circleH3):
                    clickedVariable = view.findViewById(R.id.circleH3);
                    break;
                case (R.id.circleH4):
                    clickedVariable = view.findViewById(R.id.circleH4);
                    break;
                case (R.id.circleH5):
                    clickedVariable = view.findViewById(R.id.circleH5);
                    break;
                case (R.id.circleH6):
                    clickedVariable = view.findViewById(R.id.circleH6);
                    break;
                case (R.id.circleH7):
                    clickedVariable = view.findViewById(R.id.circleH7);
                    break;
                case (R.id.circleH8):
                    clickedVariable = view.findViewById(R.id.circleH8);
                    break;
                default:
            }

            Framework.setProximity(clickedVariable, false);

            if (finalConversionResult){

                //region checkWin

                Drawable whiteColor = Objects.requireNonNull(AppCompatResources.getDrawable(clickedVariable.getContext(), R.drawable.white_circle78));
                Drawable blackColor = Objects.requireNonNull(AppCompatResources.getDrawable(clickedVariable.getContext(), R.drawable.black_circle78));

                int whiteCount = 0;
                int blackCount = 0;

                for (ArrayList<ImageView> row : store_circles){
                    for (ImageView imageView : row){
                        if (MainActivity.areDrawablesIdentical(imageView.getDrawable(), whiteColor)){
                            whiteCount++;
                        }
                        else if (MainActivity.areDrawablesIdentical(imageView.getDrawable(), blackColor)){
                            blackCount++;
                        }
                    }
                }

                if (whiteCount + blackCount == 64 || (Indicators.helperCount == 0 && MainActivity.placementHelp)){
                    MainActivity.gameWon = true;

                    MainActivity.timeEnd = Instant.now();
                    MainActivity.timeElapsed = Duration.between(MainActivity.timeStart, MainActivity.timeEnd).getSeconds();

                    if (whiteCount > blackCount){
                        MainActivity.gameWinner = "Vit";
                    }
                    else if (blackCount > whiteCount) {
                        MainActivity.gameWinner = "Svart";
                    }
                    else{
                        MainActivity.gameWinner = "Oavgjort";
                    }
                }

                //endregion

                if (!MainActivity.gameWon){

                    MainActivity.colorWhite = !MainActivity.colorWhite;
                    applyColor();
                    alterVisualiser();

                    //region ifComputersMove

                    if (MainActivity.onePlayer){
                        new java.util.Timer().schedule(
                                new java.util.TimerTask() {
                                    @Override
                                    public void run() {
                                        getActivity().runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                ifOnePlayer();
                                            }
                                        });
                                    }
                                },
                                1000
                        );
                    }

                    //endregion

                    if (MainActivity.placementHelp && !MainActivity.onePlayer){
                        Indicators.activateIndicators(false);
                    }
                }
                else{
                    //region ifWon

                    ObjectAnimator downScaleY = ObjectAnimator.ofFloat(boardArea, "scaleY", 1.0f, 0.5f);
                    ObjectAnimator downScaleX = ObjectAnimator.ofFloat(boardArea, "scaleX", 1.0f, 0.5f);

                    AnimatorSet animatorSet = new AnimatorSet();

                    animatorSet.playTogether(downScaleX, downScaleY);
                    animatorSet.setDuration(500);
                    animatorSet.start();

                    animatorSet.play(MainActivity.animateEndScreenUp);
                    animatorSet.setDuration(500);
                    animatorSet.start();

                    MainActivity.wonHeader.setText(String.format("Vinnare: %s", MainActivity.gameWinner));

                    boardArea.animate().alpha(0).setDuration(200).withEndAction(() -> {
                            /*
                            boardArea.animate().translationXBy(800).setDuration(0).withEndAction(() -> {
                                boardArea.animate().alpha(1).setDuration(200);
                            });
                             */
                    });

                    whiteVisualizer.animate().alpha(0).setDuration(200);
                    blackVisualizer.animate().alpha(0).setDuration(200);

                    //endregion
                }
            }
        }
    }

    private static void ifOnePlayer(){
        try {
            Computer.findPossiblePlacements();

            MainActivity.colorWhite = !MainActivity.colorWhite;
            applyColor();
            alterVisualiser();

            if (MainActivity.placementHelp){
                Indicators.activateIndicators(false);
            }
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void alterVisualiser(){
        if (MainActivity.colorWhite){
            userBlackImage.animate().alpha(0).setDuration(200);
            userWhiteImage.animate().alpha(1).setDuration(200);

            //Toast.makeText(userWhiteImage.getContext(), "White Active", Toast.LENGTH_SHORT).show();

            TransitionManager.beginDelayedTransition(blackVisualizer, new TransitionSet().addTransition(new ChangeBounds()));
            ViewGroup.LayoutParams menuParams = blackVisualizer.getLayoutParams();
            menuParams.width = 100;
            menuParams.height = 100;
            blackVisualizer.setLayoutParams(menuParams);

            TransitionManager.beginDelayedTransition(whiteVisualizer, new TransitionSet().addTransition(new ChangeBounds()));
            ViewGroup.LayoutParams newMenuParams = whiteVisualizer.getLayoutParams();
            newMenuParams.width = 250;
            newMenuParams.height = 250;
            whiteVisualizer.setLayoutParams(newMenuParams);

            //Toast.makeText(userBlackImage.getContext(), "White Active", Toast.LENGTH_SHORT).show();
        }
        else{
            userBlackImage.animate().alpha(1).setDuration(200);
            userWhiteImage.animate().alpha(0).setDuration(200);

            //Toast.makeText(userBlackImage.getContext(), "Black Active", Toast.LENGTH_SHORT).show();

            TransitionManager.beginDelayedTransition(whiteVisualizer, new TransitionSet().addTransition(new ChangeBounds()));
            ViewGroup.LayoutParams menuParams = whiteVisualizer.getLayoutParams();
            menuParams.width = 100;
            menuParams.height = 100;
            whiteVisualizer.setLayoutParams(menuParams);

            TransitionManager.beginDelayedTransition(blackVisualizer, new TransitionSet().addTransition(new ChangeBounds()));
            ViewGroup.LayoutParams newMenuParams = blackVisualizer.getLayoutParams();
            newMenuParams.width = 250;
            newMenuParams.height = 250;
            blackVisualizer.setLayoutParams(newMenuParams);

            //Toast.makeText(userBlackImage.getContext(), "Black Active", Toast.LENGTH_SHORT).show();
        }
    }

    private static void applyColor(){
        if (MainActivity.colorWhite){
            MainActivity.sameColor = Objects.requireNonNull(AppCompatResources.getDrawable(userWhiteImage.getContext(), R.drawable.white_circle78));
            MainActivity.oppositeColor = Objects.requireNonNull(AppCompatResources.getDrawable(userWhiteImage.getContext(), R.drawable.black_circle78));
        }
        else{
            MainActivity.sameColor = Objects.requireNonNull(AppCompatResources.getDrawable(userBlackImage.getContext(), R.drawable.black_circle78));
            MainActivity.oppositeColor = Objects.requireNonNull(AppCompatResources.getDrawable(userBlackImage.getContext(), R.drawable.white_circle78));
        }
    }
}