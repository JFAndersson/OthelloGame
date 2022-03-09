package com.example.othello;

import android.animation.AnimatorSet;
import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.os.Bundle;

import androidx.appcompat.content.res.AppCompatResources;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class BoardLayout extends Fragment implements View.OnClickListener {

    //region declarations

    private ArrayList<ArrayList<ImageView>> store_circles = new ArrayList<>();

    private View view;
    private ImageView gameBackground;
    public static boolean onePlayer = false;
    public static boolean colorWhite = false;

    private boolean clicked = false;
    boolean firstLoad = false;

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

        gameBackground = view.findViewById(R.id.gameBackground);

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
        rowA.add(circleB1); rowA.add(circleB2); rowA.add(circleB3); rowA.add(circleB4);
        rowA.add(circleB5); rowA.add(circleB6); rowA.add(circleB7); rowA.add(circleB8);

        ArrayList<ImageView> rowC = new ArrayList<>();
        rowA.add(circleC1); rowA.add(circleC2); rowA.add(circleC3); rowA.add(circleC4);
        rowA.add(circleC5); rowA.add(circleC6); rowA.add(circleC7); rowA.add(circleC8);

        ArrayList<ImageView> rowD = new ArrayList<>();
        rowA.add(circleD1); rowA.add(circleD2); rowA.add(circleD3); rowA.add(circleD4);
        rowA.add(circleD5); rowA.add(circleD6); rowA.add(circleD7); rowA.add(circleD8);

        ArrayList<ImageView> rowE = new ArrayList<>();
        rowA.add(circleE1); rowA.add(circleE2); rowA.add(circleE3); rowA.add(circleE4);
        rowA.add(circleE5); rowA.add(circleE6); rowA.add(circleE7); rowA.add(circleE8);

        ArrayList<ImageView> rowF = new ArrayList<>();
        rowA.add(circleF1); rowA.add(circleF2); rowA.add(circleF3); rowA.add(circleF4);
        rowA.add(circleF5); rowA.add(circleF6); rowA.add(circleF7); rowA.add(circleF8);

        ArrayList<ImageView> rowG = new ArrayList<>();
        rowA.add(circleG1); rowA.add(circleG2); rowA.add(circleG3); rowA.add(circleG4);
        rowA.add(circleG5); rowA.add(circleG6); rowA.add(circleG7); rowA.add(circleG8);

        ArrayList<ImageView> rowH = new ArrayList<>();
        rowA.add(circleH1); rowA.add(circleH2); rowA.add(circleH3); rowA.add(circleH4);
        rowA.add(circleH5); rowA.add(circleH6); rowA.add(circleH7); rowA.add(circleH8);

        store_circles.add(rowA); store_circles.add(rowE);
        store_circles.add(rowB); store_circles.add(rowF);
        store_circles.add(rowC); store_circles.add(rowG);
        store_circles.add(rowD); store_circles.add(rowH);

        //endregion

        //region setListeners

        circleA1.setOnClickListener(this); circleB1.setOnClickListener(this);
        circleA2.setOnClickListener(this); circleB2.setOnClickListener(this);
        circleA3.setOnClickListener(this); circleB3.setOnClickListener(this);
        circleA4.setOnClickListener(this); circleB4.setOnClickListener(this);
        circleA5.setOnClickListener(this); circleB5.setOnClickListener(this);
        circleA6.setOnClickListener(this); circleB6.setOnClickListener(this);
        circleA7.setOnClickListener(this); circleB7.setOnClickListener(this);
        circleA8.setOnClickListener(this); circleB8.setOnClickListener(this);

        circleC1.setOnClickListener(this); circleD1.setOnClickListener(this);
        circleC2.setOnClickListener(this); circleD2.setOnClickListener(this);
        circleC3.setOnClickListener(this); circleD3.setOnClickListener(this);
        circleC4.setOnClickListener(this); circleD4.setOnClickListener(this);
        circleC5.setOnClickListener(this); circleD5.setOnClickListener(this);
        circleC6.setOnClickListener(this); circleD6.setOnClickListener(this);
        circleC7.setOnClickListener(this); circleD7.setOnClickListener(this);
        circleC8.setOnClickListener(this); circleD8.setOnClickListener(this);

        circleE1.setOnClickListener(this); circleF1.setOnClickListener(this);
        circleE2.setOnClickListener(this); circleF2.setOnClickListener(this);
        circleE3.setOnClickListener(this); circleF3.setOnClickListener(this);
        circleE4.setOnClickListener(this); circleF4.setOnClickListener(this);
        circleE5.setOnClickListener(this); circleF5.setOnClickListener(this);
        circleE6.setOnClickListener(this); circleF6.setOnClickListener(this);
        circleE7.setOnClickListener(this); circleF7.setOnClickListener(this);
        circleE8.setOnClickListener(this); circleF8.setOnClickListener(this);

        circleG1.setOnClickListener(this); circleH1.setOnClickListener(this);
        circleG2.setOnClickListener(this); circleH2.setOnClickListener(this);
        circleG3.setOnClickListener(this); circleH3.setOnClickListener(this);
        circleG4.setOnClickListener(this); circleH4.setOnClickListener(this);
        circleG5.setOnClickListener(this); circleH5.setOnClickListener(this);
        circleG6.setOnClickListener(this); circleH6.setOnClickListener(this);
        circleG7.setOnClickListener(this); circleH7.setOnClickListener(this);
        circleG8.setOnClickListener(this); circleH8.setOnClickListener(this);

        //endregion

        if (!firstLoad){

            for (ArrayList<ImageView> row : store_circles){
                for (ImageView image : row){
                    if (image != circleD4 && image != circleD5 && image != circleE4 && image != circleE5){
                        image.setVisibility(View.GONE);
                    }
                }
            }

            if (getActivity() != null){
                circleD4.setImageDrawable(AppCompatResources.getDrawable(getActivity(), R.drawable.white_circle78));
                circleD5.setImageDrawable(AppCompatResources.getDrawable(getActivity(), R.drawable.black_circle78));
                circleE4.setImageDrawable(AppCompatResources.getDrawable(getActivity(), R.drawable.black_circle78));
                circleE5.setImageDrawable(AppCompatResources.getDrawable(getActivity(), R.drawable.white_circle78));
            }

            /*
            circleD4.animate().alpha(0).setDuration(0);
            circleD5.animate().alpha(0).setDuration(0);
            circleE4.animate().alpha(0).setDuration(0);
            circleE5.animate().alpha(0).setDuration(0);

             */

            firstLoad = true;
        }

        return view;
    }

    @Override
    public void onClick(View v) {

        if (getActivity() != null){
            switch (v.getId()){
                case (R.id.circleD4):
                    if (colorWhite){
                        circleD4.setImageDrawable(AppCompatResources.getDrawable(getActivity(), R.drawable.white_circle78));
                    }
                    else{
                        circleD4.setImageDrawable(AppCompatResources.getDrawable(getActivity(), R.drawable.black_circle78));
                    }
                    break;
                case (R.id.circleD5):
                    if (colorWhite){
                        circleD5.setImageDrawable(AppCompatResources.getDrawable(getActivity(), R.drawable.white_circle78));
                    }
                    else{
                        circleD5.setImageDrawable(AppCompatResources.getDrawable(getActivity(), R.drawable.black_circle78));
                    }
                    break;
                case (R.id.circleE4):
                    if (colorWhite){
                        circleE4.setImageDrawable(AppCompatResources.getDrawable(getActivity(), R.drawable.white_circle78));
                    }
                    else{
                        circleE4.setImageDrawable(AppCompatResources.getDrawable(getActivity(), R.drawable.black_circle78));
                    }
                    break;
                case (R.id.circleE5):
                    if (colorWhite){
                        circleE5.setImageDrawable(AppCompatResources.getDrawable(getActivity(), R.drawable.white_circle78));
                    }
                    else{
                        circleE5.setImageDrawable(AppCompatResources.getDrawable(getActivity(), R.drawable.black_circle78));
                    }
                    break;
            }
        }
    }
}