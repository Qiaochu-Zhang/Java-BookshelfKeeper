import java.util.ArrayList;
import java.util.Scanner;

/**
 * BookshelfKeeperProg contains the main method that allows the user
 * to perform a series of pickPos and putHeight operations on a bookshelf
 * in an interactive mode with user commands called pick and put.
 * It can also be run in a batch mode by using input and output redirection.
 */
public class BookshelfKeeperProg {
   public static void main(String[] args){
      System.out.println("Please enter initial arrangement of books followed by newline:");
      Scanner in = new Scanner(System.in);
      //Initialization of bookShelfKeeper
      String inputInitial = in.nextLine();
      BookshelfKeeper bookshelfKeeper;
      if(inputInitial.isEmpty()){
         bookshelfKeeper = new BookshelfKeeper();
         System.out.println(bookshelfKeeper);
      }
      else {
         String trimmedString = inputInitial.replaceAll("\\s+", " ").trim(); //trim the spaces
         String[] integerStrings = trimmedString.split(" ");
         ArrayList<Integer> pilesOfBooks = new ArrayList<>();
         for (int i = 0; i < integerStrings.length; i++) {
            pilesOfBooks.add(Integer.parseInt(integerStrings[i]));
         }
         for(int i=0;i < pilesOfBooks.size();i++) {
            int height = pilesOfBooks.get(i);
            if (height <= 0) {
               System.out.println("ERROR: Height of a book must be positive.");
               System.out.println("Exiting Program.");
               return;
            }
         }
         Bookshelf bookShelf = new Bookshelf(pilesOfBooks);
         if(!bookShelf.isSorted()){
            System.out.println("ERROR: Heights must be specified in non-decreasing order.");
            System.out.println("Exiting Program.");
            return;
         }
         bookshelfKeeper = new BookshelfKeeper(bookShelf);
         System.out.println(bookshelfKeeper);
      }
      System.out.println("Type pick <index> or put <height> followed by newline. Type end to exit.");
      // manipulations of bookShelfKeeper
      while(true) {
         String[] input2 = in.nextLine().replaceAll("\\s+", " ").trim().split(" ");
         if (input2.length == 1 && input2[0].equals("end")) {
            break;
         } else if (input2.length != 2) {
            System.out.println("Error: Invalid style. Valid style should be a word and a number or end.");
            break;
         } else {
            String command = input2[0];
            String numberString = input2[1];
            if (!command.equals("put") && !command.equals( "pick")) {
               System.out.println("ERROR: Invalid command. Valid commands are pick, put, or end.");
               break;
            }
            else {
               try {
                  int intInput = Integer.parseInt(numberString);
                  if (command.equals("put")) {
                     if (intInput <= 0) {
                        System.out.println("ERROR: Height of a book must be positive.");
                        break;
                     } else {
                        bookshelfKeeper.putHeight(intInput);
                        System.out.println(bookshelfKeeper);
                     }
                  }
                  else {
                     if(intInput<0 || intInput >= bookshelfKeeper.getNumBooks()){
                        System.out.println("ERROR: Entered pick operation is invalid on this shelf.");
                        break;
                     }
                     else {
                        bookshelfKeeper.pickPos(intInput);
                        System.out.println(bookshelfKeeper);
                     }
                  }
               } catch (NumberFormatException e) {
                  System.out.println("ERROR: Invalid input number. The second input is not a valid integer.");
                  System.out.println("Exiting Program.");
               }
            }
         }
      }
      System.out.println("Exiting Program.");
   }
}
