package vinicius.kalah.service;

import org.springframework.stereotype.Service;
import vinicius.kalah.model.Player;

import java.util.Map;
import java.util.stream.IntStream;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

public abstract class BoardUtils {
    private static final Integer STONES = 6;

    public static Map<Integer, Integer> createInitialBoard() {
        return IntStream.rangeClosed(1, 14)
                .boxed()
                .collect(toMap(identity(), pit -> isKalah(pit) ? 0 : STONES));
    }

    public static boolean isKalah(Integer pit) {
        return Player.ONE.getKalah().equals(pit) || Player.TWO.getKalah().equals(pit);
    }
}
