/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2016-2017. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.hal;

import java.io.File;
import java.util.Hashtable;

/**
 * Base class for all JNI wrappers. Intercepts the JNI calls for the test
 * harness.
 */
public class JNIWrapper
{
	public static int getPortWithModule(byte module, byte channel)
	{
		System.out.println("getPortWithModule(" + module + ", " + channel + ")");
		Thread.dumpStack();
		return module + channel;
	}

	public static int getPort(byte channel)
	{
		System.out.println("getPort(" + channel + ")");
		Thread.dumpStack();
		return channel;
	}
	
	public class PortChannel
	{
		private byte module = 0;
		private byte channel = 0;

		public PortChannel(byte module, byte channel)
		{
			this.module = module;
			this.channel = channel;
		}
		
		public byte getModule()
		{
			return module;
		}
		public byte getChannel()
		{
			return channel;
		}
	}
}
