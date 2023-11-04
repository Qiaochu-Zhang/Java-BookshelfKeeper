import java.util.ArrayList;

public class BookshelfTester {
    public static void main(String[] args) {
        Bookshelf bookshelf1 = new Bookshelf();
        ArrayList<Integer> arrBook = new ArrayList<>();
        arrBook.add(1);
        arrBook.add(4);
        arrBook.add(8);
        arrBook.add(5);
        Bookshelf bookshelf2 = new Bookshelf(arrBook);
        ArrayList<Integer> arrBookSorted = new ArrayList<>();
        arrBookSorted.add(1);
        arrBookSorted.add(3);
        arrBookSorted.add(5);
        arrBookSorted.add(7);
        Bookshelf bookshelfSorted = new Bookshelf(arrBookSorted);
        System.out.println("Below are tests for two constructors and toString method");
        System.out.print("Expected result: [], actual result: ");
        System.out.println(bookshelf1);
        System.out.print("Expected result: [1, 4, 8, 5], actual result: ");
        System.out.println(bookshelf2);
        System.out.println("Below are tests for add & remove methods of Bookshelf");
        bookshelf2.addFront(10);
        System.out.print("Expected result: [10, 1, 4, 8, 5], actual result: ");
        System.out.println(bookshelf2);
        bookshelf2.removeLast();
        System.out.print("Expected result: [10, 1, 4, 8], actual result: ");
        System.out.println(bookshelf2);
        bookshelf2.removeFront();
        System.out.print("Expected result: [1, 4, 8], actual result: ");
        System.out.println(bookshelf2);
        bookshelf2.addLast(7);
        System.out.print("Expected result: [1, 4, 8, 7], actual result: ");
        System.out.println(bookshelf2);
        System.out.println("Below is a test for getHeight method.");
        int height = bookshelf2.getHeight(2);
        System.out.print("Expected result: 8, actual result: ");
        System.out.println(height);
        System.out.println("Below is a test for size method.");
        int size1 = bookshelf2.size();
        System.out.print("Expected result: 4, actual result: ");
        System.out.println(size1);
        bookshelf2.addLast(20);
        System.out.print("Expected result: 5, actual result: ");
        int size2 = bookshelf2.size();
        System.out.println(size2);
        System.out.println("Below are tests for isSorted method.");
        System.out.print("Expected result: true, actual result: ");
        System.out.println(bookshelf1.isSorted());
        System.out.print("Expected result: false, actual result: ");
        System.out.println(bookshelf2.isSorted());
        System.out.print("Expected result: true, actual result: ");
        System.out.println(bookshelfSorted.isSorted());
    }
}
