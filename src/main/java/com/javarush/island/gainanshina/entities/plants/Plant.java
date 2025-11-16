package com.javarush.island.gainanshina.entities.plants;

import com.javarush.island.gainanshina.entities.Entity;
import com.javarush.island.gainanshina.entities.Food;

public class Plant extends Entity implements Food {
    private double weight = 1.0;

    public double getWeight() {
        return weight;
    }

    public void beEaten(double amount) {
        weight -= amount;
        if (weight <= 0) {
            location.removePlant(this);
        }
    }
}
