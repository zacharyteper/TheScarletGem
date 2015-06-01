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
public class ScarletGemMain extends JFrame implements ActionListener, Printable,WindowListener
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
  public static final Country[] COUNTRIES=new Country[12];
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
  
  private int score=0;
  private String userName;
  /**
   * Called by the JVM when the window is Deactivated.
   * 
   * @param e WindowEvent the event that occured on the window
   */
  public void windowDeactivated(WindowEvent e)
  {
  }
  /**
   * Called by the JVM when the window is Opened.
   * 
   * @param e WindowEvent the event that occured on the window
   */
  public void windowOpened (WindowEvent e)
  {
  }
  /**
   * Called by the JVM when the window is Closing.
   * 
   * @param e WindowEvent the event that occured on the window
   */
  public void windowClosing (WindowEvent e)
  {
    closeWarning();
  }
  /**
   * Called by the JVM when the window is Activated.
   * 
   * @param e WindowEvent the event that occured on the window
   */
  public void windowActivated (WindowEvent e)
  {
  }
  /**
   * Called by the JVM when the window is Deiconified.
   * 
   * @param e WindowEvent the event that occured on the window
   */
  public void windowDeiconified (WindowEvent e)
  {
  }
  /**
   * Called by the JVM when the window is Iconified.
   * 
   * @param e WindowEvent the event that occured on the window
   */
  public void windowIconified(WindowEvent e)
  {
  }
  /**
   * Called by the JVM when the window is Closed.
   * 
   * @param e WindowEvent the event that occured on the window
   */
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
    else if (ae.getSource().equals(highScoresItem))
    {
      new HighScoresViewer();
    }
    else if (ae.getSource().equals(saveItem))
    {
      save();
    }
    else if (ae.getSource().equals(printItem))
    {
      PrinterJob job = PrinterJob.getPrinterJob();
      job.setPrintable(this);
      boolean ok=job.printDialog();
      if (ok)
      {
        try
        {
          job.print();
        }
        catch (PrinterException e)
        {
        }
      }
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
      HighScoresViewer h = new HighScoresViewer ();
    }
    else if (ae.getSource().equals(mainMenuPanel.getEasyButton()))
    {
      levelsRemaining=2;
      difficulty=0;
      initializeGame();     
    }
    else if (ae.getSource().equals(mainMenuPanel.getMediumButton()))
    {
      levelsRemaining=5;
      difficulty=1;
      initializeGame();
    }
    else if (ae.getSource().equals(mainMenuPanel.getHardButton()))
    {
      levelsRemaining=7;
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
      JOptionPane.showMessageDialog(null,"Sorry, you ran out of time!");
      endGame();
    }
    revalidate();
  }
  /** Returns the country associated with a name
    * 
    * @param name String the name of the Country
    * @return Country the instance of the country associated with name
    * @See "ScarletGemMain.COUNTRIES"
    */
  private Country getCountry(String name)
  {
    if (name.equals("Canada"))
      return COUNTRIES[0];
    else if (name.equals("China"))
      return COUNTRIES[1];
    else if (name.equals("USA"))
      return COUNTRIES[2];
    else if (name.equals("Mexico"))
      return COUNTRIES[3];
    else if (name.equals("Portugal"))
      return COUNTRIES[4];
    else if (name.equals("Australia"))
      return COUNTRIES[5];
    else if (name.equals("Egypt"))
      return COUNTRIES[6];
    else if (name.equals("India"))
      return COUNTRIES[7];
    else if (name.equals("Russia"))
      return COUNTRIES[8];
    else if (name.equals("Japan"))
      return COUNTRIES[9];
    else if (name.equals("France"))
      return COUNTRIES[10];
    return COUNTRIES[11];
  }
  /**
   * Asks the users if they want to save before closing the program. 
   * YES==save and close
   * NO==close without saving
   * CANCEL/ESCAPE==resume game without saving
   */
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
  /**
   * saves the user's current progress to the file "progress.txt"
   */
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
  /**
   * sets up the game components and starts the timer.
   */
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
  
  private void pause ()
  {
    gamePanel.switchToPause ();
  }
  
  /**
   * displays a random multiple-choice question to the user,
   * and waits for an answer.
   */
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
      gamePanel.timer.setGameWon (true);
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
   * While loops search for COUNTRIES that have not been visited in the game.
   */ 
  private void showMapPanel()
  {
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
      currentCountry=COUNTRIES[(int)(Math.random()*10)];
    }
    while (alreadyBeen.contains(currentCountry));
    
    Country wrong1;
    Country wrong2;
    do
    {
      System.out.println ("search2");
      wrong1=COUNTRIES[(int)(Math.random()*10)];
    }
    while (alreadyBeen.contains(wrong1)||wrong1.equals(currentCountry));
    
    do
    {
      System.out.println ("search3");
      wrong2=COUNTRIES[(int)(Math.random()*10)];
    }
    while (alreadyBeen.contains(wrong2)||wrong1.equals(wrong2)
             ||wrong2.equals(currentCountry));
    //System.out.println ("worng: "+wrong2.getName());
    gamePanel.setDestinations(new Country[]{currentCountry,wrong1,wrong2});
    gamePanel.switchToMap();
    gamePanel.getAButton().setEnabled(true);
    gamePanel.getBButton().setEnabled(true);
    gamePanel.getCButton().setEnabled(true);
    repaint();
    
    //COUNTRIES[(int)(Math.random()*
    
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
        score -= 10;
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
        score -= 10;
      }
    }
  }
  /**
   * resets game variables, closes the game screen, 
   * and returns to the main menu.
   */
  public void endGame()
  {
    remove(gamePanel);
    remove(levelCounter);
    levelsRemaining = -1;
    currentCountry=COUNTRIES[0];
    alreadyBeen=new ArrayList<Country>();
    int next = 0;
    if (gamePanel.timer.getGameWon())
    {
      score += GameTimer.timeRemaining;
      System.out.println (score);
      
      try
      {
        userName = JOptionPane.showInputDialog (null, 
                                                "Congratulations, you found the Scarlet Gem! Please enter your user name!", 
                                                "You won!", JOptionPane.QUESTION_MESSAGE);
      }
      catch (NullPointerException e)
      {
        userName = "NO NAME";
      }
      
      HighScoresViewer.sort (score, userName, difficulty);
      
      if (difficulty == 0 || difficulty == 1)
        next = JOptionPane.showConfirmDialog(null, "Would you like to continue to the next level?", "Next?", JOptionPane.YES_NO_OPTION);
      if (next == 0)
      {
        if (difficulty == 0)
        {
          levelsRemaining=5;
          difficulty=1;
        }
        else
        {
          levelsRemaining=7;
          difficulty=2;
        }
        initializeGame();  
      }
      else
      {
        add(mainMenuPanel);
        repaint();
        revalidate();
      }
    }
    else
    {
      add(mainMenuPanel);
      repaint();
      revalidate();
    }
  }
  /**
   * Sends a print command to the printer specified in the dialog box.
   * 
   * @param page Graphics the page to be printed
   * @param format PageFormat the format of the page to be printed
   * @param index int the page index to be printed
   * 
   * @return int Returns Printable.PAGE_EXISTS or Printable.NO_SUCH_PAGE
   */
  public int print(Graphics page, PageFormat format, int index)
  {
    if (index>0)
      return NO_SUCH_PAGE;
    page.drawString("The Scarlet Gem",100,100);
    try
    {
      page.drawImage(ImageIO.read(new File("pics/CakeSoft Inc.png"))
                       ,100,150,null);
    }
    catch (IOException e)
    {
    }
    page.drawString("Easy",100,300);
    page.drawString("Medium",300,300);
    page.drawString("Hard",500,300);
    page.drawString("Name",50,350);
    page.drawString("Score",150,350);
    page.drawString("Name",250,350);
    page.drawString("Score",350,350);
    page.drawString("Name",450,350);
    page.drawString("Score",550,350);
    for (int x=0;x<10;x++)
    {
      System.out.println ("easy"+x);
      if (HighScoresViewer.easyNames[x].equals(""))
        break;
      page.drawString(HighScoresViewer.easyNames[x],50,400+x*50);
      if (!HighScoresViewer.easyNames[x].equals(""))
        page.drawString(HighScoresViewer.easyScores[x]+"",150,400+x*50);
    }
    for (int x=0;x<10;x++)
    {
      if (HighScoresViewer.mediumNames[x].equals(""))
        break;
      page.drawString(HighScoresViewer.mediumNames[x],250,400+x*50);
      if (!HighScoresViewer.mediumNames[x].equals(""))
        page.drawString(HighScoresViewer.mediumScores[x]+"",350,400+x*50);
    }
    for (int x=0;x<10;x++)
    {
      System.out.println (x);
      if (HighScoresViewer.hardNames[x].equals(""))
        break;
      page.drawString(HighScoresViewer.hardNames[x],450,400+x*50);
      if (!HighScoresViewer.hardNames[x].equals(""))
        page.drawString(HighScoresViewer.hardScores[x]+"",550,400+x*50);
    }
    
    
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
    
    //initialize COUNTRIES
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
    {new Question ("<html>When was Canada created?"+
                   "<br> A. 1919"+
                   "<br> B. 1407"+
                   "<br> C. 1867"+
                   "<br> D. 1776</html>"
                     ,'C'),
      new Question ("<html>How many countries share a border with Canada?"+
                    "<br> A. 3"+
                    "<br> B. 5"+
                    "<br> C. 2"+
                    "<br> D. 1</html>"
                      ,'D'),
      new Question ("<html>Which metal is most commonly mined in Canada?"+
                    "<br> A. Iron"+
                    "<br> B. Lead"+
                    "<br> C. Gold"+
                    "<br> D. Nickel</html>"
                      ,'A'),
      new Question ("<html>Which is closest to the population of Canada?"+
                    "<br> A. 30 million"+
                    "<br> B. 35 million"+
                    "<br> C. 20 million"+
                    "<br> D. 50 million</html>"
                      ,'B'),
      new Question ("<html>Which is the largest city in Canada"+
                    "<br> A. Montreal"+
                    "<br> B. Vancouver"+
                    "<br> C. St. John's"+
                    "<br> D. Toronto</html>"
                      ,'D')
    };
    Question[] canadaHard=
    {new Question ("<html>Which is closest to the population of Toronto?"+
                   "<br> A. 3 million"+
                   "<br> B. 10 million"+
                   "<br> C. 6 million"+
                   "<br> D. 8 million</html>"
                     ,'C'),
      new Question ("<html>Which is the closest to the length of Yonge St?"+
                    "<br> A. 1000 km"+
                    "<br> B. 1500 km"+
                    "<br> C. 2500 km"+
                    "<br> D. 2000 km</html>"
                      ,'D'),
      new Question ("<html>What is the infant mortality rate of Canada"+
                    "<br> A. 0.47%"+
                    "<br> B. 0.22%"+
                    "<br> C. 0.39%"+
                    "<br> D. 0.62%</html>"
                      ,'A'),
      new Question ("<html>How many Canadian provinces start with the letter N?"+
                    "<br> A. 1"+
                    "<br> B. 3"+
                    "<br> C. 4"+
                    "<br> D. 2</html>"
                      ,'B'),
      new Question ("<html>What proportion of Canada's labour-force <br>is in manufacturing?"+
                    "<br> A. 40%"+
                    "<br> B. 31%"+
                    "<br> C. 67%"+
                    "<br> D. 13%</html>"
                      ,'D')
    };
    try
    {
      Country canada =new Country ("Canada",canadaEasy, canadaMedium,
                                   canadaHard, ImageIO.read (new File ("pics/canada.jpg")),
                                   new String[]
                                     {""}
      );
      COUNTRIES [0]=canada;
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
    {new Question ("<html>What is the highest point in China?"+
                   "<br> A. Mount Everest"+
                   "<br> B. K2"+
                   "<br> C. Mont Blanc"+
                   "<br> D. Red Mountain</html>"
                     ,'A'),
      new Question ("<html>Which country does NOT border China?"+
                    "<br> A. Mongolia"+
                    "<br> B. Kazakhstan"+
                    "<br> C. Afghanistan"+
                    "<br> D. Georgia</html>"
                      ,'C'),
      new Question ("<html>Which river does NOT flow through China?"+
                    "<br> A. Heilong"+
                    "<br> B. Jordan"+
                    "<br> C. Yellow"+
                    "<br> D. Mekong</html>"
                      ,'B'),
      new Question ("<html>What is the second largest mountain range in China?"+
                    "<br> A. Himalayas"+
                    "<br> B. Tien Shan"+
                    "<br> C. Qilian"+
                    "<br> D. Kunlun</html>"
                      ,'D'),
      new Question ("<html>What is the longest river in China?"+
                    "<br> A. Huang He"+
                    "<br> B. Yangtze"+
                    "<br> C. Songhua"+
                    "<br> D. Huai</html>"
                      ,'B')
    };
    Question[] chinaHard=
    {new Question ("<html>What is the lowest point in China?"+
                   "<br> A. Turpan Pendi"+
                   "<br> B. Hong Kong"+
                   "<br> C. Xi depression"+
                   "<br> D. Bodelle Depression</html>"
                     ,'A'),
      new Question ("<html>What portion of China's population is Buddhist?"+
                    "<br> A. 50%"+
                    "<br> B. 41%"+
                    "<br> C. 18%"+
                    "<br> D. 23%</html>"
                      ,'C'),
      new Question ("<html>What percentage of Chinese export is with Hong Kong?"+
                    "<br> A. 30%"+
                    "<br> B. 17%"+
                    "<br> C. 21%"+
                    "<br> D. 49%</html>"
                      ,'B'),
      new Question ("<html>What year was the People's Republic of China established?"+
                    "<br> A. 1963"+
                    "<br> B. 1412"+
                    "<br> C. 1749"+
                    "<br> D. 1949</html>"
                      ,'D'),
      new Question ("<html>How many provinces does China contain?"+
                    "<br> A. 15"+
                    "<br> B. 23"+
                    "<br> C. 31"+
                    "<br> D. 12</html>"
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
      COUNTRIES [1]=china;
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
    {new Question ("How many geographical regions is the USA composed of?"+
                   "<br> A. 2"+
                   "<br> B. 3"+
                   "<br> C. 4"+
                   "<br> D. 5</html>"
                     ,'C'),
      new Question ("<html>Which of the following is NOT a region of the USA?"+
                    "<br> A. South"+
                    "<br> B. North"+
                    "<br> C. West"+
                    "<br> D. Midwest</html>"
                      ,'B'),
      new Question ("<html>Which of the following is NOT a mountain range in the USA?"+
                    "<br> A. Rockies"+
                    "<br> B. Himalayas"+
                    "<br> C. Appalachians"+
                    "<br> D. Cascade</html>"
                      ,'B'),
      new Question ("<html>What is the highest mountain in the USA?"+
                    "<br> A. Mt. St. Elias"+
                    "<br> B. Mt. Sanford"+
                    "<br> C. Mt. Rainier"+
                    "<br> D. Mt. McKinley</html>"
                      ,'D'),
      new Question ("<html>Which river system drains the American Midwest?"+
                    "<br> A. Mississippi"+
                    "<br> B. Colarado"+
                    "<br> C. Red"+
                    "<br> D. Rio Grande</html>"
                      ,'A')
    };
    Question[] usaHard=
    {new Question ("<html>How long is the Mississippi River system?"+
                   "<br> A. 5300 km"+
                   "<br> B. 6853 km"+
                   "<br> C. 5970 km"+
                   "<br> D. 6437 km</html>"
                     ,'C'),
      new Question ("<html>Which is closest to the area of <br>the USA (in square kilometers)?"+
                    "<br> A. 1 Billion"+
                    "<br> B. 9.8 million"+
                    "<br> C. 9.5 million"+
                    "<br> D. 9.7 million</html>"
                      ,'B'),
      new Question ("<html>How tall is Mt. McKinley?"+
                    "<br> A. 4,421 m"+
                    "<br> B. 6,244 m"+
                    "<br> C. 5,489 m"+
                    "<br> D. 6,149 m</html>"
                      ,'D'),
      new Question ("<html>Which is the largest geographical region in the USA?"+
                    "<br> A. Northeast"+
                    "<br> B. Midwest"+
                    "<br> C. West"+
                    "<br> D. South</html>"
                      ,'C'),
      new Question ("<html>What climate is found in the American Midwest?"+
                    "<br> A. Humid continental"+
                    "<br> B. Steppe"+
                    "<br> C. Subtropical"+
                    "<br> D. Marine west coast</html>"
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
      COUNTRIES [2]=usa;
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
    {new Question ("<html>How many countries does Mexico share a border with?"+
                   "<br> A. 4"+
                   "<br> B. 3"+
                   "<br> C. 6"+
                   "<br> D. 2</html>"
                     ,'B'),
      new Question ("<html>Which is the tallest mountain in Mexico?"+
                    "<br> A. Pico de Orizaba"+
                    "<br> B. Socorro"+
                    "<br> C. El Chichon"+
                    "<br> D. Ceburoco</html>"
                      ,'A'),
      new Question ("<html>Which is closest to the population of Mexico City?"+
                    "<br> A. 20 million"+
                    "<br> B. 13 million"+
                    "<br> C. 5 million"+
                    "<br> D. 18 million</html>"
                      ,'A'),
      new Question ("<html>What percentage of Mexico's landmass is water?"+
                    "<br> A. 13.1%"+
                    "<br> B. 2.5%"+
                    "<br> C. 0.3%"+
                    "<br> D. 5.4%</html>"
                      ,'B'),
      new Question ("<html>Which city is not in Mexico?"+
                    "<br> A. Veracruz"+
                    "<br> B. La Paz"+
                    "<br> C. Guadalajara"+
                    "<br> D. Montevideo</html>"
                      ,'D')
    };
    Question[] mexicoHard=
    {new Question ("<html>Approximately how many rivers are in Mexico?"+
                   "<br> A. 30"+
                   "<br> B. 150"+
                   "<br> C. 110"+
                   "<br> D. Cancun</html>"
                     ,'B'),
      new Question ("<html>How long is the Mexican border with <br>the United States?"+
                    "<br> A. 3000 km"+
                    "<br> B. 6000 km"+
                    "<br> C. 3000 km"+
                    "<br> D. 5000 km</html>"
                      ,'D'),
      new Question ("<html>Which is NOT a resource found in Mexico?"+
                    "<br> A. Iron"+
                    "<br> B. Gold"+
                    "<br> C. Silver"+
                    "<br> D. Copper</html>"
                      ,'A'),
      new Question ("<html>Which is the most active volacno in Mexico?"+
                    "<br> A. Popocatepetl"+
                    "<br> B. Colima"+
                    "<br> C. Barcena"+
                    "<br> D. Tacana</html>"
                      ,'B'),
      new Question ("<html>What year did Europeans first discover Mexico?"+
                    "<br> A. 1431"+
                    "<br> B. 1513"+
                    "<br> C. 1597"+
                    "<br> D. 1521</html>"
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
      COUNTRIES [3]=mexico;
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
    {new Question ("<html>What is the second largest city in Portugal?"+
                   "<br> A. Lisbon"+
                   "<br> B. Porto"+
                   "<br> C. Aveiro"+
                   "<br> D. Santarem</html>"
                     ,'B'),
      new Question ("<html>What year was the Portuguese Republic established?"+
                    "<br> A. 1944"+
                    "<br> B. 1837"+
                    "<br> C. 1907"+
                    "<br> D. 1910</html>"
                      ,'D'),
      new Question ("<html>What is the main goods-based industry in Portugal?"+
                    "<br> A. Textiles"+
                    "<br> B. Lumber"+
                    "<br> C. Auto parts"+
                    "<br> D. Glassware</html>"
                      ,'A'),
      new Question ("<html>What percentage of the Portuguese <br>labour-force is agriculture-based?"+
                    "<br> A. 5%"+
                    "<br> B. 10%"+
                    "<br> C. 15%"+
                    "<br> D. 2%</html>"
                      ,'B'),
      new Question ("<html>What is the unemployment rate in Portugal?"+
                    "<br> A. 4%"+
                    "<br> B. 10%"+
                    "<br> C. 29%"+
                    "<br> D. 14%</html>"
                      ,'D')
    };
    Question[] portugalHard=
    {new Question ("<html>What is Portugal's main export?"+
                   "<br> A. Wine"+
                   "<br> B. Agricultural products"+
                   "<br> C. Leather"+
                   "<br> D. Pulp and paper</html>"
                     ,'B'),
      new Question ("<html>Which is closest to the size of Madeira (in square kilometers)?"+
                    "<br> A. 300"+
                    "<br> B. 400"+
                    "<br> C. 200"+
                    "<br> D. 800</html>"
                      ,'D'),
      new Question ("<html>Which is the best description of Northern Portugal?"+
                    "<br> A. Mountainous"+
                    "<br> B. Rolling Plains"+
                    "<br> C. Marshy Wetlands"+
                    "<br> D. Flat Desert</html>"
                      ,'A'),
      new Question ("<html>How many UNESCO heritage sites are in Portugal?"+
                    "<br> A. 23"+
                    "<br> B. 15"+
                    "<br> C. 9"+
                    "<br> D. 11</html>"
                      ,'B'),
      new Question ("<html>In which Portuguese city is the Casa da Musica found?"+
                    "<br> A. Lisbon"+
                    "<br> B. Aveiro"+
                    "<br> C. Amadora"+
                    "<br> D. Porto</html>"
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
      COUNTRIES [4]=portugal;
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
    {new Question ("<html>What is Australias most commonly mined substance?"+
                   "<br> A. Silver"+
                   "<br> B. Bauxite"+
                   "<br> C. Tin"+
                   "<br> D. Coal</html>"
                     ,'B'),
      new Question ("<html>What is the second largest city in Australia?"+
                    "<br> A. Sydney"+
                    "<br> B. Canberra"+
                    "<br> C. Melbourne"+
                    "<br> D. Adelaide</html>"
                      ,'C'),
      new Question ("<html>How many states are in Australia?"+
                    "<br> A. 6"+
                    "<br> B. 4"+
                    "<br> C. 3"+
                    "<br> D. 5</html>"
                      ,'A'),
      new Question ("<html>What year did Australia gain independence?"+
                    "<br> A. 1913"+
                    "<br> B. 1901"+
                    "<br> C. 1934"+
                    "<br> D. 1852</html>"
                      ,'B'),
      new Question ("<html>Which constellation is featured on the Australian flag?"+
                    "<br> A. Cygnus"+
                    "<br> B. Ares"+
                    "<br> C. Libra"+
                    "<br> D. Southern Cross</html>"
                      ,'D')
    };
    Question[] australiaHard=
    {new Question ("<html>australiaHard1"+
                   "<br> A. Sydney"+
                   "<br> B. Canberra"+
                   "<br> C. Brisbane"+
                   "<br> D. New Zealand</html>"
                     ,'B'),
      new Question ("<html>What is Australia's main export?"+
                    "<br> A. Wheat"+
                    "<br> B. Meat"+
                    "<br> C. Metal"+
                    "<br> D. Coal</html>"
                      ,'D'),
      new Question ("<html>What is Australia's main climate type?"+
                    "<br> A. Semi-arid"+
                    "<br> B. Tropical"+
                    "<br> C. Tundra"+
                    "<br> D. Steppe</html>"
                      ,'A'),
      new Question ("<html>Which is the closest to Australia's <br>Marine Territory (in square kilometers)?"+
                    "<br> A. 1 million"+
                    "<br> B. 8 million"+
                    "<br> C. 7 million"+
                    "<br> D. 3 million</html>"
                      ,'B'),
      new Question ("<html>Which is the longest river in Australia?"+
                    "<br> A. Murchison"+
                    "<br> B. Ashburton"+
                    "<br> C. Victoria"+
                    "<br> D. Murray</html>"
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
      COUNTRIES [5]=australia;
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
      new Question ("<html>What is the main river in Egypt?"+
                    "<br> A. Congo"+
                    "<br> B. Niger"+
                    "<br> C. Nile"+
                    "<br> D. Jordan</html>"
                      ,'C'),
      new Question ("<html>What is the main terrain type of Egypt?"+
                    "<br> A. Desert"+
                    "<br> B. Tropical"+
                    "<br> C. Arctic"+
                    "<br> D. </html>"
                      ,'A'),
      new Question ("<html>What continent is Egypt part of?"+
                    "<br> A. Asia"+
                    "<br> B. Africa"+
                    "<br> C. North America"+
                    "<br> D. Europe</html>"
                      ,'B'),
      new Question ("<html>What is the official language of Egypt?"+
                    "<br> A. English"+
                    "<br> B. French"+
                    "<br> C. German"+
                    "<br> D. Arabic</html>"
                      ,'D')
    };
    Question[] egyptMedium=
    {new Question ("<html>What is the predominant religion in Egypt?"+
                   "<br> A. Muslim"+
                   "<br> B. Catholic"+
                   "<br> C. Buddhist"+
                   "<br> D. Lutheran</html>"
                     ,'A'),
      new Question ("<html>Which is closest to the population of Egypt"+
                    "<br> A. 54 million"+
                    "<br> B. 96 million"+
                    "<br> C. 86 million"+
                    "<br> D. 47 million</html>"
                      ,'C'),
      new Question ("<html>How many regions are in Egypt?"+
                    "<br> A. 27"+
                    "<br> B. 12"+
                    "<br> C. 17"+
                    "<br> D. 31</html>"
                      ,'A'),
      new Question ("<html>What is the main agricultural export of Egypt?"+
                    "<br> A. Rice"+
                    "<br> B. Cotton"+
                    "<br> C. Wheat"+
                    "<br> D. Beans</html>"
                      ,'B'),
      new Question ("<html>What is Egypt's main trading partner?"+
                    "<br> A. India"+
                    "<br> B. Saudi Arabia"+
                    "<br> C. Turkey"+
                    "<br> D. Italy</html>"
                      ,'D')
    };
    Question[] egyptHard=
    {new Question ("<html>What is the length of the Nile River within Egypt?"+
                   "<br> A. 1600 km"+
                   "<br> B. 2400 km"+
                   "<br> C. 1200 km"+
                   "<br> D. 2000 km</html>"
                     ,'A'),
      new Question ("<html>Which oasis is NOT in Egypt?"+
                    "<br> A. Siwah"+
                    "<br> B. Faiyum"+
                    "<br> C. Safsaf"+
                    "<br> D. Baharia</html>"
                      ,'C'),
      new Question ("<html>How many branches does the Nile make at its delta?"+
                    "<br> A. 2"+
                    "<br> B. 6"+
                    "<br> C. 3"+
                    "<br> D. 4</html>"
                      ,'A'),
      new Question ("<html>Which Egyptian town contains the Temple of Amun?"+
                    "<br> A. Cairo"+
                    "<br> B. Siwah"+
                    "<br> C. Giza"+
                    "<br> D. Luxor</html>"
                      ,'B'),
      new Question ("<html>Approximately how large is the Nile delta (in square kilometers)?"+
                    "<br> A. 22000"+
                    "<br> B. 42000"+
                    "<br> C. 15000"+
                    "<br> D. 31000</html>"
                      ,'D')
    };
    try
    {
      Country egypt =new Country ("Egypt",egyptEasy, egyptMedium,
                                  egyptHard, ImageIO.read (new File ("pics/egypt.jpg")),
                                  new String[]{
        "I am in the country which contains the Nile River.",
          "I am in the country which contains the Pyramids of Giza.",
          "I am in the country in which the Sinai penninsula is located.",
          "I am in the country which contains the Valley of Kings and Queens.",
          "I am in the country in which the Great Sphinx van be found."
      });
      COUNTRIES [6]=egypt;
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
      
      new Question ("<html>What is the national currency of India"+
                    "<br> A. India Dollar"+
                    "<br> B. Yuan"+
                    "<br> C. Euro"+
                    "<br> D. Rupee</html>"
                      ,'D'),
      new Question ("<html>Which colour is NOT featured on the Indian flag?"+
                    "<br> A. Orange (saffron)"+
                    "<br> B. White"+
                    "<br> C. Green"+
                    "<br> D. Yellow</html>"
                      ,'D'),
      new Question ("<html>Which ocean is off the coast of India?"+
                    "<br> A. Indian"+
                    "<br> B. Atlantic"+
                    "<br> C. Pacific"+
                    "<br> D. Arctic</html>"
                      ,'A'),
      new Question ("<html>What is the longest river in India?"+
                    "<br> A. Brahmaputra"+
                    "<br> B. Ganges"+
                    "<br> C. Krishna"+
                    "<br> D. Jamuna</html>"
                      ,'B'),
    };
    Question[] indiaMedium=
    {new Question ("<html>What is the national animal of India"+
                   "<br> A. Asian lion"+
                   "<br> B. Diamond-back python"+
                   "<br> C. Bengal tiger"+
                   "<br> D. Lamprey Eel</html>"
                     ,'C'),
      new Question ("<html>Which country does not directly border India?"+
                    "<br> A. Bangladesh"+
                    "<br> B. Bhutan"+
                    "<br> C. Nepal"+
                    "<br> D. Afghanistan</html>"
                      ,'C'),
      new Question ("<html>How many official languages does India have?"+
                    "<br> A. 16"+
                    "<br> B. 12"+
                    "<br> C. 25"+
                    "<br> D. 8</html>"
                      ,'A'),
      new Question ("<html>What year did India gain independence?"+
                    "<br> A. 1951"+
                    "<br> B. 1947"+
                    "<br> C. 1932"+
                    "<br> D. 1956</html>"
                      ,'B'),
      new Question ("<html>IndiaMedium5"+
                    "<br> A. New Zealand"+
                    "<br> B. Canberra"+
                    "<br> C. Melbourne"+
                    "<br> D. Sydney</html>"
                      ,'D')
    };
    Question[] indiaHard=
    {new Question ("<html>When is the Indian monsoon season?"+
                   "<br> A. May to July"+
                   "<br> B. October to January"+
                   "<br> C. June to September"+
                   "<br> D. February to April</html>"
                     ,'C'),
      new Question ("<html>What is India's climate type?"+
                    "<br> A. Tundra"+
                    "<br> B. Desert"+
                    "<br> C. Tropical"+
                    "<br> D. Steppe</html>"
                      ,'C'),
      new Question ("<html>How many officially recognized wetlands are in India?"+
                    "<br> A. 71"+
                    "<br> B. 34"+
                    "<br> C. 65"+
                    "<br> D. 93</html>"
                      ,'A'),
      new Question ("<html>What is the main mineral export of India?"+
                    "<br> A. Iron"+
                    "<br> B. Coal"+
                    "<br> C. Jade"+
                    "<br> D. Silver</html>"
                      ,'B'),
      new Question ("<html>What type of tree is founds at Sundarbans in India?"+
                    "<br> A. Cherry"+
                    "<br> B. Almond"+
                    "<br> C. Copperpod"+
                    "<br> D. Mangrove</html>"
                      ,'D')
    };
    try
    {
      Country india =new Country ("India",indiaEasy, indiaMedium,
                                  indiaHard, ImageIO.read (new File ("pics/india.jpg")),
                                  new String[]{
        "I am in the country which contains the Ganges river.",
          "I am in the country in which the Ghats mountain range is found.",
          "I am in the world's third largest producer of coal.",
          "I am in the country in which the Thar desert can be found.",
          "I am in the country in which borders Pakistan and Bangladesh."
      });
      COUNTRIES [7]=india;
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
                     ,'D'),
      new Question ("<html>What continents is Russia part of?"+
                    "<br> A. Africa & Asia"+
                    "<br> B. Europe & Africa"+
                    "<br> C. Europe & Asia"+
                    "<br> D. Asia & Australia</html>"
                      ,'C'),
      new Question ("<html>Which ocean is off the Northern shore of Russia?"+
                    "<br> A. Arctic"+
                    "<br> B. Atlantic"+
                    "<br> C. Pacific"+
                    "<br> D. Indian</html>"
                      ,'A'),
      new Question ("<html>Which ocean is off the Southeastern shore of Russia?"+
                    "<br> A. Arctic"+
                    "<br> B. Pacific"+
                    "<br> C. Atlantic"+
                    "<br> D. Indian</html>"
                      ,'B'),
      new Question ("<html>Which country does not have a border with Russia?"+
                    "<br> A. China"+
                    "<br> B. Lativa"+
                    "<br> C. Norway"+
                    "<br> D. Syria</html>"
                      ,'D')
    };
    Question[] russiaMedium=
    {new Question ("<html>How many different borders are there between Russia and China?"+
                   "<br> A. 2"+
                   "<br> B. 0"+
                   "<br> C. 1"+
                   "<br> D. 3</html>"
                     ,'A'),
      new Question ("<html>Approximately how large is Russia (in square kilometers)?"+
                    "<br> A. 14 million"+
                    "<br> B. 11 million"+
                    "<br> C. 17 million"+
                    "<br> D. 19 million</html>"
                      ,'C'),
      new Question ("<html>What is Russia's main export?"+
                    "<br> A. Petroleum"+
                    "<br> B. Metals"+
                    "<br> C. Lumber"+
                    "<br> D. Cars</html>"
                      ,'A'),
      new Question ("<html>How many seas are on the shores of Russia?"+
                    "<br> A. 5"+
                    "<br> B. 13"+
                    "<br> C. 9"+
                    "<br> D. 16</html>"
                      ,'B'),
      new Question ("<html>In what part of Russia is Kaliningrad located?"+
                    "<br> A. North"+
                    "<br> B. South"+
                    "<br> C. East"+
                    "<br> D. West</html>"
                      ,'D')
    };
    Question[] russiaHard=
    {new Question ("<html>russiaHard1"+
                   "<br> A. St. Petersburg"+
                   "<br> B. Kazan"+
                   "<br> C. Samara"+
                   "<br> D. Moscow</html>"
                     ,'A'),
      new Question ("<html>What is the length of the Ural mountain range"+
                    "<br> A. 1300 km"+
                    "<br> B. 1900 km"+
                    "<br> C. 2400 km"+
                    "<br> D. 2900 km</html>"
                      ,'C'),
      new Question ("<html>Which is the longest river in Europe?"+
                    "<br> A. Volga"+
                    "<br> B. Danube"+
                    "<br> C. Rhine"+
                    "<br> D. Dnieper</html>"
                      ,'A'),
      new Question ("<html>What percentage or Russia is swampland?"+
                    "<br> A. 23%"+
                    "<br> B. 10%"+
                    "<br> C. 15%"+
                    "<br> D. 30%</html>"
                      ,'B'),
      new Question ("<html>What is the average yearly temperature of Russia (in degrees Celsius)?"+
                    "<br> A. 10"+
                    "<br> B. -5"+
                    "<br> C. 0"+
                    "<br> D. 3</html>"
                      ,'D')
    };
    try
    {
      Country russia =new Country ("Russia",russiaEasy, russiaMedium,
                                   russiaHard, ImageIO.read (new File ("pics/russia.jpg")),
                                   new String[]{
        "I am in the largest country in the world.",
          "I am in the country which borders 3 oceans.",
          "I am in the ocuntry which was once ruled by the Romanov dynasty.",
          "I am in the country which is in both Europe and Asia.",
          "I am in the ocuntry in which the Ural mountains can be found."
      });
      COUNTRIES [8]=russia;
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
      new Question ("<html>What is the main religion of Japan?"+
                    "<br> A. Buddhism"+
                    "<br> B. Christianity"+
                    "<br> C. Shintoism"+
                    "<br> D. Islam</html>"
                      ,'C'),
      new Question ("<html>On which island is Tokyo located?"+
                    "<br> A. Honshu"+
                    "<br> B. Hokkaido"+
                    "<br> C. Shikoku"+
                    "<br> D. Kyushu</html>"
                      ,'A'),
      new Question ("<html>What ocean is Japan located in"+
                    "<br> A. Indian"+
                    "<br> B. Pacific"+
                    "<br> C. Atlantic"+
                    "<br> D. Arctic</html>"
                      ,'B'),
      new Question ("<html>What continent is Japan part of?"+
                    "<br> A. Africa"+
                    "<br> B. Europe"+
                    "<br> C. North America"+
                    "<br> D. Asia</html>"
                      ,'D')
    };
    Question[] japanMedium=
    {new Question ("<html>What percentage of Japan is mountainous?"+
                   "<br> A. 40%"+
                   "<br> B. 20%"+
                   "<br> C. 70%"+
                   "<br> D. 90%</html>"
                     ,'C'),
      new Question ("<html>How many chains of mountains can be found in Japan?"+
                    "<br> A. 5"+
                    "<br> B. 1"+
                    "<br> C. 3"+
                    "<br> D. 2</html>"
                      ,'C'),
      new Question ("<html>When was the last eruption of Mt. Fuji?"+
                    "<br> A. 1707"+
                    "<br> B. 1832"+
                    "<br> C. 1654"+
                    "<br> D. 1953</html>"
                      ,'A'),
      new Question ("<html>What is the name of the plain on which Tokyo is situated?"+
                    "<br> A. Nobi"+
                    "<br> B. Kanto"+
                    "<br> C. Kinai"+
                    "<br> D. Ishikari</html>"
                      ,'B'),
      new Question ("<html>Which sea is directly West of Japan?"+
                    "<br> A. Sea of Okhotsk"+
                    "<br> B. Caspian Sea"+
                    "<br> C. Yellow Sea"+
                    "<br> D. Sea of Japan</html>"
                      ,'D')
    };
    Question[] japanHard=
    {new Question ("<html>What direction does the Oyashio current flow?"+
                   "<br> A. North"+
                   "<br> B. West"+
                   "<br> C. South"+
                   "<br> D. East</html>"
                     ,'C'),
      new Question ("<html>When is the Japanese typhoon season?"+
                    "<br> A. April to May"+
                    "<br> B. November to December"+
                    "<br> C. August to September"+
                    "<br> D. February to March</html>"
                      ,'C'),
      new Question ("<html>What is the hottest temperature <br>ever recorded in Japan (in degrees Celsius)?"+
                    "<br> A. 41"+
                    "<br> B. 38"+
                    "<br> C. 45"+
                    "<br> D. 36</html>"
                      ,'A'),
      new Question ("<html>What direction does the Kuroshio current flow?"+
                    "<br> A. East"+
                    "<br> B. North"+
                    "<br> C. South"+
                    "<br> D. West</html>"
                      ,'B'),
      new Question ("<html>What is the population of Osaka?"+
                    "<br> A. 12 million"+
                    "<br> B. 25 million"+
                    "<br> C. 9 million"+
                    "<br> D. 19 million</html>"
                      ,'D')
    };
    try
    {
      Country japan =new Country ("Japan",japanEasy, japanMedium,
                                  japanHard, ImageIO.read (new File ("pics/japan.jpg")),
                                  new String[]{
        "I am in the country in which Mt. Fuji is located.",
          "I am in the country in which Osaka is located.",
          "I am in the country which is made up of four main islands.",
          "I am in the country which was once ruled by a military shogunate.",
          "I am in the country which is directly East of Korea."
      });
      COUNTRIES [9]=japan;
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
      new Question ("<html>Which continent is France part of?"+
                    "<br> A. Africa"+
                    "<br> B. South America"+
                    "<br> C. Europe"+
                    "<br> D. Asia</html>"
                      ,'C'),
      new Question ("<html>Which river is NOT on the shore of France?"+
                    "<br> A. Volga"+
                    "<br> B. Rhone"+
                    "<br> C. Seine"+
                    "<br> D. Garronne</html>"
                      ,'A'),
      new Question ("<html>Which country does NOT have a border with France?"+
                    "<br> A. Andorra"+
                    "<br> B. England"+
                    "<br> C. Italy"+
                    "<br> D. Spain</html>"
                      ,'B'),
      new Question ("<html>What is the name of the mountain range which seperates France from Spain?"+
                    "<br> A. Alps"+
                    "<br> B. Ural"+
                    "<br> C. Himalayas"+
                    "<br> D. Pyrenees</html>"
                      ,'D')
    };
    Question[] franceMedium=
    {new Question ("<html>Which country is NOT a part of France?"+
                   "<br> A. French Guiana"+
                   "<br> B. New Caledonia"+
                   "<br> C. St. Pierre and Miquelon"+
                   "<br> D. Brazil"
                     ,'D'),
      new Question ("<html>What is the main religion of France?"+
                    "<br> A. Islam"+
                    "<br> B. Hindu"+
                    "<br> C. Catholic"+
                    "<br> D. Lutheran</html>"
                      ,'C'),
      new Question ("<html>What continent is French Guiana part of?"+
                    "<br> A. South America"+
                    "<br> B. North America"+
                    "<br> C. Africa"+
                    "<br> D. Europe</html>"
                      ,'A'),
      new Question ("<html>How many countries share a border with mainland France?"+
                    "<br> A. 5"+
                    "<br> B. 8"+
                    "<br> C. 10"+
                    "<br> D. 6</html>"
                      ,'B'),
      new Question ("<html>What continent is Mayotte part of"+
                    "<br> A. Europe"+
                    "<br> B. North America"+
                    "<br> C. Asia"+
                    "<br> D. Africa</html>"
                      ,'D')
    };
    Question[] franceHard=
    {new Question ("<html>How many countries share a border with French Guiana?"+
                   "<br> A. 5"+
                   "<br> B. 3"+
                   "<br> C. 4"+
                   "<br> D. 2</html>"
                     ,'D'),
      new Question ("<html>What year was the first French republic established?"+
                    "<br> A. 1785"+
                    "<br> B. 1790"+
                    "<br> C. 1792"+
                    "<br> D. 1800</html>"
                      ,'C'),
      new Question ("<html>What is the Easternmost point in France"+
                    "<br> A. Lauterbourg"+
                    "<br> B. Pairs"+
                    "<br> C. Alsace"+
                    "<br> D. Calais</html>"
                      ,'A'),
      new Question ("<html>How many non-EU countries share a border with France?"+
                    "<br> A. 2"+
                    "<br> B. 1"+
                    "<br> C. 0"+
                    "<br> D. 3</html>"
                      ,'B'),
      new Question ("<html>Which best describes the climate of la Reunion?"+
                    "<br> A. Desert"+
                    "<br> B. Tundra"+
                    "<br> C. Steppe"+
                    "<br> D. Tropical</html>"
                      ,'D')
    };
    try
    {
      Country france =new Country ("France",franceEasy,franceMedium, franceHard, ImageIO.read (new File ("pics/france.jpeg")),
                                   new String[]{
        "I am in the largest country in Europe.",
          "I am in the country which shares a border with Spain and Germany.",
          "I am in the country in which the Loire river flows.",
          "I am in the first European contry to establish a republic.",
          "I am in the country in which Mont Blanc is located."
      });
      COUNTRIES [10]=france;
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
      new Question ("<html>Which ocean is off the coast of England?"+
                    "<br> A. Arctic"+
                    "<br> B. Pacific"+
                    "<br> C. Indian"+
                    "<br> D. Atlantic</html>"
                      ,'D'),
      new Question ("<html>Which continent is England a part of?"+
                    "<br> A. Europe"+
                    "<br> B. Asia"+
                    "<br> C. Africa"+
                    "<br> D. South America</html>"
                      ,'A'),
      new Question ("<html>What is the name of the river that flows through London?"+
                    "<br> A. Ply"+
                    "<br> B. Thames"+
                    "<br> C. Danube"+
                    "<br> D. Dee</html>"
                      ,'B'),
      new Question ("<html>What is the Westernmost city in England"+
                    "<br> A. London"+
                    "<br> B. Manchester"+
                    "<br> C. Sheffield"+
                    "<br> D. Cornwall</html>"
                      ,'D')
    };
    Question[] englandMedium=
    {new Question ("<html>What is England's climate type?"+
                   "<br> A. Desert"+
                   "<br> B. Temperate"+
                   "<br> C. Tundra"+
                   "<br> D. Steppe</html>"
                     ,'B'),
      new Question ("<html>Which English city is closest to France?"+
                    "<br> A. London"+
                    "<br> B. Cornwall"+
                    "<br> C. Manchester"+
                    "<br> D. Dover</html>"
                      ,'C'),
      new Question ("<html>What is the longest river in the United Kingdom?"+
                    "<br> A. Severn"+
                    "<br> B. Thames"+
                    "<br> C. Avon"+
                    "<br> D. Wye</html>"
                      ,'A'),
      new Question ("<html>Which is the largest of the English Isles?"+
                    "<br> A. Mersea Island"+
                    "<br> B. Isle of Wight"+
                    "<br> C. Isle of Man"+
                    "<br> D. Isle of Man</html>"
                      ,'B'),
      new Question ("<html>What is the Northernmost city in England?"+
                    "<br> A. London"+
                    "<br> B. Manchester"+
                    "<br> C. Worcester"+
                    "<br> D. Marshall Meadows</html>"
                      ,'D')
    };
    Question[] englandHard=
    {new Question ("<html>Which two countries does Hadrian's Wall seperate?"+
                   "<br> A. Scotland & Ireland"+
                   "<br> B. Scotland & England"+
                   "<br> C. France & England"+
                   "<br> D. France & Ireland</html>"
                     ,'B'),
      new Question ("<html>Which are the heighest mountains in England?"+
                    "<br> A. Mendip Hills"+
                    "<br> B. Pennies"+
                    "<br> C. Cumbrian Mountains"+
                    "<br> D. Shropshire Hills</html>"
                      ,'C'),
      new Question ("<html>What percentage of the United Kingdom is used as grazing land?"+
                    "<br> A. 46%"+
                    "<br> B. 21%"+
                    "<br> C. 63%"+
                    "<br> D. 12%</html>"
                      ,'A'),
      new Question ("<html>Which is the most abundant mineral in England?"+
                    "<br> A. Gold"+
                    "<br> B. Coal"+
                    "<br> C. Silver"+
                    "<br> D. Iron</html>"
                      ,'B'),
      new Question ("<html>Which of the following islands is NOT a British territory?"+
                    "<br> A. Gibraltar"+
                    "<br> B. Montserrat"+
                    "<br> C. Pitcairn Islands"+
                    "<br> D. Corsica</html>"
                      ,'D')
    };
    try
    {
      Country england =new Country ("England",englandEasy, englandMedium, englandHard, ImageIO.read (new File ("pics/england.jpg")),
                                    new String[]{
        "I am in the country where London can be found.",
          "I am in the country which was once ruled by Henry IV and Richard II.",
          "I am in the country which controlls the Strait of Gibraltar.",
          "I am in the largest country in the United Kingdom.",
          "I am in the country on the North side of the English Channel."
      });
      COUNTRIES [11]=england;
    }
    catch (IOException e)
    {
      System.out.println ("IO");
    }
    
    
    remove (splashScreen);
    
    alreadyBeen=new ArrayList<Country>();
    currentCountry=COUNTRIES[0];
    //alreadyBeen.add(COUNTRIES[0]);
    
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
