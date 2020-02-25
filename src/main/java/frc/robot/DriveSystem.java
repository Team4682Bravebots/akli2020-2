

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
import edu.wpi.first.wpilibj.XboxController;
//import edu.wpi.first.wpilibj.GenericHID.*;
//import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.GenericHID.Hand;


public class DriveSystem {

    //Designate the motor controllers and XBOX Controller
    
    private CANSparkMax m_left;
  //  private CANSparkMax m_right;
    private Joystick m_controller;
    private CANPIDController m_left_pidController;
    private CANPIDController m_right_pidController;
    public double kPL, kIL, kDL, kIzL, kFFL, kMaxOutputL, kMinOutputL, maxRPML, kPR, kIR, kDR, kIzR, kFFR, kMaxOutputR, kMinOutputR, maxRPMR;
   // private CANEncoder m_left_encoder = m_left.getEncoder();
    //private CANEncoder m_right_encoder = m_right.getEncoder();
    private double setPointL;
    private double setPointR;

    //Set a variable to change the output speed
    //private double k_maxSpeedModifier = 1;

    /*

    @param l  The Left Motor Controller
    @param r  The Right Motor Controller
    @return   Contructor
    @see      Drive system
    */
    public DriveSystem(CANSparkMax l, Joystick c)
    {
        m_left = l;
        m_controller = c;
    }

    public void initializeVelocityPID()
    {
        m_left_pidController = m_left.getPIDController();
        
     // PID coefficients
        kPL = 0; 
        kIL = 0;
        kDL = 0; 
        kIzL = 0; 
        kFFL = 0; 
        kMaxOutputL =0; 
        kMinOutputL = 0;
        maxRPML = 1;

        kPR = 0; 
        kIR = 0;
        kDR = 0; 
        kIzR = 0; 
        kFFR = 0; 
        kMaxOutputR = 0; 
        kMinOutputR = 0;
        maxRPMR = 1;
        //Left Motor
        m_left_pidController.setP(kPL);
        m_left_pidController.setI(kIL);
        m_left_pidController.setD(kDL);
        m_left_pidController.setIZone(kIzL);
        m_left_pidController.setFF(kFFL);
        m_left_pidController.setOutputRange(kMinOutputL, kMaxOutputL);
        //Right Motor
        m_right_pidController.setP(kPR);
        m_right_pidController.setI(kIR);
        m_right_pidController.setD(kDR);
        m_right_pidController.setIZone(kIzR);
        m_right_pidController.setFF(kFFR);
        m_right_pidController.setOutputRange(kMinOutputR, kMaxOutputR);

        SmartDashboard.putNumber("P Gain L", kPL);
        SmartDashboard.putNumber("I Gain L", kIL);
        SmartDashboard.putNumber("D Gain L", kDL);
        SmartDashboard.putNumber("I Zone L", kIzL);
        SmartDashboard.putNumber("Feed Forward L", kFFL);
        SmartDashboard.putNumber("Max Output L", kMaxOutputL);
        SmartDashboard.putNumber("Min Output L", kMinOutputL);

        SmartDashboard.putNumber("P Gain R", kPR);
        SmartDashboard.putNumber("I Gain R", kIR);
        SmartDashboard.putNumber("D Gain R", kDR);
        SmartDashboard.putNumber("I Zone R", kIzR);
        SmartDashboard.putNumber("Feed Forward R", kFFR);
        SmartDashboard.putNumber("Max Output R", kMaxOutputR);
        SmartDashboard.putNumber("Min Output R", kMinOutputR);
    }


    private void activateVelocityLoop()
    {

     setPointL = -m_controller.getY(Hand.kLeft)* 0.8;
   // setPointR = m_controller.getY(Hand.kRight)*maxRPMR * 0.75;


    SmartDashboard.putNumber("SetPointL", setPointL);
    // SmartDashboard.putNumber("SetPointL", setPointR);
    //SmartDashboard.putNumber("ProcessVariableL", m_left_encoder.getVelocity());
    //SmartDashboard.putNumber("ProcessVariableR", m_right_encoder.getVelocity());


    }

    public double getLeftSetpoint()
    {
        return setPointL;
    }

    
    /*
    @param  modifier  The desired Modifier the user wants to use
    @return void method
    Recommended that you run this in the robot_init phase
    */
    public void setMaxSpeedModifier(double modifier)
    {
        //k_maxSpeedModifier = modifier;
    }

    public void activatedifferentialDrive()
    {

        double joystickLeft = m_controller.getY(Hand.kLeft);
        

    activateVelocityLoop();


    //Applied a deadband because the controller is so sensitive;

    if((joystickLeft <= 0.1 && joystickLeft >= -0.1 ))
    {   
      m_left.set(0);
      



    }

    else{   
    m_left.set(getLeftSetpoint());
  
    }
}
}
   /*else{

    

    }

    }
*/



