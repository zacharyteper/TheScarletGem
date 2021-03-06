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
  private JPanel countries=new JPanel();
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
    tabs.add("Countries",countries);
    tabs.add("Travelling",map);
    tabs.add("Saving",saving);
    tabs.add("High Scores",scores);
    tabs.add("Key Shortcuts",keys);
    tabs.add("Contact Info",contact);
    add(tabs);
    
    intro.setLayout(null);
    countries.setLayout(null);
    map.setLayout(null);
    saving.setLayout(null);
    scores.setLayout(null);
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
    
    JLabel countriesLabel=new JLabel
      ("<html>At each country, you must answer two multiple-choice questions."+
       "This is known as the Country Stage. "+
       "<br>When you start the game, you will find yourself in Canada. "+
       "On the right side of the screen, there will be a multiple-choice question, "+
       "with four possible answers to the question, labeled A, B, C and D. "+
       "Answer the question to the best of your ability by clicking one of the four buttons."+
       "If you get a question wrong, the choice will be disabled, "+
       "and ten points will be deducted from your score!"+
       "<br>If you get the question right, another question will be displayed. "+
       "Answer the same way you did the first question. "+
       "<br>After you have answered both questions correctly, "+
       "you will move onto the Map stage (see next page)."+
       "<br>On the left side, there will be a picture of a person who "+
       "is dressed in the style of the country you are in. "+
       "He/she will give you a clue about the location of the Scarlet Gem, "+
       "once you answer the questions correctly."+
       "<br>After you have completed all of the levels, "+
       "you will find the Scarlet Gem in the next Country that you travel to.</html>"
      );
    countriesLabel.setBounds(0,0,700,400);
    countries.add(countriesLabel);
    
    JLabel mapLabel=new JLabel
      ("<html>After you answer both questions correctly, a map will appear. "+
       "On top of the map, there will be a clue, which will decide where the "+
       "next part of the game will be located. Below the map, "+
       "there will be three buttons displaying the names of various countries. "+
       "These countries are your Destinations. The Clue displayed above will give you "+
       "a hint about which Destination you should go to. "+
       "Click on the Destination that most closely matches the Clue given. "+
       "Be careful - if you choose the wrong Destination, ten points will be deducted from your score!"+
       "<br>Once you have chosen the correct Destination from the choices given, "+
       "you will asked two questions, just like in the previous screen. "+
       "The game continues in this fashion until the level counter "+
       "(found at the top of the screen) reaches 0.</html>"
      );
    mapLabel.setBounds(0,0,700,400);
    map.add(mapLabel);
    
    JLabel savingLabel=new JLabel
      ("<html>This game allows you to save your progress when you are at the Country stage. "+
       "(Sorry, you cannot save while travelling!) "+
       "You can save by selecting the \"save\" item from the menu, or by pressing Ctrl+S. "+
       "When you are in the main menu, press Load Game to resume from where you last saved."+
       "<br>Note: you can only have one save file. "+
       "That means that if you save twice, your first save will be deleted."+
       "<br>The file that you save also contains the time remaining in the game. "+
       "That means that when you load your saved game, "+
       "you will start with the amount of time left when you saved.</html>"
      );
    savingLabel.setBounds(0,0,700,400);
    saving.add(savingLabel);
    
    JLabel scoresLabel=new JLabel
      ("<html>When you win the game, your score will be displayed to you, "+
       "and you will be asked to enter your user name. "+
       "<br>The game automatically records the scores and names of "+
       "the top 10 players in each category: Easy, Medium and Hard. "+
       "You can view these scores at any time by pressing Ctrl+E or by "+
       "selecting the \"view high scores\" item from the menu. "+
       "You can print these scores at any time by pressing"+
       "Ctrl+P or by selecting the \"print\" menu item."+
       "<br><br>This formula is used to compute your score: score = 200x +s -10y"+
       "<br>,where x is the number of countries visited, "+
       "s is the number of seconds remaining at the end of the game, "+
       "and y is the number of incorrect answers.</html>"
      );
    scoresLabel.setBounds(0,0,700,400);
    scores.add(scoresLabel);
    
    JLabel keysLabel=new JLabel 
      ("<html>Here is a complete list of keyboard shortcuts within the game:"+
       "<br><br><ul><li>p - pause/unpause "+
       "<li>Ctrl+P - print "+
       "<li>Ctrl+E - view high scores "+
       "<li>Ctrl+S - save "+
       "<li>Ctrl+A - view About dialog "+
       "<li>Ctrl+H - view Help dialog "+
       "<li>Ctrl+Q - quit/exit "+
       "<li>Ctrl+R - how to play/view instructions</ul></html>"
      );
    keysLabel.setBounds(0,0,700,400);
    keys.add(keysLabel);
    
    JLabel contactLabel=new JLabel
      ("<html>Contact Info:"+
       "<br><br>Game: The Scarlet Gem"+
       "<br><br>Company: CakeSoft Inc."+
       "<br><br>Project Lead: Zachary Teper"+
       "<br><br>Project Representative: Angela Zhu"+
       "<br><br>Email: zacharyblacktail@gmail.com"+
       "<br><br>Phone: 416-223-6075"+
       "<br><br>Version: 1.0 06.09.2015</html>"
    );
    contactLabel.setBounds(0,0,700,400);
    contact.add(contactLabel);
  }
  
}
