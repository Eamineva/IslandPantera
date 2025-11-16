package com.javarush.island.gainanshina.entities.animals.herbivore;

import com.javarush.island.gainanshina.services.config.IslandConfig;

public class Sheep extends Herbivore {
    public Sheep() {
        super(IslandConfig.getProperties(Sheep.class).weight);
    }
}