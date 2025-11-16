package com.javarush.island.gainanshina.entities;

import com.javarush.island.gainanshina.island.Location;

public abstract class Entity {
    protected Location location;

    public void setLocation(Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return location;
    }
    public boolean hasLocation() {
        return location != null;
    }
}
