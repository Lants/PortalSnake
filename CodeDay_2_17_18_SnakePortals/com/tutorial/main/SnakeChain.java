package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class SnakeChain extends GameObject {

    public ArrayList<SnakePixel> sBody;
    private SnakePixel head;
    private int link = 1;
    private int lastX, lastY, count;
    private boolean toBeAdded = false;

    private ArrayList<Integer> xVs;
    private ArrayList<Integer> yVs;

    private int direction;
    private boolean changed;
    
    private SnakePortal game;
    
    public SnakeChain(int x, int y, SnakePortal game, ID Id) {
        super(x, y, Id);
        this.game = game;
        direction = 1;
        xVs = new ArrayList<Integer>();
        yVs = new ArrayList<Integer>();

        xVs.add(x);
        yVs.add(y);

        lastX = 0;
        lastY = -30;
        head = new SnakePixel(x, y, 0, ID.SnakePixel);
        sBody = new ArrayList<SnakePixel>();
        sBody.add(head);
        sBody.get(0).setVelY(30);

        sBody.add(new SnakePixel(head.getX() + lastX * link, head.getY() + lastY * link, link, ID.SnakePixel));
        sBody.get(1).setVelY(30);
        link = 2;

    }

    private void addLink() { // adds a pixel/link to snake chain, should be done in time ONLY with toBeAdded
        sBody.add(new SnakePixel(xVs.get(link - 2), yVs.get(link - 2), link, ID.SnakePixel));
//        System.out.println("added");
        setToBeAdded(false);
        // lastX = sBody.get(link).getX();
        // lastY = sBody.get(link).getY();
//        System.out.println(lastX + " " + lastY);
        link++;
        yVs.add(0, yVs.get(yVs.size() - 1));
        xVs.add(0, xVs.get(xVs.size() - 1));

    }

    public void changeDirection(int dir) {
        sBody.get(1).setVelY(0);
        if (dir == 0 && direction != 1 && changed == true) {
            direction = 0;
            sBody.get(0).setVelY(-30);
            sBody.get(0).setVelX(0);
        } else if (dir == 1 && direction != 0 && changed == true) {
            direction = 1;
            sBody.get(0).setVelY(30);
            sBody.get(0).setVelX(0);
        } else if (dir == 2 && direction != 3 && changed == true) {
            direction = 2;
            sBody.get(0).setVelY(0);
            sBody.get(0).setVelX(-30);
        } else if (dir == 3 && direction != 2 && changed == true) {
            direction = 3;
            sBody.get(0).setVelY(0);
            sBody.get(0).setVelX(30);
        }
        changed = false;

    }

    public void moveLinks() {
        yVs.add(sBody.get(0).getY());
        xVs.add(sBody.get(0).getX());
        for (int i = 0; i < sBody.size() - 1; i++) {
            sBody.get(i + 1).setY(yVs.get(i));
            sBody.get(i + 1).setX(xVs.get(i));
        }

        yVs.remove(0);
        xVs.remove(0);
    }

    @Override
    public void tick() {
        if (count % 30 == 0) {
            if (toBeAdded)
                addLink();
//            System.out.println(yVs + " " + xVs);
            for (int i = 0; i < sBody.size(); i++) {
                sBody.get(i).tick();
            }
            moveLinks();
            checkForDeath();
            lastX = sBody.get(link - 1).getX() - sBody.get(link - 2).getX();
            lastY = sBody.get(link - 1).getY() - sBody.get(link - 2).getY();
            if (!changed)
                changed = !changed;
        }
        count++;
    }
    
    public void checkForDeath() {
        for (int i = 1; i < xVs.size(); i++) {
            if (sBody.get(0).getX() == sBody.get(i).getX() &&
                    sBody.get(0).getY() == sBody.get(i).getY()) {
                System.out.println("U DED BOI");
                game.stop();
            }
        }
        if (sBody.get(0).getX() < 0 || sBody.get(0).getX() > 570 ||
                sBody.get(0).getY() < 0 || sBody.get(0).getY() > 540) {
            System.out.println("U CRASHED BOI");
            game.stop();
        }
    }

    @Override
    public void render(Graphics g) {
        for (int i = 0; i < sBody.size(); i++) {
            sBody.get(i).render(g);
        }

    }

    public int getCount() {
        return count;
    }

    public void setToBeAdded(boolean b) {
        toBeAdded = b;
    }
    
    @Override
    public int getX() {
        return sBody.get(0).getX();
    }
    
    @Override
    public int getY() {
        return sBody.get(0).getY();
    }

}
