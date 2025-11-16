package com.javarush.island.gainanshina.entities.animals.herbivore;

import com.javarush.island.gainanshina.services.config.IslandConfig;

public class Mouse extends Herbivore {
    public Mouse() {
        super(IslandConfig.getProperties(Mouse.class).weight);
    }
}