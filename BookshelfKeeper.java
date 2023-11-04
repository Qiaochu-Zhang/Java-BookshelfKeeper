// Name: Qiaochu Zhang
// USC NetID: qzhang40
// CSCI455 PA2
// Fall 2023


import java.util.PrimitiveIterator;

/**
 * Class BookshelfKeeper
 *
 * Enables users to perform efficient putPos or pickHeight operation on a bookshelf of books kept in
 * non-decreasing order by height, with the restriction that single books can only be added
 * or removed from one of the two *ends* of the bookshelf to complete a higher level pick or put
 * operation.  Pick or put operations are performed with minimum number of such adds or removes.
 */
public class BookshelfKeeper {

    /**
     Representation invariant:

     1. The bookshelfKeeper contains a valid bookshelf.
     2. The heights of books on the bookshelf are sorted from small to large.

     */


    private Bookshelf bookshelf;
    private int totalCallCounts; // total number of calls to mutators

    private int lastCalls;

    /**
     * Creates a BookShelfKeeper object with an empty bookshelf
     */
    public BookshelfKeeper() {
        bookshelf = new Bookshelf();
        totalCallCounts = 0;
        lastCalls = 0;
        assert isValidBookshelfKeeper();
    }

    /**
     * Creates a BookshelfKeeper object initialized with the given sorted bookshelf.
     * Note: method does not make a defensive copy of the bookshelf.
     *
     * PRE: sortedBookshelf.isSorted() is true.
     */
    public BookshelfKeeper(Bookshelf sortedBookshelf) {
            assert sortedBookshelf.isSorted();
            bookshelf = sortedBookshelf;
            totalCallCounts = 0;
            lastCalls = 0;
            assert isValidBookshelfKeeper();
    }


    /**
     * Removes a book from the specified position in the bookshelf and keeps bookshelf sorted
     * after picking up the book.
     *
     * Returns the number of calls to mutators on the contained bookshelf used to complete this
     * operation. This must be the minimum number to complete the operation.
     *
     * PRE: 0 <= position < getNumBooks()
     */

    // bookshelf.removeBefore removeAfter addBefore addAfter; booksLeft booksRight for putHeight
    public int pickPos(int position) {
        assert position >= 0 && position < getNumBooks();
        int counts = 0;
        int size = bookshelf.size();
        Bookshelf dropedBoooks = new Bookshelf();
        if (position < size / 2) {
            dropedBoooks = removeBefore(position);
            counts += dropedBoooks.size();
            bookshelf.removeFront();
            counts++;
            addBefore(dropedBoooks);
            counts += dropedBoooks.size();
        } else {
            dropedBoooks = removeAfter(position);
            counts += dropedBoooks.size();
            bookshelf.removeLast();
            counts++;
            addAfter(dropedBoooks);
            counts += dropedBoooks.size();
        }
        lastCalls = counts;
        totalCallCounts += lastCalls;
        assert isValidBookshelfKeeper();
        return counts;
    }

    /**
     * Inserts book with specified height into the shelf.  Keeps the contained bookshelf sorted
     * after the insertion.
     *
     * Returns the number of calls to mutators on the contained bookshelf used to complete this
     * operation. This must be the minimum number to complete the operation.
     *
     * PRE: height > 0
     */
    public int putHeight(int height) {
        assert height > 0;
        Bookshelf droppedBooks = new Bookshelf();
        int counts = 0;
        int booksLeft = booksLeft(height); // minimum distance of the inserting position to one end
        int booksRight = booksRight(height);
        if(booksLeft <= booksRight){
            droppedBooks = removeBefore(booksLeft);
            counts += droppedBooks.size();
            bookshelf.addFront(height);
            counts ++;
            addBefore(droppedBooks);
            counts += droppedBooks.size();
        }
        else {
            droppedBooks = removeAfter(bookshelf.size()-booksRight-1);
            counts += droppedBooks.size();
            bookshelf.addLast(height);
            counts ++;
            addAfter(droppedBooks);
            counts += droppedBooks.size();
        }
        lastCalls = counts;
        totalCallCounts += lastCalls;
        assert isValidBookshelfKeeper();
        return counts;
    }

    /**
     * Returns the total number of calls made to mutators on the contained bookshelf
     * so far, i.e., all the ones done to perform all of the pick and put operations
     * that have been requested up to now.
     */
    public int getTotalOperations() {
        assert isValidBookshelfKeeper();
        return totalCallCounts;
    }

    /**
     * Returns the number of books on the contained bookshelf.
     */
    public int getNumBooks() {
        assert isValidBookshelfKeeper();
        return bookshelf.size();
    }

    /**
     * Returns string representation of this BookshelfKeeper. Returns a String containing height
     * of all books present in the bookshelf in the order they are on the bookshelf, followed
     * by the number of bookshelf mutator calls made to perform the last pick or put operation,
     * followed by the total number of such calls made since we created this BookshelfKeeper.
     *
     * Example return string showing required format: “[1, 3, 5, 7, 33] 4 10”
     *
     */
    public String toString() {

        String bookShelfString = bookshelf.toString();
        assert isValidBookshelfKeeper();
        return (bookShelfString + " " + lastCalls + " " + totalCallCounts);

    }

    /**
     * Returns true iff the BookshelfKeeper data is in a valid state.
     * (See representation invariant comment for details.)
     */
    private boolean isValidBookshelfKeeper() {
        if(totalCallCounts<lastCalls || lastCalls<0){
            return false;
        }
        else {
            return bookshelf.isSorted();
        }
    }
    /**
     * Take a position as input.
     * Remove the books before a specific position of the BookShelfKeeper and collect the removed books into a new bookshelf.
     * Return the bookshelf which contains the removed books (droppedBooks) in the same order as in the former BookShelfKeeper.
     * PRE: position is a positive integer.
    */
    private Bookshelf removeBefore(int position) {
        Bookshelf droppedBooks = new Bookshelf();
        for(int i = 0; i<position; i++) {
            droppedBooks.addLast(bookshelf.getHeight(i));

        }
        for(int i = 0; i<position; i++) {
            bookshelf.removeFront();
        }
        assert isValidBookshelfKeeper();
        return droppedBooks;
    }
    /**
     * * Take a position as input.
     * Remove the books after a specific position of the BookShelfKeeper and collect the removed books into a new bookshelf.
     * Return the bookshelf which contains the removed books (droppedBooks) in the same order as in the former BookShelfKeeper.
     * PRE: position is a positive integer.
     */
    private Bookshelf removeAfter(int position) {
        Bookshelf droppedBooks = new Bookshelf();
        int size = bookshelf.size();
        for(int i = size-1; i>position; i--) {
            droppedBooks.addFront(bookshelf.getHeight(i));
            bookshelf.removeLast();
//            totalCallCounts++;
        }
        assert isValidBookshelfKeeper();
        return droppedBooks;
    }
    /**
     * Take the Bookshelf as input (droppedBooks).
     * Add the droppedBooks (bookshelf containing the removed books) to the front of the BookShelfKeeper.
     * PRE: droppedBooks is a valid bookshelf.
     */
    private void addBefore(Bookshelf droppedBooks) {
        int size = droppedBooks.size();
        for(int i = size-1; i >= 0; i--) {
            bookshelf.addFront(droppedBooks.getHeight(i));
 //           totalCallCounts++;
        }
        assert isValidBookshelfKeeper();
    }
    /**
     * Take the Bookshelf as input (droppedBooks).
     * Add the droppedBooks (bookshelf containing the removed books) to the front of the BookShelfKeeper.
     * PRE: droppedBooks is a valid bookshelf.
     */
    private void addAfter(Bookshelf droppedBooks) {
        int size = droppedBooks.size();
        for(int i =0; i< size; i++){
            bookshelf.addLast(droppedBooks.getHeight(i));
//            totalCallCounts++;
        }
        assert isValidBookshelfKeeper();
    }

    /**
     * @param height
     * @return the number of books in the BookShelfKeeper that have a smaller height than the input height.
     * PRE: height is a positive integer.
     */
    private int booksLeft(int height){
        int size = bookshelf.size();
        for(int i =0; i < size; i++){
            if(bookshelf.getHeight(i) >= height){
                return i;
            }
        }
        assert isValidBookshelfKeeper();
        return size;
    }

    /**
     * @param height
     * @return the number of books in the BookShelfKeeper that have a larger height than the input height.
     * PRE: height is a positive integer.
     */
    private int booksRight (int height) {
        int size = bookshelf.size();
        for(int i = size-1; i >=0; i--){
            if(bookshelf.getHeight(i) <= height){
                return size - 1 - i;
            }
        }
        assert isValidBookshelfKeeper();
        return size;
    }
}

