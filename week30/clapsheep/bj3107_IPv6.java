import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = br.readLine();
        String[] result = new String[8];
        Arrays.fill(result, "0000");

        if(input.contains("::")){
            String[] divide = input.split("::",-1);
            String[] left = divide[0].isEmpty() ? new String[0] : divide[0].split(":");
            String[] right = divide.length > 1 && !divide[1].isEmpty() ? divide[1].split(":") : new String[0];

            for(int i = 0 ; i < left.length; i++){
                result[i] = makeFull(left[i]);
            }
            for(int i = 0; i < right.length; i++){
                result[7-i] = makeFull(right[right.length-1-i]);
            }
        }else{
            String[] blocks = input.split(":");
            for (int i = 0; i < blocks.length; i++) {
                result[i] = makeFull(blocks[i]);
            }
        }
        System.out.println(String.join(":", result));
        
    }
    static String makeFull(String s){
        StringBuilder sb = new StringBuilder();
        int leng = s.length();
        for(int i = 0; i < 4-leng; i++){
            sb.append("0");
        }
        sb.append(s);
        return sb.toString();
    }
}
