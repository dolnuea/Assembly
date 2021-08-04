public class multiplier_test {
    public static void main(String[] args) throws Exception {
        bit_test.runTests();
        longword_test.runTests();
        rippleAdder_test.runTests();
        runTests();
    }

    public static void runTests() throws Exception {
        System.out.println("---------------------Multiplier TEST CASES------------------");
        testMultiplier(6, 5);
        testMultiplier(-6, 5);
        testMultiplier(-6, -5);
        testMultiplier(15, 15);
        testMultiplier(-15, -15);
        testMultiplier(-15, 15);
    }
    public static void testMultiplier(int x, int y) throws Exception {
        longword result = multiplier.multiply(new longword(x), new longword(y));
            System.out.println(x + " x " + y + " = " + result.getSigned());
    }
}
