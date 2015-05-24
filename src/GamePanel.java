/**
 * Auto Generated Java Class.
 */
import javax.swing.*;
import java.awt.*;
public class GamePanel extends JPanel implements Runnable {
  private Country country;
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
  private boolean atQuestionStage=true;
  private JLabel levelCounter;
  private int currentQuestionNumber;
  private Country[] destinations;
  private int answer;
  
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
      A.setEnabled(false);
    else if (button=='B')
      B.setEnabled(false);
    else if (button=='C')
      C.setEnabled(false);
    else
      D.setEnabled(false);
  }
  public void removeWrongDestination (int button)
  {
    if (button==0)
      A.setEnabled(false);
    else if (button==1)
      B.setEnabled(false);
    else
      C.setEnabled(false);
  }
  public void setQuestion(Question q)
  {
    currentQuestion=q;
    questionLabel.setText(q.getQuestion());
  }
  public void switchToCountry()
  {
    questionLabel.setText(currentQuestion.getQuestion());
    add(D);
    atQuestionStage=true;
    A.setText("A");
    B.setText("B");
    C.setText("C");
    revalidate();
  }
  public void switchToMap()
  {
    System.out.println ("map");
    remove(D);
    A.setText(destinations[0].getName());
    B.setText(destinations[1].getName());
    C.setText(destinations[2].getName());
    questionLabel.setText(destinations[0].getRandClue());
    atQuestionStage=false;
    revalidate();
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
    
  //The first element in c MUST be the correct answer!
  public GamePanel ()
  {

    A=new JButton ("A");
    B=new JButton ("B");
    C=new JButton ("C");
    D=new JButton ("D");
    questionLabel=new JLabel();
    add(A);
    add(B);
    add(C);
    add(D);
    add(questionLabel);
  }
  /* ADD YOUR CODE HERE */
  public void run()
  {
  }
}
