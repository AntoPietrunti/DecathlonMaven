package it.unimol.decathlon.app;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class DecathlonTest {
    @Test
    public void testStart() {
        Player player0 = new Player("0");
        Player player1 = new Player("1");
        Player player2 = new Player("2");
        Player player3 = new Player("3");
        List<Player> players = new ArrayList<Player>();
        players.add(player0);
        players.add(player1);
        players.add(player2);
        players.add(player3);
        Decathlon decathlon = new Decathlon(players);

        assertEquals(player0, decathlon.getCurrentPlayer());
        assertEquals("400 Meters", decathlon.getCurrentDiscipline().getName());
    }

    @Test
    public void testNextTurn() {
        Player player0 = new Player("0");
        Player player1 = new Player("1");
        Player player2 = new Player("2");
        Player player3 = new Player("3");
        List<Player> players = new ArrayList<Player>();
        players.add(player0);
        players.add(player1);
        players.add(player2);
        players.add(player3);
        Decathlon decathlon = new Decathlon(players);

        assertEquals(player0, decathlon.getCurrentPlayer());
        decathlon.nextTurn();
        assertEquals(player1, decathlon.getCurrentPlayer());
        decathlon.nextTurn();
        assertEquals(player2, decathlon.getCurrentPlayer());
        decathlon.nextTurn();
        assertEquals(player3, decathlon.getCurrentPlayer());
        decathlon.nextTurn();
        assertEquals(player0, decathlon.getCurrentPlayer());
    }

    @Test
    public void testNextDiscipline() {
        Player player0 = new Player("0");
        Player player1 = new Player("1");
        Player player2 = new Player("2");
        Player player3 = new Player("3");
        List<Player> players = new ArrayList<Player>();
        players.add(player0);
        players.add(player1);
        players.add(player2);
        players.add(player3);
        Decathlon decathlon = new Decathlon(players);

        assertEquals("400 Meters", decathlon.getCurrentDiscipline().getName());
        decathlon.nextDiscipline();
        assertEquals("100 Meters", decathlon.getCurrentDiscipline().getName());
        decathlon.nextDiscipline();
        assertEquals("1500 Meters", decathlon.getCurrentDiscipline().getName());
        decathlon.nextDiscipline();
        assertEquals("110 Meters Obstacle", decathlon.getCurrentDiscipline().getName());
        decathlon.nextDiscipline();
        assertEquals("High Jump", decathlon.getCurrentDiscipline().getName());
    }

    @Test
    public void testGetDiscipline() {
        Decathlon decathlon = new Decathlon();
        Discipline found = null;

        try {
            found = decathlon.getDiscipline("High Jump");
        } catch (Exception e) {
            fail(e);
        }
        assertEquals("High Jump", found.getName());

        try {
            found = decathlon.getDiscipline("100 Meters");
        } catch (Exception e) {
            fail(e);
        }
        assertEquals("100 Meters", found.getName());

        try {
            found = decathlon.getDiscipline("400 Meters");
        } catch (Exception e) {
            fail(e);
        }
        assertEquals("400 Meters", found.getName());

        try {
            found = decathlon.getDiscipline("1500 Meters");
        } catch (Exception e) {
            fail(e);
        }
        assertEquals("1500 Meters", found.getName());

        try {
            found = decathlon.getDiscipline("110 Meters Obstacle");
        } catch (Exception e) {
            fail(e);
        }
        assertEquals("110 Meters Obstacle", found.getName());

        try {
            found = decathlon.getDiscipline("100 Metri");
            fail("Exception expected.");
        } catch (Exception e) {
            assertTrue(e instanceof NoSuchElementException);
        }
    }
}