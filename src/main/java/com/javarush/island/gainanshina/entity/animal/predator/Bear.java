package com.javarush.island.gainanshina.entity.animal.predator;

import com.javarush.island.gainanshina.entity.Animal;

public class Bear extends Predator {
    public Bear(Location location) { super(location); }
    public double getWeight() { return Configuration.ANIMAL_CONFIGS.get(Bear.class).weight; }
    public int getMaxPerLocation() { return Configuration.ANIMAL_CONFIGS.get(Bear.class).maxPerLocation; }
    public int getMaxSpeed() { return Configuration.ANIMAL_CONFIGS.get(Bear.class).maxSpeed; }
    public double getFoodRequired() { return Configuration.ANIMAL_CONFIGS.get(Bear.class).foodRequired; }
    public int getMaxChildren() { return Configuration.ANIMAL_CONFIGS.get(Bear.class).maxChildren; }

    protected List<Class<? extends Animal>> getPreyClasses() {
        return List.of(Boa.class, Horse.class, Deer.class, Rabbit.class, Mouse.class,
                Goat.class, Sheep.class, Boar.class, Buffalo.class, Duck.class);
    }

    protected void giveBirth() {
        new Bear(location);
    }
}