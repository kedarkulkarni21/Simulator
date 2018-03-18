/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PipeLineStages;

import static PipeLineStages.Decode.Idest;
import static PipeLineStages.Decode.goToMul;
import static PipeLineStages.MUL1.*;
import simulator.Simulator;

/**
 *
 * @author pooja
 */
public class MUL2 extends Simulator{
    public static String mul2InstName;
    public static String mul2DestReg;
    public static String mul2Source1;
    public static String mul2Source2;
    public static int m2Answer;
    public static int mul2Flag = 0;
    
    public static void mul2(){
/*       System.out.println("mul1Flag "+mul1Flag+" mul2Flag "+mul2Flag+" mulStall"+mulStall+"  isDivBusyForMul"+isDivBusyForMul);*/
        if(mul1Flag == 1 && mul1InstName!=null){
        	mul2InstName = mul1InstName;
            mul2DestReg = mul1DestReg;
            mul2Source1 = mul1Source1;
            mul2Source2 = mul1Source2;
            Idest = Integer.parseInt(mul2DestReg.replaceAll("[^0-9]", ""));
            m2Answer=m1Answer;
            regStatus[Idest] = "INVALID";
            
            if(isDivBusyForMul==0) {
            mul1Flag = 0;
            mul2Flag = 1;
            goToMul=0;
            isMulBusy=1;
            System.out.println("MUL2:\t I("+count+")"+mul2InstName+" "+mul2DestReg+" "+mul2Source1+" "+mul2Source2);
            }else {
            	mul1Flag=0;
            	mul2Flag=0;
            	goToMul=0;
            	isMulBusy=1;
            	mulStall=1;
            	System.out.println("MUL2:\t I("+count+")"+mul2InstName+" "+mul2DestReg+" "+mul2Source1+" "+mul2Source2+" Stalled");
            }
        }
        else if(mul1Flag==0 && mul2Flag==0 && mulStall==1) {
      
        	Idest = Integer.parseInt(mul2DestReg.replaceAll("[^0-9]", ""));
            m2Answer=m1Answer;
            regStatus[Idest] = "INVALID";
     
            if(isDivBusyForMul==0) {
            mul1Flag = 0;
            mul2Flag = 1;
            goToMul=0;
            isMulBusy=1;
            System.out.println("MUL2:\t I("+count+")"+mul2InstName+" "+mul2DestReg+" "+mul2Source1+" "+mul2Source2);
            }else {
            	mul1Flag=0;
            	mul2Flag=0;
            	goToMul=0;
            	isMulBusy=1;
            	mulStall=1;
            	System.out.println("MUL2:\t I("+count+")"+mul2InstName+" "+mul2DestReg+" "+mul2Source1+" "+mul2Source2+" Stalled");
        }}
        else
            System.out.println("MUL2:\t Empty");
    }
}
    

