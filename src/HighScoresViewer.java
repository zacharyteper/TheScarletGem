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
  private int[] easyScores = new int [11];
  private int[] mediumScores = new int [11];
  private int[] hardScores = new int [11];
  private String[] easyNames = new String [11];
  private String[] mediumNames = new String [11];
  private String[] hardNames = new String [11];
  private JLabel difficulty = new JLabel ("High Scores --- Easy");
  private JLabel names;
  private JLabel scores;
  
  public JButton getEasy ()
  {
    return easy;
  }
  public JButton getMedium ()
  {
    return medium;
  }
  public JButton getHard ()
  {
    return hard;
  }
  
  public int[] getEasyScores ()
  {
    return easyScores;
  }
  public int[] getMediumScores ()
  {
    return mediumScores;
  }
  public int[] getHardScores ()
  {
    return hardScores;
  }
  
  public String[] getEasyNames ()
  {
    return easyNames;
  }
  public String[] getMediumNames ()
  {
    return mediumNames;
  }
  public String[] getHardNames ()
  {
    return hardNames;
  }
  
  public void addEasy (int score, String name)
  {
    easyScores [11] = score;
    easyNames [11] = name;
  }
  public void addMedium (int score, String name)
  {
    mediumScores [11] = score;
    mediumNames [11] = name;
  }
  public void addHard (int score, String name)
  {
    hardScores [11] = score;
    hardNames [11] = name;
  }
  
  public void actionPerformed (ActionEvent ae)
  {
    if (ae.getActionCommand().equals ("easy"))
    {
    }
  }
  
  public void sort ()
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
  
  public void output ()
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
    
  }
  
  public void switchToLevel (int difficulty)
  {
    if (difficulty == 0)
    {
    }
    else if (difficulty == 1)
    {
    }
    else
    {
    }
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
    setSize(400,400);
    setResizable (false);
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    easy.addActionListener (this);
    medium.addActionListener (this);
    hard.addActionListener (this);
    close.addActionListener (this);
    clear.addActionListener (this);
    setLayout (null);
    
    add (easy);
    add (medium);
    add (hard);
    add (difficulty);
    add (close);
    add (clear);
    easy.setBounds(100,50, easy.getPreferredSize().height, easy.getPreferredSize().width);
  }
}
