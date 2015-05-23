/**
 * Auto Generated Java Class.
 */
import java.awt.*;
public class Country {
  Question[] easy;
  Question[] medium;
  Question[] hard;
  final String name;
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
  public int getIndex()
  {
    if (name.equals("Canada"))
      return 0;
    else if (name.equals("China"))
      return 1;
    else if (name.equals("USA"))
      return 2;
    else if (name.equals("Mexico"))
      return 3;
    else if (name.equals("Portugal"))
      return 4;
    else if (name.equals("Australia"))
      return 5;
    else if (name.equals("Egypt"))
      return 6;
    else if (name.equals("India"))
      return 7;
    else if (name.equals("Russia"))
      return 8;
    else if (name.equals("Japan"))
      return 9;
    else if (name.equals("France"))
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
    return name;
  }
  public boolean equals (Country other)
  {
    return other.getName().equals(name);
  }
  public Country (String s)
  {
    name=s;
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
