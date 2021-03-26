package com.codecool.dungeoncrawl.logic.battle;

import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.actors.Actor;
import com.codecool.dungeoncrawl.logic.actors.Player;
import com.codecool.dungeoncrawl.logic.actors.Skeleton;

public class Battle {


    public Battle() {
    }

    public void fight(Actor actor1, Actor actor2) {
        if (actor1.getHealth() > 0 && actor2.getHealth() > 0) {
            actor2.setHealth(getPlayerAttackResult(actor1, actor2));
            actor1.setHealth(getEnemyAttackResult(actor1, actor2));
        }
        else if (actor1.getHealth() <= 0) {
            actor1.getCell().setType(CellType.ARMOR);
            System.out.println("Game over");
        }
        else if (actor2.getHealth() <= 0) {
            actor2.getCell().setActor(null);
            actor2.getCell().setType(CellType.FLOOR);
        }

    }


    public int getPlayerAttackResult(Actor actor1, Actor actor2) {
        int actor1Attack = actor1.getAttack();
        int actor2Health = actor2.getHealth();

        actor2Health = actor2Health - actor1Attack;

        return actor2Health;
    }

    public int getEnemyAttackResult(Actor actor1, Actor actor2) {
        int actor2Attack = actor2.getAttack();
        int actor1Health = actor1.getHealth() + actor1.getArmor() + actor1.getDefence();

        actor1Health = actor1Health - actor2Attack;

        return actor1Health;
    }


}
