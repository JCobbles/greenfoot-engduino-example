import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

import com.engfoot.*;
import com.engfoot.serial.*;
import com.engfoot.handler.*;


/**
 * Write a description of class Serial here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Serial extends Actor
{
    private EngduinoInterface foot;
    
    public Serial() {
        Engfoot engfoot = new Engfoot();
        
        try {
            foot = engfoot.connect();
            System.out.println("Connected");
            foot.addAccelerometerHandler(new ValueChangeHandler<Double>() {

                @Override
                public void onChange(Value<Double> value) {
                    //System.out.println("ACCELEROMETER: " + value.get());
                }
            });
            foot.addTemperatureHandler((Value<Float> value) -> {
                //System.out.println("TEMPERATURE: " + value.get());
            });
            foot.addLightSensorHandler((Value<Float> value) -> {
                System.out.println("LIGHT: " + value.get());
            });
        } catch (ConnectionException ex) {
            ex.printStackTrace();
        }
    }
    /**
     * Act - do whatever the Serial wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        // Add your action code here.
    }    
    
   
    
    public EngduinoInterface getEngduino() {
        return foot;
    }
}
