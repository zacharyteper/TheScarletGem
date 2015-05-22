/**
 * Auto Generated Java Class.
 */
import javax.swing.*;
import java.awt.*;
public class GamePanel extends JPanel implements Runnable {
  private final Country country;
  private JButton A;
  private JButton B;
  private JButton C;
  private JButton D;
  private JLabel questionLabel;
  private Question currentQuestion;
  private JLabel questionCounter;
  private JButton pauseButton;
  private JLabel timeLabel;
  private JLabel feedbackLabel;
  private boolean atQuestionStage;
  private JLabel levelCounter;
  private int currentQuestionNumber;
  private Country[] destinations;
  private JLabel clue;
  private int answer;
  
  public Question getQuestion()
  {
    return currentQuestion;
  }
  public int getQuestionNumber()
  {
    return currentQuestionNumber;
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
  public void switchToCountry()
  {
    add(D);
  }
  public void switchToMap()
  {
    remove(D);
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
  public GamePanel (Country c)
  {
    country=c;
  }
  public GamePanel (Country c, Question first)
  {
    country=c;
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
  
  public Country[] getDestinations ()
  {
    return destinations;
  }
  public void setDestinations (Country[]c)
  {
    destinations=c;
  }
  public int getAnswer()
  {
    return answer;
  }
  public void setAnswer(int ans)
  {
    answer=ans;
  }
  private void refresh()
  {
    revalidate();
  }
    
  public GamePanel ()
  {
    country=new Country("");
  }
  //The first element in c MUST be the correct answer!
  public GamePanel (Country[]c,String s,int ans)
  {
    destinations=c;
    clue=new JLabel(s);
    answer=ans;
    add (A);
    add (B);
    add (C);
    add (D);
    add (clue);
    country=new Country("");
  }
  /* ADD YOUR CODE HERE */
  public void run()
  {
  }
}
