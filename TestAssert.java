import java.util.ArrayList;

public class TestAssert {
    public static void main(String[] args) {
        ArrayList<Integer> arrBook1 = new ArrayList<>();
        arrBook1.add(1);
        arrBook1.add(4);
        arrBook1.add(8);
        Bookshelf bookshelf1 = new Bookshelf(arrBook1);

        ArrayList<Integer> arrBook2 = new ArrayList<>();
        arrBook2.add(-1);
        arrBook2.add(4);
        arrBook2.add(8);
        Bookshelf bookshelf2 = new Bookshelf(arrBook2);

    }
}
