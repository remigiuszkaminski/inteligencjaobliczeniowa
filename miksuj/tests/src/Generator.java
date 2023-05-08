import java.util.Random;

public class Generator {

    public static double[][] generateDenseMatrixA (int size) {
        double[][] matrixA = new double[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                matrixA[i][j] = getRandomDouble();
            }
        }
        return matrixA;
    }


    public static double[][] DS2generateBandMatrixA (int size, int band) {
        double[][] matrixA = new double[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (Math.abs(i - j) <= band) {
                    matrixA[i][j] = getRandomDouble();
                }
            }
        }
        return matrixA;
    }

    public static double[][] DS2generateSparseMatrixA (int size, int density) {
        double[][] matrixA = new double[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (j==i){
                    matrixA[i][j] = getRandomDouble();
                }else{
                    if (getRandomInt() < density) {
                        matrixA[i][j] = getRandomDouble();
                    }
                }
            }
        }
        return matrixA;
    }

    public static double[][] DS2generateSparseMatrixwithStaticNonZerosA(int size, int numberOfElements) {
        double[][] matrixA = new double[size][size];
        for (int i = 0; i < size; i++) {
            matrixA[i][i] = getRandomDouble();
        }

        for (int i=0; i<size;i++){
            int count = 0;
            for( int j=0; j<size; j++){
                if (matrixA[i][j] == 0 && count < numberOfElements-1){
                    matrixA[i][j] = getRandomDouble();
                    count++;
                }
            }
        }

        return matrixA;
    }


    public static double[][] DS3generateSparseMatrixA (int size, int density) {
        double[][] matrixA = new double[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (j==i){
                    matrixA[i][j] = getRandomDouble();
                }else{
                    if (getRandomInt() < density) {
                        matrixA[j][i] = getRandomDouble();
                    }
                }
            }
        }
        return matrixA;
    }

    public static double[][] DS3generateBandMatrixA (int size, int band) {
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

    private static int getRandomInt() {
        Random random = new Random();
        return random.nextInt(100);
    }


    public static double[] generateMatrixB (int size) {
        double[] matrixB = new double[size];
        for (int i = 0; i < size; i++) {
            matrixB[i] = getRandomDouble();
        }
        return matrixB;
    }

    private static double getRandomDouble() {
        Random random = new Random();
        double max = 2^16 - 1;
        double min = -2^16;
        double randomNumber = ((random.nextDouble() * (max-min)) + min);
        return randomNumber/(2^16);
    }

    public static double[] multiplyMatrixDS2(double[][] matrixA, double[] matrixX) {
        double[] matrixAX = new double[matrixA.length];
        for (int i = 0; i < matrixA.length; i++) {
            for (int j = 0; j < matrixA.length; j++) {
                matrixAX[i] += matrixA[i][j] * matrixX[j];
            }
        }

        return matrixAX;
    }
    public static double[] multiplyMatrixDS3(double[][] matrixA, double[] matrixX) {
        double[] matrixAX = new double[matrixA.length];
        for (int i = 0; i < matrixA.length; i++) {
            for (int j = 0; j < matrixA.length; j++) {
                matrixAX[i] += matrixA[j][i] * matrixX[j];
            }
        }
        return matrixAX;
    }

    public static double getAccuracy(double[] matrixAX, double[] matrixB) {
        double accuracy = 0;
        for (int i = 0; i < matrixAX.length; i++) {
            accuracy += Math.abs(matrixAX[i] - matrixB[i]);
        }
        return (accuracy/matrixAX.length);
    }

}