/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oop.model;

public abstract class Character {

    private final Location location;

    protected Character(final Location location) {
        this.location = location;
    }

    public Location getLocation() {
        return Location.clone(location);
    }

    public void moveTo(Location newLocation) {
        location.setX(newLocation.getX());
        location.setY(newLocation.getY());
    }

    public abstract boolean alive();

    public abstract String name();

}
