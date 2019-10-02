/*
 * File:    Sequence.java
 * Author:  Anthony Smith
 * Date:    2 December 2018
 * Purpose: Uses iterative and recursive methods to calculate a given number in a sequence
 */
public class Sequence{
    public static int efficiency = 0;
    public static long computeIterative(long n){
        efficiency = 0;
        long result = 0;
        if(n == 0){
            efficiency++;
            return 0;
        }else if(n == 1){
            efficiency++;
            return 1;
        }else{
            long previousTerm = 1;
            long secondPreviousTerm = 0;
            for(long i = 2; i <= n; i++){
                efficiency++;
                result = 2 * previousTerm + secondPreviousTerm;
                secondPreviousTerm = previousTerm;
                previousTerm = result;
            }
        }return result;
    }

    //sets efficiency counter to o and runs the computeRecursiveHelper method
    public static long computeRecursive(long n)
    {
        efficiency = 0;
        return computeRecursiveHelper(n);
    }

    //helper method to use recursion to calculate nth number of the sequence
    private static long computeRecursiveHelper(long n){
        long result = 0;
        if(n == 0){
            efficiency++;
            return 0;
        }else if(n == 1){
            efficiency++;
            return 1;
            //if n is greater then zero, run recursion method
        }else{
            efficiency++;
            result = 2 * computeRecursiveHelper(n-1) + computeRecursiveHelper(n-2);
        }return result;
    }

    //getter for efficiency
    public static int getEfficiency() {
        return efficiency;
    }


}
