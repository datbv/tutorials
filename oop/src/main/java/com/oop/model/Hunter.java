/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oop.model;

import com.oop.characteristic.Attackable;
import com.oop.characteristic.Movable;

public abstract class Hunter extends Character implements Movable, Attackable {

    protected Hunter(final Location location) {
        super(location);
    }

    public abstract boolean outOfBullet();

}
