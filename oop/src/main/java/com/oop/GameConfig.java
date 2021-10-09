package com.oop;

public class GameConfig {

    private final int numberOfMonsters;
    private final int bulletCount;
    private final int mapSize;
    private final boolean hideMonsterLocation;

    private GameConfig(final int numberOfMonsters, final int bulletCount, final int mapSize, final boolean hideMonsterLocation) {
        this.numberOfMonsters = numberOfMonsters;
        this.bulletCount = bulletCount;
        this.mapSize = mapSize;
        this.hideMonsterLocation = hideMonsterLocation;
    }

    public int getNumberOfMonsters() {
        return numberOfMonsters;
    }

    public int getBulletCount() {
        return bulletCount;
    }

    public int getMapSize() {
        return mapSize;
    }

    public boolean isHideMonsterLocation() {
        return hideMonsterLocation;
    }

    public static GameConfig defaultConfig() {
        return builder().bulletCount(4).numberOfMonsters(4).mapSize(5).hideMonsterLocation(false).build();
    }

    public static GameConfigBuilder builder() {
        return new GameConfigBuilder();
    }

    public static final class GameConfigBuilder {

        private int numberOfMonsters;
        private int bulletCount;
        private int mapSize;
        private boolean hideMonsterLocation;

        public GameConfigBuilder numberOfMonsters(int numberOfMonsters) {
            this.numberOfMonsters = numberOfMonsters;
            return this;
        }

        public GameConfigBuilder bulletCount(int bulletCount) {
            this.bulletCount = bulletCount;
            return this;
        }

        public GameConfigBuilder mapSize(int mapSize) {
            this.mapSize = mapSize;
            return this;
        }

        public GameConfigBuilder hideMonsterLocation(boolean hideMonsterLocation) {
            this.hideMonsterLocation = hideMonsterLocation;
            return this;
        }

        public GameConfig build() {
            return new GameConfig(numberOfMonsters, bulletCount, mapSize, hideMonsterLocation);
        }

    }

}
