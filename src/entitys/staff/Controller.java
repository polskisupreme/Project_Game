package entitys.staff;

import core.GameEngine;
import entitys.Cloud;
import entitys.ducks.Ducks;
import entitys.ducks.Ducks2;
import entitys.ducks.Ducks3;
import entitys.ducks.Ducks4;
import graphics.SpriteTextures;

import java.awt.*;
import java.util.LinkedList;
import java.util.Random;

public class Controller {

    private final LinkedList<EntityPlayer> entitiesPlayer = new LinkedList<>();
    private final LinkedList<EntityEnemy> entitiesEnemy = new LinkedList<>();

    Random random = new Random();

    EntityPlayer entityPlayer;
    EntityEnemy entityEnemy;
    GameEngine gameEngine;
    SpriteTextures textures;

    public Controller(GameEngine gameEngine, SpriteTextures textures){
        this.gameEngine = gameEngine;
        this.textures = textures;
    }

    public void createEnemy(int enemyCount){
        int randomizer = random.nextInt(4) + 1;

        for (int i = 0; i < enemyCount; i++){
            switch (randomizer) {
                case 1 -> addEntity(new Ducks(random.nextInt(100) - 200, random.nextInt((GameEngine.screenHeight / 2) * GameEngine.scale), textures, this, gameEngine));
                case 2 -> addEntity(new Ducks2(random.nextInt(100) - 200, random.nextInt((GameEngine.screenHeight / 2) * GameEngine.scale), textures, this, gameEngine));
                case 3 -> addEntity(new Ducks3(random.nextInt(100) - 200, random.nextInt((GameEngine.screenHeight / 2) * GameEngine.scale), textures, this, gameEngine));
                case 4 -> addEntity(new Ducks4(random.nextInt(100) - 200, random.nextInt((GameEngine.screenHeight / 2) * GameEngine.scale), textures, this, gameEngine));
            }
        }
    }

    public void createObjects(){
        addEntity(new Cloud(random.nextInt(100) - 200, 300, textures, this, gameEngine));
    }

    public void update(){
        for (int i = 0; i < entitiesPlayer.size(); i++){
            entityPlayer = entitiesPlayer.get(i);

            entityPlayer.update();
        }
        for (int i = 0; i < entitiesEnemy.size(); i++){
            entityEnemy = entitiesEnemy.get(i);

            entityEnemy.update();
        }
    }

    public void render(Graphics g) {
        for (int i = 0; i < entitiesPlayer.size(); i++){
            entityPlayer = entitiesPlayer.get(i);

            entityPlayer.render(g);
        }

        for (int i = 0; i < entitiesEnemy.size(); i++){
            entityEnemy = entitiesEnemy.get(i);

            entityEnemy.render(g);
        }
    }

    public void addEntity(EntityPlayer e){
        entitiesPlayer.add(e);
    }
    public void removeEntity(EntityPlayer e){
        entitiesPlayer.remove(e);
    }

    public void addEntity(EntityEnemy e){
        entitiesEnemy.add(e);
    }
    public void removeEntity(EntityEnemy e){
        GameEngine.enemyKilled++;
        entitiesEnemy.remove(e);
    }

    public LinkedList<EntityPlayer> getEntitiesPlayer(){
        return entitiesPlayer;
    }
    public LinkedList<EntityEnemy> getEntitiesEnemy(){
        return entitiesEnemy;
    }
}
