package com.javarush.island.gainanshina.entities.animals.herbivore;

import com.javarush.island.gainanshina.services.config.IslandConfig;

public class Deer extends Herbivore {
    public Deer() {
        super(IslandConfig.getProperties(Deer.class).weight);
    }
}