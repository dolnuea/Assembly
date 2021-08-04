public class cpu_test1 {
    public static void main(String[] args) throws Exception {
        bit_test.runTests();
        longword_test.runTests();
        rippleAdder_test.runTests();
        multiplier_test.runTests();
        ALU_test.runTests();
        memory_test.runTests();
        runTests();
    }

    /**
     * Creates a new CPU for each test.
     * use the ALU, print out the results using interrupt 0 and halt. Confirm using interrupt 1 that the program is stored correctly in memory.
     */
    public static void runTests(){
        System.out.println("---------------------CPU TEST CASES 1------------------");

        testCPU(new String[]{"0001 0010 0000 1001","1110 0001 0010 0011", "0010 0000 0000 0000", "0010 0000 0000 0001", "0000 0000 0000 0000"});
        testCPU(new String[]{"0001 0100 1111 1111", "0001 0001 1111 1100", "0111 0100 0001 1000", "0010 0000 0000 0000", "0010 0000 0000 0001", "0000 0000 0000 0000"});
        testCPU(new String[]{"0001 1000 0000 1101","0001 0110 111 1010","1111 1000 0110 1110", "0010 0000 0000 0000", "0010 0000 0000 0001", "0000 0000 0000 0000"});
        testCPU(new String[]{"0001 1100 1100 0000","0001 0011 111 1010","0001 1111 0011 1111","0001 0101 0000 0010","1111 1100 0011 1110","0111 1111 0101 0001", "0010 0000 0000 0000", "0010 0000 0000 0001", "0000 0000 0000 0000"});
    }

    /**
     * Helper for runTests()
     * @param instruction passed as a string array
     */
    public static void testCPU(String[] instruction){
        computer cpu = new computer();
        cpu.preload(instruction);
        cpu.run();
        System.out.println("**************************************");
    }
}
