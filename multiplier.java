public class multiplier {

    /**
     * multiplier (b) at position i is 1: multiply by all bits in multiplicand (a) -> initial shift is 0 = product
     * add product to answer when multiplier (b) at position i is 1
     * repeat until all positions at b has been multiplied
     * @param a multiplicand
     * @param b multiplier
     * @return result
     */
    public static longword multiply (longword a, longword b) {
        longword answer = new longword(0), product = new longword(0);
        int cycle = 0;
        determineMultiplicand(a, b); //helper method to determine multiplicand for calculation purposes
        for (int i = 31; i >= 0; i--) {
            for (int j = 31; j >= 0; j--) { //multiplying all bits at multiplicand (a) by b at position i
                product.setBit(j, b.getBit(i).and(a.getBit(j)));
            }
            product.leftShift(cycle);
            answer = rippleAdder.add(product, answer);
            cycle++;
        }
        return answer;
    }

    /**
     * This is a helper method to determine the multiplicand and multiplier for calculation purposes
     * @param a longword
     * @param b longword
     */
    private static void determineMultiplicand(longword a, longword b){
        for (int i = 0; i < 32; i++){
            if(b.getBit(i).getValue() == 1){ //if b is longer then b is the multiplicand
                longword temp = a;
                a = b;
                b = temp;
            } else { //else don't change anything
                break;
            }
        }
    }
}
