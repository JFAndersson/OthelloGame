package com.example.othello;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Objects;

public class Framework {

    //region declarations


    //endregion

    public static void setProximity(ImageView clickedVariable, boolean difficulty2) {

        int rowIndex;
        int colIndex;

        int rowIndexCount = 0;
        boolean clickedCircle = false;

        ArrayList<ArrayList<ImageView>> circleArrayList;

        if (!difficulty2){
            circleArrayList = BoardLayout.store_circles;
        }
        else{
            circleArrayList = BoardLayout.store_circles_future;
        }


        for (ArrayList<ImageView> row : circleArrayList){
            for (ImageView imageView : row){
                if (clickedVariable.equals(imageView)){
                    clickedCircle = true;
                    break;
                }
            }
            if (!clickedCircle){
                rowIndexCount++;
            }
        }

        rowIndex = rowIndexCount;
        colIndex = circleArrayList.get(rowIndex).indexOf(clickedVariable);

        boolean proximityCircleRight = false;
        boolean proximityCircleLeft = false;
        boolean proximityCircleAbove = false;
        boolean proximityCircleBelow = false;
        boolean proximityCircleTopRight = false;
        boolean proximityCircleTopLeft = false;
        boolean proximityCircleBotRight = false;
        boolean proximityCircleBotLeft = false;

        boolean concludingCircleRight = false;
        boolean concludingCircleLeft = false;
        boolean concludingCircleAbove = false;
        boolean concludingCircleBelow = false;
        boolean concludingCircleTopRight = false;
        boolean concludingCircleTopLeft = false;
        boolean concludingCircleBotRight = false;
        boolean concludingCircleBotLeft = false;

        boolean clickedCircleFound;

        //region checkLeftProximity

        //Checks the reverse direction for the below direction parameters...

        if (colIndex > 0){
            if (MainActivity.areDrawablesIdentical(circleArrayList.get(rowIndex).get(colIndex - 1).getDrawable(), MainActivity.oppositeColor)){
                //Toast.makeText(clickedVariable.getContext(), "Circle Found To The Left", Toast.LENGTH_SHORT).show();
                proximityCircleLeft = true;
            }
        }

        if (proximityCircleLeft){

            try{
                for (int index = colIndex - 1; index >= 0; index--){

                    Drawable imageDrawable = circleArrayList.get(rowIndex).get(index).getDrawable();

                    if (MainActivity.areDrawablesIdentical(imageDrawable, MainActivity.sameColor)) {
                        concludingCircleLeft = true;
                        break;
                    }
                    else if (MainActivity.areDrawablesIdentical(imageDrawable, MainActivity.emptyColor)){
                        break;
                    }
                }
            }
            catch (Exception ignored){}

            //Changes the middle-circles to the same color if there is a circle of the opposite color at the end of the row
            if (concludingCircleLeft){
                try{
                    for (int index = colIndex - 1; index >= 0; index--){
                        Drawable imageDrawable = circleArrayList.get(rowIndex).get(index).getDrawable();

                        if (MainActivity.areDrawablesIdentical(imageDrawable, MainActivity.oppositeColor)){
                            if (MainActivity.colorWhite){
                                circleArrayList.get(rowIndex).get(index)
                                        .setImageDrawable(AppCompatResources.getDrawable(clickedVariable.getContext(), R.drawable.white_circle78));
                            }
                            else{
                                circleArrayList.get(rowIndex).get(index)
                                        .setImageDrawable(AppCompatResources.getDrawable(clickedVariable.getContext(), R.drawable.black_circle78));
                            }
                        }
                        else{
                            break;
                        }
                    }
                }
                catch (Exception ignored){}
            }
        }

        //endregion

        //region checkRightProximity

        //Checks whether or not the user has placed their circle left of a circle of the opposite color
        if (colIndex < 7){
            if (MainActivity.areDrawablesIdentical(circleArrayList.get(rowIndex).get(colIndex + 1).getDrawable(), MainActivity.oppositeColor)){
                //Toast.makeText(clickedVariable.getContext(), "Circle Found To The Right", Toast.LENGTH_SHORT).show();
                proximityCircleRight = true;
            }
        }

        //Checks if there are multiple circles of the opposite color following the clicked circle, and if there is a circle of the same color at the end of that row
        if (proximityCircleRight){

            try{
                for (int index = colIndex + 1; index < 8; index++){

                    Drawable imageDrawable = circleArrayList.get(rowIndex).get(index).getDrawable();

                    if (MainActivity.areDrawablesIdentical(imageDrawable, MainActivity.sameColor)) {
                        concludingCircleRight = true;
                        break;
                    }
                    else if (MainActivity.areDrawablesIdentical(imageDrawable, MainActivity.emptyColor)){
                        break;
                    }
                }
            }
            catch (Exception ignored){}

            //Changes the middle-circles to the same color if there is a circle of the opposite color at the end of the row
            if (concludingCircleRight){

                clickedCircleFound = false;

                try{
                    for (ImageView imageView : circleArrayList.get(rowIndex)){

                        if (clickedCircleFound){
                            Drawable imageDrawable = imageView.getDrawable();

                            if (MainActivity.areDrawablesIdentical(imageDrawable, MainActivity.oppositeColor)){
                                if (MainActivity.colorWhite){
                                    imageView.setImageDrawable(AppCompatResources.getDrawable(clickedVariable.getContext(), R.drawable.white_circle78));
                                }
                                else{
                                    imageView.setImageDrawable(AppCompatResources.getDrawable(clickedVariable.getContext(), R.drawable.black_circle78));
                                }
                            }
                            else{
                                break;
                            }
                        }

                        if (imageView.equals(clickedVariable)){
                            clickedCircleFound = true;
                        }
                    }
                }
                catch (Exception ignored){}
            }

        }

        //endregion

        //region checkAboveProximity

        //Checks whether or not the user has placed their circle below a circle of the opposite color
        if (rowIndex > 0){
            if (MainActivity.areDrawablesIdentical(circleArrayList.get(rowIndex - 1).get(colIndex).getDrawable(), MainActivity.oppositeColor)){
                //Toast.makeText(clickedVariable.getContext(), "Circle Found Above", Toast.LENGTH_SHORT).show();
                proximityCircleAbove = true;
            }
        }

        if (proximityCircleAbove){

            try{
                for (int index = rowIndex - 1; index >= 0; index--){

                    Drawable imageDrawable = circleArrayList.get(index).get(colIndex).getDrawable();

                    if (MainActivity.areDrawablesIdentical(imageDrawable, MainActivity.sameColor)){
                        concludingCircleAbove = true;
                        break;
                    }
                    else if (MainActivity.areDrawablesIdentical(imageDrawable, MainActivity.emptyColor)){
                        break;
                    }
                }
            }
            catch (Exception ignored){}

            //Changes the middle-circles to the same color if there is a circle of the opposite color at the end of the row
            if (concludingCircleAbove){

                try{
                    for (int index = rowIndex - 1; index >= 0; index--){

                        Drawable imageDrawable = circleArrayList.get(index).get(colIndex).getDrawable();

                        if (MainActivity.areDrawablesIdentical(imageDrawable, MainActivity.oppositeColor)){

                            if (MainActivity.colorWhite){
                                circleArrayList.get(index).get(colIndex)
                                        .setImageDrawable(AppCompatResources.getDrawable(clickedVariable.getContext(), R.drawable.white_circle78));
                            }
                            else{
                                circleArrayList.get(index).get(colIndex)
                                        .setImageDrawable(AppCompatResources.getDrawable(clickedVariable.getContext(), R.drawable.black_circle78));
                            }
                        }
                        else{
                            break;
                        }
                    }
                }
                catch (Exception ignored){}
            }
        }

        //endregion

        //region checkBelowProximity

        //Checks whether or not the user has placed their circle above a circle of the opposite color

        if (rowIndex < 7){
            if (MainActivity.areDrawablesIdentical(circleArrayList.get(rowIndex + 1).get(colIndex).getDrawable(), MainActivity.oppositeColor)){
                //Toast.makeText(clickedVariable.getContext(), "Circle Found Below", Toast.LENGTH_SHORT).show();
                proximityCircleBelow = true;
            }
        }

        if (proximityCircleBelow){
            try{
                for (int index = rowIndex + 1; index < 8; index++){

                    Drawable imageDrawable = circleArrayList.get(index).get(colIndex).getDrawable();

                    if (MainActivity.areDrawablesIdentical(imageDrawable, MainActivity.sameColor)){
                        concludingCircleBelow = true;
                        break;
                    }
                    else if (MainActivity.areDrawablesIdentical(imageDrawable, MainActivity.emptyColor)){
                        break;
                    }
                }
            }
            catch (Exception ignored){}

            //Changes the middle-circles to the same color if there is a circle of the opposite color at the end of the row
            if (concludingCircleBelow){

                try{
                    for (int index = rowIndex + 1; index < 8; index++){

                        Drawable imageDrawable = circleArrayList.get(index).get(colIndex).getDrawable();

                        if (MainActivity.areDrawablesIdentical(imageDrawable, MainActivity.oppositeColor)){

                            if (MainActivity.colorWhite){
                                circleArrayList.get(index).get(colIndex)
                                        .setImageDrawable(AppCompatResources.getDrawable(clickedVariable.getContext(), R.drawable.white_circle78));
                            }
                            else{
                                circleArrayList.get(index).get(colIndex)
                                        .setImageDrawable(AppCompatResources.getDrawable(clickedVariable.getContext(), R.drawable.black_circle78));
                            }
                        }
                        else{
                            break;
                        }
                    }
                }
                catch (Exception ignored){}
            }
        }

        //endregion


        //region initializeDiagonalParameters

        int colIndexAlter;

        Drawable topLeftImageDrawable;

        if (rowIndex > 0 && colIndex > 0){
            topLeftImageDrawable = circleArrayList.get(rowIndex - 1).get(colIndex - 1).getDrawable();
            if (MainActivity.areDrawablesIdentical(topLeftImageDrawable, MainActivity.oppositeColor)){
                //Toast.makeText(clickedVariable.getContext(), "Circle Found In The Top Left", Toast.LENGTH_SHORT).show();
                proximityCircleTopLeft = true;
            }
        }

        Drawable topRightImageDrawable;

        if (rowIndex > 0 && colIndex < 7){
            topRightImageDrawable = circleArrayList.get(rowIndex - 1).get(colIndex + 1).getDrawable();
            if (MainActivity.areDrawablesIdentical(topRightImageDrawable, MainActivity.oppositeColor)){
                //Toast.makeText(clickedVariable.getContext(), "Circle Found In The Top Right", Toast.LENGTH_SHORT).show();
                proximityCircleTopRight = true;
            }
        }

        Drawable botLeftImageDrawable;

        if (rowIndex < 7 && colIndex > 0){
            botLeftImageDrawable = circleArrayList.get(rowIndex + 1).get(colIndex - 1).getDrawable();
            if (MainActivity.areDrawablesIdentical(botLeftImageDrawable, MainActivity.oppositeColor)){
                //Toast.makeText(clickedVariable.getContext(), "Circle Found In The Bot Left", Toast.LENGTH_SHORT).show();
                proximityCircleBotLeft = true;
            }
        }

        Drawable botRightImageDrawable;

        if (rowIndex < 7 && colIndex < 7){
            botRightImageDrawable = circleArrayList.get(rowIndex + 1).get(colIndex + 1).getDrawable();
            if (MainActivity.areDrawablesIdentical(botRightImageDrawable, MainActivity.oppositeColor)){
                //Toast.makeText(clickedVariable.getContext(), "Circle Found In The Bot Right", Toast.LENGTH_SHORT).show();
                proximityCircleBotRight = true;
            }
        }

        //endregion

        //region checkProximityTopLeft

        if (proximityCircleTopLeft){

            colIndexAlter = colIndex - 1;

            try{
                for (int index = rowIndex - 1; index >= 0; index--){

                    Drawable imageDrawable = circleArrayList.get(index).get(colIndexAlter).getDrawable();

                    if (MainActivity.areDrawablesIdentical(imageDrawable, MainActivity.sameColor)){
                        concludingCircleTopLeft = true;
                        break;
                    }
                    else if (MainActivity.areDrawablesIdentical(imageDrawable, MainActivity.emptyColor)){
                        break;
                    }
                    else{
                        colIndexAlter--;
                    }
                }
            }
            catch (Exception ignored){}

            if (concludingCircleTopLeft){

                colIndexAlter = colIndex - 1;

                try{
                    for (int index = rowIndex - 1; index >= 0; index--){

                        Drawable imageDrawable = circleArrayList.get(index).get(colIndexAlter).getDrawable();

                        if (MainActivity.areDrawablesIdentical(imageDrawable, MainActivity.oppositeColor)){
                            if (MainActivity.colorWhite){
                                circleArrayList.get(index).get(colIndexAlter)
                                        .setImageDrawable(AppCompatResources.getDrawable(clickedVariable.getContext(), R.drawable.white_circle78));
                            }
                            else{
                                circleArrayList.get(index).get(colIndexAlter)
                                        .setImageDrawable(AppCompatResources.getDrawable(clickedVariable.getContext(), R.drawable.black_circle78));
                            }
                        }
                        else{
                            break;
                        }

                        colIndexAlter--;
                    }
                }
                catch (Exception ignored){}
            }
        }

        //endregion

        //region checkProximityTopRight

        if (proximityCircleTopRight){

            colIndexAlter = colIndex + 1;

            try{
                for (int index = rowIndex - 1; index >= 0; index--){

                    Drawable imageDrawable = circleArrayList.get(index).get(colIndexAlter).getDrawable();

                    if (MainActivity.areDrawablesIdentical(imageDrawable, MainActivity.sameColor)){
                        concludingCircleTopRight = true;
                        break;
                    }
                    else if (MainActivity.areDrawablesIdentical(imageDrawable, MainActivity.emptyColor)){
                        break;
                    }
                    else{
                        colIndexAlter++;
                    }
                }
            }
            catch (Exception ignored){}

            if (concludingCircleTopRight){

                colIndexAlter = colIndex + 1;

                try{
                    for (int index = rowIndex - 1; index >= 0; index--){

                        Drawable imageDrawable = circleArrayList.get(index).get(colIndexAlter).getDrawable();

                        if (MainActivity.areDrawablesIdentical(imageDrawable, MainActivity.oppositeColor)){
                            if (MainActivity.colorWhite){
                                circleArrayList.get(index).get(colIndexAlter)
                                        .setImageDrawable(AppCompatResources.getDrawable(clickedVariable.getContext(), R.drawable.white_circle78));
                            }
                            else{
                                circleArrayList.get(index).get(colIndexAlter)
                                        .setImageDrawable(AppCompatResources.getDrawable(clickedVariable.getContext(), R.drawable.black_circle78));
                            }
                            colIndexAlter++;
                        }
                        else{
                            break;
                        }
                    }
                }
                catch (Exception ignored){}
            }
        }

        //endregion

        //region checkProximityBotLeft

        if (colIndex > 0 && rowIndex < 7){
            if (proximityCircleBotLeft){

                colIndexAlter = colIndex - 1;

                try{
                    for (int index = rowIndex + 1; index < 8; index++){

                        Drawable imageDrawable = circleArrayList.get(index).get(colIndexAlter).getDrawable();

                        if (MainActivity.areDrawablesIdentical(imageDrawable, MainActivity.sameColor)){
                            concludingCircleBotLeft = true;
                            break;
                        }
                        else if (MainActivity.areDrawablesIdentical(imageDrawable, MainActivity.emptyColor)){
                            break;
                        }
                        else{
                            colIndexAlter--;
                        }
                    }
                }
                catch (Exception ignored){}

                if (concludingCircleBotLeft){

                    colIndexAlter = colIndex - 1;

                    try{
                        for (int index = rowIndex + 1; index < 8; index++){

                            Drawable imageDrawable = circleArrayList.get(index).get(colIndexAlter).getDrawable();

                            if (MainActivity.areDrawablesIdentical(imageDrawable, MainActivity.oppositeColor)){
                                if (MainActivity.colorWhite){
                                    circleArrayList.get(index).get(colIndexAlter)
                                            .setImageDrawable(AppCompatResources.getDrawable(clickedVariable.getContext(), R.drawable.white_circle78));
                                }
                                else{
                                    circleArrayList.get(index).get(colIndexAlter)
                                            .setImageDrawable(AppCompatResources.getDrawable(clickedVariable.getContext(), R.drawable.black_circle78));
                                }
                                colIndexAlter--;
                            }
                            else{
                                break;
                            }
                        }
                    }
                    catch (Exception ignored){}
                }
            }
        }

        //endregion

        //region checkProximityBotRight

        if (proximityCircleBotRight){

            colIndexAlter = colIndex + 1;

            try{
                for (int index = rowIndex + 1; index < 8; index++){

                    Drawable imageDrawable = circleArrayList.get(index).get(colIndexAlter).getDrawable();

                    if (MainActivity.areDrawablesIdentical(imageDrawable, MainActivity.sameColor)){
                        concludingCircleBotRight = true;
                        break;
                    }
                    else if (MainActivity.areDrawablesIdentical(imageDrawable, MainActivity.emptyColor)){
                        break;
                    }
                    else{
                        colIndexAlter++;
                    }
                }
            }
            catch (Exception ignored){}

            if (concludingCircleBotRight){

                colIndexAlter = colIndex + 1;

                try{
                    for (int index = rowIndex + 1; index < 8; index++){

                        Drawable imageDrawable = circleArrayList.get(index).get(colIndexAlter).getDrawable();

                        if (MainActivity.areDrawablesIdentical(imageDrawable, MainActivity.oppositeColor)){
                            if (MainActivity.colorWhite){
                                circleArrayList.get(index).get(colIndexAlter)
                                        .setImageDrawable(AppCompatResources.getDrawable(clickedVariable.getContext(), R.drawable.white_circle78));
                            }
                            else{
                                circleArrayList.get(index).get(colIndexAlter)
                                        .setImageDrawable(AppCompatResources.getDrawable(clickedVariable.getContext(), R.drawable.black_circle78));
                            }
                            colIndexAlter++;
                        }
                        else{
                            break;
                        }
                    }
                }
                catch (Exception ignored){}
            }
        }

        //endregion

        BoardLayout.finalConversionResult = false;

        if (concludingCircleLeft || concludingCircleRight || concludingCircleAbove || concludingCircleBelow ||
                concludingCircleTopLeft || concludingCircleTopRight || concludingCircleBotLeft || concludingCircleBotRight){

            clickedVariable.setImageDrawable(MainActivity.sameColor);

            clickedVariable.setClickable(false);
            clickedVariable.setImageAlpha(255);
            BoardLayout.finalConversionResult = true;
        }
    }
}
