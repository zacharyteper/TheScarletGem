/**
 * Auto Generated Java Class.
 */
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
public class InstructionsViewer extends JFrame
{
  private JTabbedPane tabs=new JTabbedPane();
  private JPanel intro=new JPanel();
  private JPanel country=new JPanel();
  private JPanel map=new JPanel();
  private JPanel saving=new JPanel();
  private JPanel scores=new JPanel();
  private JPanel keys=new JPanel();
  private JPanel contact=new JPanel();
  
  /* ADD YOUR CODE HERE */
  public InstructionsViewer ()
  {
    super ("Instructions");
    setVisible (true);
    setSize (800,500);
    setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
    
    setLayout (new CardLayout());
    
    tabs.add("Introduction",intro);
    tabs.add("Countries",country);
    tabs.add("Travelling",map);
    tabs.add("Saving",saving);
    tabs.add("High Scores",scores);
    tabs.add("Key Shortcuts",keys);
    tabs.add("Contact Info",contact);
    add(tabs);
    
    intro.setLayout(null);
    country.setLayout(null);
    map.setLayout(null);
    saving.setLayout(null);
    score.setLayout(null);
    keys.setLayout(null);
    contact.setLayout(null);
    
    JLabel introLabel=new JLabel
      ("<html>Welcome to The Scarlet Gem! <br>"+
       "In this game, you are a treasure hunter, on a quest "+
       "to discover the secret location of the mysterious Scarlet Gem!<br>"+
       "To play, you must travel around the world, finding clues which "+
       "will lead you to the Gem's location. <br>"+
       "To begin the game, select Easy, Medium or Hard from the Main Menu.<br>"+
       "To load a previously saved game, select Load Game form the Main Menu.<br>"+
       "How the Game Works: <ul><li>To win, you must find the Scarlet Gem before "+
       "time runs out, by travelling to the required number of countries. "+
       "<li>For Easy mode, you must travel to 3 countries. "+
       "<li>For Medium mode, you must travel to 6 countries. "+
       "<li>For Hard mode, you must travel to 9 countries! "+
       "<li>This game is based on speed, so, the faster you go, the higher your score will be. "+
       "<li>When the timer runs out, you lose! "+
       "<li>You can pause the game at any time by pressing the \"pause\" button "+
       "(at the top of the screen), or by pressing 'p'. "+
       "<li>Unpause the game by pressing 'p' again, or by pressing the \"unpause\" button "+
       "<li>The questions get harder with the Difficulty "+
       "(e.g. Easy mode will have easy questions, "+
       "Medium mode will have somewhat more challenging questions, "+
       "Hard mode will have difficult questions) </ul>"+
       "<br>Other Information: "+
       "<ul><li>Age group: 9-15 years "+
       "<li>Theme: World Geography, Educational "+
       "<li>System Requirements: Java Runtime Environment 8.0 or higher. </ul></html>"
         );
    introLabel.setBounds(0,0,700,400);
    intro.add(introLabel);
    
  }
  
}
