import java.util.ArrayList;
import java.util.Collections;

public class memory {
    final int length = 8192; //= can hold up to 256 longwords
    public ArrayList<bit> state;

    /**
     * You must hard code the amount of memory our computer will have – 1024 bytes. The memory must be constructed from our existing bit class.
     */
    public memory(){
        state = new ArrayList<>(Collections.nCopies(length, new bit(0))); //memory amount 1024 bytes, state is set to 0 by default
    }

    /**
     * Read from memory
     * @param address a number
     * @return longword
     */
    public longword read(longword address) {
        int index = address.getSigned() * 8;
        longword result = new longword();

        for (int j = 0, i = index + j; i < (index + 32); i++, j++) { //get next 32 bits
            result.setBit(j, state.get(i));
        }
        return result;
    }

    /**
     * Write to memory
     * @param address number
     * @param value number to set
     */
    public void write(longword address, longword value){
        int index = address.getSigned() * 8;
        //write to memory
        for (int j = 0, i = index + j; j < 32; i++,j++)
            state.set(i, value.getBit(j));
    }

    @Override
    public String toString() {
        int group_count = 1;
        StringBuilder output = new StringBuilder("\n");
        for (int i = 0; i < length; i++){
            output.append(state.get(i));
            if (group_count % 4 == 0) {
                output.append(" ");
            }
            if (group_count % 16 == 0)
                output.append("\n");
            group_count++;
        }
        return String.valueOf(output);
    }

    /**
     * Helper method to print memory in stack format: Just the last 20 bytes (limit can always be changed by modifying n in length - n) to avoid long outputs
     * @return memory in stack format
     */
    public String toStack(){
        StringBuilder output = new StringBuilder("...\n");
        int group_count = 1;

        for (int i = length-320; i < length; i++) {
            output.append(state.get(i));
            if (group_count % 4 == 0) {
                output.append(" ");
            }
            if (group_count % 32 == 0)
                output.append("\n");
            group_count++;
        }
        return String.valueOf(output);
    }
}
