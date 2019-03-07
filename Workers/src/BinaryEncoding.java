import java.util.ArrayList;

public class BinaryEncoding {
	
	public static ArrayList<Integer> encode6(int value){
	    boolean[] bits = new boolean[6];
	    for (int i = (6-1); i >= 0; i--) {
	        bits[i] = (value & (1 << i)) != 0;
	    }
	    ArrayList<Integer> result = new ArrayList<Integer>();
	    for (int i=0; i<6; i++) {
	    	if (bits[i])
	    		result.add(1);
	    	else 
	    		result.add(0);
	    }
	    return result;
	}
	
	public static int decode6(ArrayList<Integer> binary) {
		int result = 0;
		for (int i = (6-1); i >= 0; i--) {
	    	result += binary.get(i) * Math.pow(2, i);
	    }
		return result;
	}
	


}
