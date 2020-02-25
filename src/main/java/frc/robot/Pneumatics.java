package frc.robot;



import edu.wpi.first.wpilibj.DoubleSolenoid;

import edu.wpi.first.wpilibj.Solenoid;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;



public class Pneumatics {

  private Solenoid _sol_deploy;

  

  private static int kSolChannel_deploy = 2;



  public Pneumatics() {

    _sol_deploy = new Solenoid(kSolChannel_deploy);

  }



  public void activate() {

    _sol_deploy.set(true);

  }



  public void deactivate() {

    _sol_deploy.set(false);

  }

}