public class assembler_test {
    public static void main(String[] args) throws Exception {
        bit_test.runTests();
        longword_test.runTests();
        rippleAdder_test.runTests();
        multiplier_test.runTests();
        ALU_test.runTests();
        memory_test.runTests();
        cpu_test1.runTests();
        runTests();
    }
    public static void runTests(){
        System.out.println("-------------------ASSEMBLER TEST CASES------------------");

        testAssembler(new String[]{"move R1 -1","interrupt 1","interrupt 0","halt","and R1 R2 R3","or R1 R2 R3","xor R1 R2 R3", "not R1 R2", "leftShift R1 R2 R3", "rightShift R1 R2 R3", "add R1 R2 R3", "subtract R1 R2 R3", "multiply R1 R2 R3"});
        System.out.println("**************************");
        testAssembler(new String[]{"move R15 -128","interrupt 1","interrupt 0","halt","and R5 R10 R4","or R2 R8 R0","xor R7 R5 R3", "not R9 R3", "leftShift R4 R5 R6", "rightShift R14 R12 R13", "add R11 R10 R2", "subtract R4 R5 R3", "multiply R2 R4 R3"});
        System.out.println("**************************");
        testAssembler(new String[]{"move R0 6","interrupt 1","interrupt 0","halt","and R0 R1 R2","or R3 R4 R5","xor R6 R7 R8", "not R9 R10", "leftShift R11 R12 R13", "rightShift R14 R15 R0", "add R1 R2 R3", "subtract R1 R2 R3", "multiply R4 R5 R6"});
        System.out.println("**************************");
        testAssembler(new String[]{"move R0 127","interrupt 1","interrupt 0","halt","and R15 R14 R13","or R12 R11 R10","xor R9 R8 R7", "not R6 R5", "leftShift R4 R3 R2", "rightShift R1 R0 R15", "add R14 R13 R12", "subtract R11 R10 R9", "multiply R8 R7 R6"});
    }
    public static void testAssembler(String[] language){
        System.out.println("Assembly language:");
        for (String s : language) {
            System.out.println(s);
        }
        System.out.println("Bit Patterns: ");
        String[] array = Assembler.assemble(language);
        for (String s : array) {
            System.out.println(s);
        }
    }
}
