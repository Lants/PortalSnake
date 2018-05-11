package com.tutorial.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter{
    
    private Handler handler;
    
    public KeyInput(Handler handler) {
        this.handler = handler;
    }
    
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        
        for(int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            
            if(tempObject.getId() == ID.SnakeChain) {
                SnakeChain tempSnakeChain = (SnakeChain) tempObject;
//                key events for Snake's body (SnakeChain)
                
                if (key == KeyEvent.VK_R) {
                    tempSnakeChain.setToBeAdded(true);
                }
                else if(key == KeyEvent.VK_W) tempSnakeChain.changeDirection(0);
                else if(key == KeyEvent.VK_S) tempSnakeChain.changeDirection(1);
                else if(key == KeyEvent.VK_A) tempSnakeChain.changeDirection(2);
                else if(key == KeyEvent.VK_D) tempSnakeChain.changeDirection(3);

            }
            
        }
        
    }
    
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        for(int i = 0; i < handler.object.size(); i++) {
            GameObject tempObject = handler.object.get(i);
            
            if(tempObject.getId() == ID.Player) {
//                key events for player 1
                
                if(key == KeyEvent.VK_W) tempObject.setVelY(0);
                if(key == KeyEvent.VK_S) tempObject.setVelY(0);
                if(key == KeyEvent.VK_D) tempObject.setVelX(0);
                if(key == KeyEvent.VK_A) tempObject.setVelX(0);

            }
            
        }
        
    }
    

}
