/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PipeLineStages;


import static PipeLineStages.Decode.*;
import static PipeLineStages.IntegerFU.FUDestReg;
import static PipeLineStages.Memory.*;

import simulator.Simulator;

/**
 *
 * @author pooja
 */
public class WriteBack extends Simulator{
    public static int writeBackFlag=0;
    public static String WBInstName;
    public static String WBDestReg;
    public static String WBSource1;
    public static String WBSource2;
    
    
    public static void Writeback(){
        
        if(memoryFlag==1 && writeBackFlag==0){
        WBInstName = memoryInstName;
        WBDestReg = memoryDestReg;
        WBSource1 = memorySource1;
        WBSource2 = memorySource2;
        memoryFlag = 0;
        
       /* memoryInstName = "";
        memoryDestReg = "";
        memorySource1 = "";
        memorySource2 = "";*/
        
        switch(WBInstName){
            case "MOVC":    Idest = Integer.parseInt(WBDestReg.replaceAll("[^0-9]", ""));
                            registers[Idest]=Integer.parseInt(WBSource1);
                          
                            regStatus[Idest] = "VALID";
                            System.out.println("WB:\t I("+count+")" + WBInstName + " " + WBDestReg + "  " + WBSource1 + "\t value written : -->"+registers[Idest]);
                            break;
                          
                          
            case "ADD":
            case "AND":
            case "SUB":
            case "OR":
            case "EX-OR":
            case "EXOR":
                            Idest = Integer.parseInt(WBDestReg.replaceAll("[^0-9]", ""));
                            registers[Idest] = latch;
                            regStatus[Idest] = "VALID";
                            System.out.println("WB:\t I("+count+")" + WBInstName + " " + WBDestReg + "  " + WBSource1 + "  " + WBSource2
						+ "\t \tvalue written : -->" + registers[Idest]);
                            break;
            case "LOAD":    Idest = Integer.parseInt(WBDestReg.replaceAll("[^0-9]", ""));
                            registers[Idest]=latch;
                            regStatus[Idest] = "VALID";
                            System.out.println("WB:\t I("+count+")" + WBInstName + " " + WBDestReg + "  " + WBSource1 + "  " + WBSource2
						+ "\t \tvalue written : -->" + registers[Idest]);
                            break;
            case "STORE":   System.out.println("WB:\t I("+count+")"+ WBInstName +  " " + WBDestReg +  " " + WBSource1 +" "+ WBSource2+" Value written in Memory stage");
                            break;
            case "MUL":     Idest = Integer.parseInt(WBDestReg.replaceAll("[^0-9]", ""));
                            registers[Idest]=latch;
                            regStatus[Idest] = "VALID";
                            System.out.println("WB:\t I("+count+")" + WBInstName + " " + WBDestReg + "  " + WBSource1 + "  " + WBSource2
						+ "\t \tvalue written : -->" + registers[Idest]);
                            break;
            case "BNZ":
            case "BZ":      System.out.println("WB:\t I("+count+")"+WBInstName+" "+WBDestReg);
                            break;
            case "HALT":    System.out.println("WB:\t I("+count+")"+WBInstName);
            				break;
            case "DIV":		Idest = Integer.parseInt(WBDestReg.replaceAll("[^0-9]", ""));
            				registers[Idest]=latch;
            				regStatus[Idest] = "VALID";
            				System.out.println("WB:\t I("+count+")" + WBInstName + " " + WBDestReg + "  " + WBSource1 + "  " + WBSource2
            						+ "\t \tvalue written : -->" + registers[Idest]);
            				break;
            case "JAL":		Idest = Integer.parseInt(WBDestReg.replaceAll("[^0-9]", ""));
            				registers[Idest]=newJAlAdd;
            				regStatus[Idest] = "VALID";
            				System.out.println("WB:\t I("+count+")" + WBInstName + " " + WBDestReg + "  " + WBSource1 + "  " + WBSource2
            						+ "\t \tvalue written : -->" + registers[Idest]);
            				break;
        }
        
    
            
}
        else {
            System.out.println("WB:\t Empty");
        }
}
}