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
  public void moveUp() {
    final var newLocation = getLocation().transform(0, -1);
    moveTo(newLocation);
  }

  @Override
  public void moveDown() {
    final var newLocation = getLocation().transform(0, 1);
    moveTo(newLocation);
  }

  @Override
  public void moveLeft() {
    final var newLocation = getLocation().transform(-1, 0);
    moveTo(newLocation);
  }

  @Override
  public void moveRight() {
    final var newLocation = getLocation().transform(1, 0);
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
