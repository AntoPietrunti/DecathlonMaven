package it.unimol.decathlon.app;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Meters400Test {

    @Test
    void testGetTotalSetDicesScore() {
        Meters400 meters400 = new Meters400();
        try {
            meters400.roll();
        } catch (Exception e) {
            fail(e);
        }

        int score = 0;
        for (int a : meters400.getSetDicesScore()) {
            if (a == 6) {
                score += -6;
            } else {
                score += a;
            }
        }

        assertEquals(score, meters400.getTotalSetDicesScore());
    }

    @Test
    void testGetFullScore() {
        Meters400 meters400 = new Meters400();
        try {
            meters400.roll();
        } catch (Exception e) {
            fail(e);
        }

        int score = 0;
        for (Dice dice : meters400.getDices()) {
            if (dice.getValue() == 6) {
                score += -6;
            } else {
                score += dice.getValue();
            }
        }

        assertEquals(score, meters400.getFullScore());
    }
}