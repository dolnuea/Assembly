/**
 * @author Luna Dagci
 * Testing class for longword methods and their implementations
 */
public class longword_test {
    public static void main(String[] args) throws Exception {
        bit_test.runTests();
        runTests();
    }

    /**
     * Runs all tests
     * @throws Exception when there is a Fail in test cases
     */
    public static void runTests() throws Exception {
        System.out.println("---------------------Longword TEST CASES------------------");
        TestGetBit();
        TestSetBit();
        TestAnd();
        TestOr();
        TestXor();
        TestNot();
        TestRightShift();
        TestLeftShift();
        TestGetUnsigned();
        TestGetSigned();
        TestCopy();
        TestSet();
        TestToString();
    }

    /*
    Helper Functions (Verified results on https://www.rapidtables.com/calc/math/binary-calculator.html)
     */
    /**
     * Test the getBit(int) method for longword
     * @throws Exception if result is incorrect
     * The test cases can be changed by just replacing the values, but the result must be calculated by hand.
     */
    static void TestGetBit() throws Exception {
        if(new longword(10).getBit(29).getValue() == 0)
            System.out.println("getBit(29) for "+10+" PASS");
        else
            throw new Exception("getBit(29) for "+10+" FAIL");
        if(new longword(-10).getBit(29).getValue() == 1)
            System.out.println("getBit(29) for "+-10+" PASS");
        else
            throw new Exception("getBit(29) for "+-10+" FAIL");

        if(new longword(50).getBit(30).getValue() == 1)
            System.out.println("getBit(30) for "+50+" PASS");
        else
            throw new Exception("getBit(30) for "+50+" FAIL");

        if(new longword(-9).getBit(0).getValue() == 1)
            System.out.println("getBit(0) for "+-9+" PASS");
        else
            throw new Exception("getBit(0) for "+-9+" FAIL");
    }

    /**
     * Test the setBit(int, bit) method
     * @throws Exception if result is incorrect
     */
    static void TestSetBit() throws Exception {
        longword lw = new longword();

        lw.setBit(27, new bit(1));
        if (lw.getSigned() == 16 ){
            System.out.println("setBit(27, bit(1)) PASS");
        } else {
            throw new Exception("setBit(27, bit(1)) FAIL");
        }
        lw.set(0); //clear longword
        lw.setBit(0, new bit(1));
        if (lw.getSigned() == -2147483648){
            System.out.println("setBit(0, bit(1)) PASS");
        } else {
            throw new Exception("setBit(0, bit(1)) FAIL ");
        }
        lw.set(0); //clear longword
        lw.setBit(6, new bit(1));
        if (lw.getSigned() == 33554432 ){
            System.out.println("setBit(6, bit(1)) PASS");
        } else {
            throw new Exception("setBit(6, bit(1)) FAIL");
        }
        lw.set(0); //clear longword
        lw.setBit(6, new bit(0));
        if (lw.getSigned() == 0 ){
            System.out.println("setBit(6, bit(0)) PASS");
        } else {
            throw new Exception("setBit(6, bit(0)) FAIL");
        }
    }

    /**
     * Test AND for two longwords
     * I have used this resource to verify my bitwise operations https://www.rapidtables.com/calc/math/binary-calculator.html and the windows calculator in programmer
     * mode
     * @throws Exception if result is incorrect
     */
    static void TestAnd() throws Exception {

        if (new longword(70).and(new longword(40)).getSigned() == 0){
            System.out.println(70 + " and " + 40 + " PASS");
        } else {
            throw new Exception(70 + " and " + 40 + " FAIL");
        }
        if (new longword(-3).and(new longword(3)).getSigned() == 1){
            System.out.println(-3 + " and " + 3 + " PASS");
        } else {
            throw new Exception(-3 + " and " + 3 + " FAIL");
        }
        if (new longword(-8).and(new longword(-4)).getSigned() == -8){
            System.out.println(-8 + " and " + -4 + " PASS");
        } else {
            throw new Exception(-8 + " and " + -4 + " FAIL");
        }
        if (new longword(354353545).and(new longword(265757567)).getSigned() == 85393673){
            System.out.println(354353545 + " and " + 265757567 + " PASS");
        } else {
            throw new Exception(354353545 + " and " + 265757567 + " FAIL");
        }
        if (new longword(870976540).and(new longword(27385252)).getSigned() == 27266052){
            System.out.println(870976540 + " and " + 27385252 + " PASS");
        } else {
            throw new Exception(870976540 + " and " + 27385252 + " FAIL");
        }
        if (new longword(-870976540).and(new longword(-27385252)).getSigned() == -871095740){
            System.out.println(-870976540 + " and " + -27385252 + " PASS");
        } else {
            throw new Exception(-870976540 + " and " + -27385252 + " FAIL");
        }

    }
    /**
     * Test OR for two longwords
     * I have used this resource to verify my bitwise operations https://www.rapidtables.com/calc/math/binary-calculator.html and the windows calculator in programmer
     * mode
     * @throws Exception if result is incorrect
     */
    static void TestOr() throws Exception {
        if (new longword(70).or(new longword(40)).getSigned() == 110 ){
            System.out.println(70 + " or " + 40 + " PASS");
        } else {
            throw new Exception(70 + " or " + 40 + " FAIL");
        }
        if (new longword(-3).or(new longword(3)).getSigned() == -1 ){
            System.out.println(-3 + " or " + 3 + " PASS");
        } else {
            throw new Exception(70 + " or " + 3 + " FAIL");
        }
        if (new longword(-8).or(new longword(-4)).getSigned() == -4 ){
            System.out.println(-8 + " or " + -4 + " PASS");
        } else {
            throw new Exception(-8 + " or " + -4 + " FAIL");
        }
        if (new longword(354353545).or(new longword(265757567)).getSigned() == 534717439){
            System.out.println(354353545 + " or " + 265757567 + " PASS");
        } else {
            throw new Exception(354353545 + " or " + 265757567 + " FAIL");
        }
        if (new longword(870976540).or(new longword(27385252)).getSigned() == 871095740){
            System.out.println(870976540 + " or " + 27385252 + " PASS");
        } else {
            throw new Exception(870976540 + " or " + 27385252 + " FAIL");
        }
        if (new longword(-870976540).or(new longword(-27385252)).getSigned() == -27266052){
            System.out.println(-870976540 + " or " + -27385252 + " PASS");
        } else {
            throw new Exception(-870976540 + " or " + -27385252 + " FAIL");
        }
    }

    /**
     * Test XOR for two longwords
     * I have used this resource to verify my bitwise operations https://www.rapidtables.com/calc/math/binary-calculator.html and the windows calculator in programmer
     * mode
     * @throws Exception if result is incorrect
     */
    static void TestXor() throws Exception {
        if (new longword(70).xor(new longword(40)).getSigned() == 110 ){
            System.out.println(70 + " xor " + 40 + " PASS");
        } else {
            throw new Exception(70 + " xor " + 40 + " FAIL");
        }
        if (new longword(-3).xor(new longword(3)).getSigned() == -2 ){
            System.out.println(-3 + " xor " + 3 + " PASS");
        } else {
            throw new Exception(70 + " xor " + 3 + " FAIL");
        }
        if (new longword(-8).xor(new longword(-4)).getSigned() == 4 ){
            System.out.println(-8 + " xor " + -4 + " PASS");
        } else {
            throw new Exception(-8 + " xor " + -4 + " FAIL");
        }
        if (new longword(354353545).xor(new longword(265757567)).getSigned() == 449323766){
            System.out.println(354353545 + " xor " + 265757567 + " PASS");
        } else {
            throw new Exception(354353545 + " xor " + 265757567 + " FAIL");
        }
        if (new longword(870976540).xor(new longword(27385252)).getSigned() == 843829688){
            System.out.println(870976540 + " xor " + 27385252 + " PASS");
        } else {
            throw new Exception(870976540 + " xor " + 27385252 + " FAIL");
        }
        if (new longword(-870976540).xor(new longword(-27385252)).getSigned() == 843829688){
            System.out.println(-870976540 + " xor " + -27385252 + " PASS");
        } else {
            throw new Exception(-870976540 + " xor " + -27385252 + " FAIL");
        }
    }

    /**
     * Test NOT for a longword
     * I have used this resource to verify my bitwise operations https://www.rapidtables.com/calc/math/binary-calculator.html and the windows calculator in programmer
     * mode
     * @throws Exception if result is incorrect
     */
    static void TestNot() throws Exception {
        if (new longword(70).not().getSigned() == -71){
            System.out.println(70 + " not PASS");
        } else {
            throw new Exception(70 + " not FAIL");
        }
        if (new longword(-60).not().getSigned() == 59){
            System.out.println(-60 + " not PASS");
        } else {
            throw new Exception(-60 + " not FAIL");
        }
        if (new longword(1).not().getSigned() == -2){
            System.out.println(1 + " not PASS");
        } else {
            throw new Exception(1 + " not FAIL");
        }
        if (new longword(5000).not().getSigned() == -5001){
            System.out.println(5000 + " not PASS");
        } else {
            throw new Exception(5000 + " not FAIL");
        }
        if (new longword(843829688).not().getSigned() == -843829689){
            System.out.println(843829688 + " not PASS");
        } else {
            throw new Exception(843829688 + " not FAIL");
        }
    }

    /*
    The left shift and right shift operators should not be used for negative numbers. The result of is undefined behaviour if any of the operands is a negative number.
     */

    /**
     * Test the rightShift(int) method
     * @throws Exception if result is incorrect
     */
    static void TestRightShift() throws Exception {

        if(new longword(192).rightShift(3).getSigned() == 24)
            System.out.println("rightShift("+ 3 +") for "+192+" PASS");
            else
                throw new Exception("rightShift("+ 3 +") for "+192+" FAIL");

        if(new longword(-192).rightShift(4).getSigned() == -12)
            System.out.println("rightShift("+ 4 +") for "+-192+" PASS");
        else
            throw new Exception("rightShift("+ 4 +") for "+-192+" FAIL");

        if(new longword(200).rightShift(1).getSigned() == 100)
            System.out.println("rightShift("+ 1 +") for "+200+" PASS");
        else
            throw new Exception("rightShift("+ 1 +") for "+200+" FAIL");

        if(new longword(604).rightShift(2).getSigned() == 151)
            System.out.println("rightShift("+ 2 +") for "+604+" PASS");
        else
            throw new Exception("rightShift("+ 2 +") for "+604+" FAIL");

        if(new longword(400400).rightShift(4).getSigned() == 25025)
            System.out.println("rightShift("+ 4 +") for "+400400+" PASS");
        else
            throw new Exception("rightShift("+ 4 +") for "+400400+" FAIL");
    }

    /**
     * Test the leftShift(int) method
     * @throws Exception if result is incorrect
     *
     */
    static void TestLeftShift() throws Exception {
        if(new longword(192).leftShift(3).getSigned() == 1536)
            System.out.println("leftShift("+ 3 +") for "+192+" PASS");
        else
            throw new Exception("leftShift("+ 3 +") for "+192+" FAIL");

        if(new longword(-192).leftShift(4).getSigned() == -3072)
            System.out.println("leftShift("+ 4 +") for "+-192+" PASS");
        else
            throw new Exception("leftShift("+ 4 +") for "+-192+" FAIL");

        if(new longword(200).leftShift(1).getSigned() == 400)
            System.out.println("leftShift("+ 1 +") for "+200+" PASS");
        else
            throw new Exception("leftShift("+ 1 +") for "+200+" FAIL");

        if(new longword(604).leftShift(2).getSigned() == 2416)
            System.out.println("leftShift("+ 2 +") for "+604+" PASS");
        else
            throw new Exception("leftShift("+ 2 +") for "+604+" FAIL");

        if(new longword(1010).leftShift(9).getSigned() == 517120)
            System.out.println("leftShift("+ 9 +") for "+1010+" PASS");
        else
            throw new Exception("leftShift("+ 9 +") for "+1010+" FAIL");
    }

    /**
     * Test the getUnsigned() method
     * @throws Exception if result is incorrect
     */
    static void TestGetUnsigned() throws Exception {
        if (new longword(70).getUnsigned() == 70){
            System.out.println("getUnsigned() "+70+" PASS");
        } else {
            throw new Exception("getUnsigned() "+70+" PASS");
        }
        if (new longword(-70).getUnsigned() == 4294967226L){
            System.out.println("getUnsigned() "+-70+" PASS");
        } else {
            throw new Exception("getUnsigned() "+-70+" PASS\"");
        }
        if (new longword(768768696).getUnsigned() == 768768696){
            System.out.println("getUnsigned() "+768768696+" PASS");
        } else {
            throw new Exception("getUnsigned() "+768768696+" PASS");
        }
        if(new longword(-248925092).getUnsigned() == 4046042204L){
            System.out.println("getSigned() "+-248925092+" PASS");
        } else {
            throw new Exception("getSigned() "+-248925092+" PASS");
        }
    }

    /**
     * Test the getSigned() method
     * @throws Exception if result is incorrect
     */
    static void TestGetSigned() throws Exception {
        if(new longword(-77).getSigned() == -77){
            System.out.println("getSigned() "+-77+" PASS");
        } else {
            throw new Exception("getSigned() "+-77+" PASS");
        }
        if(new longword(77).getSigned() == 77){
            System.out.println("getSigned() "+77+" PASS");
        } else {
            throw new Exception("getSigned() "+77+" PASS");
        }
        if(new longword(-5656).getSigned() == -5656){
            System.out.println("getSigned() "+-5656+" PASS");
        } else {
            throw new Exception("getSigned() "+-5656+" PASS");
        }
        if(new longword(-248925092).getSigned() == -248925092){
            System.out.println("getSigned() "+-248925092+" PASS");
        } else {
            throw new Exception("getSigned() "+-248925092+" PASS");
        }
        if(new longword(-1).getSigned() == -1){
            System.out.println("getSigned() "+-1+" PASS");
        } else {
            throw new Exception("getSigned() "+-1+" PASS");
        }
        if(new longword(1).getSigned() == 1){
            System.out.println("getSigned() "+1+" PASS");
        } else {
            throw new Exception("getSigned() "+1+" PASS");
        }
    }

    /**
     * Test the copy(longword) method
     * @throws Exception if result is incorrect
     */
    static void TestCopy() throws Exception {
        longword lw = new longword(60);
        lw.copy(new longword(70));
        if (lw.getSigned() == 70){
            System.out.println("copy(longword) PASS");
        } else {
            throw new Exception("copy(longword) FAIL");
        }
        longword lw_neg = new longword(-60);
        lw_neg.copy(new longword(-70));
        if (lw_neg.getSigned() == -70){
            System.out.println("copy(longword) for negative longword PASS");
        } else {
            throw new Exception("copy(longword) for negative longword FAIL");
        }
    }

    /**
     * Test the set(int) method
     * @throws Exception if result is incorrect
     */
    static void TestSet() throws Exception {
        longword lw = new longword();
        lw.set(80);
        if (lw.getSigned() == 80){
            System.out.println("set(80) PASS");
        } else {
            throw new Exception("set(80) FAIL");
        }
        lw.set(-80);
        if (lw.getSigned() == -80){
            System.out.println("set(-80) PASS");
        } else {
            throw new Exception("set(-80) FAIL");
        }
        lw.set(-8095488);
        if (lw.getSigned() == -8095488){
            System.out.println("set(-8095488) PASS");
        } else {
            throw new Exception("set(-8095488) FAIL");
        }
        lw.set(349478979);
        if (lw.getSigned() == 349478979){
            System.out.println("set(349478979) PASS");
        } else {
            throw new Exception("set(349478979)  FAIL");
        }
    }

    /**
     * Test the toString() method
     */
    static void TestToString() {
        System.out.println("toString() Representations:\nLongword 500: " + new longword(500).toString() +"\nLongword -500: " + new longword(-500).toString()+
                "\nLongword -49803468: " + new longword(-49803468).toString()+"\nLongword 49803468: " + new longword(49803468).toString());
    }
}
