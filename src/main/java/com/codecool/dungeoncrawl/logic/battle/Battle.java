package com.codecool.dungeoncrawl.logic.battle;

import com.codecool.dungeoncrawl.logic.CellType;
import com.codecool.dungeoncrawl.logic.actors.Actor;

public class Battle {


    public Battle() {
    }

    public void fight(Actor player, Actor enemy) {
        int playerHealth = getEnemyAttackResult(player, enemy);
        int enemyHealth = getPlayerAttackResult(player, enemy);

        if (playerHealth <= 0) {
            System.out.println("Game over");
            System.exit(0);
        }
        if (enemyHealth <= 0) {
            enemy.getCell().setActor(null);
            enemy.getCell().setType(CellType.FLOOR);
        } else {
            enemy.setHealth(getPlayerAttackResult(player, enemy));
            player.setHealth(getEnemyAttackResult(player, enemy));
        }
    }


    public int getPlayerAttackResult(Actor player, Actor enemy) {
        int playerAttack = player.getAttack();
        int enemyHealth = enemy.getHealth();

        return enemyHealth - playerAttack;
    }

    public int getEnemyAttackResult(Actor player, Actor enemy) {
        int enemyAttack = enemy.getAttack();
        int playerHealth = player.getHealth() + player.getArmor() + player.getDefence();

        return playerHealth - enemyAttack;
    }


}
