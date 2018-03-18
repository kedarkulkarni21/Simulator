/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PipeLineStages;

import static PipeLineStages.Decode.*;
import static PipeLineStages.IntegerFU.*;
import static PipeLineStages.MUL1.*;
import static PipeLineStages.MUL2.*;
import static PipeLineStages.DIV4.*;
import static simulator.Simulator.isDivBusyForMul;

import simulator.Simulator;
import simulator.Simulator.*;


public class Memory extends Simulator {
    public static int memoryFlag=0;
    public static String memoryInstName;
    public static String memoryDestReg;
    public static String memorySource1; 
    public static String memorySource2;
    public static int latch;
    
    public static void Memory(){
    	
    	//System.out.println("in mem stage, memoryFlag"+memoryFlag+" IntegerFUFlag " +IntegerFUFlag+" mul2Flag"+mul2Flag+" isMulBusy"+isMulBusy+ " isDivBusy"+isDivBusy);
//         if(stall == 1){
//            System.out.println("Memory--->	");
//            return;
////            System.out.println("Memory ---> Stalled");
////            memStall=1;
//           // memoryFlag = 1;
//        }
//        else
        if(memoryFlag==0 && IntegerFUFlag==1 &&mul2Flag==0 &&isMulBusy==0 &&  isDivBusy==0){
         memoryInstName = FUInstName;
         memoryDestReg = FUDestReg;
         memorySource1 = FUSource1;
         memorySource2 = FUSource2;
         
         switch(memoryInstName){
                            
            case "MOVC":    Idest = Integer.parseInt(memoryDestReg.replaceAll("[^0-9]", ""));
                            regStatus[Idest]="INVALID";
                           // System.out.println("dest of movc in memory"+Idest);
                            System.out.println("MEM:\t I("+count+")" + memoryInstName + "  " + memoryDestReg + "  " + memorySource1);
                            break;
            case "SUB":
            case "EX-OR":
            case "EXOR":
            case "OR":
            case "ADD":     Idest = Integer.parseInt(memoryDestReg.replaceAll("[^0-9]", ""));
                            regStatus[Idest]="INVALID";
                            
                            latch = answer;
                            System.out.println("MEM:\t I("+count+")" + memoryInstName + "  " + memoryDestReg + "  " + memorySource1 + "  " + memorySource2
							+ "\tResult:	" + answer);
                            break;
            case "AND":     Idest = Integer.parseInt(memoryDestReg.replaceAll("[^0-9]", ""));
                            regStatus[Idest] = "INVALID";
                            latch=answer;
                            System.out.println("MEM:\t I("+count+")" + memoryInstName + "  " + memoryDestReg + "  " + memorySource1 + "  " + memorySource2
							+ "\tResult:	" + answer);
                            break;
            case "LOAD":    Idest = Integer.parseInt(memoryDestReg.replaceAll("[^0-9]", ""));
                     
                            regStatus[Idest]="INVALID";
                           
                            latch = memory[answer];
                  
                            System.out.println("MEM:\t I("+count+")" + memoryInstName + "  " + memoryDestReg + "  " + memorySource1 + "  " + memorySource2
							+ "\tResult:	" + latch);
                            break;
            case "STORE":   Idest = Integer.parseInt(memoryDestReg.replaceAll("[^0-9]", ""));
                            Isource1 = Integer.parseInt(memorySource1.replaceAll("[^0-9]", ""));
                            memory[answer]=Idest;
                            regStatus[Idest]="VALID";
                            regStatus[Isource1]="VALID";
                            System.out.println("MEM:\t I("+count+")" + memoryInstName + "  " + memoryDestReg + "  " + memorySource1 + "  " + memorySource2
							+ "\tValue Written : -->" + memory[answer]);
                             break;
            case "BNZ":
            case "BZ":      System.out.println("MEM:\t I("+count+")" +memoryInstName + " "+memoryDestReg);
                            break;
            case "HALT":    System.out.println("MEM:\t I("+count+")"+memoryInstName);
                            break;
            case "JAL":		System.out.println("MEM:\t I("+count+")" + memoryInstName + "  " + memoryDestReg + "  " + memorySource1 + "  " + memorySource2);
            				break;
        }
         memoryFlag = 1;
         IntegerFUFlag = 0;
    }else if(memoryFlag == 0 && mul2Flag ==1 && isMulBusy==1){
         memoryInstName = mul2InstName;
         memoryDestReg = mul2DestReg;
         memorySource1 = mul2Source1;
         memorySource2 = mul2Source2;
         
      
         Idest = Integer.parseInt(memoryDestReg.replaceAll("[^0-9]", ""));
           
         regStatus[Idest]="INVALID";
         latch = m2Answer;
         mul2Flag=0;
         isMulBusy=0;
         memoryFlag=1;
         mulStall=0;
          System.out.println("MEM:\t I("+count+")"+memoryInstName + "  " + memoryDestReg + "  " + memorySource1 + "  " + memorySource2
							+ "\tResult : " + latch);
    }
    else if(memoryFlag==0 && div4Flag==1 && isDivBusy==1 ) {
    	 memoryInstName = div4InstName;
         memoryDestReg = div4DestReg;
         memorySource1 = div4Source1;
         memorySource2 = div4Source2;
         switch(memoryInstName) {
         case "DIV":	Idest = Integer.parseInt(memoryDestReg.replaceAll("[^0-9]", ""));
           				regStatus[Idest]="INVALID";
           				latch = d4Answer;
           				
           				System.out.println("MEM:\t I("+count+")"+memoryInstName + "  " + memoryDestReg + "  " + memorySource1 + "  " + memorySource2
							+ "\tResult : " + latch); 	
           				break;
         case "HALT":	System.out.println("MEM:\t I("+count+")"+memoryInstName);
         				break;	
        }
         memoryFlag=1;
         div4Flag=0;
         isDivBusy=0;
         isDivBusyForMul=0;
         }
            else {
			memoryFlag = 0;
			System.out.println("MEM:\t Empty");
		}
        
   
    
    
    }
}
