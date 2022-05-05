package com.example.othello;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.content.res.AppCompatResources;

import java.security.spec.ECField;
import java.util.ArrayList;
import java.util.Objects;

public class Indicators{


    public static int helperCount;

    //The following method makes the surrounding circles of the acquired circle visible and clickable
    public static void activateIndicators(boolean computersMove, boolean checkHelperCount, boolean assessOpponent){

        boolean concludingCircleLeft;
        boolean concludingCircleRight;
        boolean concludingCircleAbove;
        boolean concludingCircleBelow;
        boolean concludingCircleTopLeft;
        boolean concludingCircleTopRight;
        boolean concludingCircleBotLeft;
        boolean concludingCircleBotRight;

        if (!assessOpponent){
            for (ArrayList<ImageView> row : BoardLayout.store_circles){
                for (ImageView imageView : row){
                    if (imageView.isClickable() && imageView.getImageAlpha() == 255){
                        imageView.setImageAlpha(0);
                    }
                }
            }
        }

        ArrayList<ArrayList<ImageView>> circleArrayList;

        if (!assessOpponent){
            circleArrayList = BoardLayout.store_circles;
        }
        else{
            circleArrayList = BoardLayout.store_circles_future;
        }

        for (ArrayList<ImageView> row : circleArrayList){
            for (ImageView image : row){

                int rowIndex = circleArrayList.indexOf(row);
                int colIndex = row.indexOf(image);

                concludingCircleLeft = false;
                concludingCircleRight = false;
                concludingCircleAbove = false;
                concludingCircleBelow = false;
                concludingCircleTopLeft = false;
                concludingCircleTopRight = false;
                concludingCircleBotLeft = false;
                concludingCircleBotRight = false;

                Drawable checkDrawable = circleArrayList.get(rowIndex).get(colIndex).getDrawable();

                if (!image.isClickable() && MainActivity.areDrawablesIdentical(checkDrawable, MainActivity.oppositeColor)){

                    // Left <- (X)

                    if (colIndex != 7){
                        ImageView rightProximity = circleArrayList.get(rowIndex).get(colIndex + 1);
                        int conversionsCountLeft = 0;

                        if (rightProximity.isClickable()){
                            for (int index = colIndex; index >= 0; index--){
                                Drawable imageDrawable = circleArrayList.get(rowIndex).get(index).getDrawable();

                                if (MainActivity.areDrawablesIdentical(imageDrawable, MainActivity.sameColor)) {
                                    //Toast.makeText(activityContext, "LEFT CLICKABLE: " + indexSizeLeft, Toast.LENGTH_SHORT).show();
                                    concludingCircleLeft = true;

                                    //region countConversionsLeft
                                    if (computersMove){
                                        for (int i = colIndex; i >= 0; i--){
                                            if (MainActivity.areDrawablesIdentical(circleArrayList.get(rowIndex).get(i).getDrawable(), MainActivity.oppositeColor)){
                                                conversionsCountLeft++;
                                            }
                                            else{
                                                break;
                                            }
                                        }
                                    }
                                    //endregion

                                    break;
                                }
                                else if (MainActivity.areDrawablesIdentical(imageDrawable, MainActivity.emptyColor)){
                                    break;
                                }
                            }

                            if (concludingCircleLeft){
                                if (!checkHelperCount){
                                    if (!computersMove){
                                        rightProximity.setImageAlpha(255);
                                    }
                                    else{
                                        Computer.possiblePlacements.put(String.valueOf(0), rightProximity);
                                        Computer.successfulConversions.put(String.valueOf(0), conversionsCountLeft);
                                    }
                                }
                                helperCount++;
                            }
                        }
                    }

                    // (X) -> Right

                    if (colIndex != 0){
                        ImageView leftProximity = circleArrayList.get(rowIndex).get(colIndex - 1);
                        int conversionsCountRight = 0;

                        if (leftProximity.isClickable()){
                            for (int index = colIndex; index < 8; index++){
                                Drawable imageDrawable = circleArrayList.get(rowIndex).get(index).getDrawable();

                                if (MainActivity.areDrawablesIdentical(imageDrawable, MainActivity.sameColor)) {
                                    //Toast.makeText(activityContext, "RIGHT CLICKABLE: " + indexSizeLeft, Toast.LENGTH_SHORT).show();
                                    concludingCircleRight = true;

                                    //region countConversionsRight
                                    if (computersMove){
                                        for (int i = colIndex; i < 8; i++){
                                            if (MainActivity.areDrawablesIdentical(circleArrayList.get(rowIndex).get(i).getDrawable(), MainActivity.oppositeColor)){
                                                conversionsCountRight++;
                                            }
                                            else{
                                                break;
                                            }
                                        }
                                    }
                                    //endregion

                                    break;
                                }
                                else if (MainActivity.areDrawablesIdentical(imageDrawable, MainActivity.emptyColor)){
                                    break;
                                }
                            }

                            if (concludingCircleRight){
                                if (!checkHelperCount){
                                    if (!computersMove){
                                        leftProximity.setImageAlpha(255);
                                    }
                                    else{
                                        Computer.possiblePlacements.put(String.valueOf(1), leftProximity);
                                        Computer.successfulConversions.put(String.valueOf(1), conversionsCountRight);
                                    }
                                }
                                helperCount++;
                            }
                        }
                    }

                    // (X) -> Above

                    if (rowIndex != 7){
                        ImageView belowProximity = circleArrayList.get(rowIndex + 1).get(colIndex);
                        int conversionsCountAbove = 0;

                        if (belowProximity.isClickable()){
                            for (int index = rowIndex; index >= 0; index--){
                                Drawable imageDrawable = circleArrayList.get(index).get(colIndex).getDrawable();

                                if (MainActivity.areDrawablesIdentical(imageDrawable, MainActivity.sameColor)){
                                    concludingCircleAbove = true;

                                    //region countConversionsAbove
                                    if (computersMove){
                                        for (int i = rowIndex; i >= 0; i--){
                                            if (MainActivity.areDrawablesIdentical(circleArrayList.get(i).get(colIndex).getDrawable(), MainActivity.oppositeColor)){
                                                conversionsCountAbove++;
                                            }
                                            else{
                                                break;
                                            }
                                        }
                                    }
                                    //endregion

                                    break;
                                }
                                else if (MainActivity.areDrawablesIdentical(imageDrawable, MainActivity.emptyColor)){
                                    break;
                                }
                            }

                            if (concludingCircleAbove){
                                if (!checkHelperCount){
                                    if (!computersMove){
                                        belowProximity.setImageAlpha(255);
                                    }
                                    else{
                                        Computer.possiblePlacements.put(String.valueOf(2), belowProximity);
                                        Computer.successfulConversions.put(String.valueOf(2), conversionsCountAbove);
                                    }
                                }
                                helperCount++;
                            }
                        }
                    }

                    // Below <- (X)

                    if (rowIndex != 0){
                        ImageView aboveProximity = circleArrayList.get(rowIndex - 1).get(colIndex);
                        int conversionsCountBelow = 0;

                        if (aboveProximity.isClickable()){
                            for (int index = rowIndex; index < 8; index++){

                                Drawable imageDrawable = circleArrayList.get(index).get(colIndex).getDrawable();

                                if (MainActivity.areDrawablesIdentical(imageDrawable, MainActivity.sameColor)) {
                                    concludingCircleBelow = true;

                                    //region countConversionsBelow
                                    if (computersMove){
                                        for (int i = rowIndex; i < 8; i++){
                                            if (MainActivity.areDrawablesIdentical(circleArrayList.get(i).get(colIndex).getDrawable(), MainActivity.oppositeColor)){
                                                conversionsCountBelow++;
                                            }
                                            else{
                                                break;
                                            }
                                        }
                                    }
                                    //endregion

                                    break;
                                }
                                else if (MainActivity.areDrawablesIdentical(imageDrawable, MainActivity.emptyColor)){
                                    break;
                                }
                            }

                            if (concludingCircleBelow){
                                if (!checkHelperCount){
                                    if (!computersMove){
                                        aboveProximity.setImageAlpha(255);
                                    }
                                    else{
                                        Computer.possiblePlacements.put(String.valueOf(3), aboveProximity);
                                        Computer.successfulConversions.put(String.valueOf(3), conversionsCountBelow);
                                    }
                                }
                                helperCount++;
                            }
                        }
                    }

                    //Top Left

                    try{
                        if (rowIndex != 0 && rowIndex != 7 && colIndex != 0 && colIndex != 7){
                            ImageView botRightProximity = circleArrayList.get(rowIndex + 1).get(colIndex + 1);
                            int conversionsCountTopLeft = 0;

                            if (botRightProximity.isClickable()){

                                int colIndexAlter = colIndex - 1;

                                for (int index = rowIndex - 1; index >= 0; index--){

                                    Drawable imageDrawable = circleArrayList.get(index).get(colIndexAlter).getDrawable();

                                    if (MainActivity.areDrawablesIdentical(imageDrawable, MainActivity.sameColor)){
                                        concludingCircleTopLeft = true;

                                        //region countConversionsTopLeft
                                        for (int index_append = rowIndex; index_append >= 0; index_append--){

                                            Drawable imageDrawable_append = circleArrayList.get(index_append).get(colIndex).getDrawable();

                                            if (MainActivity.areDrawablesIdentical(imageDrawable_append, MainActivity.oppositeColor)){
                                                conversionsCountTopLeft++;
                                                break;
                                            }
                                            else if (MainActivity.areDrawablesIdentical(imageDrawable_append, MainActivity.sameColor)){
                                                break;
                                            }
                                            else{
                                                colIndexAlter--;
                                            }
                                        }
                                        //endregion

                                        break;
                                    }
                                    else if (MainActivity.areDrawablesIdentical(imageDrawable, MainActivity.emptyColor)){
                                        break;
                                    }
                                    else{
                                        colIndexAlter--;
                                    }
                                }

                                if (concludingCircleTopLeft){
                                    if (!checkHelperCount){
                                        if (!computersMove){
                                            botRightProximity.setImageAlpha(255);
                                        }
                                        else{
                                            Computer.possiblePlacements.put(String.valueOf(4), botRightProximity);
                                            Computer.successfulConversions.put(String.valueOf(4), conversionsCountTopLeft);
                                        }
                                    }
                                    helperCount++;
                                }
                            }
                        }
                    }
                    catch (Exception ignored){}

                    //Top Right

                    try{
                        if (rowIndex != 0 && rowIndex != 7 && colIndex != 0 && colIndex != 7){
                            ImageView botLeftProximity = circleArrayList.get(rowIndex + 1).get(colIndex - 1);
                            int conversionsCountTopRight = 0;

                            if (botLeftProximity.isClickable()){

                                int colIndexAlter = colIndex + 1;

                                for (int index = rowIndex - 1; index >= 0; index--){

                                    Drawable imageDrawable = circleArrayList.get(index).get(colIndexAlter).getDrawable();

                                    if (MainActivity.areDrawablesIdentical(imageDrawable, MainActivity.sameColor)){
                                        concludingCircleTopRight = true;

                                        //region countConversionsTopRight
                                        for (int index_append = rowIndex; index_append >= 0; index_append--){

                                            Drawable imageDrawable_append = circleArrayList.get(index_append).get(colIndex).getDrawable();

                                            if (MainActivity.areDrawablesIdentical(imageDrawable_append, MainActivity.oppositeColor)){
                                                conversionsCountTopRight++;
                                                break;
                                            }
                                            else if (MainActivity.areDrawablesIdentical(imageDrawable_append, MainActivity.sameColor)){
                                                break;
                                            }
                                            else{
                                                colIndexAlter++;
                                            }
                                        }
                                        //endregion

                                        break;
                                    }
                                    else if (MainActivity.areDrawablesIdentical(imageDrawable, MainActivity.emptyColor)){
                                        break;
                                    }
                                    else{
                                        colIndexAlter++;
                                    }
                                }

                                if (concludingCircleTopRight){

                                    if (!checkHelperCount){
                                        if (!computersMove){
                                            botLeftProximity.setImageAlpha(255);
                                        }
                                        else{
                                            Computer.possiblePlacements.put(String.valueOf(5), botLeftProximity);
                                            Computer.successfulConversions.put(String.valueOf(5), conversionsCountTopRight);
                                        }
                                    }
                                    helperCount++;
                                }
                            }
                        }
                    }
                    catch (Exception ignored){}

                    //Bot Left

                    try{
                        if (rowIndex != 0 && rowIndex != 7 && colIndex != 0 && colIndex != 7){
                            ImageView topRightProximity = circleArrayList.get(rowIndex - 1).get(colIndex + 1);
                            int conversionsCountBotLeft = 0;

                            if (topRightProximity.isClickable()){

                                int colIndexAlter = colIndex - 1;

                                for (int index = rowIndex + 1; index < 8; index++){

                                    Drawable imageDrawable = circleArrayList.get(index).get(colIndexAlter).getDrawable();

                                    if (MainActivity.areDrawablesIdentical(imageDrawable, MainActivity.sameColor)){
                                        concludingCircleBotLeft = true;

                                        //region countConversionsBotLeft
                                        for (int index_append = rowIndex; index_append < 8; index_append++){

                                            Drawable imageDrawable_append = circleArrayList.get(index_append).get(colIndex).getDrawable();

                                            if (MainActivity.areDrawablesIdentical(imageDrawable_append, MainActivity.oppositeColor)){
                                                conversionsCountBotLeft++;
                                                break;
                                            }
                                            else if (MainActivity.areDrawablesIdentical(imageDrawable_append, MainActivity.sameColor)){
                                                break;
                                            }
                                            else{
                                                colIndexAlter--;
                                            }
                                        }
                                        //endregion

                                        break;
                                    }
                                    else if (MainActivity.areDrawablesIdentical(imageDrawable, MainActivity.emptyColor)){
                                        break;
                                    }
                                    else{
                                        colIndexAlter--;
                                    }
                                }

                                if (concludingCircleBotLeft){
                                    if (!checkHelperCount){
                                        if (!computersMove){
                                            topRightProximity.setImageAlpha(255);
                                        }
                                        else{
                                            Computer.possiblePlacements.put(String.valueOf(6), topRightProximity);
                                            Computer.successfulConversions.put(String.valueOf(6), conversionsCountBotLeft);
                                        }
                                    }
                                    helperCount++;
                                }
                            }
                        }
                    }
                    catch (Exception ignored){}

                    //Bot Right

                    try{
                        if (rowIndex != 0 && rowIndex != 7 && colIndex != 0 && colIndex != 7){
                            ImageView topLeftProximity = circleArrayList.get(rowIndex - 1).get(colIndex - 1);
                            int conversionsCountBotRight = 0;

                            if (topLeftProximity.isClickable()){

                                int colIndexAlter = colIndex + 1;

                                for (int index = rowIndex + 1; index < 8; index++){

                                    Drawable imageDrawable = circleArrayList.get(index).get(colIndexAlter).getDrawable();

                                    if (MainActivity.areDrawablesIdentical(imageDrawable, MainActivity.sameColor)){
                                        concludingCircleBotRight = true;

                                        //region countConversionsBotRight
                                        for (int index_append = rowIndex; index_append < 8; index_append++){

                                            Drawable imageDrawable_append = circleArrayList.get(index_append).get(colIndex).getDrawable();

                                            if (MainActivity.areDrawablesIdentical(imageDrawable_append, MainActivity.oppositeColor)){
                                                conversionsCountBotRight++;
                                                break;
                                            }
                                            else if (MainActivity.areDrawablesIdentical(imageDrawable_append, MainActivity.sameColor)){
                                                break;
                                            }
                                            else{
                                                colIndexAlter++;
                                            }
                                        }
                                        //endregion

                                        break;
                                    }
                                    else if (MainActivity.areDrawablesIdentical(imageDrawable, MainActivity.emptyColor)){
                                        break;
                                    }
                                    else{
                                        colIndexAlter++;
                                    }
                                }

                                if (concludingCircleBotRight){
                                    if (!checkHelperCount){
                                        if (!computersMove){
                                            topLeftProximity.setImageAlpha(255);
                                        }
                                        else{
                                            Computer.possiblePlacements.put(String.valueOf(7), topLeftProximity);
                                            Computer.successfulConversions.put(String.valueOf(7), conversionsCountBotRight);
                                        }
                                    }
                                    helperCount++;
                                }
                            }
                        }
                    }
                    catch (Exception ignored){}
                }
            }
        }

        if (assessOpponent){
            MainActivity.colorWhite = !MainActivity.colorWhite;
            BoardLayout.applyColor();
        }
    }
}

