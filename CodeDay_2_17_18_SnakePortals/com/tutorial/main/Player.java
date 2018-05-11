package com.tutorial.main;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class Player extends GameObject {

    Random r = new Random();
    private int count = 0;

    public Player(int x, int y, ID id) {
        super(x, y, id);

        velX = 0;
        velY = 0;

    }

    public void tick() {
        count += 1;
        if (id == ID.Player && count >= 3) {
            y += velY;
            count = 0;
            velY += 1;

        }
        if (id == ID.Player2 && count >= 3) {
            x += velX;
            count = 0;
            velX += 1;
        }
        if (id == ID.Player3 && count >= 3) {
            x += velX;
            count = 0;
            velX += 2;
        }

        // y += velY;
        // if (tick % 13 == 0) {
        // velY += 1;
        //
        // if(y >= HEIGHT) {
        // velY *= -1;
        // velY += 2;
        // }
        // System.out.print(x + ", " + y);
        //
        // }
        //
        // tick += 1;
        // x += velX;

    }

    public void render(Graphics g) {
        if (id == ID.Player) {
            g.setColor(Color.white);
            g.fillRect(x, y, WIDTH, 3);
        } else if (id == ID.Player2) {
            g.setColor(Color.red);
            g.fillRect(x, y, 3, HEIGHT);
        }
        else {
            g.setColor(Color.orange);
            g.fillRect(x, y, 3, HEIGHT);
        }

        g.fillRect(x, y, 16, 16);

    }

}
