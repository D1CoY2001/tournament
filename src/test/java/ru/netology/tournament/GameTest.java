package ru.netology.tournament;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GameTest {

    private Game game;

    @BeforeEach
    public void setUp() {
        game = new Game();

        game.register(new Player(1, "Alice", 10));
        game.register(new Player(2, "Bob", 20));
        game.register(new Player(3, "Charlie", 10));
    }

    @Test
    public void shouldReturnOneWhenFirstPlayerIsStronger() {
        int result = game.round("Bob", "Alice");

        assertEquals(1, result);
    }

    @Test
    public void shouldReturnTwoWhenSecondPlayerIsStronger() {
        int result = game.round("Alice", "Bob");

        assertEquals(2, result);
    }

    @Test
    public void shouldReturnZeroWhenStrengthIsEqual() {
        int result = game.round("Alice", "Charlie");

        assertEquals(0, result);
    }

    @Test
    public void shouldThrowWhenFirstPlayerIsNotRegistered() {
        NotRegisteredException exception = assertThrows(
                NotRegisteredException.class,
                () -> game.round("Unknown", "Alice")
        );

        assertEquals(
                "Игрок с именем Unknown не зарегистрирован",
                exception.getMessage()
        );
    }

    @Test
    public void shouldThrowWhenSecondPlayerIsNotRegistered() {
        NotRegisteredException exception = assertThrows(
                NotRegisteredException.class,
                () -> game.round("Alice", "Unknown")
        );

        assertEquals(
                "Игрок с именем Unknown не зарегистрирован",
                exception.getMessage()
        );
    }

    @Test
    public void shouldThrowWhenBothPlayersAreNotRegistered() {
        NotRegisteredException exception = assertThrows(
                NotRegisteredException.class,
                () -> game.round("Unknown1", "Unknown2")
        );

        assertEquals(
                "Игрок с именем Unknown1 не зарегистрирован",
                exception.getMessage()
        );
    }

    @Test
    public void shouldThrowWhenThereAreNoRegisteredPlayers() {
        Game emptyGame = new Game();

        NotRegisteredException exception = assertThrows(
                NotRegisteredException.class,
                () -> emptyGame.round("Unknown1", "Unknown2")
        );

        assertEquals(
                "Игрок с именем Unknown1 не зарегистрирован",
                exception.getMessage()
        );
    }
}