/**
 * @author Luna Dagci
 * Testing class for bit methods and their implementations
 */
public class bit_test {
    public static void main(String[] args) throws Exception {
        runTests();
    }

    /**
     * Runs all tests
     * @throws Exception when there is a Fail in test cases
     */
    public static void runTests() throws Exception {
        System.out.println("---------------------Bit TEST CASES------------------");
        //test set(int)
        TestSet(new bit(1), 0);
        TestSet(new bit(0), 1);
        // TestSet(new bit(1), 3); //will throw exception since a bit can only be 1 or 0
        // TestSet(new bit(1), -1); //will throw exception since a bit can only be 1 or 0

        //test toggle()
        TestToggle(new bit(1));
        TestToggle(new bit(0));
       // TestToggle(new bit(2)); // Will fail because of invalid value

        //Test set()
        TestSet(new bit(0));
        TestSet(new bit(1));
       // TestSet(new bit(-1)); // Will fail because of invalid value

        //test clear()
        TestClear(new bit(1));
        TestClear(new bit(0));
      //  TestClear(new bit(-4)); // Will fail because of invalid value

        // Test getValue()
        TestGetValue(new bit(1));
        TestGetValue(new bit(0));
       // TestGetValue(new bit(2)); // Will fail because of invalid value

        //Test Logical Operators

        TestAnd();

        TestOr();

        TestXor();

        TestNot();

        //Test toString()
        TestToString(new bit(1));
        TestToString(new bit(0));
      // TestToString(new bit(-43)); // Will fail because of invalid value
    }

    /*Helper Functions*/

    /**
     * Test the and method for bit
     * @throws Exception if result is incorrect
     */
    public static void TestAnd() throws Exception {
        if(new bit(1).and(new bit(0)).getValue() != 0)
            throw new Exception("1 AND 0 FAILED");
        else System.out.println("1 AND 0 PASSED");

        if (new bit(1).and(new bit(1)).getValue() != 1)
            throw new Exception("1 AND 1 FAILED");
        else System.out.println("1 AND 1 PASSED");

        if (new bit(0).and(new bit(1)).getValue() != 0)
            throw new Exception("0 AND 1 FAILED");
        else System.out.println("0 AND 1 PASSED");

        if (new bit(0).and(new bit(0)).getValue() != 0)
            throw new Exception("0 AND 0 FAILED");
        else System.out.println("0 AND 0 PASSED");
    }

    /**
     * Test the or method for bit
     * @throws Exception if result is incorrect
     */
    public static void TestOr() throws Exception {
        if(new bit(1).or(new bit(0)).getValue() != 1)
            throw new Exception("1 OR 0 FAILED");
        else System.out.println("1 OR 0 PASSED");

        if (new bit(1).or(new bit(1)).getValue() != 1)
            throw new Exception("1 OR 1 FAILED");
        else System.out.println("1 OR 1 PASSED");

        if (new bit(0).or(new bit(1)).getValue() != 1)
            throw new Exception("0 OR 1 FAILED");
        else System.out.println("0 OR 1 PASSED");

        if (new bit(0).or(new bit(0)).getValue() != 0)
            throw new Exception("0 OR 0 FAILED");
        else System.out.println("0 OR 0 PASSED");
    }

    /**
     * Test the xor method for bit
     * @throws Exception if result is incorrect
     */
    public static void TestXor() throws Exception {
        if(new bit(1).xor(new bit(0)).getValue() != 1)
            throw new Exception("1 XOR 0 FAILED");
        else System.out.println("1 XOR 0 PASSED");

        if (new bit(1).xor(new bit(1)).getValue() != 0)
            throw new Exception("1 XOR 1 FAILED");
        else System.out.println("1 XOR 1 PASSED");

        if (new bit(0).xor(new bit(1)).getValue() != 1)
            throw new Exception("0 XOR 1 FAILED");
        else System.out.println("0 XOR 1 PASSED");

        if (new bit(0).xor(new bit(0)).getValue() != 0)
            throw new Exception("0 XOR 0 FAILED");
        else System.out.println("0 XOR 0 PASSED");
    }

    /**
     * Test the not method for bit
     * @throws Exception if result is incorrect
     */
    public static void TestNot() throws Exception {
        if(new bit(1).not().getValue() != 0)
            throw new Exception("NOT 1 FAILED");
        else System.out.println("NOT 1 PASSED");

        if(new bit(0).not().getValue() != 1)
            throw new Exception("NOT 0 FAILED");
        else System.out.println("NOT 0 PASSED");
    }

    /**
     * Test the toggle method for bit
     * @param bit for testing
     * @throws Exception if result is incorrect
     */
    public static void TestToggle(bit bit) throws Exception {
        switch(bit.getValue()) {
            case 1:
                bit.toggle();
                if(bit.getValue() != 0)
                    throw new Exception("toggle() for 1 FAILED");
                else System.out.println("toggle() for 1 PASSED");
                break;
            case 0:
                bit.toggle();
                if(bit.getValue() != 1)
                    throw new Exception("toggle() for 0 FAILED");
                else System.out.println("toggle() for 0 PASSED");
                break;
                default:
                    throw new Exception("toggle() FAILED: Invalid Value");
        }
    }

    /**
     * Test the set(int) method for bit
     * @param bit for testing
     * @param i value to set
     * @throws Exception Exception if result is incorrect
     */
    public static void TestSet(bit bit, int i) throws Exception {
        switch(i){
            case 1:
                bit.set(1);
                if(bit.getValue() != 1)
                    throw new Exception("set(1) FAILED");
                else System.out.println("set(1) PASSED");
             break;
            case 0:
                bit.set(0);
                if(bit.getValue() != 0)
                    throw new Exception("set(0) FAILED");
                else System.out.println("set(0) PASSED");
                break;
            default:
                throw new Exception("set(" + i + ") Failed. Invalid Value");
        }
    }
    public static void TestSet(bit bit) throws Exception {
        switch(bit.getValue()){
            case 1:
            case 0:
                bit.set();
                if(bit.getValue() != 1)
                    throw new Exception("set() for "+ bit.getValue() +" FAILED");
                else System.out.println("set() for "+ bit.getValue() +" PASSED");
                break;
            default:
                throw new Exception("set() Failed. Invalid Value");
        }
    }

    public static void TestClear(bit bit) throws Exception {
        switch(bit.getValue()){
            case 1:
            case 0:
                bit.clear();
                if(bit.getValue() != 0)
                    throw new Exception("clear() for "+ bit.getValue() +" FAILED");
                else System.out.println("clear() for "+ bit.getValue() +" PASSED");
                break;
            default:
                throw new Exception("clear() Failed. Invalid Value");
        }
    }

    public static void TestGetValue(bit bit) throws Exception {
        switch(bit.getValue()){
            case 0:
                if (bit.getValue() != 0)
                    throw new Exception("getValue() for 0 FAILED");
                else System.out.println("getValue() for 0 PASSED");
                break;
            case 1:
                if (bit.getValue() != 1)
                    throw new Exception("getValue() for 1 FAILED");
                else System.out.println("getValue() for 1 PASSED");
                break;
            default:
                throw new Exception("getValue() Failed. Invalid Value");
        }
    }

    public static void TestToString(bit bit) throws Exception {
        switch(bit.getValue()){
            case 0:
                if(bit.toString().equals(String.valueOf(0)))
                    System.out.println("toString() for 0 PASSED");
                else throw new Exception("toString() for 0 FAILED");
                break;
            case 1:
                if(bit.toString().equals(String.valueOf(1)))
                    System.out.println("toString() for 1 PASSED");
                else throw new Exception("toString() for 1 FAILED");
                break;
            default:
                throw new Exception("toString() Failed. Invalid Value");
        }
    }
}
