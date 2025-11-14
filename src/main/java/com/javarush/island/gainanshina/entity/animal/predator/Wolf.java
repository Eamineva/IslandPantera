package com.javarush.island.gainanshina.entity.animal.predator;

import com.javarush.island.gainanshina.entity.Animal;

public class Wolf extends Predator {
    public Wolf(Location location) { super(location); }
    public double getWeight() { return Configuration.ANIMAL_CONFIGS.get(Wolf.class).weight; }
    public int getMaxPerLocation() { return Configuration.ANIMAL_CONFIGS.get(Wolf.class).maxPerLocation; }
    public int getMaxSpeed() { return Configuration.ANIMAL_CONFIGS.get(Wolf.class).maxSpeed; }
    public double getFoodRequired() { return Configuration.ANIMAL_CONFIGS.get(Wolf.class).foodRequired; }
    public int getMaxChildren() { return Configuration.ANIMAL_CONFIGS.get(Wolf.class).maxChildren; }

    protected List<Class<? extends Animal>> getPreyClasses() {
        return List.of(Rabbit.class, Mouse.class, Goat.class, Sheep.class, Deer.class,
                Horse.class, Boar.class, Buffalo.class, Duck.class);
    }

    protected void giveBirth() {
        new Wolf(location);
    }
}
