/**
 * 
 */
package org.fpsrobotics.steamworks.utframework;

/**
 * This class handles the DriverStation stuff.
 * 
 * @author bczdog
 *
 */
public class DriverStation
{
	public static final long MATCH_LENGTH = (2 * 60 + 30) * 1000;
	private static DriverStation me = new DriverStation();
	
	private JoystickList joysticks = new JoystickList();
	private int allianceStation = 0;
	private long matchEndTime = System.currentTimeMillis() + MATCH_LENGTH;
	private boolean systemActive = false;
	private boolean brownedOut = false;
	
	/**
	 * Singleton accessor method
	 * @return
	 */
	public static DriverStation getSingleton()
	{
		return me;
	}
	
	/**
	 * hidden c'tor to keep the singleton instance controlled
	 */
	private DriverStation()
	{
		joysticks.add(new Joystick(0));
		joysticks.add(new Joystick(1));
	}

	public JoystickList getJoysticks()
	{
		return joysticks;
	}

	/**
	 * @return the allianceStation
	 */
	public int getAllianceStation()
	{
		return allianceStation;
	}

	/**
	 * @param allianceStation the allianceStation to set
	 */
	public void setAllianceStation(int allianceStation)
	{
		this.allianceStation = allianceStation;
	}
	
	public long getRemainingMatchTime()
	{
		return Math.max(matchEndTime - System.currentTimeMillis(), 0); 
	}
	
	public void restartMatch()
	{
		matchEndTime = System.currentTimeMillis() + MATCH_LENGTH;
	}

	/**
	 * @return the systemActive
	 */
	public boolean isSystemActive()
	{
		return systemActive;
	}

	/**
	 * @param systemActive the systemActive to set
	 */
	public void setSystemActive(boolean systemActive)
	{
		this.systemActive = systemActive;
	}

	/**
	 * @return the brownedOut
	 */
	public boolean isBrownedOut()
	{
		return brownedOut;
	}

	/**
	 * @param brownedOut the brownedOut to set
	 */
	public void setBrownedOut(boolean brownedOut)
	{
		this.brownedOut = brownedOut;
	}
}
