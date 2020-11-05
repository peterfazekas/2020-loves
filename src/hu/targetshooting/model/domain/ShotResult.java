package hu.targetshooting.model.domain;

import hu.targetshooting.model.service.ShotResultHelper;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ShotResult {

    private final Integer id;
    private final String shots;

    public ShotResult(Integer id, String shots) {
        this.id = id;
        this.shots = shots;
    }

    public Integer getId() {
        return id;
    }

    public String getShots() {
        return shots;
    }

    public boolean hasTwoSuccessShotsInRow() {
        return shots.contains("++");
    }

    public Integer getShotCount() {
        return shots.length();
    }

    public Integer getScore() {
        return ShotResultHelper.getScore(shots);
    }

    public String getSuccessShotIndexes() {
        return IntStream.range(0, getShotCount())
                .filter(i -> shots.charAt(i) == '+')
                .mapToObj(i -> String.valueOf(i + 1))
                .collect(Collectors.joining(" "));
    }

    public long countSuccessShots() {
        return shots.chars()
                .filter(i -> (char) i == '+')
                .count();
    }

    public Integer getLongestSuccessSequenceSize() {
        return Arrays.stream(shots.split("-"))
                .mapToInt(String::length)
                .max()
                .getAsInt();
    }

    @Override
    public String toString() {
        return id + "\t" + getScore();
    }
}
