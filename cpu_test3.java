import java.util.Arrays;

public class cpu_test3 {
    public static void main(String[] args) throws Exception {
        bit_test.runTests();
        longword_test.runTests();
        rippleAdder_test.runTests();
        multiplier_test.runTests();
        ALU_test.runTests();
        memory_test.runTests();
        cpu_test1.runTests();
        assembler_test.runTests();
        cpu_test2.runTests();
        runTests();
    }

    public static void runTests() {
        System.out.println("---------------------CPU TEST CASES 3------------------");

        testCPU(new String[]{"jump 16", //address 0
                "pop R15", //address 2
                "pop R14", //address 4
                "pop R13", //address 6
                "subtract R14 R13 R14", //address 8
                "push R14", //address 10
                "push R15", //address 12
                "return", //address 14
                "move R0 5",//address 16
                "move R1 7", //address 18
                "push R0", //address 20
                "push R1", //address 22
                "call 36", //address 24
                "push R0", //address 26
                "call 2", //address 28
                "pop R2", //address 30
                "interrupt 0", //address 32
                "halt", //address 34
                "pop R15", //address 36
                "pop R14", //address 38
                "pop R13", //address 40
                "subtract R14 R13 R14", //address 42
                "push R14", //address 44
                "push R15", //address 46
                "return"//address 48
        });
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
