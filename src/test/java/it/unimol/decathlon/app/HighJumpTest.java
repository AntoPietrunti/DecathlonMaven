package it.unimol.decathlon.app;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HighJumpTest {

    @Test
    void testSetHeight() {
        HighJump highJump = new HighJump();
        int height;
        height = 3;
        assertFalse(highJump.setHeight(height));

        height = 10;
        assertTrue(highJump.setHeight(height));

        height = 33;
        assertFalse(highJump.setHeight(height));
    }

    @Test
    void testGetTotalSetDicesScore() {
        HighJump highJump = new HighJump();
                try {
            highJump.roll();
        } catch (Exception e) {
            fail(e);
        }

        int score = 0;
        for (int a : highJump.getSetDicesScore()) {
            score += a;
        }

        assertEquals(score, highJump.getTotalSetDicesScore());
    }

    @Test
    void testGetFullScore() {
        HighJump highJump = new HighJump();
        try {
            highJump.roll();
        } catch (Exception e) {
            fail(e);
        }
        highJump.setHeight(23);
        int height = 23;

        int score = 0;
        for (int a : highJump.getSetDicesScore()) {
            score += a;
        }

        if(score < height) {
            assertEquals(0, highJump.getFullScore());
        } else {
            assertEquals(height, highJump.getFullScore());
        }
    }
}