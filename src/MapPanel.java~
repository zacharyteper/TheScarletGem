/**
 * Auto Generated Java Class.
 */
import javax.swing.*;
import java.awt.*;
public class MapPanel extends JPanel implements Runnable
{
  private Graphics screen;
  private Country[] destinations;
  private JButton destination1;
  private JButton destination2;
  private JButton destination3;
  private JLabel clue;
  private int answer;
  
  public JButton getDestination1()
  {
    return destination1;
  }
  public JButton getDestination2()
  {
    return destination2;
  }
  public JButton getDestination3()
  {
    return destination3;
  }
  
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
  public int getAnswer()
  {
    return answer;
  }
  public void setAnswer(int ans)
  {
    answer=ans;
  }
  public void removeWrongAnswer (int destination)
  {
    if (destination==1)
      remove(destination1);
    else if (destination==2)
      remove(destination2);
    else
      remove(destination3);
    refresh();
  }
  public void shuffleButtons()
  {
    int index=(int)(Math.random()*3);
    answer=index;
    Country temp=destinations[index];
    destinations[index]=destinations[0];
    destinations[0]=temp;    
    refresh();
  }
  private void refresh()
  {
    destination1.setText(destinations[0].getName());
    destination2.setText(destinations[1].getName());
    destination3.setText(destinations[2].getName());
    revalidate();
  }
    
  public MapPanel ()
  {
  }
  public MapPanel (Graphics g,Country[]c,String s,int ans)
  {
    screen=g;
    destinations=c;
    answer=ans;
    destination1=new JButton(c[0].getName());
    destination2=new JButton(c[1].getName());
    destination3=new JButton (c[2].getName());
    clue=new JLabel(s);
    add (destination1);
    add (destination2);
    add (destination3);
    add (clue);
  }
 
  public void run()
  {
  }
  /* ADD YOUR CODE HERE */
  
}
