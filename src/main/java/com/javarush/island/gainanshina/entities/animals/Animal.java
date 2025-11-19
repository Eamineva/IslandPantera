package com.javarush.island.gainanshina.entities.animals;

import com.javarush.island.gainanshina.entities.Entity;
import com.javarush.island.gainanshina.entities.Food;
import com.javarush.island.gainanshina.island.Location;
import com.javarush.island.gainanshina.services.config.IslandConfig;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Animal extends Entity implements Food {
    protected double weight;
    protected double health = 100;
    protected boolean isAlive = true;

    public Animal(double weight) {
        this.weight = weight;
    }

    public abstract void eat();
    public abstract void move();
    public abstract void reproduce();

    public double getWeight() { return weight; }
    public double getHealth() { return health; }
    public boolean isAlive() { return isAlive; }

    protected void decreaseHealth(double amount) {
        health -= amount;
        if (health <= 0) {
            die();
        }
    }

    public void die() {
        isAlive = false;
        if (location != null) {
            location.removeAnimal(this);
        }
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }

    protected boolean tryToReproduce() {
        return ThreadLocalRandom.current().nextDouble() <
                IslandConfig.getProperties(getClass()).reproductionProbability;
    }
}

