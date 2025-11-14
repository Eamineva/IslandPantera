package com.javarush.island.gainanshina.entity.animal.herbivore;

public class Mouse extends Herbivore {
    public Mouse(Location location) { super(location); }
    public double getWeight() { return Configuration.ANIMAL_CONFIGS.get(Mouse.class).weight; }
    public int getMaxPerLocation() { return Configuration.ANIMAL_CONFIGS.get(Mouse.class).maxPerLocation; }
    public int getMaxSpeed() { return Configuration.ANIMAL_CONFIGS.get(Mouse.class).maxSpeed; }
    public double getFoodRequired() { return Configuration.ANIMAL_CONFIGS.get(Mouse.class).foodRequired; }
    public int getMaxChildren() { return Configuration.ANIMAL_CONFIGS.get(Mouse.class).maxChildren; }

    protected List<Class<? extends Animal>> getAdditionalPreyClasses() {
        return List.of(Caterpillar.class);
    }

    protected void giveBirth() {
        new Mouse(location);
    }
}