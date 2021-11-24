package ru.job4j.pool;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

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

    public static Sums[] asyncSum(int[][] matrix) throws ExecutionException, InterruptedException {
        Sums[] sums = new Sums[matrix.length];
        Sums objSums = new Sums();
        Map<Integer, CompletableFuture<Sums>> futureMap = new HashMap<>();
        for (int i = 0; i < matrix.length; i++) {
            int finalI = i;
            futureMap.put(i,  CompletableFuture.supplyAsync(
                    () -> sumByIRowAndColumn(finalI, matrix)
            ));
        }
        for (Integer key: futureMap.keySet()) {
            sums[key] = futureMap.get(key).get();
        }

        return sums;
    }
}
