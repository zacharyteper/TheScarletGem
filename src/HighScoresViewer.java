/**
 * Auto Generated Java Class.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
public class HighScoresViewer extends JFrame implements ActionListener
{
  private Graphics screen;
  private JButton easy = new JButton ("Easy");
  private JButton medium = new JButton ("Medium");
  private JButton hard = new JButton ("Hard");
  private JButton close = new JButton ("Close");
  private JButton clear = new JButton ("Clear");
  public static int[] easyScores = new int [11];
  public static int[] mediumScores = new int [11];
  public static int[] hardScores = new int [11];
  public static String[] easyNames = new String [11];
  public static String[] mediumNames = new String [11];
  public static String[] hardNames = new String [11];
  private JLabel difficulty = new JLabel ("High Scores --- Easy");
  private JLabel names;
  private JLabel scores;
  
  public void actionPerformed (ActionEvent ae)
  {
    if (ae.getActionCommand().equals ("Easy"))
    {
      switchToLevel (0);
    }
    else if (ae.getActionCommand().equals ("Medium"))
    {
      switchToLevel (1);
    }
    else if (ae.getActionCommand().equals ("Hard"))
    {
      switchToLevel (2);
    }
    else if (ae.getActionCommand().equals ("Clear"))
    {
      try
      {
        PrintWriter out = new PrintWriter (new FileWriter ("High Scores.txt"));
        out.println ("The Scarlet Gem");
      }
      catch (IOException i)
      {
        JOptionPane.showMessageDialog(null,"Unable to write to file.");
      }
    }
    else
    {
      System.exit (0);
    }
  }
  
  public static void sort ()
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
    catch (NullPointerException e)
    {
      fileExist = false;
    }
    
    if (fileExist == false)
    {
      try
      {
        PrintWriter out = new PrintWriter (new FileWriter ("High Scores.txt"));
        out.println ("The Scarlet Gem");
        out.close ();
      }
      catch (IOException e)
      {
        JOptionPane.showMessageDialog(null,"Unable to write to file.");
      }
    }
    
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
            if (easyNames [x] == null)
              break;
            easyScores [x] = Integer.parseInt (in.readLine ());
          }
        }
        if (in.readLine ().equals ("medium"))
        {
          for (int x = 0 ; x < 10 ; x++)
          {
            mediumNames [x] = in.readLine ();
            if (mediumNames [x] == null)
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
      
      PrintWriter out = new PrintWriter (new FileWriter ("High Scores.txt"));
      out.println ("The Scarlet Gem");
      out.println ("easy");
      int x;
      for (x = 0; x < 10 && easyScores[x] > easyScores[10]; x++)
      {
        out.println (easyNames[x]);
        out.println (easyScores[x]);
      }
      out.println (easyNames[10]);
      out.println (easyScores[10]);
      for (x = x; x < 10 && easyScores[x]!=0; x++)
      {
        out.println (easyNames[x]);
        out.println (easyScores[x]);
      }
      
      out.println ();
      out.println ("medium");
      for (x = 0; x < 10 && mediumScores[x] > mediumScores[10]; x++)
      {
        out.println (mediumNames[x]);
        out.println (mediumScores[x]);
      }
      out.println (mediumNames[10]);
      out.println (mediumScores[10]);
      for (x = x; x < 10 && mediumScores[x]!=0; x++)
      {
        out.println (mediumNames[x]);
        out.println (mediumScores[x]);
      }
      
      out.println ();
      out.println ("hard");
      for (x = 0; x < 10 && hardScores[x] > hardScores[10]; x++)
      {
        out.println (hardNames[x]);
        out.println (hardScores[x]);
      }
      out.println (hardNames[10]);
      out.println (hardScores[10]);
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
            if (easyNames [x] == null)
              break;
            easyScores [x] = Integer.parseInt (in.readLine ());
          }
        }
        if (in.readLine ().equals ("medium"))
        {
          for (int x = 0 ; x < 10 ; x++)
          {
            mediumNames [x] = in.readLine ();
            if (mediumNames [x] == null)
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
    }
    catch (NullPointerException e)
    {
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
  
  private void switchToLevel (int level)
  {
    remove (difficulty);
    remove (names);
    remove (scores);
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
      difficulty = new JLabel ("High Scores --- Easy");
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
      difficulty = new JLabel ("High Scores --- Medium");
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
      difficulty = new JLabel ("High Scores --- Hard");
    }
    name += "</html>";
    score += "</html>";
    names = new JLabel (name);
    scores = new JLabel (score);
    add (names);
    add (scores);
    add (difficulty);
    names.setBounds (100, 100, names.getPreferredSize().width, names.getPreferredSize().height);
    scores.setBounds (350, 100, scores.getPreferredSize().width, scores.getPreferredSize().height);
    difficulty.setBounds(180,60, difficulty.getPreferredSize().width + 30, difficulty.getPreferredSize().height);
  }
  
  public Graphics getScreen ()
  {
    return screen;
  }
  public void setScreen (Graphics g)
  {
    screen=g;
  }
  /* ADD YOUR CODE HERE */
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
    
    output ();
    
    add (easy);
    add (medium);
    add (hard);
    add (difficulty);
    add (close);
    add (clear);
    add (names);
    add (scores);
    easy.setBounds(50,20, easy.getPreferredSize().width, easy.getPreferredSize().height);
    medium.setBounds(200,20, medium.getPreferredSize().width, medium.getPreferredSize().height);
    hard.setBounds(350,20, hard.getPreferredSize().width, hard.getPreferredSize().height);
    close.setBounds(300,500, close.getPreferredSize().width, close.getPreferredSize().height);
    clear.setBounds(100,500, clear.getPreferredSize().width, clear.getPreferredSize().height);
    difficulty.setBounds(180,60, difficulty.getPreferredSize().width + 30, difficulty.getPreferredSize().height);
    names.setBounds (100, 100, names.getPreferredSize().width, names.getPreferredSize().height);
    scores.setBounds (350, 100, scores.getPreferredSize().width, scores.getPreferredSize().height);
  }
}
