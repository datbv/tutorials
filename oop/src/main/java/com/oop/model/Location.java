/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oop.model;

import java.util.Collection;

public class Location {

    private int x;
    private int y;

    public static Location clone(Location location) {
        return new Location(location.getX(), location.getY());
    }

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Location transform(int x, int y) {
        return new Location(this.x + x, this.y + y);
    }

    public void move(int x, int y) {
        this.x += x;
        this.y += y;
    }

    @Override
    public boolean equals(Object point) {
        if (point == null) {
            return false;
        }
        if (point instanceof Location) {
            return (x == ((Location) point).x) && (y == ((Location) point).y);
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.x;
        hash = 37 * hash + this.y;
        return hash;
    }

    public <T extends Location> boolean nearAnyLocation(Collection<T> locations) {
        return locations.stream().anyMatch(l -> {
            if (l.getX() == this.getX()) {
                if (l.getY() == this.getY() - 1 || l.getY() == this.getY() + 1) {
                    return true;
                }
            }
            if (l.getY() == this.getY()) {
                if (l.getX() == this.getX() - 1 || l.getX() == this.getX() + 1) {
                    return true;
                }
            }
            if (l.getX() == this.getX() + 1 || l.getX() == this.getX() - 1) {
                return l.getY() == this.getY() + 1 || l.getY() == this.getY() - 1;
            }
            return false;
        });
    }

}
