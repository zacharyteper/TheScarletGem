import java.awt.*;
import javax.swing.*;
import java.awt.print.*;
import javax.imageio.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
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
  private Country currentCountry;
  private int difficulty;
  private int levelsRemaining;
  private int timeRemaining;
  private boolean paused;
  private ArrayList <Country> alreadyBeen;
  private Country[] countries;
  private JPanel gamePanel;
  private MapPanel mapPanel;
  private CountryPanel countryPanel;
  private MainMenuPanel mainMenuPanel;
  private HighScoresViewer highScoresViewer;
  private InstructionsViewer instructionsViewer;
  private GameTimer gameTimer;
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
      dispose();
      System.exit(0);
    }
    if (ae.getSource().equals(mainMenuPanel.getEasyButton()))
    {
      levelsRemaining=1;
      showCountryPanel();      
    }
    if (ae.getSource().equals(countryPanel.getAButton()))
    {
      checkAnswer('A');
    }
    if (ae.getSource().equals(countryPanel.getBButton()))
    {
      checkAnswer ('B');
    }
    if (ae.getSource().equals(countryPanel.getCButton()))
    {
      checkAnswer ('C');
    }
    if (ae.getSource().equals(countryPanel.getDButton()))
    {
      checkAnswer ('D');
    }
    if (ae.getSource().equals(mapPanel.getDestination1()))
    {
      checkDestination (1);
    }
    if (ae.getSource().equals(mapPanel.getDestination2()))
    {
      checkDestination (2);
    }
    if (ae.getSource().equals(mapPanel.getDestination3()))
    {
      checkDestination (3);
    }
    revalidate();
  }
  private void showCountryPanel()
  {
    System.out.println ("showCountry");
   remove (mainMenuPanel);
      countryPanel=new CountryPanel (countries[0],null,countries[0].getRandQuestion(0));
      add (countryPanel);
      countryPanel.getAButton().addActionListener(this);
      countryPanel.getBButton().addActionListener(this);
      countryPanel.getCButton().addActionListener(this);
      countryPanel.getDButton().addActionListener(this); 
  }
  private void showMapPanel()
  {
    do
    {
      currentCountry=countries[(int)(Math.random()*13)];
    }
    while (!alreadyBeen.contains(currentCountry));
    
    System.out.println  ("showMap");
    remove (countryPanel);
    System.out.println (countries[1]);
    System.out.println (countries[2]);
    mapPanel=new MapPanel (null, new Country[]
                             {countries[1], countries[2],countries[3]},countries[1].getRandClue(),1);
    
    add (mapPanel);
  }
  private void checkDestination (int destination)
  {
    if (destination==mapPanel.getAnswer())
    {
    }
    else
      mapPanel.removeWrongAnswer(destination);
    
  }
  private void checkAnswer (char answer)
  {
    if (countryPanel.getCurrentQuestion().getAnswer()==answer)
    {
      levelsRemaining--;
      showMapPanel();
    }
    else
      countryPanel.removeWrongAnswer(answer);
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
    setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
    
    //set the JFrame icon
    try
    {
      setIconImage (ImageIO.read (new File ("scarlet-gem.png")));
    }
    catch (IOException e)
    {
    }
    
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
    revalidate();
    
    //setup countries
    countries=new Country[14];
    
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
      new Question ("What languages the two official languages of Canada?"+
                    "\n A. Spanish & French"+
                    "\n B. German & English"+
                    "\n C. English & Spanish"+
                    "\n D. French & English"
                      ,'D')
    };
    try
    {
      Country canada =new Country ("Canada",canadaEasy, null, null, ImageIO.read (new File ("canada.jpg")),
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
      new Question ("Which Ocean is off the coast of China?"+
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
    try
    {
      Country china =new Country ("China",chinaEasy, null, null, ImageIO.read (new File ("china.jpg")),
                                  new String[]{"I am in the country with the largest population in the world."});
      countries [1]=china;
    }
    catch (IOException e)
    {
      System.out.println ("IO");
    }
    //initialize USA
    Question[] usaEasy=
    {new Question ("What is the capital of USA?"+
                   "\n A. Boston"+
                   "\n B. New York"+
                   "\n C. Texas"+
                   "\n D. Washington D.C."
                     ,'D'),
      new Question ("In what part of USA is New Jersey located?"+
                    "\n A. North"+
                    "\n B. West"+
                    "\n C. East"+
                    "\n D. South"
                      ,'C'),
      new Question ("In which state of USA is San Fransisco?"+
                    "\n A. Texas"+
                    "\n B. California"+
                    "\n C. Mexico"+
                    "\n D. Florida"
                      ,'B'),
      new Question ("What continent is USA part of?"+
                    "\n A. Europe"+
                    "\n B. Asia"+
                    "\n C. Africa"+
                    "\n D. North America"
                      ,'D'),
      new Question ("What is the official language of USA?"+
                    "\n A. English"+
                    "\n B. Spanish"+
                    "\n C. French"+
                    "\n D. Chinese"
                      ,'A')
    };
    try
    {
      Country usa =new Country ("USA",usaEasy, null, null, ImageIO.read (new File ("USA.jpg")),
                                   null);
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
                     ,'C'),
      new Question ("In what part of Mexico is Newfoundland located?"+
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
      new Question ("What languages the two official languages of Canada?"+
                    "\n A. Spanish & French"+
                    "\n B. German & English"+
                    "\n C. English & Spanish"+
                    "\n D. French & English"
                      ,'D')
    };
    try
    {
      Country mexico =new Country ("Mexico",mexicoEasy, null, null, ImageIO.read (new File ("mexico.jpeg")),
                                   null);
      countries [3]=mexico;
    }
    catch (IOException e)
    {
      System.out.println ("IO");
    }
    revalidate();
  }
  
}
