package com.example.othello;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import androidx.appcompat.content.res.AppCompatResources;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Computer {

    //Computer difficulty levels. (Only performs the move that results in the max amount of circles being converted):

    //Difficulty 1: If multiple moves exist that all result in the same amount of circles being converted, then one of these moves is chosen at random.
    //Difficulty 2: If multiple moves exist that all result in the same amount of circles being converted, then moves succeeding the player's turn will also be taken into consideration.
    //Difficulty 3: To be determined

    //region declarations

    // [Index], [Potential Placements]
    public static Map<String, ImageView> possiblePlacements = new HashMap<>(8);

    // [Move Index], [Possible Conversions]
    public static Map<String, Integer> successfulConversions = new HashMap<>(8);

    public static int chosenMaxValue;

    public static boolean difficulty1 = false;
    public static boolean difficulty2 = false;
    public static boolean difficulty3 = false;

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

        Indicators.activateIndicators(true);

        setComputerPlacement();
    }


    public static void setComputerPlacement() throws InterruptedException {

        // [Move Index], [POSSIBLE Placements]
        ArrayList<Integer> valueDupes;

        int finalMoveIndex = 0;

        if (difficulty1) {

            valueDupes = new ArrayList<>(successfulConversions.values());

            Collections.sort(valueDupes);

            chosenMaxValue = Collections.max(valueDupes);

            finalMoveIndex = Integer.parseInt(getKey(successfulConversions, chosenMaxValue));
        }
        else if (difficulty2){
            valueDupes = new ArrayList<>(successfulConversions.values());

            Collections.sort(valueDupes);

            chosenMaxValue = Collections.max(valueDupes);

            finalMoveIndex = Integer.parseInt(getKey(successfulConversions, chosenMaxValue));

            Framework.setProximity(possiblePlacements.get(String.valueOf(finalMoveIndex)), true);
        }
        else{
            //...
        }

        Framework.setProximity(possiblePlacements.get(String.valueOf(finalMoveIndex)), false);
    }

    public static <K, V> K getKey(Map<K, V> map, V value)
    {
        return map.entrySet().stream()
                .filter(entry -> value.equals(entry.getValue()))
                .findAny().map(Map.Entry::getKey)
                .orElse(null);
    }
}
