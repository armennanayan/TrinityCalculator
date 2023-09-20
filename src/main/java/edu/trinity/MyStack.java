package edu.trinity;

import java.util.EmptyStackException;

public class MyStack<T> {

    Object[] arr = new Object[100]; //Assuming there is a limit of 100 n
    int posit = 0;
    T lastObj = null;
    boolean status = false;

    public void push(T hello) {
        arr[posit] = hello;
        posit++;
    }

    public T peek() {
        if(isEmpty() == true){
            throw new EmptyStackException();
        }
        else{
            lastObj = (T) arr[posit-1];
            return lastObj;
        }
    }

    public T pop(){
        if(isEmpty()){
            throw new EmptyStackException();
        }
        else{
            if(posit > 0){
                lastObj = (T) arr[posit-1];
                arr[posit-1] = null;
                posit--;
            }
            else{
                lastObj = null;
            }
            return lastObj;
        }
    }

    public boolean isEmpty() {
        if(posit <= 0){
            status = true;
        }
        else{
            status = false;
        }
        return status;
    }

}
