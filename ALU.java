public class ALU {
    /**
     * 1000 – and
     * 1001 – or
     * 1010 – xor
     * 1011 – not (not “a”; ignore b)
     * 1100 – left shift ( “a” is the value to shift, “b” is the amount to shift it; ignore all but the lowest 5 bits)
     * 1101 – right shift ( “a” is the value to shift, “b” is the amount to shift it; ignore all but the lowest 5 bits)
     * 1110 – add
     * 1111 – subtract
     * 0111 - multiply
     * @param operation 4bit
     * @param a longword to be calculated
     * @param b longword to be calculated
     * @return calculation
     */
    public static longword doOp(bit[] operation, longword a, longword b) {
        longword ans = new longword();
        switch (operation[0].getValue()) {
            case 1:
                if(operation[1].getValue() == 0){ //10--
                    if(operation[2].getValue() == 0){ //100-
                        if(operation[3].getValue() == 0){ //1000 and
                            ans = a.and(b);
                        }
                        else{ //1001 or
                            ans = a.or(b);
                        }
                    }
                    else{ //101-
                        if (operation[3].getValue() == 0){ // 1010 xor
                            ans = a.xor(b);
                        }
                        else{ //1011 not a, ignore b
                            ans = a.not();
                        }
                    }
                } else { //11--
                    if (operation[2].getValue() == 0) { //110-
                        if (operation[3].getValue() == 0) { //1100 left shift
                            //ignore all but the lowest 5 bits: when shifting is more than 32 positions (0-31) the result will always become 0
                            ans = a.leftShift(b.getSigned());
                        } else { //1101 right shift
                            //ignore all but the lowest 5 bits: when shifting is more than 32 positions (0-31) the result will always become 0
                            ans = a.rightShift(b.getSigned());
                        }
                    } else { //111-
                        if (operation[3].getValue() == 0) { // 1110 add
                            ans = rippleAdder.add(a,b);
                        }
                        else { //1111 subtract
                            ans = rippleAdder.subtract(a,b);
                        }
                    }
                }
                break;
            case 0:
                if(operation[1].getValue() == 1) { //01--
                    if (operation[2].getValue() == 1) { //011-
                        if (operation[3].getValue() == 1) { //0111 multiply
                            ans = multiplier.multiply(a,b);
                        }
                    }
                }
                break;
        }
        return ans;
    }
}
