import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;
import java.awt.Color;

public class Game extends Canvas implements Runnable{

    private static final long serialVersionUID = -6112428091888191314L;

    public static final int WIDTH = 640, HEIGHT = WIDTH / 12 * 9;
    private Thread thread;
    private boolean running = false;

    private Handler handler;
    private HUD hud;
    private Spawn spawner;

    private Random r;

    public Game() {

        handler = new Handler();
        hud = new HUD();
        spawner = new Spawn(handler, hud);
        
        this.addKeyListener(new KeyInput(handler));

        new Window(WIDTH, HEIGHT, "WAVE", this);

        r = new Random();

        handler.addObject(new Player(WIDTH/2 - 32, HEIGHT/2-32, ID.Player, handler));
        handler.addObject(new Coin((int)(Math.random()*WIDTH - 100) + 100, (int)(Math.random()*HEIGHT - 100) + 100, ID.Coin));
        for(int i = 0; i < 5; i++)
            handler.addObject(new BasicEnemy((int)(Math.random()*WIDTH - 50) + 50, (int)(Math.random()*HEIGHT - 50) + 50, ID.BasicEnemy));
    }


    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }
    public synchronized void stop() {
        try{
            thread.join();
            running = false;
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void run() {
        this.requestFocus();
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running){
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1){
                tick();
                delta--;
            }
            if(running)
                render();
            frames++;

            if(System.currentTimeMillis() - timer > 1000){
                timer += 1000;
                if(frames < 200)
                    System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
    }
    
    private void tick() {
        handler.tick();
        hud.tick();
        spawner.tick();
    }

    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        g.setColor(Color.black);
        g.fillRect(0, 0, WIDTH, HEIGHT);

        handler.render(g);
        hud.render(g);

        g.dispose();
        bs.show();
    }

    public static int clamp (int var, int min, int max) {
        if(var <= min) {
            var = min;
            return var;
        }
        if(var >= max) {
            var = max;
            return var;
        }
        else {
            return var;
        }
    }
}