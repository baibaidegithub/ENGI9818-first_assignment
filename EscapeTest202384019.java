import static org.junit.Assert.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.Test;


public class EscapeTest202384019 {
    private final Escape escape = new EscapeImpl();

    final char[][] maze1 = new char[][]{
            {' ', ' ', ' ', ' ', ' '},
            {' ', 'E', 'W', 'W', ' '},
            {' ', 'W', 'X', ' ', ' '},
            {' ', ' ', ' ', ' ', ' '},
    };

    @Test
    public void testCase1() {
        final List<Pair<Integer, Integer>> result = escape.escape(maze1);
        assertEquals(true, testPath(result, maze1));
    }

    private boolean testPath(List<Pair<Integer, Integer>> path, char[][] maze) {
        final Pair<Integer, Integer> exitCoordinate = path.remove(0);
        if (maze[exitCoordinate.first][exitCoordinate.second] != 'X') {
            return false;
        }
        while (path.size() > 1) {
            final Pair<Integer, Integer> coordinate = path.remove(0);
            if (maze[coordinate.first][coordinate.second] != ' ') {
                return false;
            }
        }
        final Pair<Integer, Integer> entranceCoordinate = path.remove(0);
        if (maze[entranceCoordinate.first][entranceCoordinate.second] != 'E') {
            return false;
        }
        return true;
    }
}
