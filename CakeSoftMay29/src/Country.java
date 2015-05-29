/**
 * Auto Generated Java Class.
 */
import java.awt.*;
public class Country {
  private Question[] easy;
  private Question[] medium;
  private Question[] hard;
  private final String NAME;
  private String[]clues;
  private Image background;
  
  public Question getRandQuestion (int difficulty)
  {
    int index= (int) (Math.random()*4);
    if (difficulty==0)
      return easy[index];
    else
    {
      if (difficulty==1)
        return medium[index];
    }
    return hard[index];
  }
  public int getIndex()
  {
    if (NAME.equals("Canada"))
      return 0;
    else if (NAME.equals("China"))
      return 1;
    else if (NAME.equals("USA"))
      return 2;
    else if (NAME.equals("Mexico"))
      return 3;
    else if (NAME.equals("Portugal"))
      return 4;
    else if (NAME.equals("Australia"))
      return 5;
    else if (NAME.equals("Egypt"))
      return 6;
    else if (NAME.equals("India"))
      return 7;
    else if (NAME.equals("Russia"))
      return 8;
    else if (NAME.equals("Japan"))
      return 9;
    else if (NAME.equals("France"))
      return 10;
    //england
    return 11;
  }
  public Image getBackground ()
  {
    return background;
  }
  public String getRandClue()
  {
    return clues [(int)(Math.random()*4)];
  }
  public String getName ()
  {
    return NAME;
  }
  public boolean equals (Country other)
  {
    return other.getName().equals(NAME);
  }
  public Country (String s)
  {
    NAME=s;
  }
  
  public Country (String NAME, Question[]a,Question[]b,
                  Question[]c,Image back, String[]clues)
  {
    this.NAME=NAME;
    easy=a;
    medium=b;
    hard=c;
    background=back;
    this.clues=clues;
  }
  /* ADD YOUR CODE HERE */
  
}
