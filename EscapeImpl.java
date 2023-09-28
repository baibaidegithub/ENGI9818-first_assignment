import java.util.ArrayList;
import java.util.List;


public class EscapeImpl implements Escape {
    /**
     * Find a path from the entrance to the exit in a maze.
     */
    @Override
    public List<Pair<Integer, Integer>> escape(final char[][] maze) {
        final List<Pair<Integer, Integer>> s = new ArrayList<>();
        boolean enter = false;
        boolean end = false;
        int[] start = new int[2];
        int[] destination = new int[2];
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[0].length; j++) {
                if (maze[i][j] == 'E') {
                    enter = true;
                    start = new int[]{i, j};
                    s.add(new Pair<>(i, j));


                    // found the entrance

                    // TODO: Your magic here...
                    // blah, blah, blah...
                }
                if (maze[i][j] == 'X') {
                    end = true;
                    destination = new int[]{i, j};
                }
            }
        }
        if (enter && end) {
            return myPerfectRecursionMethod(maze, start);
        }
        return new ArrayList<>();
    }

    private List<Pair<Integer, Integer>> myPerfectRecursionMethod(char[][] maze, int[] start) {
        final List<Pair<Integer, Integer>> result = new ArrayList<>();
        //有无访问过
        boolean[][] visit = new boolean[maze.length][maze[0].length];
        dfs(maze, start, visit, result);
        return result;
    }


    public boolean dfs(char[][] maze, int[] start, boolean[][] visit, List<Pair<Integer, Integer>> result) {
        if (maze[start[0]][start[1]] == 'X') {
            result.add(new Pair<>(start[0], start[1]));
            return true;
        }
        visit[start[0]][start[1]] = true;
        //顺时针试探
        //右
        if (0 <= start[0] && start[0] < maze.length && 0 <= start[1] + 1 && start[1] + 1 < maze[0].length) {
            if (maze[start[0]][start[1] + 1] != 'W' && visit[start[0]][start[1] + 1] == false) {
                visit[start[0]][start[1] + 1] = true;
                if (dfs(maze, new int[]{start[0], start[1] + 1}, visit, result)) {
                    result.add(new Pair<>(start[0], start[1]));
                    return true;
                }
            }
        }
        //下
        if (0 <= start[0] + 1 && start[0] + 1 < maze.length && 0 <= start[1] && start[1] < maze[0].length) {
            if (maze[start[0] + 1][start[1]] != 'W' && visit[start[0] + 1][start[1]] == false) {
                visit[start[0] + 1][start[1]] = true;
                if (dfs(maze, new int[]{start[0] + 1, start[1]}, visit, result)) {
                    result.add(new Pair<>(start[0], start[1]));
                    return true;
                }
            }
        }
        //左
        if (0 <= start[0] && start[0] < maze.length && 0 <= start[1] - 1 && start[1] - 1 < maze[0].length) {
            if (maze[start[0]][start[1] - 1] != 'W' && visit[start[0]][start[1] - 1] == false) {
                visit[start[0]][start[1] - 1] = true;
                if (dfs(maze, new int[]{start[0], start[1] - 1}, visit, result)) {
                    result.add(new Pair<>(start[0], start[1]));
                    return true;
                }
            }
        }
        //上
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
