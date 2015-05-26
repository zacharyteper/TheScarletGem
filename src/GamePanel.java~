/**
 * Auto Generated Java Class.
 */
import javax.swing.*;
import java.awt.*;
public class GamePanel extends JPanel 
{
  private Country country;
  private JButton a;
  private JButton b;
  private JButton c;
  private JButton d;
  private JLabel questionLabel;
  private Question currentQuestion;
  private JLabel questionCounter;
  private JButton pauseButton;
  private JLabel timeLabel = new JLabel ("start");
  private JLabel feedbackLabel;
  private boolean atQuestionStage=true;
  private JLabel levelCounter;
  private int currentQuestionNumber;
  private Country[] destinations;
  private int answer;
  public GameTimer timer;
  
  public Question getQuestion()
  {
    return currentQuestion;
  }
  public boolean getStage()
  {
    return atQuestionStage;
  }
  public void changeStage()
  {
    atQuestionStage=!atQuestionStage;
  }
  public int getQuestionNumber()
  {
    return currentQuestionNumber;
  }
  public void removeWrongAnswer (char button)
  {
    if (button=='A')
      a.setEnabled(false);
    else if (button=='B')
      b.setEnabled(false);
    else if (button=='C')
      c.setEnabled(false);
    else
      d.setEnabled(false);
  }
  public void removeWrongDestination (int button)
  {
    if (button==0)
      a.setEnabled(false);
    else if (button==1)
      b.setEnabled(false);
    else
      c.setEnabled(false);
  }
  public void setQuestion(Question q)
  {
    currentQuestion=q;
    questionLabel.setText(q.getQuestion());
  }
  public void switchToCountry()
  {
    questionLabel.setText(currentQuestion.getQuestion());
    add(d);
    atQuestionStage=true;
    a.setText("A");
    b.setText("B");
    c.setText("C");
    revalidate();
  }
  public void switchToMap()
  {
    System.out.println ("map");
    remove(d);
    a.setText(destinations[0].getName());
    b.setText(destinations[1].getName());
    c.setText(destinations[2].getName());
    questionLabel.setText(destinations[0].getRandClue());
    atQuestionStage=false;
    revalidate();
  }
  
  public JButton getAButton()
  {
    return a;
  }
  public JButton getBButton()
  {
    return b;
  }
  public JButton getCButton()
  {
    return c;
  }
  public JButton getDButton()
  {
    return d;
  }
  
  public Country getCountry()
  {
    return country;
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
    
  //The first element in c MUST be the correct answer!
  public GamePanel (int difficulty)
  {
    timer = new GameTimer (difficulty, timeLabel, this);
    timer.start ();
    a=new JButton ("A");
    b=new JButton ("B");
    c=new JButton ("C");
    d=new JButton ("D");
    questionLabel=new JLabel();
    add(a);
    add(b);
    add(c);
    add(d);
    add(questionLabel);
    revalidate ();
  }
}
