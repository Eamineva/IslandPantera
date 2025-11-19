package com.javarush.island.gainanshina.view;


import com.javarush.island.gainanshina.services.simulation.SimulationManager;

public class SimulationView {
    private final SimulationManager simulationManager;

    public SimulationView() {
        this.simulationManager = new SimulationManager();
    }

    public void start() {
        System.out.println("=== СИМУЛЯЦИЯ ОСТРОВА ===");
        System.out.println("Запуск симуляции.");

        simulationManager.startSimulation();

        // Добавляем обработчик завершения
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("Завершение симуляции.");
            simulationManager.stopSimulation();
        }));
    }

    public void stop() {
        simulationManager.stopSimulation();
        System.out.println("Симуляция завершена.");
    }
}