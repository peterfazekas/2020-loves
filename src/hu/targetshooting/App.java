package hu.targetshooting;

import hu.targetshooting.controller.ShotService;
import hu.targetshooting.model.service.*;

import java.util.Scanner;

public class App {

    private final ShotService shotService;
    private final Console console;
    private final ResultWriter resultWriter;

    private App() {
        console = new Console(new Scanner(System.in));
        resultWriter = new ResultWriter("sorrend.txt");
        DataApi dataApi = new DataApi(new FileReader(), new DataParser());
        shotService = new ShotService(dataApi.getData("verseny.txt"));
    }

    public static void main(String[] args) {
        new App().run();
    }

    private void run() {
        System.out.println("2. feladat:");
        System.out.println("Az egymást követően többször találó versenyzők: "
                + shotService.getTwoSuccessShotIds());
        System.out.println("3. feladat:");
        System.out.println("A legtöbb lövést leadó versenyző rajtszáma: "
                + shotService.getLongestShotSequenceId());
        System.out.println("5. feladat");
        System.out.print("Adjon meg egy rajtszamot! ");
        int id = console.read();
        System.out.println("   a. Célt érő lövések: "
                + shotService.getSuccessShotIndexes(id));
        System.out.println("   b. Az eltalált korongok száma: "
                + shotService.countSuccessShots(id));
        System.out.println("   c. A leghosszabb hibátlan sorozat hossza: "
                + shotService.getLongestSuccessSequenceSize(id));
        System.out.println("   d. A versenyző pontszáma: "
                + shotService.getScore(id));
        resultWriter.printAll(shotService.getFinalResult());
    }
}
