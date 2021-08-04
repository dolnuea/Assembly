public class ALU_test {
    public static void main(String[] args) throws Exception {
        bit_test.runTests();
        longword_test.runTests();
        rippleAdder_test.runTests();
        multiplier_test.runTests();
        runTests();
    }

    /**
     * Run All Tests for ALU
     */
    public static void runTests(){
        System.out.println("---------------------ALU TEST CASES------------------");
        runALU(3, 2);
        runALU(-3, 2);
        runALU(3, -2);
        runALU(-3, -2);
        runALU(-3, 0);
        runALU(0, -2);
        runALU(6, 5);
        runALU(6, 55);
        runALU(55, 31);
    }

    /**
     * Run ALU TESTS
     * @param a longword value
     * @param b a longword value
     */
    public static void runALU(int a, int b) {
        test1000(new longword(a),new longword(b));
        test1001(new longword(a),new longword(b));
        test1010(new longword(a),new longword(b));
        test1011(new longword(a),new longword(b));
        test1100(new longword(a),new longword(b));
        test1101(new longword(a),new longword(b));
        test1110(new longword(a),new longword(b));
        test1111(new longword(a),new longword(b));
        test0111(new longword(a),new longword(b));
        System.out.println("***********************************************************************************");
    }

    /**
     * Demonstrates Operation 1000 - and
     * @param a longword
     * @param b longword
     */
    public static void test1000(longword a, longword b) {
        System.out.println("Operation 1000 - and:\n" + a.getSigned() + " AND " + b.getSigned() + " = " + ALU.doOp(new bit[]{new bit(1), new bit(0), new bit(0),new bit(0)}, a, b).getSigned() + "\n" + a + "\nAND" + b + "\n= " + ALU.doOp(new bit[]{new bit(1), new bit(0), new bit(0),new bit(0)}, a, b));
    }
    /**
     * Demonstrates Operation 1001 - or
     * @param a longword
     * @param b longword
     */
    public static void test1001(longword a, longword b){
        System.out.println("Operation 1001 - or:\n"  + a.getSigned() + " OR " + b.getSigned() + " = " + ALU.doOp(new bit[]{new bit(1), new bit(0), new bit(0),new bit(1)}, a, b).getSigned()+ "\n" + a + "\nOR " + b + "\n= " + ALU.doOp(new bit[]{new bit(1), new bit(0), new bit(0),new bit(1)}, a, b));
    }
    /**
     * Demonstrates Operation 1010 - xor
     * @param a longword
     * @param b longword
     */
    public static void test1010(longword a, longword b) {
        System.out.println("Operation 1010 - xor:\n" + a.getSigned() + " XOR " + b.getSigned() + " = " + ALU.doOp(new bit[]{new bit(1), new bit(0), new bit(1),new bit(0)}, a, b).getSigned()+ "\n" + a + "\nXOR " + b + "\n = " + ALU.doOp(new bit[]{new bit(1), new bit(0), new bit(1),new bit(0)}, a, b));
    }
    /**
     * Demonstrates Operation 1011 - not
     * @param a longword
     */
    public static void test1011(longword a, longword b){
        System.out.println("Operation 1011 - not:\n" + a.getSigned() + " NOT " + " = " + ALU.doOp(new bit[]{new bit(1), new bit(0), new bit(1),new bit(1)}, a, b).getSigned()+ "\n" + a + "\nNOT " + "\n= " + ALU.doOp(new bit[]{new bit(1), new bit(0), new bit(1),new bit(1)}, a, b));
    }
    /**
     * Demonstrates Operation 1100 - left shift
     * @param a longword
     * @param b positions to shift
     */
    public static void test1100(longword a, longword b){
        int a_val = a.getSigned();
        System.out.println("Operation 1100 - left shift:\n"  + a_val + " << " + b.getSigned() + " = " + ALU.doOp(new bit[]{new bit(1), new bit(1), new bit(0),new bit(0)}, a, b).getSigned()+ "\n" + new longword(a_val) + "\n<< " + new longword(b.getSigned()) + "\n= " + ALU.doOp(new bit[]{new bit(1), new bit(1), new bit(0),new bit(0)}, new longword(a_val), b));
    }
    /**
     * Demonstrates Operation 1100 - left shift
     * @param a longword
     * @param b positions to shift
     */
    public static void test1101(longword a, longword b){
        int a_val = a.getSigned();
        System.out.println("Operation 1101 - right shift:\n"  + a_val + " >> " + b.getSigned() + " = " + ALU.doOp(new bit[]{new bit(1), new bit(1), new bit(0),new bit(1)}, a, b).getSigned()+ "\n" + new longword(a_val) + "\n>> " + new longword(b.getSigned()) + "\n= " + ALU.doOp(new bit[]{new bit(1), new bit(1), new bit(0),new bit(0)}, new longword(a_val), b));
    }
    /**
     * Demonstrates Operation 1010 - add
     * @param a longword
     * @param b longword
     */
    public static void test1110(longword a, longword b){
        System.out.println("Operation 1110 - add:\n"  + a.getSigned() + " + " + b.getSigned() + " = " + ALU.doOp(new bit[]{new bit(1), new bit(1), new bit(1),new bit(0)}, a, b).getSigned()+ "\n" + a + "\n+ " + b + "\n= " + ALU.doOp(new bit[]{new bit(1), new bit(1), new bit(1),new bit(0)}, a, b));
    }
    /**
     * Demonstrates Operation 1010 - subtract
     * @param a longword
     * @param b longword
     */
    public static void test1111(longword a, longword b){
        System.out.println("Operation 1111 - subtract:\n"  + a.getSigned() + " - " + b.getSigned() + " = " + ALU.doOp(new bit[]{new bit(1), new bit(1), new bit(1),new bit(1)}, a, b).getSigned() + "\n" + a + "\n- " + b + "\n = " + ALU.doOp(new bit[]{new bit(1), new bit(1), new bit(1),new bit(1)}, a, b));
    }
    /**
     * Demonstrates Operation 1010 - multiply
     * @param a longword
     * @param b longword
     */
    public static void test0111(longword a, longword b){
        System.out.println("Operation 0111 - multiply:\n"  + a.getSigned() + " x " + b.getSigned() + " = " + ALU.doOp(new bit[]{new bit(0), new bit(1), new bit(1),new bit(1)}, a, b).getSigned()+ "\n" + a + "\nx " + b + "\n = " + ALU.doOp(new bit[]{new bit(0), new bit(1), new bit(1),new bit(1)}, a, b));
    }
}
