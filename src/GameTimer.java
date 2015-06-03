import javax.swing.*;
import java.awt.event.*;
/**
 * This is the GameTimer class that controls the running and display of the timer used in the game.
 * It is a countdown timer.
 * 
 * @author Zachary Teper and Angela Zhu
 * @version 1.0 05.24.2015
 */
public class GameTimer extends Thread
{
  /**
   * The int variable startingTime stores the total number of time (in seconds) that the user has to play the game.
   * It would be 1 minute for easy, 1.5 minutes for medium, and 2 minutes for hard.
   */
  private int startingTime;
  /**
   * The int variable timeRemaining stores the time (in seconds) that the user has left to complete the game.
   */
  public static int timeRemaining;
  /**
   * The boolean variable paused determines whether or not the timer is being paused.
   */
  private boolean paused = false;
  /**
   * The boolean variable gameWon determines whether or not the user has won the game.
   */
  private boolean gameWon = false;
  /**
   * The JLabel variable time is the label used to display the timer to the user.
   */
  private JLabel time;
  /**
   * The variabel panel is an instance of the class GamePanel,
   * which is used to display the label to the panel.
   */
  private GamePanel panel;
  /**
   * The variable source is an instance of the class ScarletGemMain, 
   * which is used to control the endGame method in the ScarletGemMain class.
   */
  private ScarletGemMain source;
  
  /**
   * This is the contructor for the class GameTimer.
   * 
   * @param difficulty, int
   * @param label, JLabel
   * @param panel, GamePanel
   * @param game, ScarletGemMain
   * 
   * <b>local variables</b>
   * <p><b>difficulty</b> the int variable used to store the difficulty of the game.
   * <p><b>label</b> an instance of the JLabel class used to display the timer.
   * <p><b>panel</b> an instance of the GamePanel class, used to display the label.
   * <p><b>game</b> an instance of the ScarletGemMain class, used to control methods in the class.
   * <p>
   * <b>Conditional Statements</b>
   * <p><b>If Structure #1:</b>
   * <p>If the difficulty is 0 (easy), set the startingTime to 60.
   * <p>Or if the difficulty is 1 (medium), set the startingTime to 90.
   * <p>If it's neither of the above, set the startingTime to 120.
   */
  public GameTimer (int difficulty, JLabel label, GamePanel panel,
  ScarletGemMain game)
  {
    if (difficulty==0)
      startingTime=60;
    else if (difficulty==1)
      startingTime=90;
    else
      startingTime=120;
    timeRemaining = startingTime;
    time = label;
    this.panel = panel;
    source=game;
  }
  
  /**
   * Tells the game timer is the game is paused or not.
   * 
   * @param pause boolean sets the paused variable to pause.
   */
  public void setPaused (boolean pause)
  {
    paused = pause;
  }
  /**
   * Returns true only if the game is currently paused.
   * 
   * @return boolean if the game is paused.
   */
  public boolean getPaused ()
  {
    return paused;
  }
  /**
   * Tells the GameTimer instance of the game has been completed.
   * 
   * @param won boolean the new value of the gameWon variable.
   */
  public void setGameWon (boolean won)
  {
    gameWon = won;
  }
  /**
   * Returns true only if the game has been completed.
   * 
   * @return boolean true only if game has been completed.
   */
  public boolean getGameWon ()
  {
    return gameWon;
  }
  /**
   * Starts counting down from the starting time.
   * Regularly updates the Timer JLabel on the game panel.
   * <b>While loops</b>
   * <ul><li> continuously decrements the game timer.</ul>
   * <b>If statements</b>
   * <ul><li>checks if game is paused.
   * <li>checks if the timer is at zero.
   * <li> checks if the game has been won.
   * </ul>
   */
  public void timer ()
  {
    panel.add (time);
    while (timeRemaining >= 0 && !gameWon)
    {
      time.setText ("Time: "+minutes()+":"+seconds());

      time.setBounds(550,50,80,30);
      panel.revalidate();
      try
      {
        sleep (1000);
      }
      catch (InterruptedException e)
      {
      }
      if (!paused)
      {
        timeRemaining--;
      }
      if (timeRemaining==0)
      {
        setPaused(true);
        source.actionPerformed(new ActionEvent (this
                                                , ActionEvent.ACTION_PERFORMED,"loss"));
        break;
      }
    }
    if (gameWon)
    {
      System.out.println ("game over");
      panel.remove (time);
      gameWon = false;
    }
  }
  /**
   * Returns the number of minutes left in the game.
   * 
   * @return int the number of minutes left in the game.
   */
  public int minutes ()
  {
    return timeRemaining / 60;
  }
  /**
   * Returns a String representation of 
   * the number of seconds left in the game (not including minutes).
   * 
   * @return String the number of seconds left in the game (not including minutes).
   */
  public String seconds ()
  {
    if (timeRemaining % 60 < 10)
      return "0" + timeRemaining % 60;
    return Integer.toString (timeRemaining % 60);
  }
  /**
   * Calls the timer() method to start the timer
   */
  public void run()
  {
    timer ();
  }
}