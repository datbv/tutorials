/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oop.game;

import com.oop.GameConfig;
import com.oop.model.Character;
import com.oop.model.Bear;
import com.oop.model.Hunter;
import com.oop.model.Monster;
import com.oop.model.RobinHood;
import com.oop.model.Location;
import com.oop.validator.GameUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Game {

    private final Hunter hero;
    private final GameConfig gameConfig;
    private final List<Monster> monsters;

    private final HunterActivity heroActivity;

    public Game(GameConfig config) {
        gameConfig = config;
        hero = generateHero(config.getBulletCount());
        monsters = generateMonsters(hero, config.getNumberOfMonsters());
        heroActivity = new HunterActivity(hero, gameConfig.getMapSize(), monsters, this::printMap);

        printMap();
    }

    public HunterActivity hero() {
        return heroActivity;
    }

    private Hunter generateHero(int bulletCount) {
        final Location location = GameUtils.generateRandomLocation(gameConfig.getMapSize());
        return new RobinHood(bulletCount, location);
    }

    private List<Monster> generateMonsters(Hunter hero, int noOfMonsters) {
        final var generatedMonsters = new ArrayList<Monster>(noOfMonsters);
        for (int i = 0; i < noOfMonsters; i++) {
            final var ignoreLocations = generatedMonsters.stream().map(Monster::getLocation).collect(Collectors.toList());
            ignoreLocations.add(hero.getLocation());
            final var monsterLocation = GameUtils.generateRandomLocation(gameConfig.getMapSize(), ignoreLocations);
            final var bat = new Bear(monsterLocation);
            generatedMonsters.add(bat);
        }
        return generatedMonsters;
    }

    private void printMap() {
        System.out.println("\n");
        System.out.println("-------------------------------------------------\n");
        for (int y = 0; y < gameConfig.getMapSize(); y++) {
            for (int x = 0; x < gameConfig.getMapSize(); x++) {
                System.out.print(printText(x, y));
                System.out.print(" ");
            }
            System.out.println();
        }

        if (monsters.isEmpty()) {
            win();
            return;
        }

        if (hero.getLocation().nearAnyLocation(monsters.stream().map(Character::getLocation).collect(Collectors.toList()))) {
            System.out.println("Near the monster");
        }

        checkHeroIsOnMonster();
        checkHeroIsOutOfBullet();
    }

    private void checkHeroIsOnMonster() {
        boolean stepOnMonster = monsters.stream().map(Monster::getLocation).anyMatch(l -> l.equals(hero.getLocation()));
        if (stepOnMonster) {
            lose();
        }
    }

    private void checkHeroIsOutOfBullet() {
        if (hero.outOfBullet()) {
            lose();
        }
    }

    private void lose() {
        System.out.println("Game over!!!");
        System.exit(1);
    }

    private void win() {
        System.out.println("Win!!!");
        System.exit(0);

    }

    private String printText(int x, int y) {
        final var currentLocation = new Location(x, y);
        if (currentLocation.equals(hero.getLocation())) {
            return hero.name();
        }

        final var monsterOpt = monsters.stream().filter(monster -> monster.getLocation().equals(currentLocation)).findFirst();
        if (monsterOpt.isEmpty()) {
            return "-";
        }
        if (gameConfig.isHideMonsterLocation()) {
            return "-";
        }
        return monsterOpt.get().name();
    }

}
