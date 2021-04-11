import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;

public class Coin extends GameObject{

    public Coin(int x, int y, ID id) {
        super(x, y, id);
        
        velX = 0;
        velY = 0;
    }


    public void tick() {
        x += velX;
        y += velY;       

        if(y <= 0 || y>= Game.HEIGHT - 40) {
            velY *= -1;
        }
        if(x <= 0 || x >= Game.WIDTH-16) {
            velX *= -1;
        }
    }

    public void render(Graphics g) {
        g.setColor(Color.GRAY);        
        g.fillRect(x, y, 16, 16);
    }


    public Rectangle getBounds() {
        return new Rectangle(x, y, 16, 16);
    }
    
}
