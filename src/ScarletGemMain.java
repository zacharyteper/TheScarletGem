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
   * 10 - Brazil
   * 11 - Cuba
   * 12 - South Africa
   * 13 - Switzerland
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
   * Holds the time remaining in the game, in seconds. This value will be regularly decremented 
   * throughout the game, and displayed at the top of the game screen. When this value reaches 0, 
   * the game will end.
   */
  private int timeRemaining;
  private boolean paused;
  private ArrayList <Country> alreadyBeen;
  public static final Country[] countries=new Country[12];
  private GamePanel gamePanel;
  private JLabel levelCounter=new JLabel();
  private MainMenuPanel mainMenuPanel;
  private HighScoresViewer highScoresViewer;
  private InstructionsViewer instructionsViewer;
  private GameTimer gameTimer;
  private int currentQuestion=0;
  private JMenuItem howToPlayItem =new JMenuItem ("How To Play Ctrl+R");
  private JMenuItem printItem=new JMenuItem ("Print Ctrl+P");
  private JMenuItem saveItem=new JMenuItem ("Save Ctrl+S");
  private JMenuItem exitItem=new JMenuItem ("Exit Ctrl+Q");
  private JMenuItem highScoresItem=new JMenuItem ("View High Scores Ctrl+E");
  
  private JMenuItem helpItem=new JMenuItem ("Help Ctrl+H");
  private JMenuItem aboutItem=new JMenuItem ("About Ctrl+A");
  
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
    }
    else if (ae.getSource().equals(helpItem))
    {
    }
    else if (ae.getSource().equals(aboutItem))
    {
    }
    else if (ae.getSource().equals(highScoresItem))
    {
    }
    else if (ae.getSource().equals(mainMenuPanel.getEasyButton()))
    {
      levelsRemaining=3;
      difficulty=0;
      gamePanel = new GamePanel (difficulty);
      
      remove(mainMenuPanel);
      add(levelCounter);
      
      add(gamePanel);
      showCountryPanel();      
    }
    else if (ae.getSource().equals(mainMenuPanel.getMediumButton()))
    {
      levelsRemaining=6;
      difficulty=1;
      gamePanel = new GamePanel (difficulty);
      
      remove(mainMenuPanel);
      add(levelCounter);
      
      add(gamePanel);
      showCountryPanel();      
    }
    else if (ae.getSource().equals(mainMenuPanel.getHardButton()))
    {
      levelsRemaining=9;
      difficulty=2;
      gamePanel = new GamePanel (difficulty);
      
      remove(mainMenuPanel);
      add(levelCounter);
      
      add(gamePanel);
      showCountryPanel();      
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
        timeRemaining=Integer.parseInt(in.readLine());
        in.readLine();
        currentQuestion=Integer.parseInt(in.readLine());
        System.out.println (currentCountry.getName());
        showCountryPanel();
        
      }
      catch (IOException e)
      {
        JOptionPane.showMessageDialog(null,"File could not be loaded.");
      }
      levelsRemaining=3;
      remove (mainMenuPanel);
      add(levelCounter);
      add(gamePanel);
      levelsRemaining=3-alreadyBeen.size();
      showCountryPanel();
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
      
      out.println ();
      
      out.println (timeRemaining);
      out.println ();
      out.println (currentQuestion);
      out.close();
    }
    catch (IOException e)
    {
      JOptionPane.showMessageDialog(null,"Progress could not be saved.");
    }
  }
  private void showCountryPanel()
  {
    System.out.println (currentCountry.getName());
    try
    {
      Thread.sleep(10);
    }
    catch (InterruptedException e)
    {
    }
    gamePanel.getAButton().setEnabled(true);
    gamePanel.getBButton().setEnabled(true);
    gamePanel.getCButton().setEnabled(true);
    gamePanel.getDButton().setEnabled(true);
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
      gamePanel.getAButton().addActionListener(this);
      gamePanel.getBButton().addActionListener(this);
      gamePanel.getCButton().addActionListener(this);
      gamePanel.getDButton().addActionListener(this); 
      
      
    }
  }
  private void showMapPanel()
  {
//    for (Country s:alreadyBeen)
//      System.out.println(s.getName());
    gamePanel.getAButton().setEnabled(true);
    gamePanel.getBButton().setEnabled(true);
    gamePanel.getCButton().setEnabled(true);
    do
    {
      currentCountry=countries[(int)(Math.random()*10)];
    }
    while (alreadyBeen.contains(currentCountry));
    
    Country wrong1;
    Country wrong2;
    do
    {
      wrong1=countries[(int)(Math.random()*10)];
    }
    while (alreadyBeen.contains(wrong1)||wrong1.equals(currentCountry));
    
    do
    {
      wrong2=countries[(int)(Math.random()*10)];
    }
    while (alreadyBeen.contains(wrong2)||wrong1.equals(wrong2)
             ||wrong2.equals(currentCountry));
    
    gamePanel.setDestinations(new Country[]{currentCountry,wrong1,wrong2});
    gamePanel.switchToMap();
    
    //countries[(int)(Math.random()*
    
  }
//  private void checkDestination (int destination)
//  {
//    if (destination==gamePanel.getAnswer())
//    {
//      showCountryPanel();
//    }
//    else
//    {
//      gamePanel.removeWrongDestination(destination);
//    }
//    
//  }
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
      }
      else
      {
        gamePanel.removeWrongDestination(answer+65);
      }
    }
  }
  private void endGame()
  {
    remove(gamePanel);
    remove(levelCounter);
    //remove(timer);
    //remove(pauseButton);
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
  /* ADD YOUR CODE HERE */
  public ScarletGemMain()
  {
    super ("The Scarlet Gem");
    setVisible (true);
    setSize (640,500);
    setDefaultCloseOperation (JFrame.DO_NOTHING_ON_CLOSE);
    
    //set the JFrame icon
    try
    {
      setIconImage (ImageIO.read (new File ("scarlet-gem.png")));
    }
    catch (IOException e)
    {
    }
    
    //setup countries
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
     * 10 - Brazil
     * 11 - Cuba
     * 12 - South Africa
     * 13 - Switzerland
     */
//    countries[0]=new Country("Canada");
//    countries[1]=new Country("China");
//    countries[2]=new Country("USA");
//    countries[3]=new Country("Mexico");
//    countries[4]=new Country("Portugal");
//    countries[5]=new Country("Australia");
//    countries[6]=new Country("Egypt");
//    countries[7]=new Country("India");
//    countries[8]=new Country("Russia");
//    countries[9]=new Country("Japan");
//    countries[10]=new Country("Brazil");
//    countries[11]=new Country("Cuba");
//    countries[12]=new Country("South Africa");
//    countries[13]=new Country("Switzerland");
    
    Question[] canadaEasy=
    {new Question ("What is the capital of Canada?"+
                   "\n A. Toronto"+
                   "\n B. Montreal"+
                   "\n C. Ottawa"+
                   "\n D. Portugal"
                     ,'C'),
      new Question ("In what part of Canada is Newfoundland located?"+
                    "\n A. North"+
                    "\n B. West"+
                    "\n C. South"+
                    "\n D. East"
                      ,'D'),
      new Question ("In what part of Canada is Nunavut?"+
                    "\n A. North"+
                    "\n B. West"+
                    "\n C. South"+
                    "\n D. East"
                      ,'A'),
      new Question ("What continent is Canada part of?"+
                    "\n A. Europe"+
                    "\n B. North America"+
                    "\n C. Africa"+
                    "\n D. Asia"
                      ,'B'),
      new Question ("What are the two official languages of Canada?"+
                    "\n A. Spanish & French"+
                    "\n B. German & English"+
                    "\n C. English & Spanish"+
                    "\n D. French & English"
                      ,'D')
    };
    Question[] canadaMedium=
    {new Question ("CanadaMedium1"+
                   "\n A. Toronto"+
                   "\n B. Montreal"+
                   "\n C. Ottawa"+
                   "\n D. Portugal"
                     ,'C'),
      new Question ("CanadaMedium2"+
                    "\n A. North"+
                    "\n B. West"+
                    "\n C. South"+
                    "\n D. East"
                      ,'D'),
      new Question ("CanadaMedium3"+
                    "\n A. North"+
                    "\n B. West"+
                    "\n C. South"+
                    "\n D. East"
                      ,'A'),
      new Question ("CanadaMedium4"+
                    "\n A. Europe"+
                    "\n B. North America"+
                    "\n C. Africa"+
                    "\n D. Asia"
                      ,'B'),
      new Question ("CanadaMedium5"+
                    "\n A. Spanish & French"+
                    "\n B. German & English"+
                    "\n C. English & Spanish"+
                    "\n D. French & English"
                      ,'D')
    };
    try
    {
      Country canada =new Country ("Canada",canadaEasy, canadaMedium, null, ImageIO.read (new File ("canada.jpg")),
                                   null);
      countries [0]=canada;
    }
    catch (IOException e)
    {
      System.out.println ("IO");
    }
    //initialize China
    Question[] chinaEasy=
    {new Question ("What is the capital of China?"+
                   "\n A. Beijing"+
                   "\n B. Shanghai"+
                   "\n C. Bangkok"+
                   "\n D. Tokyo"
                     ,'A'),
      new Question ("Which ocean is off the coast of China?"+
                    "\n A. Atlantic"+
                    "\n B. Indian"+
                    "\n C. Pacific"+
                    "\n D. Arctic"
                      ,'C'),
      new Question ("In what part of China are the Himalaya Mountains?"+
                    "\n A. North"+
                    "\n B. West"+
                    "\n C. South"+
                    "\n D. East"
                      ,'B'),
      new Question ("What continent is China part of?"+
                    "\n A. Europe"+
                    "\n B. North America"+
                    "\n C. Africa"+
                    "\n D. Asia"
                      ,'D'),
      new Question ("What is the main agricultural crop of China?"+
                    "\n A. Tomatoes"+
                    "\n B. Rice"+
                    "\n C. Radishes"+
                    "\n D. Corn"
                      ,'B')
    };
    Question[] chinaMedium=
    {new Question ("ChinaMedium1"+
                   "\n A. Beijing"+
                   "\n B. Shanghai"+
                   "\n C. Bangkok"+
                   "\n D. Tokyo"
                     ,'A'),
      new Question ("chinaMedium2"+
                    "\n A. Atlantic"+
                    "\n B. Indian"+
                    "\n C. Pacific"+
                    "\n D. Arctic"
                      ,'C'),
      new Question ("chinaMedium3"+
                    "\n A. North"+
                    "\n B. West"+
                    "\n C. South"+
                    "\n D. East"
                      ,'B'),
      new Question ("chinaMedium4"+
                    "\n A. Europe"+
                    "\n B. North America"+
                    "\n C. Africa"+
                    "\n D. Asia"
                      ,'D'),
      new Question ("chinaMedium5"+
                    "\n A. Tomatoes"+
                    "\n B. Rice"+
                    "\n C. Radishes"+
                    "\n D. Corn"
                      ,'B')
    };
    try
    {
      Country china =new Country ("China",chinaEasy, chinaMedium, null, ImageIO.read (new File ("china.jpg")),
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
    {new Question ("What is the capital of the USA?"+
                   "\n A. Boston"+
                   "\n B. New York"+
                   "\n C. Texas"+
                   "\n D. Washington D.C."
                     ,'D'),
      new Question ("In what part of the USA is New Jersey located?"+
                    "\n A. North"+
                    "\n B. West"+
                    "\n C. East"+
                    "\n D. South"
                      ,'C'),
      new Question ("In which state is San Fransisco located?"+
                    "\n A. Texas"+
                    "\n B. California"+
                    "\n C. Mexico"+
                    "\n D. Florida"
                      ,'B'),
      new Question ("What continent is the USA part of?"+
                    "\n A. Europe"+
                    "\n B. Asia"+
                    "\n C. Africa"+
                    "\n D. North America"
                      ,'D'),
      new Question ("What is the official language of the USA?"+
                    "\n A. English"+
                    "\n B. Spanish"+
                    "\n C. French"+
                    "\n D. Chinese"
                      ,'A')
    };
    Question[] usaMedium=
    {new Question ("USAMedium1"+
                   "\n A. Boston"+
                   "\n B. New York"+
                   "\n C. Texas"+
                   "\n D. Washington D.C."
                     ,'D'),
      new Question ("USAMedium2"+
                    "\n A. North"+
                    "\n B. West"+
                    "\n C. East"+
                    "\n D. South"
                      ,'C'),
      new Question ("USAMedium3"+
                    "\n A. Texas"+
                    "\n B. California"+
                    "\n C. Mexico"+
                    "\n D. Florida"
                      ,'B'),
      new Question ("USAMedium4"+
                    "\n A. Europe"+
                    "\n B. Asia"+
                    "\n C. Africa"+
                    "\n D. North America"
                      ,'D'),
      new Question ("USAMedium5"+
                    "\n A. English"+
                    "\n B. Spanish"+
                    "\n C. French"+
                    "\n D. Chinese"
                      ,'A')
    };
    try
    {
      Country usa =new Country ("USA",usaEasy, usaMedium, null, ImageIO.read (new File ("USA.jpg")),
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
    {new Question ("What is the capital of Mexico?"+
                   "\n A. Peru"+
                   "\n B. Mexico City"+
                   "\n C. Monterrey"+
                   "\n D. Cancun"
                     ,'B'),
      new Question ("Which tribal group is indigenous to Mexico?"+
                    "\n A. Inuit"+
                    "\n B. Hindi"+
                    "\n C. Portugal"+
                    "\n D. Aztec"
                      ,'D'),
      new Question ("Which country is directly north of Mexico?"+
                    "\n A. USA"+
                    "\n B. Chile"+
                    "\n C. Africa"+
                    "\n D. Arctic"
                      ,'A'),
      new Question ("What continent is Mexico part of?"+
                    "\n A. Europe"+
                    "\n B. North America"+
                    "\n C. Africa"+
                    "\n D. Asia"
                      ,'B'),
      new Question ("What is the official language of Mexico?"+
                    "\n A. French"+
                    "\n B. German"+
                    "\n C. English"+
                    "\n D. Spanish"
                      ,'D')
    };
    Question[] mexicoMedium=
    {new Question ("mexicoMedium1"+
                   "\n A. Peru"+
                   "\n B. Mexico City"+
                   "\n C. Monterrey"+
                   "\n D. Cancun"
                     ,'B'),
      new Question ("mexicoMedium2"+
                    "\n A. Inuit"+
                    "\n B. Hindi"+
                    "\n C. Portugal"+
                    "\n D. Aztec"
                      ,'D'),
      new Question ("mexicoMedium3"+
                    "\n A. USA"+
                    "\n B. Chile"+
                    "\n C. Africa"+
                    "\n D. Arctic"
                      ,'A'),
      new Question ("mexicoMedium4"+
                    "\n A. Europe"+
                    "\n B. North America"+
                    "\n C. Africa"+
                    "\n D. Asia"
                      ,'B'),
      new Question ("mexicoMedium5"+
                    "\n A. French"+
                    "\n B. German"+
                    "\n C. English"+
                    "\n D. Spanish"
                      ,'D')
    };
    try
    {
      Country mexico =new Country ("Mexico",mexicoEasy, mexicoMedium, null, ImageIO.read (new File ("mexico.jpeg")),
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
    {new Question ("What is the capital of Portugal?"+
                   "\n A. Barcelona"+
                   "\n B. Lisbon"+
                   "\n C. Madrid"+
                   "\n D. Madeira"
                     ,'B'),
      new Question ("Which ocean is off the coast of Portugal?"+
                    "\n A. Arctic"+
                    "\n B. Pacific"+
                    "\n C. Indian"+
                    "\n D. Atlantic"
                      ,'D'),
      new Question ("Which country is directly east of Portugal?"+
                    "\n A. Spain"+
                    "\n B. Chile"+
                    "\n C. Africa"+
                    "\n D. Arctic"
                      ,'A'),
      new Question ("What continent is Portugal part of?"+
                    "\n A. North America"+
                    "\n B. Europe"+
                    "\n C. Africa"+
                    "\n D. Asia"
                      ,'B'),
      new Question ("What is the official language of Portugal?"+
                    "\n A. French"+
                    "\n B. German"+
                    "\n C. English"+
                    "\n D. Portuguese"
                      ,'D')
    };
    Question[] portugalMedium=
    {new Question ("portugalMedium1"+
                   "\n A. Barcelona"+
                   "\n B. Lisbon"+
                   "\n C. Madrid"+
                   "\n D. Madeira"
                     ,'B'),
      new Question ("PortugalMedium2"+
                    "\n A. Arctic"+
                    "\n B. Pacific"+
                    "\n C. Indian"+
                    "\n D. Atlantic"
                      ,'D'),
      new Question ("portugalMedium3"+
                    "\n A. Spain"+
                    "\n B. Chile"+
                    "\n C. Africa"+
                    "\n D. Arctic"
                      ,'A'),
      new Question ("portugalMedium4"+
                    "\n A. North America"+
                    "\n B. Europe"+
                    "\n C. Africa"+
                    "\n D. Asia"
                      ,'B'),
      new Question ("portugalMedium5"+
                    "\n A. French"+
                    "\n B. German"+
                    "\n C. English"+
                    "\n D. Portuguese"
                      ,'D')
    };
    try
    {
      Country portugal =new Country ("Portugal",portugalEasy, portugalMedium, null, ImageIO.read (new File ("portugal.jpg")),
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
    {new Question ("What is the capital of Australia?"+
                   "\n A. Sydney"+
                   "\n B. Canberra"+
                   "\n C. Brisbane"+
                   "\n D. New Zealand"
                     ,'B'),
      new Question ("Which ocean is off the west coast of Australia?"+
                    "\n A. Arctic"+
                    "\n B. Pacific"+
                    "\n C. Indian"+
                    "\n D. Atlantic"
                      ,'C'),
      new Question ("Which country is directly north of Australia?"+
                    "\n A. Indonesia"+
                    "\n B. Russia"+
                    "\n C. Africa"+
                    "\n D. Mexico"
                      ,'A'),
      new Question ("What continent is Australia part of?"+
                    "\n A. India"+
                    "\n B. Australia"+
                    "\n C. Africa"+
                    "\n D. Asia"
                      ,'B'),
      new Question ("What is the largest city in Australia?"+
                    "\n A. New Zealand"+
                    "\n B. Canberra"+
                    "\n C. Melbourne"+
                    "\n D. Sydney"
                      ,'D')
    };
    Question[] australiaMedium=
    {new Question ("australiaMedium1"+
                   "\n A. Sydney"+
                   "\n B. Canberra"+
                   "\n C. Brisbane"+
                   "\n D. New Zealand"
                     ,'B'),
      new Question ("australiaMedium2"+
                    "\n A. Arctic"+
                    "\n B. Pacific"+
                    "\n C. Indian"+
                    "\n D. Atlantic"
                      ,'C'),
      new Question ("australiaMedium3"+
                    "\n A. Indonesia"+
                    "\n B. Russia"+
                    "\n C. Africa"+
                    "\n D. Mexico"
                      ,'A'),
      new Question ("australiaMedium4"+
                    "\n A. India"+
                    "\n B. Australia"+
                    "\n C. Africa"+
                    "\n D. Asia"
                      ,'B'),
      new Question ("australiaMedium5"+
                    "\n A. New Zealand"+
                    "\n B. Canberra"+
                    "\n C. Melbourne"+
                    "\n D. Sydney"
                      ,'D')
    };
    try
    {
      Country australia =new Country ("Australia",australiaEasy, australiaMedium, null, ImageIO.read (new File ("australia.jpg")),
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
    {new Question ("What is the capital of Egypt?"+
                   "\n A. Cairo"+
                   "\n B. Memphis"+
                   "\n C. Alexandria"+
                   "\n D. Suez"
                     ,'A'),
      new Question ("EgyptEasy2"+
                    "\n A. Arctic"+
                    "\n B. Pacific"+
                    "\n C. Indian"+
                    "\n D. Atlantic"
                      ,'C'),
      new Question ("EgyptEasy3"+
                    "\n A. Indonesia"+
                    "\n B. Russia"+
                    "\n C. Africa"+
                    "\n D. Mexico"
                      ,'A'),
      new Question ("EgyptEasy4"+
                    "\n A. India"+
                    "\n B. Australia"+
                    "\n C. Africa"+
                    "\n D. Asia"
                      ,'B'),
      new Question ("EgyptEasy5"+
                    "\n A. New Zealand"+
                    "\n B. Canberra"+
                    "\n C. Melbourne"+
                    "\n D. Sydney"
                      ,'D')
    };
    Question[] egyptMedium=
    {new Question ("egyptMedium1"+
                   "\n A. Cairo"+
                   "\n B. Memphis"+
                   "\n C. Alexandria"+
                   "\n D. Suez"
                     ,'A'),
      new Question ("EgyptMedium2"+
                    "\n A. Arctic"+
                    "\n B. Pacific"+
                    "\n C. Indian"+
                    "\n D. Atlantic"
                      ,'C'),
      new Question ("EgyptMedium3"+
                    "\n A. Indonesia"+
                    "\n B. Russia"+
                    "\n C. Africa"+
                    "\n D. Mexico"
                      ,'A'),
      new Question ("EgyptMedium4"+
                    "\n A. India"+
                    "\n B. Australia"+
                    "\n C. Africa"+
                    "\n D. Asia"
                      ,'B'),
      new Question ("EgyptMedium5"+
                    "\n A. New Zealand"+
                    "\n B. Canberra"+
                    "\n C. Melbourne"+
                    "\n D. Sydney"
                      ,'D')
    };
    try
    {
      Country egypt =new Country ("Egypt",egyptEasy, egyptMedium, null, ImageIO.read (new File ("egypt.jpg")),
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
    {new Question ("What is the capital of India?"+
                   "\n A. Mumbai"+
                   "\n B. Bangalore"+
                   "\n C. New Delhi"+
                   "\n D. Hyderabad"
                     ,'C'),
      new Question ("IndiaEasy2"+
                    "\n A. Arctic"+
                    "\n B. Pacific"+
                    "\n C. Indian"+
                    "\n D. Atlantic"
                      ,'C'),
      new Question ("IndiaEasy3"+
                    "\n A. Indonesia"+
                    "\n B. Russia"+
                    "\n C. Africa"+
                    "\n D. Mexico"
                      ,'A'),
      new Question ("IndiaEasy4"+
                    "\n A. India"+
                    "\n B. Australia"+
                    "\n C. Africa"+
                    "\n D. Asia"
                      ,'B'),
      new Question ("IndiaEasy5"+
                    "\n A. New Zealand"+
                    "\n B. Canberra"+
                    "\n C. Melbourne"+
                    "\n D. Sydney"
                      ,'D')
    };
    Question[] indiaMedium=
    {new Question ("indiaMedium1"+
                   "\n A. Mumbai"+
                   "\n B. Bangalore"+
                   "\n C. New Delhi"+
                   "\n D. Hyderabad"
                     ,'C'),
      new Question ("IndiaMedium2"+
                    "\n A. Arctic"+
                    "\n B. Pacific"+
                    "\n C. Indian"+
                    "\n D. Atlantic"
                      ,'C'),
      new Question ("IndiaMedium3"+
                    "\n A. Indonesia"+
                    "\n B. Russia"+
                    "\n C. Africa"+
                    "\n D. Mexico"
                      ,'A'),
      new Question ("IndiaMedium4"+
                    "\n A. India"+
                    "\n B. Australia"+
                    "\n C. Africa"+
                    "\n D. Asia"
                      ,'B'),
      new Question ("IndiaMedium5"+
                    "\n A. New Zealand"+
                    "\n B. Canberra"+
                    "\n C. Melbourne"+
                    "\n D. Sydney"
                      ,'D')
    };
    try
    {
      Country india =new Country ("India",indiaEasy, indiaMedium, null, ImageIO.read (new File ("india.jpg")),
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
    {new Question ("What is the capital of Russia?"+
                   "\n A. St. Petersburg"+
                   "\n B. Kazan"+
                   "\n C. Samara"+
                   "\n D. Moscow"
                     ,'A'),
      new Question ("RussiaEasy2"+
                    "\n A. Arctic"+
                    "\n B. Pacific"+
                    "\n C. Indian"+
                    "\n D. Atlantic"
                      ,'C'),
      new Question ("RussiaEasy3"+
                    "\n A. Indonesia"+
                    "\n B. Russia"+
                    "\n C. Africa"+
                    "\n D. Mexico"
                      ,'A'),
      new Question ("RussiaEasy4"+
                    "\n A. India"+
                    "\n B. Australia"+
                    "\n C. Africa"+
                    "\n D. Asia"
                      ,'B'),
      new Question ("RussiaEasy5"+
                    "\n A. New Zealand"+
                    "\n B. Canberra"+
                    "\n C. Melbourne"+
                    "\n D. Sydney"
                      ,'D')
    };
    Question[] russiaMedium=
    {new Question ("russiaMedium1"+
                   "\n A. St. Petersburg"+
                   "\n B. Kazan"+
                   "\n C. Samara"+
                   "\n D. Moscow"
                     ,'A'),
      new Question ("RussiaMedium2"+
                    "\n A. Arctic"+
                    "\n B. Pacific"+
                    "\n C. Indian"+
                    "\n D. Atlantic"
                      ,'C'),
      new Question ("RussiaMedium3"+
                    "\n A. Indonesia"+
                    "\n B. Russia"+
                    "\n C. Africa"+
                    "\n D. Mexico"
                      ,'A'),
      new Question ("RussiaMedium4"+
                    "\n A. India"+
                    "\n B. Australia"+
                    "\n C. Africa"+
                    "\n D. Asia"
                      ,'B'),
      new Question ("RussiaMedium5"+
                    "\n A. New Zealand"+
                    "\n B. Canberra"+
                    "\n C. Melbourne"+
                    "\n D. Sydney"
                      ,'D')
    };
    try
    {
      Country russia =new Country ("Russia",russiaEasy, russiaMedium, null, ImageIO.read (new File ("russia.jpg")),
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
    {new Question ("What is the capital of Japan?"+
                   "\n A. Yokohama"+
                   "\n B. Osaka"+
                   "\n C. Tokyo"+
                   "\n D. Nagoya"
                     ,'C'),
      new Question ("JapanEasy2"+
                    "\n A. Arctic"+
                    "\n B. Pacific"+
                    "\n C. Indian"+
                    "\n D. Atlantic"
                      ,'C'),
      new Question ("JapanEasy3"+
                    "\n A. Indonesia"+
                    "\n B. Russia"+
                    "\n C. Africa"+
                    "\n D. Mexico"
                      ,'A'),
      new Question ("JapanEasy4"+
                    "\n A. India"+
                    "\n B. Australia"+
                    "\n C. Africa"+
                    "\n D. Asia"
                      ,'B'),
      new Question ("JapanEasy5"+
                    "\n A. New Zealand"+
                    "\n B. Canberra"+
                    "\n C. Melbourne"+
                    "\n D. Sydney"
                      ,'D')
    };
    Question[] japanMedium=
    {new Question ("japanMedium1"+
                   "\n A. Yokohama"+
                   "\n B. Osaka"+
                   "\n C. Tokyo"+
                   "\n D. Nagoya"
                     ,'C'),
      new Question ("JapanMedium2"+
                    "\n A. Arctic"+
                    "\n B. Pacific"+
                    "\n C. Indian"+
                    "\n D. Atlantic"
                      ,'C'),
      new Question ("JapanMedium3"+
                    "\n A. Indonesia"+
                    "\n B. Russia"+
                    "\n C. Africa"+
                    "\n D. Mexico"
                      ,'A'),
      new Question ("JapanMedium4"+
                    "\n A. India"+
                    "\n B. Australia"+
                    "\n C. Africa"+
                    "\n D. Asia"
                      ,'B'),
      new Question ("JapanMedium5"+
                    "\n A. New Zealand"+
                    "\n B. Canberra"+
                    "\n C. Melbourne"+
                    "\n D. Sydney"
                      ,'D')
    };
    try
    {
      Country japan =new Country ("Japan",japanEasy, japanMedium, null, ImageIO.read (new File ("japan.jpg")),
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
    {new Question ("What is the capital of France?"+
                   "\n A. Marceille"+
                   "\n B. Nice"+
                   "\n C. Lille"+
                   "\n D. Paris"
                     ,'D'),
      new Question ("FranceEasy2"+
                    "\n A. Arctic"+
                    "\n B. Pacific"+
                    "\n C. Indian"+
                    "\n D. Atlantic"
                      ,'C'),
      new Question ("FranceEasy3"+
                    "\n A. Indonesia"+
                    "\n B. Russia"+
                    "\n C. Africa"+
                    "\n D. Mexico"
                      ,'A'),
      new Question ("FranceEasy4"+
                    "\n A. India"+
                    "\n B. Australia"+
                    "\n C. Africa"+
                    "\n D. Asia"
                      ,'B'),
      new Question ("FranceEasy5"+
                    "\n A. New Zealand"+
                    "\n B. Canberra"+
                    "\n C. Melbourne"+
                    "\n D. Sydney"
                      ,'D')
    };
    Question[] franceMedium=
    {new Question ("franceMedium1"+
                   "\n A. Marceille"+
                   "\n B. Nice"+
                   "\n C. Lille"+
                   "\n D. Paris"
                     ,'D'),
      new Question ("FranceMedium2"+
                    "\n A. Arctic"+
                    "\n B. Pacific"+
                    "\n C. Indian"+
                    "\n D. Atlantic"
                      ,'C'),
      new Question ("FranceMedium3"+
                    "\n A. Indonesia"+
                    "\n B. Russia"+
                    "\n C. Africa"+
                    "\n D. Mexico"
                      ,'A'),
      new Question ("FranceMedium4"+
                    "\n A. India"+
                    "\n B. Australia"+
                    "\n C. Africa"+
                    "\n D. Asia"
                      ,'B'),
      new Question ("FranceMedium5"+
                    "\n A. New Zealand"+
                    "\n B. Canberra"+
                    "\n C. Melbourne"+
                    "\n D. Sydney"
                      ,'D')
    };
    try
    {
      Country france =new Country ("France",franceEasy,franceMedium, null, ImageIO.read (new File ("france.jpeg")),
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
    {new Question ("What is the capital of England?"+
                   "\n A. London"+
                   "\n B. Leeds"+
                   "\n C. Birmingham"+
                   "\n D. Bradford"
                     ,'B'),
      new Question ("EngalndEasy2"+
                    "\n A. Arctic"+
                    "\n B. Pacific"+
                    "\n C. Indian"+
                    "\n D. Atlantic"
                      ,'C'),
      new Question ("EnglandEasy3"+
                    "\n A. Indonesia"+
                    "\n B. Russia"+
                    "\n C. Africa"+
                    "\n D. Mexico"
                      ,'A'),
      new Question ("EnglandEasy4"+
                    "\n A. India"+
                    "\n B. Australia"+
                    "\n C. Africa"+
                    "\n D. Asia"
                      ,'B'),
      new Question ("EnglandEasy5"+
                    "\n A. New Zealand"+
                    "\n B. Canberra"+
                    "\n C. Melbourne"+
                    "\n D. Sydney"
                      ,'D')
    };
    Question[] englandMedium=
    {new Question ("englandMedium1"+
                   "\n A. London"+
                   "\n B. Leeds"+
                   "\n C. Birmingham"+
                   "\n D. Bradford"
                     ,'B'),
      new Question ("EngalndMedium2"+
                    "\n A. Arctic"+
                    "\n B. Pacific"+
                    "\n C. Indian"+
                    "\n D. Atlantic"
                      ,'C'),
      new Question ("EnglandMedium3"+
                    "\n A. Indonesia"+
                    "\n B. Russia"+
                    "\n C. Africa"+
                    "\n D. Mexico"
                      ,'A'),
      new Question ("EnglandMedium4"+
                    "\n A. India"+
                    "\n B. Australia"+
                    "\n C. Africa"+
                    "\n D. Asia"
                      ,'B'),
      new Question ("EnglandMedium5"+
                    "\n A. New Zealand"+
                    "\n B. Canberra"+
                    "\n C. Melbourne"+
                    "\n D. Sydney"
                      ,'D')
    };
    try
    {
      Country england =new Country ("England",englandEasy, englandMedium, null, ImageIO.read (new File ("england.jpg")),
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