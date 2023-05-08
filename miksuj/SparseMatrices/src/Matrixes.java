import java.util.Random;


public class Matrixes {

    public static double[][] matrixDenseRow(int size) {
        double[][] matrix = new double[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[i][j] = getRandomDouble();
            }
        }
        return matrix;
    }

    public static double[][] matrixDenseCol(int size) {
        double[][] matrix = new double[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrix[j][i] = getRandomDouble();
            }
        }
        return matrix;
    }

    public static double[][] matrixBandRow(int size, int band) {
        double[][] matrix2 = new double[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (Math.abs(i - j) <= band) {
                    matrix2[i][j] = getRandomDouble();
                }
            }
        }
        return matrix2;
    }

    public static double[][] matrixBandCol(int size, int band) {
        double[][] matrixA = new double[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (Math.abs(i - j) <= band) {
                    matrixA[j][i] = getRandomDouble();
                }
            }
        }
        return matrixA;
    }

    public static double[][] sparseRandomMatrixRow(int size, double density) {
        double[][] matrix = new double[size][size];
        int maxNonZeroElements = (int) Math.round(density * size);
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            matrix[i][i] = getRandomDouble();
            int numNonZeroElements = random.nextInt(maxNonZeroElements + 1);
            for (int j = 0; j < numNonZeroElements; j++) {
                int col = random.nextInt(size);
                if (col != i) {
                    matrix[i][col] = getRandomDouble();
                }
            }
        }
        return matrix;
    }

    public static double[][] sparseRandomMatrixCol(int size, double density) {
        double[][] matrix = new double[size][size];
        int maxNonZeroElements = (int) Math.round(density * size);
        Random random = new Random();
        for (int j = 0; j < size; j++) {
            matrix[j][j] = getRandomDouble();
            int numNonZeroElements = random.nextInt(maxNonZeroElements + 1);
            for (int i = 0; i < numNonZeroElements; i++) {
                int row = random.nextInt(size);
                if (row != j) {
                    matrix[row][j] = getRandomDouble();
                }
            }
        }
        return matrix;
    }

    public static double[] matrixB (int size) {
        double[] matrixB = new double[size];
        for (int i = 0; i < size; i++) {
            matrixB[i] = getRandomDouble();
        }
        return matrixB;
    }

    public static double[] multiplyRow(double[][] matrixA, double[] matrixX) {
        double[] matrixAX = new double[matrixA.length];
        for (int i = 0; i < matrixA.length; i++) {
            for (int j = 0; j < matrixA.length; j++) {
                matrixAX[i] += matrixA[i][j] * matrixX[j];
            }
        }

        return matrixAX;
    }

    public static double[] multiplyCol(double[][] matrixA, double[] matrixX) {
        double[] matrixAX = new double[matrixA.length];
        for (int i = 0; i < matrixA.length; i++) {
            for (int j = 0; j < matrixA.length; j++) {
                matrixAX[i] += matrixA[j][i] * matrixX[j];
            }
        }
        return matrixAX;
    }

    public static double calcErr(double[] matrixAX, double[] matrixB) {
        double err = 0;
        for (int i = 0; i < matrixAX.length; i++) {
            err += Math.abs(matrixAX[i] - matrixB[i]);
        }
        return (err/matrixAX.length);
    }



    private static double getRandomDouble() {
        Random random = new Random();
        double max = Math.pow(2, 16) - 1;
        double min = -Math.pow(2, 16);
        double randomNumber = ((random.nextDouble() * (max - min)) + min);
        return randomNumber / Math.pow(2, 16);
    }
}
