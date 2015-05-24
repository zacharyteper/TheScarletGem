import javax.swing.*;
/**
 * Auto Generated Java Class.
 */
public class GameTimer extends Thread
{
  private int startingTime = 300;
  private int timeRemaining;
  private boolean paused = false;
  private JLabel time;
  private GamePanel panel;
  
  public GameTimer (int difficulty, JLabel label, GamePanel panel)
  {
    timeRemaining = startingTime;
    time = label;
    this.panel = panel;
  }
  
  public int getTimeRemaining ()
  {
    return timeRemaining;
  }
  
  public boolean getPaused ()
  {
    return paused;
  }
  
  public void setPaused (boolean pause)
  {
    paused = pause;
  }
  
  public void timer ()
  {
    while (timeRemaining != 0)
    {
      time.setText (minutes()+":"+seconds());
      panel.add (time);
      try
      {
        sleep (1000);
      }
      catch (InterruptedException e)
      {
      }
      timeRemaining--;
    }
  }
  
  public int minutes ()
  {
    return timeRemaining / 60;
  }
  
  public String seconds ()
  {
    if (timeRemaining % 60 == 0)
      return "00";
    return Integer.toString (timeRemaining % 60);
  }
  
  public void run()
  {
    timer ();
  }
}