package greedy;

import java.io.*;
import java.util.*;

public class Boj13305 {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int n;
    static int[] distances;
    static int[] gasStations;

    public static void main(String[] args) throws Exception {
        init();

        long minimumGasPrice = Integer.MAX_VALUE;
        long cost = 0;
        for (int i = 0; i < n - 1; i++) {
            minimumGasPrice = Math.min(minimumGasPrice, gasStations[i]);
            cost += minimumGasPrice * distances[i];
        }

        System.out.println(cost);
    }

    public static void init() throws Exception {
        n = Integer.parseInt(br.readLine());

        distances = new int[n - 1];
        gasStations = new int[n];

        String[] input = br.readLine().split(" ");
        for (int i = 0; i < n - 1; i++) {
            distances[i] = Integer.parseInt(input[i]);
        }

        input = br.readLine().split(" ");
        for (int i = 0; i < n; i++) {
            gasStations[i] = Integer.parseInt(input[i]);
        }
    }
}

