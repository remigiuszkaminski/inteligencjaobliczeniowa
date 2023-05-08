import java.util.Objects;

public class MySparseMatrix {
    private final double[][] matrix;

    public MySparseMatrix(double[][] A) {
        this.matrix = A;
    }

    public double[] eliminateWithoutChoosing(double[] BOriginal) {
        int N = BOriginal.length;
        double[][] A = new double[N][N];
        for (int i = 0; i < N; i++) {
            System.arraycopy(matrix[i], 0, A[i], 0, N);
        }

        double [] B = new double[N];
        System.arraycopy(BOriginal, 0, B, 0, N);

        for (int k = 0; k < N; k++) {
                for (int i = k + 1; i < N; i++) {
                    double factor = A[i][k] / A[k][k];
                    B[i] -= factor * B[k];
                    for (int j = k + 1; j < N; j++) {
                        A[i][j] -= factor * A[k][j];
                    }
                }
            }
        return calculateMatrix(N, A, B);
    }

    public double[] eliminateWithChoosing(double[] BOriginal) {
        int N = BOriginal.length;

        double[][] A = new double[N][N];
        for (int i = 0; i < N; i++) {
            System.arraycopy(matrix[i], 0, A[i], 0, N);
        }
        double[] B = new double[N];
        System.arraycopy(BOriginal, 0, B, 0, N);

        for (int k = 0; k < N - 1; k++) {
            int maxRow = k;
            for (int i = k + 1; i < N; i++) {
                if (Math.abs(A[i][k]) > Math.abs(A[maxRow][k])) {
                    maxRow = i;
                }
            }
            if (A[maxRow][k] != 0) {
                double[] temp = A[k];
                A[k] = A[maxRow];
                A[maxRow] = temp;

                double t = B[k];
                B[k] = B[maxRow];
                B[maxRow] = t;

                double div = A[k][k];
                A[k][k] = 1;
                for (int j = k + 1; j < N; j++) {
                    A[k][j] /= div;
                }
                B[k] /= div;

                for (int i = k + 1; i < N; i++) {
                    double factor = A[i][k];
                    A[i][k] = 0;
                    for (int j = k + 1; j < N; j++) {
                        A[i][j] -= factor * A[k][j];
                    }
                    B[i] -= factor * B[k];
                }
            }
        }
        return calculateMatrix(N, A, B);
    }

    private double[] calculateMatrix(int n, double[][] a, double[] b) {
        double[] X = new double[n];
        for (int i = n - 1; i >= 0; i--) {
            double sum = 0;
            for (int j = i + 1; j < n; j++) {
                sum += a[i][j] * X[j];
            }
            X[i] = (b[i] - sum) / a[i][i];
        }
        return X;
    }
}
