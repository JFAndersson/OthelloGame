package com.example.othello;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.content.res.AppCompatResources;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.Set;

public class Computer {


    //Difficulty 1: Picks a move at random. Every second move results in the least possible amount circles being converted.
    //              If multiple moves exist that all result in the same amount of circles being converted, then one of these moves is picked at random.

    //Difficulty 2: Performs the move that results in the max amount of circles being converted.
    //              If multiple moves exist that all result in the same amount of circles being converted, then one of these moves is picked at random.

    //Difficulty 3:

    //region declarations

    // [Move Index], [Potential Placements]
    public static Map<String, ImageView> possiblePlacements = new HashMap<>();
    // [Move Index], [Possible Conversions]
    public static Map<String, Integer> successfulConversions = new HashMap<>();

    public static int chosenMaxValue;

    // [Conversion Index], [Conversion Value]
    public static ArrayList<Integer> valueList;

    public static boolean finalPlacementChosen = false;


    private static int moveCount = 0;

    public static boolean difficulty1 = false;
    public static boolean difficulty2 = false;
    public static boolean difficulty3 = false;

    public static ImageView testImage;
    public static boolean couldntAvoidWall = false;

    public static Drawable sameColor;
    public static Drawable oppositeColor;
    public static Drawable emptyColor;

    //endregion


    public static void initializeVariables(Activity activityContext){

        //Toast.makeText(activityContext, possiblePlacements.toString(), Toast.LENGTH_SHORT).show();

        emptyColor = Objects.requireNonNull(AppCompatResources.getDrawable(activityContext, R.drawable.transparent_circle78));

        if (MainActivity.colorWhite){
            sameColor = Objects.requireNonNull(AppCompatResources.getDrawable(activityContext, R.drawable.white_circle78));
            oppositeColor = Objects.requireNonNull(AppCompatResources.getDrawable(activityContext, R.drawable.black_circle78));
        }
        else{
            sameColor = Objects.requireNonNull(AppCompatResources.getDrawable(activityContext, R.drawable.black_circle78));
            oppositeColor = Objects.requireNonNull(AppCompatResources.getDrawable(activityContext, R.drawable.white_circle78));
        }
    }

    public static void findPossiblePlacements() throws InterruptedException {

        possiblePlacements.clear();
        successfulConversions.clear();
        chosenMaxValue = 0;

        Indicators.activateIndicators(true, false, false);

        setComputerPlacement();
    }


    public static void setComputerPlacement() throws InterruptedException {

        String finalMoveIndex = "100";

        Map<String, ImageView> pastPossiblePlacements = new HashMap<>(possiblePlacements);

        if (difficulty1) {

            valueList = new ArrayList<>(successfulConversions.values());

            Collections.sort(valueList);

            if (moveCount > 0){
                chosenMaxValue = Collections.min(valueList);

                moveCount = 0;
            }
            else {
                Random rand = new Random();

                int upperBound = valueList.size();

                int rand_value = rand.nextInt(upperBound);

                chosenMaxValue = valueList.get(rand_value);
            }

            finalMoveIndex = getKey(successfulConversions, chosenMaxValue);

            moveCount++;
        }
        else if (difficulty2){

            valueList = new ArrayList<>(successfulConversions.values());

            Collections.sort(valueList);

            chosenMaxValue = Collections.max(valueList);

            finalMoveIndex = getKey(successfulConversions, chosenMaxValue);
        }
        else if (difficulty3){
/*
            finalPlacementChosen = false;

            ArrayList<ArrayList<ImageView>> boardLayoutCopy = new ArrayList<>(BoardLayout.store_circles);

            while (true){

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


                int rowIndex;
                int colIndex;

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
                    else{
                        break;
                    }
                }

                rowIndex = rowIndexCount;
                colIndex = circleArrayList.get(rowIndex).indexOf(clickedVariable);

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


                boolean parameters = (concludingCircleLeft || concludingCircleRight || concludingCircleAbove || concludingCircleBelow ||
                        concludingCircleTopLeft || concludingCircleTopRight || concludingCircleBotLeft || concludingCircleBotRight);

                if (!futureCheck){

                    BoardLayout.finalConversionResult = false;

                    if (parameters){

                        clickedVariable.setImageDrawable(MainActivity.sameColor);

                        clickedVariable.setClickable(false);
                        clickedVariable.setImageAlpha(255);
                        BoardLayout.finalConversionResult = true;

                        BoardLayout.addDesirable();
                    }
                }
            }

            valueList = new ArrayList<>(successfulConversions.values());

            chosenMaxValue = Collections.max(valueList);

            finalMoveIndex = getKey(successfulConversions, chosenMaxValue);

 */
        }

        if (difficulty1 || difficulty2){
            Framework.setProximity(possiblePlacements.get(finalMoveIndex), false);
        }
        else if (difficulty3){
            Framework.setProximity(pastPossiblePlacements.get(finalMoveIndex), false);
        }
    }


    public static <K, V> Set<K> collectKeys(Map<K, V> map, V value) {

        Set<K> keys = new HashSet<>();

        for (Map.Entry<K, V> entry : map.entrySet()) {
            if (entry.getValue().equals(value)) {
                keys.add(entry.getKey());
            }
        }

        return keys;
    }

    public static String getKey(Map<String, Integer> map, Integer value) {

        ArrayList<String> keyArrayList = new ArrayList<>(collectKeys(map, value));

        Random rand = new Random();

        int upperBound = keyArrayList.size();

        int rand_value = rand.nextInt(upperBound);

        String returnKey = keyArrayList.get(rand_value);

        return returnKey;
    }
}
