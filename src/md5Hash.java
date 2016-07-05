import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

	
public class md5Hash {

	public static void main(String[] args)  {
		/*
		 * Initialize all the given parameters in String variables
		 * String hash=> Given hash to be decoded
		 * String myEmail=>Candidates email address
		 * String myEmailHash=> Calculate md5 value of the candidates email address 
		 * chars[] => List all possible characters of the secret email address in a char array for looping
		 */
        String hash="2d21bcb0ef49da56b1bb9a398bcd89b28435f5a2948281c7d9a2b774bc6857096b8e96be75ec518fa69144df45a44f0dd5b5f8b9220c60a387fcb37e650932e311d378a2ec38d7d18622231bf63fc8b14155efd33089313776b5bee1353933e402ff52d37beb8ace311b2b2801fdf3d15abf7358da4a22507384533af172721bdafb43b54d839197c60fa5eaa1ab58e94b4ef4f26d89f96f68350ec66c17b66bc11e5f1e54d7bfbd630b416983b6599ca8d5cda6c2de1d2712606cd0703d875bb6cd9a1c66a98e970183c20ded9aba0e5194243ec396e17aa0d49f11fd80590e03345e5cbcf477f77247e72c16497c31a52aff3cf0fc9624f11768a8c5d9a8709d9e6d911024f652b3d766115f10049069d13b201549d8e4c4635cde42632a6085f259f58fe152fd4a9477cde89a8b242345c8255b90c1c396b2a68ca18461a65c114621e49a244bb18655b32c240900c254452f7f8e0655172cdeec115686570fdd85ea9356c555ff6a95acbb4093eaeab95e7c1872e1aed756326f722c356b7a87525bf215c3d55e918f878ac769e2b1ca170c29ac3f72720a61e4ee04f9d06422107b93fb94121ce6e9f8f29c0aab0b0cd143b3d5df14eb029cb3fa2f5e3b";
		String myEmail="yashnaik@email.arizona.edu";
		String myEmailHash=calcMD5Hash(myEmail);
		int breaker=0;
		char chars[]="abcdefghijklmnopqrstuvwxyz._@+0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
			
		/* Algorithm  for finding the secret email address
		 * 
		 * 
		 * Length of hash string =896
		 * Md5 hashing generates a 128 bit hash which is equal to 32 4bit hex numbers. We can conclude that there are 896/32=28
		 * concatenations done on the hash string. Since, every concatenations adds 2 characters we can conclude that 
		 * there are 28*2=56 characters in the secret email address. We know the string ends with "anvato.com" 
		 * a 10 lettered string. So we already know the last 10 characters of the secret email address. Therefore,
		 * we need to compute 46 more characters which will need running the search algorithm 23 times
		 */
		
			
		String email="";//Initialize a String to hold the secret email address
			for(int k=0;k<23;k++){                       //Run the algorithm 23 times
				
				String Hash_piece=hash.substring(k*32, k*32+32); //On every iterations get a 32 length chunk of the string
				
				//String Hash_piece=hash.substring(32,32);
			
			for(int i=0;i<chars.length;i++)
			{
				for(int j=0;j<chars.length;j++){                 //Get two characters from char array  
					//check if after hashing and concatenating as required do they equal to the 32 length chunk of the hash string
					String parts=email+ String.valueOf(chars[i])+String.valueOf(chars[j]);
					String partsHash=calcMD5Hash(parts);
					
					String checkString=calcMD5Hash( myEmailHash+parts+partsHash);
					if(checkString.equals(Hash_piece)){  //If, value is same, then we have obtained 2 more characters of the secret email address
						//System.out.println(email+parts);
						email=parts;
						//System.out.println(parts);
						breaker=1;
					}
					
				}
				if(breaker==1){   //If two characters for the current iteration are found break from loop and move to the next iteration
					breaker=0;
					break;
				}
				
			}
			}
		
		System.out.println("Secret email is => "+ email+"anvato.com");
	}
	
	
	/*
	 * Function to calculate md5 hashing of a given string
	 * MessageDigest class provides implementation of Hashing using MD5 algorithm
	 */
	public static String calcMD5Hash(String text){
		
		
		try {
			MessageDigest md= MessageDigest.getInstance("MD5"); //Select md5 as algorithm for hashing
			md.update(text.getBytes());                         
			byte textbyte[]= md.digest();                      //Compute hash value of the given string
			StringBuffer hexText = new StringBuffer();
	    	for (int i=0;i<textbyte.length;i++) {              //Convert byte array to Hex format the standard representation of md5
	    		String hex=Integer.toHexString(0xff & textbyte[i]);
	   	     	if(hex.length()==1) hexText.append('0');
	   	     	hexText.append(hex);
	    	}
	    	//System.out.println(hexText);
			
			return hexText.toString();
			
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "-1";
	}

}
