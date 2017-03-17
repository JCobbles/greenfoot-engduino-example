import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import com.engfoot.Engfoot;

import com.engfoot.handler.Value;
import com.engfoot.handler.ValueChangeHandler;
import com.engfoot.serial.SerialException;
import com.engfoot.serial.EngduinoInterface;

/**
 * Write a description of class MyWorld here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class MyWorld extends World
{
    public Teddy teddy;
    private Serial serial;
    private Rock rock;
    /**
     * Constructor for objects of class MyWorld.
     * 
     */
    public MyWorld() {    
        // Create a new world with 600x400 cells with a cell size of 1x1 pixels
        super(600, 400, 1);
        prepare();
    }

    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare() {
        serial = new Serial();
        addObject(serial,599,1);
        
        teddy = new Teddy();
        addObject(teddy,263,211);
    }
    
    /**
     * checks the spaceLevel and increases difficulty.
     */
    public void act()
    {
        if (Greenfoot.getRandomNumber(200) < 4) {
            Rock rock = new Rock();
            addObject(rock, 899, 490);
        }
    }
    public EngduinoInterface getEngduino() {
        return serial.getEngduino();
    }
}
