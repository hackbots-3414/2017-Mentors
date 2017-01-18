/**
 * 
 */
package org.fpsrobotics.steamworks.utframework;

import java.util.ArrayList;
import java.util.Collection;

/**
 * This is an ArrayList, but, I needed some additional checks...
 * @author zdog
 *
 */
public class JoystickList extends ArrayList<Joystick>
{

	/**
	 * for serialization...
	 */
	private static final long serialVersionUID = 6006636534751951119L;

	/**
	 * default c'tor
	 */
	public JoystickList()
	{
		super();
	}

	/**
	 * @param arg0
	 */
	public JoystickList(int arg0)
	{
		super(arg0);
	}

	/**
	 * @param arg0
	 */
	public JoystickList(Collection<Joystick> arg0)
	{
		super(arg0);
	}

	/**
	 * @see java.util.ArrayList#get(int)
	 */
	@Override
	public Joystick get(int arg0)
	{
		if (arg0 < size())
			return super.get(arg0);
		Joystick dummy = new Joystick();
		dummy.setCurrentPointOfView(-1);
		dummy.setNumberOfAxes((short)-1);
		return dummy;
	}

	
}
