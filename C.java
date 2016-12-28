import java.util.HashMap;

public class C {
	public static void main(String args[]){
		String x=fractionToDecimal(-1,Integer.MIN_VALUE);
	}
    public static String fractionToDecimal(int numerator, int denominator) {
        int l=Math.abs(numerator/denominator);
        String left="";
        if(numerator*denominator>=0){
            left=l+"";
        }else{
            left="-"+l;
        }
        int remain=Math.abs(numerator)%Math.abs(denominator);
        denominator=Math.abs(denominator);
        HashMap<String,Integer> set=new HashMap();
        if(remain==0){
            return left+"";
        }else{
            int b=0;
            String right="";
            int i=0;
            while(true){
                int a=10*remain;
                remain=a%denominator;
                b=a/denominator;
                if(remain==0){
                    right+=b;
                    return left+"."+right;
                }else if(set.containsKey(remain+""+b)){
                    return left+"."+right.substring(0,set.get(remain+""+b))+"("+right.substring(set.get(remain+""+b))+")";
                }else{
                    right+=b;
                    set.put(remain+""+b,i);
                }
                i++;
            }
        }

    }
}