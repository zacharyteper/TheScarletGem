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
  public static int[] easyScores = new int [10];
  public static int[] mediumScores = new int [10];
  public static int[] hardScores = new int [10];
  public static String[] easyNames = new String [10];
  public static String[] mediumNames = new String [10];
  public static String[] hardNames = new String [10];
  private JLabel difficulty = new JLabel ("High Scores --- Easy");
  private JLabel names;
  private JLabel scores;
  
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
    }
    else
      dispose();
  }
  
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
  
  //writes entry to file, puts it automatically in top 10 order.
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
              System.out.println ("break1");
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
              System.out.println ("break2");
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
              System.out.println ("break3");
              break;
            }
            hardScores [x] = Integer.parseInt (in.readLine ());
          }
        }
      }
      
      PrintWriter out = new PrintWriter (new FileWriter ("High Scores.txt"));
      out.println ("The Scarlet Gem");
      out.println ("easy");
      System.out.println ("easy");
      int x;
      for (x = 0; x < 10 && easyScores[x] > score + 210; x++)
      {
        out.println (easyNames[x]);
        out.println (easyScores[x]);
      }
      if (level == 0)
      {
        out.println (name);
        out.println (score + 180);
      }
      
      for (x = x; x < 10 && easyScores[x]!=0; x++)
      {
        out.println (easyNames[x]);
        out.println (easyScores[x]);
      }
      
      out.println ();
      out.println ("medium");
      for (x = 0; x < 10 && mediumScores[x] > score + 480; x++)
      {
        out.println (mediumNames[x]);
        out.println (mediumScores[x]);
      }
      if (level == 1)
      {
        out.println (name);
        out.println (score + 450);
      }
      for (x = x; x < 10 && mediumScores[x]!=0; x++)
      {
        out.println (mediumNames[x]);
        out.println (mediumScores[x]);
      }
      
      out.println ();
      out.println ("hard");
      for (x = 0; x < 10 && hardScores[x] > score + 660; x++)
      {
        out.println (hardNames[x]);
        out.println (hardScores[x]);
      }
      if (level == 2)
      {
        out.println (name);
        out.println (score + 630);
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
            System.out.println ("Loop"+x);
            easyNames [x] = in.readLine ();
            System.out.println (easyNames[x]);
            if (easyNames [x].equals(""))
            {
              System.out.println ("Break1");
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
    System.out.println (easyNames.length);
    for (String s:easyNames)
      System.out.println (s);
  }
  
  private void switchLevel (int level)
  {
    remove (difficulty);
    remove (names);
    remove (scores);
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
    revalidate();
    repaint ();
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
