/**
 * This class will execute the ScarletGemMain class. The execution of the 
 * main method will call the default constructor of the ScarletGemMain class.
 * This class should not contain any code regarding the logic and flow of the game.
 * The purpose of this class is to run, test and debug the other components of the game.
 * 
 * @author Angela Zhu and Zachary Teper
 * @version 1.0 18.05.15
 */
public class ScarletGemRunner {
  /**
   * This method will call the default constructor fo the ScarletGemMain class.
   * This method should be called to actually 'run' the game.
   * 
   * @param args String[] The command-line arguments (not used)
   */
  public static void main (String[]args)
  {
    new ScarletGemMain();
  }
  
}
