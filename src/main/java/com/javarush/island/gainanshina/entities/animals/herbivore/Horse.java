package com.javarush.island.gainanshina.entities.animals.herbivore;

import com.javarush.island.gainanshina.services.config.IslandConfig;

public class Horse extends Herbivore {
    public Horse() {
        super(IslandConfig.getProperties(Horse.class).weight);
    }
}