package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Portal extends GameObject {

    private Random r;
    private SnakeChain sc;
    private ID Id;
    Portal p;

    public Portal(int x, int y, SnakeChain sc, ID Id) {
        super(x, y, Id);
        r = new Random();
        this.sc = sc;
        setX(r.nextInt(18) * 30);
        setY(r.nextInt(17) * 30);
        this.Id = Id;
    }

    public void addP(Portal p) {
        this.p = p;
    }

    @Override
    public void tick() {
//        System.out.println(sc.sBody.get(0).getVelX());
        if (x == sc.getX() && y == sc.getY()) {
            sc.setToBeAdded(true);
            setX(r.nextInt(18) * 30);
            setY(r.nextInt(17) * 30);

            if (Id == ID.Portal1) {
                sc.sBody.get(0).setX(p.getX() + sc.sBody.get(0).getVelX());
                sc.sBody.get(0).setY(p.getY() + sc.sBody.get(0).getVelY());

            }
        }

    }

    @Override
    public void render(Graphics g) {
        if (Id == ID.Portal1)
            g.setColor(Color.magenta);
        if (Id == ID.Portal2)
            g.setColor(Color.orange);
        g.fillRect(x, y, 30, 30);

    }
}
