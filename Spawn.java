import java.util.Random;

public class Spawn {
    private Handler handler;
    private HUD hud;
    private Random  r = new Random();
    
    public Spawn(Handler handler, HUD hud) {
        this.handler = handler;
        this.hud = hud;
    }
    public void tick() {
        if(Player.scoreCount == 1) {
            int xCoord = (int)(Math.random()*(Game.WIDTH - 200)) + 100;
            int yCoord = (int)(Math.random()*(Game.HEIGHT - 200)) + 100;


            handler.addObject(new BasicEnemy(r.nextInt(Game.WIDTH), r.nextInt(Game.HEIGHT), ID.BasicEnemy));
            handler.addObject(new Coin(xCoord, yCoord, ID.Coin));
            System.out.println(xCoord + "," + yCoord);
            Player.scoreCount = 0;
        }   
    }
}
