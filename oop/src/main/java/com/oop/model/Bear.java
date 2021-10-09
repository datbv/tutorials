/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oop.model;

public class Bear extends Monster {

    private int hp;

    public Bear(final Location location) {
        super(location);
        hp = 100;
    }

    @Override
    public boolean alive() {
        return hp > 0;
    }

    @Override
    public String name() {
        return "X";
    }

    @Override
    public void beAttacked(int hp) {
        this.hp -= hp;
    }

    @Override
    public void attackedBy(Hunter hunter) {
        beAttacked(100);
    }

}
