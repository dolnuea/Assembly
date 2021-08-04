import java.util.ArrayList;
import java.util.Collections;
/**
 * @author Luna Dagci
 * Implementation of longword
 */
public class longword implements ILongword {
    private final int length = 32;
    private ArrayList<bit> longword_bits = new ArrayList<>(Collections.nCopies(length, new bit(0)));


    /**
     * Default Constructor
     */
    longword(){
    }

    /**
     * Constructor that can create an longword object with the given integer value
     * @param val of longword
     */
    longword(int val){
        set(val);
    }
    longword(ArrayList<bit> longword_bits){
        this.longword_bits = longword_bits;
    }

    /**
     * Get bit i
     * @param i position
     * @return bit
     */
    @Override
    public bit getBit(int i) {
        return longword_bits.get(i);
    }

    /**
     * set bit i's value
     * @param i position
     * @param value to set bit
     */
    @Override
    public void setBit(int i, bit value) {
        longword_bits.set(i, value);
    }

    /**
     * and two longwords, returning a third
     * @param other longword
     * @return longword
     */
    @Override
    public longword and(longword other) {
        ArrayList<bit> bits = new ArrayList<>(length);
        for(int i = 0; i < length; i++){
            bits.add(this.getBit(i).and(other.getBit(i)));
            }
        return new longword(bits);
        }

    /**
     * or two longwords, returning a third
     * @param other longword
     * @return longword
     */
    @Override
    public longword or(longword other) {
        ArrayList<bit> bits = new ArrayList<>(length);
        for(int i = 0; i < length; i++){
            bits.add(this.getBit(i).or(other.getBit(i)));
        }
        return new longword(bits);
    }

    /**
     * xor two longwords, returning a third
     * @param other longword
     * @return longword
     */
    @Override
    public longword xor(longword other) {
        ArrayList<bit> bits = new ArrayList<>(length);
        for(int i = 0; i < length; i++){
            bits.add(this.getBit(i).xor(other.getBit(i)));
        }
        return new longword(bits);
    }

    /**
     * negate this longword, creating another
     * @return longword
     */
    @Override
    public longword not() {
        ArrayList<bit> bits = new ArrayList<>(length);
        for (int i = 0; i < length; i++) {
            if (this.getBit(i).getValue() == 1)
                bits.add(i, new bit(0));
            else {
                bits.add(i, new bit(1));
            }
        }
        return new longword(bits);
    }

    /**
     * rightshift this longword by amount bits, creating a new longword
     * @param amount to shift to right
     * @return longword
     * shifting by 1
     * 11000000
     * 01100000
     *
     * 0 comes from left, pushes bits to right
     */
    @Override
    public longword rightShift(int amount) {
        ArrayList<bit> shifted_bits = new ArrayList<>(longword_bits);
        for(int i = 0; i < amount; i++)
        {
            // remove last element, add it to front of the ArrayList
            shifted_bits.remove( length - 1);
            if(shifted_bits.get(0).getValue() == 1 )
                shifted_bits.add(0, new bit(1));
            else shifted_bits.add(0, new bit(0));
        }
        return new longword(shifted_bits);
    }

    /**
     * leftshift this longword by amount bits, creating a new longword
     * @param amount to shift to left
     * @return longword
     *by 1
     * 00000111
     * 00001110
     * 0 comes from right, pushes bits to left
     */
    @Override
    public longword leftShift(int amount) {
        for(int i = 0; i < amount; i++)
        {
            // remove first element, add it to end of the ArrayList
            longword_bits.remove( 0);
            longword_bits.add(length-1 , new bit(0));
        }
        return new longword(longword_bits);
    }

    /**
     * @return the value of this longword as a long
     * long is 64 bit
     */
    @Override
    public long getUnsigned() {
        long conversion = 0;
        for (int i = 0, j = length-1; i < length; i++, j--){ //left most power of 2 starts with 32
            conversion += this.getBit(i).getValue() * Math.pow(2, j);
        }
        return conversion;
    }

    /**
     * @return the value of this longword as an int
     * represent sign
     * First, note that the number is negative, since it starts with a 1.
     */
    @Override
    public int getSigned() {
        int conversion = 0;
        // If the leftmost bit is a 1, the number is negative
        if (this.getBit(0).getValue() == 1) {

            //Flip all the bits in the number
            longword new_lw = this.not();

            //convert to decimal
            for (int i = 0, j = length-1; i < length; i++, j--){ //left most power of 2 starts with 32
                conversion += new_lw.getBit(i).getValue() * Math.pow(2, j);
            }

            conversion += 1; //add 1 for negative number
            return -conversion;
        } else {
            for (int i = 0, j = length-1; i < length; i++, j--){ //left most power of 2 starts with 32
                conversion += this.getBit(i).getValue() * Math.pow(2, j);
            }
            return conversion;
        }
    }

    /**
     * copies the values of the bits from another longword into this one
     * @param other longword
     */
    @Override
    public void copy(longword other) {
        this.longword_bits = other.longword_bits;
    }

    /**
     * set the value of the bits of this longword (used for tests)
     * @param value to set
     */
    @Override
    public void set(int value) {
        //if the value is a negative number
        if(value < 0) {
            value *= -1; //calculate as if it is positive at first (needed for binary conversion calculation)

            // convert to binary
            for (int i = 0, j = length-1; i < length; i++, j--){
                if (value > Math.pow(2,j)){
                    value -= Math.pow(2, j);
                    longword_bits.set(i, new bit(1));
                } else longword_bits.set(i, new bit(0));
            }

            //Flip all the bits in the number
            for (bit bit : this.longword_bits) {
                bit.toggle();
            }
        }
        else {
            // convert to binary
            for (int i = length; i > 0; i--){
                longword_bits.set(i-1,new bit(value %2));
                value = value/2;
            }
        }
    }

    /**
     *
     * @return strong representation of longword in binary
     */
    @Override
    public String toString() {
        int count = 1;
        StringBuilder output = new StringBuilder("");
        for (int i = 0; i < length; i++){
            output.append(longword_bits.get(i));
            if (count % 4 == 0) {
                output.append(" ");
            }
            count++;
        }
        return String.valueOf(output);
    }
}
