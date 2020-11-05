package hu.targetshooting.model.service;

public class ShotResultHelper {

    public static Integer getScore(String shots) {
        return loertek(shots);
    }

    private static Integer loertek(String sor) {
        int aktpont = 20, ertek = 0;
        for (int i = 0; i < sor.length(); i++) {
            if (aktpont > 0 && sor.charAt(i) == '-') {
                aktpont--;
            } else {
                ertek += aktpont;
            }
        }
        return ertek;
    }
}
