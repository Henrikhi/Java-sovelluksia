package com.mycompany.alkuluvut;

import java.util.ArrayList;

public class Alkuluvut {

    private ArrayList<Integer> alkuluvut;

    public Alkuluvut() {
        this.alkuluvut = new ArrayList<>();
    }

    public boolean onkoLukuJaollinenEdellisilla(Integer luku) {
        if (luku < 2) {
            return false;
        }
        for (Integer alkuluku : alkuluvut) {
            if (onkoJaollinenLuvulla(luku, alkuluku)) {
                return false;
            }
        }
        return true;
    }

    public void lisaaListalle(Integer luku) {
        alkuluvut.add(luku);
    }

    public boolean onkoJaollinenLuvulla(int luku, int luvulla) {
        return luku % luvulla == 0;
    }

    @Override
    public String toString() {
        for (Integer luku : alkuluvut) {
            System.out.println(luku);
        }
        return "";
    }

    public Integer koko() {
        return this.alkuluvut.size();
    }

    public Boolean sisaltaa(Integer luku) {
        return this.alkuluvut.contains(luku);
    }
    
    public void tyhjenna() {
        this.alkuluvut.clear();
    }

}
