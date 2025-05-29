package catcafe;

import tree.InOrderVisitor;
import tree.PostOrderVisitor;

import java.util.Optional;

/**
 * Startklasse für das CatCafe-Programm.
 */
public class Main {
    /**
     * Einstiegspunkt des Programms.
     *
     * @param args Kommandozeilenargumente, werden hier nicht verwendet
     */
    public static void main(String... args) {
        // Neues CatCafe wird erstellt
        CatCafe cafe = new CatCafe();

        // Vier Katzen werden hinzugefügt
        cafe.addCat(new FelineOverLord("Miss Chief Sooky", 2));
        cafe.addCat(new FelineOverLord("Gwenapurr Esmeralda", 3));
        cafe.addCat(new FelineOverLord("Morticia", 3));
        cafe.addCat(new FelineOverLord("Fitzby Darnsworth", 5));

        // Ausgabe der Gesamtanzahl an Katzen
        System.out.println("Es schnurren " + cafe.getCatCount() + " Samtpfötchen.");

        // Sucht nach einer Katze im Gewichtsbereich [3, 4)
        cafe.getCatByWeight(3, 4)
            .ifPresent(cat -> System.out.println("Gewicht [3, 4] " + cat));

        // Sucht nach einer Katze mit dem Namen "Morticia"
        cafe.getCatByName("Morticia")
            .ifPresent(cat -> System.out.println("Name 'Morticia' " + cat));

        // Sucht nach einer Katze mit dem Namen "Miss Chief Sooky"
        cafe.getCatByName("Miss Chief Sooky")
            .ifPresent(cat -> System.out.println("Name 'Miss Chief Sooky' " + cat));

        System.out.println("");

        // Alternativ: Ergebnisse erneut als Optional gespeichert und ausgegeben
        Optional<FelineOverLord> meow = cafe.getCatByWeight(3, 4);
        System.out.println("Gewicht [3,4]: " + meow);

        meow = cafe.getCatByName("Morticia");
        System.out.println("Name 'Morticia': " + meow);

        meow = cafe.getCatByName("Miss Chief Sooky");
        System.out.println("Name 'Miss Chief Sooky': " + meow);

        // Visitor zur Traversierung: InOrder und PostOrder
        InOrderVisitor<FelineOverLord> inOrderVisitor = new InOrderVisitor<>();
        PostOrderVisitor<FelineOverLord> postOrderVisitor = new PostOrderVisitor<>();

        // Ausgabe der Traversierungsergebnisse
        System.out.println("InOrder: " + cafe.accept(inOrderVisitor));
        System.out.println("PostOrder: " + cafe.accept(postOrderVisitor));
    }
}
