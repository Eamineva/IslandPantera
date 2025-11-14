package com.javarush.island.gainanshina.entity;

import java.util.Random;

public abstract class Animal {
    protected static final Random random = new Random();

    protected Location location;
    protected double satiety;
    protected boolean isAlive = true;

    public Animal(Location location) {
        this.location = location;
        this.satiety = getMaxSatiety() * 0.5;
        location.addAnimal(this);
    }

    public abstract double getWeight();
    public abstract int getMaxPerLocation();
    public abstract int getMaxSpeed();
    public abstract double getFoodRequired();
    public abstract int getMaxChildren();

    public double getMaxSatiety() {
        return getFoodRequired();
    }

    public void eat() {
        if (!isAlive) return;

        // Поиск пищи
        Object food = findFood();
        if (food != null) {
            double foodValue = getFoodValue(food);
            if (tryToEat(food)) {
                satiety = Math.min(getMaxSatiety(), satiety + foodValue);
                consumeFood(food);
            }
        }
    }

    protected abstract Object findFood();
    protected abstract double getFoodValue(Object food);
    protected abstract boolean tryToEat(Object food);
    protected abstract void consumeFood(Object food);

    public void move() {
        if (!isAlive || getMaxSpeed() == 0) return;

        int speed = random.nextInt(getMaxSpeed()) + 1;
        Island island = Island.getInstance();

        for (int i = 0; i < speed; i++) {
            Location newLocation = island.getRandomAdjacentLocation(location);
            if (newLocation != null && canMoveTo(newLocation)) {
                location.removeAnimal(this);
                this.location = newLocation;
                newLocation.addAnimal(this);
                break;
            }
        }
    }

    protected boolean canMoveTo(Location newLocation) {
        Class<? extends Animal> animalClass = this.getClass();
        int currentCount = newLocation.getAnimalsByType(animalClass).size();
        return currentCount < getMaxPerLocation();
    }

    public void reproduce() {
        if (!isAlive) return;

        List<Animal> sameTypeAnimals = location.getAnimalsByType(this.getClass());
        if (sameTypeAnimals.size() >= 2) {
            Animal partner = findPartner(sameTypeAnimals);
            if (partner != null && random.nextDouble() < 0.5) {
                int childrenCount = random.nextInt(getMaxChildren()) + 1;
                for (int i = 0; i < childrenCount; i++) {
                    if (location.getAnimalsByType(this.getClass()).size() < getMaxPerLocation()) {
                        giveBirth();
                    }
                }
            }
        }
    }

    protected Animal findPartner(List<Animal> sameTypeAnimals) {
        return sameTypeAnimals.stream()
                .filter(animal -> animal != this && animal.isAlive)
                .findFirst()
                .orElse(null);
    }

    protected abstract void giveBirth();

    public void decreaseSatiety() {
        if (!isAlive) return;

        satiety -= getFoodRequired() * 0.1;
        if (satiety <= 0) {
            die();
        }
    }

    protected void die() {
        isAlive = false;
        location.removeAnimal(this);
    }

    public boolean isAlive() { return isAlive; }
    public Location getLocation() { return location; }

    protected double getEatProbability(Class<? extends Animal> preyClass) {
        Integer predatorIndex = Configuration.ANIMAL_INDEXES.get(this.getClass());
        Integer preyIndex = Configuration.ANIMAL_INDEXES.get(preyClass);

        if (predatorIndex != null && preyIndex != null) {
            return Configuration.EAT_PROBABILITIES[predatorIndex][preyIndex] / 100.0;
        }
        return 0.0;
    }
}