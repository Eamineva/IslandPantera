package com.javarush.island.gainanshina.entities.animals.herbivore;

import com.javarush.island.gainanshina.services.config.IslandConfig;

public class Buffalo extends Herbivore {
    public Buffalo() {
        super(IslandConfig.getProperties(Buffalo.class).weight);
    }
}