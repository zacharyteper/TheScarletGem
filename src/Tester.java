import java.awt.*;
import javax.swing.*;
import java.awt.print.*;
import javax.imageio.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

public class Tester extends JFrame
{
  public JLabel label;
  public Tester ()
  {
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setSize(200, 200);
    label = new JLabel("5:00", JLabel.CENTER);
    setVisible(true);
  }
  
  public static void main (String[] args)
  {
    Tester t = new Tester ();
    //GameTimer timer = new GameTimer (300, t.label, );
    //timer.start();
  }
}