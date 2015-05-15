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
  Country currentCountry;
  int difficulty;
  int levelsRemaining;
  int timeRemaining;
  boolean paused;
  ArrayList <Country> alreadyBeen;
  Country[] countries;
  JPanel gamePanel;
  MapPanel mapPanel;
  CountryPanel countryPanel;
  MainMenuPanel mainMenuPanel;
  HighScoresViewer highScoresViewer;
  InstructionsViewer instructionsViewer;
  GameTimer gameTimer;
  JMenuItem howToPlayItem =new JMenuItem ("How To Play Ctrl+R");
  JMenuItem printItem=new JMenuItem ("Print Ctrl+P");
  JMenuItem saveItem=new JMenuItem ("Save Ctrl+S");
  JMenuItem exitItem=new JMenuItem ("Exit Ctrl+Q");
  JMenuItem highScoresItem=new JMenuItem ("View High Scores Ctrl+E");
  
  JMenuItem helpItem=new JMenuItem ("Help Ctrl+H");
  JMenuItem aboutItem=new JMenuItem ("About Ctrl+A");
  
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
      //stuff goes here
    }
      
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
    
    fileMenu.add(howToPlayItem);
    fileMenu.add(saveItem);
    fileMenu.add(printItem);
    fileMenu.add(highScoresItem);
    fileMenu.add(exitItem);
    
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
  }
  
}
