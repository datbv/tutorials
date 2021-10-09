/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oop.model;

import com.oop.characteristic.Damaged;

public abstract class Monster extends Character implements Damaged {

    protected Monster(final Location location) {
        super(location);
    }

}
