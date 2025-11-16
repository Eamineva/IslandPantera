package com.javarush.island.gainanshina.entities.animals.herbivore;

import com.javarush.island.gainanshina.services.config.IslandConfig;

public class Rabbit extends Herbivore {
    public Rabbit() {
        super(IslandConfig.getProperties(Rabbit.class).weight);
    }
}