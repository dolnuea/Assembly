# Assembly Implementation in Java
## Implemented Instructions:
* Add
* Subtract
* Multiply
* Left Shift
* Right Shift
* Jump
* Pop
* Push
* Halt
* Move
* Interrupt
* Compare
* Branching 
* Call
* Return
* And
* Or
* Xor
* Not

## Goals Achieved upon completion of this project:
* [X] Be able to understand and compare assembly languages
* [X]	Be able to explain how circuits are used to construct memory and processors
* [X]	Be able to discuss performance techniques such as pipelining and out of order execution
* [X]	Be able to compare and contrast computer designs 
* [X]	Be able to write an assembler 
* [X]	Be able to emulate a simple CPU using a virtual machine

## Challenges Faced
* I had difficulty with negative numbers while implementing the multiplier. I later realized my shift methods were incorrect after debugging my program, and fixed my shift methods, and I learned left shift always shifts 0's and right shift method shifts 0s for positives and 1s for negatives on <a href="https://docs.microsoft.com/en-us/cpp/cpp/left-shift-and-right-shift-operators-input-and-output?view=msvc-160#:~:text=Right%20Shifts,-The%20right%2Dshift&text=For%20signed%20numbers%2C%20the%20sign,is%20negative%2C%201%20is%20used.&text=The%20result%20of%20a%20right,negative%20number%20is%20implementation%2Ddependent.">Microsoft Docs</a>. 
* I had difficulty with the move instruction, which moved the number to wrong registers. I spent a long time figuring out this problem since my memory, preload decode and execute were implemented correctly. After my getting guidance from my professor, I have learned the fix was actually caused from one line of code only, and my implementation only really created 1 register and gave it 16 names.
Therefore, I have used ```Arrays.fill(registers, new longword(0))``` to fill my registers array with 16 references to the same longword. 

