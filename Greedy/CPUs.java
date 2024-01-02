package Greedy;

//ByteDance is working on a new algorithm that operates on a special CPU that
//        can handle n types of tasks, and the priority of the ith task is represented by
//        the array priority[i]. The CPU will run for y seconds and you can choose any
//        task to perform which follows the given conditions:
//        • In each second, you user can choose to perform any available task from the n
//        types (if there are any tasks to perform).
//        • It is allowed to perform multiple tasks of the same type within the given time
//        frame, but there is a time constraint that there must be at least x seconds
//        between any two consecutive tasks of the same type.
//        • It is not allowed to perform more than one task at any second.
//
//        Given n tasks, an array priority, and an integer x and y, find the maximum
//        possible sum of the priority of tasks performed during the y seconds.
//        Example
//        Given, n= 3, priority = [3, 1, 21, x =5, and y = 7.

import java.util.Arrays;

public class CPUs {
    public int maxPriority(int n, int[] priority, int x, int y){
        //greedy algorithm to choose the highest-priority available task
        int maxPriority = 0;
        Arrays.sort(priority);
        int count = 1;
        int idx = priority.length - 1;
        int i = 1;
        while(i <= y){
            while(i <= y && count <= x){
                if(idx >= 0){
                    maxPriority += priority[idx];
                }
                idx--;
                count++;
                i++;
            }
            idx = priority.length - 1;
            count = 1;
        }
        return maxPriority;
    }

    public static void main(String[] args){
        CPUs cpu = new CPUs();
        int n = 3;
        int[] priority = {3, 1, 2};
        int x = 5;
        int y = 7;

        int result = cpu.maxPriority(n, priority, x, y);
        System.out.println("Maximum possible sum of priority: " + result);
    }
}
