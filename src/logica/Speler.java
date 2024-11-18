package logica;

public class Speler implements Comparable<Speler> {

    @Override
    public String toString() {
        return "Speler{" +
                "voornaam='" + voornaam + '\'' +
                ", familienaam='" + familienaam + '\'' +
                ", geboortejaar=" + geboortejaar +
                ", lidNummer=" + lidNummer +
                '}';
    }

    private String voornaam, familienaam;
    private int geboortejaar, lidNummer;

    public String getVoornaam() {
        return voornaam;
    }

    public Speler setVoornaam(String voornaam) {
        this.voornaam = voornaam;
        return this;
    }

    public String getFamilienaam() {
        return familienaam;
    }

    public Speler setFamilienaam(String familienaam) {
        this.familienaam = familienaam;
        return this;
    }

    public int getGeboortejaar() {
        return geboortejaar;
    }

    public Speler setGeboortejaar(int geboortejaar) {
        this.geboortejaar = geboortejaar;
        return this;
    }

    public int getLidNummer() {
        return lidNummer;
    }

    public Speler setLidNummer(int lidNummer) {
        this.lidNummer = lidNummer;
        return this;
    }

    public Speler(String voornaam, String familienaam, int geboortejaar) {
        this.voornaam = voornaam;
        this.familienaam = familienaam;
        this.geboortejaar = geboortejaar;
    }

    @Override
    public int compareTo(Speler speler) {
        int familienaamVergelijk = this.familienaam.compareTo(speler.familienaam);
        if (familienaamVergelijk != 0) {
            return familienaamVergelijk;
        }
        return this.voornaam.compareTo(speler.voornaam);
    }
}
