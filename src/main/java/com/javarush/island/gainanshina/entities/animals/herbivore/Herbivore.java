package com.javarush.island.gainanshina.entities.animals.herbivore;

import com.javarush.island.gainanshina.entities.animals.Animal;
import com.javarush.island.gainanshina.entities.plants.Plant;
import com.javarush.island.gainanshina.island.Location;
import com.javarush.island.gainanshina.services.config.IslandConfig;
import java.util.concurrent.ThreadLocalRandom;


public abstract class Herbivore extends Animal {
    public Herbivore(double weight) {
        super(weight);
    }

    @Override
    public void eat() {
        if (location == null) return;

        // Пытаемся съесть растения
        for (Plant plant : location.getPlants()) {
            double foodRequired = IslandConfig.getProperties(getClass()).foodRequired;
            double foodEaten = Math.min(plant.getWeight(), foodRequired);

            plant.beEaten(foodEaten);
            health = Math.min(100, health + (foodEaten / foodRequired) * 100);
            return;
        }

        // Если это утка, мышь, кабан, может съесть гусеницу
        if (this instanceof Duck || this instanceof Mouse || this instanceof Boar) {
            for (Animal animal : location.getAnimals()) {
                if (animal instanceof Caterpillar && ThreadLocalRandom.current().nextInt(100) < 90) {
                    animal.die();
                    health = Math.min(100, health + 25);
                    return;
                }
            }
        }

        // Если не нашли еду, теряем здоровье
        decreaseHealth(10);
    }

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