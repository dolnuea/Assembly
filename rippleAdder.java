/**
 * @author Luna Dagci
 */
public class rippleAdder {

    /***
     * @param a longword to be added
     * @param b longword to be added
     * @return sum of a and b
     * 1 + 1 = 10
     * 1 + 0 = 01
     * 0 + 1 = 01
     * 0 + 0 = 00
     */
    public static longword add(longword a, longword b) {
        bit carry = new bit(0), result; // sum of 2 bits
        longword sum = new longword(); //final result
        for (int i = 31; i >= 0; i--) { //start from rightmost index
            result = a.getBit(i).xor(b.getBit(i)).xor(carry); //X XOR Y XOR C_in
            carry = a.getBit(i).and(b.getBit(i)).or((a.getBit(i).xor(b.getBit(i))).and(carry)); //C_out = = X AND Y OR (( X XOR Y) AND Cin)
            sum.setBit(i, result); //add calculated bit to index i
        }
        return sum;
    }

    /**
     * @param a longword to be subtracted
     * @param b longword to be subtracted
     * @return subtraction of a and b
     * a - b
     * take 2s complement of p
     * then add like normal
     */
    public static longword subtract(longword a, longword b) {
        bit borrow = new bit(0), result; // difference of 2 bits
        longword difference = new longword(); //final result
        for (int i = 31; i >= 0; i--) { //start from rightmost index
            result = a.getBit(i).xor(b.getBit(i)).xor(borrow); //X XOR Y XOR B_in
            borrow = a.getBit(i).not().and(b.getBit(i)).or((a.getBit(i).xor(b.getBit(i)).not()).and(borrow)); //B_out = = ~X AND Y OR ~(( X XOR Y) AND Bin)
            difference.setBit(i, result); //add calculated bit to index i
        }
        return difference;
    }
}
