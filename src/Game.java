import java.util.Scanner;
import java.util.Random;

abstract class Player {
    protected double health = 100;
    protected double attackDamage;
    protected String type;
    protected double attackDamageMultiplier;
    protected double healthGivenModifier;

    public void calculateAttackDamage() {
        Random rand = new Random();
        attackDamage = Math.round(rand.nextInt((50 - 10) + 1) + 10 * attackDamageMultiplier);

    }

    public void calculateHP() {

        this.health = Math.round(this.health * healthGivenModifier);
    }

    public abstract void attack(Player opponent);

    public boolean isAlive() {
        return this.health > 0;
    }

    public void takeDamage(double damage) {
        this.health -= damage;
    }

    public String getType() {
        return type;
    }
}

class PlayerFactory {
    private static PlayerFactory instance;

    private PlayerFactory() {
    }

  
    public static synchronized PlayerFactory getInstance() {
        // If the instance doesn't exist, create it.
        if (instance == null) {
            instance = new PlayerFactory();
        }
        return instance;
    }

    public Player getPlayer(String playerType) {
        return switch (playerType.toLowerCase()) {
            case "mage" -> new Mage();
            case "marksmen" -> new Marksmen();
            case "fighter" -> new Fighter();
            case "tank" -> new Tank();
            case "assassin" -> new Assassin();
            default -> null;
        };
    }
}

class Monster {
    private double strength = 200;

    public void smash(Player opponent) {
        System.out.println("Monster smashes for " + strength + " DMG" + "\n");
        opponent.takeDamage(strength);
    }

    public double getStrength() {
        return strength;
    }
}


class MonsterAdapter extends Player {
    private Monster monster;

    public MonsterAdapter(Monster monster) {
        this.monster = monster;
        this.health = monster.getStrength(); 
    }

    @Override
    public void calculateAttackDamage() {
        this.attackDamage = monster.getStrength();
    }

    @Override
    public void attack(Player opponent) {
        monster.smash(opponent);
    }
}

class Mage extends Player {
    public Mage() {
        type = "Mage";
        attackDamageMultiplier = 1.8;
        healthGivenModifier = 0.6;
        calculateHP();

    }

    @Override
    public void attack(Player opponent) {
        calculateAttackDamage();
        System.out.println(type + " strikes for " + attackDamage + " DMG" + "\n");
        opponent.takeDamage(attackDamage);
    }
}


class Fighter extends Player {
    public Fighter() {
        type = "Fighter";
        attackDamageMultiplier = 1.2;
        healthGivenModifier = 1.2;
        calculateHP();

    }

    @Override
    public void attack(Player opponent) {
        calculateAttackDamage();
        System.out.println(type + " strikes for " + attackDamage + " DMG" + "\n");
        opponent.takeDamage(attackDamage);
    }
}


class Marksmen extends Player {
    public Marksmen() {
        type = "Marksmen";
        attackDamageMultiplier = 1.5;
        healthGivenModifier = 0.9;
        calculateHP();

    }

    @Override
    public void attack(Player opponent) {
        calculateAttackDamage();
        System.out.println(type + " strikes for " + attackDamage + " DMG" + "\n");
        opponent.takeDamage(attackDamage);
    }
}


class Assassin extends Player {
    public Assassin() {
        type = "Assassin";
        attackDamageMultiplier = 2;
        healthGivenModifier = 0.4;
        calculateHP();

    }

    @Override
    public void attack(Player opponent) {
        calculateAttackDamage();
        System.out.println(type + " strikes for " + attackDamage + " DMG" + "\n");
        opponent.takeDamage(attackDamage);
    }
}


class Tank extends Player {
    public Tank() {
        type = "Tank";
        attackDamageMultiplier = 1;
        healthGivenModifier = 1.4;
        calculateHP();

    }

    @Override
    public void attack(Player opponent) {
        calculateAttackDamage();
        System.out.println(type + " strikes for " + attackDamage + " DMG" + "\n");
        opponent.takeDamage(attackDamage);
    }
}


public class Game {
    private Player player1;
    private Player player2;
    private PlayerFactory playerFactory;

    public void waitMS(int ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Game() {
        playerFactory = PlayerFactory.getInstance();
    }

    public void startGame() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Player 1 Enter Desired Class (Mage, Marksmen, Warrior, Tank, Assasin): ");
        String player1Class = scanner.nextLine();
        player1 = playerFactory.getPlayer(player1Class);

        System.out.println("Player 2 Enter Desired Class (Mage, Marksmen, Warrior, Tank, Assasin): ");
        String player2Class = scanner.nextLine();
        player2 = playerFactory.getPlayer(player2Class);

        scanner.close();

        while (player1.isAlive() && player2.isAlive()) {

            waitMS(500);
            player1.attack(player2);
            if (!player2.isAlive()) {
                System.out.println(player1.getType() + " defeated " + player2.getType());
                break;
            }
            waitMS(500);
            player2.attack(player1);
            if (!player1.isAlive()) {
                System.out.println(player2.getType() + " defeated " + player1.getType());
            }
        }
        System.out.println("Game Over");
    }

    public static void main(String[] args) {
        Game game = new Game();
        game.startGame();
    }

}