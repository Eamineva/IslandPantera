package com.javarush.island.gainanshina.services.statistics;

import com.javarush.island.gainanshina.entities.animals.Animal;
import com.javarush.island.gainanshina.island.Island;
import com.javarush.island.gainanshina.island.Location;

import java.util.HashMap;
import java.util.Map;

public class StatisticsService {
    private final Island island;

    public StatisticsService(Island island) {
        this.island = island;
    }

    public void printStatistics() {
        Map<Class<? extends Animal>, Integer> animalCounts = getAnimalCounts();
        int totalPlants = getTotalPlants();
        int totalAnimals = getTotalAnimals();

        System.out.println("\n=== СТАТИСТИКА ОСТРОВА ===");
        System.out.printf("Всего растений: %d\n", totalPlants);
        System.out.printf("Всего животных: %d\n", totalAnimals);
        System.out.println("Распределение животных:");

        animalCounts.entrySet().stream()
                .sorted(Map.Entry.<Class<? extends Animal>, Integer>comparingByValue().reversed())
                .forEach(entry -> System.out.printf("  %s: %d\n",
                        entry.getKey().getSimpleName(), entry.getValue()));

        System.out.println("========================");
    }

    public Map<Class<? extends Animal>, Integer> getAnimalCounts() {
        Map<Class<? extends Animal>, Integer> animalCounts = new HashMap<>();

        for (Location location : island.getAllLocations()) {
            for (Animal animal : location.getAnimals()) {
                animalCounts.merge(animal.getClass(), 1, Integer::sum);
            }
        }

        return animalCounts;
    }

    public int getTotalPlants() {
        int total = 0;
        for (Location location : island.getAllLocations()) {
            total += location.getPlants().size();
        }
        return total;
    }

    public int getTotalAnimals() {
        int total = 0;
        for (Location location : island.getAllLocations()) {
            total += location.getAnimals().size();
        }
        return total;
    }
}