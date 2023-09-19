package edu.trinity;

public class Fibonacci {

    public static int calculate(int i) {
        int initialVar = 1;
        int intermVar = 0;
        int finalVal = 1;
        if(i == 0){
            return 0;
        }
        else if(i == 1 || i == 2){
            return 1;
        }
        else{
            for(int f = 3; f <= i; f++){
                intermVar = finalVal;
                finalVal = finalVal + initialVar;
                initialVar = intermVar;
            }
        }
       
       return finalVal;
    }
}
