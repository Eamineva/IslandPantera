package com.javarush.island.gainanshina.entities.animals.herbivore;

import com.javarush.island.gainanshina.services.config.IslandConfig;

public class Boar extends Herbivore {
    public Boar() {
        super(IslandConfig.getProperties(Boar.class).weight);
    }
}