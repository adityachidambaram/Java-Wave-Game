import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;

public class Player extends GameObject{
    Handler handler;
    public static int scoreCount = 0;
    public static boolean finish = false;

    public Player(int x, int y, ID id, Handler handler) {
        super(x, y, id);
        this.handler = handler;
    }

    public void tick() {
        x += velX;
        y += velY;

        x = Game.clamp(x, 0, Game.WIDTH - 32);
        y = Game.clamp(y, 0, Game.HEIGHT - 60);

        collision();
        if(HUD.health == 0)
            stopAll();
    }

    public void render(Graphics g) {
        g.setColor(Color.yellow);
        g.fillRect(x, y, 32, 32);
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, 32, 32);
    }
    private void collision() {
        for(int i = 0; i < handler.object.size();i++) {
            GameObject tempObject = handler.object.get(i);

            if(tempObject.getId() == ID.BasicEnemy) {
                if(getBounds().intersects(tempObject.getBounds())) {
                    HUD.health -= 2;
                }
            }
            if(tempObject.getId() == ID.Coin) {
                if(getBounds().intersects(tempObject.getBounds())) {
                    HUD.score += 1;
                    HUD.healthRefill++;
                    scoreCount++;
                    handler.object.remove(tempObject);
                }
            }
        }
    }

    public void stopAll() {
        for(int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            if(tempObject.getId() == ID.Coin)
                handler.object.remove(i);
            if(tempObject.getId() == ID.BasicEnemy)
                handler.object.remove(i);
        }
        if(handler.object.size() == 1) {
            finish = true;
        }
    }

    
    
}
