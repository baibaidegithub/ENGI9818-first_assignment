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

    final char[][] maze2 = new char[][]{
            {' ', ' ', ' ', ' ', ' '},
            {' ', 'E', 'W', 'W', ' '},
            {' ', 'W', ' ', ' ', ' '},
            {' ', ' ', ' ', ' ', ' '},
    };

    final char[][] maze3 = new char[][]{
            {' '}
    };

    final char[][] maze4 = new char[][]{
            {}
    };

    final char[][] maze5 = new char[][]{
            {' ', ' ', ' ', ' ', ' '},
            {' ', ' ', 'W', 'W', ' '},
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

    @Test
    public void testCase2() {
        final List<Pair<Integer, Integer>> result = escape.escape(maze2);
        assertEquals(true, isMazeNull(result, maze2));
    }

    /**
     * Check whether an empty list is returned when the maze is empty or lacks an entrance or exit.
     *
     * @param path Requires a continuous collection of valid two-dimensional arrays, or an empty list if there is an empty maze.
     * @param maze Requires a valid two-dimensional array of type char.
     * @return The result must be a boolean type.
     * @ensures testPath() always return and no exception thrown, no side effect.
     */
    private boolean isMazeNull(List<Pair<Integer, Integer>> path, char[][] maze) {
        int countE = 0;
        int countX = 0;

        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                if (maze[i][j] == 'E') {
                    countE++;
                }
                if (maze[i][j] == 'X') {
                    countX++;
                }
            }
        }
        if (countE != 1 || countX != 1) {
            if (path.size() == 0) {
                return true;
            } else {
                return false;
            }
        } else {
            return testPath(path, maze);
        }
    }

    @Test
    public void testCase3() {
        final List<Pair<Integer, Integer>> result = escape.escape(maze1);
        assertEquals(true, isContinue(result, maze1));
    }

    /**
     * Test whether each step is continuous
     *
     * @param path Requires a continuous collection of valid two-dimensional arrays, or an empty list if there is an empty maze.
     * @param maze Requires a valid two-dimensional array of type char.
     * @return The result must be a boolean type.
     * @ensures Always return and no exception thrown, no side effect.
     */
    private boolean isContinue(List<Pair<Integer, Integer>> path, char[][] maze) {
        boolean flag = true;
        while (path.size() > 1) {
            final Pair<Integer, Integer> temp = path.get(0);
            int pre = temp.first + temp.second;
            final Pair<Integer, Integer> coordinate = path.remove(0);
            final Pair<Integer, Integer> present = path.get(0);
            int now = present.first + present.second;
            if ((pre - now) != 1 && (pre - now) != -1) {
                flag = false;
            }
        }
        if (flag) {
            return true;
        } else {
            return false;
        }
    }

    @Test
    public void testCase4() {
        final List<Pair<Integer, Integer>> result = escape.escape(maze1);
        assertEquals(true, isIndexOut(result, maze1));
    }

    /**
     * Determine whether the value of path exceeds the maze size
     *
     * @param path Requires a continuous collection of valid two-dimensional arrays, or an empty list if there is an empty maze.
     * @param maze Requires a valid two-dimensional array of type char.
     * @return The result must be a boolean type.
     * @ensures Always return and no exception thrown, no side effect.
     */
    private boolean isIndexOut(List<Pair<Integer, Integer>> path, char[][] maze) {
        boolean flag = true;
        while (path.size() > 1) {
            final Pair<Integer, Integer> coordinate = path.remove(0);
            if (coordinate.first < 0 || coordinate.second < 0) {
                flag = false;
            }
            if (coordinate.first > maze.length || coordinate.second > maze[0].length) {
                flag = false;
            }
        }
        if (flag) {
            return true;
        } else {
            return false;
        }
    }
}



