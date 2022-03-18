package com.example.othello;

import android.graphics.drawable.Drawable;
import android.media.Image;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;

import java.util.ArrayList;
import java.util.Calendar;

public class GameFramework {

    //region declarations

    public static ArrayList<ArrayList<Character>> docileCircles = new ArrayList<>();

    public static int circleRow = 0;

    public static boolean firstLoad = false;

    public static boolean rowTrue = false;
    public static boolean columnTrue = false;
    public static boolean diagonalTrue = false;

    //endregion

    public static void findClickedCircles(){

        for (int i = 0; i < 8; i++){
            docileCircles.add(new ArrayList<>());
        }

        for (ArrayList<ImageView> row : BoardLayout.store_circles){
            for (ImageView image : row){
                //Checks if any of the circles in that row are unclickable, and thus if any of the circles there have clicked on by the player
                if (!image.isClickable() && image.getVisibility() == View.VISIBLE){
                    //Adds a 'U' to the ArrayList to signify that the circle in question is unclickable
                    docileCircles.get(circleRow).add('U');
                }
                else{
                    //Adds a 'C' to the ArrayList to signify that the circle in question is clickable
                    docileCircles.get(circleRow).add('C');
                }
            }
            circleRow++;
        }

        circleRow = 0;
    }

    public static boolean checkProximity(ImageView clickedVariable){

        int middleCircleCount = 0;
        int oppositeColorIndex = 0;
        boolean circleFound = false;

        Drawable oppositeColor;

        if (MainActivity.colorWhite){
            oppositeColor = AppCompatResources.getDrawable(clickedVariable.getContext(), R.drawable.black_circle78);
        }
        else{
            oppositeColor = AppCompatResources.getDrawable(clickedVariable.getContext(), R.drawable.white_circle78);
        }

        Toast.makeText(clickedVariable.getContext(), String.valueOf(BoardLayout.rowIndexClicked), Toast.LENGTH_SHORT).show();

        for (ImageView imageView : BoardLayout.store_circles.get(BoardLayout.rowIndexClicked)){

            if (imageView.getDrawable().equals(oppositeColor)){
                oppositeColorIndex = BoardLayout.store_circles.get(BoardLayout.rowIndexClicked).indexOf(imageView);
            }

            /*
            if (imageView.equals(clickedVariable)){
                circleFound = true;
            }

            if (circleFound){
                if (!imageView.getDrawable().equals(oppositeColor)){
                    middleCircleCount++;
                }
                else{
                    break;
                }
            }

             */
        }

        Toast.makeText(clickedVariable.getContext(), String.valueOf(oppositeColorIndex), Toast.LENGTH_SHORT).show();

        if (MainActivity.placementHelp){
            activateProximity(clickedVariable);
        }

        return middleCircleCount == 1;
    }

    //The following method makes the surrounding circles of the acquired circle visible and clickable
    public static void activateProximity(ImageView clickedVariable){
/*
        ArrayList<Boolean> rowActivate = new ArrayList<>();
        int rowIndex = 0;
        int circleIndex = 0;

        for (int i = 1; i < 8; i++){
            rowActivate.add(false);
        }

        for (ArrayList<Character> row : docileCircles){
            if (row.contains('U')){
                rowActivate.set(rowIndex, true);
                break;
            }
            else{
                rowIndex++;
            }
        }

        try{
            for (Boolean bool : rowActivate){
                if (bool){
                    for (ImageView circle : BoardLayout.store_circles.get(rowIndex)){
                        if (circle.isClickable()){
                            circle.setVisibility(View.VISIBLE);
                        }
                    }
                }
            }
        }
        catch (Exception ignored){}

 */
    }

    public static boolean runFramework(ImageView clickedVariable){
        findClickedCircles();

        return checkProximity(clickedVariable);
    }
}
