/**
 * @author Luna Dagci
 * Implementation of bit
 */
public class bit implements IBit {

    private int value; //stores the integer value 1 or 0 of a bit

    /**
     * Default Constructor For bit
     */
    public bit(){
    }

    /**
     * Constructor of bit
     * @param value representing integer value of bit
     */
    public bit(int value){
        this.value = value;
    }

    /**
     * sets the value of the bit
     * @param value set to bit
     */
    @Override
    public void set(int value) {
        this.value = value;
    }

    /**
     * Changes the value from 0 to 1 or 1 to 0
     */
    @Override
    public void toggle() {
        if (this.value == 1)
            this.value = 0;
        else
            this.value = 1;
    }

    /**
     * Sets the bit to 1
     */
    @Override
    public void set() {
        this.value = 1;
    }

    /**
     * Sets the bit to 0
     */
    @Override
    public void clear() {
        this.value = 0;
    }

    /**
     * returns the current value
     * @return value of bit
     */
    @Override
    public int getValue() {
        return value;
    }

    /**
     * Performs and on two bits and returns a new bit set to the result
     * @param other bit
     * @return new bit as result
     */
    @Override
    public bit and(bit other) {
        bit bit = new bit();
        switch(other.value){
            case 1:
                if (this.value == 0)
                    bit.set(0);
                else bit.set(1);
                break;
            case 0:
                bit.set(0);
                break;
        }
        return bit;
    }

    /**
     * Performs or on two bits and returns a new bit set to the result
     * @param other bit
     * @return new bit as result
     */
    @Override
    public bit or(bit other) {
        bit bit = new bit();
        switch(other.value){
            case 1:
                bit.set(1);
                break;
            case 0:
                if (this.value == 0)
                    bit.set(0);
                else bit.set(1);
        }
        return bit;
    }

    /**
     * Performs xor on two bits and returns a new bit set to the result
     * @param other bit
     * @return new bit as result
     */
    @Override
    public bit xor(bit other) {
        if(this.value == other.value)
            return new bit(0);
        else
            return new bit(1);
    }

    /**
     * Performs not on the existing bit, returning the result as a new bit
     * @return new bit as result to not
     */
    @Override
    public bit not() {
        if (this.value == 1)
            return new bit(0);
        else {
            return new bit(1);
        }
    }

    /**
     * String representation of bit
     * @return 1 or 0
     */
    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
