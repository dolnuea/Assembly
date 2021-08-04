import java.util.Arrays;

public class cpu_test2 {
    public static void main(String[] args) throws Exception {
        bit_test.runTests();
        longword_test.runTests();
        rippleAdder_test.runTests();
        multiplier_test.runTests();
        ALU_test.runTests();
        memory_test.runTests();
        cpu_test1.runTests();
        assembler_test.runTests();
        runTests();
    }

    public static void runTests() {
        System.out.println("---------------------CPU TEST CASES 2------------------");

        testCPU(new String[] {"move R0 5", "move R1 7", "compare R0 R1", "jump 12", "move R15 4", "jump 26", "branchIfNotEqual 6", "branchIfGreaterThan -6", "branchIfEqual -6", "branchIfGreaterThanOrEqual 6", "move R15 2", "jump 26", "move R15 8", "interrupt 0", "halt"});
        testCPU(new String[] {"move R0 5", "move R1 5", "compare R0 R1", "jump 12", "move R15 4", "jump 26", "branchIfNotEqual 6", "branchIfGreaterThan -6", "branchIfEqual -6", "branchIfGreaterThanOrEqual 6", "move R15 2", "jump 24", "move R15 8", "interrupt 0", "halt"});
        testCPU(new String[] {"move R0 10", "move R1 5", "compare R0 R1", "jump 12", "move R15 4", "jump 26", "branchIfNotEqual 6", "branchIfGreaterThan -6", "branchIfEqual -6", "branchIfGreaterThanOrEqual 6", "move R15 2", "jump 24", "move R15 8", "interrupt 0", "halt"});
        testCPU(new String[] {"move R0 -4", "move R1 -6", "compare R0 R1", "jump 12", "move R15 4", "jump 26", "branchIfNotEqual 6", "branchIfGreaterThan -6", "branchIfEqual -6", "branchIfGreaterThanOrEqual 6", "move R15 2", "jump 24", "move R15 8", "interrupt 0", "halt"});
        testCPU(new String[] {"move R0 -5", "move R1 4", "compare R0 R1", "jump 12", "move R15 4", "jump 26", "branchIfNotEqual 6", "branchIfGreaterThan -6", "branchIfEqual -6", "branchIfGreaterThanOrEqual 6", "move R15 2", "jump 24", "move R15 8", "interrupt 0", "halt"});
        testCPU(new String[] {"move R0 6", "move R1 -5", "compare R0 R1", "jump 12", "move R15 4", "jump 26", "branchIfNotEqual 6", "branchIfGreaterThan -6", "branchIfEqual -6", "branchIfGreaterThanOrEqual 6", "move R15 2", "jump 24", "move R15 8", "interrupt 0", "halt"});
        testCPU(new String[] {"move R0 10", "move R1 10", "compare R0 R1", "branchIfGreaterThanOrEqual 6", "move R15 2", "jump 24", "move R15 8", "interrupt 0", "halt"});

    }

    /**
     * Helper for runTests()
     * @param instruction passed as a string array
     */
    public static void testCPU(String[] instruction){
        String[] assembledCode = Assembler.assemble(instruction);
        System.out.println("Instructions: " + Arrays.toString(instruction));
        computer cpu = new computer();
        cpu.preload(assembledCode);
        cpu.run();
        System.out.println("**************************************");
    }
}
