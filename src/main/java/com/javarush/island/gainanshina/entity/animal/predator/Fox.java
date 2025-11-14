package com.javarush.island.gainanshina.entity.animal.predator;

import com.javarush.island.gainanshina.entity.Animal;

public class Fox extends Predator {
    public Fox(Location location) { super(location); }
    public double getWeight() { return Configuration.ANIMAL_CONFIGS.get(Fox.class).weight; }
    public int getMaxPerLocation() { return Configuration.ANIMAL_CONFIGS.get(Fox.class).maxPerLocation; }
    public int getMaxSpeed() { return Configuration.ANIMAL_CONFIGS.get(Fox.class).maxSpeed; }
    public double getFoodRequired() { return Configuration.ANIMAL_CONFIGS.get(Fox.class).foodRequired; }
    public int getMaxChildren() { return Configuration.ANIMAL_CONFIGS.get(Fox.class).maxChildren; }

    protected List<Class<? extends Animal>> getPreyClasses() {
        return List.of(Rabbit.class, Mouse.class, Duck.class, Caterpillar.class);
    }

    protected void giveBirth() {
        new Fox(location);
    }
}