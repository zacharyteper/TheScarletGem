
import javax.swing.*;
import java.awt.*;
/**
 * Holds all of the components on the game screen. Components are controlled
 * using the get/set methods included in this class.
 * 
 * @author Zachary Teper (4 hours) and Angela Zhu (2.5 hours + 3 hours graphics)
 * @version 1.0 06.01.2015
 */
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
  /**Displays the amount of time remaining to the user.
   */
  private JLabel timeLabel = new JLabel ("start");
  /**Holds the text of the clue that is currently being viewed.
    */
  private String currentClue;
  /**Displays an 'INCORRECT' message if user chooses a wrong answer.
    */
  private JLabel feedbackLabel= new JLabel("INCORRECT. Please try again.");
  /**Holds a boolean value: if TRUE, the user is at the Country stage.
    * if FALSE, the user is at the Travelling Stage.
    */
  private boolean atQuestionStage=true;
  /**Displays the number of levels remaining to the user.
    */
  private JLabel levelCounter=new JLabel();
  /**Holds the three destinations at the Map Stage.
    */
  private Country[] destinations;
  /**Holds 0,1 or 2, representing the correct answer to the clue.
    */
  private int answer;
  /**
   * The GameTimer Instance which controls the timing of the game.
   */
  public GameTimer timer;
  /**
   * The background image.
   */
  private Image background;
  /**Holds the background Image on the screen.
    */
  private JLabel imageLabel=new JLabel();
  /**Holds the travelling map image.
    */
  private JLabel mapImageLabel=new JLabel();
  /**
   * Sets the background of the game.
   * 
   * @param i Image the new background.
   */
  public void setBackground(Image i)
  {
    background=i;
    imageLabel.setIcon(new ImageIcon(i));
    imageLabel.setBounds(0,0,700,600);
    add(imageLabel);
  }
  /**
   * Returns the current Question.
   * 
   * @return Question the question currently being viewed.
   */
  public Question getQuestion()
  {
    return currentQuestion;
  }
  /**
   * Returns true only if the user is a the Question stage.
   * 
   * @return boolean true only if the user is at the question stage.
   */
  public boolean getStage()
  {
    return atQuestionStage;
  }
  /**
   * Returns the component displaying the question number, current country,
   * and difficulty.
   * 
   * @return JLabel the question counter at the top of the scren.
   */
  public JLabel getQuestionCounter()
  {
    return questionCounter;
  }
  /**
   * Returns the component displaying the current level number.
   *
   * @return JLabel the level counter at the top of the screen.
   */
  public JLabel getLevelCounter()
  {
    return levelCounter;
  }
  /**
   * Disables the button specified by <code>button</code>
   * Also displays the feedback label.
   * If statements determine which button to disable.
   * 
   * @param button char the button to be disabled.
   */
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
  /**
   * Disables the destination specified by <code>button</code>
   * Also displays the feedback label.
   * If statements determine which button to disable.
   * 
   * @param button int the destination to be disabled.
   */
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
  /**
   * Sets the current question. Also hides the feedback label.
   * 
   * @param q Question the new Question.
   */
  public void setQuestion(Question q)
  {
    currentQuestion=q;
    questionLabel.setText(q.getQuestion());
    feedbackLabel.setVisible(false);
  }
  /**
   * Shuffles the destination buttons in a random order.
   * 
   * If statements determine which arrangement should be used.
   * 
   * <b> Local variables </b>
   * <p>
   * <b> index </b> int - the new location of the correct answer.
   */
  private void shuffleButtons()
  {
    int index=(int)(Math.random()*3);
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
  /**
   * Switches the game panel to the Country stage.
   */
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
    questionLabel.setBounds(380,100,600,100);
    revalidate();
  }
  /**
   * Switches the game panel to the Map stage.
   */
  public void switchToMap()
  {
    feedbackLabel.setVisible(false);
    d.setVisible(false);
    a.setText(destinations[0].getName());
    b.setText(destinations[1].getName());
    c.setText(destinations[2].getName());
    currentClue=destinations[0].getRandClue();
    questionLabel.setText(currentClue);
    atQuestionStage=false;
    shuffleButtons();
    questionLabel.setBounds(650-questionLabel.getPreferredSize().width,120,600,30);
    mapImageLabel.setVisible(true);
    mapImageLabel.repaint();
    revalidate();
  }
  /**
   * Switches the game panel to a Paused state.
   */
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
  /**
   * Unpauses the game panel.
   * 
   * If statement determines whether to show the questionor the clue.
   * If statement determines whether to display choice D.
   */
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
  /**
   * Returns choice A.
   * 
   * @return JButton choice A.
   */
  public JButton getAButton()
  {
    return a;
  }
  /**
   * Returns choice B.
   * 
   * @return JButton choice B.
   */
  public JButton getBButton()
  {
    return b;
  }
  /**
   * Returns choice C.
   * 
   * @return JButton choice C.
   */
  public JButton getCButton()
  {
    return c;
  }
  /**
   * Returns choice D.
   * 
   * @return JButton choice D.
   */
  public JButton getDButton()
  {
    return d;
  }
  /**
   * Returns the pause button.
   * 
   * @return JButton the pause button.
   */
  public JButton getPauseButton ()
  {
    return pauseButton;
  }
  /**
   * Returns the destinations currently displayed.
   * 
   * @return Country[] the destinations currently being displayed.
   */
  public Country[] getDestinations ()
  {
    return destinations;
  }
  /**
   * Sets the new destinations for the game panel.
   * 
   * @param c Country[] the new destinations.
   */
  public void setDestinations (Country[]c)
  {
    destinations=c;
  }
  /**
   * Returns the answer to the question.
   * 
   * @return int the answer to the question
   */
  public int getAnswer()
  {
    return answer;
  }
  /**
   * Returns the GameTimer instance associated with this panel.
   * 
   * @return GameTimer the GameTimer instance associated with this panel.
   */
  public GameTimer getTimer()
  {
    return timer;
  }
  /**
   * Sets up a new Game Panel.
   * 
   * @param difficulty int the difficulty of the game.
   * @param source ScarletGemMain the Main JFrame class.
   */
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
    a.setBounds(380,220,a.getPreferredSize().height+20,a.getPreferredSize().width);
    b.setBounds(500,220,b.getPreferredSize().height+20,b.getPreferredSize().width);
    c.setBounds(308,320,c.getPreferredSize().height+20,c.getPreferredSize().width);
    d.setBounds(500,320,d.getPreferredSize().height+20,d.getPreferredSize().width);
    questionLabel.setBounds(380,100,600,100);
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
