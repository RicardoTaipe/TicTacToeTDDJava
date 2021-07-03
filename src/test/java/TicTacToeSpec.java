import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

class TicTacToeSpec {
    private TicTacToe ticTacToe;

    @BeforeEach
    public final void before() {
        ticTacToe = new TicTacToe();
    }

    @Test
    public void whenXOutsideBoardThenRuntimeException() {
        //Junit5
        assertThrows(RuntimeException.class, () -> ticTacToe.play(5, 2));
        //AssertJ
        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> ticTacToe.play(5, 2))
                .withMessage("X is outside the board");
    }

    @Test
    public void whenYOutsideBoardThenRuntimeException() {
        assertThrows(RuntimeException.class, () -> ticTacToe.play(2, 5));

        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> ticTacToe.play(2, 5))
                .withMessage("X is outside the board");
    }

    @Test
    public void whenOccupiedPlaceThenRuntimeException() {
        ticTacToe.play(2, 1);
        assertThrows(RuntimeException.class, () -> ticTacToe.play(2, 1));

        assertThatExceptionOfType(RuntimeException.class)
                .isThrownBy(() -> ticTacToe.play(2, 1))
                .withMessage("Box is occupied");
    }

    @Test
    public void givenFirstTurnWhenNextPlayerThenX() {

        assertEquals('X', ticTacToe.nextPlayer());

        assertThat(ticTacToe.nextPlayer()).isEqualTo('X');
    }

    @Test
    public void givenLastTurnWasXWhenNextPlayerThen0() {
        ticTacToe.play(1, 1);
        assertEquals('O', ticTacToe.nextPlayer());
        assertThat(ticTacToe.nextPlayer()).isEqualTo('O');
    }

    @Test
    public void whenPlayThenNoWinner() {
        String actual = ticTacToe.play(1, 1);
        assertEquals("No winner", actual);
        assertThat(actual).isEqualTo("No winner");
    }

    @Test
    public void whenPlayAndWholeHorizontalLineThenWinner() {
        ticTacToe.play(1, 1);//X
        ticTacToe.play(1, 2);//O
        ticTacToe.play(2, 1);//X
        ticTacToe.play(2, 2);//0
        String actual = ticTacToe.play(3, 1); //X
        assertEquals("X is the winner", actual);

        assertThat(actual).isEqualTo("X is the winner");
    }

    @Test
    public void whenPlayAndWholeVerticalLineThenWinner() {
        ticTacToe.play(2, 1); // X
        ticTacToe.play(1, 1); // O
        ticTacToe.play(3, 1); // X
        ticTacToe.play(1, 2); // O
        ticTacToe.play(2, 2); // X
        String actual = ticTacToe.play(1, 3); // O
        assertEquals("O is the winner", actual);

        assertThat(actual).isEqualTo("O is the winner");
    }

    @Test
    public void whenPlayAndTopBottomLine() {
        ticTacToe.play(1, 1); // X
        ticTacToe.play(1, 2); // O
        ticTacToe.play(2, 2); // X
        ticTacToe.play(1, 3); // O
        String actual = ticTacToe.play(3, 3); // X
        assertEquals("X is the winner", actual);

        assertThat(actual).isEqualTo("X is the winner");
    }

    @Test
    public void whenPlayAndBottomTopDiagonalLineThenWinner() {
        ticTacToe.play(1, 3); // X
        ticTacToe.play(1, 1); // O
        ticTacToe.play(2, 2); // X
        ticTacToe.play(1, 2); // O
        String actual = ticTacToe.play(3, 1); // X
        assertEquals("X is the winner", actual);

        assertThat(actual).isEqualTo("X is the winner");
    }

    @Test
    public void whenAllBoxesAreFilledThenDraw() {
        ticTacToe.play(1, 1);
        ticTacToe.play(1, 2);
        ticTacToe.play(1, 3);
        ticTacToe.play(2, 1);
        ticTacToe.play(2, 3);
        ticTacToe.play(2, 2);
        ticTacToe.play(3, 1);
        ticTacToe.play(3, 3);
        String actual = ticTacToe.play(3, 2);
        assertEquals("The result is draw", actual);

        assertThat(actual).isEqualTo("The result is draw");
    }
}