package com.tutorial.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class SnakePortal extends Canvas implements Runnable{

    
    /**
     * 
     */
    private static final long serialVersionUID = 6056270306992070907L;
    public static final int WIDTH = 30 * 20, HEIGHT = 30 * 20;
    
    private Thread thread;
    private boolean running = false;
    
    private Random r;
    private Handler handler;
    
//    ---------------------FIELDS END-----------------------------

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Welcome to Portal Snake! Use WASD to move. The Yellow square is normal food, and the Pink\n"
                + "food teleports you to the Yellow food :D\n\nThe game will pop up soon, CLICK ON THE WINDOW to enable keyboard!");
        TimeUnit.SECONDS.sleep(4);
        new SnakePortal();
        
    }
    
    
    public SnakePortal() {
        handler = new Handler();
        this.addKeyListener(new KeyInput(handler));
        new Window(WIDTH, HEIGHT, "Portal Snake", this);
        
        r = new Random();
        
        SnakeChain sc = new SnakeChain(0, 0, this, ID.SnakeChain);
        Portal p1 = new Portal(-1, -1, sc, ID.Portal1);
        Portal p2 = new Portal(-1, -1, sc, ID.Portal2);
        
        p1.addP(p2);
        p2.addP(p1);
        
        handler.addObject(sc);
        handler.addObject(p1);
        handler.addObject(p2);
        
//        handler.addObject(new Player(WIDTH/2 - 32, HEIGHT/2 - 32, ID.Player));
//        handler.addObject(new BasicEnemy(WIDTH/2 - 32, HEIGHT/2 - 32, ID.BasicEnemy));
        
//        Player bindPlayer = new Player(WIDTH/2 + 64, HEIGHT/2 - 32, ID.Player2);
//        handler.addObject(bindPlayer);
//        InputMap im = bindPlayer.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        

//        handler.addObject(new Player(WIDTH/2 + 64, HEIGHT/2 - 32, ID.Player2));

        
        
    }
    
    public synchronized void start() {
        thread = new Thread(this);
        thread.start();
        running = true;
    }
    
    public synchronized void stop() {
        try {
            thread.join();
            running = false;
        }catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 240.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        long timer = System.currentTimeMillis();
        int frames = 0;
        while(running) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            while(delta >= 1) {
                tick();
                delta--;
                }
            if(running)
                render();
            frames++;
            
            if(System.currentTimeMillis() - timer > 1000) {
                timer += 1000;
//                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();
        
    }
    
    private void tick() {
        handler.tick();
    }
    
    private void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if(bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = bs.getDrawGraphics();
        
        g.setColor(Color.blue);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        
        handler.render(g);
        
        g.dispose();
        bs.show();
    }
    
}
