package com.javarush.island.gainanshina.entity.animal.herbivore;

public class Rabbit extends Herbivore {
    public Rabbit(Location location) { super(location); }
    public double getWeight() { return Configuration.ANIMAL_CONFIGS.get(Rabbit.class).weight; }
    public int getMaxPerLocation() { return Configuration.ANIMAL_CONFIGS.get(Rabbit.class).maxPerLocation; }
    public int getMaxSpeed() { return Configuration.ANIMAL_CONFIGS.get(Rabbit.class).maxSpeed; }
    public double getFoodRequired() { return Configuration.ANIMAL_CONFIGS.get(Rabbit.class).foodRequired; }
    public int getMaxChildren() { return Configuration.ANIMAL_CONFIGS.get(Rabbit.class).maxChildren; }

    protected void giveBirth() {
        new Rabbit(location);
    }
}
