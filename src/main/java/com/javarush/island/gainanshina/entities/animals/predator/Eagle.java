package com.javarush.island.gainanshina.entities.animals.predator;

import com.javarush.island.gainanshina.entities.animals.Animal;
import com.javarush.island.gainanshina.entities.animals.herbivore.Duck;
import com.javarush.island.gainanshina.entities.animals.herbivore.Mouse;
import com.javarush.island.gainanshina.entities.animals.herbivore.Rabbit;
import com.javarush.island.gainanshina.services.config.IslandConfig;

public class Eagle extends Predator {
    public Eagle() {
        super(IslandConfig.getProperties(Eagle.class).weight);
    }

    @Override
    protected boolean canEat(Animal animal) {
        return animal instanceof Fox || animal instanceof Rabbit ||
                animal instanceof Mouse || animal instanceof Duck;
    }
}