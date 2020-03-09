package com.example.connect3game;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.ImageViewCompat;

import android.os.Bundle;
import android.view.View;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    //0 = yellow 1=red
    int activePlayer = 0;
    int cnt=9;
    int[] gameState = {2,2,2,2,2,2,2,2,2}; //2 means unplayed
    LinearLayout linearLayout;
    TextView winnerMessage;
    boolean play=true;
    int[][] winningPositions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    GridLayout gridLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }
    public void dropin(View view)
    {
        if(play && cnt>0) {

            ImageView counter = (ImageView) view;
            int tappedCounter = Integer.parseInt(counter.getTag().toString().trim());
            if (gameState[tappedCounter] == 2) {
                gameState[tappedCounter] = activePlayer;
                counter.setTranslationY(-1000f);
                if (activePlayer == 0) {
                    counter.setImageResource(R.drawable.yellow);
                    activePlayer = 1;
                    cnt--;
                } else {
                    counter.setImageResource(R.drawable.red);
                    activePlayer = 0;
                    cnt--;
                }
                counter.animate().translationYBy(1000f).setDuration(340);
                if(cnt==0)
                {
                    linearLayout = findViewById(R.id.playAgainLayout);

                    linearLayout.setVisibility(View.VISIBLE);
                    winnerMessage = findViewById(R.id.textView);
                    winnerMessage.setText("Draw!");
                }
                for (int[] winningPosition : winningPositions) {
                    if (gameState[winningPosition[0]] == gameState[winningPosition[1]] && gameState[winningPosition[1]] == gameState[winningPosition[2]] && gameState[winningPosition[0]] != 2) {
                        play=false;
                        String winner = "Red";
                        System.out.println("SomeOne has Won");
                         winnerMessage = findViewById(R.id.textView);
                        if (gameState[winningPosition[0]] == 0) {
                            winner = "Yellow";
                        }

                        winnerMessage.setText(winner + " has WON!!!");
                         linearLayout = findViewById(R.id.playAgainLayout);

                        linearLayout.setVisibility(View.VISIBLE);
                    }
                }

            }
        }
    }

    public void playAgain(View view)
    {
        LinearLayout linearLayout = findViewById(R.id.playAgainLayout);

        linearLayout.setVisibility(View.INVISIBLE);

        activePlayer = 0;
        play=true;
        cnt = 9;
        for(int i=0;i<gameState.length;i++)
        {
            gameState[i]=2;
        }
        androidx.gridlayout.widget.GridLayout gridLayout = findViewById(R.id.gridLayout);

        for(int i=0;i<gridLayout.getChildCount();i++)
        {
            ((ImageView)gridLayout.getChildAt(i)).setImageResource(0);
        }


    }

}
