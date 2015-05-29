/**
 * Auto Generated Java Class.
 */
import javax.swing.*;
import java.awt.*;
public class HighScoresViewer extends JFrame 
{
  private Graphics screen;
  private JButton easy = new JButton ("Easy");
  private JButton medium = new JButton ("Medium");
  private JButton hard = new JButton ("Hard");
  
  public JButton getEasy ()
  {
    return easy;
  }
  public JButton getMedium ()
  {
    return medium;
  }
  public JButton getHard ()
  {
    return hard;
  }
  
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
