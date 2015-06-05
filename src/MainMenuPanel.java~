/**
 * Contains the main menu buttons that appear at the start of the game.
 * 
 * @author Zachary Teper and Angela Zhu
 * @version 1.0 05.24.2015
 */
import javax.swing.*;
import java.awt.*;
import javax.imageio.*;
import java.io.*;
public class MainMenuPanel extends JPanel
{
  /**
   * Starts a game in "easy mode".
   */
  private JButton easy;
  /**
   * Starts a game in "medium mode".
   */
  private JButton medium;
  /**
   * Starts a game in "hard mode".
   */
  private JButton hard;
  /**
   * Loads the game stored in the progress file if possible.
   */
  private JButton loadGame;
  
  private JLabel title=new JLabel();
  
  private JLabel background=new JLabel();
  /**
   * Returns the button to start the game in easy mode.
   * 
   * @return easy JButton the button to start easy mode.
   */
  public JButton getEasyButton()
  {
    return easy;
  }
  /**
   * Returns the button to start the game in medium mode.
   * 
   * @return medium JButton the button to start medium mode.
   */
  public JButton getMediumButton()
  {
    return medium;
  }
  /**
   * Returns the button to start the game in hard mode.
   * 
   * @return hard JButton the button to start hard mode.
   */
  public JButton getHardButton()
  {
    return hard;
  }
  /**
   * Return the button to load a saved game.
   * 
   * @return load JButton the button to load a game.
   */
  public JButton getLoadButton()
  {
    return loadGame;
  }
  /**
   * Initializes the buttons and the graphics.
   */
  public MainMenuPanel ()
  {
    setLayout(null);
    try
    {
      background.setIcon(new ImageIcon(ImageIO.read(new File ("pics/scarlet-gem2.png"))));
      title.setIcon(new ImageIcon(ImageIO.read(new File("pics/title2.png"))));
    }
    catch (IOException e)
    {
    }
    background.setBounds(0,0,700,600);
    easy=new JButton ("Easy");
    medium=new JButton ("Medium");
    hard=new JButton ("Hard");
    loadGame=new JButton ("Load Saved Game");
    easy.setBounds(275,150,150,30);
    medium.setBounds(275,250,150,30);
    hard.setBounds(275,350,150,30);
    loadGame.setBounds(275,450,150,30);
    title.setBounds(50,20,600,100);
    add(title);
    add(easy);
    add(medium);
    add(hard);
    add(loadGame);
    add(background);

    
    
    revalidate();
    
  }
  /* ADD YOUR CODE HERE */
  
}
