import java.awt.*;
import javax.swing.*;
import java.awt.print.*;
import javax.imageio.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
/**
 * This is the 'Main' class for this project. All of the event listeners used
 * in the game are implemented by this class. This class will respond to any buttons pressed
 * in any other classes. This class acts as the "brain" of the project: it does all of the logic,
 * processing and recordkeeping. The other classes are just utility classes that help to store temporary
 * game data (such as the question sets for each country) and display the GUI.
 * 
 * @author Zachary Teper and Angela Zhu
 * @version 1.0 17.05.15
 */
public class ScarletGemMain extends JFrame implements ActionListener, Printable, KeyListener, MouseListener,
  WindowListener
{
  /* currentCountry index:
   * 0 - Canada
   * 1 - China
   * 2 - USA
   * 3 - Mexico
   * 4 - Portugal
   * 5 - Australia
   * 6 - Egypt
   * 7 - India
   * 8 - Russia
   * 9 - Japan
   * 10 - France
   * 11 - England
   */
  
  /**
   * Holds the country that the user is currently in. This value will be reassigned
   * to a new value every time the user completes a level.
   */
  private Country currentCountry;
  /**
   * Holds the number representing the difficulty chosen by the user at the start of the game.
   * 0 represents easy. 1 represents medium and 2 represents hard.
   */
  private int difficulty;
  /**
   * Holds the number of levels that the user has left. In easy mode, this is initialized to 3.
   * In medium mode, this is initialized to 6. In hard mode, this is initialized to 9. When <code>
   * difficulty</code> reaches 0, the game ends.
   * It is initialized to -1 in order to check whether the user started the game or not.
   */
  private int levelsRemaining = -1;
  /**
   * Flag which will determine if the game is "paused". The can be changed by pressing the pause button
   * at the top of each game screen.
   */
  private boolean paused;
  /**
   * Holds the instances of each country that the player visited. This makes sure that the player will
   * not visit the same country twice.
   */
  private ArrayList <Country> alreadyBeen;
  /**
   * Holds the instances of each country in the game. These are the indexes of each country in this array:
   * <p>
   * <ul>
   * <li>0 - Canada
   * <li>1 - China
   * <li>2 - Mexico
   * <li>3 - USA
   * <li>4 - Portugal
   * <li>5 - Australia
   * <li>6 - Egypt
   * <li>7 - India
   * <li>8 - Russia
   * <li>9 - Japan
   * <li>10 - France
   * <li>11 - England
   * </ul>
   */
  public static final Country[] countries=new Country[12];
  /**
   * Holds the instance of the GamePanel class. This is initialized only once per game, and contains the buttons
   * and menus used in the game.
   */
  private GamePanel gamePanel;
  /**
   * Displays the number of levels that the user still has to complete in order to win the game.
   * Will be refreshed every time the user travels to a new country.
   */
  private JLabel levelCounter=new JLabel();
  /**
   * Holds the instance of the MainMenuPanel, which contains the buttons at the start of the game.
   * This is only initialized once, and reappears every time the user completes a level.
   */
  private MainMenuPanel mainMenuPanel;
  /**
   * Holds the instance of the HighScoresViewer which the user can see at any time
   * using the menu option or by pressing Ctrl+E. This is only initialized once.
   */
  private HighScoresViewer highScoresViewer;
  /**
   * Holds the instance of the InstructionsViewer which the user can see at any time
   * using the menu option or by pressing Ctrl+R. This is only initialized once.
   */
  private InstructionsViewer instructionsViewer;
  /**
   * Holds the number 1 or 0, depending on which question the user is currently on.
   */
  private int currentQuestion=0;
  /**
   * Opens the Instructions viewer when the user selects this menu choice.
   */
  private JMenuItem howToPlayItem =new JMenuItem ("How To Play");
  /**
   * Opens the Print dialog when the user selects this menu choice.
   */
  private JMenuItem printItem=new JMenuItem ("Print");
  /**
   * Saves the user's progress when the user selects this menu choice.
   */
  private JMenuItem saveItem=new JMenuItem ("Save");
  /**
   * Closes the game window when the user selects this menu choice.
   */
  private JMenuItem exitItem=new JMenuItem ("Exit");
  /**
   * Opens the High Scores viewer when the user selects this menu choice.
   */
  private JMenuItem highScoresItem=new JMenuItem ("View High Scores");
  /**
   * Opens the .chm help file when the user selects this menu choice.
   */
  private JMenuItem helpItem=new JMenuItem ("Help");
  /**
   * Opens the About dialog when the user selects this menu choice.
   */
  private JMenuItem aboutItem=new JMenuItem ("About");
  
  public void windowDeactivated(WindowEvent e)
  {
  }
  public void windowOpened (WindowEvent e)
  {
  }
  public void windowClosing (WindowEvent e)
  {
    closeWarning();
  }
  public void windowActivated (WindowEvent e)
  {
  }
  public void windowDeiconified (WindowEvent e)
  {
  }
  public void windowIconified(WindowEvent e)
  {
  }
  public void windowClosed (WindowEvent e)
  {
  }
  /**
   * Processes action events from all of the Buttons and Menus throughout the game.
   * Called by the JVM when a Button or Menu is pressed.
   * If statements control which block of code will be executed based on <code> ae</code>
   * 
   * @param ae ActionEvent
   */
  public void actionPerformed(ActionEvent ae)
  {
    if (ae.getSource().equals(exitItem))
    {
      closeWarning();
    }
    else if (ae.getSource().equals(saveItem))
    {
      save();
    }
    else if (ae.getSource().equals(printItem))
    {
    }
    else if (ae.getSource().equals(howToPlayItem))
    {
      new InstructionsViewer();
    }
    else if (ae.getSource().equals(helpItem))
    {
      File file = new File("TmpHtml/help.chm");
      try
      {
        Runtime.getRuntime().exec("HH.EXE ms-its:" + file.getAbsolutePath() + "::/TOPIC_ID.html");
      } catch (IOException e1)
      {
        e1.printStackTrace();
      }
    }
    else if (ae.getSource().equals(aboutItem))
    {
      JFrame about=new JFrame("About");
      JPanel aboutPanel=new JPanel();
      JLabel name=new JLabel ("Game: The Scarlet Gem");
      JLabel author1=new JLabel("Project Lead: Zachary Teper");
      JLabel author2=new JLabel("Project Representative: Angela Zhu");
      JLabel version=new JLabel("Version: 1.0 06.09.2015");
      JLabel email=new JLabel("Email: zacharyblacktail@gmail.com");
      JLabel phone=new JLabel("Phone: 416-223-6075");
      ImageIcon logo=new ImageIcon ("pics/CakeSoft Inc.png");
      JLabel logoLabel=new JLabel();
      
      about.setSize(400,200);
      about.add(aboutPanel);
      about.setResizable(false);
      about.setVisible(true);
      about.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      aboutPanel.setLayout(new FlowLayout());
      logoLabel.setIcon(logo);
      aboutPanel.add(logoLabel);
      aboutPanel.add(name);
      aboutPanel.add(author1);
      aboutPanel.add(author2);
      aboutPanel.add(version);
      aboutPanel.add(email);
      aboutPanel.add(phone);
    }
    else if (ae.getSource().equals(highScoresItem))
    {
    }
    else if (ae.getSource().equals(mainMenuPanel.getEasyButton()))
    {
      levelsRemaining=3;
      difficulty=0;
      initializeGame();     
    }
    else if (ae.getSource().equals(mainMenuPanel.getMediumButton()))
    {
      levelsRemaining=6;
      difficulty=1;
      initializeGame();
    }
    else if (ae.getSource().equals(mainMenuPanel.getHardButton()))
    {
      levelsRemaining=9;
      difficulty=2;
      initializeGame();     
    }
    else if (ae.getSource().equals(mainMenuPanel.getLoadButton()))
    {
      System.out.println ("load");
      try
      {
        BufferedReader in=new BufferedReader(new FileReader("progress.txt"));
        difficulty=Integer.parseInt(in.readLine());
        in.readLine();
        
        String next;
        while(true)
        {
          next=in.readLine();
          if (next.equals(""))
            break;
          alreadyBeen.add(getCountry(next));
        }
        //in.readLine();
        
        
        currentCountry=getCountry(in.readLine());
        in.readLine();
        GameTimer.timeRemaining=(Integer.parseInt(in.readLine()));
        in.readLine();
        currentQuestion=Integer.parseInt(in.readLine());
        System.out.println (currentCountry.getName());
        
      }
      catch (IOException e)
      {
        JOptionPane.showMessageDialog(null,"File could not be loaded.");
      }
      int total;
      if (difficulty==0)
        total=4;
      else if (difficulty==1)
        total=7;
      else
        total=10;
      levelsRemaining=total-alreadyBeen.size();
      initializeGame();
    }
    else if (ae.getSource().equals(gamePanel.getAButton()))
    {
      checkAnswer('A');
    }
    else if (ae.getSource().equals(gamePanel.getBButton()))
    {
      checkAnswer ('B');
    }
    else if (ae.getSource().equals(gamePanel.getCButton()))
    {
      checkAnswer ('C');
    }
    else if (ae.getSource().equals(gamePanel.getDButton()))
    {
      checkAnswer ('D');
    }
    else if (ae.getActionCommand().equals("loss"))
    {
      JOptionPane.showMessageDialog(null,"Sorry, yoiu ran out of time!");
      endGame();
    }
    revalidate();
  }
  private Country getCountry(String name)
  {
    if (name.equals("Canada"))
      return countries[0];
    else if (name.equals("China"))
      return countries[1];
    else if (name.equals("USA"))
      return countries[2];
    else if (name.equals("Mexico"))
      return countries[3];
    else if (name.equals("Portugal"))
      return countries[4];
    else if (name.equals("Australia"))
      return countries[5];
    else if (name.equals("Egypt"))
      return countries[6];
    else if (name.equals("India"))
      return countries[7];
    else if (name.equals("Russia"))
      return countries[8];
    else if (name.equals("Japan"))
      return countries[9];
    else if (name.equals("France"))
      return countries[10];
    return countries[11];
  }
  private void closeWarning()
  {
    if (levelsRemaining != -1)
    {
      int option=JOptionPane.showConfirmDialog(this,
                                               "Do you want to save your progress?",
                                               "Save?",
                                               JOptionPane.YES_NO_CANCEL_OPTION);
      if (option==JOptionPane.YES_OPTION)
      {
        save();
        System.exit(0);
      }
      else
      {
        if (option==JOptionPane.NO_OPTION)
          System.exit(0);
      }
    }
    else
    {
      System.exit (0);
    }
  }
  private void save()
  {
    System.out.println ("save");
    try
    {
      PrintWriter out=new PrintWriter(new FileWriter("progress.txt"));
      out.println (difficulty);
      out.println ();
      for (Country s:alreadyBeen)
        out.println(s.getName());
      out.println();
      out.println (currentCountry.getName());
      
      out.println();
            
      out.println (GameTimer.timeRemaining);
      out.println ();
      out.println (currentQuestion);
      out.close();
    }
    catch (IOException e)
    {
      JOptionPane.showMessageDialog(null,"Progress could not be saved.");
    }
  }
  
  private void initializeGame ()
  {
    remove(mainMenuPanel);
    add(levelCounter);
    gamePanel = new GamePanel (difficulty,this);
    add (gamePanel);
    gamePanel.getPauseButton ().addActionListener (this);
    gamePanel.getAButton().addActionListener(this);
    gamePanel.getBButton().addActionListener(this);
    gamePanel.getCButton().addActionListener(this);
    gamePanel.getDButton().addActionListener(this);
    gamePanel.getPauseButton().setEnabled (true);
    showCountryPanel();    
  }
  
  private void showCountryPanel()
  {
    
    System.out.println (currentCountry.getName());
    try
    {
      Thread.sleep(500);
    }
    catch (InterruptedException e)
    {
    }
    gamePanel.setBackground(currentCountry.getBackground());
    if (!alreadyBeen.contains(currentCountry))
      currentQuestion=0;
    
    currentQuestion=0;
    
    if (levelsRemaining==0)
    {
      JOptionPane.showMessageDialog(null,"You Win");
      endGame();
    }
    else
    {
      //Question temp=
      gamePanel.setQuestion(currentCountry.getRandQuestion(difficulty));
      gamePanel.switchToCountry();
      //gamePanel.setQuestion(currentCountry.getRandQuestion(difficulty));
      gamePanel.getAButton().setEnabled(true);
      gamePanel.getBButton().setEnabled(true);
      gamePanel.getCButton().setEnabled(true);
      gamePanel.getDButton().setEnabled(true);
      revalidate();
    }
  }
  /**
   * Creates the 3 destination options for the Map screen and displays it.
   * While loops search for countries that have not been visited in the game.
   */ 
  private void showMapPanel()
  {
//    for (Country s:alreadyBeen)
//      System.out.println(s.getName());
    try
    {
      Thread.sleep(500);
    }
    catch (InterruptedException e)
    {
    }
    do
    {
      System.out.println ("search");
      currentCountry=countries[(int)(Math.random()*10)];
    }
    while (alreadyBeen.contains(currentCountry));
    
    Country wrong1;
    Country wrong2;
    do
    {
      System.out.println ("search2");
      wrong1=countries[(int)(Math.random()*10)];
    }
    while (alreadyBeen.contains(wrong1)||wrong1.equals(currentCountry));
    
    do
    {
      System.out.println ("search3");
      wrong2=countries[(int)(Math.random()*10)];
    }
    while (alreadyBeen.contains(wrong2)||wrong1.equals(wrong2)
             ||wrong2.equals(currentCountry));
    //System.out.println ("worng: "+wrong2.getName());
    gamePanel.setDestinations(new Country[]{currentCountry,wrong1,wrong2});
    gamePanel.switchToMap();
    gamePanel.getAButton().setEnabled(true);
    gamePanel.getBButton().setEnabled(true);
    gamePanel.getCButton().setEnabled(true);
    revalidate();
    
    //countries[(int)(Math.random()*
    
  }
  /**
   * Checks if the answer selected by the user is correct. If it is, the next screen is displayed.
   * If not, the choice is eliminated time is deducted from the user's score.
   * 
   * @param answer char the answer that the user selected
   */
  private void checkAnswer (char answer)
  {
    if (gamePanel.getStage())
    {
      if (gamePanel.getQuestion().getAnswer()==answer)
      {
        if (currentQuestion==0)
        {
          System.out.println ("More");
          gamePanel.getAButton().setEnabled(true);
          gamePanel.getBButton().setEnabled(true);
          gamePanel.getCButton().setEnabled(true);
          gamePanel.getDButton().setEnabled(true);
          Question temp=currentCountry.getRandQuestion(difficulty);
          while (temp==gamePanel.getQuestion())
          {
            temp=currentCountry.getRandQuestion(difficulty);
          }
          gamePanel.setQuestion(temp);
          currentQuestion++;
        }
        else
        {
          System.out.println ("Exec");
          levelsRemaining--;
          System.out.println (levelsRemaining);
          alreadyBeen.add(currentCountry);
          showMapPanel();
        }
      }
      else
      {
        System.out.println ("remove");
        gamePanel.removeWrongAnswer(answer);
      }
    }
    else
    {
      if (answer-65==gamePanel.getAnswer())
      {
        showCountryPanel();
        System.out.println ("show country");
      }
      else
      {
        gamePanel.removeWrongDestination(answer-65);
        System.out.println ("remove country " + (answer-65));
      }
    }
  }
  public void endGame()
  {
    gamePanel.timer.setGameWon (true);
    remove(gamePanel);
    remove(levelCounter);
    add(mainMenuPanel);
    levelsRemaining = -1;
    currentCountry=countries[0];
    alreadyBeen=new ArrayList<Country>();
    revalidate();
  }
  public void keyReleased (KeyEvent e)
  {
  }
  public void keyTyped (KeyEvent e)
  {
  }
  public void keyPressed (KeyEvent e)
  {
  }
  public void mouseExited (MouseEvent e)
  {
  }
  public void mouseEntered (MouseEvent e)
  {
  }
  public void mouseClicked (MouseEvent e)
  {
  }
  public void mousePressed (MouseEvent e)
  {
  }
  public void mouseReleased (MouseEvent e)
  {
  }
  public int print(Graphics page, PageFormat format, int copies)
  {
    return PAGE_EXISTS;
  }
  /**
   * Displays the splash screen, initializes the game variables and displays the main menu.
   * Adds ActionListeners and KeyListeners to game Objects.
   */
  public ScarletGemMain()
  {
    super ("The Scarlet Gem");
    setVisible (true);
    setSize (700,600);
    setResizable(false);
    setDefaultCloseOperation (JFrame.DO_NOTHING_ON_CLOSE);
    requestFocusInWindow();
    
    //setLayout(null);
    
    //set the JFrame icon and read splashImage
    ImageIcon splashImage=null;
    try
    {
      setIconImage (ImageIO.read (new File ("pics/scarlet-gem.png")));
      splashImage=new ImageIcon("pics/scarlet-gem.png");
    }
    catch (IOException e)
    {
      System.out.println ("Image IO");
    }
    //show splashscreen
    JLabel imageLabel=new JLabel();
    JLabel infoLabel=new JLabel();
    JPanel splashScreen=new JPanel();
    
    infoLabel.setText("The Scarlet Gem is Loading...");
    imageLabel.setIcon(splashImage);
    splashScreen.add(imageLabel);
    splashScreen.add(infoLabel);
    add(splashScreen);
    revalidate();
    
    //initialize countries
    Question[] canadaEasy=
    {new Question ("<html>What is the capital of Canada?"+
                   "<br> A. Toronto"+
                   "<br> B. Montreal"+
                   "<br> C. Ottawa"+
                   "<br> D. Portugal</html>"
                     ,'C'),
      new Question ("<html>In what part of Canada is Newfoundland located?"+
                    "<br> A. North"+
                    "<br> B. West"+
                    "<br> C. South"+
                    "<br> D. East</html>"
                      ,'D'),
      new Question ("<html>In what part of Canada is Nunavut?"+
                    "<br> A. North"+
                    "<br> B. West"+
                    "<br> C. South"+
                    "<br> D. East</html>"
                      ,'A'),
      new Question ("<html>What continent is Canada part of?"+
                    "<br> A. Europe"+
                    "<br> B. North America"+
                    "<br> C. Africa"+
                    "<br> D. Asia</html>"
                      ,'B'),
      new Question ("<html>What are the two official languages of Canada?"+
                    "<br> A. Spanish & French"+
                    "<br> B. German & English"+
                    "<br> C. English & Spanish"+
                    "<br> D. French & English</html>"
                      ,'D')
    };
    Question[] canadaMedium=
    {new Question ("<html>CanadaMedium1"+
                   "<br> A. Toronto"+
                   "<br> B. Montreal"+
                   "<br> C. Ottawa"+
                   "<br> D. Portugal</html>"
                     ,'C'),
      new Question ("<html>CanadaMedium2"+
                    "<br> A. North"+
                    "<br> B. West"+
                    "<br> C. South"+
                    "<br> D. East</html>"
                      ,'D'),
      new Question ("<html>CanadaMedium3"+
                    "<br> A. North"+
                    "<br> B. West"+
                    "<br> C. South"+
                    "<br> D. East</html>"
                      ,'A'),
      new Question ("<html>CanadaMedium4"+
                    "<br> A. Europe"+
                    "<br> B. North America"+
                    "<br> C. Africa"+
                    "<br> D. Asia</html>"
                      ,'B'),
      new Question ("<html>CanadaMedium5"+
                    "<br> A. Spanish & French"+
                    "<br> B. German & English"+
                    "<br> C. English & Spanish"+
                    "<br> D. French & English</html>"
                      ,'D')
    };
    Question[] canadaHard=
    {new Question ("<html>CanadaHard1"+
                   "<br> A. Toronto"+
                   "<br> B. Montreal"+
                   "<br> C. Ottawa"+
                   "<br> D. Portugal</html>"
                     ,'C'),
      new Question ("<html>CanadaHard2"+
                    "<br> A. North"+
                    "<br> B. West"+
                    "<br> C. South"+
                    "<br> D. East</html>"
                      ,'D'),
      new Question ("<html>CanadaHard3"+
                    "<br> A. North"+
                    "<br> B. West"+
                    "<br> C. South"+
                    "<br> D. East</html>"
                      ,'A'),
      new Question ("<html>CanadaHard4"+
                    "<br> A. Europe"+
                    "<br> B. North America"+
                    "<br> C. Africa"+
                    "<br> D. Asia</html>"
                      ,'B'),
      new Question ("<html>CanadaHard5"+
                    "<br> A. Spanish & French"+
                    "<br> B. German & English"+
                    "<br> C. English & Spanish"+
                    "<br> D. French & English</html>"
                      ,'D')
    };
    try
    {
      Country canada =new Country ("Canada",canadaEasy, canadaMedium,
                                   canadaHard, ImageIO.read (new File ("pics/canada.jpg")),
                                   new String[]{"","",""});
      countries [0]=canada;
    }
    catch (IOException e)
    {
      System.out.println ("IO");
    }
    //initialize China
    Question[] chinaEasy=
    {new Question ("<html>What is the capital of China?"+
                   "<br> A. Beijing"+
                   "<br> B. Shanghai"+
                   "<br> C. Bangkok"+
                   "<br> D. Tokyo</html>"
                     ,'A'),
      new Question ("<html>Which ocean is off the coast of China?"+
                    "<br> A. Atlantic"+
                    "<br> B. Indian"+
                    "<br> C. Pacific"+
                    "<br> D. Arctic</html>"
                      ,'C'),
      new Question ("<html>In what part of China are the Himalaya Mountains?"+
                    "<br> A. North"+
                    "<br> B. West"+
                    "<br> C. South"+
                    "<br> D. East</html>"
                      ,'B'),
      new Question ("<html>What continent is China part of?"+
                    "<br> A. Europe"+
                    "<br> B. North America"+
                    "<br> C. Africa"+
                    "<br> D. Asia</html>"
                      ,'D'),
      new Question ("<html>What is the main agricultural crop of China?"+
                    "<br> A. Tomatoes"+
                    "<br> B. Rice"+
                    "<br> C. Radishes"+
                    "<br> D. Corn</html>"
                      ,'B')
    };
    Question[] chinaMedium=
    {new Question ("<html>ChinaMedium1"+
                   "<br> A. Beijing"+
                   "<br> B. Shanghai"+
                   "<br> C. Bangkok"+
                   "<br> D. Tokyo</html>"
                     ,'A'),
      new Question ("<html>chinaMedium2"+
                    "<br> A. Atlantic"+
                    "<br> B. Indian"+
                    "<br> C. Pacific"+
                    "<br> D. Arctic</html>"
                      ,'C'),
      new Question ("<html>chinaMedium3"+
                    "<br> A. North"+
                    "<br> B. West"+
                    "<br> C. South"+
                    "<br> D. East</html>"
                      ,'B'),
      new Question ("<html>chinaMedium4"+
                    "<br> A. Europe"+
                    "<br> B. North America"+
                    "<br> C. Africa"+
                    "<br> D. Asia</html>"
                      ,'D'),
      new Question ("<html>chinaMedium5"+
                    "<br> A. Tomatoes"+
                    "<br> B. Rice"+
                    "<br> C. Radishes"+
                    "<br> D. Corn</html>"
                      ,'B')
    };
    Question[] chinaHard=
    {new Question ("<html>ChinaHard1"+
                   "<br> A. Beijing"+
                   "<br> B. Shanghai"+
                   "<br> C. Bangkok"+
                   "<br> D. Tokyo</html>"
                     ,'A'),
      new Question ("<html>chinaHard2"+
                    "<br> A. Atlantic"+
                    "<br> B. Indian"+
                    "<br> C. Pacific"+
                    "<br> D. Arctic</html>"
                      ,'C'),
      new Question ("<html>chinaHard3"+
                    "<br> A. North"+
                    "<br> B. West"+
                    "<br> C. South"+
                    "<br> D. East</html>"
                      ,'B'),
      new Question ("<html>chinaHard4"+
                    "<br> A. Europe"+
                    "<br> B. North America"+
                    "<br> C. Africa"+
                    "<br> D. Asia</html>"
                      ,'D'),
      new Question ("<html>chinaHard5"+
                    "<br> A. Tomatoes"+
                    "<br> B. Rice"+
                    "<br> C. Radishes"+
                    "<br> D. Corn</html>"
                      ,'B')
    };
    try
    {
      Country china =new Country ("China",chinaEasy, chinaMedium, chinaHard, ImageIO.read (new File ("pics/china.jpg")),
                                  new String[]{"I am in the country with the largest population in the world."
        ,"I am in the counry whose capital is Beijing."
                                    ,"I am in the country where bamboo forests, pandas and the Asian Black Bear"+
                                    " can be found.",
                                    "I am in the country which was ruled by over 15 different dynasties over the"+
                                    "course of 5000 years.",
                                    "I am in the country in which the city of Shanghai is located."
      });
      countries [1]=china;
    }
    catch (IOException e)
    {
      System.out.println ("IO");
    }
    //initialize USA
    Question[] usaEasy=
    {new Question ("<html>What is the capital of the USA?"+
                   "<br> A. Boston"+
                   "<br> B. New York"+
                   "<br> C. Texas"+
                   "<br> D. Washington D.C.</html>"
                     ,'D'),
      new Question ("<html>In what part of the USA is New Jersey located?"+
                    "<br> A. North"+
                    "<br> B. West"+
                    "<br> C. East"+
                    "<br> D. South</html>"
                      ,'C'),
      new Question ("<html>In which state is San Fransisco located?"+
                    "<br> A. Texas"+
                    "<br> B. California"+
                    "<br> C. Mexico"+
                    "<br> D. Florida</html>"
                      ,'B'),
      new Question ("<html>What continent is the USA part of?"+
                    "<br> A. Europe"+
                    "<br> B. Asia"+
                    "<br> C. Africa"+
                    "<br> D. North America</html>"
                      ,'D'),
      new Question ("<html>What is the official language of the USA?"+
                    "<br> A. English"+
                    "<br> B. Spanish"+
                    "<br> C. French"+
                    "<br> D. Chinese</html>"
                      ,'A')
    };
    Question[] usaMedium=
    {new Question ("<html>USAMedium1"+
                   "<br> A. Boston"+
                   "<br> B. New York"+
                   "<br> C. Texas"+
                   "<br> D. Washington D.C.</html>"
                     ,'D'),
      new Question ("<html>USAMedium2"+
                    "<br> A. North"+
                    "<br> B. West"+
                    "<br> C. East"+
                    "<br> D. South</html>"
                      ,'C'),
      new Question ("<html>USAMedium3"+
                    "<br> A. Texas"+
                    "<br> B. California"+
                    "<br> C. Mexico"+
                    "<br> D. Florida</html>"
                      ,'B'),
      new Question ("<html>USAMedium4"+
                    "<br> A. Europe"+
                    "<br> B. Asia"+
                    "<br> C. Africa"+
                    "<br> D. North America</html>"
                      ,'D'),
      new Question ("<html>USAMedium5"+
                    "<br> A. English"+
                    "<br> B. Spanish"+
                    "<br> C. French"+
                    "<br> D. Chinese</html>"
                      ,'A')
    };
    Question[] usaHard=
    {new Question ("<html>USAHard1"+
                   "<br> A. Boston"+
                   "<br> B. New York"+
                   "<br> C. Texas"+
                   "<br> D. Washington D.C.</html>"
                     ,'D'),
      new Question ("<html>USAHard2"+
                    "<br> A. North"+
                    "<br> B. West"+
                    "<br> C. East"+
                    "<br> D. South</html>"
                      ,'C'),
      new Question ("<html>USAHard3"+
                    "<br> A. Texas"+
                    "<br> B. California"+
                    "<br> C. Mexico"+
                    "<br> D. Florida</html>"
                      ,'B'),
      new Question ("<html>USAHard4"+
                    "<br> A. Europe"+
                    "<br> B. Asia"+
                    "<br> C. Africa"+
                    "<br> D. North America</html>"
                      ,'D'),
      new Question ("<html>USAHard5"+
                    "<br> A. English"+
                    "<br> B. Spanish"+
                    "<br> C. French"+
                    "<br> D. Chinese</html>"
                      ,'A')
    };
    try
    {
      Country usa =new Country ("USA",usaEasy, usaMedium, usaHard, ImageIO.read (new File ("pics/USA.jpg")),
                                new String[]
                                  {"I am in the country in which Chicago is located.",
        "I am in the country with the largest military in the world.",
                                    "I am in the country whose national flower is the Rose.",
                                    "I am in the country whose flag contains stars and stripes.",
                                    "I am in the country which contains 50 states."
      });
      countries [2]=usa;
    }
    catch (IOException e)
    {
      System.out.println ("IO");
    }
    //initialize mexico
    Question[] mexicoEasy=
    {new Question ("<html>What is the capital of Mexico?"+
                   "<br> A. Peru"+
                   "<br> B. Mexico City"+
                   "<br> C. Monterrey"+
                   "<br> D. Cancun</html>"
                     ,'B'),
      new Question ("<html>Which tribal group is indigenous to Mexico?"+
                    "<br> A. Inuit"+
                    "<br> B. Hindi"+
                    "<br> C. Portugal"+
                    "<br> D. Aztec</html>"
                      ,'D'),
      new Question ("<html>Which country is directly north of Mexico?"+
                    "<br> A. USA"+
                    "<br> B. Chile"+
                    "<br> C. Africa"+
                    "<br> D. Arctic</html>"
                      ,'A'),
      new Question ("<html>What continent is Mexico part of?"+
                    "<br> A. Europe"+
                    "<br> B. North America"+
                    "<br> C. Africa"+
                    "<br> D. Asia</html>"
                      ,'B'),
      new Question ("<html>What is the official language of Mexico?"+
                    "<br> A. French"+
                    "<br> B. German"+
                    "<br> C. English"+
                    "<br> D. Spanish</html>"
                      ,'D')
    };
    Question[] mexicoMedium=
    {new Question ("<html>mexicoMedium1"+
                   "<br> A. Peru"+
                   "<br> B. Mexico City"+
                   "<br> C. Monterrey"+
                   "<br> D. Cancun</html>"
                     ,'B'),
      new Question ("<html>mexicoMedium2"+
                    "<br> A. Inuit"+
                    "<br> B. Hindi"+
                    "<br> C. Portugal"+
                    "<br> D. Aztec</html>"
                      ,'D'),
      new Question ("<html>mexicoMedium3"+
                    "<br> A. USA"+
                    "<br> B. Chile"+
                    "<br> C. Africa"+
                    "<br> D. Arctic</html>"
                      ,'A'),
      new Question ("<html>mexicoMedium4"+
                    "<br> A. Europe"+
                    "<br> B. North America"+
                    "<br> C. Africa"+
                    "<br> D. Asia</html>"
                      ,'B'),
      new Question ("<html>mexicoMedium5"+
                    "<br> A. French"+
                    "<br> B. German"+
                    "<br> C. English"+
                    "<br> D. Spanish</html>"
                      ,'D')
    };
    Question[] mexicoHard=
    {new Question ("<html>mexicoHard1"+
                   "<br> A. Peru"+
                   "<br> B. Mexico City"+
                   "<br> C. Monterrey"+
                   "<br> D. Cancun</html>"
                     ,'B'),
      new Question ("<html>mexicoHard2"+
                    "<br> A. Inuit"+
                    "<br> B. Hindi"+
                    "<br> C. Portugal"+
                    "<br> D. Aztec</html>"
                      ,'D'),
      new Question ("<html>mexicoHard3"+
                    "<br> A. USA"+
                    "<br> B. Chile"+
                    "<br> C. Africa"+
                    "<br> D. Arctic</html>"
                      ,'A'),
      new Question ("<html>mexicoHard4"+
                    "<br> A. Europe"+
                    "<br> B. North America"+
                    "<br> C. Africa"+
                    "<br> D. Asia</html>"
                      ,'B'),
      new Question ("<html>mexicoHard5"+
                    "<br> A. French"+
                    "<br> B. German"+
                    "<br> C. English"+
                    "<br> D. Spanish</html>"
                      ,'D')
    };
    try
    {
      Country mexico =new Country ("Mexico",mexicoEasy, mexicoMedium,
                                   mexicoHard, ImageIO.read (new File ("pics/mexico.jpeg")),
                                   new String[]{
        "I am in the country which was home to the Maya and Aztec people.",
          "I am in the country in which corn was first grown as a crop.",
          "I am in the country whose national bird is the Golden Eagle.",
          "I am in the southernmost country in North America.",
          "I am in the country whose states include Tabasco, Oaxaca and Morelos."
      });
      countries [3]=mexico;
    }
    catch (IOException e)
    {
      System.out.println ("IO");
    }
    //initialize Portugal
    Question[] portugalEasy=
    {new Question ("<html>What is the capital of Portugal?"+
                   "<br> A. Barcelona"+
                   "<br> B. Lisbon"+
                   "<br> C. Madrid"+
                   "<br> D. Madeira</html>"
                     ,'B'),
      new Question ("<html>Which ocean is off the coast of Portugal?"+
                    "<br> A. Arctic"+
                    "<br> B. Pacific"+
                    "<br> C. Indian"+
                    "<br> D. Atlantic</html>"
                      ,'D'),
      new Question ("<html>Which country is directly east of Portugal?"+
                    "<br> A. Spain"+
                    "<br> B. Chile"+
                    "<br> C. Africa"+
                    "<br> D. Arctic</html>"
                      ,'A'),
      new Question ("<html>What continent is Portugal part of?"+
                    "<br> A. North America"+
                    "<br> B. Europe"+
                    "<br> C. Africa"+
                    "<br> D. Asia</html>"
                      ,'B'),
      new Question ("<html>What is the official language of Portugal?"+
                    "<br> A. French"+
                    "<br> B. German"+
                    "<br> C. English"+
                    "<br> D. Portuguese</html>"
                      ,'D')
    };
    Question[] portugalMedium=
    {new Question ("<html>portugalMedium1"+
                   "<br> A. Barcelona"+
                   "<br> B. Lisbon"+
                   "<br> C. Madrid"+
                   "<br> D. Madeira</html>"
                     ,'B'),
      new Question ("<html>PortugalMedium2"+
                    "<br> A. Arctic"+
                    "<br> B. Pacific"+
                    "<br> C. Indian"+
                    "<br> D. Atlantic</html>"
                      ,'D'),
      new Question ("<html>portugalMedium3"+
                    "<br> A. Spain"+
                    "<br> B. Chile"+
                    "<br> C. Africa"+
                    "<br> D. Arctic</html>"
                      ,'A'),
      new Question ("<html>portugalMedium4"+
                    "<br> A. North America"+
                    "<br> B. Europe"+
                    "<br> C. Africa"+
                    "<br> D. Asia</html>"
                      ,'B'),
      new Question ("<html>portugalMedium5"+
                    "<br> A. French"+
                    "<br> B. German"+
                    "<br> C. English"+
                    "<br> D. Portuguese</html>"
                      ,'D')
    };
    Question[] portugalHard=
    {new Question ("<html>portugalHard1"+
                   "<br> A. Barcelona"+
                   "<br> B. Lisbon"+
                   "<br> C. Madrid"+
                   "<br> D. Madeira</html>"
                     ,'B'),
      new Question ("<html>PortugalHard2"+
                    "<br> A. Arctic"+
                    "<br> B. Pacific"+
                    "<br> C. Indian"+
                    "<br> D. Atlantic</html>"
                      ,'D'),
      new Question ("<html>portugalHard3"+
                    "<br> A. Spain"+
                    "<br> B. Chile"+
                    "<br> C. Africa"+
                    "<br> D. Arctic</html>"
                      ,'A'),
      new Question ("<html>portugalHard4"+
                    "<br> A. North America"+
                    "<br> B. Europe"+
                    "<br> C. Africa"+
                    "<br> D. Asia</html>"
                      ,'B'),
      new Question ("<html>portugalHard5"+
                    "<br> A. French"+
                    "<br> B. German"+
                    "<br> C. English"+
                    "<br> D. Portuguese</html>"
                      ,'D')
    };
    try
    {
      Country portugal =new Country ("Portugal",portugalEasy, portugalMedium,
                                     portugalHard, ImageIO.read (new File ("pics/portugal.jpg")),
                                     new String[]{
        "I am in the country which contains the Tagus River.",
          "I am in the country in which the Temple of Evora can be found.",
          "I am in the country whose national symbol is the Armillary Sphere.",
          "I am in the westernmost country in Europe.",
          "I am in the country whose districts include Beja, Aveiro and Madeira."
      });
      countries [4]=portugal;
    }
    catch (IOException e)
    {
      System.out.println ("IO");
    }
    //initialize Australia
    Question[] australiaEasy=
    {new Question ("<html>What is the capital of Australia?"+
                   "<br> A. Sydney"+
                   "<br> B. Canberra"+
                   "<br> C. Brisbane"+
                   "<br> D. New Zealand</html>"
                     ,'B'),
      new Question ("<html>Which ocean is off the west coast of Australia?"+
                    "<br> A. Arctic"+
                    "<br> B. Pacific"+
                    "<br> C. Indian"+
                    "<br> D. Atlantic</html>"
                      ,'C'),
      new Question ("<html>Which country is directly north of Australia?"+
                    "<br> A. Indonesia"+
                    "<br> B. Russia"+
                    "<br> C. Africa"+
                    "<br> D. Mexico</html>"
                      ,'A'),
      new Question ("<html>What continent is Australia part of?"+
                    "<br> A. India"+
                    "<br> B. Australia"+
                    "<br> C. Africa"+
                    "<br> D. Asia</html>"
                      ,'B'),
      new Question ("<html>What is the largest city in Australia?"+
                    "<br> A. New Zealand"+
                    "<br> B. Canberra"+
                    "<br> C. Melbourne"+
                    "<br> D. Sydney</html>"
                      ,'D')
    };
    Question[] australiaMedium=
    {new Question ("<html>australiaMedium1"+
                   "<br> A. Sydney"+
                   "<br> B. Canberra"+
                   "<br> C. Brisbane"+
                   "<br> D. New Zealand</html>"
                     ,'B'),
      new Question ("<html>australiaMedium2"+
                    "<br> A. Arctic"+
                    "<br> B. Pacific"+
                    "<br> C. Indian"+
                    "<br> D. Atlantic</html>"
                      ,'C'),
      new Question ("<html>australiaMedium3"+
                    "<br> A. Indonesia"+
                    "<br> B. Russia"+
                    "<br> C. Africa"+
                    "<br> D. Mexico</html>"
                      ,'A'),
      new Question ("<html>australiaMedium4"+
                    "<br> A. India"+
                    "<br> B. Australia"+
                    "<br> C. Africa"+
                    "<br> D. Asia</html>"
                      ,'B'),
      new Question ("<html>australiaMedium5"+
                    "<br> A. New Zealand"+
                    "<br> B. Canberra"+
                    "<br> C. Melbourne"+
                    "<br> D. Sydney</html>"
                      ,'D')
    };
    Question[] australiaHard=
    {new Question ("<html>australiaHard1"+
                   "<br> A. Sydney"+
                   "<br> B. Canberra"+
                   "<br> C. Brisbane"+
                   "<br> D. New Zealand</html>"
                     ,'B'),
      new Question ("<html>australiaHard2"+
                    "<br> A. Arctic"+
                    "<br> B. Pacific"+
                    "<br> C. Indian"+
                    "<br> D. Atlantic</html>"
                      ,'C'),
      new Question ("<html>australiaHard3"+
                    "<br> A. Indonesia"+
                    "<br> B. Russia"+
                    "<br> C. Africa"+
                    "<br> D. Mexico</html>"
                      ,'A'),
      new Question ("<html>australiaHard4"+
                    "<br> A. India"+
                    "<br> B. Australia"+
                    "<br> C. Africa"+
                    "<br> D. Asia</html>"
                      ,'B'),
      new Question ("<html>australiaHard5"+
                    "<br> A. New Zealand"+
                    "<br> B. Canberra"+
                    "<br> C. Melbourne"+
                    "<br> D. Sydney</html>"
                      ,'D')
    };
    try
    {
      Country australia =new Country ("Australia",australiaEasy, australiaMedium,
                                      australiaHard, ImageIO.read (new File ("pics/australia.jpg")),
                                      new String[]{
        "I am in the country in which Ayers Rock can be found.",
          "I am in the country in which the Murray River can be found.",
          "I am in the country which is the world's fourth largest producer of wine.",
          "I am in the country whose name is the same as its continent's name.",
          "I am in the country whose states include New South Wales, Queensland and Victoria."
      });
      countries [5]=australia;
    }
    catch (IOException e)
    {
      System.out.println ("IO");
    }
    //initialize Egypt
    Question[] egyptEasy=
    {new Question ("<html>What is the capital of Egypt?"+
                   "<br> A. Cairo"+
                   "<br> B. Memphis"+
                   "<br> C. Alexandria"+
                   "<br> D. Suez</html>"
                     ,'A'),
      new Question ("<html>EgyptEasy2"+
                    "<br> A. Arctic"+
                    "<br> B. Pacific"+
                    "<br> C. Indian"+
                    "<br> D. Atlantic</html>"
                      ,'C'),
      new Question ("<html>EgyptEasy3"+
                    "<br> A. Indonesia"+
                    "<br> B. Russia"+
                    "<br> C. Africa"+
                    "<br> D. Mexico</html>"
                      ,'A'),
      new Question ("<html>EgyptEasy4"+
                    "<br> A. India"+
                    "<br> B. Australia"+
                    "<br> C. Africa"+
                    "<br> D. Asia</html>"
                      ,'B'),
      new Question ("<html>EgyptEasy5"+
                    "<br> A. New Zealand"+
                    "<br> B. Canberra"+
                    "<br> C. Melbourne"+
                    "<br> D. Sydney</html>"
                      ,'D')
    };
    Question[] egyptMedium=
    {new Question ("<html>egyptMedium1"+
                   "<br> A. Cairo"+
                   "<br> B. Memphis"+
                   "<br> C. Alexandria"+
                   "<br> D. Suez</html>"
                     ,'A'),
      new Question ("<html>EgyptMedium2"+
                    "<br> A. Arctic"+
                    "<br> B. Pacific"+
                    "<br> C. Indian"+
                    "<br> D. Atlantic</html>"
                      ,'C'),
      new Question ("<html>EgyptMedium3"+
                    "<br> A. Indonesia"+
                    "<br> B. Russia"+
                    "<br> C. Africa"+
                    "<br> D. Mexico</html>"
                      ,'A'),
      new Question ("<html>EgyptMedium4"+
                    "<br> A. India"+
                    "<br> B. Australia"+
                    "<br> C. Africa"+
                    "<br> D. Asia</html>"
                      ,'B'),
      new Question ("<html>EgyptMedium5"+
                    "<br> A. New Zealand"+
                    "<br> B. Canberra"+
                    "<br> C. Melbourne"+
                    "<br> D. Sydney</html>"
                      ,'D')
    };
    Question[] egyptHard=
    {new Question ("<html>egyptHard1"+
                   "<br> A. Cairo"+
                   "<br> B. Memphis"+
                   "<br> C. Alexandria"+
                   "<br> D. Suez</html>"
                     ,'A'),
      new Question ("<html>EgyptHard2"+
                    "<br> A. Arctic"+
                    "<br> B. Pacific"+
                    "<br> C. Indian"+
                    "<br> D. Atlantic</html>"
                      ,'C'),
      new Question ("<html>EgyptHard3"+
                    "<br> A. Indonesia"+
                    "<br> B. Russia"+
                    "<br> C. Africa"+
                    "<br> D. Mexico</html>"
                      ,'A'),
      new Question ("<html>EgyptHard4"+
                    "<br> A. India"+
                    "<br> B. Australia"+
                    "<br> C. Africa"+
                    "<br> D. Asia</html>"
                      ,'B'),
      new Question ("<html>EgyptHard5"+
                    "<br> A. New Zealand"+
                    "<br> B. Canberra"+
                    "<br> C. Melbourne"+
                    "<br> D. Sydney</html>"
                      ,'D')
    };
    try
    {
      Country egypt =new Country ("Egypt",egyptEasy, egyptMedium,
                                  egyptHard, ImageIO.read (new File ("pics/egypt.jpg")),
                                  new String[]{
        "EgyptClue1.",
          "EgyptClue2.",
          "EgyptClue3.",
          "EgyptClue4.",
          "EgyptClue5."
      });
      countries [6]=egypt;
    }
    catch (IOException e)
    {
      System.out.println ("IO");
    }
    //initialize India
    Question[] indiaEasy=
    {new Question ("<html>What is the capital of India?"+
                   "<br> A. Mumbai"+
                   "<br> B. Bangalore"+
                   "<br> C. New Delhi"+
                   "<br> D. Hyderabad</html>"
                     ,'C'),
      new Question ("<html>IndiaEasy2"+
                    "<br> A. Arctic"+
                    "<br> B. Pacific"+
                    "<br> C. Indian"+
                    "<br> D. Atlantic</html>"
                      ,'C'),
      new Question ("<html>IndiaEasy3"+
                    "<br> A. Indonesia"+
                    "<br> B. Russia"+
                    "<br> C. Africa"+
                    "<br> D. Mexico</html>"
                      ,'A'),
      new Question ("<html>IndiaEasy4"+
                    "<br> A. India"+
                    "<br> B. Australia"+
                    "<br> C. Africa"+
                    "<br> D. Asia</html>"
                      ,'B'),
      new Question ("<html>IndiaEasy5"+
                    "<br> A. New Zealand"+
                    "<br> B. Canberra"+
                    "<br> C. Melbourne"+
                    "<br> D. Sydney</html>"
                      ,'D')
    };
    Question[] indiaMedium=
    {new Question ("<html>indiaMedium1"+
                   "<br> A. Mumbai"+
                   "<br> B. Bangalore"+
                   "<br> C. New Delhi"+
                   "<br> D. Hyderabad</html>"
                     ,'C'),
      new Question ("<html>IndiaMedium2"+
                    "<br> A. Arctic"+
                    "<br> B. Pacific"+
                    "<br> C. Indian"+
                    "<br> D. Atlantic</html>"
                      ,'C'),
      new Question ("<html>IndiaMedium3"+
                    "<br> A. Indonesia"+
                    "<br> B. Russia"+
                    "<br> C. Africa"+
                    "<br> D. Mexico</html>"
                      ,'A'),
      new Question ("<html>IndiaMedium4"+
                    "<br> A. India"+
                    "<br> B. Australia"+
                    "<br> C. Africa"+
                    "<br> D. Asia</html>"
                      ,'B'),
      new Question ("<html>IndiaMedium5"+
                    "<br> A. New Zealand"+
                    "<br> B. Canberra"+
                    "<br> C. Melbourne"+
                    "<br> D. Sydney</html>"
                      ,'D')
    };
    Question[] indiaHard=
    {new Question ("<html>indiaHard1"+
                   "<br> A. Mumbai"+
                   "<br> B. Bangalore"+
                   "<br> C. New Delhi"+
                   "<br> D. Hyderabad</html>"
                     ,'C'),
      new Question ("<html>IndiaHard2"+
                    "<br> A. Arctic"+
                    "<br> B. Pacific"+
                    "<br> C. Indian"+
                    "<br> D. Atlantic</html>"
                      ,'C'),
      new Question ("<html>IndiaHard3"+
                    "<br> A. Indonesia"+
                    "<br> B. Russia"+
                    "<br> C. Africa"+
                    "<br> D. Mexico</html>"
                      ,'A'),
      new Question ("<html>IndiaHard4"+
                    "<br> A. India"+
                    "<br> B. Australia"+
                    "<br> C. Africa"+
                    "<br> D. Asia</html>"
                      ,'B'),
      new Question ("<html>IndiaHard5"+
                    "<br> A. New Zealand"+
                    "<br> B. Canberra"+
                    "<br> C. Melbourne"+
                    "<br> D. Sydney</html>"
                      ,'D')
    };
    try
    {
      Country india =new Country ("India",indiaEasy, indiaMedium,
                                  indiaHard, ImageIO.read (new File ("pics/india.jpg")),
                                  new String[]{
        "IndiaClue1.",
          "IndiaClue2.",
          "IndiaClue3.",
          "IndiaClue4.",
          "IndiaClue5."
      });
      countries [7]=india;
    }
    catch (IOException e)
    {
      System.out.println ("IO");
    }
    //initialize Russia
    Question[] russiaEasy=
    {new Question ("<html>What is the capital of Russia?"+
                   "<br> A. St. Petersburg"+
                   "<br> B. Kazan"+
                   "<br> C. Samara"+
                   "<br> D. Moscow</html>"
                     ,'A'),
      new Question ("<html>RussiaEasy2"+
                    "<br> A. Arctic"+
                    "<br> B. Pacific"+
                    "<br> C. Indian"+
                    "<br> D. Atlantic</html>"
                      ,'C'),
      new Question ("<html>RussiaEasy3"+
                    "<br> A. Indonesia"+
                    "<br> B. Russia"+
                    "<br> C. Africa"+
                    "<br> D. Mexico</html>"
                      ,'A'),
      new Question ("<html>RussiaEasy4"+
                    "<br> A. India"+
                    "<br> B. Australia"+
                    "<br> C. Africa"+
                    "<br> D. Asia</html>"
                      ,'B'),
      new Question ("<html>RussiaEasy5"+
                    "<br> A. New Zealand"+
                    "<br> B. Canberra"+
                    "<br> C. Melbourne"+
                    "<br> D. Sydney</html>"
                      ,'D')
    };
    Question[] russiaMedium=
    {new Question ("<html>russiaMedium1"+
                   "<br> A. St. Petersburg"+
                   "<br> B. Kazan"+
                   "<br> C. Samara"+
                   "<br> D. Moscow</html>"
                     ,'A'),
      new Question ("<html>RussiaMedium2"+
                    "<br> A. Arctic"+
                    "<br> B. Pacific"+
                    "<br> C. Indian"+
                    "<br> D. Atlantic</html>"
                      ,'C'),
      new Question ("<html>RussiaMedium3"+
                    "<br> A. Indonesia"+
                    "<br> B. Russia"+
                    "<br> C. Africa"+
                    "<br> D. Mexico</html>"
                      ,'A'),
      new Question ("<html>RussiaMedium4"+
                    "<br> A. India"+
                    "<br> B. Australia"+
                    "<br> C. Africa"+
                    "<br> D. Asia</html>"
                      ,'B'),
      new Question ("<html>RussiaMedium5"+
                    "<br> A. New Zealand"+
                    "<br> B. Canberra"+
                    "<br> C. Melbourne"+
                    "<br> D. Sydney</html>"
                      ,'D')
    };
    Question[] russiaHard=
    {new Question ("<html>russiaHard1"+
                   "<br> A. St. Petersburg"+
                   "<br> B. Kazan"+
                   "<br> C. Samara"+
                   "<br> D. Moscow</html>"
                     ,'A'),
      new Question ("<html>RussiaHard2"+
                    "<br> A. Arctic"+
                    "<br> B. Pacific"+
                    "<br> C. Indian"+
                    "<br> D. Atlantic</html>"
                      ,'C'),
      new Question ("<html>RussiaHard3"+
                    "<br> A. Indonesia"+
                    "<br> B. Russia"+
                    "<br> C. Africa"+
                    "<br> D. Mexico</html>"
                      ,'A'),
      new Question ("<html>RussiaHard4"+
                    "<br> A. India"+
                    "<br> B. Australia"+
                    "<br> C. Africa"+
                    "<br> D. Asia</html>"
                      ,'B'),
      new Question ("<html>RussiaHard5"+
                    "<br> A. New Zealand"+
                    "<br> B. Canberra"+
                    "<br> C. Melbourne"+
                    "<br> D. Sydney</html>"
                      ,'D')
    };
    try
    {
      Country russia =new Country ("Russia",russiaEasy, russiaMedium,
                                   russiaHard, ImageIO.read (new File ("pics/russia.jpg")),
                                   new String[]{
        "RussiaClue1.",
          "RussiaClue2.",
          "RussiaClue3.",
          "RussiaClue4.",
          "RussiaClue5."
      });
      countries [8]=russia;
    }
    catch (IOException e)
    {
      System.out.println ("IO");
    }
    //initialize Japan
    Question[] japanEasy=
    {new Question ("<html>What is the capital of Japan?"+
                   "<br> A. Yokohama"+
                   "<br> B. Osaka"+
                   "<br> C. Tokyo"+
                   "<br> D. Nagoya</html>"
                     ,'C'),
      new Question ("<html>JapanEasy2"+
                    "<br> A. Arctic"+
                    "<br> B. Pacific"+
                    "<br> C. Indian"+
                    "<br> D. Atlantic</html>"
                      ,'C'),
      new Question ("<html>JapanEasy3"+
                    "<br> A. Indonesia"+
                    "<br> B. Russia"+
                    "<br> C. Africa"+
                    "<br> D. Mexico</html>"
                      ,'A'),
      new Question ("<html>JapanEasy4"+
                    "<br> A. India"+
                    "<br> B. Australia"+
                    "<br> C. Africa"+
                    "<br> D. Asia</html>"
                      ,'B'),
      new Question ("<html>JapanEasy5"+
                    "<br> A. New Zealand"+
                    "<br> B. Canberra"+
                    "<br> C. Melbourne"+
                    "<br> D. Sydney</html>"
                      ,'D')
    };
    Question[] japanMedium=
    {new Question ("<html>japanMedium1"+
                   "<br> A. Yokohama"+
                   "<br> B. Osaka"+
                   "<br> C. Tokyo"+
                   "<br> D. Nagoya</html>"
                     ,'C'),
      new Question ("<html>JapanMedium2"+
                    "<br> A. Arctic"+
                    "<br> B. Pacific"+
                    "<br> C. Indian"+
                    "<br> D. Atlantic</html>"
                      ,'C'),
      new Question ("<html>JapanMedium3"+
                    "<br> A. Indonesia"+
                    "<br> B. Russia"+
                    "<br> C. Africa"+
                    "<br> D. Mexico</html>"
                      ,'A'),
      new Question ("<html>JapanMedium4"+
                    "<br> A. India"+
                    "<br> B. Australia"+
                    "<br> C. Africa"+
                    "<br> D. Asia</html>"
                      ,'B'),
      new Question ("<html>JapanMedium5"+
                    "<br> A. New Zealand"+
                    "<br> B. Canberra"+
                    "<br> C. Melbourne"+
                    "<br> D. Sydney</html>"
                      ,'D')
    };
    Question[] japanHard=
    {new Question ("<html>japanHard1"+
                   "<br> A. Yokohama"+
                   "<br> B. Osaka"+
                   "<br> C. Tokyo"+
                   "<br> D. Nagoya</html>"
                     ,'C'),
      new Question ("<html>JapanHard2"+
                    "<br> A. Arctic"+
                    "<br> B. Pacific"+
                    "<br> C. Indian"+
                    "<br> D. Atlantic</html>"
                      ,'C'),
      new Question ("<html>JapanHard3"+
                    "<br> A. Indonesia"+
                    "<br> B. Russia"+
                    "<br> C. Africa"+
                    "<br> D. Mexico</html>"
                      ,'A'),
      new Question ("<html>JapanHard4"+
                    "<br> A. India"+
                    "<br> B. Australia"+
                    "<br> C. Africa"+
                    "<br> D. Asia</html>"
                      ,'B'),
      new Question ("<html>JapanHard5"+
                    "<br> A. New Zealand"+
                    "<br> B. Canberra"+
                    "<br> C. Melbourne"+
                    "<br> D. Sydney</html>"
                      ,'D')
    };
    try
    {
      Country japan =new Country ("Japan",japanEasy, japanMedium,
                                  japanHard, ImageIO.read (new File ("pics/japan.jpg")),
                                  new String[]{
        "JapanClue1.",
          "JapanClue2.",
          "JapanClue3.",
          "JapanClue4.",
          "JapanClue5."
      });
      countries [9]=japan;
    }
    catch (IOException e)
    {
      System.out.println ("IO");
    }
    //initialize France
    Question[] franceEasy=
    {new Question ("<html>What is the capital of France?"+
                   "<br> A. Marceille"+
                   "<br> B. Nice"+
                   "<br> C. Lille"+
                   "<br> D. Paris</html>"
                     ,'D'),
      new Question ("<html>FranceEasy2"+
                    "<br> A. Arctic"+
                    "<br> B. Pacific"+
                    "<br> C. Indian"+
                    "<br> D. Atlantic</html>"
                      ,'C'),
      new Question ("<html>FranceEasy3"+
                    "<br> A. Indonesia"+
                    "<br> B. Russia"+
                    "<br> C. Africa"+
                    "<br> D. Mexico</html>"
                      ,'A'),
      new Question ("<html>FranceEasy4"+
                    "<br> A. India"+
                    "<br> B. Australia"+
                    "<br> C. Africa"+
                    "<br> D. Asia</html>"
                      ,'B'),
      new Question ("<html>FranceEasy5"+
                    "<br> A. New Zealand"+
                    "<br> B. Canberra"+
                    "<br> C. Melbourne"+
                    "<br> D. Sydney</html>"
                      ,'D')
    };
    Question[] franceMedium=
    {new Question ("<html>franceMedium1"+
                   "<br> A. Marceille"+
                   "<br> B. Nice"+
                   "<br> C. Lille"+
                   "<br> D. Paris</html>"
                     ,'D'),
      new Question ("<html>FranceMedium2"+
                    "<br> A. Arctic"+
                    "<br> B. Pacific"+
                    "<br> C. Indian"+
                    "<br> D. Atlantic</html>"
                      ,'C'),
      new Question ("<html>FranceMedium3"+
                    "<br> A. Indonesia"+
                    "<br> B. Russia"+
                    "<br> C. Africa"+
                    "<br> D. Mexico</html>"
                      ,'A'),
      new Question ("<html>FranceMedium4"+
                    "<br> A. India"+
                    "<br> B. Australia"+
                    "<br> C. Africa"+
                    "<br> D. Asia</html>"
                      ,'B'),
      new Question ("<html>FranceMedium5"+
                    "<br> A. New Zealand"+
                    "<br> B. Canberra"+
                    "<br> C. Melbourne"+
                    "<br> D. Sydney</html>"
                      ,'D')
    };
    Question[] franceHard=
    {new Question ("<html>franceHard1"+
                   "<br> A. Marceille"+
                   "<br> B. Nice"+
                   "<br> C. Lille"+
                   "<br> D. Paris</html>"
                     ,'D'),
      new Question ("<html>FranceHard2"+
                    "<br> A. Arctic"+
                    "<br> B. Pacific"+
                    "<br> C. Indian"+
                    "<br> D. Atlantic</html>"
                      ,'C'),
      new Question ("<html>FranceHard3"+
                    "<br> A. Indonesia"+
                    "<br> B. Russia"+
                    "<br> C. Africa"+
                    "<br> D. Mexico</html>"
                      ,'A'),
      new Question ("<html>FranceHard4"+
                    "<br> A. India"+
                    "<br> B. Australia"+
                    "<br> C. Africa"+
                    "<br> D. Asia</html>"
                      ,'B'),
      new Question ("<html>FranceHard5"+
                    "<br> A. New Zealand"+
                    "<br> B. Canberra"+
                    "<br> C. Melbourne"+
                    "<br> D. Sydney</html>"
                      ,'D')
    };
    try
    {
      Country france =new Country ("France",franceEasy,franceMedium, null, ImageIO.read (new File ("pics/france.jpeg")),
                                   new String[]{
        "FranceClue1.",
          "FranceClue2.",
          "FranceClue3.",
          "FranceClue4.",
          "FranceClue5."
      });
      countries [10]=france;
    }
    catch (IOException e)
    {
      System.out.println ("IO");
    }
    //initialize England
    Question[] englandEasy=
    {new Question ("<html>What is the capital of England?"+
                   "<br> A. London"+
                   "<br> B. Leeds"+
                   "<br> C. Birmingham"+
                   "<br> D. Bradford</html>"
                     ,'B'),
      new Question ("<html>EngalndEasy2"+
                    "<br> A. Arctic"+
                    "<br> B. Pacific"+
                    "<br> C. Indian"+
                    "<br> D. Atlantic</html>"
                      ,'C'),
      new Question ("<html>EnglandEasy3"+
                    "<br> A. Indonesia"+
                    "<br> B. Russia"+
                    "<br> C. Africa"+
                    "<br> D. Mexico</html>"
                      ,'A'),
      new Question ("<html>EnglandEasy4"+
                    "<br> A. India"+
                    "<br> B. Australia"+
                    "<br> C. Africa"+
                    "<br> D. Asia</html>"
                      ,'B'),
      new Question ("<html>EnglandEasy5"+
                    "<br> A. New Zealand"+
                    "<br> B. Canberra"+
                    "<br> C. Melbourne"+
                    "<br> D. Sydney</html>"
                      ,'D')
    };
    Question[] englandMedium=
    {new Question ("<html>englandMedium1"+
                   "<br> A. London"+
                   "<br> B. Leeds"+
                   "<br> C. Birmingham"+
                   "<br> D. Bradford</html>"
                     ,'B'),
      new Question ("<html>EngalndMedium2"+
                    "<br> A. Arctic"+
                    "<br> B. Pacific"+
                    "<br> C. Indian"+
                    "<br> D. Atlantic</html>"
                      ,'C'),
      new Question ("<html>EnglandMedium3"+
                    "<br> A. Indonesia"+
                    "<br> B. Russia"+
                    "<br> C. Africa"+
                    "<br> D. Mexico</html>"
                      ,'A'),
      new Question ("<html>EnglandMedium4"+
                    "<br> A. India"+
                    "<br> B. Australia"+
                    "<br> C. Africa"+
                    "<br> D. Asia</html>"
                      ,'B'),
      new Question ("<html>EnglandMedium5"+
                    "<br> A. New Zealand"+
                    "<br> B. Canberra"+
                    "<br> C. Melbourne"+
                    "<br> D. Sydney</html>"
                      ,'D')
    };
    Question[] englandHard=
    {new Question ("<html>englandHard1"+
                   "<br> A. London"+
                   "<br> B. Leeds"+
                   "<br> C. Birmingham"+
                   "<br> D. Bradford</html>"
                     ,'B'),
      new Question ("<html>EngalndHard2"+
                    "<br> A. Arctic"+
                    "<br> B. Pacific"+
                    "<br> C. Indian"+
                    "<br> D. Atlantic</html>"
                      ,'C'),
      new Question ("<html>EnglandHard3"+
                    "<br> A. Indonesia"+
                    "<br> B. Russia"+
                    "<br> C. Africa"+
                    "<br> D. Mexico</html>"
                      ,'A'),
      new Question ("<html>EnglandHard4"+
                    "<br> A. India"+
                    "<br> B. Australia"+
                    "<br> C. Africa"+
                    "<br> D. Asia</html>"
                      ,'B'),
      new Question ("<html>EnglandHard5"+
                    "<br> A. New Zealand"+
                    "<br> B. Canberra"+
                    "<br> C. Melbourne"+
                    "<br> D. Sydney</html>"
                      ,'D')
    };
    try
    {
      Country england =new Country ("England",englandEasy, englandMedium, null, ImageIO.read (new File ("pics/england.jpg")),
                                    new String[]{
        "EnglandClue1.",
          "EnglandClue2.",
          "EnglandClue3.",
          "EnglandClue4.",
          "EnglandClue5."
      });
      countries [11]=england;
    }
    catch (IOException e)
    {
      System.out.println ("IO");
    }
    
    
    remove (splashScreen);
    
    alreadyBeen=new ArrayList<Country>();
    currentCountry=countries[0];
    //alreadyBeen.add(countries[0]);
    
    //initialize menus
    JMenuBar menuBar=new JMenuBar();
    add (menuBar);
    setJMenuBar (menuBar);
    JMenu helpMenu=new JMenu ("Help");
    JMenu fileMenu =new JMenu ("File");
    menuBar.add(fileMenu);
    menuBar.add(helpMenu);
    
    
    fileMenu.add(saveItem);
    fileMenu.add(printItem);
    fileMenu.add(highScoresItem);
    fileMenu.add(exitItem);
    
    helpMenu.add(howToPlayItem);
    helpMenu.add(helpItem);
    helpMenu.add(aboutItem);
    
    howToPlayItem.addActionListener(this);
    printItem.addActionListener(this);
    saveItem.addActionListener(this);
    exitItem.addActionListener(this);
    highScoresItem.addActionListener(this);
    
    helpItem.addActionListener(this);
    aboutItem.addActionListener(this);
    
    //load main menu
    mainMenuPanel=new MainMenuPanel();
    add(mainMenuPanel);
    mainMenuPanel.getEasyButton().addActionListener(this);
    mainMenuPanel.getMediumButton().addActionListener(this);
    mainMenuPanel.getHardButton().addActionListener(this);
    mainMenuPanel.getLoadButton().addActionListener(this);
    
    saveItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
                                                   Event.CTRL_MASK));
    exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q,
                                                   Event.CTRL_MASK));
    howToPlayItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R,
                                                        Event.CTRL_MASK));
    printItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,
                                                    Event.CTRL_MASK));
    aboutItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_U,
                                                    Event.CTRL_MASK));
    helpItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H,
                                                   Event.CTRL_MASK));
    highScoresItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E,
                                                         Event.CTRL_MASK));
    revalidate();
    
    
    levelCounter=new JLabel(levelsRemaining+"");
    addWindowListener(this);
    System.out.println ("ready");
    revalidate();
  }
  
}
