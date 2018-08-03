import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.HashMap;

public class HW1_V2{
    static Integer counter = 0;
    static String tokenstring; //string of the user's inputs
    public static Integer gate(){
        HashMap<String, Integer> hashoutputs = new HashMap<String, Integer>();
        hashoutputs.put("NA",6);
        System.out.println("Input:");
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); //this reads the user's input
        try{
            Beginning:
                while(true){
                String input = br.readLine();
                tokenstring = tokenstring + "\t" + input;
                if(input == null){
                    System.out.println("Output:");
                    break Beginning; //this breaks out of the while statement so that the program exits
                    
                }
                else{
                    String[] tokens = input.split("\t"); //this splits the string every time there is a tab and puts it into "tokens"
                    if(tokens[0].equals("NOT")){
                        int flag = 0;
                        if(tokens[1].equals("NA")){
                            flag = 1; //flag helps detect if the user enters NA
                        }
                        if(tokens[2].equals("NA")){
                            flag = 1;
                        }
                        if(flag == 0){ //if user doesn't input NA into a NOT gate, print warning message and exits program
                            System.out.println("Warning! You have tried to use a NOT gate with two inputs, instead of one. Please use one input and NA");
                            break Beginning;
                        }

                    }
                    if(tokens.length > 4){
                        System.out.println("Too many inputs! Try again.");
                        break Beginning;

                    }
                    if(hashoutputs.containsKey(tokens[1])){
                    }
                    else{
                        counter++; //the counter is used to add up how many inputs there are, ignoring the outputs
                        hashoutputs.put(tokens[1],0);
                    }
                    if(hashoutputs.containsKey(tokens[2])){
                    }
                    else{
                        counter++;
                        hashoutputs.put(tokens[2],0);
                    }
                    hashoutputs.put(tokens[3],0);
                }
            }
            
        }catch(IOException e){
            //System.out.println(e);
        }
        return counter;
    }
    
    public static void getResults(String _tokenstring, int[] _bits, int size){ //this function gets the logic gate's bitwise results
        HashMap<String, Integer> hashresults = new HashMap<String, Integer>();
        hashresults.put("NA",6);
        int num = 0;
        int i = 1;
        int j = 0;
        int x = 0;
        int sizeofp = size<<size; //size<<size means size*2^(size)
        String[] inputkey = new String[size];
        String[] tokens = _tokenstring.split("\t");
       
       while(num<sizeofp){
           if(num == 0){
                while(num!=size) {
                
                if(hashresults.containsKey(tokens[i+1])){
                }
                else{                                          // If the unique number of inputs (variable size) is 5, then
                    hashresults.put(tokens[i+1],_bits[num]); //this associates the first 5 permutation bits to a input key and save those input keys
                    System.out.print(_bits[num] +"\t");
                    inputkey[num] = tokens[i+1];
                    num++; //needs to increment
                
                }
                
                if(hashresults.containsKey(tokens[i+2])){
                    
                }
                else{
                    hashresults.put(tokens[i+2],_bits[num]);
                    System.out.print(_bits[num] +"\t");
                    inputkey[num] = tokens[i+2];
                    num++;
                }
                
                hashresults.put(tokens[i+3],5);
                i+=4;
                }
            }
            
           else{
               for(i = 0; i < size; i++){
                hashresults.put(inputkey[i],_bits[num]); //uses the saved input keys to associate the rest of the permutation bits
                   System.out.print(_bits[num] +"\t");
                num++;
                
                }
            }
            for(i = 1; i < (tokens.length)-1; i+=4){    //Does gate calculations
                if(tokens[i].equals("AND")){
                    Integer token1 = hashresults.get(tokens[i+1]);
                    Integer token2 = hashresults.get(tokens[i+2]);
                    hashresults.put(tokens[i+3],token1&token2);
                }
                if(tokens[i].equals("OR")){
                    Integer token1 = hashresults.get(tokens[i+1]);
                    Integer token2 = hashresults.get(tokens[i+2]);
                    hashresults.put(tokens[i+3],token1|token2);
                }
                
                if(tokens[i].equals("NOT")){
                    Integer token1 = hashresults.get(tokens[i+1]);
                    Integer token2 = hashresults.get(tokens[i+2]);
                    Integer result = 0;
                   
                    if (token1 == 0){
                        hashresults.put(tokens[i+3],1);
                    }
                    if (token1 == 1){
                        hashresults.put(tokens[i+3],0);
                    }
                    if (token2 == 0){
                        hashresults.put(tokens[i+3],1);
                    }
                    if (token2 == 1){
                        hashresults.put(tokens[i+3],0);
                    }
                    
                }
                
                if(tokens[i].equals("XOR")){
                    Integer token1 = hashresults.get(tokens[i+1]);
                    Integer token2 = hashresults.get(tokens[i+2]);
                    hashresults.put(tokens[i+3],token1^token2);
                }
                
                if(tokens[i].equals("NAND")){
                    Integer token1 = hashresults.get(tokens[i+1]);
                    Integer token2 = hashresults.get(tokens[i+2]);
                    Integer result = 0;
                    if ((token1&token2) == 0){
                        result = 1;
                    }
                    if ((token1&token2) == 1){
                        result = 0;
                    }
                    hashresults.put(tokens[i+3],result);
                }
                
                if(tokens[i].equals("NOR")){
                    Integer token1 = hashresults.get(tokens[i+1]);
                    Integer token2 = hashresults.get(tokens[i+2]);
                    Integer result = 0;
                    if ((token1|token2) == 0){
                        result = 1;
                    }
                    if ((token1|token2) == 1){
                        result = 0;
                    }
                    hashresults.put(tokens[i+3],result);
                }
            }
            System.out.println(hashresults.get(tokens[tokens.length-2]));
        }
       }
    
    public static void Printing(String _tokenstring, int size){ //this function prints out the user's inputs and the very last output
        HashMap<String, Integer> hashresults = new HashMap<String, Integer>();
        hashresults.put("NA",6);
        String[] inputkey = new String[size];
        String[] tokens = _tokenstring.split("\t");
        int num = 0;
        int i = 1;
        int sizeofp = size<<size;
        
        if(size == 1){
            if(tokens[i+1].equals("NA")){
                System.out.print(tokens[i+2] +"\t");
            }
            if(tokens[i+2].equals("NA")){
                System.out.print(tokens[i+1] +"\t");
            }
            System.out.println(tokens[tokens.length-2]);
        }
  
        while(num<size-1){
            if(num == 0){
                while(num!=size) {
                    if(hashresults.containsKey(tokens[i+1])){
                    }
                    else{
                        hashresults.put(tokens[i+1],0);
                        System.out.print(tokens[i+1] +"\t");
                        num++;
                        
                    }
                    
                    if(hashresults.containsKey(tokens[i+2])){
                        
                    }
                    else{
                        hashresults.put(tokens[i+2],0);
                        System.out.print(tokens[i+2] +"\t");
                        num++;
                    }
                    
                    hashresults.put(tokens[i+3],5);
                    i+=4;
                }
            }
            
           System.out.println(tokens[tokens.length-2]);
        }
    }
    
    private static int[] TruthTable(int n) { //this function generates the permutations for the truth table
        int row = (int) Math.pow(2,n);
        int num = 0;
        int [] outputstring = new int [n<<n];
        for (int i=0; i<row; i++) {
            for (int j=n-1; j>=0; j--) {
                outputstring [num] =((i/(int) Math.pow(2, j))%2);
                num++;
            }
            
        }
        return outputstring;
    }
    
    public static void main(String[] argvs){ //main function that calls the subfunctions
        HW1_V2 hw1_v2 = new HW1_V2();
        Integer count= hw1_v2.gate();
        int[] little_string = TruthTable(count);
        Printing(tokenstring,count);
        hw1_v2.getResults(tokenstring,little_string,count);
    }
    
    
    
}

