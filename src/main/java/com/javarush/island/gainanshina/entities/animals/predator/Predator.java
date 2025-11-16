package com.javarush.island.gainanshina.entities.animals.predator;

import com.javarush.island.gainanshina.entities.animals.Animal;
import com.javarush.island.gainanshina.island.Location;
import com.javarush.island.gainanshina.services.config.IslandConfig;
import java.util.concurrent.ThreadLocalRandom;

public abstract class Predator extends Animal {
    public Predator(double weight) {
        super(weight);
    }

    @Override
    public void eat() {
        if (location == null) return;

        for (Animal animal : location.getAnimals()) {
            if (animal != this && canEat(animal) && ThreadLocalRandom.current().nextInt(100) <
                    IslandConfig.getEatingProbability(getClass(), animal.getClass())) {

                double foodRequired = IslandConfig.getProperties(getClass()).foodRequired;
                double foodEaten = Math.min(animal.getWeight(), foodRequired);

                animal.die();
                health = Math.min(100, health + (foodEaten / foodRequired) * 100);
                return;
            }
        }

        decreaseHealth(10);
    }

    protected abstract boolean canEat(Animal animal);

    @Override
    public void move() {
        if (location == null) return;

        int maxSpeed = IslandConfig.getProperties(getClass()).maxSpeed;
        if (maxSpeed > 0) {
            Location newLocation = location.getRandomNeighbor(maxSpeed);
            if (newLocation != null && newLocation.canAddAnimal(this)) {
                location.removeAnimal(this);
                newLocation.addAnimal(this);
                location = newLocation;
            }
        }
    }

    @Override
    public void reproduce() {
        if (location == null || !tryToReproduce()) return;

        int maxPerLocation = IslandConfig.getProperties(getClass()).maxPerLocation;
        if (location.getAnimalsOfType(getClass()).size() < maxPerLocation) {
            try {
                Animal offspring = getClass().getDeclaredConstructor().newInstance();
                location.addAnimal(offspring);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}