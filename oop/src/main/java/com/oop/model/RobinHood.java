/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oop.model;

import com.oop.characteristic.Damaged;
import com.oop.validator.GameUtils;

public class RobinHood extends Hunter {

    private int bulletCount;

    public RobinHood(final int bulletCount, final Location location) {
        super(location);
        this.bulletCount = bulletCount;
    }

    @Override
    public boolean outOfBullet() {
        return bulletCount <= 0;
    }

    @Override
    public void moveUp(int boundary) {
        final var newLocation = getLocation().transform(0, -1);
        if (!GameUtils.canMoveToNewLocation(newLocation, boundary)) {
            System.out.println("Cannot move up");
            return;
        }
        moveTo(newLocation);
    }

    @Override
    public void moveDown(int boundary) {
        final var newLocation = getLocation().transform(0, 1);
        if (!GameUtils.canMoveToNewLocation(newLocation, boundary)) {
            System.out.println("Cannot move down");
            return;
        }
        moveTo(newLocation);
    }

    @Override
    public void moveLeft(int boundary) {
        final var newLocation = getLocation().transform(-1, 0);
        if (!GameUtils.canMoveToNewLocation(newLocation, boundary)) {
            System.out.println("Cannot move left");
            return;
        }
        moveTo(newLocation);
    }

    @Override
    public void moveRight(int boundary) {
        final var newLocation = getLocation().transform(1, 0);
        if (!GameUtils.canMoveToNewLocation(newLocation, boundary)) {
            System.out.println("Cannot move right");
            return;
        }
        moveTo(newLocation);
    }

    @Override
    public String name() {
        return "H";
    }

    @Override
    public boolean alive() {
        return true;
    }

    @Override
    public void shoot(Damaged character) {
        if (outOfBullet()) {
            System.out.println("Out of bullet");
            return;
        }

        bulletCount -= 1;
        System.out.println("Bullets: " + bulletCount);

        if (character == null) {
            System.out.println("Miss!");
            return;
        }
        character.attackedBy(this);
    }

}
