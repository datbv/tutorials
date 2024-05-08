/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oop.game;

import com.oop.model.Hunter;
import com.oop.model.Location;
import com.oop.model.Monster;
import com.oop.validator.GameUtils;
import java.util.List;

public class HunterActivity {

  private final Hunter hero;
  private final int boundary;
  private final List<Monster> monsters;
  private final HunterActivityWatcher watcher;

  public HunterActivity(Hunter hero, int boundary, List<Monster> monsters,
      HunterActivityWatcher observer) {
    this.hero = hero;
    this.boundary = boundary;
    this.monsters = monsters;
    this.watcher = observer;
  }

  public void moveUp() {
    final var newLocation = hero.getLocation().transform(0, -1);
    if (!GameUtils.canMoveToNewLocation(newLocation, boundary)) {
      System.out.println("Cannot move up");
    } else {
      System.out.println("Moving up");
      hero.moveUp();
    }
    watcher.onComplete();
  }

  public void moveDown() {
    final var newLocation = hero.getLocation().transform(0, 1);
    if (!GameUtils.canMoveToNewLocation(newLocation, boundary)) {
      System.out.println("Cannot move down");
    } else {
      System.out.println("Moving down");
      hero.moveDown();
    }
    watcher.onComplete();
  }

  public void moveLeft() {
    final var newLocation = hero.getLocation().transform(-1, 0);
    if (!GameUtils.canMoveToNewLocation(newLocation, boundary)) {
      System.out.println("Cannot move left");
    } else {
      System.out.println("Moving left");
      hero.moveLeft();
    }
    watcher.onComplete();
  }

  public void moveRight() {
    final var newLocation = hero.getLocation().transform(1, 0);
    if (!GameUtils.canMoveToNewLocation(newLocation, boundary)) {
      System.out.println("Cannot move right");
    } else {
      System.out.println("Moving right");
      hero.moveRight();
    }

    watcher.onComplete();
  }

  public void shootUp() {
    System.out.println("Shoot up");
    final var heroLocation = hero.getLocation();
    final var shootLocation = heroLocation.transform(0, -1);
    shoot(shootLocation);
  }

  public void shootDown() {
    System.out.println("Shoot down");
    final var heroLocation = hero.getLocation();
    final var shootLocation = heroLocation.transform(0, 1);
    shoot(shootLocation);
  }

  public void shootLeft() {
    System.out.println("Shoot left");
    final var heroLocation = hero.getLocation();
    final var shootLocation = heroLocation.transform(-1, 0);
    shoot(shootLocation);
  }

  public void shootRight() {
    System.out.println("Shoot right");
    final var heroLocation = hero.getLocation();
    final var shootLocation = heroLocation.transform(1, 0);
    shoot(shootLocation);
  }

  private void shoot(Location shootLocation) {
    final var monster = monsters.stream()
        .filter(m -> m.getLocation().equals(shootLocation))
        .findAny()
        .orElse(null);
    hero.shoot(monster);
    if (monster != null && !monster.alive()) {
      System.out.println("Good shoot");
      monsters.remove(monster);
    }
    watcher.onComplete();
  }

}
