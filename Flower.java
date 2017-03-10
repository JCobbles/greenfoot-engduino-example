import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import com.engfoot.serial.EngduinoInterface;
import com.engfoot.serial.ColorSettings;
import java.awt.Color;
import com.engfoot.handler.ButtonHandler;

/**
 * Write a description of class Flower here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Flower extends Actor
{
    int jumpIterations = 0;
    double pi = 3.1415926;
    private int ySpeed;
    private int apexTimer;
    private EngduinoInterface engduino;
    
    public Flower() {
        
    }
    
    protected void addedToWorld(World world) {
        engduino = ((MyWorld) getWorld()).getEngduino();
        engduino.addButtonHandler(new ButtonHandler() {
            @Override
            public void handle() {
                ((MyWorld) getWorld()).flower.move(10);
            }
        });
    }

    /**
     * Act - do whatever the Flower wants to do. This method is called whenever
     * the 'Act' or 'Run' button gets pressed in the environment.
     */
    public void act() 
    {
        if (Greenfoot.mouseClicked(this)) {
            try {
            engduino.createLightCommand().setLED(4, new ColorSettings(true, Color.yellow)).build().execute();
            engduino.createLightCommand()
               .setLED(5, new ColorSettings(true, Color.yellow))
               .setLED(6, new ColorSettings(true, Color.yellow)).build().execute();
            }catch( Exception e) {
                }
        }
        
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
            if ("space".equals(Greenfoot.getKey())) {
                ySpeed = -15;
                setLocation(getX(), getY() + ySpeed);
                apexTimer = 4;
            }
        }
    }    
}
