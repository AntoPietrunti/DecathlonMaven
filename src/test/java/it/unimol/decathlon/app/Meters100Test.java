package it.unimol.decathlon.app;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Meters100Test {

    @Test
    void testGetTotalSetDicesScore() {
        Meters100 meters100 = new Meters100();
        try {
            meters100.roll();
        } catch (Exception e) {
            fail(e);
        }

        int score = 0;
        for (int a : meters100.getSetDicesScore()) {
            if (a == 6) {
                score += -6;
            } else {
                score += a;
            }
        }

        assertEquals(score, meters100.getTotalSetDicesScore());
    }

    @Test
    void testGetFullScore() {
        Meters100 meters100 = new Meters100();
        try {
            meters100.roll();
        } catch (Exception e) {
            fail(e);
        }

        int score = 0;
        for (Dice dice : meters100.getDices()) {
            if (dice.getValue() == 6) {
                score += -6;
            } else {
                score += dice.getValue();
            }
        }
        assertEquals(score, meters100.getFullScore());
    }
}