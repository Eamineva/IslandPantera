package com.javarush.island.gainanshina.entities.animals.predator;

import com.javarush.island.gainanshina.entities.animals.Animal;
import com.javarush.island.gainanshina.entities.animals.herbivore.Caterpillar;
import com.javarush.island.gainanshina.entities.animals.herbivore.Herbivore;
import com.javarush.island.gainanshina.services.config.IslandConfig;

public class Wolf extends Predator {
    public Wolf() {
        super(IslandConfig.getProperties(Wolf.class).weight);
    }

    @Override
    protected boolean canEat(Animal animal) {
        return animal instanceof Herbivore && !(animal instanceof Caterpillar);
    }
}