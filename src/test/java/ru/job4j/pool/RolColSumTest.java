package ru.job4j.pool;

import org.junit.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RolColSumTest {

    @Test
    public void whenHasMatrixThenSumRows() {
        int[][] matrix = new int[][] {{1, 2, 3}, {3, 1, 4}, {4, 5, 6}};
        RolColSum.Sums[] sum = RolColSum.sum(matrix);
        RolColSum.Sums sums  = sum[0];
        assertThat(sums.getRowSum(), is(8));
    }

    @Test
    public void whenHasMatrixThenSumColumn() {
        int[][] matrix = new int[][] {{1, 2, 3}, {3, 1, 4}, {4, 5, 6}};
        RolColSum.Sums[] sum = RolColSum.sum(matrix);
        RolColSum.Sums sums  = sum[2];
        assertThat(sums.getColSum(), is(15));
    }

    @Test
    public void whenUseCompleTableFutureThenSumColumn() throws ExecutionException, InterruptedException {
        int[][] matrix = new int[][] {{1, 2, 3}, {3, 1, 4}, {4, 5, 6}};
        RolColSum.Sums[] sum = RolColSum.asyncSum(matrix);
        RolColSum.Sums sums  = sum[2];
        assertThat(sums.getColSum(), is(15));
    }

    @Test
    public void whenUseCompleTableFutureThenSumRows() throws ExecutionException, InterruptedException {
        int[][] matrix = new int[][] {{1, 2, 3}, {3, 1, 4}, {4, 5, 6}};
        RolColSum.Sums[] sum = RolColSum.asyncSum(matrix);
        RolColSum.Sums sums  = sum[0];
        assertThat(sums.getRowSum(), is(8));
    }

}