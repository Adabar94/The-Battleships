package constants;

public abstract class Logic {
	/**
	 * Checks if value if between min and max
	 * @param value
	 * @param min
	 * @param max
	 * @return boolean
	 */
	public static boolean inBetween(int value ,int min, int max){
		if(min <= value && value <= max){
			return true;
		}
		return false;
	}


}
