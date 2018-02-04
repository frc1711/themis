import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;

public class Controller extends XboxController
{
	public Controller(int port)
	{
		super(port);
	}
	
	public double getAngle(GenericHID.Hand hand)
	{
		double x = this.getX(hand);
		double y = this.getY(hand);
		double hypotenuse = Math.sqrt((y*y) + (x*x));
		double theta = Math.asin(y/hypotenuse);
		
		if(theta > 0)
		{
			if(y < 0)
				theta += (Math.PI/2);
		}
		else
		{
			theta *= -1;
			if(y > 0)
				theta += ((6 * Math.PI)/4);
			else
				theta += Math.PI;
		}
		
		return theta;
	}
	
	public double getMagnitude(GenericHID.Hand hand)
	{
		double y = this.getY(hand);
		double x = this.getX(hand);
		
		double magnitude = Math.sqrt((y*y) + (x*x));
		
		return magnitude;
	}
}