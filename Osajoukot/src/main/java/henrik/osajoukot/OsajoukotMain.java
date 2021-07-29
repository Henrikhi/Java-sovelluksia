package henrik.osajoukot;

public class OsajoukotMain {

    static int[] A;
    static boolean[] mukana;

    private static void osajoukot(int m) {
        if (m == A.length) {
            for (int i = 0; i < A.length; i++) {
                if (mukana[i]) {
                    System.out.print(A[i] + " ");
                }
            }
            System.out.println();
        } else {
            mukana[m] = true;
            osajoukot(m + 1);
            mukana[m] = false;
            osajoukot(m + 1);
        }
    }

    public static void main(String[] args) {
        A = new int[]{0, 1, 2, 3, 4};
        mukana = new boolean[A.length];
        osajoukot(3);

    }

}
