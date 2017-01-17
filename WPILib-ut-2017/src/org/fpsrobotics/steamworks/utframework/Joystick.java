/**
 * 
 */
package org.fpsrobotics.steamworks.utframework;

/**
 * Represents a Joystick class, without needing a real joystick
 * 
 * @author bczdog
 *
 */
public class Joystick extends AddressableItem
{
	private float axisX = 0;
	private float axisY = 0;
	private float axisZ= 0;
	private float twist = 0;
	private float throttle = 0;
	private boolean[] buttonList = new boolean[7];
	private short numberOfAxes = 2;
	private boolean isXBox = false;
	private String name = null;
	private int pointOfViewCount = 0;
	private int currentPointOfView = 0;
	private short rumbleLeft = 0;
	private short rumbleRight = 0;
	
	/**
	 * default c'tor
	 */
	public Joystick()
	{
		super();
	}
	
	/**
	 * Convenience c'tor, probably the one you want.
	 * 
	 * @param address
	 */
	public Joystick(int address)
	{
		super(address);
	}

	/**
	 * @return the axisX
	 */
	public float getAxisX()
	{
		return axisX;
	}

	/**
	 * @param axisX the axisX to set
	 */
	public void setAxisX(float axisX)
	{
		this.axisX = axisX;
	}

	/**
	 * @return the axisY
	 */
	public float getAxisY()
	{
		return axisY;
	}

	/**
	 * @param axisY the axisY to set
	 */
	public void setAxisY(float axisY)
	{
		this.axisY = axisY;
	}

	/**
	 * @return the axisZ
	 */
	public float getAxisZ()
	{
		return axisZ;
	}

	/**
	 * @param axisZ the axisZ to set
	 */
	public void setAxisZ(float axisZ)
	{
		this.axisZ = axisZ;
	}

	/**
	 * @return the twist
	 */
	public float getTwist()
	{
		return twist;
	}

	/**
	 * @param twist the twist to set
	 */
	public void setTwist(float twist)
	{
		this.twist = twist;
	}

	/**
	 * @return the throttle
	 */
	public float getThrottle()
	{
		return throttle;
	}

	/**
	 * @param throttle the throttle to set
	 */
	public void setThrottle(float throttle)
	{
		this.throttle = throttle;
	}

	/**
	 * get the on/off value of button buttonNumber
	 * 
	 * @param buttonNumber
	 * @return on or off
	 * @throws ArrayIndexOutOfBounds if you try to access a button we don't have
	 */
	public boolean getButton(int buttonNumber)
	{
		return buttonList[buttonNumber];
	}
	
	public boolean isButtonTriggerOn()
	{
		return buttonList[0];
	}
	
	public boolean isButtonTopOn()
	{
		return buttonList[1];
	}
	
	public int getNumberOfAxes()
	{
		return numberOfAxes;
	}

	/**
	 * @param numberOfAxes the numberOfAxes to set
	 */
	public void setNumberOfAxes(short numberOfAxes)
	{
		this.numberOfAxes = numberOfAxes;
	}

	/**
	 * @return the isXBox
	 */
	public boolean isXBox()
	{
		return isXBox;
	}

	/**
	 * @param isXBox the isXBox to set
	 */
	public void setXBox(boolean isXBox)
	{
		this.isXBox = isXBox;
	}

	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * @return the pointOfViewCount
	 */
	public int getPointOfViewCount()
	{
		return pointOfViewCount;
	}

	/**
	 * @param pointOfViewCount the pointOfViewCount to set
	 */
	public void setPointOfViewCount(int pointOfViewCount)
	{
		this.pointOfViewCount = pointOfViewCount;
	}

	/**
	 * @return the currentPointOfView
	 */
	public int getCurrentPointOfView()
	{
		return currentPointOfView;
	}

	/**
	 * @param currentPointOfView the currentPointOfView to set
	 */
	public void setCurrentPointOfView(int currentPointOfView)
	{
		this.currentPointOfView = currentPointOfView;
	}
	
	/**
	 * @return the total number of buttons
	 */
	public int getNumberOfButtons()
	{
		return buttonList.length;
	}

	/**
	 * @return the rumbleLeft
	 */
	public short getRumbleLeft()
	{
		return rumbleLeft;
	}

	/**
	 * @param rumbleLeft the rumbleLeft to set
	 */
	public void setRumbleLeft(short rumbleLeft)
	{
		this.rumbleLeft = rumbleLeft;
	}

	/**
	 * @return the rumbleRight
	 */
	public short getRumbleRight()
	{
		return rumbleRight;
	}

	/**
	 * @param rumbleRight the rumbleRight to set
	 */
	public void setRumbleRight(short rumbleRight)
	{
		this.rumbleRight = rumbleRight;
	}
	
	public void setOutputs(int outputs)
	{
		for (int i = 0; i < buttonList.length; i ++)
		{
			// TODO implement a bitshift to set the button array
		}
	}
}
