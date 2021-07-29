package henrik.osavali;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class OsavaliMain {

    public static void main(String[] args) {
        Vali v = new Vali();
        v.lisaaVali(0, 4);
        v.lisaaVali(4, 7);
        v.lisaaVali(1, 5);
        v.tarkista();
    }

    private static class Vali {

        private ArrayList<Long> alut;
        private ArrayList<Long> loput;
        private HashMap<Long, Long> taulu;

        public Vali() {
            this.alut = new ArrayList();
            this.loput = new ArrayList();
            this.taulu = new HashMap<>();
        }

        public void lisaaVali(long alku, long loppu) {
            this.alut.add(alku);
            this.loput.add(loppu);
            this.taulu.put(alku, loppu);
        }

        public void tarkista() {
            alut.sort(Comparator.naturalOrder());
            loput.sort(Comparator.naturalOrder());
            for (int i = 0; i < alut.size(); i++) {
                long alku = alut.get(i);
                long loppu = loput.get(i);
                long arvo = taulu.get(alku);
//                System.out.println("alkupiste on " + alku);
//                System.out.println("loppupiste on " + loppu);
//                System.out.println("taulun arvo on " + arvo);
                if (loppu != arvo) {
                    System.out.println("väli löytyi!!");
                    System.out.println("tässä meni vikaan: ");
                    System.out.println("alkupiste on " + alku);
                    System.out.println("loppupiste on " + loppu);
                    System.out.println("taulun arvo on " + arvo);
                    return;
                }
            }
            System.out.println("kaikki meni ok.");
        }

    }

}
