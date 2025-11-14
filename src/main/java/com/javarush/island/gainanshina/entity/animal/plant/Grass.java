package com.javarush.island.gainanshina.entity.animal.plant;

import com.javarush.island.gainanshina.entity.Animal;

public class Grass extends Animal {
    public Plant(Location location) {
        super(location);
    }

    public double getWeight() { return 1; }
    public int getMaxPerLocation() { return 200; }
    public int getMaxSpeed() { return 0; }
    public double getFoodRequired() { return 0; }
    public int getMaxChildren() { return 0; }

    protected Object findFood() { return null; }
    protected double getFoodValue(Object food) { return 0; }
    protected boolean tryToEat(Object food) { return false; }
    protected void consumeFood(Object food) {}
    protected void giveBirth() {}
    public void eat() {}
    public void move() {}
    public void reproduce() {}
    public void decreaseSatiety() {}
}