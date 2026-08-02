package realTimeApplication_JDBC;

public class OTPGenerationAndVerificationProcess {
       public static int OTPGeneration() {
    	   double random = Math.random();
    	   int r = (int)(random*900000)+100000;
    	   return r;
       }
}
