/*

.______   .______          ___   ____    ____  _______ .______     ______   .___________.    _______.
|   _  \  |   _  \        /   \  \   \  /   / |   ____||   _  \   /  __  \  |           |   /       |
|  |_)  | |  |_)  |      /  ^  \  \   \/   /  |  |__   |  |_)  | |  |  |  | `---|  |----`  |   (----`
|   _  <  |      /      /  /_\  \  \      /   |   __|  |   _  <  |  |  |  |     |  |        \   \    
|  |_)  | |  |\  \----./  _____  \  \    /    |  |____ |  |_)  | |  `--'  |     |  |    .----)   |   
|______/  | _| `._____/__/     \__\  \__/     |_______||______/   \______/      |__|    |_______/    
                                                                                                     
*/

package frc.robot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.GenericHID.*;

public class Carousel
{

    private Position m_carousel;
    private int isPositionLoaded;
    private int k_BallsLoaded;
    private double k_rotationposition;
    private IRSensor IRS_1;
    private IRSensor IRS_2;


    public Carousel(Position c, IRSensor s1, IRSensor s2)
    {
        m_carousel = c;
        m_carousel.initializePositionalPID();
        k_rotationposition = 4.7123;
        IRS_1 = s1;
        IRS_2 = s2;

    }

    public void spinOneSlot()
    {
        m_carousel.runCarousel(k_rotationposition);
    }

    public void spinFullRotation()
    {
        m_carousel.runCarousel(k_rotationposition / 5);
    }

    public int getBallsLoaded()
    {
        return k_BallsLoaded;
    }

    public void updateSlotState()
    {
        if(IRS_1.getIRValue() == true && IRS_2.getIRValue() == true)
        {
            System.out.println("This Slot is Full");
            isPositionLoaded = 1;
        }
        
        if(IRS_1.getIRValue() == false && IRS_2.getIRValue() == false)
        {
            System.out.println("This Slot is Empty, FEED ME DADDY");
            isPositionLoaded = 0;
        }
    
    }

    public void loadSlot(int slot)
    {
        switch(isPositionLoaded)
        {
            case 1: 
            spinOneSlot();
            break;

            case 2:
            spinFullRotation();
            break;
        }
    }

    


}