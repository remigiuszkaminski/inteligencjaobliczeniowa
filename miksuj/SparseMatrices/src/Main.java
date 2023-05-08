import org.ejml.simple.SimpleMatrix;

import java.io.FileWriter;
import java.io.IOException;
public class Main {
    public static void main(String[] args) throws IOException {
//        compareDepSize();
//        compareMethods();
        testTimeWithBib();
        compareTimes();
    }

    public static void testTimeWithBib() {
        long time1 = 0;
        long time2 = 0;
        for(int i = 0; i < 500; i++) {
            double[][] denseMatrixA = Matrixes.matrixDenseRow(100);
            double[] matrixB = Matrixes.matrixB(100);
            MySparseMatrix denseMatrixSolver = new MySparseMatrix(denseMatrixA);
            long start = System.nanoTime();
            double[] denseMatrixX = denseMatrixSolver.eliminateWithChoosing(matrixB);
            long end = System.nanoTime();
            time1 += end - start;

            SimpleMatrix A = new SimpleMatrix(100,100);
            for(int a = 0; a< 100;a++) {
                for(int b = 0; b<100;b++){
                    A.set(a,b, denseMatrixA[a][b]);
                }
            }

            SimpleMatrix B = new SimpleMatrix(100,1);
            B.setColumn(0, 0, matrixB);
            long start2 = System.nanoTime();
            SimpleMatrix X = A.solve(B);
            long end2 = System.nanoTime();
            time2 += end2 - start2;
        }
        System.out.println("Średnia dla mojej metody: " + time1/500);
        System.out.println("Średnia dla wbudowanej metody: " + time2/500);
    }


    public static void compareDepSize() throws IOException {
        String filename = "results.csv";
        FileWriter writer = new FileWriter(filename);
        writer.write("Size,Band,Density,DenseWithChoosing,BandWithChoosing,SparseWithChoosing,DenseWithoutChoosing,BandWithoutChoosing,SparseWithoutChoosing\n");
        int[] arguments = new int[]{200, 5, 10};
        for (int i = 0; i < 8; i++) {
            double[] result = Tests.testRow(arguments);
            writer.write(arguments[0] + "," + arguments[1] + "," + arguments[2] + "," + result[0] + "," + result[1] + "," + result[2] + "," + result[3] + "," + result[4] + "," + result[5] + "\n");
            arguments[0] += 60;
            arguments[1] = arguments[0] / 10;
        }
        writer.close();
        System.out.println("Results saved to file: " + filename);
    }

    public static void compareMethods() throws IOException {
        String filename = "compare.csv";
        FileWriter writer = new FileWriter(filename);
        writer.write("DenseWithChoosing,BandWithChoosing,SparseWithChoosing,DenseWithoutChoosing,BandWithoutChoosing,SparseWithoutChoosing\n");
        int[] arguments = new int[]{100, 1, 10};
        int testSize = 100;
        double[] errors = new double[6];
        for (int i = 0; i < testSize; i++) {
            double[] results = Tests.testRow(arguments);
            for (int j = 0; j < 6; j++) {
                errors[j] += results[j];
            }
            writer.write(results[0] + "," + results[1] + "," + results[2] + "," + results[3] + "," + results[4] + "," + results[5] + "\n");
        }
        for (int i = 0; i < 6; i++) {
            errors[i] /= testSize;
        }
        System.out.println("DenseWithChoosing : " + errors[0]);
        System.out.println("BandWithChoosing : " + errors[1]);
        System.out.println("SparseWithChoosing : " + errors[2]);
        System.out.println("DenseWithoutChoosing : " + errors[3]);
        System.out.println("BandWithoutChoosing : " + errors[4]);
        System.out.println("SparseWithoutChoosing : " + errors[5]);
        writer.close();
    }
    public static void compareTimes() {
        long time1 = 0;
        long time2 = 0;
        long time3 = 0;
        long time4 = 0;
        for (int i = 0; i<100; i++) {
            double[][] denseMatrixA = Matrixes.matrixDenseRow(100);

            double[] matrixB = Matrixes.matrixB(100);

            MySparseMatrix denseMatrixSolver = new MySparseMatrix(denseMatrixA);

            long start1 = System.nanoTime();
            double[] denseMatrixX = denseMatrixSolver.eliminateWithChoosing(matrixB);
            long end1 = System.nanoTime();
            time1 += end1 - start1;


            double[][] sparseMatrixA = Matrixes.sparseRandomMatrixRow(100,25);
            MySparseMatrix sparseMatrixSolver = new MySparseMatrix(sparseMatrixA);
            long start2 = System.nanoTime();
            double[] sparseMatrixX = sparseMatrixSolver.eliminateWithChoosing(matrixB);
            long end2 = System.nanoTime();
            time2 += end2 - start2;


            long start3 = System.nanoTime();
            double[] denseMatrixX2 = denseMatrixSolver.eliminateWithoutChoosing(matrixB);
            long end3 = System.nanoTime();
            time3 = end3 - start3;


            long start4 = System.nanoTime();
            double[] sparseMatrixX2 = sparseMatrixSolver.eliminateWithoutChoosing(matrixB);
            long end4 = System.nanoTime();
            time4 += end4 - start4;
        }

        System.out.println("Czas dla macierzy rzadkich z wyborem : " + time2/100);
        System.out.println("Czas dla macierzy gęstych z wyborem : " + time1/100);
        System.out.println("Czas dla macierzy rzadkich bez wyboru : " + time4/100);
        System.out.println("Czas dla macierzy gęstych bez wyboru : " + time3/100);
    }
}