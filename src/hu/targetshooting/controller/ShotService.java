package hu.targetshooting.controller;

import hu.targetshooting.model.domain.ShotResult;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class ShotService {

    private final List<ShotResult> shotResults;

    public ShotService(List<ShotResult> shotResults) {
        this.shotResults = shotResults;
    }

    /**
     * 2. feladat: Írja a képernyőre azon versenyzők rajtszámát,
     * akiknek egymás után két (vagy több) lövése is talált!
     * A versenyzők rajtszámát egy-egy szóközzel válassza el egymástól!
     */
    public String getTwoSuccessShotIds() {
        return shotResults.stream()
                .filter(ShotResult::hasTwoSuccessShotsInRow)
                .map(ShotResult::getId)
                .map(String::valueOf)
                .collect(Collectors.joining(" "));
    }

    /**
     * 3. feladat: Írja a képernyőre, hogy melyik versenyző adta le
     * a legtöbb lövést! Ha többen is ugyanannyi lövést adtak le,
     * elegendő egyikük rajtszámát kiírni.
     */
    public int getLongestShotSequenceId() {
        return shotResults.stream()
                .max(Comparator.comparing(ShotResult::getShotCount))
                .map(ShotResult::getId)
                .get();
    }

    /**
     * 5. feladat: Kérje be a felhasználótól egy versenyző sorszámát,
     * majd írja ki, hogy:
     *  a. hányadik lövései találtak (az értékeket egymástól szóközzel válassza el!)
     */
    public String getSuccessShotIndexes(int id) {
        return shotResults.stream()
                .filter(i -> i.getId() == id)
                .map(ShotResult::getSuccessShotIndexes)
                .findFirst()
                .get();
    }

    /**
     * 5. feladat: Kérje be a felhasználótól egy versenyző sorszámát,
     * majd írja ki, hogy:
     *  b. hány korongot talált el összesen
     */
    public long countSuccessShots(int id) {
        return shotResults.stream()
                .filter(i -> i.getId() == id)
                .map(ShotResult::countSuccessShots)
                .findFirst()
                .get();
    }

    /**
     * 5. feladat: Kérje be a felhasználótól egy versenyző sorszámát,
     * majd írja ki, hogy:
     *  c. milyen hosszú volt a leghosszabb hibátlan lövéssorozata
     */
    public Integer getLongestSuccessSequenceSize(int id) {
        return shotResults.stream()
                .filter(i -> i.getId() == id)
                .map(ShotResult::getLongestSuccessSequenceSize)
                .findFirst()
                .get();
    }
    /**
     * 5. feladat: Kérje be a felhasználótól egy versenyző sorszámát,
     * majd írja ki, hogy:
     *  d. hány pontot ért el!
     */
    public Integer getScore(int id) {
        return shotResults.stream()
                .filter(i -> i.getId() == id)
                .map(ShotResult::getScore)
                .findFirst()
                .get();
    }

    /**
     * Állítsa elő a verseny végeredményét!
     * A listában soronként tüntesse fel a versenyző helyezését,
     * rajtszámát és pontszámát! A helyezés megadásakor a holtversenyt
     * a bevezetőben megfogalmazott szabályok alapján az alábbi mintához
     * hasonlóan kezelje! Az adatokat egy-egy tabulátorral
     * (ASCII kódja a 9-es) válassza el egymástól!
     * A lista legyen pontszám szerint csökkenő!
     */
    public List<String> getFinalResult() {
        List<String> lines = new ArrayList<>();
        List<ShotResult> finalResultList = createFinalResult();
        int prevScore = 0, prevOrder = 0;
        for(int i = 0; i < finalResultList.size(); i++) {
            ShotResult actualResult = finalResultList.get(i);
            int order = actualResult.getScore() == prevScore
                    ? prevOrder
                    : i + 1;
            lines.add(order + "\t" + actualResult.getId() + "\t" + actualResult.getScore());
            prevScore = actualResult.getScore();
            prevOrder = order;
        }
        return lines;
    }

    private List<ShotResult> createFinalResult() {
        return shotResults.stream()
                .sorted((i, j) -> j.getScore().compareTo(i.getScore()))
                .collect(Collectors.toList());
    }
}
