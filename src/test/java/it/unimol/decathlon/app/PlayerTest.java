package it.unimol.decathlon.app;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerTest {

    @Test
    void testAddScore() {
        Player player = new Player("Player");

        Discipline highJump = new HighJump();
        Discipline metersObstacle110 = new MetersObstacle110();
        Discipline meters100 = new Meters100();
        Discipline meters400 = new Meters400();
        Discipline meters1500 = new Meters1500();

        player.addScore(26, highJump.getName());
        assertEquals(26, player.getDisciplineScore(highJump.getName()));

        player.addScore(27, metersObstacle110.getName());
        assertEquals(27, player.getDisciplineScore(metersObstacle110.getName()));

        player.addScore(28, meters100.getName());
        assertEquals(28, player.getDisciplineScore(meters100.getName()));

        player.addScore(29, meters400.getName());
        assertEquals(29, player.getDisciplineScore(meters400.getName()));

        player.addScore(30, meters1500.getName());
        assertEquals(30, player.getDisciplineScore(meters1500.getName()));
    }

    @Test
    void testIsDisciplineExist() {
        Player player = new Player("Player");

        Discipline highJump = new HighJump();
        Discipline metersObstacle110 = new MetersObstacle110();
        Discipline meters100 = new Meters100();
        Discipline meters400 = new Meters400();
        Discipline meters1500 = new Meters1500();

        assertFalse(player.isDisciplineExist(highJump.getName()));
        assertFalse(player.isDisciplineExist(metersObstacle110.getName()));
        assertFalse(player.isDisciplineExist(meters100.getName()));
        assertFalse(player.isDisciplineExist(meters400.getName()));
        assertFalse(player.isDisciplineExist(meters1500.getName()));

        player.addScore(26, highJump.getName());
        assertTrue(player.isDisciplineExist(highJump.getName()));

        player.addScore(27, metersObstacle110.getName());
        assertTrue(player.isDisciplineExist(metersObstacle110.getName()));

        player.addScore(28, meters100.getName());
        assertTrue(player.isDisciplineExist(meters100.getName()));

        player.addScore(29, meters400.getName());
        assertTrue(player.isDisciplineExist(meters400.getName()));

        player.addScore(30, meters1500.getName());
        assertTrue(player.isDisciplineExist(meters1500.getName()));
    }

    @Test
    void testResetScore() {
        Player player = new Player("Player");

        Discipline highJump = new HighJump();
        Discipline metersObstacle110 = new MetersObstacle110();
        Discipline meters100 = new Meters100();
        Discipline meters400 = new Meters400();
        Discipline meters1500 = new Meters1500();

        player.addScore(26, highJump.getName());
        player.addScore(27, metersObstacle110.getName());
        player.addScore(28, meters100.getName());
        player.addScore(29, meters400.getName());
        player.addScore(30, meters1500.getName());

        assertEquals(5, player.getScores().size());
        player.resetScore();

        assertEquals(0, player.getScores().size());
    }
}