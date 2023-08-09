package apliccation;
import xadrez.Cor;
import xadrez.PeçaXadrez;
public class UI {
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
	public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
	public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
	public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
	public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
	public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
	public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
	public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";
	
  public static void printTabuleiro(PeçaXadrez[][] peças){	 
	  for(int i=0; i<peças.length; i++) {
		  System.out.print((8 - i) + " ");
		  for(int j = 0; j<peças.length; j++) {
			  printPeça(peças[i][j]);
		  }
		  System.out.println();
	  }
	  System.out.println("  a b c d e f g h");
  }
  private static void printPeça(PeçaXadrez peça) {
  	if (peça == null) {
          System.out.print("-");
      }
      else {
          if (peça.getCor() == Cor.Branco) {
              System.out.print(ANSI_WHITE + peça + ANSI_RESET);
          }
          else {
              System.out.print(ANSI_YELLOW + peça + ANSI_RESET);
          }
      }
      System.out.print(" ");
	}
}