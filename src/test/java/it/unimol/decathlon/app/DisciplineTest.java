package it.unimol.decathlon.app;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DisciplineTest {

    @Test
    void testAddDice() {
        Discipline highJump = new HighJump();
        Discipline metersObstacle110 = new MetersObstacle110();
        Discipline meters100 = new Meters100();
        Discipline meters400 = new Meters400();
        Discipline meters1500 = new Meters1500();

        Dice dice = new Dice();
        meters100.addDice(dice);
        assertEquals(dice.getValue(), meters100.getDices().get(0).getValue());

        highJump.addDice(dice);
        assertEquals(dice.getValue(), highJump.getDices().get(0).getValue());

        meters400.addDice(dice);
        assertEquals(dice.getValue(), meters400.getDices().get(0).getValue());

        meters1500.addDice(dice);
        assertEquals(dice.getValue(), meters1500.getDices().get(0).getValue());

        metersObstacle110.addDice(dice);
        assertEquals(dice.getValue(), metersObstacle110.getDices().get(0).getValue());
    }

    @Test
    void testEquals() {
        Discipline highJump = new HighJump();
        Discipline highJump1 = new HighJump();
        Discipline metersObstacle110 = new MetersObstacle110();

        assertFalse(metersObstacle110.equals(highJump));
        assertTrue(highJump.equals(highJump1));
    }

    @Test
    void testRoll() {
        Discipline highJump = new HighJump();
        try {
            highJump.roll();
        } catch (Exception e) {
            fail(e);
        }
        assertEquals(1, highJump.getSlot());
        assertEquals(5, highJump.getSetDices());
        try {
            highJump.roll();
            fail("Exception expected.");
        } catch (Exception e) {
            assertTrue(e instanceof SlotException);
            SlotException t = (SlotException)e; 
            assertEquals(SlotException.MAX_SLOT_ERR, t.getCode());
        }

        Discipline metersObstacle110 = new MetersObstacle110();
        try {
            metersObstacle110.roll();
        } catch (Exception e) {
            fail(e);
        }
        assertEquals(1, metersObstacle110.getSlot());
        assertEquals(5, metersObstacle110.getSetDices());
        try {
            metersObstacle110.roll();
            fail("Exception expected.");
        } catch (Exception e) {
            assertTrue(e instanceof SlotException);
            SlotException t = (SlotException)e; 
            assertEquals(SlotException.MAX_SLOT_ERR, t.getCode());
        }

        Discipline meters100 = new Meters100();
        try {
            meters100.roll();
        } catch (Exception e) {
            fail(e);
        }
        assertEquals(1, meters100.getSlot());
        assertEquals(4, meters100.getSetDices());
        try {
            meters100.roll();
        } catch (Exception e) {
            fail(e);
        }
        assertEquals(2, meters100.getSlot());
        assertEquals(4, meters100.getSetDices());
        try {
            meters100.roll();
            fail("Exception expected.");
        } catch (Exception e) {
            assertTrue(e instanceof SlotException);
            SlotException t = (SlotException)e; 
            assertEquals(SlotException.MAX_SLOT_ERR, t.getCode());
        }
        assertEquals(2, meters100.getSlot());
        assertEquals(4, meters100.getSetDices());
        try {
            meters100.roll();
            fail("Exception expected.");
        } catch (Exception e) {
            assertTrue(e instanceof SlotException);
            SlotException t = (SlotException)e; 
            assertEquals(SlotException.MAX_SLOT_ERR, t.getCode());
        }

        Discipline meters400 = new Meters400();
        try {
            meters400.roll();
        } catch (Exception e) {
            fail(e);
        }
        assertEquals(1, meters400.getSlot());
        assertEquals(2, meters400.getSetDices());
        try {
            meters400.roll();
        } catch (Exception e) {
            fail(e);
        }
        assertEquals(2, meters400.getSlot());
        assertEquals(2, meters400.getSetDices());
        try {
            meters400.roll();
        } catch (Exception e) {
            fail(e);
        }
        assertEquals(3, meters400.getSlot());
        assertEquals(2, meters400.getSetDices());
        try {
            meters400.roll();
        } catch (Exception e) {
            fail(e);
        }
        assertEquals(4, meters400.getSlot());
        assertEquals(2, meters400.getSetDices());
        try {
            meters400.roll();
            fail("Exception expected.");
        } catch (Exception e) {
            assertTrue(e instanceof SlotException);
            SlotException t = (SlotException)e; 
            assertEquals(SlotException.MAX_SLOT_ERR, t.getCode());
        }

        Discipline meters1500 = new Meters1500();
        try {
            meters1500.roll();
        } catch (Exception e) {
            fail(e);
        }
        assertEquals(1, meters1500.getSlot());
        assertEquals(1, meters1500.getSetDices());
        try {
            meters1500.roll();
        } catch (Exception e) {
            fail(e);
        }
        assertEquals(2, meters1500.getSlot());
        assertEquals(1, meters1500.getSetDices());
        try {
            meters1500.roll();
        } catch (Exception e) {
            fail(e);
        }
        assertEquals(3, meters1500.getSlot());
        assertEquals(1, meters1500.getSetDices());
        try {
            meters1500.roll();
        } catch (Exception e) {
            fail(e);
        }
        assertEquals(4, meters1500.getSlot());
        assertEquals(1, meters1500.getSetDices());
        try {
            meters1500.roll();
        } catch (Exception e) {
            fail(e);
        }
        assertEquals(5, meters1500.getSlot());
        assertEquals(1, meters1500.getSetDices());
        try {
            meters1500.roll();
        } catch (Exception e) {
            fail(e);
        }
        assertEquals(6, meters1500.getSlot());
        assertEquals(1, meters1500.getSetDices());
        try {
            meters1500.roll();
        } catch (Exception e) {
            fail(e);
        }
        assertEquals(7, meters1500.getSlot());
        assertEquals(1, meters1500.getSetDices());
        try {
            meters1500.roll();
        } catch (Exception e) {
            fail(e);
        }
        assertEquals(8, meters1500.getSlot());
        assertEquals(1, meters1500.getSetDices());
        try {
            meters1500.roll();
            fail("Exception expected.");
        } catch (Exception e) {
            assertTrue(e instanceof SlotException);
            SlotException t = (SlotException)e; 
            assertEquals(SlotException.MAX_SLOT_ERR, t.getCode());
        }
    }

    @Test
    void testReRoll() {
        Discipline highJump = new HighJump();
        try {
            highJump.roll();
        } catch (Exception e) {
            fail(e);
        }
        assertEquals(1, highJump.getSlot());
        assertEquals(5, highJump.getSetDices());
        assertEquals(0, highJump.getCurrentAttempt());
        try {
            highJump.reRoll();
        } catch (Exception e) {
            fail(e);
        }
        assertEquals(1, highJump.getCurrentAttempt());
        try {
            highJump.reRoll();
        } catch (Exception e) {
            fail(e);
        }
        assertEquals(2, highJump.getCurrentAttempt());
        assertEquals(1, highJump.getSlot());
        assertEquals(5, highJump.getSetDices());

        Discipline metersObstacle110 = new MetersObstacle110();
        try {
            metersObstacle110.roll();
        } catch (Exception e) {
            fail(e);
        }
        assertEquals(1, metersObstacle110.getSlot());
        assertEquals(5, metersObstacle110.getSetDices());
        assertEquals(0, metersObstacle110.getCurrentAttempt());
        try {
            metersObstacle110.reRoll();
        } catch (Exception e) {
            fail(e);
        }
        assertEquals(1, metersObstacle110.getCurrentAttempt());
        try {
            metersObstacle110.reRoll();
        } catch (Exception e) {
            fail(e);
        }
        assertEquals(2, metersObstacle110.getCurrentAttempt());
        assertEquals(1, metersObstacle110.getSlot());
        assertEquals(5, metersObstacle110.getSetDices());

        Discipline meters100 = new Meters100();
        try {
            meters100.roll();
        } catch (Exception e) {
            fail(e);
        }
        assertEquals(1, meters100.getSlot());
        assertEquals(4, meters100.getSetDices());
        assertEquals(0, meters100.getCurrentAttempt());
        try {
            meters100.reRoll();
        } catch (Exception e) {
            fail(e);
        }
        assertEquals(1, meters100.getCurrentAttempt());
        try {
            meters100.reRoll();
        } catch (Exception e) {
            fail(e);
        }
        assertEquals(2, meters100.getCurrentAttempt());
        assertEquals(1, meters100.getSlot());
        try {
            meters100.roll();
        } catch (Exception e) {
            fail(e);
        }
        assertEquals(2, meters100.getSlot());
        assertEquals(4, meters100.getSetDices());
        try {
            meters100.reRoll();
        } catch (Exception e) {
            fail(e);
        }
        assertEquals(3, meters100.getCurrentAttempt());
        assertEquals(2, meters100.getSlot());
        assertEquals(4, meters100.getSetDices());
        try {
            meters100.reRoll();
        } catch (Exception e) {
            fail(e);
        }

        Discipline meters400 = new Meters400();
        try {
            meters400.roll();
        } catch (Exception e) {
            fail(e);
        }
        assertEquals(1, meters400.getSlot());
        assertEquals(2, meters400.getSetDices());
        assertEquals(0, meters400.getCurrentAttempt());
        try {
            meters400.reRoll();
        } catch (Exception e) {
            fail(e);
        }
        assertEquals(1, meters400.getCurrentAttempt());
        assertEquals(1, meters400.getSlot());
        assertEquals(2, meters400.getSetDices());
        try {
            meters400.roll();
        } catch (Exception e) {
            fail(e);
        }
        assertEquals(2, meters400.getSlot());
        assertEquals(2, meters400.getSetDices());
        assertEquals(1, meters400.getCurrentAttempt());
        try {
            meters400.reRoll();
        } catch (Exception e) {
            fail(e);
        }
        assertEquals(2, meters400.getCurrentAttempt());
        assertEquals(2, meters400.getSlot());
        assertEquals(2, meters400.getSetDices());
        try {
            meters400.roll();
        } catch (Exception e) {
            fail(e);
        }
        assertEquals(3, meters400.getSlot());
        assertEquals(2, meters400.getSetDices());
        assertEquals(2, meters400.getCurrentAttempt());
        try {
            meters400.reRoll();
        } catch (Exception e) {
            fail(e);
        }
        assertEquals(3, meters400.getCurrentAttempt());
        assertEquals(3, meters400.getSlot());
        assertEquals(2, meters400.getSetDices());
        try {
            meters400.roll();
        } catch (Exception e) {
            fail(e);
        }
        assertEquals(4, meters400.getSlot());
        assertEquals(2, meters400.getSetDices());
        assertEquals(3, meters400.getCurrentAttempt());
        try {
            meters400.reRoll();
        } catch (Exception e) {
            fail(e);
        }
        assertEquals(4, meters400.getCurrentAttempt());
        assertEquals(4, meters400.getSlot());
        assertEquals(2, meters400.getSetDices());

        Discipline meters1500 = new Meters1500();
        try {
            meters1500.roll();
        } catch (Exception e) {
            fail(e);
        }
        assertEquals(1, meters1500.getSlot());
        assertEquals(1, meters1500.getSetDices());
        try {
            meters1500.reRoll();
        } catch (Exception e) {
            fail(e);
        }
        assertEquals(1, meters1500.getCurrentAttempt());
        assertEquals(1, meters1500.getSlot());
        assertEquals(1, meters1500.getSetDices());
        try {
            meters1500.roll();
        } catch (Exception e) {
            fail(e);
        }
        assertEquals(2, meters1500.getSlot());
        assertEquals(1, meters1500.getSetDices());
        try {
            meters1500.roll();
        } catch (Exception e) {
            fail(e);
        }
        assertEquals(3, meters1500.getSlot());
        assertEquals(1, meters1500.getSetDices());
        try {
            meters1500.reRoll();
        } catch (Exception e) {
            fail(e);
        }
        assertEquals(2, meters1500.getCurrentAttempt());
        assertEquals(3, meters1500.getSlot());
        assertEquals(1, meters1500.getSetDices());
        try {
            meters1500.roll();
        } catch (Exception e) {
            fail(e);
        }
        assertEquals(4, meters1500.getSlot());
        assertEquals(1, meters1500.getSetDices());
        try {
            meters1500.reRoll();
        } catch (Exception e) {
            fail(e);
        }
        assertEquals(3, meters1500.getCurrentAttempt());
        assertEquals(4, meters1500.getSlot());
        assertEquals(1, meters1500.getSetDices());
        try {
            meters1500.roll();
        } catch (Exception e) {
            fail(e);
        }
        assertEquals(5, meters1500.getSlot());
        assertEquals(1, meters1500.getSetDices());
        try {
            meters1500.roll();
        } catch (Exception e) {
            fail(e);
        }
        assertEquals(6, meters1500.getSlot());
        assertEquals(1, meters1500.getSetDices());
        try {
            meters1500.reRoll();
        } catch (Exception e) {
            fail(e);
        }
        assertEquals(4, meters1500.getCurrentAttempt());
        assertEquals(6, meters1500.getSlot());
        assertEquals(1, meters1500.getSetDices());
        try {
            meters1500.roll();
        } catch (Exception e) {
            fail(e);
        }
        assertEquals(7, meters1500.getSlot());
        assertEquals(1, meters1500.getSetDices());
        try {
            meters1500.roll();
        } catch (Exception e) {
            fail(e);
        }
        assertEquals(8, meters1500.getSlot());
        assertEquals(1, meters1500.getSetDices());
        try {
            meters1500.reRoll();
        } catch (Exception e) {
            fail(e);
        }
        assertEquals(5, meters1500.getCurrentAttempt());
        assertEquals(8, meters1500.getSlot());
        assertEquals(1, meters1500.getSetDices());
        try {
            meters1500.reRoll();
            fail("Exception expected.");
        } catch (Exception e) {
            assertTrue(e instanceof AttemptException);
            AttemptException t = (AttemptException)e;
            assertEquals(AttemptException.MAX_ATTEMPT_ERR, t.getCode());
        }
    }

    @Test
    void testGetSetDicesScore() {
        Discipline highJump = new HighJump();
        try {
            highJump.roll();
        } catch (Exception e) {
            fail(e);
        }
        assertEquals(1, highJump.getSlot());
        assertEquals(5, highJump.getSetDices());

        List<Integer> dicesHighJump = new ArrayList<Integer>();
        for (Dice dice : highJump.getDices()) {
            dicesHighJump.add(dice.getValue());
        }
        assertEquals(dicesHighJump, highJump.getSetDicesScore());


        Discipline metersObstacle110 = new MetersObstacle110();
        try {
            metersObstacle110.roll();
        } catch (Exception e) {
            fail(e);
        }
        assertEquals(1, metersObstacle110.getSlot());
        assertEquals(5, metersObstacle110.getSetDices());

        List<Integer> dicesMetersObstacle110 = new ArrayList<Integer>();
        for (Dice dice : metersObstacle110.getDices()) {
            dicesMetersObstacle110.add(dice.getValue());
        }
        assertEquals(dicesMetersObstacle110, metersObstacle110.getSetDicesScore());


        Discipline meters100 = new Meters100();
        try {
            meters100.roll();
        } catch (Exception e) {
            fail(e);
        }
        assertEquals(1, meters100.getSlot());
        assertEquals(4, meters100.getSetDices());

        int sumMeters100 = 0;
        for (Dice dice : meters100.getDices()) {
            sumMeters100 += dice.getValue();
        }
        int dicesSumMeters100 = 0;
        for (Integer dice : meters100.getSetDicesScore()) {
            dicesSumMeters100 += dice;
        }
        assertEquals(sumMeters100, dicesSumMeters100);


        Discipline meters400 = new Meters400();
        try {
            meters400.roll();
        } catch (Exception e) {
            fail(e);
        }
        assertEquals(1, meters400.getSlot());
        assertEquals(2, meters400.getSetDices());

        int sumMeters400 = 0;
        for (Dice dice : meters400.getDices()) {
            sumMeters400 += dice.getValue();
        }
        int dicesSumMeters400 = 0;
        for (Integer dice : meters400.getSetDicesScore()) {
            dicesSumMeters400 += dice;
        }
        assertEquals(sumMeters400, dicesSumMeters400);


        Discipline meters1500 = new Meters1500();
        try {
            meters1500.roll();
        } catch (Exception e) {
            fail(e);
        }
        assertEquals(1, meters1500.getSlot());
        assertEquals(1, meters1500.getSetDices());

        int sumMeters1500 = 0;
        for (Dice dice : meters1500.getDices()) {
            sumMeters1500 += dice.getValue();
        }
        int dicesSumMeters1500 = 0;
        for (Integer dice : meters1500.getSetDicesScore()) {
            dicesSumMeters1500 += dice;
        }
        assertEquals(sumMeters1500, dicesSumMeters1500);
    }

    @Test
    void testGetSetPreviousDices() {
        Discipline highJump = new HighJump();
        try {
            highJump.roll();
        } catch (Exception e) {
            fail(e);
        }
        assertEquals(1, highJump.getSlot());
        assertEquals(5, highJump.getSetDices());
        try {
            List<List<Integer>> dice = highJump.getSetPreviousDices();
            fail("Exception expected.");
        } catch (Exception e) {
            assertTrue(e instanceof SlotException);
            SlotException t = (SlotException)e;
            assertEquals(SlotException.PREVIOUS_SLOT_ERR, t.getCode());
        }

        Discipline metersObstacle110 = new MetersObstacle110();
        try {
            metersObstacle110.roll();
        } catch (Exception e) {
            fail(e);
        }
        assertEquals(1, metersObstacle110.getSlot());
        assertEquals(5, metersObstacle110.getSetDices());
        try {
            List<List<Integer>> dice = highJump.getSetPreviousDices();
            fail("Exception expected.");
        } catch (Exception e) {
            assertTrue(e instanceof SlotException);
            SlotException t = (SlotException)e;
            assertEquals(SlotException.PREVIOUS_SLOT_ERR, t.getCode());
        }

        Discipline meters100 = new Meters100();
        try {
            meters100.roll();
        } catch (Exception e) {
            fail(e);
        }
        assertEquals(1, meters100.getSlot());
        assertEquals(4, meters100.getSetDices());

        int sumMeters100 = 0;
        for (Dice dice : meters100.getDices()) {
            sumMeters100 += dice.getValue();
        }
        try {
            meters100.roll();
        } catch (Exception e) {
            fail(e);
        }
        assertEquals(2, meters100.getSlot());
        assertEquals(4, meters100.getSetDices());
        int dicesPreviousSumMeters100 = 0;
        try{
            for (List<Integer> dice : meters100.getSetPreviousDices()) {
                for(Integer i : dice) {
                    dicesPreviousSumMeters100 += i;
                }
            }
            assertEquals(sumMeters100, dicesPreviousSumMeters100);
        } catch (Exception e) {
            fail(e);
        }


        Discipline meters400 = new Meters400();
        try {
            meters400.roll();
        } catch (Exception e) {
            fail(e);
        }
        assertEquals(1, meters400.getSlot());
        assertEquals(2, meters400.getSetDices());

        int sumMeters400 = 0;
        for (Dice dice : meters400.getDices()) {
            sumMeters400 += dice.getValue();
        }
        try {
            meters400.roll();
        } catch (Exception e) {
            fail(e);
        }
        assertEquals(2, meters400.getSlot());
        assertEquals(2, meters400.getSetDices());
        int dicesPreviousSumMeters400 = 0;
        try {
            for (List<Integer> dice : meters400.getSetPreviousDices()) {
                for(Integer i : dice) {
                    dicesPreviousSumMeters400 += i;
                }
            }
            assertEquals(sumMeters400, dicesPreviousSumMeters400);
        } catch (Exception e) {
            fail(e);
        }


        Discipline meters1500 = new Meters1500();
        try {
            meters1500.roll();
        } catch (Exception e) {
            fail(e);
        }
        assertEquals(1, meters1500.getSlot());
        assertEquals(1, meters1500.getSetDices());

        int sumMeters1500 = 0;
        for (Dice dice : meters1500.getDices()) {
            sumMeters1500 += dice.getValue();
        }
        try {
            meters1500.roll();
        } catch (Exception e) {
            fail(e);
        }
        assertEquals(2, meters1500.getSlot());
        assertEquals(1, meters1500.getSetDices());
        int dicesPreviousSumMeters1500 = 0;
        try {
            for (List<Integer> dice : meters1500.getSetPreviousDices()) {
                for(Integer i : dice) {
                    dicesPreviousSumMeters1500 += i;
                }
            }
            assertEquals(sumMeters1500, dicesPreviousSumMeters1500);
        } catch (Exception e) {
            fail(e);
        }
    }

    @Test
    void testReset() {
        Discipline highJump = new HighJump();
        try {
            highJump.roll();
        } catch (Exception e) {
            fail(e);
        }
        assertEquals(1, highJump.getSlot());
        assertEquals(5, highJump.getSetDices());
        assertEquals(0, highJump.getCurrentAttempt());
        try {
            highJump.reRoll();
        } catch (Exception e) {
            fail(e);
        }
        assertEquals(1, highJump.getCurrentAttempt());
        try {
            highJump.reRoll();
        } catch (Exception e) {
            fail(e);
        }
        assertEquals(2, highJump.getCurrentAttempt());
        assertEquals(1, highJump.getSlot());
        assertEquals(5, highJump.getSetDices());

        highJump.reset();
        assertEquals(0, highJump.getSlot());
        assertEquals(0, highJump.getCurrentAttempt());
        for (Dice dice : highJump.getDices()) {
            assertEquals(0, dice.getValue());
        }


        Discipline metersObstacle110 = new MetersObstacle110();
        try {
            metersObstacle110.roll();
        } catch (Exception e) {
            fail(e);
        }
        assertEquals(1, metersObstacle110.getSlot());
        assertEquals(5, metersObstacle110.getSetDices());
        assertEquals(0, metersObstacle110.getCurrentAttempt());
        try {
            metersObstacle110.reRoll();
        } catch (Exception e) {
            fail(e);
        }
        assertEquals(1, metersObstacle110.getCurrentAttempt());
        try {
            metersObstacle110.reRoll();
        } catch (Exception e) {
            fail(e);
        }
        assertEquals(2, metersObstacle110.getCurrentAttempt());
        assertEquals(1, metersObstacle110.getSlot());
        assertEquals(5, metersObstacle110.getSetDices());

        metersObstacle110.reset();
        assertEquals(0, metersObstacle110.getSlot());
        assertEquals(0, metersObstacle110.getCurrentAttempt());
        for (Dice dice : metersObstacle110.getDices()) {
            assertEquals(0, dice.getValue());
        }


        Discipline meters100 = new Meters100();
        try {
            meters100.roll();
        } catch (Exception e) {
            fail(e);
        }
        assertEquals(1, meters100.getSlot());
        assertEquals(4, meters100.getSetDices());
        assertEquals(0, meters100.getCurrentAttempt());
        try {
            meters100.reRoll();
        } catch (Exception e) {
            fail(e);
        }
        assertEquals(1, meters100.getCurrentAttempt());
        try {
            meters100.reRoll();
        } catch (Exception e) {
            fail(e);
        }
        assertEquals(2, meters100.getCurrentAttempt());
        assertEquals(1, meters100.getSlot());
        try {
            meters100.roll();
        } catch (Exception e) {
            fail(e);
        }
        assertEquals(2, meters100.getSlot());
        assertEquals(4, meters100.getSetDices());
        try {
            meters100.reRoll();
        } catch (Exception e) {
            fail(e);
        }
        assertEquals(3, meters100.getCurrentAttempt());
        assertEquals(2, meters100.getSlot());
        assertEquals(4, meters100.getSetDices());

        meters100.reset();
        assertEquals(0, meters100.getSlot());
        assertEquals(0, meters100.getCurrentAttempt());
        for (Dice dice : meters100.getDices()) {
            assertEquals(0, dice.getValue());
        }


        Discipline meters400 = new Meters400();
        try {
            meters400.roll();
        } catch (Exception e) {
            fail(e);
        }
        assertEquals(1, meters400.getSlot());
        assertEquals(2, meters400.getSetDices());
        assertEquals(0, meters400.getCurrentAttempt());
        try {
            meters400.reRoll();
        } catch (Exception e) {
            fail(e);
        }
        assertEquals(1, meters400.getCurrentAttempt());
        assertEquals(1, meters400.getSlot());
        assertEquals(2, meters400.getSetDices());
        try {
            meters400.roll();
        } catch (Exception e) {
            fail(e);
        }
        assertEquals(2, meters400.getSlot());
        assertEquals(2, meters400.getSetDices());
        assertEquals(1, meters400.getCurrentAttempt());
        try {
            meters400.reRoll();
        } catch (Exception e) {
            fail(e);
        }
        assertEquals(2, meters400.getCurrentAttempt());
        assertEquals(2, meters400.getSlot());
        assertEquals(2, meters400.getSetDices());
        try {
            meters400.roll();
        } catch (Exception e) {
            fail(e);
        }
        assertEquals(3, meters400.getSlot());
        assertEquals(2, meters400.getSetDices());
        assertEquals(2, meters400.getCurrentAttempt());
        try {
            meters400.reRoll();
        } catch (Exception e) {
            fail(e);
        }
        assertEquals(3, meters400.getCurrentAttempt());
        assertEquals(3, meters400.getSlot());
        assertEquals(2, meters400.getSetDices());
        try {
            meters400.roll();
        } catch (Exception e) {
            fail(e);
        }
        assertEquals(4, meters400.getSlot());
        assertEquals(2, meters400.getSetDices());
        assertEquals(3, meters400.getCurrentAttempt());
        try {
            meters400.reRoll();
        } catch (Exception e) {
            fail(e);
        }
        assertEquals(4, meters400.getCurrentAttempt());
        assertEquals(4, meters400.getSlot());
        assertEquals(2, meters400.getSetDices());

        meters400.reset();
        assertEquals(0, meters400.getSlot());
        assertEquals(0, meters400.getCurrentAttempt());
        for (Dice dice : meters400.getDices()) {
            assertEquals(0, dice.getValue());
        }


        Discipline meters1500 = new Meters1500();
        try {
            meters1500.roll();
        } catch (Exception e) {
            fail(e);
        }
        assertEquals(1, meters1500.getSlot());
        assertEquals(1, meters1500.getSetDices());
        try {
            meters1500.reRoll();
        } catch (Exception e) {
            fail(e);
        }
        assertEquals(1, meters1500.getCurrentAttempt());
        assertEquals(1, meters1500.getSlot());
        assertEquals(1, meters1500.getSetDices());
        try {
            meters1500.roll();
        } catch (Exception e) {
            fail(e);
        }
        assertEquals(2, meters1500.getSlot());
        assertEquals(1, meters1500.getSetDices());
        try {
            meters1500.roll();
        } catch (Exception e) {
            fail(e);
        }
        assertEquals(3, meters1500.getSlot());
        assertEquals(1, meters1500.getSetDices());
        try {
            meters1500.reRoll();
        } catch (Exception e) {
            fail(e);
        }
        assertEquals(2, meters1500.getCurrentAttempt());
        assertEquals(3, meters1500.getSlot());
        assertEquals(1, meters1500.getSetDices());
        try {
            meters1500.roll();
        } catch (Exception e) {
            fail(e);
        }
        assertEquals(4, meters1500.getSlot());
        assertEquals(1, meters1500.getSetDices());
        try {
            meters1500.reRoll();
        } catch (Exception e) {
            fail(e);
        }
        assertEquals(3, meters1500.getCurrentAttempt());
        assertEquals(4, meters1500.getSlot());
        assertEquals(1, meters1500.getSetDices());
        try {
            meters1500.roll();
        } catch (Exception e) {
            fail(e);
        }
        assertEquals(5, meters1500.getSlot());
        assertEquals(1, meters1500.getSetDices());
        try {
            meters1500.roll();
        } catch (Exception e) {
            fail(e);
        }
        assertEquals(6, meters1500.getSlot());
        assertEquals(1, meters1500.getSetDices());
        try {
            meters1500.reRoll();
        } catch (Exception e) {
            fail(e);
        }
        assertEquals(4, meters1500.getCurrentAttempt());
        assertEquals(6, meters1500.getSlot());
        assertEquals(1, meters1500.getSetDices());
        try {
            meters1500.roll();
        } catch (Exception e) {
            fail(e);
        }
        assertEquals(7, meters1500.getSlot());
        assertEquals(1, meters1500.getSetDices());
        try {
            meters1500.roll();
        } catch (Exception e) {
            fail(e);
        }
        assertEquals(8, meters1500.getSlot());
        assertEquals(1, meters1500.getSetDices());
        try {
            meters1500.reRoll();
        } catch (Exception e) {
            fail(e);
        }
        assertEquals(5, meters1500.getCurrentAttempt());
        assertEquals(8, meters1500.getSlot());
        assertEquals(1, meters1500.getSetDices());

        meters1500.reset();
        assertEquals(0, meters1500.getSlot());
        assertEquals(0, meters1500.getCurrentAttempt());
        for (Dice dice : meters1500.getDices()) {
            assertEquals(0, dice.getValue());
        }
    }
}