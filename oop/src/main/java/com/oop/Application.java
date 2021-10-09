/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oop;

import com.oop.game.Game;
import com.oop.validator.GameUtils;

import java.util.Scanner;

public class Application {

    public static void main(String[] args) {

        printGuideLine();
        var console = new Scanner(System.in);

        final var gameConfig = GameConfig.defaultConfig();
        GameUtils.validateGameConfig(gameConfig);

        final var game = new Game(gameConfig);

        String input = console.nextLine();
        while (!"e".equals(input)) {
            switch (input) {
                case "w":
                    game.hero().moveUp();
                    break;
                case "s":
                    game.hero().moveDown();
                    break;
                case "a":
                    game.hero().moveLeft();
                    break;
                case "d":
                    game.hero().moveRight();
                    break;
                case "i":
                    game.hero().shootUp();
                    break;
                case "k":
                    game.hero().shootDown();
                    break;
                case "j":
                    game.hero().shootLeft();
                    break;
                case "l":
                    game.hero().shootRight();
                    break;
                default:
                    break;
            }
            console = new Scanner(System.in);
            input = console.nextLine();
        }
        System.out.println("Exit!");
    }

    private static void printGuideLine() {
        System.out.println("H is hero");
        System.out.println("X is monster");
        System.out.println("When H is near X, print: Near the monster");
        System.out.println("When H reach X: Game over");

        System.out.println("");
        System.out.println("Move up/down/left/right by: w/s/a/d");
        System.out.println("Shoot up/down/left/right by: i/k/j/l");
        System.out.println("Exit by: e");
    }

}
