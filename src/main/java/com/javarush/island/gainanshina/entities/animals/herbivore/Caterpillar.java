package com.javarush.island.gainanshina.entities.animals.herbivore;

import com.javarush.island.gainanshina.services.config.IslandConfig;

public class Caterpillar extends Herbivore {
    public Caterpillar() {
        super(IslandConfig.getProperties(Caterpillar.class).weight);
    }

    @Override
    public void move() {
        // Гусеница не перемещается
    }
}