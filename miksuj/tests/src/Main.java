import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class Main
{
    public static void main (String[] args) throws IOException {
//        compareMethods();
//        compareResultsDependingOnSize();
//        TestTime();
        Test.TestWithStaticNonZeros();
//        Test.testWithLibrary();
    }

    public static void compareResultsDependingOnSize() throws IOException {
        FileWriter fileWriter = new FileWriter("resultValues.csv");
        PrintWriter printWriter = new PrintWriter(fileWriter);

        int[] values = new int[3];
        int testSize = 1;
        double[] errors = new double[3];

        printWriter.println("Size;DenseMatrixA2;BandMatrixA2;SparseMatrixA2");
        for (int size = 50; size <= 700; size += 50) {
            values[0] = size;
            values[1] = size/10;
            values[2] = 10;
            for (int i = 0; i < testSize; i++) {
                double[] results = Test.testDS2(values);
                for (int j = 0; j < 3; j++) {
                    errors[j] += results[j + 3];
                }
            }
            for (int i = 0; i < 3; i++) {
                errors[i] /= testSize;
            }
            printWriter.println(size + ";" + errors[0] + ";" + errors[1] + ";" + errors[2]);
        }
        printWriter.close();
    }
    public static void TestTime(){
        long[] result = Test.testTimeDifference();
        System.out.println("DenseMatrixA2: " + result[0]);
        System.out.println("SparseMatrixA2: " + result[1]);
    }

    public static void compareMethods() throws IOException {
        FileWriter fileWriter = new FileWriter("result.csv");
        PrintWriter printWriter = new PrintWriter(fileWriter);

        int[] values = new int[3];
        values[0] = 100;
        values[1] = 1;
        values[2] = 10;

        printWriter.println("DenseMatrixA1;BandMatrixA1;SparseMatrixA1;DenseMatrixA2;BandMatrixA2;SparseMatrixA2");
        int testSize = 100;
        double[] errors = new double[6];
        for (int i = 0; i < testSize; i++) {
            double[] results = Test.testDS2(values);
            for (int j = 0; j < 6; j++) {
                errors[j] += results[j];
            }
            printWriter.println(results[0] + ";" + results[1] + ";" + results[2] + ";" + results[3] + ";" + results[4] + ";" + results[5]);
        }
        for (int i = 0; i < 6; i++) {
            errors[i] /= testSize;
        }
        System.out.println("DenseMatrixA1: " + errors[0]);
        System.out.println("BandMatrixA1: " + errors[1]);
        System.out.println("SparseMatrixA1: " + errors[2]);
        System.out.println("DenseMatrixA2: " + errors[3]);
        System.out.println("BandMatrixA2: " + errors[4]);
        System.out.println("SparseMatrixA2: " + errors[5]);
        printWriter.close();
    }
}