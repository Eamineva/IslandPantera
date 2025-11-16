package com.javarush.island.gainanshina.entities.animals.predator;

import com.javarush.island.gainanshina.entities.animals.Animal;
import com.javarush.island.gainanshina.entities.animals.herbivore.Caterpillar;
import com.javarush.island.gainanshina.services.config.IslandConfig;

public class Bear extends Predator {
    public Bear() {
        super(IslandConfig.getProperties(Bear.class).weight);
    }

    @Override
    protected boolean canEat(Animal animal) {
        return !(animal instanceof Predator && !(animal instanceof Boa)) &&
                !(animal instanceof Caterpillar);
    }
}
