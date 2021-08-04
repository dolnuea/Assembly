public class Assembler {

    /**
     * Assembler uses lexical analysis to separate incoming text
     *
     * @param instruction series of strings, like “move R1 -1”
     * @return the bit patterns that are represented by that command "0001 0001 1111 1111"
     */
    public static String[] assemble(String[] instruction) {
        String[] output = new String[instruction.length];

        for (int i = 0; i < instruction.length; i++) {
            String[] tokens = instruction[i].split(" "); //Lexing using split method for each instruction
            output[i] = parser(tokens); //Parse each passed instruction one by one
        }
        return output;
    }

    /**
     * Parses incoming text
     *
     * @param tokens of assembly language
     * @return string
     */
    private static String parser(String[] tokens) {
        StringBuilder string = new StringBuilder("");

        switch (tokens[0]) {
            //0000 0000 0000 0000
            case "halt":
                string.append("0000 0000 0000 0000");
                break;
            //0001, op1, and 8 bit op2
            case "move":
                try {
                    if (Integer.parseInt(tokens[2]) < -128 || Integer.parseInt(tokens[2]) > 127) //handling 8-bit op
                        throw new Exception("Input is out of Bounds. Value range is [-128, 127]");
                    else string.append("0001 ").append(registerToBits(tokens[1])).append(" ").append(toBinary(8, tokens[2]));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            //0010 0000 0000 interrupt_type
            case "interrupt":
                try {
                    if (Integer.parseInt(tokens[1]) > 1) //handling interrupt types
                        throw new Exception("Input is out of Bounds. Interrupt Failed");
                    else string.append("0010 0000 0000 ").append(toBinary(4, tokens[1]));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "jump": //0011 0000 0000 1010 (12 bits of address val)
                try {
                    if (Integer.parseInt(tokens[1]) > 4095 || Integer.parseInt(tokens[1]) < 0)
                        throw new Exception("Input is out of Bounds. Jump Failed");
                    else string.append("0011 ").append(toBinary(12, tokens[1]));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "compare": //0100 0000 xxxx yyyy : compare Rx and Ry.
                try {
                    string.append("0100 0000 ").append(registerToBits(tokens[1])).append(" ").append(registerToBits(tokens[2]));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            //Branch instructions
            case "branchIfEqual":
                try {
                    if(Integer.parseInt(tokens[1]) < -512 || Integer.parseInt(tokens[1]) > 511)
                        throw new Exception("Input is out of Bounds. Value range is [-512, 511]");
                    else string.append("0101 01").append(toBinary(10, tokens[1])); //greater than equal
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "branchIfGreaterThan":
                try {
                    if(Integer.parseInt(tokens[1]) < -512 || Integer.parseInt(tokens[1]) > 511)
                        throw new Exception("Input is out of Bounds. Value range is [-512, 511]");
                    else string.append("0101 10").append(toBinary(10, tokens[1])); //less than or equal
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "branchIfGreaterThanOrEqual":
                try {
                    if(Integer.parseInt(tokens[1]) < -512 || Integer.parseInt(tokens[1]) > 511)
                        throw new Exception("Input is out of Bounds. Value range is [-512, 511]");
                    else string.append("0101 11").append(toBinary(10, tokens[1])); //greater than
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "branchIfNotEqual":
                try {
                    if(Integer.parseInt(tokens[1]) < -512 || Integer.parseInt(tokens[1]) > 511)
                    throw new Exception("Input is out of Bounds. Value range is [-512, 511]");
                else string.append("0101 00").append(toBinary(10, tokens[1]));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "push":
                try {
                    string.append("0110 0000 0000 ").append(registerToBits(tokens[1]));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "pop":
                try {
                    string.append("0110 0100 0000 ").append(registerToBits(tokens[1]));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "call":
                try {
                    if(Integer.parseInt(tokens[1]) < 0 || Integer.parseInt(tokens[1]) > 1023)
                        throw new Exception("Input is out of Bounds. Value range is [0,1023]");
                    else string.append("0110 10").append(toBinary(10, tokens[1]));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "return":
                string.append("0110 1100 0000 0000");
                break;
            //ALU op1 op2 op3 - 4 tokens total except for not
            case "and":
                try {
                    string.append("1000 ").append(registerToBits(tokens[1])).append(" ").append(registerToBits(tokens[2])).append(" ").append(registerToBits(tokens[3]));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "or":
                try {
                    string.append("1001 ").append(registerToBits(tokens[1])).append(" ").append(registerToBits(tokens[2])).append(" ").append(registerToBits(tokens[3]));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "xor":
                try {
                    string.append("1010 ").append(registerToBits(tokens[1])).append(" ").append(registerToBits(tokens[2])).append(" ").append(registerToBits(tokens[3]));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "not": //not R1 R2 - 3 tokens
                try {
                    string.append("1011 ").append(registerToBits(tokens[1])).append(" 0000 ").append(registerToBits(tokens[2])); //XXXX XXXX 0000 XXXX
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "leftShift":
                try {
                    string.append("1100 ").append(registerToBits(tokens[1])).append(" ").append(registerToBits(tokens[2])).append(" ").append(registerToBits(tokens[3]));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "rightShift":
                try {
                    string.append("1101 ").append(registerToBits(tokens[1])).append(" ").append(registerToBits(tokens[2])).append(" ").append(registerToBits(tokens[3]));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "add":
                try {
                    string.append("1110 ").append(registerToBits(tokens[1])).append(" ").append(registerToBits(tokens[2])).append(" ").append(registerToBits(tokens[3]));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "subtract":
                try {
                    string.append("1111 ").append(registerToBits(tokens[1])).append(" ").append(registerToBits(tokens[2])).append(" ").append(registerToBits(tokens[3]));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "multiply":
                try {
                    string.append("0111 ").append(registerToBits(tokens[1])).append(" ").append(registerToBits(tokens[2])).append(" ").append(registerToBits(tokens[3]));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
        return String.valueOf(string);
    }

    /**
     * Helper to convert registers to bit patterns
     *
     * @param op to convert to bit terms
     * @return op in binary representations
     */
    private static String registerToBits(String op) throws Exception {
        switch (op) {
            case "R0":
                return "0000";

            case "R1":
                return "0001";

            case "R2":
                return "0010";

            case "R3":
                return "0011";

            case "R4":
                return "0100";

            case "R5":
                return "0101";

            case "R6":
                return "0110";

            case "R7":
                return "0111";

            case "R8":
                return "1000";

            case "R9":
                return "1001";

            case "R10":
                return "1010";

            case "R11":
                return "1011";

            case "R12":
                return "1100";

            case "R13":
                return "1101";

            case "R14":
                return "1110";

            case "R15":
                return "1111";

            default:
                throw new Exception("Input is out of Bounds. Register capacity is [0,15]");
        }
    }

    /**
     * Helper function: Converts an integer value to binary according to appropriate opcodes
     *
     * @param op value to convert
     * @return n-bit converted binary
     * @throws Exception when value is out of bounds
     */
    private static String toBinary(int n, String op) throws Exception {
        longword binary = new longword(Integer.parseInt(op));
        StringBuilder output = new StringBuilder("");
        switch (n) {
            case 8: //move
            case 12://jump
            case 10://branch and call
                for (int i = 0; i < n; i++) {
                        if (i % 4 == 0)
                            output.append(" ");
                        output.append(binary.getBit(31 - i));
                }
                break;
            case 4: //interrupt
                    for (int i = 0; i < n; i++)
                        output.append(binary.getBit(31 - i));
                break;
        }
        return output.reverse().toString(); //reversing the output since the nature of append concatenates strings left to right, while we want right to left
    }
}
