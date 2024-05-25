package Gameplay;

import Kernel.Kernel;
import Kernel.Text;
import Utils.Vector2;

import java.awt.*;

/**
 * The Class ScoreManager manages the score in the Pac-Man game.
 * It maintains the current score and high score, and provides methods for manipulating them.
 */
public class ScoreManager {

    private static Text textScore;
    private static Text highScoreText;
    private static int score = 0;
    private static int highScore = 0;
    private static ScoreManager scoreManager;

    private ScoreManager(){

        textScore = new Text(
                new Vector2(10,22),
                new Font("Consolas", Font.BOLD, 22),
                Color.WHITE,
                "SCORE: "


        );
        highScoreText = new Text(
                new Vector2(250,22),
                new Font("Consolas", Font.BOLD, 22),
                Color.WHITE,
                "HIGH SCORE: "

                );

        Kernel.getKernel().addText(textScore);
        Kernel.getKernel().addText(highScoreText);
    }

    public static ScoreManager getInstance(){
        if (scoreManager==null){
            scoreManager = new ScoreManager();
        }
        return scoreManager;
    }


    /**
     * Adds a specified number of points to the current score. +10 for a point, +50 for a SuperGum,+100 for a ghost.
     * If the new score is higher than the high score, updates the high score.
     * @param points The number of points to add to the score.
     */
    public void addPoints(int points) {
        score += points;
        textScore.setText("SCORE: "+score);
        if (score > highScore) {
            highScore = score;
            highScoreText.setText("HIGH SCORE: "+score);
        }
    }

    /**
     * @return The current score.
     */
    public int getScore() {
        return score;
    }

    /**
     * @return The current HighScore.
     */
    public int getHighScore() {
        return highScore;
    }
    /**
     * Sets the score to 0 when the game is reset.
     * {@link GameplayMain#startLevel()}
     */
    public void resetScore() {
        score = 0;
        textScore.setText("SCORE: 0");
    }

}
