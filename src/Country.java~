/**
 * Auto Generated Java Class.
 */
import java.awt.*;
public class Country {
  Question[] easy;
  Question[] medium;
  Question[] hard;
  String name;
  String[]clues;
  Image background;
  
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
    return name;
  }
  
  public Country (String name, Question[]a,Question[]b,
                  Question[]c,Image back, String[]clues)
  {
    this.name=name;
    easy=a;
    medium=b;
    hard=c;
    background=back;
    this.clues=clues;
  }
  /* ADD YOUR CODE HERE */
  
}
