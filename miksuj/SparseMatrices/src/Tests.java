import org.ejml.simple.SimpleMatrix;
public class Tests {

    public static double[] testRow(int[] args) {
        int size = args[0];
        int band = args[1];
        int dens = args[2];
        double[][] denseMatrixA = Matrixes.matrixDenseRow(size);
        double[][] bandMatrixA = Matrixes.matrixBandRow(size, band);
        double[][] sparseMatrixA = Matrixes.sparseRandomMatrixRow(size, dens);

        double[] matrixB = Matrixes.matrixB(size);

        MySparseMatrix denseMatrixSolver = new MySparseMatrix(denseMatrixA);
        MySparseMatrix bandMatrixSolver = new MySparseMatrix(bandMatrixA);
        MySparseMatrix sparseMatrixSolver = new MySparseMatrix(sparseMatrixA);

        double[] denseMatrixX = denseMatrixSolver.eliminateWithChoosing(matrixB);
        double[] bandMatrixX = bandMatrixSolver.eliminateWithChoosing(matrixB);
        double[] sparseMatrixX = sparseMatrixSolver.eliminateWithChoosing(matrixB);

        double[] denseMatrixX1 = denseMatrixSolver.eliminateWithoutChoosing(matrixB);
        double[] bandMatrixX1 = bandMatrixSolver.eliminateWithoutChoosing(matrixB);
        double[] sparseMatrixX1 = sparseMatrixSolver.eliminateWithoutChoosing(matrixB);

        double[] denseMatrixAX = Matrixes.multiplyRow(denseMatrixA, denseMatrixX);
        double[] bandMatrixAX = Matrixes.multiplyRow(bandMatrixA, bandMatrixX);
        double[] sparseMatrixAX = Matrixes.multiplyRow(sparseMatrixA, sparseMatrixX);

        double[] denseMatrixAX1 = Matrixes.multiplyRow(denseMatrixA, denseMatrixX1);
        double[] bandMatrixAX1 = Matrixes.multiplyRow(bandMatrixA, bandMatrixX1);
        double[] sparseMatrixAX1 = Matrixes.multiplyRow(sparseMatrixA, sparseMatrixX1);
        double[] result = new double[6];
        result[0] = Matrixes.calcErr(denseMatrixAX, matrixB);
        result[1] = Matrixes.calcErr(bandMatrixAX, matrixB);
        result[2] = Matrixes.calcErr(sparseMatrixAX, matrixB);
        result[3] = Matrixes.calcErr(denseMatrixAX1, matrixB);
        result[4] = Matrixes.calcErr(bandMatrixAX1, matrixB);
        result[5] = Matrixes.calcErr(sparseMatrixAX1, matrixB);
        return result;

    }

    public static double[] testCol(int[] args) {
        int size = args[0];
        int band = args[1];
        int dens = args[2];
        double[][] denseMatrixA = Matrixes.matrixDenseCol(size);
        double[][] bandMatrixA = Matrixes.matrixBandCol(size, band);
        double[][] sparseMatrixA = Matrixes.sparseRandomMatrixCol(size, dens);

        double[] matrixB = Matrixes.matrixB(size);

        MySparseMatrix denseMatrixSolver = new MySparseMatrix(denseMatrixA);
        MySparseMatrix bandMatrixSolver = new MySparseMatrix(bandMatrixA);
        MySparseMatrix sparseMatrixSolver = new MySparseMatrix(sparseMatrixA);

        double[] denseMatrixX = denseMatrixSolver.eliminateWithChoosing(matrixB);
        double[] bandMatrixX = bandMatrixSolver.eliminateWithChoosing(matrixB);
        double[] sparseMatrixX = sparseMatrixSolver.eliminateWithChoosing(matrixB);

        double[] denseMatrixX1 = denseMatrixSolver.eliminateWithoutChoosing(matrixB);
        double[] bandMatrixX1 = bandMatrixSolver.eliminateWithoutChoosing(matrixB);
        double[] sparseMatrixX1 = sparseMatrixSolver.eliminateWithoutChoosing(matrixB);

        double[] denseMatrixAX = Matrixes.multiplyCol(denseMatrixA, denseMatrixX);
        double[] bandMatrixAX = Matrixes.multiplyCol(bandMatrixA, bandMatrixX);
        double[] sparseMatrixAX = Matrixes.multiplyCol(sparseMatrixA, sparseMatrixX);

        double[] denseMatrixAX1 = Matrixes.multiplyCol(denseMatrixA, denseMatrixX1);
        double[] bandMatrixAX1 = Matrixes.multiplyCol(bandMatrixA, bandMatrixX1);
        double[] sparseMatrixAX1 = Matrixes.multiplyCol(sparseMatrixA, sparseMatrixX1);

        double[] result = new double[6];
        result[0] = Matrixes.calcErr(denseMatrixAX, matrixB);
        result[1] = Matrixes.calcErr(bandMatrixAX, matrixB);
        result[2] = Matrixes.calcErr(sparseMatrixAX, matrixB);
        result[3] = Matrixes.calcErr(denseMatrixAX1, matrixB);
        result[4] = Matrixes.calcErr(bandMatrixAX1, matrixB);
        result[5] = Matrixes.calcErr(sparseMatrixAX1, matrixB);

        return result;
    }

}
