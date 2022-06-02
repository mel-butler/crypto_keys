import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class keys {
    static int p, q, m, f;
    static int public_encrypt_ex;

    //Generate a public key
    public static void main(String[] args) {
        //Produce two prime numbers
        p = primeNumberGenerator();
        q = primeNumberGenerator();
        while (q == p){
            q = primeNumberGenerator();
        }

        // find the modulus
        m = modulus(p, q);

        // find the function
        f = function(p, q);

        // find the public encrypt format by selecting any number that is relatively prime
        // aka when they share no common factors other than one.
        public_encrypt_ex = generateCoPrime(f);

        System.out.println("public key: ");
        System.out.println("encryption exponent = " + public_encrypt_ex);
        System.out.println("modulus = " + m);

    }



    // generate a prime number
    public static int primeNumberGenerator(){
        int num;
        Random rand = new Random(); // generate a random number
        num = rand.nextInt(100) + 1; // generate number twixt 0 - 100

        while (!isPrime(num)){
            num = rand.nextInt(100) + 1;
        }
        return num;
    }

    // checks if a number is prime
    public static boolean isPrime(int numInput){
        if (numInput <= 3 || numInput % 2 == 0){
            return numInput == 2 || numInput == 3; //returns true if number is 2 or 3
        }
        int divider = 3;
        while ((Math.sqrt(numInput) >= divider) && (numInput % divider != 0)){
            divider += 2;
        }
        return numInput % divider != 0; // returns true if prime
    }

    // returns the modulus of the arithmetic that will be used for the key, which
    // is given by their product.
    public static int modulus(int a, int b){
        return a*b;
    }

    public static int function(int a, int b){
        return (a - 1) * (b - 1);
    }

    // calculate the greatest common divider using euclid's method
    public static int gcd(int a, int b) {
        int t;
        while(b != 0){
            t = a;
            a = b;
            b = t % b;
        }
        return a;
    }

    // checks if two numbers are co primes
    public static boolean coPrime(int a, int b){
        return gcd(a,b) == 1;
    }

    // generate random co-prime for a number
    public static int generateCoPrime(int a){
        List<Integer> coPrimes = new ArrayList<>();

        for (int i = 0; i < a; i++ ){
            if(coPrime(a, i)){
                coPrimes.add(i);
            }
        }
        int rand = new Random().nextInt(coPrimes.size());
        return coPrimes.get(rand);
    }

    // generate private decrypt exponent
    public static int generateDecryptExponent(int a, int f){
        return 0;
    }

}
