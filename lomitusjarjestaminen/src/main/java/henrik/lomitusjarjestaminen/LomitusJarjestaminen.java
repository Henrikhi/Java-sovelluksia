package henrik.lomitusjarjestaminen;

public class LomitusJarjestaminen {

    private int[] lista;

    public int[] jarjesta(int[] lista) {
        this.lista = lista;

        jarjesta(0, lista.length - 1);
        return this.lista;
    }

    public void jarjesta(int a, int b) {
        if (a == b) {
            return;
        }
        int k = (a + b) / 2;
        jarjesta(a, k);
        jarjesta(k + 1, b);
        lomita(a, k, k + 1, b);
    }

    public void lomita(int a1, int b1, int a2, int b2) {
        int a = a1;
        int b = b2;
        int[] apulista = new int[this.lista.length];

        for (int i = a; i <= b; i++) {
            if (a2 > b2 || (a1 <= b1 && lista[a1] < lista[a2])) {
                apulista[i] = lista[a1];
                a1++;
            } else {
                apulista[i] = lista[a2];
                a2++;
            }
        }
        for (int i = a; i <= b; i++) {
            lista[i] = apulista[i];
        }
    }

}
