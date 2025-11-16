package com.javarush.island.gainanshina;

import com.javarush.island.gainanshina.view.SimulationView;

public class Runner {
    public static void main(String[] args) {
        SimulationView simulationView = new SimulationView();
        simulationView.start();

        // Ждем некоторое время или до ручного завершения
        try {
            Thread.sleep(300000); // 5 минут
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        simulationView.stop();
    }
}