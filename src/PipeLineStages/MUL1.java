/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PipeLineStages;

import static PipeLineStages.Decode.*;

import simulator.Simulator;

/**
 *
 * @author pooja
 */
public class MUL1 extends Simulator {
    public static String mul1InstName;
    public static String mul1DestReg;
    public static String mul1Source1;
    public static String mul1Source2;
    public static int mul1Flag = 0;
    public static int m1Answer;
    
public static void mul1(){
    mul1InstName = decodeInstName;
    mul1DestReg = decodeDestReg;
    mul1Source1 = decodeSource1;
    mul1Source2 = decodeSource2;
    if(decodeFlag == 1 && mul1Flag == 0 && goToMul==1){
        if(mul1InstName!=null){
            if(mul1InstName.equals("MUL")){
                Idest = Integer.parseInt(mul1DestReg.replaceAll("[^0-9]", ""));
                regStatus[Idest] = "INVALID";
                msource1 = Integer.parseInt(mul1Source1.replaceAll("[^0-9]", ""));
                msource2 = Integer.parseInt(mul1Source2.replaceAll("[^0-9]", ""));
                m1Answer = registers[msource1]*registers[msource2];
                if(m1Answer==0) 
                	zeroFlag=1;
                else
                	zeroFlag=0;
                
                System.out.println("MUL1:\t I("+count+")"+mul1InstName+" "+mul1DestReg+" "+mul1Source1+" "+mul1Source2);
                mul1Flag = 1;
                decodeFlag=0;
                isMulBusy=1;
            }
        }
    }else{
        System.out.println("MUL1:\t Empty");
    }  
    
}    
}
