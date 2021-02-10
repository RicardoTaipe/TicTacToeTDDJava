import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TicTacToeSpec {
    private TicTacToe ticTacToe;

    @BeforeEach
    public final void before() {
        ticTacToe = new TicTacToe();
    }

    @Test
    public void whenXOutsideBoardThenRuntimeException() {
        assertThrows(RuntimeException.class, () -> ticTacToe.play(5, 2));
    }

    @Test
    public void whenYOutsideBoardThenRuntimeException() {
        assertThrows(RuntimeException.class, () -> ticTacToe.play(2, 5));
    }

    @Test
    public void whenOccupiedPlaceThenRuntimeException() {
        ticTacToe.play(2, 1);
        assertThrows(RuntimeException.class, () -> ticTacToe.play(2, 1));
    }

    @Test
    public void givenFirstTurnWhenNextPlayerThenX() {
        assertEquals('X', ticTacToe.nextPlayer());
    }

    @Test
    public void givenLastTurnWasXWhenNextPlayerThen0(){
        ticTacToe.play(1,1);
        assertEquals('O', ticTacToe.nextPlayer());
    }

    @Test
    public void whenPlayThenNoWinner(){
        String actual = ticTacToe.play(1,1);
        assertEquals("No winner", actual);
    }

    @Test
    public void whenPlayAndWholeHorizontalLineThenWinner(){
        ticTacToe.play(1,1);//X
        ticTacToe.play(1,2);//O
        ticTacToe.play(2,1);//X
        ticTacToe.play(2,2);//0
        String actual = ticTacToe.play(3,1); //X
        assertEquals("X is the winner", actual);
    }
}