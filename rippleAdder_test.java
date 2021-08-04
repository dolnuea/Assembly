/**
 * @author Luna Dagci
 */
public class rippleAdder_test {
    public static void main(String[] args) throws Exception {
        bit_test.runTests();
        longword_test.runTests();
        runTests();

    }

    /**
     *
     * @throws Exception when a case fails
     */
    public static void runTests() throws Exception {
        System.out.println("---------------------RippleAdder TEST CASES------------------");
        System.out.println("---Addition---");
        testAdd(30, 20);
        testAdd(10, -10);
        testAdd(-10, 10);
        testAdd(100, 100);
        testAdd(-30, -30);
        testAdd(-70, 20);
        testAdd(99, 55);
        testAdd(4747, -2929);
        testAdd(-1111, 1110);
        testAdd(56, 4);
        testAdd(-25, -25);
        testAdd(-40, 20);
        System.out.println("---Subtraction---");
        testSubtract(30, 20);
        testSubtract(10, -10);
        testSubtract(-20, 10);
        testSubtract(100, 100);
        testSubtract(-30, -30);
        testSubtract(70, -20);
        testSubtract(40, 80);
        testSubtract(55, -5);
        testSubtract(-3973, 2348);
        testSubtract(908948, 283794);
        testSubtract(-1, -2);
        testSubtract(34, -35);


    }

    /*
    Helper test functions
     */
    public static void testAdd(int x, int y) throws Exception {
        longword a = new longword(x), b = new longword(y);
        int result = rippleAdder.add(a, b).getSigned();
        if(result == (x + y))
            System.out.println(x +" + "+ y + " = "+ result +" PASS");
        else throw new Exception(x +" + "+ y + " = "+ result +" FAIL");
    }
    public static void testSubtract(int x, int y) throws Exception {
        longword a = new longword(x), b = new longword(y);
        int result = rippleAdder.subtract(a, b).getSigned();
        if(result == (x-y))
            System.out.println(x +" - "+ y + " = "+ result +" PASS");
        else throw new Exception(x +" - "+ y + " = "+ result +" FAIL");
    }
}
