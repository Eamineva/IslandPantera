package com.javarush.island.gainanshina.island;

import com.javarush.island.gainanshina.entities.animals.Animal;
import com.javarush.island.gainanshina.entities.animals.herbivore.*;
import com.javarush.island.gainanshina.entities.animals.predator.*;
import com.javarush.island.gainanshina.entities.plants.Plant;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Island {
    private final Location[][] locations;
    private final int width, height;

    public Island(int width, int height) {
        this.width = width;
        this.height = height;
        this.locations = new Location[width][height];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                locations[x][y] = new Location(x, y, this);
            }
        }
    }
    public Location getLocation(int x, int y) {
        if (isValidCoordinate(x, y)) {
            return locations[x][y];
        }
        return null;
    }

    public boolean isValidCoordinate(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    public List<Location> getAllLocations() {
        List<Location> allLocations = new ArrayList<>();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                allLocations.add(locations[x][y]);
            }
        }
        return allLocations;
    }

    public void initialize() {
        // Начальная инициализация животных и растений
        Random random = new Random();

        for (Location location : getAllLocations()) {
            // Добавляем случайные растения
            for (int i = 0; i < random.nextInt(10); i++) {
                location.addPlant(new Plant());
            }

            // Добавляем случайных животных
            addRandomAnimals(location, Wolf.class, 2, random);
            addRandomAnimals(location, Boa.class, 3, random);
            addRandomAnimals(location, Fox.class, 5, random);
            addRandomAnimals(location, Bear.class, 1, random);
            addRandomAnimals(location, Eagle.class, 2, random);

            // ТРАВОЯДНЫЕ
            addRandomAnimals(location, Horse.class, 3, random);
            addRandomAnimals(location, Deer.class, 4, random);
            addRandomAnimals(location, Rabbit.class, 10, random);
            addRandomAnimals(location, Mouse.class, 20, random);
            addRandomAnimals(location, Goat.class, 8, random);
            addRandomAnimals(location, Sheep.class, 8, random);
            addRandomAnimals(location, Boar.class, 5, random);
            addRandomAnimals(location, Buffalo.class, 2, random);
            addRandomAnimals(location, Duck.class, 15, random);
            addRandomAnimals(location, Caterpillar.class, 30, random);
        }
    }

    private <T extends Animal> void addRandomAnimals(Location location, Class<T> animalClass,
                                                     int maxCount, Random random) {
        int count = random.nextInt(maxCount);
        for (int i = 0; i < count; i++) {
            try {
                Animal animal = animalClass.getDeclaredConstructor().newInstance();
                location.addAnimal(animal);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}