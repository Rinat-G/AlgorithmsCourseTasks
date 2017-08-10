import edu.princeton.cs.algs4.StdIn;

public class Permutation {

    public static void main(String[] args) {
        int k;

        k = Integer.parseInt(args[0]);

        RandomizedQueue<String> rq = new RandomizedQueue<>();

//        while (!StdIn.isEmpty()) {
        for (int i = 0; i < k; i++) {
            String string = StdIn.readString();
            rq.enqueue(string);
        }

        int i = 0;
        for (String str :
                rq) {
            if (i == k) return;
            System.out.println(str);
            i++;

        }


    }
}
