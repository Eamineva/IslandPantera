package com.javarush.island.gainanshina.island;

import com.javarush.island.gainanshina.entities.Food;
import com.javarush.island.gainanshina.entities.animals.Animal;
import com.javarush.island.gainanshina.entities.plants.Plant;
import com.javarush.island.gainanshina.services.config.IslandConfig;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class Location {
    private final int x, y;
    private final List<Animal> animals = new CopyOnWriteArrayList<>();
    private final List<Plant> plants = new CopyOnWriteArrayList<>();
    private final Island island;

    public Location(int x, int y, Island island) {
        this.x = x;
        this.y = y;
        this.island = island;
    }

    public List<Food> getAllFood() {
        List<Food> food = new ArrayList<>();
        food.addAll(plants);
        food.addAll(animals);
        return food;
    }
    public void addAnimal(Animal animal) {
        if (canAddAnimal(animal)) {
            animals.add(animal);
            animal.setLocation(this);
        }
    }

    public void removeAnimal(Animal animal) {
        animals.remove(animal);
    }

    public void addPlant(Plant plant) {
        if (plants.size() < IslandConfig.MAX_PLANTS_PER_LOCATION) {
            plants.add(plant);
            plant.setLocation(this);
        }
    }

    public void removePlant(Plant plant) {
        plants.remove(plant);
    }

    public boolean canAddAnimal(Animal animal) {
        Class<? extends Animal> animalClass = animal.getClass();
        int maxPerLocation = IslandConfig.getProperties(animalClass).maxPerLocation;
        return getAnimalsOfType(animalClass).size() < maxPerLocation;
    }

    public List<Animal> getAnimals() {
        return Collections.unmodifiableList(animals);
    }

    public List<Plant> getPlants() {
        return Collections.unmodifiableList(plants);
    }

    public <T extends Animal> List<T> getAnimalsOfType(Class<T> type) {
        List<T> result = new ArrayList<>();
        for (Animal animal : animals) {
            if (type.isInstance(animal)) {
                result.add(type.cast(animal));
            }
        }
        return result;
    }

    public Location getRandomNeighbor(int maxDistance) {
        List<Location> neighbors = new ArrayList<>();

        for (int dx = -maxDistance; dx <= maxDistance; dx++) {
            for (int dy = -maxDistance; dy <= maxDistance; dy++) {
                if (dx == 0 && dy == 0) continue;

                int newX = x + dx;
                int newY = y + dy;

                if (island.isValidCoordinate(newX, newY)) {
                    neighbors.add(island.getLocation(newX, newY));
                }
            }
        }

        return neighbors.isEmpty() ? null :
                neighbors.get(ThreadLocalRandom.current().nextInt(neighbors.size()));
    }

    public void growPlants() {
        if (plants.size() < IslandConfig.MAX_PLANTS_PER_LOCATION &&
                ThreadLocalRandom.current().nextBoolean()) {
            addPlant(new Plant());
        }
    }

    public int getX() {return x; }
    public int getY() { return y; }
}