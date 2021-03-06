import java.awt.Graphics;
import java.awt.Color;


public class HUD {
    public static int health = 100;
    private int greenValue = 255;
    public static int score = 0;
    public static int healthRefill = 0;
    public static boolean clicked = false;

    public void tick() {
        health = Game.clamp(health, 0, 100);
        greenValue = Game.clamp(greenValue, 0, 255);

        greenValue = health*2;

        if(healthRefill % 10 == 0) {
            health = 100;
        }
        
    }
    public void render(Graphics g) {
        g.setColor(Color.gray);
        g.fillRect(15, 15, 200, 32);
        g.setColor(new Color(75, greenValue, 0));
        g.fillRect(15, 15, health * 2, 32);
        g.setColor(Color.white);
        g.drawRect(15, 15, 200, 32);
        g.setColor(Color.blue);
        g.drawString("Score: " + score, 10, 64);

        if(Player.finish) {
            g.setColor(Color.white);
            g.drawString("You Lose, Score : " + score, Game.WIDTH/2 - 64, Game.HEIGHT/2 - 32);
            g.drawString("Wave by Aditya Chidambaram", Game.WIDTH/2 - 90, 64);
            g.drawString("CLICK ESCAPE TO EXIT", Game.WIDTH/2 - 70, Game.HEIGHT/2);
        }
    }

    public void addScore(int x) {
        score += x;
    }
    public void setScore(int x) {
        score = x;
    }
    public int getScore() {
        return score;
    }
    
}
