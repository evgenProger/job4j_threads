package ru.job4j.pool;

import java.util.concurrent.CompletableFuture;

public class RolColSum {

    public static class Sums {
        private int rowSum;
        private int ColSum;

        public int getRowSum() {
            return rowSum;
        }

        public void setRowSum(int rowSum) {
            this.rowSum = rowSum;
        }

        public int getColSum() {
            return ColSum;
        }

        public void setColSum(int colSum) {
            ColSum = colSum;
        }
    }

    public static Sums[] sum(int[][] matrix) {
        Sums[] sums = new Sums[matrix.length];
        for (int i = 0; i < matrix.length; i++) {
            int rowSum = 0;
            int colSum = 0;
            Sums objSums = new Sums();
            for (int j = 0; j < matrix.length; j++) {
                colSum = colSum + matrix[i][j];
                rowSum = rowSum + matrix[j][i];
            }
            objSums.setColSum(colSum);
            objSums.setRowSum(rowSum);
            sums[i] = objSums;
        }
        return sums;
    }

    public static CompletableFuture<Sums[]> asyncSum(int[][] matrix) {
        Sums[] sums = new Sums[matrix.length];
       return CompletableFuture.supplyAsync(() -> {
           for (int i = 0; i < matrix.length; i++) {
               int rowSum = 0;
               int colSum = 0;
               Sums objSums = new Sums();
               for (int j = 0; j < matrix.length; j++) {
                   colSum = colSum + matrix[i][j];
                   rowSum = rowSum + matrix[j][i];
               }
               objSums.setColSum(colSum);
               objSums.setRowSum(rowSum);
               sums[i] = objSums;
           }

           return sums;
       });
    }
}
