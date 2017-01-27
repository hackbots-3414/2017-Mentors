package edu.wpi.first.wpilibj.networktables;

import edu.wpi.first.wpilibj.tables.*;

import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Hashtable;

/**
 * This class replaces the JNI calls with no-ops since for JUnit / GUI unit
 * testing we do not need wire communication.
 * 
 * @author zdog
 *
 */
public class NetworkTablesJNI
{
	private static Hashtable<String, Object> networkTable = new Hashtable<>();
	private static Hashtable<String, Object> defaultTable = new Hashtable<>();
	private static Hashtable<String, Integer> entryFlags = new Hashtable<>();
	private static LoggerFunction logger = null;
	private static String networkIdentity = null;

	public static boolean containsKey(String key)
	{
		return networkTable.containsKey(key);
	}

	/**
	 * zdog added to throw TableKeyNotDefinedException when a key does not exist
	 * in the table.
	 * 
	 * @param key
	 * @throws TableKeyNotDefinedException
	 *             when the key has not been set
	 */
	public static void checkContainsKey(String key) throws TableKeyNotDefinedException
	{
		if (!containsKey(key))
			throw new TableKeyNotDefinedException(key);
	}

	public static int getType(String key)
	{
		Object value = networkTable.get(key);
		if (value == null)
			return -1;
		// NT_UNASSIGNED = 0,
		// NT_BOOLEAN = 0x01,
		// NT_DOUBLE = 0x02,
		// NT_STRING = 0x04,
		// NT_RAW = 0x08,
		// NT_BOOLEAN_ARRAY = 0x10,
		// NT_DOUBLE_ARRAY = 0x20,
		// NT_STRING_ARRAY = 0x40,
		// NT_RPC = 0x80
		if (value instanceof Boolean)
			return 0x01;
		else if (value instanceof Double)
			return 0x02;
		else if (value instanceof String)
			return 0x04;
		else if (value instanceof boolean[])
			return 0x10;
		else if (value instanceof double[])
			return 0x20;
		else if (value instanceof String[])
			return 0x40;
		// default - we don't know what this is...
		return 0;
	}

	public static boolean putBoolean(String key, boolean value)
	{
		networkTable.put(key, new Boolean(value));
		return true;
	}

	public static boolean putDouble(String key, double value)
	{
		networkTable.put(key, new Double(value));
		return true;
	}

	public static boolean putString(String key, String value)
	{
		networkTable.put(key, value);
		return true;
	}

	public static boolean putRaw(String key, byte[] value)
	{
		networkTable.put(key, value);
		return true;
	}

	public static boolean putRaw(String key, ByteBuffer value, int len)
	{
		byte[] stuffToStore = new byte[len];
		value.get(stuffToStore, 0, len);
		networkTable.put(key, stuffToStore);
		return true;
	}

	public static boolean putBooleanArray(String key, boolean[] value)
	{
		networkTable.put(key, value);
		return true;
	}

	public static boolean putDoubleArray(String key, double[] value)
	{
		networkTable.put(key, value);
		return true;
	}

	public static boolean putStringArray(String key, String[] value)
	{
		networkTable.put(key, value);
		return true;
	}

	public static void forcePutBoolean(String key, boolean value)
	{
		putBoolean(key, value);
	}

	public static void forcePutDouble(String key, double value)
	{
		putDouble(key, value);
	}

	public static void forcePutString(String key, String value)
	{
		putString(key, value);
	}

	public static void forcePutRaw(String key, byte[] value)
	{
		putRaw(key, value);
	}

	public static void forcePutRaw(String key, ByteBuffer value, int len)
	{
		putRaw(key, value, len);
	}

	public static void forcePutBooleanArray(String key, boolean[] value)
	{
		putBooleanArray(key, value);
	}

	public static void forcePutDoubleArray(String key, double[] value)
	{
		putDoubleArray(key, value);
	}

	public static void forcePutStringArray(String key, String[] value)
	{
		putStringArray(key, value);
	}

	public static Object getValue(String key) throws TableKeyNotDefinedException
	{
		checkContainsKey(key);
		return networkTable.get(key);
	}

	public static boolean getBoolean(String key) throws TableKeyNotDefinedException
	{
		checkContainsKey(key);
		return ((Boolean) networkTable.get(key)).booleanValue();
	}

	public static double getDouble(String key) throws TableKeyNotDefinedException
	{
		checkContainsKey(key);
		return ((Double) networkTable.get(key)).doubleValue();
	}

	public static String getString(String key) throws TableKeyNotDefinedException
	{
		checkContainsKey(key);
		return (String) networkTable.get(key);
	}

	public static byte[] getRaw(String key) throws TableKeyNotDefinedException
	{
		checkContainsKey(key);
		return (byte[]) networkTable.get(key);
	}

	public static boolean[] getBooleanArray(String key) throws TableKeyNotDefinedException
	{
		checkContainsKey(key);
		return (boolean[]) networkTable.get(key);
	}

	public static double[] getDoubleArray(String key) throws TableKeyNotDefinedException
	{
		checkContainsKey(key);
		return (double[]) networkTable.get(key);
	}

	public static String[] getStringArray(String key) throws TableKeyNotDefinedException
	{
		checkContainsKey(key);
		return (String[]) networkTable.get(key);
	}

	public static Object getValue(String key, Object defaultValue)
	{
		Object value = networkTable.get(key);
		if (value != null)
			return value;
		else
			return defaultValue;
	}

	public static boolean getBoolean(String key, boolean defaultValue)
	{
		return ((Boolean) getValue(key, new Boolean(defaultValue))).booleanValue();
	}

	public static double getDouble(String key, double defaultValue)
	{
		return ((Double) getValue(key, new Double(defaultValue))).doubleValue();
	}

	public static String getString(String key, String defaultValue)
	{
		return (String) getValue(key, defaultValue);
	}

	public static byte[] getRaw(String key, byte[] defaultValue)
	{
		return (byte[]) getValue(key, defaultValue);
	}

	public static boolean[] getBooleanArray(String key, boolean[] defaultValue)
	{
		return (boolean[]) getValue(key, defaultValue);
	}

	public static double[] getDoubleArray(String key, double[] defaultValue)
	{
		return (double[]) getValue(key, defaultValue);
	}

	public static String[] getStringArray(String key, String[] defaultValue)
	{
		return (String[]) getValue(key, defaultValue);
	}

	public static boolean setDefaultBoolean(String key, boolean defaultValue)
	{
		defaultTable.put(key, new Boolean(defaultValue));
		return true;
	}

	public static boolean setDefaultDouble(String key, double defaultValue)
	{
		defaultTable.put(key, new Double(defaultValue));
		return true;
	}

	public static boolean setDefaultString(String key, String defaultValue)
	{
		defaultTable.put(key, defaultValue);
		return true;
	}

	public static boolean setDefaultRaw(String key, byte[] defaultValue)
	{
		defaultTable.put(key, defaultValue);
		return true;
	}

	public static boolean setDefaultBooleanArray(String key, boolean[] defaultValue)
	{
		defaultTable.put(key, defaultValue);
		return true;
	}

	public static boolean setDefaultDoubleArray(String key, double[] defaultValue)
	{
		defaultTable.put(key, defaultValue);
		return true;
	}

	public static boolean setDefaultStringArray(String key, String[] defaultValue)
	{
		defaultTable.put(key, defaultValue);
		return true;
	}

	public static void setEntryFlags(String key, int flags)
	{

	}

	public static int getEntryFlags(String key)
	{
		return ((Integer) entryFlags.get(key)).intValue();
	}

	public static void deleteEntry(String key)
	{
		networkTable.remove(key);
	}

	public static void deleteAllEntries()
	{
		networkTable = new Hashtable<>();
	}

	public static EntryInfo[] getEntries(String prefix, int types)
	{
		// TODO build this
		return null;
	}

	public static void flush()
	{
		// no-op - no network communication for this unit test library
	}

	@FunctionalInterface
	public interface EntryListenerFunction
	{
		void apply(int uid, String key, Object value, int flags);
	}

	public static int addEntryListener(String prefix, EntryListenerFunction listener, int flags)
	{
		// TODO build this
		return 0;
	}

	public static void removeEntryListener(int entryListenerUid)
	{
		// TODO build this
	}

	@FunctionalInterface
	public interface ConnectionListenerFunction
	{
		void apply(int uid, boolean connected, ConnectionInfo conn);
	}

	public static int addConnectionListener(ConnectionListenerFunction listener, boolean immediateNotify)
	{
		// TODO build this
		return 0;
	}

	public static void removeConnectionListener(int connListenerUid)
	{
		// TODO build this
	}

	// public static void createRpc(String key, byte[] def, IRpc rpc);
	// public static void createRpc(String key, ByteBuffer def, int
	// def_len, IRpc rpc);
	public static byte[] getRpc(String key) throws TableKeyNotDefinedException
	{
		// TODO build this
		return null;
	}

	public static byte[] getRpc(String key, byte[] defaultValue)
	{
		// TODO build this
		return null;
	}

	public static int callRpc(String key, byte[] params)
	{
		// TODO build this
		return 0;
	}

	public static int callRpc(String key, ByteBuffer params, int params_len)
	{
		// TODO build this
		return 0;
	}
	// public static byte[] getRpcResultBlocking(int callUid);
	// public static byte[] getRpcResultNonblocking(int callUid) throws
	// RpcNoResponseException;

	public static void setNetworkIdentity(String name)
	{
		networkIdentity = name;
	}

	public static void startServer(String persistFilename, String listenAddress, int port)
	{
		// TODO build this
	}

	public static void stopServer()
	{
		// TODO build this
	}

	public static void startClient()
	{
		startClient((String) null, 0);
	}

	public static void startClient(String serverName, int port)
	{
		startClient(new String[] {serverName}, new int[] {port});
	}

	public static void startClient(String[] serverNames, int[] ports)
	{
		// TODO build this
	}

	public static void stopClient()
	{
		// TODO build this
	}

	public static void setServer(String serverName, int port)
	{
		setServer(new String[] {serverName}, new int[] { port });
	}

	public static void setServer(String[] serverNames, int[] ports)
	{
		// TODO build this
	}

	public static void startDSClient(int port)
	{
		// TODO build this
	}

	public static void stopDSClient()
	{
		// TODO build this
	}

	public static void setUpdateRate(double interval)
	{
		// TODO build this
	}

	public static ConnectionInfo[] getConnections()
	{
		// TODO build this
		return null;
	}

	public static void savePersistent(String filename) throws PersistentException
	{
		// TODO build this
	}

	public static String[] loadPersistent(String filename) throws PersistentException
	{
		// TODO build this
		return null;
	}

	public static long now()
	{
		return System.currentTimeMillis();
	}

	@FunctionalInterface
	public interface LoggerFunction
	{
		void apply(int level, String file, int line, String msg);
	}

	public static void setLogger(LoggerFunction func, int minLevel)
	{
		logger = func;
	}

	public class DefaultLoggerFunction implements LoggerFunction
	{
		public int MIN_LEVEL = 0;

		public void apply(int level, String file, int line, String msg)
		{
			String levelMessage = "TRACE";
			switch (level)
			{
			case 0: 
				levelMessage = "DEBUG";
				break;
			case 1:
				levelMessage = "INFO";
				break;
			case 2:
				levelMessage = "WARN";
				break;
			case 3:
				levelMessage = "ERROR";
				break;
			}
			System.out.println(levelMessage + ": " + file + ": line: " + line + ": " + msg);
		}
	}
}
