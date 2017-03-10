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
    public Flower flower;
    public Serial serial;
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
        addObject(serial,591,13);
        
        flower = new Flower();
        addObject(flower,263,211);
    }
    
    public EngduinoInterface getEngduino() {
        return serial.getEngduino();
    }
}
