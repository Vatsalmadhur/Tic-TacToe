package com.example.tictactoe;

import android.widget.TextView;

import androidx.lifecycle.ViewModel;

import java.util.Arrays;

public class TicViewModal extends ViewModel {
    boolean gameActive = true;

    //Player representations
    //0-X
    //1-O
    int activePlayer = 0;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    int[][] winPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    public void resetData() {
        Arrays.fill(gameState, 2);
    }

    public String getActiveState() {
        if (activePlayer == 0) {
            activePlayer = 1;
            return "O's turn-tap to play";
        } else {
            activePlayer = 0;
            return "X's turn-tap to play";
        }
    }
}
