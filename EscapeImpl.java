import java.util.ArrayList;
import java.util.List;

public class EscapeImpl implements Escape {
    /**
     * Using DFS find a path from the entrance to the exit in a maze.
     *
     * @param maze Requires a valid Two-dimensional array set of type char.
     * @return The result must be a continuous collection of valid two-dimensional arrays, or an empty collection if there is no path.
     * @ensures myPerfectRecursionMethod() always return and no exception thrown, no side effect.
     */
    @Override
    public List<Pair<Integer, Integer>> escape(final char[][] maze) {
        int[] start;
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                if (maze[i][j] == 'E') {
                    start = new int[]{i, j};
                    return myPerfectRecursionMethod(maze, start);
                }
            }
        }
        return new ArrayList<>();
    }

    /**
     * DFS function use recursion.
     *
     * @param maze  Requires a valid two-dimensional array of type char.
     * @param start Requires an array of type int is required and cannot be empty.
     * @return The result must be a continuous collection of valid two-dimensional arrays, or an empty collection if there is no path.
     * @ensures dfs() always return and no exception thrown, no side effect.
     */
    private List<Pair<Integer, Integer>> myPerfectRecursionMethod(char[][] maze, int[] start) {
        final List<Pair<Integer, Integer>> result = new ArrayList<>();
        //visit or not?
        boolean[][] visit = new boolean[maze.length][maze[0].length];
        dfs(maze, start, visit, result);
        return result;
    }


    /**
     * Recursive field
     *
     * @param maze   Requires a valid two-dimensional array of type char.
     * @param start  Requires an array of type int is required and cannot be empty.
     * @param visit  Requires a two-dimensional array of type boolean.
     * @param result A list of custom types.
     * @return result list will be returned.
     * @ensures Always return and no exception thrown, no side effect.
     */
    public boolean dfs(char[][] maze, int[] start, boolean[][] visit, List<Pair<Integer, Integer>> result) {
        if (maze[start[0]][start[1]] == 'X') {
            result.add(new Pair<>(start[0], start[1]));
            return true;
        }
        visit[start[0]][start[1]] = true;
        //right
        if (0 <= start[0] && start[0] < maze.length && 0 <= start[1] + 1 && start[1] + 1 < maze[0].length) {
            if (maze[start[0]][start[1] + 1] != 'W' && visit[start[0]][start[1] + 1] == false) {
                visit[start[0]][start[1] + 1] = true;
                if (dfs(maze, new int[]{start[0], start[1] + 1}, visit, result)) {
                    result.add(new Pair<>(start[0], start[1]));
                    return true;
                }
            }
        }
        //down
        if (0 <= start[0] + 1 && start[0] + 1 < maze.length && 0 <= start[1] && start[1] < maze[0].length) {
            if (maze[start[0] + 1][start[1]] != 'W' && visit[start[0] + 1][start[1]] == false) {
                visit[start[0] + 1][start[1]] = true;
                if (dfs(maze, new int[]{start[0] + 1, start[1]}, visit, result)) {
                    result.add(new Pair<>(start[0], start[1]));
                    return true;
                }
            }
        }
        //left
        if (0 <= start[0] && start[0] < maze.length && 0 <= start[1] - 1 && start[1] - 1 < maze[0].length) {
            if (maze[start[0]][start[1] - 1] != 'W' && visit[start[0]][start[1] - 1] == false) {
                visit[start[0]][start[1] - 1] = true;
                if (dfs(maze, new int[]{start[0], start[1] - 1}, visit, result)) {
                    result.add(new Pair<>(start[0], start[1]));
                    return true;
                }
            }
        }
        //up
        if (0 <= start[0] - 1 && start[0] - 1 < maze.length && 0 <= start[1] && start[1] < maze[0].length) {
            if (maze[start[0] - 1][start[1]] != 'W' && visit[start[0] - 1][start[1]] == false) {
                visit[start[0] - 1][start[1]] = true;
                if (dfs(maze, new int[]{start[0] - 1, start[1]}, visit, result)) {
                    result.add(new Pair<>(start[0], start[1]));
                    return true;
                }
            }
        }
        return false;
    }
}
