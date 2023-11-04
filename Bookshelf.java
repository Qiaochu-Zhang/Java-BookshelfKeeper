// Name: Qiaochu Zhang
// USC NetID: qzhang40
// CSCI455 PA2
// Fall 2023


import java.util.ArrayList;

/**
 * Class Bookshelf
 * Implements idea of arranging books into a bookshelf.
 * Books on a bookshelf can only be accessed in a specific way so books don’t fall down;
 * You can add or remove a book only when it’s on one of the ends of the shelf.
 * However, you can look at any book on a shelf by giving its location (starting at 0).
 * Books are identified only by their height; two books of the same height can be
 * thought of as two copies of the same book.
 */

public class Bookshelf {

    /**
     Representation invariant:

     1. The bookshelf can never be null
     2. The height of each book on the bookshelf can only be a positive integer.

     */

    private ArrayList<Integer> books;


    /**
     * Creates an empty Bookshelf object i.e. with no books
     */
    public Bookshelf() {
        this.books = new ArrayList<>();
        assert isValidBookshelf();  // sample assert statement (you will be adding more of these calls)
    }

    /**
     * Creates a Bookshelf with the arrangement specified in pileOfBooks. Example
     * values: [20, 1, 9].
     *
     * PRE: pileOfBooks contains an array list of 0 or more positive numbers
     * representing the height of each book.
     */
    public Bookshelf(ArrayList<Integer> pileOfBooks) {
        this.books = pileOfBooks;
        assert isValidBookshelf();

    }

    /**
     * Inserts book with specified height at the start of the Bookshelf, i.e., it
     * will end up at position 0.
     *
     * PRE: height > 0 (height of book is always positive)
     */
    public void addFront(int height) {
        assert height >0;
        this.books.add(0, height);
        assert isValidBookshelf();
    }

    /**
     * Inserts book with specified height at the end of the Bookshelf.
     *
     * PRE: height > 0 (height of book is always positive)
     */
    public void addLast(int height) {
        assert height >0;
        this.books.add(height);
        assert isValidBookshelf();
    }

    /**
     * Removes book at the start of the Bookshelf and returns the height of the
     * removed book.
     *
     * PRE: this.size() > 0 i.e. can be called only on non-empty BookShelf
     */
    public int removeFront() {
        int height = this.books.get(0);
        this.books.remove(0);
        assert isValidBookshelf();
        return (height);
    }

    /**
     * Removes book at the end of the Bookshelf and returns the height of the
     * removed book.
     *
     * PRE: this.size() > 0 i.e. can be called only on non-empty BookShelf
     */
    public int removeLast() {
        assert this.size()>0;
        int size = this.books.size();
        int height = this.books.get(size-1);
        this.books.remove(size-1);
        assert isValidBookshelf();
        return (height);
    }

    /*
     * Gets the height of the book at the given position.
     *
     * PRE: 0 <= position < this.size()
     */
    public int getHeight(int position) {
        assert isValidBookshelf();
        assert (position < this.size()) && (position >=0);
        return this.books.get(position);
    }

    /**
     * Returns number of books on the Bookshelf.
     */
    public int size() {
        assert isValidBookshelf();
        return this.books.size();
    }

    /**
     * Returns string representation of this Bookshelf. Returns a string with the height of all
     * books on the bookshelf, in the order they are in on the bookshelf, using the format shown
     * by example here:  “[7, 33, 5, 4, 3]”
     */
    public String toString() {
        assert isValidBookshelf();
        String arrString = "[";
        int size = this.books.size();
        if(size==0){
            return "[]";
        }
        else
        {
            for (int i = 0; i < size; i++) {
                arrString += String.valueOf(this.books.get(i));
                arrString += ", ";
            }
            StringBuilder builder = new StringBuilder(arrString);
            builder.deleteCharAt(arrString.length() - 2);
            String arrStringModified = builder.toString();
            if (arrStringModified.endsWith(" ")) {
                arrStringModified = arrStringModified.substring(0, arrStringModified.length() - 1);
            }
            arrStringModified += "]";
            return arrStringModified;
        }
    }

    /**
     * Returns true iff the books on this Bookshelf are in non-decreasing order.
     * (Note: this is an accessor; it does not change the bookshelf.)
     */
    public boolean isSorted() {
        assert isValidBookshelf();
        if(this.size() == 0){
            return true;
        }
        else
        {
            int height =0;
            int size = this.size();
            for(int i=0;i<size;i++){
                if(height >= this.getHeight(i)){
                    return false;
                }
                else
                {
                 height = this.getHeight(i);
                }
            }
        }
        return true;
    }

    /**
     * Returns true iff the Bookshelf data is in a valid state.
     * (See representation invariant comment for more details.)
     */
    private boolean isValidBookshelf() {
        int size = this.books.size();
        if(size == 0){
            return true;
        }
        else
        {
            for(int i=0;i<size;i++){
                int height = this.books.get(i);
                if(height <= 0){
                    return false;
                }
            }
        }
        return true;
    }
}
