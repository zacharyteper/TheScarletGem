/**
 * Auto Generated Java Class.
 */
import javax.swing.*;
import java.awt.*;
public class CountryPanel extends JPanel implements Runnable
{
  private final Country country;
  private Graphics screen;
  
  public Country getCountry()
  {
    return country;
  }
  public Graphics getScreen()
  {
    return screen;
  }
  public void setScreen (Graphics g)
  {
    screen=g;
  }
  public CountryPanel (Country c, Graphics g)
  {
    country=c;
    screen=g;
  }
  
  public void run()
  {
  }
  /* ADD YOUR CODE HERE */
  
}
