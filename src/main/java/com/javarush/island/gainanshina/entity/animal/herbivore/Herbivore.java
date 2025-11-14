package com.javarush.island.gainanshina.entity.animal.herbivore;

import com.javarush.island.gainanshina.entity.Animal;

public abstract class Herbivore extends Animal {
    public Herbivore(Location location) {
        super(location);
    }

    @Override
    protected Object findFood() {
        if (location.getPlants() > 0) {
            return "plants";
        }

        // Для животных, которые едят и растения и других животных (например, утка)
        for (Class<? extends Animal> preyClass : getAdditionalPreyClasses()) {
            List<Animal> preys = location.getAnimalsByType(preyClass);
            if (!preys.isEmpty()) {
                return preys.get(random.nextInt(preys.size()));
            }
        }
        return null;
    }

    @Override
    protected double getFoodValue(Object food) {
        if ("plants".equals(food)) {
            return getFoodRequired();
        } else if (food instanceof Animal) {
            return ((Animal) food).getWeight();
        }
        return 0;
    }

    @Override
    protected boolean tryToEat(Object food) {
        if ("plants".equals(food)) {
            return true;
        } else if (food instanceof Animal) {
            Animal prey = (Animal) food;
            double probability = getEatProbability(prey.getClass());
            return random.nextDouble() < probability;
        }
        return false;
    }

    @Override
    protected void consumeFood(Object food) {
        if ("plants".equals(food)) {
            double plantsToEat = Math.min(getFoodRequired(), location.getPlants());
            location.setPlants(location.getPlants() - plantsToEat);
        } else if (food instanceof Animal) {
            Animal prey = (Animal) food;
            prey.die();
        }
    }

    protected List<Class<? extends Animal>> getAdditionalPreyClasses() {
        return List.of();
    }
}