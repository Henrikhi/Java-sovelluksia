
public class Vihollinen {

    private String nimi;
    private int maxiskuvoima;
    private int hp;
    private int maxHp;
    private int osumatarkkuus;
    private int kriittinenTN;

    public Vihollinen(String nimi, int maxHp, int maxiskuvoima, int osumatarkkuus, int kriittinenTN) {
        this.nimi = nimi;
        this.maxiskuvoima = maxiskuvoima;
        this.hp = maxHp;
        this.maxHp = maxHp;
        this.osumatarkkuus = osumatarkkuus;
        this.kriittinenTN = kriittinenTN;
    }

    public int getHp() {
        return hp;
    }

    public int getMaxiskuvoima() {
        return maxiskuvoima;
    }

    public int getMaxHp() {
        return maxHp;
    }

    public String getNimi() {
        return nimi;
    }

    public int getOsumatarkkuus() {
        return osumatarkkuus;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public void setMaxIskuvoima(int iskuvoima) {
        this.maxiskuvoima = maxiskuvoima;
    }

    public void setMaxHp(int maxHp) {
        this.maxHp = maxHp;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public void setOsumatarkkuus(int osumatarkkuus) {
        this.osumatarkkuus = osumatarkkuus;
    }

    public int getKriittinenTN() {
        return kriittinenTN;
    }

    public void setMaxiskuvoima(int maxiskuvoima) {
        this.maxiskuvoima = maxiskuvoima;
    }
    
    
    
    
    
    
}
