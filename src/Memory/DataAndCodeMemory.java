
package Memory;

import simulator.Simulator;


public class DataAndCodeMemory extends Simulator{
    public static void initializeCodeLine(){
    for(int i=0;i<100;i++){ //for number of instructions in a file
            address[i]=0;
            instructionNum[i]=0;
            instructionName[i]=null;
            destinationReg[i]=null;
            source1[i]=null;
            source2[i]=null;
        }
    }
    
    public static void initializeCodeMemory(){
        for (int i = 0; i < 3999; i++)
        {
            memory[i] = 0;
            
        }
    }
}
