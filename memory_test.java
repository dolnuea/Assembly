public class memory_test {
    public static void main(String[] args) throws Exception {
        bit_test.runTests();
        longword_test.runTests();
        rippleAdder_test.runTests();
        multiplier_test.runTests();
        ALU_test.runTests();
        runTests();
    }

    /**
     * Runs memory unit tests
     * @throws Exception if a unit case fails
     */
    public static void runTests() throws Exception {
        System.out.println("---------------------Memory TEST CASES------------------");
        memory memory = new memory();
        testWrite(new longword(1), new longword(12345), memory);
        testRead(new longword(1), memory);

        testWrite(new longword(3), new longword(5667), memory);
        testRead(new longword(3), memory);

        testWrite(new longword(7), new longword(-4), memory);
        testRead(new longword(7), memory);

        testWrite(new longword(50), new longword(10), memory);
        testRead(new longword(50), memory);

        testWrite(new longword(400), new longword(7000), memory);
        testRead(new longword(400), memory);

        testWrite(new longword(1000), new longword(67889), memory);
        testRead(new longword(1000), memory);

    }

    /*Helper*/

    /**
     *
     * @param address to read in memory
     * @param memory to write
     * @throws Exception if address overflows memory
     */
    public static void testRead(longword address, memory memory) throws Exception {
        if (address.getSigned() <= 1024)
            System.out.println("Data stored in address " + address.getUnsigned() + " is " + memory.read(address).getSigned() + " : " + memory.read(address));
        else
            throw new Exception("Read failed: No such address");
    }

    /**
     *
     * @param address to write in memory
     * @param data to write in memory
     * @param memory to read from
     * @throws Exception if address overflows memory
     */
    public static void testWrite(longword address, longword data,memory memory) throws Exception {
        memory.write(address,data);
        if (address.getUnsigned() <= 1024)
            System.out.println(data.getSigned() + " successfully written to address " + address.getUnsigned());
        else
            throw new Exception("Writing to address " + address.getUnsigned() + " failed: not enough memory space. Capacity: 1024 bytes");
    }
}

