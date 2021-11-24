package ru.job4j.pool;

import java.util.concurrent.CompletableFuture;

public class RolColSum {

    public static class Sums {
        private int rowSum;
        private int colSum;

        public int getRowSum() {
            return rowSum;
        }

        public void setRowSum(int rowSum) {
            this.rowSum = rowSum;
        }

        public int getColSum() {
            return colSum;
        }

        public void setColSum(int colSum) {
            this.colSum = colSum;
        }
    }

    private static Sums sumByIRowAndColumn(int i, int[][] matrix) {
        Sums objSums = new Sums();
        int rowSum = 0;
        int colSum = 0;
        for (int j = 0; j < matrix.length; j++) {
            rowSum = rowSum + matrix[j][i];
            colSum = colSum + matrix[i][j];
        }
        objSums.setRowSum(rowSum);
        objSums.setColSum(colSum);

        return objSums;
    }

    public static Sums[] sum(int[][] matrix) {
        Sums[] sums = new Sums[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            sums[i] = sumByIRowAndColumn(i, matrix);
        }
        return sums;
    }

    public static CompletableFuture<Sums[]> asyncSum(int[][] matrix) {
        Sums[] sums = new Sums[matrix.length];
       return CompletableFuture.supplyAsync(() -> {
           for (int i = 0; i < matrix.length; i++) {
               sums[i] = sumByIRowAndColumn(i, matrix);
           }
           return sums;
       });
    }
}
