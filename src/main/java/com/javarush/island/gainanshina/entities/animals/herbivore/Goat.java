package com.javarush.island.gainanshina.entities.animals.herbivore;

import com.javarush.island.gainanshina.services.config.IslandConfig;

public class Goat extends Herbivore {
    public Goat() {
        super(IslandConfig.getProperties(Goat.class).weight);
    }
}