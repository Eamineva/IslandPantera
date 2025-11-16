package com.javarush.island.gainanshina.services.simulation;

import com.javarush.island.gainanshina.entities.animals.Animal;
import com.javarush.island.gainanshina.island.Island;
import com.javarush.island.gainanshina.island.Location;
import com.javarush.island.gainanshina.services.config.IslandConfig;
import com.javarush.island.gainanshina.services.statistics.StatisticsService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class SimulationManager {
    private final Island island;
    private final ScheduledExecutorService scheduler;
    private final ExecutorService animalExecutor;
    private final StatisticsService statisticsService;
    private volatile boolean running = false;

    public SimulationManager() {
        this.island = new Island(IslandConfig.WIDTH, IslandConfig.HEIGHT);
        this.scheduler = Executors.newScheduledThreadPool(3);
        this.animalExecutor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        this.statisticsService = new StatisticsService(island);
    }

    public void startSimulation() {
        running = true;
        island.initialize();

        // Задача для роста растений
        scheduler.scheduleAtFixedRate(this::growPlants,
                0, IslandConfig.PLANT_GROWTH_INTERVAL_MS, TimeUnit.MILLISECONDS);

        // Задача для жизненного цикла животных
        scheduler.scheduleAtFixedRate(this::processAnimalLifeCycle,
                0, IslandConfig.SIMULATION_TICK_DELAY_MS, TimeUnit.MILLISECONDS);

        // Задача для статистики
        scheduler.scheduleAtFixedRate(this::printStatistics,
                0, IslandConfig.STATISTICS_INTERVAL_MS, TimeUnit.MILLISECONDS);
    }

    private void growPlants() {
        if (!running) return;

        for (Location location : island.getAllLocations()) {
            location.growPlants();
        }
    }

    private void processAnimalLifeCycle() {
        if (!running) return;

        List<Callable<Void>> tasks = new ArrayList<>();

        for (Location location : island.getAllLocations()) {
            for (Animal animal : new ArrayList<>(location.getAnimals())) {
                tasks.add(() -> {
                    if (animal.isAlive()) {
                        animal.eat();
                        animal.move();
                        animal.reproduce();
                    }
                    return null;
                });
            }
        }

        try {
            animalExecutor.invokeAll(tasks);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void printStatistics() {
        if (!running) return;

        statisticsService.printStatistics();

        // Проверка условия остановки
        if (statisticsService.getTotalAnimals() == 0) {
            System.out.println("Все животные вымерли! Завершение симуляции...");
            stopSimulation();
        }
    }

    public void stopSimulation() {
        running = false;
        scheduler.shutdown();
        animalExecutor.shutdown();
    }
}
