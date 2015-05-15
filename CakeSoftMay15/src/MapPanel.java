/**
 * Auto Generated Java Class.
 */
import javax.swing.*;
import java.awt.*;
public class MapPanel extends JPanel implements Runnable
{
  private Graphics screen;
  private Country[] destinations;
  public Country[] getDestinations ()
  {
    return destinations;
  }
  public void setDestinations (Country[]c)
  {
    destinations=c;
  }
  public Graphics getScreen()
  {
    return screen;
  }
  public void setScreen(Graphics g)
  {
    screen=g;
  }
  public MapPanel (Graphics g,Country[]c)
  {
    screen=g;
    destinations=c;
  }
 
  public void run()
  {
  }
  /* ADD YOUR CODE HERE */
  
}
