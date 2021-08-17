
public class NewMain {

    public static void main(String[] args) {
        System.out.println("Tulostetaan Eulerin phi-funktion arvoja.");

        for (int i = 1; i <= 1000; i++) {
            int jaollisia = 0;

            for (int j = 1; j <= i; j++) {
                for (int k = 1; k <= j; k++) {
                    int syt = 0;
                    if (j % k == 0 && i % k == 0) {
                        syt = k;
                        if (syt > 1) {
                            jaollisia++;
                            break;
                        }
                    }
                }

            }
            System.out.println(i + "\t" + (i - jaollisia));
        }

    }

}
