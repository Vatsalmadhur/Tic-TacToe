package com.example.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tictactoe.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    // View Modal object
    TicViewModal viewModal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        viewModal = new ViewModelProvider(this).get(TicViewModal.class);
        manageConfigurationChange();
    }

    @SuppressLint("SetTextI18n")
    public void playerTap(View view) {
        ImageView img = (ImageView) view;
        int tappedImg = Integer.parseInt(img.getTag().toString());
//        if (!viewModal.gameActive) {
//            gameReset(view);
//        }
        if (viewModal.gameState[tappedImg] == 2 && viewModal.gameActive) {
            viewModal.gameState[tappedImg] = viewModal.activePlayer;
            img.setTranslationY(-1000f);
            if (viewModal.activePlayer == 0) {
                img.setImageResource(R.drawable.imgx);
                binding.status.setText(viewModal.getActiveState());
            } else {
                img.setImageResource(R.drawable.imgo);
                binding.status.setText(viewModal.getActiveState());
            }
            img.animate().translationYBy(1000f).setDuration(300);


        }


        for (int[] winPosition : viewModal.winPositions) {
            if (viewModal.gameState[winPosition[0]] == viewModal.gameState[winPosition[1]] && viewModal.gameState[winPosition[1]] == viewModal.gameState[winPosition[2]] && viewModal.gameState[winPosition[0]] != 2) {
                //someone won
                String winnerStr;
                if (viewModal.gameState[winPosition[0]] == 0) {
                    winnerStr = "X has won";
                } else {
                    winnerStr = "O has won";
                }
                TextView status = findViewById(R.id.status);
                status.setText(winnerStr);
                Button buttonreset = (Button) findViewById(R.id.button_reset);
                buttonreset.setVisibility(View.VISIBLE);


            }

        }
    }

    private void statusUpdate() {

    }

    public void gameReset(View view) {
        viewModal.gameActive = true;
        viewModal.activePlayer = 0;
        viewModal.resetData();

        ((ImageView) findViewById(R.id.imageView9)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView) findViewById(R.id.imageView8)).setImageResource(0);
        binding.buttonReset.setVisibility(View.GONE);

    }

    private void manageConfigurationChange() {
        ImageView[] imageViews = {binding.imageView1, binding.imageView2, binding.imageView3,
                binding.imageView4, binding.imageView5, binding.imageView6, binding.imageView7, binding.imageView8};

        for (int i = 0; i < 9; i++) {
            switch (viewModal.gameState[i]) {
                case 0:
                    imageViews[i].setImageResource(R.drawable.imgx);
                    break;
                case 1:
                    imageViews[i].setImageResource(R.drawable.imgo);
                    break;
                default:
            }
        }
    }

}