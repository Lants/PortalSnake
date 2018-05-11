package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class Yum extends GameObject{

    private Random r;
    private SnakeChain sc;
    
    public Yum(int x, int y, SnakeChain sc, ID Id) {
        super(x, y, Id);
        r = new Random();
        this.sc = sc;
        setX(r.nextInt(18) * 30);
        setY(r.nextInt(17) * 30);
    }

    @Override
    public void tick() {
        if (x == sc.getX() && y == sc.getY()) {
            sc.setToBeAdded(true);
            setX(r.nextInt(18) * 30);
            setY(r.nextInt(17) * 30);
        }
        
    }

    @Override
    public void render(Graphics g) {
        g.setColor(Color.green);
        g.fillRect(x, y, 30, 30);
        
    }

}
