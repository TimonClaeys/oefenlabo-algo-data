package logica;

import java.time.Year;
import java.util.*;
import java.util.stream.Collectors;

public class BasketClub {
    private Map<Categorie, TreeSet<Speler>> leden = new TreeMap<>() ;
    private static  int currentLidnummer = 0;

    public void schrijfIn(Speler speler) {
        speler.setLidNummer(++currentLidnummer);
        TreeSet<Speler> spelersInCategorie = leden.computeIfAbsent(getCategorie(speler.getGeboortejaar()), k -> new TreeSet<Speler>());
        spelersInCategorie.add(speler);
    }

    public void schrijfUit(Speler speler) {
        TreeSet<Speler> spelersInCategorie = leden.get(getCategorie(speler.getGeboortejaar()));
        spelersInCategorie.remove(speler);
        if (spelersInCategorie.isEmpty()) {
            leden.remove(getCategorie(speler.getGeboortejaar()));
        }
    }

    public TreeSet<Speler> getSpelers(Categorie categorie) {
        return leden.get(categorie);
    }

    public List<Speler> getLedenVolgensLidnummer() {
        return leden
                .entrySet()
                .stream()
                .flatMap(i -> i.getValue().stream())
                .sorted(Comparator.comparingInt(Speler::getLidNummer))
                .collect(Collectors.toList());
    }

    public List<Speler> getSpelersInCategorieVolgensLidnummer(Categorie categorie) {
        return leden.get(categorie).stream().sorted(Comparator.comparingInt(Speler::getLidNummer)).toList();
    }

    public List<Speler> getSpelersSorted() {
        return leden.entrySet()
                .stream()
                .flatMap(i -> i.getValue().stream())
                .sorted(Comparator.comparingInt(Speler::getGeboortejaar)
                        .thenComparing(Comparator.comparingInt(Speler::getLidNummer)))
                .toList();
    }

    public Categorie getCategorie(int geboortejaar) {
        int leeftijd = Year.now().getValue() - geboortejaar;

        return switch (leeftijd) {
            case 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40 -> Categorie.seniores;
            case 25, 26, 27 -> Categorie.juniores;
            case 22, 23, 24 -> Categorie.cadetten;
            case 21 -> Categorie.miniemen;
            case 19, 20 -> Categorie.pupillen;
            case 17, 18 -> Categorie.benjamins;
            case 15, 16 -> Categorie.microben;
            case 12, 13, 14 -> Categorie.premicroben;
            default -> throw new IllegalArgumentException("Leeftijd moet minimaal 12 jaar zijn");
        };
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Categorie, TreeSet<Speler>> entry : leden.entrySet()) {
            sb.append(entry.getKey().toString() + "\n");
            entry.getValue().forEach(speler -> sb.append(speler.toString()).append("\n"));
        }
        return sb.toString();
    }
}
