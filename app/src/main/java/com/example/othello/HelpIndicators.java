package com.example.othello;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.provider.ContactsContract;
import android.widget.ImageView;

import androidx.appcompat.content.res.AppCompatResources;

import java.util.ArrayList;
import java.util.Objects;

public class HelpIndicators {

    //The following method makes the surrounding circles of the acquired circle visible and clickable
    public static void activateIndicators(ImageView clickedVariable){

        boolean concludingCircleRight;
        boolean concludingCircleLeft;
        boolean concludingCircleAbove;
        boolean concludingCircleBelow;
        boolean concludingCircleTopRight;
        boolean concludingCircleTopLeft;
        boolean concludingCircleBotRight;
        boolean concludingCircleBotLeft;

        for (ArrayList<ImageView> row : BoardLayout.store_circles){
            for (ImageView image : row){
                if (image.isClickable()){
                    image.setImageAlpha(0);
                }
            }
        }

        Drawable oppositeColor;
        Drawable sameColor;
        Drawable emptyColor;

        emptyColor = Objects.requireNonNull(AppCompatResources.getDrawable(clickedVariable.getContext(), R.drawable.transparent_circle78));

        if (MainActivity.colorWhite){
            sameColor = Objects.requireNonNull(AppCompatResources.getDrawable(clickedVariable.getContext(), R.drawable.white_circle78));
            oppositeColor = Objects.requireNonNull(AppCompatResources.getDrawable(clickedVariable.getContext(), R.drawable.black_circle78));
        }
        else{
            sameColor = Objects.requireNonNull(AppCompatResources.getDrawable(clickedVariable.getContext(), R.drawable.black_circle78));
            oppositeColor = Objects.requireNonNull(AppCompatResources.getDrawable(clickedVariable.getContext(), R.drawable.white_circle78));
        }


        for (ArrayList<ImageView> row : BoardLayout.store_circles){
            for (ImageView image : row){

                int rowIndex = BoardLayout.store_circles.indexOf(row);
                int colIndex = row.indexOf(image);

                concludingCircleRight = false;
                concludingCircleLeft = false;
                concludingCircleAbove = false;
                concludingCircleBelow = false;
                concludingCircleTopRight = false;
                concludingCircleTopLeft = false;
                concludingCircleBotRight = false;
                concludingCircleBotLeft = false;

                Drawable checkDrawable = BoardLayout.store_circles.get(rowIndex).get(colIndex).getDrawable();

                if (!image.isClickable() && MainActivity.areDrawablesIdentical(checkDrawable, oppositeColor)){
/*
                    BoardLayout.store_circles.get(rowIndex).get(colIndex).animate().alpha(0.5f).setDuration(2000).withEndAction(() -> {
                        BoardLayout.store_circles.get(rowIndex).get(colIndex).animate().alpha(1);
                    });
 */

                    // Left <- (X)

                    if (colIndex != 7){
                        ImageView checkLeft = BoardLayout.store_circles.get(rowIndex).get(colIndex + 1);

                        if (checkLeft.isClickable()){
                            try{
                                for (int index = colIndex; index >= 0; index--){
                                    Drawable imageDrawable = BoardLayout.store_circles.get(rowIndex).get(index).getDrawable();

                                    if (MainActivity.areDrawablesIdentical(imageDrawable, sameColor)) {
                                        concludingCircleLeft = true;
                                        break;
                                    }
                                    else if (MainActivity.areDrawablesIdentical(imageDrawable, emptyColor)){
                                        break;
                                    }
                                }
                            }
                            catch (Exception ignored){}

                            if (concludingCircleLeft){
                                BoardLayout.store_circles.get(rowIndex).get(colIndex + 1).setImageAlpha(255);
                            }
                        }
                    }

                    // (X) -> Right

                    if (colIndex != 0){
                        ImageView checkRight = BoardLayout.store_circles.get(rowIndex).get(colIndex - 1);

                        if (checkRight.isClickable()){
                            try{
                                for (int index = colIndex; index < 8; index++){
                                    Drawable imageDrawable = BoardLayout.store_circles.get(rowIndex).get(index).getDrawable();

                                    if (MainActivity.areDrawablesIdentical(imageDrawable, sameColor)) {
                                        concludingCircleRight = true;
                                        break;
                                    }
                                    else if (MainActivity.areDrawablesIdentical(imageDrawable, emptyColor)){
                                        break;
                                    }
                                }
                            }
                            catch (Exception ignored){}

                            if (concludingCircleRight){
                                BoardLayout.store_circles.get(rowIndex).get(colIndex - 1).setImageAlpha(255);
                            }
                        }
                    }

                    // (X) -> Above

                    if (rowIndex != 7){
                        ImageView checkAbove = BoardLayout.store_circles.get(rowIndex + 1).get(colIndex);

                        if (checkAbove.isClickable()){
                            try{
                                for (int index = rowIndex; index >= 0; index--){
                                    Drawable imageDrawable = BoardLayout.store_circles.get(index).get(colIndex).getDrawable();

                                    if (MainActivity.areDrawablesIdentical(imageDrawable, sameColor)){
                                        concludingCircleAbove = true;
                                        break;
                                    }
                                    else if (MainActivity.areDrawablesIdentical(imageDrawable, emptyColor)){
                                        break;
                                    }
                                }
                            }
                            catch (Exception ignored){}

                            if (concludingCircleAbove){
                                BoardLayout.store_circles.get(rowIndex + 1).get(colIndex).setImageAlpha(255);
                            }
                        }
                    }

                    // Below <- (X)

                    if (rowIndex != 0){
                        ImageView checkBelow = BoardLayout.store_circles.get(rowIndex - 1).get(colIndex);

                        if (checkBelow.isClickable()){
                            try{
                                for (int index = rowIndex; index < 8; index++){

                                    Drawable imageDrawable = BoardLayout.store_circles.get(index).get(colIndex).getDrawable();

                                    if (MainActivity.areDrawablesIdentical(imageDrawable, sameColor)) {
                                        concludingCircleBelow = true;
                                        break;
                                    }
                                    else if (MainActivity.areDrawablesIdentical(imageDrawable, emptyColor)){
                                        break;
                                    }
                                }
                            }
                            catch (Exception ignored){}

                            if (concludingCircleBelow){
                                BoardLayout.store_circles.get(rowIndex - 1).get(colIndex).setImageAlpha(255);
                            }
                        }
                    }

                    //Top Left

                    if (rowIndex != 7 && colIndex != 7){
                        ImageView checkTopLeft = BoardLayout.store_circles.get(rowIndex + 1).get(colIndex + 1);

                        if (checkTopLeft.isClickable()){

                            int colIndexAlter = colIndex - 1;

                            try{
                                for (int index = rowIndex - 1; index >= 0; index--){

                                    Drawable imageDrawable = BoardLayout.store_circles.get(index).get(colIndexAlter).getDrawable();

                                    if (MainActivity.areDrawablesIdentical(imageDrawable, sameColor)){
                                        concludingCircleTopLeft = true;
                                        break;
                                    }
                                    else if (MainActivity.areDrawablesIdentical(imageDrawable, emptyColor)){
                                        break;
                                    }
                                    else{
                                        colIndexAlter--;
                                    }
                                }
                            }
                            catch (Exception ignored){}

                            if (concludingCircleTopLeft){
                                BoardLayout.store_circles.get(rowIndex + 1).get(colIndex + 1).setImageAlpha(255);
                            }
                        }
                    }

                    //Top Right

                    if (rowIndex != 7 && colIndex != 0){
                        ImageView checkTopRight = BoardLayout.store_circles.get(rowIndex + 1).get(colIndex - 1);

                        if (checkTopRight.isClickable()){

                            int colIndexAlter = colIndex + 1;

                            try{
                                for (int index = rowIndex - 1; index >= 0; index--){

                                    Drawable imageDrawable = BoardLayout.store_circles.get(index).get(colIndexAlter).getDrawable();

                                    if (MainActivity.areDrawablesIdentical(imageDrawable, sameColor)){
                                        concludingCircleTopRight = true;
                                        break;
                                    }
                                    else if (MainActivity.areDrawablesIdentical(imageDrawable, emptyColor)){
                                        break;
                                    }
                                    else{
                                        colIndexAlter++;
                                    }
                                }
                            }
                            catch (Exception ignored){}

                            if (concludingCircleTopRight){
                                BoardLayout.store_circles.get(rowIndex + 1).get(colIndex - 1).setImageAlpha(255);
                            }
                        }
                    }

                    //Bot Left

                    if (rowIndex != 0 && colIndex != 7){
                        ImageView checkBotLeft = BoardLayout.store_circles.get(rowIndex - 1).get(colIndex + 1);

                        if (checkBotLeft.isClickable()){

                            int colIndexAlter = colIndex - 1;

                            try{
                                for (int index = rowIndex + 1; index < 8; index++){

                                    Drawable imageDrawable = BoardLayout.store_circles.get(index).get(colIndexAlter).getDrawable();

                                    if (MainActivity.areDrawablesIdentical(imageDrawable, sameColor)){
                                        concludingCircleBotLeft = true;
                                        break;
                                    }
                                    else if (MainActivity.areDrawablesIdentical(imageDrawable, emptyColor)){
                                        break;
                                    }
                                    else{
                                        colIndexAlter--;
                                    }
                                }
                            }
                            catch (Exception ignored){}

                            if (concludingCircleBotLeft){
                                BoardLayout.store_circles.get(rowIndex - 1).get(colIndex + 1).setImageAlpha(255);
                            }
                        }
                    }

                    //Bot Right

                    if (rowIndex != 0 && colIndex != 0){
                        ImageView checkBotRight = BoardLayout.store_circles.get(rowIndex - 1).get(colIndex - 1);

                        if (checkBotRight.isClickable()){

                            int colIndexAlter = colIndex + 1;

                            try{
                                for (int index = rowIndex + 1; index < 8; index++){

                                    Drawable imageDrawable = BoardLayout.store_circles.get(index).get(colIndexAlter).getDrawable();

                                    if (MainActivity.areDrawablesIdentical(imageDrawable, sameColor)){
                                        concludingCircleBotRight = true;
                                        break;
                                    }
                                    else if (MainActivity.areDrawablesIdentical(imageDrawable, emptyColor)){
                                        break;
                                    }
                                    else{
                                        colIndexAlter++;
                                    }
                                }
                            }
                            catch (Exception ignored){}

                            if (concludingCircleBotRight){
                                BoardLayout.store_circles.get(rowIndex - 1).get(colIndex - 1).setImageAlpha(255);
                            }
                        }
                    }
                }
            }
        }
    }
}
