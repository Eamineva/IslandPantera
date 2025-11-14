package com.javarush.island.gainanshina.entity.animal.predator;

import com.javarush.island.gainanshina.entity.Animal;

public abstract class Predator extends Animal {
    public Predator(Location location) {
        super(location);
    }

    @Override
    protected Object findFood() {
        // Поиск травоядных
        for (Class<? extends Animal> preyClass : getPreyClasses()) {
            List<Animal> preys = location.getAnimalsByType(preyClass);
            if (!preys.isEmpty()) {
                return preys.get(random.nextInt(preys.size()));
            }
        }
        return null;
    }

    @Override
    protected double getFoodValue(Object food) {
        if (food instanceof Animal) {
            return ((Animal) food).getWeight();
        }
        return 0;
    }

    @Override
    protected boolean tryToEat(Object food) {
        if (food instanceof Animal) {
            Animal prey = (Animal) food;
            double probability = getEatProbability(prey.getClass());
            return random.nextDouble() < probability;
        }
        return false;
    }

    @Override
    protected void consumeFood(Object food) {
        if (food instanceof Animal) {
            Animal prey = (Animal) food;
            prey.die();
        }
    }

    protected abstract List<Class<? extends Animal>> getPreyClasses();
}