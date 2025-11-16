package com.javarush.island.gainanshina.entities.animals.herbivore;

import com.javarush.island.gainanshina.services.config.IslandConfig;

public class Duck extends Herbivore {
    public Duck() {
        super(IslandConfig.getProperties(Duck.class).weight);
    }
}