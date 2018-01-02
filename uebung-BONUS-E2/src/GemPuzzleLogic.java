import java.util.Random;

public class GemPuzzleLogic {

    private static int length = 0;

    public static Direction[] autoComplete(int[][] matrix) {
        Direction[] dirs = new Direction[length];

        if(!isGameOver(matrix)) {
            for(Direction dir: Direction.values()) {
                Direction[] n = new Direction[dirs.length+1];
                n[0] = dir;
                return n;
            }
        }

        length = 0;
        return dirs;
    }

    private int getNextFree(Direction[] dirs) {
        int next = 0;
        for(int i = 0; i < dirs.length; i++) {
            if(dirs[i] == null) {
                next = i;
            }
        }

        return next;
    }


    public static int[][] init(int rows, int cols) {
        int[][] field = new int[rows][cols];
        int[] used = new int[(rows * cols) - 1];
        for(int i = 0; i < used.length; i++) {
            used[i] = -1;
        }

        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                int expected = 1 + (cols * i + j );
                field[i][j] = expected;

            }
        }

        field[rows - 1][cols - 1] = 0;

        return field;

    }

    public static boolean isGameOver(int[][] matrix) {
        boolean isGameOver = true;
        int rows = matrix.length;
        int cols = matrix[0].length;
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                int expected = 1 + (cols * i + j );
                if(matrix[i][j] != expected && (i != (rows - 1) && (j != cols -1) )) {
                    isGameOver = false;
                    break;
                } else if((i == (rows - 1) && (j == cols -1) ) && matrix[i][j] != 0) {
                    isGameOver = false;
                    break;
                }
            }
        }
        return isGameOver;
    }

    public static boolean moveGap(int[][] matrix, Direction direction) {

        if(isGameOver(matrix)) {
            return false;
        }
        int rows = matrix.length;
        int cols = matrix[0].length;
        int zI = 0;
        int zJ = 0;
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                if(matrix[i][j] == 0) {
                    zI = i;
                    zJ = j;
                    break;

                }
            }
        }

        int dir = direction.ordinal();
        boolean moved = false;
        switch(dir) {
            case 0:
                if(zI > 0) {
                    matrix[zI][zJ] = matrix[zI-1][zJ];
                    matrix[zI-1][zJ] = 0;
                    moved = true;
                }
                break;

            case 1:
                if(zI < rows - 1) {
                    matrix[zI][zJ] = matrix[zI+1][zJ];
                    matrix[zI+1][zJ] = 0;
                    moved = true;
                }
                break;

            case 2:
                if(zJ > 0) {
                    matrix[zI][zJ] = matrix[zI][zJ-1];
                    matrix[zI][zJ-1] = 0;
                    moved = true;
                }

                break;

            case 3:
                if(zJ < cols - 1) {
                    matrix[zI][zJ] = matrix[zI][zJ+1];
                    matrix[zI][zJ+1] = 0;
                    moved = true;
                }
                break;
        }

        return moved;
    }

    public static void shuffle(int[][] matrix) {

        Random rand = new Random();


        int rows = matrix.length;
        int cols = matrix[0].length;
        int zI = 0;
        int zJ = 0;
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                if(matrix[i][j] == 0) {
                    zI = i;
                    zJ = j;
                    break;

                }
            }
        }
        boolean shuffled = false;
        do {
            int randDir = rand.nextInt(4);
            switch (randDir) {
                case 0:
                    if (zI > 0) {
                        matrix[zI][zJ] = matrix[zI - 1][zJ];
                        matrix[zI - 1][zJ] = 0;
                        shuffled = true;
                    } else {
                        shuffled = false;
                    }
                    break;

                case 1:
                    if (zI < rows - 1) {
                        matrix[zI][zJ] = matrix[zI + 1][zJ];
                        matrix[zI + 1][zJ] = 0;
                        shuffled = true;
                    } else {
                        shuffled = false;
                    }
                    break;

                case 2:
                    if (zJ > 0) {
                        matrix[zI][zJ] = matrix[zI][zJ - 1];
                        matrix[zI][zJ - 1] = 0;
                        shuffled = true;
                    } else {
                        shuffled = false;
                    }

                    break;

                case 3:
                    if (zJ < cols - 1) {
                        matrix[zI][zJ] = matrix[zI][zJ + 1];
                        matrix[zI][zJ + 1] = 0;
                        shuffled = true;
                    } else {
                        shuffled = false;
                    }
                    break;
                default:
                    shuffled = false;
            }

        } while(!shuffled);

    }
}
