package it.unimol.decathlon.app;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MetersObstacle110Test {

    @Test
    void testGetTotalSetDicesScore() {
        MetersObstacle110 metersObstacle110 = new MetersObstacle110();
        try {
            metersObstacle110.roll();
        } catch (Exception e) {
            fail(e);
        }

        int score = 0;
        for (int a : metersObstacle110.getSetDicesScore()) {
            score += a;
        }

        assertEquals(score, metersObstacle110.getTotalSetDicesScore());
    }

    @Test
    void testGetFullScoreWithZeroReRoll() {
        MetersObstacle110 metersObstacle110 = new MetersObstacle110();
        try {
            metersObstacle110.roll();
        } catch (Exception e) {
            fail(e);
        }

        int score = 0;
        for (Dice dice : metersObstacle110.getDices()) {
            score += dice.getValue();
        }

        assertEquals(score, metersObstacle110.getFullScore());
    }

    @Test
    void testGetFullScoreWithReRoll() {
        MetersObstacle110 metersObstacle110 = new MetersObstacle110();
        try {
            metersObstacle110.roll();
        } catch (Exception e) {
            fail(e);
        }

        try {
            metersObstacle110.reRoll();
        } catch (Exception e) {
            fail(e);
        }


        int score = 0;
        for (Dice dice : metersObstacle110.getDices()) {
            score += dice.getValue();
        }

        assertEquals(score-1, metersObstacle110.getFullScore());

        try {
            metersObstacle110.reRoll();
        } catch (Exception e) {
            fail(e);
        }
        score = 0;
        for (Dice dice : metersObstacle110.getDices()) {
            score += dice.getValue();
        }

        assertEquals(score-2, metersObstacle110.getFullScore());

    }
}