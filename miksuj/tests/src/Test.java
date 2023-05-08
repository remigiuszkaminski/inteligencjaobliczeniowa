import org.ejml.simple.SimpleMatrix;
public class Test {

    public static void testWithLibrary() {
        long averageTime1 = 0;
        long averageTime2 = 0;
        for (int i = 0; i < 1000; i++) {
            double[][] denseMatrixA = Generator.generateDenseMatrixA(100);
            double[] matrixB = Generator.generateMatrixB(100);
            MySparseMatrix denseMatrixSolver = new MySparseMatrix(denseMatrixA, "DS2");
            long startTime1 = System.nanoTime();
            double[] denseMatrixX = denseMatrixSolver.solveA2(matrixB);
            long endTime1 = System.nanoTime();
            averageTime1 += endTime1 - startTime1;

            SimpleMatrix A = new SimpleMatrix(100,100);
            for(int j = 0; j < 100; j++){
                for(int k = 0; k < 100; k++){
                    A.set(j, k, denseMatrixA[j][k]);
                }
            }
            SimpleMatrix b = new SimpleMatrix(100,1);
            b.setColumn(0, 0, matrixB);
            long startTime2 = System.nanoTime();
            SimpleMatrix x = A.solve(b);
            long endTime2 = System.nanoTime();
            averageTime2 += endTime2 - startTime2;
        }
        System.out.println("Average for our method: " + averageTime1 / 1000);
        System.out.println("Average for library method: " + averageTime2 / 1000);
    }

    public static double[] testDS2(int[] args) {
        int size = args[0];
        int band = args[1];
        int density = args[2];

        double[][] denseMatrixA = Generator.generateDenseMatrixA(size);
        double[][] bandMatrixA = Generator.DS2generateBandMatrixA(size, band);
        double[][] sparseMatrixA = Generator.DS2generateSparseMatrixA(size, density);

        double[] matrixB = Generator.generateMatrixB(size);

        MySparseMatrix denseMatrixSolver = new MySparseMatrix(denseMatrixA, "DS2");
        MySparseMatrix bandMatrixSolver = new MySparseMatrix(bandMatrixA, "DS2");
        MySparseMatrix sparseMatrixSolver = new MySparseMatrix(sparseMatrixA, "DS2");

        double[] denseMatrixX = denseMatrixSolver.solveA1(matrixB);
        double[] bandMatrixX = bandMatrixSolver.solveA1(matrixB);
        double[] sparseMatrixX = sparseMatrixSolver.solveA1(matrixB);

        double[] denseMatrixX1 = denseMatrixSolver.solveA2(matrixB);
        double[] bandMatrixX1 = bandMatrixSolver.solveA2(matrixB);
        double[] sparseMatrixX1 = sparseMatrixSolver.solveA2(matrixB);

        double[] denseMatrixAX = Generator.multiplyMatrixDS2(denseMatrixA, denseMatrixX);
        double[] bandMatrixAX = Generator.multiplyMatrixDS2(bandMatrixA, bandMatrixX);
        double[] sparseMatrixAX = Generator.multiplyMatrixDS2(sparseMatrixA, sparseMatrixX);

        double[] denseMatrixAX1 = Generator.multiplyMatrixDS2(denseMatrixA, denseMatrixX1);
        double[] bandMatrixAX1 = Generator.multiplyMatrixDS2(bandMatrixA, bandMatrixX1);
        double[] sparseMatrixAX1 = Generator.multiplyMatrixDS2(sparseMatrixA, sparseMatrixX1);
        double[] result = new double[6];
        result[0] = Generator.getAccuracy(denseMatrixAX, matrixB);
        result[1] = Generator.getAccuracy(bandMatrixAX, matrixB);
        result[2] = Generator.getAccuracy(sparseMatrixAX, matrixB);
        result[3] = Generator.getAccuracy(denseMatrixAX1, matrixB);
        result[4] = Generator.getAccuracy(bandMatrixAX1, matrixB);
        result[5] = Generator.getAccuracy(sparseMatrixAX1, matrixB);
        return result;
    }

    public static double[] testDS3(int[] args) {
        int size = args[0];
        int band = args[1];
        int density = args[2];

        double[][] denseMatrixA = Generator.generateDenseMatrixA(size);
        double[][] bandMatrixA = Generator.DS3generateBandMatrixA(size, band);
        double[][] sparseMatrixA = Generator.DS3generateSparseMatrixA(size, density);

        double[] matrixB = Generator.generateMatrixB(size);

        MySparseMatrix denseMatrixSolver = new MySparseMatrix(denseMatrixA, "DS3");
        MySparseMatrix bandMatrixSolver = new MySparseMatrix(bandMatrixA, "DS3");
        MySparseMatrix sparseMatrixSolver = new MySparseMatrix(sparseMatrixA, "DS3");

        double[] denseMatrixX = denseMatrixSolver.solveA1(matrixB);
        double[] bandMatrixX = bandMatrixSolver.solveA1(matrixB);
        double[] sparseMatrixX = sparseMatrixSolver.solveA1(matrixB);

        double[] denseMatrixX1 = denseMatrixSolver.solveA2(matrixB);
        double[] bandMatrixX1 = bandMatrixSolver.solveA2(matrixB);
        double[] sparseMatrixX1 = sparseMatrixSolver.solveA2(matrixB);

        double[] denseMatrixAX = Generator.multiplyMatrixDS3(denseMatrixA, denseMatrixX);
        double[] bandMatrixAX = Generator.multiplyMatrixDS3(bandMatrixA, bandMatrixX);
        double[] sparseMatrixAX = Generator.multiplyMatrixDS3(sparseMatrixA, sparseMatrixX);

        double[] denseMatrixAX1 = Generator.multiplyMatrixDS3(denseMatrixA, denseMatrixX1);
        double[] bandMatrixAX1 = Generator.multiplyMatrixDS3(bandMatrixA, bandMatrixX1);
        double[] sparseMatrixAX1 = Generator.multiplyMatrixDS3(sparseMatrixA, sparseMatrixX1);
        double[] result = new double[6];

        result[0] = Generator.getAccuracy(denseMatrixAX, matrixB);
        result[1] = Generator.getAccuracy(bandMatrixAX, matrixB);
        result[2] = Generator.getAccuracy(sparseMatrixAX, matrixB);
        result[3] = Generator.getAccuracy(denseMatrixAX1, matrixB);
        result[4] = Generator.getAccuracy(bandMatrixAX1, matrixB);
        result[5] = Generator.getAccuracy(sparseMatrixAX1, matrixB);
        return result;
    }

    public static long[] testTimeDifference(){
        int size = 100;
        int density = 30;

        long averageDense = 0;
        long averageSparse = 0;
        for (int i = 0; i < 100; i++) {
            double[][] denseMatrixA = Generator.generateDenseMatrixA(size);

            double[] matrixB = Generator.generateMatrixB(size);

            MySparseMatrix denseMatrixSolver = new MySparseMatrix(denseMatrixA, "DS2");

            long startTime1 = System.nanoTime();
            double[] denseMatrixX = denseMatrixSolver.solveA1(matrixB);
            long endTime1 = System.nanoTime();
            averageDense += endTime1 - startTime1;

            double[][] sparseMatrixA = Generator.DS2generateSparseMatrixA(size, density);
            MySparseMatrix sparseMatrixSolver = new MySparseMatrix(sparseMatrixA, "DS2");
            long startTime2 = System.nanoTime();
            double[] sparseMatrixX = sparseMatrixSolver.solveA1(matrixB);
            long endTime2 = System.nanoTime();
            averageSparse += endTime2 - startTime2;
        }
        averageDense /= 100;
        averageSparse /= 100;

        return new long[]{averageDense, averageSparse};
    }

    public static void TestWithStaticNonZeros(){
        long averageSparseA2 = 0;
        for (int i = 50; i <= 1000; i+=50){
            for (int j = 10; j <= 20; j+=1){
                double [][] matrix = Generator.DS2generateSparseMatrixwithStaticNonZerosA(i^2, 10);
                MySparseMatrix solver = new MySparseMatrix(matrix, "DS2");
                double[] b = Generator.generateMatrixB(i^2);
                long startTime2 = System.nanoTime();
                double[] x = solver.solveA2(b);
                long endTime2 = System.nanoTime();
                // System.out.println(i + ";" + (endTime2 - startTime2));
                averageSparseA2 += endTime2 - startTime2;
            }
            System.out.println("Average A2: " + averageSparseA2/20);
        }


        long averageSparseA1 = 0;

        for (int i = 50; i <= 1000; i+=50){
            for (int j = 10; j <= 20; j+=1){
                double [][] matrix = Generator.DS2generateSparseMatrixwithStaticNonZerosA(i^2, 10);
                MySparseMatrix solver = new MySparseMatrix(matrix, "DS2");
                double[] b = Generator.generateMatrixB(i^2);
                long startTime2 = System.nanoTime();
                double[] x = solver.solveA1(b);
                long endTime2 = System.nanoTime();
                // System.out.println(i + ";" + (endTime2 - startTime2));
                averageSparseA1 += endTime2 - startTime2;
            }
            System.out.println("Average A1: " + averageSparseA1/20);
        }
    }
}