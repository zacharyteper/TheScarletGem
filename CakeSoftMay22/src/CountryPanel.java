/**
 * Auto Generated Java Class.
 */
import javax.swing.*;
import java.awt.*;
public class CountryPanel extends JPanel implements Runnable
{
  private final Country country;
  private Graphics screen;
  private JButton A;
  private JButton B;
  private JButton C;
  private JButton D;
  private JLabel questionLabel;
  private Question currentQuestion;
  
  public Question getQuestion()
  {
    return currentQuestion;
  }
  public void removeWrongAnswer (char button)
  {
    if (button=='A')
      remove(A);
    else if (button=='B')
      remove(B);
    else if (button=='C')
      remove(C);
    else
      remove(D);
  }
  public void setQuestion(Question q)
  {
    currentQuestion=q;
    questionLabel.setText(q.getQuestion());
  }
  public JButton getAButton()
  {
    return A;
  }
  public JButton getBButton()
  {
    return B;
  }
  public JButton getCButton()
  {
    return C;
  }
  public JButton getDButton()
  {
    return D;
  }
  
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
  public CountryPanel (Country c)
  {
    country=c;
  }
  public CountryPanel (Country c, Graphics g, Question first)
  {
    country=c;
    screen=g;
    A=new JButton ("A");
    B=new JButton ("B");
    C=new JButton ("C");
    D=new JButton ("D");
    questionLabel=new JLabel(first.getQuestion());
    currentQuestion=first;
    add(A);
    add(B);
    add(C);
    add(D);
    add(questionLabel);
  }
  
  public void run()
  {
  }
  /* ADD YOUR CODE HERE */
  
}
