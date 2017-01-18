/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2016-2017. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package edu.wpi.first.wpilibj.hal;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.BitSet;

import org.fpsrobotics.steamworks.utframework.DriverStation;
import org.fpsrobotics.steamworks.utframework.Joystick;

/**
 * JNI Wrapper for HAL<br>
 * Stubbed out to bypass C code in favor of Eclipse for unit testing.
 * .
 */
public class HAL extends JNIWrapper
{
	private static String lastErrorMessage = null;
	// see ControlWord: disabled/enabled, teleop/auton, real/test, !emerStop/emerStop, !fmsAttached/fmsAttached, !dsAttached/dsAttached 
	private static BitSet currentState = new BitSet(6);
	private static final int STATE_POSITION_ENABLE = 0;
	private static final int STATE_POSITION_AUTON = 1;
	private static final int STATE_POSITION_TEST = 2;
	private static final int STATE_POSITION_EMERGENCY_STOP = 3;
	private static final int STATE_POSITION_FMS_ATTACHED = 4;
	private static final int STATE_POSITION_DS_ATTACHED = 5;
	
	
	public static void waitForDSData()
	{
		// no-op for us
	}

	public static int initialize(int mode)
	{
		return mode;
	}

	public static void observeUserProgramStarting()
	{
		// no-op for now
	}

	public static void observeUserProgramDisabled()
	{
		currentState.set(STATE_POSITION_ENABLE, false);
	}

	public static void observeUserProgramAutonomous()
	{
		currentState.set(STATE_POSITION_AUTON, true);
	}

	public static void observeUserProgramTeleop()
	{
		currentState.set(STATE_POSITION_AUTON, false); 
	}

	public static void observeUserProgramTest()
	{
		currentState.set(STATE_POSITION_TEST, true);
	}

	public static void report(int resource, int instanceNumber)
	{
		report(resource, instanceNumber, 0, "");
	}

	public static void report(int resource, int instanceNumber, int context)
	{
		report(resource, instanceNumber, context, "");
	}

	/**
	 * Report the usage of a resource of interest. <br>
	 *
	 * <p>
	 * Original signature:
	 * <code>uint32_t report(tResourceType, uint8_t, uint8_t, const
	 * char*)</code>
	 *
	 * @param resource
	 *            one of the values in the tResourceType above (max value 51).
	 *            <br>
	 * @param instanceNumber
	 *            an index that identifies the resource instance. <br>
	 * @param context
	 *            an optional additional context number for some cases (such as
	 *            module number). Set to 0 to omit. <br>
	 * @param feature
	 *            a string to be included describing features in use on a
	 *            specific resource. Setting the same resource more than once
	 *            allows you to change the feature string.
	 */
	public static int report(int resource, int instanceNumber, int context, String feature)
	{
		System.out.println("HAL.report(resource=" + resource + ", instanceNumber=" + instanceNumber + ", context=" + context + ", feature=" + feature + ")");
		return 0;
	}

	/**
	 * Tool to get the robot's demanded state from the match administrator (higher power than even the driver)
	 * @param controlWord
	 */
	public static void getControlWord(ControlWord controlWord)
	{
		controlWord.update(currentState.get(STATE_POSITION_ENABLE), 
				currentState.get(STATE_POSITION_AUTON), 
				currentState.get(STATE_POSITION_TEST), 
				currentState.get(STATE_POSITION_EMERGENCY_STOP),
				currentState.get(STATE_POSITION_FMS_ATTACHED), 
				currentState.get(STATE_POSITION_DS_ATTACHED));
	}

	private static int nativeGetAllianceStation()
	{
		return DriverStation.getSingleton().getAllianceStation();
	}

	public static AllianceStationID getAllianceStation()
	{
		switch (nativeGetAllianceStation())
		{
		case 0:
			return AllianceStationID.Red1;
		case 1:
			return AllianceStationID.Red2;
		case 2:
			return AllianceStationID.Red3;
		case 3:
			return AllianceStationID.Blue1;
		case 4:
			return AllianceStationID.Blue2;
		case 5:
			return AllianceStationID.Blue3;
		default:
			return null;
		}
	}

	public static int kMaxJoystickAxes = 12;
	public static int kMaxJoystickPOVs = 12;

	public static short getJoystickAxes(byte joystickNum, float[] axesArray)
	{
		ArrayList<Joystick> joysticks = DriverStation.getSingleton().getJoysticks();
		if (joysticks.size() >= joystickNum)
			return -1;
		return (short) joysticks.get(joystickNum).getNumberOfAxes();
	}

	public static short getJoystickPOVs(byte joystickNum, short[] povsArray)
	{
		return (short) DriverStation.getSingleton().getJoysticks().get(joystickNum).getCurrentPointOfView();
	}

	public static int getJoystickButtons(byte joystickNum, ByteBuffer count)
	{
		return DriverStation.getSingleton().getJoysticks().get(joystickNum).getNumberOfButtons();
	}

	/**
	 * Most of the joystick methods do reads, but this one writes back to the DriverStation.
	 * 
	 * @param joystickNum
	 * @param outputs
	 * @param leftRumble
	 * @param rightRumble
	 * @return
	 */
	public static int setJoystickOutputs(byte joystickNum, int outputs, short leftRumble, short rightRumble)
	{
		Joystick joystick = DriverStation.getSingleton().getJoysticks().get(joystickNum);
		joystick.setOutputs(outputs);
		joystick.setRumbleLeft(leftRumble);
		joystick.setRumbleRight(rightRumble);
		return 0;
	}

	public static int getJoystickIsXbox(byte joystickNum)
	{
		if (DriverStation.getSingleton().getJoysticks().get(joystickNum).isXBox())
			return 1;
		return 0;
	}

	public static native int getJoystickType(byte joystickNum);

	public static String getJoystickName(byte joystickNum)
	{
		return DriverStation.getSingleton().getJoysticks().get(joystickNum).getName();
	}

	public static int getJoystickAxisType(byte joystickNum, byte axis)
	{
		// this is a weird method.  We'll get to make sure we have a valid joystick
		// at joystickNum, but, ultimately return axis.  WPILib appears to permit
		// remapping of joystick axes, something I highly doubt anyone sane would do.
		if (joystickNum > DriverStation.getSingleton().getJoysticks().size())
			throw new ArrayIndexOutOfBoundsException("Joystick " + joystickNum + " does not exist");
		return axis;
	}

	public static double getMatchTime()
	{
		// convert DriverStation remaining match time to seconds
		return (double) DriverStation.getSingleton().getRemainingMatchTime() / 1000;
	}

	public static boolean getSystemActive()
	{
		return DriverStation.getSingleton().isSystemActive();
	}

	public static boolean getBrownedOut()
	{
		return DriverStation.getSingleton().isBrownedOut();
	}

	public static int setErrorData(String error)
	{
		lastErrorMessage = error;
		return 0;
	}

	public static int sendError(boolean isError, int errorCode, boolean isLVCode, String details,
			String location, String callStack, boolean printMsg)
	{
		if (!printMsg)
			return 0;
		StringBuffer message = new StringBuffer();
		if (isError)
			message.append("ERROR ");
		message.append(errorCode).append(",");
		if (isLVCode)
			message.append("(LVCode), ");
		else
			message.append(" , ");
		message.append(details).append(", ").append(location).append(", ").append(callStack);
		System.err.println(message.toString());
		return 0;
	}
}
