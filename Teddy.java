import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import java.awt.Color;

import com.engfoot.*;
import com.engfoot.serial.*;
import com.engfoot.handler.*;

/**
 * Write a description of class Flower here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Teddy extends Actor
{
    int jumpIterations = 0;
    double pi = 3.1415926;
    private int ySpeed;
    private int apexTimer;
    private EngduinoInterface engduino;
    private boolean jumpTrigger = false;
    
    public Teddy() {
        
    }
    
    protected void addedToWorld(World world) {
        System.out.println("added to world");
        engduino = ((MyWorld) getWorld()).getEngduino();
        engduino.addLightSensorHandler(new ValueChangeHandler<Float>() {
            public void onChange(Value<Float> value) {
                System.out.println(value.get());
                //if (value.get() < 1) {
                //    engduino.createLightCommand().setLED(14, new ColorSettings(true, Color.yellow)).build().execute();
                //} else {
                //    engduino.createLightCommand().setLED(14, new ColorSettings(true, Color.yellow)).build().execute();
                //}
            }
        });
        engduino.addButtonHandler(new ButtonHandler() {
            @Override
            public void handle() {
                jumpTrigger = true;
            }
        });
    }

    /**
     * Act - do whatever the Flower wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        int groundLevel = getWorld().getHeight() - getImage().getHeight()/2;
        boolean onGround = (getY() == groundLevel);
        
        if (!onGround) {
            if (ySpeed == 0 && apexTimer > 0) {
                apexTimer--;
                return;
            }
            ySpeed++;
            setLocation(getX(), getY() + ySpeed);
            if (getY() >= groundLevel) {
                setLocation(getX(), groundLevel); // set on ground
                Greenfoot.getKey(); // clears any key pressed during jump
            }
        } else {
            if (jumpTrigger) {
                jumpTrigger = false;
                ySpeed = -18;
                setLocation(getX(), getY() + ySpeed);
                apexTimer = 6;
            }
        }
        
        Rock collided = (Rock) getOneIntersectingObject(Rock.class); //make a variable(in this case collided) of type actor and make it an intersecting object of the type you are running into
        if(collided != null) {
           collided.destroy();
        }
    }    
}
