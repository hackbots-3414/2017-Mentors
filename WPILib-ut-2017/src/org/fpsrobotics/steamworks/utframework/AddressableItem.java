package org.fpsrobotics.steamworks.utframework;

/**
 * Represents a widget we need to talk to / draw
 * 
 * @author zdog
 *
 */
public class AddressableItem
{
	private int address = -1;
	
	/**
	 * default c'tor
	 */
	public AddressableItem()
	{
		super();
	}
	
	/**
	 * Convenience c'tor to set the address.
	 * 
	 * @param address
	 */
	public AddressableItem(int address)
	{
		this.address = address;
	}

	/**
	 * @return the address
	 */
	public int getAddress()
	{
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(int address)
	{
		this.address = address;
	}

}
