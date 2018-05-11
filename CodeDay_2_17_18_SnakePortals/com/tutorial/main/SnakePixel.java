package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;

public class SnakePixel extends GameObject{
    
    private int link;

    public SnakePixel(int x, int y, int link, ID id) {
        super(x, y, id);
        this.link = link;
        velY = 0;
        velX = 0;
    }
    
    public int getLink() {
        return link;
    }
    

    public void render(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(x, y, 30, 30);
    }


    @Override
    public void tick() {
            x += velX;
            y += velY;
//            System.out.println(this.toString());
    }

    public String toString() {
        return link + "";
    }

}
