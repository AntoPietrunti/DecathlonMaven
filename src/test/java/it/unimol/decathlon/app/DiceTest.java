package it.unimol.decathlon.app;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class DiceTest {

    @Test
    public void testRoll() {
        int dice;
        Random random = new Random();
        dice = random.nextInt(6) + 1;
        assertTrue(dice <= 6 && dice >= 1);
    }


}