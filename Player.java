import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Color;

public class Player extends GameObject{
    Handler handler;
    public static int scoreCount = 0;

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
                    if(HUD.health <= 0)
                        stopAll();
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

    private void stopAll() {
        for(int i = 0; i < handler.object.size(); i++) {
            if(handler.object.get(i).getId() != ID.Player)
                handler.object.remove(i);
        }
    }

    
    
}
