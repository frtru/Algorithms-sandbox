package core;

import java.util.Map;

import com.google.common.collect.ImmutableMap;

public class Direction {

	// Following constants can now be used safely to access 
	// direction IDs
	public static final int NORTH = 0;
	public static final int SOUTH = 1;
	public static final int EAST =  2;
	public static final int WEST =  3;
		
	// This map forces a constant collection of directions IDs
	// recognized through the application and also forces
	// a counter for how many directions there are
	public static Map<Integer, Integer> IDS =  ImmutableMap.of(
		NORTH, 0x00,
		SOUTH, 0x01,
		EAST, 0x02,
		WEST, 0x03
	);

	public static boolean safetyCheck(int a_direction) {
		if (!IDS.containsKey(a_direction)) {
			System.err.println("Caught unrecognized direction: " + a_direction);
			return false;
		}
		
		return true;
	}
	
}


