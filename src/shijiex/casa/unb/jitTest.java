package shijiex.casa.unb;

public class jitTest {

	public static void JITMethod(int no){
		int sum = 0;
		for(int i=0; i<no; i++){
			sum+=i;
		}
		
		int j =4;
		int r=0;
		r++;
		r = j-r;
		j = r-j;
	}
	
	
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for(int i=0; i<10000000;i++){
			JITMethod(i);
		}
	}

}
