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
      remove (mainMenuPanel);
      countryPanel=new CountryPanel (countries[0],null);
      add (countryPanel);
    }
    
  }
  private void askQuestion()
  {
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
    revalidate();
  }
  
}
