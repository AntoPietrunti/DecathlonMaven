package it.unimol.decathlon.app;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Meters1500Test {

    @Test
    void testGetTotalSetDicesScore() {
        Meters1500 meters1500 = new Meters1500();
        try {
            meters1500.roll();
        } catch (Exception e) {
            fail(e);
        }

        int score = 0;
        for (int a : meters1500.getSetDicesScore()) {
            if (a == 6) {
                score += -6;
            } else {
                score += a;
            }
        }

        assertEquals(score, meters1500.getTotalSetDicesScore());
    }

    @Test
    void testGetFullScore() {
        Meters1500 meters1500 = new Meters1500();
        try {
            meters1500.roll();
        } catch (Exception e) {
            fail(e);
        }

        int score = 0;
        for (Dice dice : meters1500.getDices()) {
            if (dice.getValue() == 6) {
                score += -6;
            } else {
                score += dice.getValue();
            }
        }

        assertEquals(score, meters1500.getFullScore());
    }
}