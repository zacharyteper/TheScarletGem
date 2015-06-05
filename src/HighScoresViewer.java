
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.imageio.*;
import java.io.*;
/**
 * Displays the High Scores to the user. Includes option to clear the high scores.
 * 
 * @author Angela Zhu (4 hours + 0.5 hours graphics) and Zachary Teper (0.5 hours)
 * @version 1.0 09.06.2015
 */
public class HighScoresViewer extends JFrame implements ActionListener
{
  /**
   * Holds the button to display the Easy scores.
   */
  private JButton easy = new JButton ("Easy");
  /**Holds the button to display the Medium scores.
    */
  private JButton medium = new JButton ("Medium");
  /**Holds the button to display the Hard scores.
    */
  private JButton hard = new JButton ("Hard");
  /**Holds the button to close the window.
    */
  private JButton close = new JButton ("Close");
  /**Holds the button to clear the High Scores.
    */
  private JButton clear = new JButton ("Clear");
  /**Holds the background of the High Scores Viewer.
    */
  private JLabel background=new JLabel();
  /**Holds the set of Easy scores.
    */
  public static int[] easyScores = new int [10];
  /**Holds the set of Medium scores.
    */
  public static int[] mediumScores = new int [10];
  /**Holds the set of Hard scores.
    */
  public static int[] hardScores = new int [10];
  /**Holds the set of Easy names.
    */
  public static String[] easyNames = new String [10];
  /**Holds the set of Medium names.
    */
  public static String[] mediumNames = new String [10];
  /**Holds the set of Hard names.
    */
  public static String[] hardNames = new String [10];
  /**Displays the difficulty currently being viewed.
    */
  private JLabel difficulty = new JLabel ("High Scores --- Easy");
  /**Displays the list of names for the current difficulty.
    */
  private JLabel names;
  /**Displays the list of scores for the current difficulty.
   */
  private JLabel scores;
  /**
   * Responds when a button is pressed.
   * 
   * If statements determine which action was triggered.
   * 
   * For loop resets all elements of arrays.
   * 
   * try block writes an empty file.
   * 
   * <b> Local Variables </b>
   * <p>
   * <b>out </b> PrintWriter - used to write to the high scores file.
   * 
   * @param ae ActionEvent the action that was triggered.
   */
  public void actionPerformed (ActionEvent ae)
  {
    if (ae.getActionCommand().equals ("Easy"))
    {
      switchLevel (0);
    }
    else if (ae.getActionCommand().equals ("Medium"))
    {
      switchLevel (1);
    }
    else if (ae.getActionCommand().equals ("Hard"))
    {
      switchLevel (2);
    }
    else if (ae.getActionCommand().equals ("Clear"))
    { 
      try
      {
        PrintWriter out = new PrintWriter (new FileWriter ("High Scores.txt"));
        out.println ("The Scarlet Gem");
        out.println ("easy");
        out.println ();
        out.println ("medium");
        out.println ();
        out.println ("hard");
        out.close ();
      }
      catch (IOException i)
      {
        JOptionPane.showMessageDialog(null,"Unable to write to file.");
      }
      for (int x = 0; x < 10; x++)
      {
        easyScores [x] = 0;
        easyNames [x] = "";
        mediumScores [x] = 0;
        mediumNames [x] = "";
        hardScores [x] = 0;
        hardNames [x] = "";
      }
      switchLevel (0);
    }
    else
      dispose();
  }
  /**
   * Reads in the high scores file to the arrays.
   * 
   * If statement checks if first line is valid.
   * If statement checks if a valid file exists.
   * 
   * For loop initializes arrays.
   * 
   * Try block checks for a valid file.
   * Try block writes a new empty file.
   * 
   * <b> Local variables </b>
   * <p>
   * <b> fileExist </b> boolean - checks if a valid file exists.
   * <p>
   * <b> in </b> BufferedReader - checks if the first line of an 
   * existing file is valid.
   * <p>
   * <b> out </b> PrintWriter - writes a new empty file.
   */
  public static void fileCheck ()
  {
    boolean fileExist = false;
    
    try
    {
      BufferedReader in = new BufferedReader (new FileReader ("High Scores.txt"));
      if (in.readLine ().equals ("The Scarlet Gem"))
        fileExist = true;
    }
    catch (IOException e)
    {
      JOptionPane.showMessageDialog(null,"File unreadable.");
    }
    
    if (fileExist == false)
    {
      try
      {
        PrintWriter out = new PrintWriter (new FileWriter ("High Scores.txt"));
        out.println ("The Scarlet Gem");
        out.println ("easy");
        out.println ();
        out.println ("medium");
        out.println ();
        out.println ("hard");
        out.close ();
      }
      catch (IOException e)
      {
        JOptionPane.showMessageDialog(null,"Unable to write to file.");
      }
      for (int x = 0; x < 10; x++)
      {
        easyScores [x] = 0;
        easyNames [x] = "";
        mediumScores [x] = 0;
        mediumNames [x] = "";
        hardScores [x] = 0;
        hardNames [x] = "";
      }
    }
  }
  
  /**writes entry to file, puts it automatically in top 10 order.
    * 
    * If statement checks if the first line of file is valid.
    * For loop and if reads easy names and scores until a blank line is reached.
    * For loop and if read medium names and scores until a blank line is reached.
    * For loop and if read hard names and scores until a blank line is reached.
    * If statement checks if level is Easy
    * If statement checks if level is Medium
    * If statement checks if level is Hard
    * 
    * For loop rewrites Easy entries.
    * For lopp writes Easy entries lower than entry.
    * For loop rewrites Medium entries.
    * For loop rewrites Medium entries lower than entry.
    * For loop rewrites Hard entries.
    * For loop rewrites Hard entries lower than entry.
    * For loop writes String text for names array.
    * 
    * Try block writes high scores file.
    * Try block reads in Strings as integers.
    *
    * <b> Local Variables </b>
    * <p>
    * <b> in </b> BufferedReader - reads the file.
    * <p>
    * <b> out </b> PrintWriter - writes to the file.
    * 
    * @param score int the score that the user acheived.
    * @param name String the username that the user entered.
    * @param level int the difficulty that the user completed.
    */
  public static void sort (int score, String name, int level)
  {
    fileCheck ();
    try
    {
      BufferedReader in = new BufferedReader (new FileReader ("High Scores.txt"));
      if (in.readLine ().equals ("The Scarlet Gem"))
      {
        if (in.readLine ().equals ("easy"))
        {
          for (int x = 0 ; x < 10 ; x++)
          {
            easyNames [x] = in.readLine ();
            if (easyNames [x].equals (""))
            {
              break;
            }
            easyScores [x] = Integer.parseInt (in.readLine ());
          }
        }
        if (in.readLine ().equals ("medium"))
        {
          for (int x = 0 ; x < 10 ; x++)
          {
            mediumNames [x] = in.readLine ();
            if (mediumNames [x].equals (""))
            {
              break;
            }
            mediumScores [x] = Integer.parseInt (in.readLine ());
          }
        }
        if (in.readLine ().equals ("hard"))
        {
          for (int x = 0 ; x < 10 ; x++)
          {
            hardNames [x] = in.readLine ();
            if (hardNames [x] == null)
            {
              break;
            }
            hardScores [x] = Integer.parseInt (in.readLine ());
          }
        }
      }
      
      PrintWriter out = new PrintWriter (new FileWriter ("High Scores.txt"));
      out.println ("The Scarlet Gem");
      out.println ("easy");
      int x;
      for (x = 0; x < 10 && easyScores[x] > score; x++)
      {
        out.println (easyNames[x]);
        out.println (easyScores[x]);
      }
      if (level == 0)
      {
        out.println (name);
        out.println (score);
      }
      
      for (x = x; x < 10 && easyScores[x]!=0; x++)
      {
        out.println (easyNames[x]);
        out.println (easyScores[x]);
      }
      
      out.println ();
      out.println ("medium");
      for (x = 0; x < 10 && mediumScores[x] > score; x++)
      {
        out.println (mediumNames[x]);
        out.println (mediumScores[x]);
      }
      if (level == 1)
      {
        out.println (name);
        out.println (score);
      }
      for (x = x; x < 10 && mediumScores[x]!=0; x++)
      {
        out.println (mediumNames[x]);
        out.println (mediumScores[x]);
      }
      
      out.println ();
      out.println ("hard");
      for (x = 0; x < 10 && hardScores[x] > score; x++)
      {
        out.println (hardNames[x]);
        out.println (hardScores[x]);
      }
      if (level == 2)
      {
        out.println (name);
        out.println (score);
      }
      for (x = x; x < 10 && hardScores[x]!=0; x++)
      {
        out.println (hardNames[x]);
        out.println (hardScores[x]);
      }
      
      out.close ();
    }
    catch (IOException e)
    {
      JOptionPane.showMessageDialog(null,"Unable to write to file.");
    }
    catch (NumberFormatException e)
    {
    }
  }
  
  /**
   * Displays the names and scores to the user.
   * 
   * If statement checks if first line is valid.
   * If statement checks if the next line is Easy.
   * For loop and if statement read file until a blank line is reached.
   * If statement checks if the next line is Medium.
   * For loop and if statement read file until a blank line is reached.
   * If statement checks if the next line is Hard.
   * For loop and if statement read file until a blank line is reached.
   * 
   * For loop writes the String text of the names and scores.
   * 
   * Try block reads file.
   * Try block converts String to int.
   * 
   * <b> Local variables </b>
   * <p>
   * <b>
   */
  private void output ()
  {    
    try
    {
      BufferedReader in = new BufferedReader (new FileReader ("High Scores.txt"));
      if (in.readLine ().equals ("The Scarlet Gem"))
      {
        if (in.readLine ().equals ("easy"))
        {
          for (int x = 0 ; x < 10 ; x++)
          {
            easyNames [x] = in.readLine ();
            if (easyNames [x].equals(""))
            {
              break;
            }
            easyScores [x] = Integer.parseInt (in.readLine ());
          }
        }
        if (in.readLine ().equals ("medium"))
        {
          for (int x = 0 ; x < 10 ; x++)
          {
            mediumNames [x] = in.readLine ();
            if (mediumNames [x].equals(""))
              break;
            mediumScores [x] = Integer.parseInt (in.readLine ());
          }
        }
        if (in.readLine ().equals ("hard"))
        {
          for (int x = 0 ; x < 10 ; x++)
          {
            hardNames [x] = in.readLine ();
            if (hardNames [x] == null)
              break;
            hardScores [x] = Integer.parseInt (in.readLine ());
          }
        }
      }
    }
    catch (IOException i)
    {
      JOptionPane.showMessageDialog(null,"File unreadable.");
    }
    catch (NumberFormatException n)
    {
      n.printStackTrace();
    }
    String name = "<html><b>User Name</b><br>";
    String score = "<html><b>Scores</b><br>";
    for (int x = 0; x < 10; x++)
    {
      if (easyScores[x]==0)
        break;
      name += "<br>"+ easyNames[x] + "<br>";
      score += "<br>" + easyScores[x] + "<br>";
    }
    name += "</html>";
    score += "</html>";
    names = new JLabel (name);
    scores = new JLabel (score);
  }
  
  private void switchLevel (int level)
  {
    difficulty.setVisible(false);
    names.setVisible(false);
    scores.setVisible(false);
    revalidate ();
    repaint ();
    String name = "<html><b>User Name</b><br>";
    String score = "<html><b>Scores</b><br>";
    if (level == 0)
    {
      for (int x = 0; x < 10; x++)
      {
        if (easyScores[x]==0)
          break;
        name += "<br>"+ easyNames[x] + "<br>";
        score += "<br>" + easyScores[x] + "<br>";
      }
      difficulty.setText("High Scores --- Easy");
    }
    else if (level == 1)
    {
      for (int x = 0; x < 10; x++)
      {
        if (mediumScores[x]==0)
          break;
        name += "<br>"+ mediumNames[x] + "<br>";
        score += "<br>" + mediumScores[x] + "<br>";
      }
      difficulty.setText("High Scores --- Medium");
    }
    else
    {
      for (int x = 0; x < 10; x++)
      {
        if (hardScores[x]==0)
          break;
        name += "<br>"+ hardNames[x] + "<br>";
        score += "<br>" + hardScores[x] + "<br>";
      }
      difficulty.setText("High Scores --- Hard");
    }
    name += "</html>";
    score += "</html>";
    names.setText(name);
    scores.setText(score);

    names.setVisible(true);
    scores.setVisible(true);
    difficulty.setVisible(true);
    difficulty.setBounds(190,130, difficulty.getPreferredSize().width + 30, difficulty.getPreferredSize().height);
    names.setBounds (120, 160, names.getPreferredSize().width, names.getPreferredSize().height);
    scores.setBounds (320, 160, scores.getPreferredSize().width, scores.getPreferredSize().height);
    revalidate();
    repaint ();
  }
  
  public HighScoresViewer ()
  {
    super ("High Scores");
    setVisible(true);
    setSize(500,600);
    setResizable (false);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    easy.addActionListener (this);
    medium.addActionListener (this);
    hard.addActionListener (this);
    close.addActionListener (this);
    clear.addActionListener (this);
    setLayout (null);
    
    fileCheck ();
    output ();
    
    add (easy);
    add (medium);
    add (hard);
    add (difficulty);
    add (close);
    add (clear);
    add (names);
    add (scores);
    add(background);
    try
    {
      background.setIcon(new ImageIcon(ImageIO.read(new File("pics/background2.png"))));
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    background.setBounds(0,0,500,600);
    easy.setBounds(50,20, easy.getPreferredSize().width, easy.getPreferredSize().height);
    medium.setBounds(210,20, medium.getPreferredSize().width, medium.getPreferredSize().height);
    hard.setBounds(380,20, hard.getPreferredSize().width, hard.getPreferredSize().height);
    close.setBounds(300,520, close.getPreferredSize().width, close.getPreferredSize().height);
    clear.setBounds(120,520, clear.getPreferredSize().width, clear.getPreferredSize().height);
    difficulty.setBounds(190,130, difficulty.getPreferredSize().width + 30, difficulty.getPreferredSize().height);
    names.setBounds (120, 160, names.getPreferredSize().width, names.getPreferredSize().height);
    scores.setBounds (320, 160, scores.getPreferredSize().width, scores.getPreferredSize().height);
  }
}
