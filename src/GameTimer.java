import javax.swing.*;
import java.awt.event.*;
/**
 * Auto Generated Java Class.
 */
public class GameTimer extends Thread
{
  private int startingTime = 30;
  public static int timeRemaining;
  private boolean paused = false;
  private boolean gameWon = false;
  private JLabel time;
  private GamePanel panel;
  private ScarletGemMain source;
  
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

  public void setPaused (boolean pause)
  {
    paused = pause;
  }
  
  public void setGameWon (boolean won)
  {
    gameWon = won;
  }
  
  public void timer ()
  {
    panel.add (time);
    while (timeRemaining >= 0 && !gameWon)
    {
      time.setText ("Time: "+minutes()+":"+seconds());

      time.setBounds(550,50,80,20);
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
      }
      
      //System.out.println (timeRemaining);
    }
    if (gameWon)
    {
      System.out.println ("game over");
      panel.remove (time);
      gameWon = false;
    }
  }
  public int minutes ()
  {
    return timeRemaining / 60;
  }
  
  public String seconds ()
  {
    if (timeRemaining % 60 < 10)
      return "0" + timeRemaining % 60;
    return Integer.toString (timeRemaining % 60);
  }
  
  public void run()
  {
    timer ();
  }
}