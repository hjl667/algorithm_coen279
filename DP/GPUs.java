package DP;

//    ByteDance is planning to buy some GPs for training their new computer vision models using them. They have 2 clusters on which all the models are
//    saved. There are n GPs available, where the cost of the fn GPU is represented by array element cost/(I). Also, there are
//    two arrays compatible1 and compatible? each containing n integers, where each integer is either 0 or 1, representing the following:
//    If compatible1(1) = 1, then the fh GPU is compatible with cluster 1, else it is not compatible with cluster 1.
//    If compatible2(1] = 1, then the ph GPU is compatible with cluster 2, else it is not compatible with cluster 2.
//            The company wants to buy the GPs such that there are at least a min_compatible number of GPUs compatible with each of cluster 1 and cluster 2.
//            Given n GPs, an integer min compatible, and three arrays cost, compatible1 and compatible2, find the minimum possible cost of GPs such that there
//                           are at least a min compatible number of GPUs compatible with each of cluster 1 and cluster 2. Return -1 if it is not possible to buy the GPs satisfying the
//                           above condition.
//            Example
//                           Given, cost = (2, 4, 6, 5], compatible1 = (1, 1, 1, 01, compatible2 = (0, 0, 1, 1], and min compatible = 2.

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GPUs {

    public int minCost(int[] cost, int[] compatible1, int[] compatible2, int min_compatible){
        //this is a knapsack variant
        //to build up to the min cost of having min_compatible of GPUs, we need to calculate from 1 to min_compatible - 1
        int n1 = 0;
        int n2 = 0;
        int n3 = 0;

        for(int i = 0; i < compatible1.length; i++){
            if(compatible1[i] == 1 & compatible2[i] != 1){
                n1++;
            }else if(compatible2[i] == 1 && compatible1[i] != 1){
                n2++;
            }else if(compatible2[i] == 1 && compatible1[i] == 1){
                n3++;
            }
        }

        int[] GPU1 = new int[n1];
        int[] GPU2 = new int[n2];
        int[] GPU3 = new int[n3];

        int idx1 = 0;
        int idx2 = 0;
        int idx3 = 0;

        for(int i = 0; i < compatible1.length; i++){
            if(compatible1[i] == 1 & compatible2[i] != 1){
                GPU1[idx1++] = cost[i];
            }else if(compatible2[i] == 1 && compatible1[i] != 1){
                GPU2[idx2++] = cost[i];
            }else if(compatible2[i] == 1 && compatible1[i] == 1){
                GPU3[idx3++] = cost[i];
            }
        }

        Arrays.sort(GPU1);
        Arrays.sort(GPU2);
        Arrays.sort(GPU3);
        int[] GPU4 = new int[Math.min(GPU1.length, GPU2.length)];

        for(int i = 0; i < GPU4.length; i++){
            GPU4[i] = GPU1[i] + GPU2[i];
        }

        if(min_compatible > GPU4.length + GPU3.length){
            return -1;
        }

        idx1 = 0;
        idx2 = 0;

        int dp[] = new int[min_compatible + 1];
        dp[0] = 0;
        int i = 1;
        while(idx1 < GPU3.length && idx2 < GPU4.length && i < dp.length){
            if(GPU3[idx1] < GPU4[idx2]){
                dp[i] = dp[i - 1] + GPU3[idx1];
                idx1++;
            }else{
                dp[i] = dp[i - 1] + GPU4[idx2];
                idx2++;
            }
            i++;
        }

        if(idx1 == GPU3.length){
            while(idx2 < GPU4.length && i < dp.length){
                dp[i] = GPU4[idx2] + dp[i - 1];
                i++;
                idx2++;
            }
        }else if(idx2 == GPU4.length){
            while(idx1 < GPU3.length && i < dp.length){
                dp[i] = GPU3[idx1] + dp[i - 1];
                i++;
                idx1++;
            }
        }

        return dp[min_compatible];
    }

    public static void main(String[] args) {
        GPUs gpus = new GPUs();
        int[] cost = {2, 4, 8, 5};
        int[] compatible1 = {1, 1, 1, 0};
        int[] compatible2 = {0, 0, 1, 1};
        int min_compatible = 1;
        System.out.println("Minimum cost: " + gpus.minCost(cost, compatible1, compatible2, min_compatible));
    }

}
