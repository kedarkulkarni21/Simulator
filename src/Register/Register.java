
package Register;

import simulator.Simulator;



public class Register extends Simulator {
    public static void initializeRegister(){
            for(int i=0;i<16;i++){ //intializing all the registers
            registers[i]=0;
            regStatus[i] = "VALID";
        }
    }
}
