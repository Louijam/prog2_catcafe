package catcafe;

import tree.InOrderVisitor;
import tree.PostOrderVisitor;

import java.util.Optional;

/** Starter for the cat-café task. */
public class Main {
    /**
     * And go.
     *
     * @param args command line parameters, not used
     */
    public static void main(String... args) {
        CatCafe cafe = new CatCafe();

        cafe.addCat(new FelineOverLord("Miss Chief Sooky", 2));
        cafe.addCat(new FelineOverLord("Gwenapurr Esmeralda", 3));
        cafe.addCat(new FelineOverLord("Morticia", 3));
        cafe.addCat(new FelineOverLord("Fitzby Darnsworth", 5));

        System.out.println("Es schnurren " + cafe.getCatCount() + " Samtpfötchen.");

        cafe.getCatByWeight(3,4 )
            .ifPresent(cat -> System.out.println("Gewicht [3, 4] "+cat));

        cafe.getCatByName("Morticia")
            .ifPresent(cat -> System.out.println("Name 'Morticia' "+cat));

        cafe.getCatByName("Miss Chief Sooky")
            .ifPresent(cat -> System.out.println("Name 'Miss Chief Sooky' "+cat));

        System.out.println("");

        Optional<FelineOverLord> meow = cafe.getCatByWeight(3, 4);
        System.out.println("Gewicht [3,4]: " + meow);

        meow = cafe.getCatByName("Morticia");
        System.out.println("Name 'Morticia': " + meow);

        meow = cafe.getCatByName("Miss Chief Sooky");
        System.out.println("Name 'Miss Chief Sooky': " + meow);

        InOrderVisitor<FelineOverLord> inOrderVisitor = new InOrderVisitor<>();
        PostOrderVisitor<FelineOverLord> postOrderVisitor = new PostOrderVisitor<>();

        System.out.println("InOrder: " + cafe.accept(inOrderVisitor));
        System.out.println("PostOrder: " + cafe.accept(postOrderVisitor));
    }
}
