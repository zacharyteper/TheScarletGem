/**
 * Auto Generated Java Class.
 */
import javax.swing.*;
import java.awt.*;
public class HighScoresViewer extends JFrame 
{
  private Graphics screen;
  
  public Graphics getScreen ()
  {
    return screen;
  }
  public void setScreen (Graphics g)
  {
    screen=g;
  }
  /* ADD YOUR CODE HERE */
  public HighScoresViewer ()
  {
    super ("High Scores");
    setVisible(true);
    setSize(400,400);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
  }
}
