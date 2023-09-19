package edu.trinity;

import java.time.Year;

public class Car {

    private String make;
    private String model;
    private Year year;
    int miles = 0;
    boolean running = false;

    public Car(String make, String model, Year year) {
        this.make = make;
        this.model = model;
        this.year = year;

    }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public Year getYear() {
        return year;
    }

    public boolean isRunning() {
        return running;
    }

    public void start() {
        if(isRunning() == false){
            running = true;
        }
        else{
            return;
        }
    }

    public void stop() {
        if(isRunning()==true){
            running = false;
        }
        else{
            return;
        }

    }

    public void drive(int distance) {
        if(isRunning() == true){
            miles += distance;
        }
        else{
            return;
        }    
    }

    public int getMiles() {
        return miles;
    }
}
