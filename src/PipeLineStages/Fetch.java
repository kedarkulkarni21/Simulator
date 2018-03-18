/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PipeLineStages;

import static PipeLineStages.IntegerFU.*;
import simulator.Simulator;

/**
 *
 * @author pooja
 */
public class Fetch extends Simulator {
    
   public static String fetchInstName;
   public static String fetchDestReg;
   public static String fetchSource1;
   public static String fetchSource2;
   public static int fetchInstNum;
   public static int fetchInstAdd;

    public static void Fetch(int i) {
            if(fetchFlag==0 && stall == 0 && isHalt==0 && stallFetchForDecode==0){
            if(instructionName[i]!=null)
            {   
            	fetchInstNum=instructionNum[i];
                fetchInstAdd = address[i];
                fetchInstName=instructionName[i];
                fetchDestReg=destinationReg[i];
                fetchSource1=source1[i];
                fetchSource2=source2[i];
                switch(fetchInstName){
                case "HALT":    System.out.println("Fetch:\t I("+count+") "+fetchInstName);
                                break;
                case "BZ":
                case "BNZ":
                            System.out.println("Fetch:\t I("+count+") "  + fetchInstName + "  " + fetchDestReg);
                            //branchIO=icount;
                            break;
                case "JUMP":   System.out.println("Fetch:\t I("+count+")"  + fetchInstName + "  " + fetchDestReg + "  " + fetchSource1);
                                
                                break;
                case "MOVC":    System.out.println("Fetch:\t I("+count+") " + fetchInstName + "  " + fetchDestReg + "  " + fetchSource1);
                            //branchIO=icount;
                            break;
                case "SUB":
                case "AND":
                case "MUL":
                case "OR":
                case "DIV":
                case "EX-OR":
                case "EXOR":
                case "LOAD":
                case "STORE":
                case "JAL":
                case "ADD":     System.out.println("Fetch:\t I("+count+") " +fetchInstName+" "+fetchDestReg+" "+fetchSource1+" "+fetchSource2);
                                break;
                                
                                
                
        
            }
        
            count++;
            fetchFlag=1;
        } else if(fetchFlag==0 && stall == 0 && isHalt==0 && stallFetchForDecode==1) {
   
        	 switch(fetchInstName){
             case "HALT":    System.out.println("Fetch:\t I("+count+") "+fetchInstName);
                             break;
             case "BZ":
             case "BNZ":
                         System.out.println("Fetch:\t I("+count+") "  + fetchInstName + "  " + fetchDestReg);
                         //branchIO=icount;
                         break;
             case "JUMP":   System.out.println("Fetch:\t I("+count+")"  + fetchInstName + "  " + fetchDestReg + "  " + fetchSource1);
                             
                             break;
             case "MOVC":    System.out.println("Fetch:\t I("+count+") " + fetchInstName + "  " + fetchDestReg + "  " + fetchSource1);
                         //branchIO=icount;
                         break;
             case "SUB":
             case "AND":
             case "MUL":
             case "OR":
             case "DIV":
             case "EX-OR":
             case "EXOR":
             case "LOAD":
             case "STORE":
             case "JAL":
             case "ADD":     System.out.println("Fetch:\t I("+count+") " +fetchInstName+" "+fetchDestReg+" "+fetchSource1+" "+fetchSource2);
                             break;
                             
                             
             
     
         }
     
         count++;
         fetchFlag=1;
        }
            else {                 
				System.out.println("Fetch:\t Empty"); 
			}    
        }
            
                
     
        else if(stall==1){
            fetchInstName = instructionName[i];
            fetchDestReg = destinationReg[i];
      fetchSource1 = source1[i];
        fetchSource2 = source2[i];
             if(fetchInstName!=null) {
        
            switch(fetchInstName){
                case "BZ":
                case "BNZ":
                                System.out.println("Fetch:\t I("+count+") " + fetchInstName + "  " + fetchDestReg);
                                //branchIO=icount;
                                break;
                case "JUMP":
                case "BAL":
                case "MOVC":  
                case "MOV":     System.out.println("Fetch:\t I("+count+") " + fetchInstName + "  " + fetchDestReg + "  " + fetchSource1);
                                //branchIO=icount;
                                break;
                default:        System.out.println("Fetch:\t I("+count+") " +fetchInstName+" "+fetchDestReg+" "+fetchSource1+" "+fetchSource2);

                                break;
        }
             }
            fetchFlag=0;
       
       
}
        else {
        	fetchInstName = instructionName[i];
            fetchDestReg = destinationReg[i];
      fetchSource1 = source1[i];
        fetchSource2 = source2[i];
        switch(fetchInstName){
//            case "BZ":
//            case "BNZ":
//                            System.out.println("Fetch:\t" + fetchInstName + "  " + fetchDestReg);
//                            //branchIO=icount;
//                            break;
//            case "JUMP":
//            case "BAL":
//            case "MOVC":  
//            case "MOV":     System.out.println("Fetch:\t" + fetchInstName + "  " + fetchDestReg + "  " + fetchSource1);
//                            //branchIO=icount;
//                            break;
            default:        System.out.println("Fetch:\t Empty");

                            break;
    }
        fetchFlag=0;
   
        }
}
}

