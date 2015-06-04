/**
 * Holds all of the components on the game screen. Components are controlled
 * using the get/set methods included in this class.
 * 
 * @author Zachary Teper and Angela Zhu
 * @version 1.0 06.01.2015
 */
import javax.swing.*;
import java.awt.*;
public class GamePanel extends JPanel 
{
  /** Holds the button representing choice A.
    */
  private JButton a;
  /** Holds the button representing choice B.
    */
  private JButton b;
  /** Holds the button representing choice C.
    */
  private JButton c;
  /** Holds the button representing choice D.
    */
  private JButton d;
  /**Holds the Label which holds the question/clue.
    */
  private JLabel questionLabel;
  /**Holds the question which is currently being viewed (question stage only).
   */
  private Question currentQuestion;
  /**Displays the current question and the total number of questions to the user.
    */
  private JLabel questionCounter=new JLabel();
  /**Holds the button which is used to pause and unpause the game.
    */
  private JButton pauseButton = new JButton ("PAUSE");
  /**
   */
  private JLabel timeLabel = new JLabel ("start");
  private String currentClue;
  private JLabel feedbackLabel= new JLabel("INCORRECT. Please try again.");
  private boolean atQuestionStage=true;
  private JLabel levelCounter=new JLabel();
  private int currentQuestionNumber;
  private Country[] destinations;
  private int answer;
  public GameTimer timer;
  private Image background;
  private JLabel imageLabel=new JLabel();
  private JLabel mapImageLabel=new JLabel();
  
  public void setBackground(Image i)
  {
    System.out.println ("image");
    background=i;
    imageLabel.setIcon(new ImageIcon(i));
    imageLabel.setBounds(0,0,700,600);
    add(imageLabel);
  }
  public JLabel getMapImage()
  {
    return mapImageLabel;
  }
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
  public JLabel getQuestionCounter()
  {
    return questionCounter;
  }
  public JLabel getLevelCounter()
  {
    return levelCounter;
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
    feedbackLabel.setVisible(true);
  }
  public void removeWrongDestination (int button)
  {
    if (button==0)
      a.setEnabled(false);
    else if (button==1)
      b.setEnabled(false);
    else
      c.setEnabled(false);
    feedbackLabel.setVisible(true);
  }
  public void setQuestion(Question q)
  {
    currentQuestion=q;
    questionLabel.setText(q.getQuestion());
    feedbackLabel.setVisible(false);
  }
  private void shuffleButtons()
  {
    int index=(int)(Math.random()*3);
    System.out.println ("shuffle: "+index);
    //add(mapImageLabel);
    if (index==1)
    {
      a.setBounds(350,500,a.getPreferredSize().height+80,30);
      b.setBounds(200,500,b.getPreferredSize().height+80,30);
      c.setBounds(500,500,c.getPreferredSize().height+80,30);
    }
    else if (index==2)
    {
      a.setBounds(500,500,a.getPreferredSize().height+80,30);
      c.setBounds(200,500,c.getPreferredSize().height+80,30);
      b.setBounds(350,500,b.getPreferredSize().height+80,30);
    }
    else
    {
      a.setBounds(200,500,a.getPreferredSize().height+80,30);
      b.setBounds(350,500,b.getPreferredSize().height+80,30);
      c.setBounds(500,500,c.getPreferredSize().height+80,30);
    }
  }
  public void switchToCountry()
  {
    questionLabel.setText(currentQuestion.getQuestion());
    d.setVisible(true);
    atQuestionStage=true;
    mapImageLabel.setVisible(false);
    a.setText("A");
    b.setText("B");
    c.setText("C");
    a.setBounds(380,220,a.getPreferredSize().height+20,30);
    b.setBounds(500,220,b.getPreferredSize().height+20,30);
    c.setBounds(380,320,c.getPreferredSize().height+20,30);
    d.setBounds(500,320,d.getPreferredSize().height+20,30);
    questionLabel.setBounds(380,100,400,100);
    revalidate();
  }
  public void switchToMap()
  {
    System.out.println ("map");
    feedbackLabel.setVisible(false);
    d.setVisible(false);
    a.setText(destinations[0].getName());
    b.setText(destinations[1].getName());
    c.setText(destinations[2].getName());
    currentClue=destinations[0].getRandClue();
    questionLabel.setText(currentClue);
    atQuestionStage=false;
    shuffleButtons();
    questionLabel.setBounds(650-questionLabel.getPreferredSize().width,120,500,30);
    mapImageLabel.setVisible(true);
    //add(mapImageLabel);
    mapImageLabel.repaint();
    revalidate();
  }
  
  public void switchToPause ()
  {
    timer.setPaused (true);
    questionLabel.setText ("<html><br>You have paused the game!"+
                           "<br>To continue, please press the pause button again!"+
                           "<br>~^_^~</html>");
    a.setVisible(false);
    b.setVisible(false);
    c.setVisible(false);
    d.setVisible(false);
    repaint ();
    revalidate ();
  }
  
  public void unpause ()
  {
    timer.setPaused (false);
    if (atQuestionStage)
      questionLabel.setText(currentQuestion.getQuestion());
    else
      questionLabel.setText(currentClue);
    a.setVisible(true);
    b.setVisible(true);
    c.setVisible(true);
    if (atQuestionStage)
      d.setVisible(true);
    repaint ();
    revalidate ();
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
  
  public JButton getPauseButton ()
  {
    return pauseButton;
  }
  public JLabel getFeedbackLabel()
  {
    return feedbackLabel;
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
  public GameTimer getTimer()
  {
    return timer;
  }
  //The first element in c MUST be the correct answer!
  public GamePanel (int difficulty, ScarletGemMain source)
  {
    timer = new GameTimer (difficulty, timeLabel, this, source);
    timer.start ();
    a=new JButton ("A");
    b=new JButton ("B");
    c=new JButton ("C");
    d=new JButton ("D");
    questionLabel=new JLabel();
    
    setLayout(null);
    //Insets insets = this.getInsets ();
    a.setBounds(380,220,a.getPreferredSize().height+20,a.getPreferredSize().width);
    b.setBounds(500,220,b.getPreferredSize().height+20,b.getPreferredSize().width);
    c.setBounds(308,320,c.getPreferredSize().height+20,c.getPreferredSize().width);
    d.setBounds(500,320,d.getPreferredSize().height+20,d.getPreferredSize().width);
    questionLabel.setBounds(380,100,400,100);
    pauseButton.setBounds (450,50,pauseButton.getPreferredSize().width, pauseButton.getPreferredSize().height);
    
    mapImageLabel.setIcon(new ImageIcon("pics/map.jpg"));
    mapImageLabel.setBounds(200,170,500,280);
    mapImageLabel.setVisible(false);
    
    feedbackLabel.setBounds(250,450,200,30);
    pauseButton.setBounds(550,10,90,30);
    levelCounter.setBounds(50,50,450,30);
    questionCounter.setBounds(400,50,90,30);
    
    feedbackLabel.setVisible(false);
    
    add(feedbackLabel);
    add(mapImageLabel);
    add(a);
    add(b);
    add(c);
    add(d);
    add(questionLabel);
    add(pauseButton);
    add(questionCounter);
    add(levelCounter);
    revalidate ();
  }
}
