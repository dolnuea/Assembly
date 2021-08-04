import java.util.Arrays;
public class computer {

    private memory memory; //programs are stored in memory
    private bit halt; //determines if computer is on/off
    private longword PC, currentInstruction, op1, op2, result, op3, SP; //program counter (the tracker that knows where to load the next part of the program from); instruction (16 bits); operand 1; operand 2
    private bit[] opcode, conditioncode;
    private longword[] registers; //local variables
    private bit[] comparison_bits = new bit[2];

    /**
     * Default constructor
     */
    public computer(){
        this.memory = new memory();
        halt = new bit(0); //1 is on, 0 is off. Set to off by default
        PC = new longword(0); //program counter set to 0 by default
        registers = new longword[16];
        //Comparison bits by default is [0,0]
        comparison_bits[0] = new bit(0);
        comparison_bits[1] = new bit(0);
        for(int i = 0; i < 16; i++){
            registers[i] = new longword(0);
        }
        SP = new longword(1020); //indicates the NEXT place to write the stack
    }

    /**
     * Runs in a while loop until the halted bit (0) indicates that the computer should not run anymore.
     * Within this loop, four methods needs are called, respectively:
     * void fetch(), void decode(), void execute(), void store().
     */
    void run(){
        halt.set(1);
        while(halt.getValue() != 0){ //loop until the halted bit indicates that the computer should not run anymore
            fetch();
            decode();
            execute();
            store();
        }
    }

    /**
     * Reads the next longword from memory that the PC points to. Fetches instructions
     */
    void fetch(){
        //read the next longword from memory that the PC points to
        currentInstruction = memory.read(PC);
        System.out.println("Address: " + PC.getSigned());
        PC = rippleAdder.add(PC, new longword(2)); //increment by two
    }

    /**
     * Decodes instructions read by the memory
     */
    void decode(){ //XXXX XXXX XXXX XXXX
        opcode = new bit[]{currentInstruction.getBit(0),currentInstruction.getBit(1),currentInstruction.getBit(2), currentInstruction.getBit(3)};
        conditioncode = new bit[]{currentInstruction.getBit(4), currentInstruction.getBit(5)}; //get condition code on branch instructions
        op3 = currentInstruction.rightShift(16).and(new longword(15)); //used as interrupt type
        op1 = registers[currentInstruction.rightShift(24).and(new longword(15)).getSigned()];
        op2 = registers[currentInstruction.rightShift(20).and(new longword(15)).getSigned()];
    }

    /**
     * Helper method to check opcode for halt
     * @param opcode to be compared
     * @return true/false
     */
    boolean check_halt(bit[] opcode){
        if(opcode[0].getValue() == 0)
            if(opcode[1].getValue() == 0)
                if(opcode[2].getValue() == 0)
                    return opcode[3].getValue() == 0;
        return false;
    }
    /**
     * Helper method to check opcode for move
     * @param opcode to be compared
     * @return true/false
     */
    boolean check_move(bit[] opcode){
        if(opcode[0].getValue() == 0)
            if(opcode[1].getValue() == 0)
                if(opcode[2].getValue() == 0)
                    return opcode[3].getValue() == 1;
        return false;
    }
    /**
     * Helper method to check opcode for interrupt
     * @param opcode to be compared
     * @return true/false
     */
    boolean check_interrupt(bit[] opcode){
        if(opcode[0].getValue() == 0)
            if(opcode[1].getValue() == 0)
                if(opcode[2].getValue() == 1)
                    return opcode[3].getValue() == 0;
        return false;
    }
    /**
     * Helper method to check opcode for jump
     * @param opcode to be compared
     * @return true/false
     */
    boolean check_jump(bit[] opcode){
        if(opcode[0].getValue() == 0)
            if(opcode[1].getValue() == 0)
                if(opcode[2].getValue() == 1)
                    return opcode[3].getValue() == 1;
        return false;
    }
    /**
     * Helper method to check opcode for compare
     * @param opcode to be compared
     * @return true/false
     */
    boolean check_compare(bit[] opcode){
        if(opcode[0].getValue() == 0)
            if(opcode[1].getValue() == 1)
                if(opcode[2].getValue() == 0)
                    return opcode[3].getValue() == 0;
        return false;
    }
    /**
     * Helper method to check opcode for branch
     * @param opcode to be compared
     * @return true/false
     */
    boolean checkBranch(bit[] opcode){
        if(opcode[0].getValue() == 0)
            if(opcode[1].getValue() == 1)
                if(opcode[2].getValue() == 0)
                    return opcode[3].getValue() == 1;
        return false;
    }

    /**
     * Helper method to check opcode for push
     * @param opcode to be compared
     * @return true/false
     */
    boolean checkPush(bit[] opcode){
        if(opcode[0].getValue() == 0)
            if(opcode[1].getValue() == 1)
                if(opcode[2].getValue() == 1)
                    if (opcode[3].getValue() == 0)
                        if (conditioncode[0].getValue() == 0)
                            return conditioncode[1].getValue() == 0;
        return false;
    }

    /**
     * Helper method to check opcode for pop
     * @param opcode to be compared
     * @return true/false
     */
    boolean checkPop(bit[] opcode){
        if(opcode[0].getValue() == 0)
            if(opcode[1].getValue() == 1)
                if(opcode[2].getValue() == 1)
                    if (opcode[3].getValue() == 0)
                        if (conditioncode[0].getValue() == 0)
                            return conditioncode[1].getValue() == 1;
        return false;
    }

    /**
     * Helper method to check opcode for call
     * Pushes the address of the NEXT instruction after the call onto the stack
     * @param opcode to be compared
     * @return true/false
     */
    boolean checkCall(bit[] opcode){
        if(opcode[0].getValue() == 0)
            if(opcode[1].getValue() == 1)
                if(opcode[2].getValue() == 1)
                    if (opcode[3].getValue() == 0)
                        if (conditioncode[0].getValue() == 1)
                            return conditioncode[1].getValue() == 0;

        return false;
    }

    /**
     * Helper method to check opcode for return
     * @param opcode to be compared
     * @return true/false
     */
    boolean checkReturn(bit[] opcode){
        if(opcode[0].getValue() == 0)
            if(opcode[1].getValue() == 1)
                if(opcode[2].getValue() == 1)
                    if (opcode[3].getValue() == 0)
                        if (conditioncode[0].getValue() == 1)
                            return conditioncode[1].getValue() == 1;
        return false;
    }

    /**
     * Executes instructions in the CPU
     */
    void execute() {
        if (check_halt(opcode)) { //halt
            System.out.println("Halt");
            halt.set(0); //alter halt bit
        }
        //move - does nothing
        else if(check_move(opcode))
            return;

        else if (check_interrupt(opcode)) { //interrupt
            System.out.println("Interrupt type " + op3.getSigned());

            //Interrupt Types 1/0
            if (op3.getSigned() == 0){
                System.out.println("Registers " + beautifyOutput());
            }
            else if (op3.getSigned() == 1){
                System.out.println("Memory " + memory);
            }
        }
        else if(check_jump(opcode)) //jump - does nothing: sets program counter in store()
            return;
         /*
          BranchIfEqual = 01
          BranchIfNotEqual = 00
          BranchIfGreaterThan = 10
          BranchIfGreaterThanOrEqual = 11
          */
        else if (check_compare(opcode)){ //0100 0000 xxxx yyyy
            int Rx = currentInstruction.rightShift(20).and(new longword(15)).getSigned();
            int Ry = currentInstruction.rightShift(16).and(new longword(15)).getSigned();
            System.out.println("Comparing R" + Rx + " and R" + Ry);
            longword difference = rippleAdder.subtract(registers[Rx],registers[Ry]);
            if(difference.getSigned() == 0){ //equal
                comparison_bits[0].clear();
                comparison_bits[1].set(1);
            }
            else if (difference.getSigned() > 1){ //greater than
                comparison_bits[0].set(1);
                comparison_bits[1].clear();
            }
            else { //not equal
                comparison_bits[0].clear();
                comparison_bits[1].clear();
            }
        }
        else if(checkBranch(opcode)){ //branch - does nothing
            return;
        }
        else if(checkPush(opcode)){
            return;
        }
        else if(checkPop(opcode)){
            return;
        }
        else if(checkCall(opcode)){
            return;
        }
        else if(checkReturn(opcode)){
            return;
        }
        else {
            System.out.println("ALU Operation of R" + currentInstruction.rightShift(24).and(new longword(15)).getSigned() + " and R" + currentInstruction.rightShift(20).and(new longword(15)).getSigned() + " stored in R" + op3.getSigned());
            result = ALU.doOp(new bit[]{currentInstruction.getBit(0), currentInstruction.getBit(1), currentInstruction.getBit(2), currentInstruction.getBit(3)}, op1, op2);
        }
    }

    /**
     * Stores values in the CPU
     */
    void store(){
        if (halt.getValue() == 0) //halt - does nothing
            return;
        else if (check_move(opcode)) {
            int value = signBitExtensionHelper(currentInstruction.rightShift(16).and(new longword(255))).getSigned();
            int index = currentInstruction.rightShift(24).and(new longword(15)).getSigned();
            System.out.println("Moving " + value + " to R" + index);
            registers[index].set(value); // 1111 1111 MASK OF 8 BITS
        }
        else if (check_interrupt(opcode)) //interrupt - does nothing
            return;
        else if (check_jump(opcode)){
            longword address = currentInstruction.rightShift(16).and(new longword(4095));
            System.out.print("Jumping from address " + PC.getSigned()); //printing procedure to follow easier
            PC.copy(address);
            System.out.println(". Jumped to address " + PC.getSigned());
        }
        else if(check_compare(opcode)) //compare - does nothing
            return;
        else if(checkBranch(opcode)) { //Branch checks to see if comparison bits from the compare instruction and condition bits from branch instruction matches
            if (comparison_bits[0].getValue() == conditioncode[0].getValue() && comparison_bits[1].getValue() == conditioncode[1].getValue()) {
                PC = rippleAdder.subtract(PC, new longword(2)); //because we increment by 2 everytime
                System.out.print("Branch " + Arrays.toString(conditioncode) + ": Jumping from address " + PC.getSigned()); //printing procedure
                PC = rippleAdder.add(PC, signBitExtensionHelper(currentInstruction.rightShift(16).and(new longword(1023))));
                System.out.println(". Jumped to address " + PC.getSigned());
            }
            //BranchIfGreaterThanOrEqual verifies the condition bits first then performs a jump if comparison is either "equal" or "greater than"
            else if ((conditioncode[0].and(conditioncode[1]).getValue() == 1) && (comparison_bits[0].getValue() == 1 || comparison_bits[1].getValue() == 1)) {
                PC = rippleAdder.subtract(PC, new longword(2)); //because we increment by 2 everytime
                System.out.print("Branch " + Arrays.toString(conditioncode) + ": Jumping from address " + PC.getSigned());
                PC = rippleAdder.add(PC, signBitExtensionHelper(currentInstruction.rightShift(16).and(new longword(1023))));
                System.out.println(". Jumped to address " + PC.getSigned());
            }
            else return; //do nothing
        }
        else if(checkPush(opcode)){
            System.out.println("Pushing R" + currentInstruction.rightShift(16).and(new longword(15)).getSigned() + " into stack.");
            System.out.println("SP: " + SP.getSigned());
            memory.write(SP, registers[currentInstruction.rightShift(16).and(new longword(15)).getSigned()]); //push Rx into stack
            SP.copy(rippleAdder.subtract(SP, new longword(4))); //decrease capacity in stack
            System.out.println("Stack:\n" + memory.toStack());
        }
        else if(checkPop(opcode)){
            System.out.println("Popping R" + currentInstruction.rightShift(16).and(new longword(15)).getSigned() + " from stack.");
            SP.copy(rippleAdder.add(SP, new longword(4))); //open up space in stack
            System.out.println("SP: " + SP.getSigned());
            registers[currentInstruction.rightShift(16).and(new longword(15)).getSigned()].copy(memory.read(SP)); //copy value into specified register
            memory.write(SP, new longword(0)); //pop from stack
            System.out.println("Stack:\n" + memory.toStack());
        }
        else if(checkCall(opcode)){
            memory.write(SP, PC); //push PC into stack
            SP = rippleAdder.subtract(SP, new longword(4));
            longword address = currentInstruction.rightShift(16).and(new longword(1023)); //get address in call
            System.out.println("Calling from address " + address.getSigned());
            PC.copy(address); //jump to specified address
        }
        else if(checkReturn(opcode)){
            SP.copy(rippleAdder.add(SP, new longword(4))); //open up space for pop
            longword RN = memory.read(SP); //read return value at SP
            System.out.println("Returning " + RN.getSigned());
            PC.copy(RN); //jump to return address
            memory.write(SP, new longword(0)); //pop RN from stack
        }
        else {
            registers[currentInstruction.rightShift(16).and(new longword(15)).getSigned()].set(result.getSigned());
        }
    }

    /**
     * Helper to extend sign
     * @param value to be extended
     * @return the sign extended value
     */
    longword signBitExtensionHelper(longword value){
        if (check_move(opcode) && value.getBit(24).getValue() == 1){
            for(int i = 24; i >=  0; i--)
                value.setBit(i, new bit(1));
        }
        else if(checkBranch(opcode) && value.getBit(22).getValue()==1)
            for(int i = 22; i >=  0; i--)
                value.setBit(i, new bit(1));
        return value;
    }
    /**
     * Helper method for printing type 0 interrupts
     * @return organized output for registers
     */
    String beautifyOutput(){
        StringBuilder output = new StringBuilder("\n");
        int i = 0;
        for (longword register : registers) {
            output.append("R").append(i).append(": ").append(register.getSigned()).append("\n");
            i++;
        }
        return String.valueOf(output);
    }

    /**
     * Preload loads bunch of instructions for the CPU to handle
     * @param instr list of instructions to be loaded before running the computer
     */
    void preload(String[] instr) {
        int length = instr.length, count = 1; //for pairs
        longword[] holder = new longword[(length/2) + 1]; //stores pairs of instruction (i.e. 5 instructions = 2 paired, 1 single instruction mean 3 longwords
        Arrays.fill(holder, new longword());
        longword address = new longword(0);

        for (int i = 0, j = 0, l = 0; i < length; i++) { //where indices belong to: i = instr, j = holder, k = instr[i], l = holder[j]

            instr[i] = instr[i].replaceAll(" ", ""); //remove spaces

            if (length % 2 != 0 && i == length - 1) { //case for the last odd numbered instruction
                for (int k = 0; l < 32; k++, l++) {
                    if (k < instr[i].length())
                        holder[j].setBit(l, new bit(Character.getNumericValue(instr[i].charAt(k)))); //16 bits assigned
                    else holder[j].setBit(l, new bit(0)); //pad the last write with all 0’s.
                }
                memory.write(address, holder[j]);
            } else { //for pairs

                for (int k = 0; k < instr[i].length(); k++, l++) {
                    holder[j].setBit(l, new bit(Character.getNumericValue(instr[i].charAt(k)))); //16 bits assigned to holder for each instruction
                }

                if (count % 2 == 0) {
                    memory.write(address, holder[j]); //each pair's address
                    address = rippleAdder.add(address, new longword(4)); //increment address by 4 bytes (each instruction is 2 bytes)
                    j++; //if a pair is stored increment holder, go to next holder
                    l = 0; //reset the index for the next holder
                }
                count++;
            }
        }
    }
}
