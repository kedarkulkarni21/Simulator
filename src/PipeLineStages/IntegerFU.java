/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PipeLineStages;

import static PipeLineStages.Decode.*;
import simulator.Simulator;
import static simulator.Simulator.*;

public class IntegerFU extends Simulator {
    public static int FUInstNum;
    public static int FUInstAdd;
    public static String FUInstName;
    public static String FUDestReg;
    public static String FUSource1;
    public static String FUSource2;
    
    public static String result;
    public static int answer;
    public static int IntegerFUFlag = 0;
    public static int jumpAddress1;
    public static int lineNum;
    public static int add1;
    public static int branchFlag=0;
    
    
    public static void IntegerFU(){
if(decodeFlag == 1 && decodeInstName != null && IntegerFUFlag == 0){
        FUInstAdd = decodeInstAdd;
        FUInstNum = decodeInstNum;
        FUInstName = decodeInstName;
        FUDestReg = decodeDestReg;
        FUSource1 = decodeSource1;
        FUSource2 = decodeSource2;
        switch(FUInstName){
            case "MOVC":    Idest = Integer.parseInt(FUDestReg.replaceAll("[^0-9]", ""));
                            regStatus[Idest]="INVALID";
                            FUSource1=FUSource1;
                            if(isMulBusy==0 && isDivBusy==0){
                       
                                decodeFlag=0;
                                IntegerFUFlag=1;
                                intfuMulHalt=0;
                                 System.out.println("INTFU:\t I("+count+")" + FUInstName + "  " + FUDestReg + "  " + FUSource1);
                            }
                            else if(isMulBusy==1){
                                decodeFlag=0;
                                IntegerFUFlag=0;
                                intfuMulHalt=1;
                                
                                System.out.println("INTFU:\t I("+count+")" + FUInstName + "  " + FUDestReg + "  " + FUSource1+" Stalled");
                            }
                            else if(isDivBusy==1) {
                                decodeFlag=0;
                                IntegerFUFlag=0;
                                intfuDivHalt=1;
                                System.out.println("INTFU:\t I("+count+")" + FUInstName + "  " + FUDestReg + "  " + FUSource1+" Stalled");
                            }
                           
                            break;
            case "ADD":     answer = registers[temp1] + registers[temp2];
                            Idest = Integer.parseInt(FUDestReg.replaceAll("[^0-9]", ""));
                            if(answer==0)
                                zeroFlag=1;
                            else
                                zeroFlag=0;
                            regStatus[Idest]="INVALID";
                            if(isMulBusy==0 && isDivBusy==0){
                                decodeFlag=0;
                                IntegerFUFlag=1;
                                intfuMulHalt=0;
                                System.out.println("INTFU:\t I("+count+")"+FUInstName+" "+FUDestReg+" "+FUSource1+" "+FUSource2);
                            } else if(isMulBusy==1){
                                decodeFlag=0;
                                IntegerFUFlag=0;
                                intfuMulHalt=1;
                                
                                System.out.println("INTFU:\t I("+count+")" + FUInstName + "  " + FUDestReg + "  " + FUSource1+" "+FUSource2+" Stalled");
                            }
                            
                            else if(isDivBusy==1){
                                decodeFlag=0;
                                IntegerFUFlag=0;
                                intfuDivHalt=1;
                                System.out.println("INTFU:\t I("+count+")"+FUInstName+" "+FUDestReg+" "+FUSource1+" "+FUSource2+" Stalled");
                            }
                            
                            break;
            case "SUB":     Idest = Integer.parseInt(FUDestReg.replaceAll("[^0-9]", ""));
                            regStatus[Idest]="INVALID";
                            answer = registers[temp1]-registers[temp2];
                         
                            if(isMulBusy==0 && isDivBusy==0){
                            	if(answer == 0) 
                                	
                                    zeroFlag = 1;
                                else
                                    zeroFlag = 0;
                                decodeFlag=0;
                                IntegerFUFlag=1;
                                intfuMulHalt=0;
                                System.out.println("INTFU:\t I("+count+")"+FUInstName+" "+FUDestReg+" "+FUSource1+" "+FUSource2);
                            } else if(isMulBusy==1){
                                decodeFlag=0;
                                IntegerFUFlag=0;
                                intfuMulHalt=1;
                                
                                System.out.println("INTFU:\t I("+count+")" + FUInstName + "  " + FUDestReg + "  " + FUSource1+" "+FUSource2+" Stalled");
                            }
                            
                            else if(isDivBusy==1){
                                decodeFlag=0;
                                IntegerFUFlag=0;
                                intfuDivHalt=1;
                                System.out.println("INTFU:\t I("+count+")"+FUInstName+" "+FUDestReg+" "+FUSource1+" "+FUSource2+" Stalled");
                            }
                            break;
            case "EXOR":
            case "EX-OR":   Idest = Integer.parseInt(FUDestReg.replaceAll("[^0-9]", ""));
                            regStatus[Idest]="INVALID";
                            answer = registers[temp1]^registers[temp2];
                            if(isMulBusy==0 && isDivBusy==0){
                            	if(answer == 0) 
                                
                                    zeroFlag = 1;
                                else
                                    zeroFlag = 0;
                                decodeFlag=0;
                                IntegerFUFlag=1;
                                intfuMulHalt=0;
                                System.out.println("INTFU:\t I("+count+")"+FUInstName+" "+FUDestReg+" "+FUSource1+" "+FUSource2);
                            } else if(isMulBusy==1){
                                decodeFlag=0;
                                IntegerFUFlag=0;
                                intfuMulHalt=1;
                                
                                System.out.println("INTFU:\t I("+count+")" + FUInstName + "  " + FUDestReg + "  " + FUSource1+" "+FUSource2+" Stalled");
                            }
                            
                            else if(isDivBusy==1){
                                decodeFlag=0;
                                IntegerFUFlag=0;
                                intfuDivHalt=1;
                                System.out.println("INTFU:\t I("+count+")"+FUInstName+" "+FUDestReg+" "+FUSource1+" "+FUSource2+" Stalled");
                            }
                            break;
            case "OR":      Idest = Integer.parseInt(FUDestReg.replaceAll("[^0-9]", ""));
                            regStatus[Idest]="INVALID";
                            answer = registers[temp1] | registers[temp2];
                            if(isMulBusy==0 && isDivBusy==0){
                            	if(answer == 0) 
                                
                                    zeroFlag = 1;
                                else
                                    zeroFlag = 0;
                                decodeFlag=0;
                                IntegerFUFlag=1;
                                intfuMulHalt=0;
                                System.out.println("INTFU:\t I("+count+")"+FUInstName+" "+FUDestReg+" "+FUSource1+" "+FUSource2);
                            } else if(isMulBusy==1){
                                decodeFlag=0;
                                IntegerFUFlag=0;
                                intfuMulHalt=1;
                                
                                System.out.println("INTFU:\t I("+count+")" + FUInstName + "  " + FUDestReg + "  " + FUSource1+" "+FUSource2+" Stalled");
                            }
                            
                            else if(isDivBusy==1){
                                decodeFlag=0;
                                IntegerFUFlag=0;
                                intfuDivHalt=1;
                                System.out.println("INTFU:\t I("+count+")"+FUInstName+" "+FUDestReg+" "+FUSource1+" "+FUSource2+" Stalled");
                            }
                            break;
            case "AND":     Idest = Integer.parseInt(FUDestReg.replaceAll("[^0-9]", ""));
                            regStatus[Idest]="INVALID";
                            answer = registers[temp1] & registers[temp2];
                            if(isMulBusy==0 && isDivBusy==0){
                            	if(answer == 0) 
                                
                                    zeroFlag = 1;
                                else
                                    zeroFlag = 0;
                                decodeFlag=0;
                                IntegerFUFlag=1;
                                intfuMulHalt=0;
                                System.out.println("INTFU:\t I("+count+")"+FUInstName+" "+FUDestReg+" "+FUSource1+" "+FUSource2);
                            } else if(isMulBusy==1){
                                decodeFlag=0;
                                IntegerFUFlag=0;
                                intfuMulHalt=1;
                                
                                System.out.println("INTFU:\t I("+count+")" + FUInstName + "  " + FUDestReg + "  " + FUSource1+" "+FUSource2+" Stalled");
                            }
                            
                            else if(isDivBusy==1){
                                decodeFlag=0;
                                IntegerFUFlag=0;
                                intfuDivHalt=1;
                                System.out.println("INTFU:\t I("+count+")"+FUInstName+" "+FUDestReg+" "+FUSource1+" "+FUSource2+" Stalled");
                            }
                            break;
            case "LOAD":    Idest = Integer.parseInt(FUDestReg.replaceAll("[^0-9]", ""));
                            regStatus[Idest]="INVALID";
                            answer = registers[Isource1]+Isource2;
                            if(isMulBusy==0 && isDivBusy==0){
                                decodeFlag=0;
                                IntegerFUFlag=1;
                                intfuMulHalt=0;
                                System.out.println("INTFU:\t I("+count+")"+FUInstName+" "+FUDestReg+" "+FUSource1+" "+FUSource2);
                            } else if(isMulBusy==1){
                                decodeFlag=0;
                                IntegerFUFlag=0;
                                intfuMulHalt=1;
                                
                                System.out.println("INTFU:\t I("+count+")" + FUInstName + "  " + FUDestReg + "  " + FUSource1+" "+FUSource2+" Stalled");
                            }
                            
                            else if(isDivBusy==1){
                                decodeFlag=0;
                                IntegerFUFlag=0;
                                intfuDivHalt=1;
                                System.out.println("INTFU:\t I("+count+")"+FUInstName+" "+FUDestReg+" "+FUSource1+" "+FUSource2+" Stalled");
                            }
                            break;
            case "STORE":   Idest = Integer.parseInt(FUDestReg.replaceAll("[^0-9]", ""));
                            regStatus[Isource1] = "INVALID";  
                            regStatus[Idest]="INVALID";
                            answer = registers[Isource1]+Isource2;
                            if(isMulBusy==0 && isDivBusy==0){
                                decodeFlag=0;
                                IntegerFUFlag=1;
                                intfuMulHalt=0;
                                System.out.println("INTFU:\t I("+count+")"+FUInstName+" "+FUDestReg+" "+FUSource1+" "+FUSource2);
                            } else if(isMulBusy==1){
                                decodeFlag=0;
                                IntegerFUFlag=0;
                                intfuMulHalt=1;
                                
                                System.out.println("INTFU:\t I("+count+")" + FUInstName + "  " + FUDestReg + "  " + FUSource1+" "+FUSource2+" Stalled");
                            }
                            
                            else if(isDivBusy==1){
                                decodeFlag=0;
                                IntegerFUFlag=0;
                                intfuDivHalt=1;
                                System.out.println("INTFU:\t I("+count+")"+FUInstName+" "+FUDestReg+" "+FUSource1+" "+FUSource2+" Stalled");
                            }
                            break;
            case "JUMP":    Isource1 = Integer.parseInt(FUDestReg.replaceAll("[^0-9]", "")); 
                            Isource2 = Integer.parseInt(FUSource1.replaceAll("[^0-9]", "")); 
                            jumpAddress1 = registers[Isource1] + Isource2;
                            lineNum=(jumpAddress1-4000)/4;
                         
                            count=lineNum;
                          //  System.out.println("Counter value changed to "+count);
                            //System.out.println("line number: "+lineNum);
                            if(isMulBusy==0 && isDivBusy==0){
                            branchFlag=1;
                            IntegerFUFlag=1;
                            decodeFlag=0;
                            intfuMulHalt=0;
                            fetchFlag=0;
                            System.out.println("INTFU:\t I("+count+")"+FUInstName+" "+FUDestReg+" "+FUSource1);
                            }
                            else if(isMulBusy==1){
                                decodeFlag=0;
                                fetchFlag=0;
                                intfuMulHalt=1;
                                System.out.println("INTFU:\t I("+count+")"+FUInstName+" "+FUDestReg+" "+FUSource1+" Stalled");
                            }
                            else if(isDivBusy==1) {
                            	decodeFlag=0;
                            	  fetchFlag=0;
                                intfuDivHalt=1;
                                System.out.println("INTFU:\t I("+count+")"+FUInstName+" "+FUDestReg+" "+FUSource1+" Stalled");
                            }
                            break;
            case "BZ":      if(zeroFlag==1){
                            literal=Integer.parseInt(FUDestReg);
                            System.out.println("inst address " +FUInstAdd);
                            add1 = FUInstAdd + literal;
                            System.out.println("Jump address "+add1);
                            lineNum =(add1-4000)/4;
                           // lineNum = lineNum-1;
                            count = lineNum;
                            
                            System.out.println("line number value"+count);
                            branchFlag=1;
                            IntegerFUFlag=1;
                            decodeFlag=0;
                            fetchFlag=0;}
                            else{
                                IntegerFUFlag=1;
                                decodeFlag=0;
                            }
                            System.out.println("INTFU:\t I("+count+")"+FUInstName+" "+FUDestReg);
                            break;
            case "BNZ":     if(zeroFlag==0){
                            literal=Integer.parseInt(FUDestReg);
                            add1 = FUInstAdd + literal;
                            lineNum =(FUInstAdd-add1)/4;
                            //lineNum = lineNum-1;
                            count = lineNum;
                            branchFlag=1;
                            IntegerFUFlag=1;
                            decodeFlag=0;
                            fetchFlag=0;}
                            else{
                                IntegerFUFlag=1;
                                decodeFlag=0;
                            }
                            System.out.println("INTFU:\t I("+count+")"+FUInstName+" "+FUDestReg);
                            break;
    
            case "JAL":		newJAlAdd=FUInstAdd+4;
            				Isource1 = Integer.parseInt(FUSource1.replaceAll("[^0-9]", "")); 
            				Isource2 = Integer.parseInt(FUSource2.replaceAll("[^0-9]", ""));
            				Idest = Integer.parseInt(FUDestReg.replaceAll("[^0-9]", ""));
							regStatus[Idest]="INVALID";
            				jumpAddress1 = registers[Isource1] + Isource2;
            			//	System.out.println("JUMPaddress "+jumpAddress1);
            				lineNum=(jumpAddress1-4000)/4;
            				
            				count=lineNum;
            				//System.out.println("Counter value changed to "+count);
            			//	System.out.println("line number: "+lineNum);
            				if(isMulBusy==0 && isDivBusy==0){
            					branchFlag=1;
            					IntegerFUFlag=1;
            					decodeFlag=0;
            					intfuMulHalt=0;
            					fetchFlag=0;
            					System.out.println("INTFU:\t I("+count+")"+FUInstName+" "+FUDestReg+" "+FUSource1+" "+FUSource2);
            				}
            				else if(isMulBusy==1){
            					decodeFlag=0;
            					fetchFlag=0;
            					intfuMulHalt=1;
            					System.out.println("INTFU:\t I("+count+")"+FUInstName+" "+FUDestReg+" "+FUSource1+" "+FUSource2+" Stalled");
            				}
            				else if(isDivBusy==1) {
            					decodeFlag=0;
            					fetchFlag=0;
            					intfuDivHalt=1;
            					System.out.println("INTFU:\t I("+count+")"+FUInstName+" "+FUDestReg+" "+FUSource1+" "+FUSource2+" Stalled");
            				}
            				break;

            
                
                                
        //System.out.println("FU done");
    }
         ;
}else if(decodeFlag==0 && IntegerFUFlag==0 && intfuMulHalt==1 && intfuDivHalt==0){
   
        switch(FUInstName){
        	case "JAL":		/*newJAlAdd=FUInstAdd+4;
							Isource1 = Integer.parseInt(FUDestReg.replaceAll("[^0-9]", "")); 
							Isource2 = Integer.parseInt(FUSource1.replaceAll("[^0-9]", ""));
							Idest = Integer.parseInt(FUSource1.replaceAll("[^0-9]", ""));
							regStatus[Idest]="INVALID";
							jumpAddress1 = registers[Isource1] + Isource2;
							lineNum=(jumpAddress1-4000)/4;
			
							count=lineNum;
							System.out.println("Counter value changed to "+count);
							System.out.println("line number: "+lineNum);*/
							if(isMulBusy==0 && isDivBusy==0){
            					branchFlag=1;
            					IntegerFUFlag=1;
            					decodeFlag=0;
            					intfuMulHalt=0;
            					
            					System.out.println("INTFU:\t I("+count+")"+FUInstName+" "+FUDestReg+" "+FUSource1+" "+FUSource2);
            				}
            				else if(isMulBusy==1){
            					decodeFlag=0;
            					fetchFlag=0;
            					intfuMulHalt=1;
            					System.out.println("INTFU:\t I("+count+")"+FUInstName+" "+FUDestReg+" "+FUSource1+" "+FUSource2+" Stalled");
            				}
            				else if(isDivBusy==1) {
            					decodeFlag=0;
            					fetchFlag=0;
            					intfuDivHalt=1;
            					System.out.println("INTFU:\t I("+count+")"+FUInstName+" "+FUDestReg+" "+FUSource1+" "+FUSource2+" Stalled");
            				}
							break;
            case "MOVC":    Idest = Integer.parseInt(FUDestReg.replaceAll("[^0-9]", ""));
                            regStatus[Idest]="INVALID";
                            FUSource1=FUSource1;
                            if(isMulBusy==0 && isDivBusy==0){
                                decodeFlag=0;
                                intfuMulHalt=0;
                                intfuDivHalt=0;
                                IntegerFUFlag=1;
                                 System.out.println("INTFU:\t I("+count+")" + FUInstName + "  " + FUDestReg + "  " + FUSource1);
                            }
                            else if(isMulBusy==1){
                   
                                decodeFlag=0;
                                IntegerFUFlag=0;
                                intfuMulHalt=1;
                                System.out.println("INTFU:\t I("+count+")" + FUInstName + "  " + FUDestReg + "  " + FUSource1+" Stalled");
                            }
                            else if(isDivBusy==1) {
                            	 decodeFlag=0;
                                 IntegerFUFlag=0;
                                 intfuDivHalt=1;
                                 System.out.println("INTFU:\t I("+count+")" + FUInstName + "  " + FUDestReg + "  " + FUSource1+" Stalled");
                            }
                           
                            break;
            case "ADD":     answer = registers[temp1] + registers[temp2];
                            Idest = Integer.parseInt(FUDestReg.replaceAll("[^0-9]", ""));
                            if(answer==0)
                                zeroFlag=1;
                            else
                                zeroFlag=0;
                            regStatus[Idest]="INVALID";
                            if(isMulBusy==0 && isDivBusy==0){
                                decodeFlag=0;
                                intfuMulHalt=0;
                                IntegerFUFlag=1;
                                System.out.println("INTFU:\t I("+count+")"+FUInstName+" "+FUDestReg+" "+FUSource1+" "+FUSource2);
                            }else if(isMulBusy==1){
                     
                                decodeFlag=0;
                                IntegerFUFlag=0;
                                intfuMulHalt=1;
                                System.out.println("INTFU:\t I("+count+")" + FUInstName + "  " + FUDestReg + "  " + FUSource1+" "+FUSource2+" Stalled");
                            }
                            else if(isDivBusy==1){
                                decodeFlag=0;
                                IntegerFUFlag=0;
                                intfuDivHalt=1;
                                System.out.println("INTFU:\t I("+count+")"+FUInstName+" "+FUDestReg+" "+FUSource1+" "+FUSource2+" Stalled");
                            }
                            
                            break;
            case "SUB":     Idest = Integer.parseInt(FUDestReg.replaceAll("[^0-9]", ""));
                            regStatus[Idest]="INVALID";
                            answer = registers[temp1]-registers[temp2];
                            
                            if(isMulBusy==0 && isDivBusy==0){
                                if(answer == 0)
                                    zeroFlag = 1;
                                else
                                    zeroFlag = 0;
                                decodeFlag=0;
                                IntegerFUFlag=1;
                                intfuMulHalt=0;
                                System.out.println("INTFU:\t I("+count+")"+FUInstName+" "+FUDestReg+" "+FUSource1+" "+FUSource2);
                            }else if(isMulBusy==1){
                            	
                                decodeFlag=0;
                                IntegerFUFlag=0;
                                intfuMulHalt=1;
                                System.out.println("INTFU:\t I("+count+")" + FUInstName + "  " + FUDestReg + "  " + FUSource1+" "+FUSource2+" Stalled");
                            }
                            else if(isDivBusy==1){
                                decodeFlag=0;
                                IntegerFUFlag=0;
                                intfuDivHalt=1;
                                 System.out.println("INTFU:\t I("+count+")"+FUInstName+" "+FUDestReg+" "+FUSource1+" "+FUSource2+" Stalled");
                            }
                            break;
            case "EXOR":
            case "EX-OR":   Idest = Integer.parseInt(FUDestReg.replaceAll("[^0-9]", ""));
                            regStatus[Idest]="INVALID";
                            answer = registers[temp1]^registers[temp2];
                           
                            if(isMulBusy==0 && isDivBusy==0){
                                if(answer == 0)
                                    zeroFlag = 1;
                                else
                                    zeroFlag = 0;
                                decodeFlag=0;
                                IntegerFUFlag=1;
                                intfuMulHalt=0;
                                System.out.println("INTFU:\t I("+count+")"+FUInstName+" "+FUDestReg+" "+FUSource1+" "+FUSource2);
                            }else if(isMulBusy==1){
                            	
                                decodeFlag=0;
                                IntegerFUFlag=0;
                                intfuMulHalt=1;
                                System.out.println("INTFU:\t I("+count+")" + FUInstName + "  " + FUDestReg + "  " + FUSource1+" "+FUSource2+" Stalled");
                            }
                            else if(isDivBusy==1){
                                decodeFlag=0;
                                IntegerFUFlag=0;
                                intfuDivHalt=1;
                                 System.out.println("INTFU:\t I("+count+")"+FUInstName+" "+FUDestReg+" "+FUSource1+" "+FUSource2+" Stalled");
                            }
                            break;
            case "OR":      Idest = Integer.parseInt(FUDestReg.replaceAll("[^0-9]", ""));
                            regStatus[Idest]="INVALID";
                            answer = registers[temp1] | registers[temp2];
                            if(isMulBusy==0 && isDivBusy==0){
                                if(answer == 0)
                                    zeroFlag = 1;
                                else
                                    zeroFlag = 0;
                                decodeFlag=0;
                                IntegerFUFlag=1;
                                intfuMulHalt=0;
                                System.out.println("INTFU:\t I("+count+")"+FUInstName+" "+FUDestReg+" "+FUSource1+" "+FUSource2);
                            }else if(isMulBusy==1){
                            	
                                decodeFlag=0;
                                IntegerFUFlag=0;
                                intfuMulHalt=1;
                                System.out.println("INTFU:\t I("+count+")" + FUInstName + "  " + FUDestReg + "  " + FUSource1+" "+FUSource2+" Stalled");
                            }
                            else if(isDivBusy==1){
                                decodeFlag=0;
                                IntegerFUFlag=0;
                                intfuDivHalt=1;
                                 System.out.println("INTFU:\t I("+count+")"+FUInstName+" "+FUDestReg+" "+FUSource1+" "+FUSource2+" Stalled");
                            }
                            break;
            case "AND":     Idest = Integer.parseInt(FUDestReg.replaceAll("[^0-9]", ""));
                            regStatus[Idest]="INVALID";
                            answer = registers[temp1] & registers[temp2];
                            if(isMulBusy==0 && isDivBusy==0){
                                if(answer == 0)
                                    zeroFlag = 1;
                                else
                                    zeroFlag = 0;
                                decodeFlag=0;
                                IntegerFUFlag=1;
                                intfuMulHalt=0;
                                System.out.println("INTFU:\t I("+count+")"+FUInstName+" "+FUDestReg+" "+FUSource1+" "+FUSource2);
                            }else if(isMulBusy==1){
                            	
                                decodeFlag=0;
                                IntegerFUFlag=0;
                                intfuMulHalt=1;
                                System.out.println("INTFU:\t I("+count+")" + FUInstName + "  " + FUDestReg + "  " + FUSource1+" "+FUSource2+" Stalled");
                            }
                            else if(isDivBusy==1){
                                decodeFlag=0;
                                IntegerFUFlag=0;
                                intfuDivHalt=1;
                                 System.out.println("INTFU:\t I("+count+")"+FUInstName+" "+FUDestReg+" "+FUSource1+" "+FUSource2+" Stalled");
                            }
                            break;
            case "LOAD":    Idest = Integer.parseInt(FUDestReg.replaceAll("[^0-9]", ""));
                            regStatus[Idest]="INVALID";
                            answer = registers[Isource1]+Isource2;
                            if(isMulBusy==0 && isDivBusy==0){
                                if(answer == 0)
                                    zeroFlag = 1;
                                else
                                    zeroFlag = 0;
                                decodeFlag=0;
                                IntegerFUFlag=1;
                                intfuMulHalt=0;
                                System.out.println("INTFU:\t I("+count+")"+FUInstName+" "+FUDestReg+" "+FUSource1+" "+FUSource2);
                            }else if(isMulBusy==1){
                            	
                                decodeFlag=0;
                                IntegerFUFlag=0;
                                intfuMulHalt=1;
                                System.out.println("INTFU:\t I("+count+")" + FUInstName + "  " + FUDestReg + "  " + FUSource1+" "+FUSource2+" Stalled");
                            }
                            else if(isDivBusy==1){
                                decodeFlag=0;
                                IntegerFUFlag=0;
                                intfuDivHalt=1;
                                 System.out.println("INTFU:\t I("+count+")"+FUInstName+" "+FUDestReg+" "+FUSource1+" "+FUSource2+" Stalled");
                            }
                            break;
            case "STORE":   Idest = Integer.parseInt(FUDestReg.replaceAll("[^0-9]", ""));
                            regStatus[Isource1] = "INVALID";  
                            regStatus[Idest]="INVALID";
                            answer = registers[Isource1]+Isource2;
                            if(isMulBusy==0 && isDivBusy==0){
                                if(answer == 0)
                                    zeroFlag = 1;
                                else
                                    zeroFlag = 0;
                                decodeFlag=0;
                                IntegerFUFlag=1;
                                intfuMulHalt=0;
                                System.out.println("INTFU:\t I("+count+")"+FUInstName+" "+FUDestReg+" "+FUSource1+" "+FUSource2);
                            }else if(isMulBusy==1){
                            	
                                decodeFlag=0;
                                IntegerFUFlag=0;
                                intfuMulHalt=1;
                                System.out.println("INTFU:\t I("+count+")" + FUInstName + "  " + FUDestReg + "  " + FUSource1+" "+FUSource2+" Stalled");
                            }
                            else if(isDivBusy==1){
                                decodeFlag=0;
                                IntegerFUFlag=0;
                                intfuDivHalt=1;
                                 System.out.println("INTFU:\t I("+count+")"+FUInstName+" "+FUDestReg+" "+FUSource1+" "+FUSource2+" Stalled");
                            }
                            break;
            case "JUMP":    Isource1 = Integer.parseInt(FUDestReg.replaceAll("[^0-9]", "")); 
                            Isource2 = Integer.parseInt(FUSource1.replaceAll("[^0-9]", "")); 
                            jumpAddress1 = registers[Isource1] + Isource2;
                            lineNum=(jumpAddress1-4000)/4;
                           
                            count=lineNum;
                          //  System.out.println("Counter value changed to "+count);
                            //System.out.println("line number: "+lineNum);
                            if(isMulBusy==0 && isDivBusy==0){
                                branchFlag=1;
                                IntegerFUFlag=1;
                                decodeFlag=0;
                                intfuMulHalt=0;
                                fetchFlag=0;
                                System.out.println("INTFU:\t I("+count+")"+FUInstName+" "+FUDestReg+" "+FUSource1);
                                }
                                else if(isMulBusy==1){
                                    decodeFlag=0;
                                    fetchFlag=0;
                                    intfuMulHalt=1;
                                    System.out.println("INTFU:\t I("+count+")"+FUInstName+" "+FUDestReg+" "+FUSource1+" Stalled");
                                }
                                else if(isDivBusy==1) {
                                	decodeFlag=0;
                                	  fetchFlag=0;
                                    intfuDivHalt=1;
                                    System.out.println("INTFU:\t I("+count+")"+FUInstName+" "+FUDestReg+" "+FUSource1+" Stalled");
                                }
                            break;
            case "BZ":      if(zeroFlag==1){
                            literal=Integer.parseInt(FUDestReg);
                            add1 = FUInstAdd + literal;
                            lineNum =(add1-FUInstAdd)/4;
                            lineNum = lineNum-1;
                            count = lineNum;
                            branchFlag=1;
                            IntegerFUFlag=1;
                            decodeFlag=0;
                            fetchFlag=0;}
                            else{
                                IntegerFUFlag=1;
                                decodeFlag=0;
                         
                            }
                            System.out.println("INTFU:\t I("+count+")"+FUInstName+" "+FUDestReg);
                            break;
            case "BNZ":     if(zeroFlag==0){
                            literal=Integer.parseInt(FUDestReg);
                            add1 = FUInstAdd + literal;
                            lineNum =(add1-FUInstAdd)/4;
                            lineNum = lineNum-1;
                            count = lineNum;
                            branchFlag=1;
                            IntegerFUFlag=1;
                            decodeFlag=0;
                            fetchFlag=0;}
                            else{
                                IntegerFUFlag=1;
                                decodeFlag=0;
                            
                            }
                            System.out.println("INTFU:\t I("+count+")"+FUInstName+" "+FUDestReg);
                            break;
                            
      
                
                                
        //System.out.println("FU done");
}}
else if(decodeFlag==0 && IntegerFUFlag==0 && intfuDivHalt==1 && intfuMulHalt==0){
	   
    switch(FUInstName){
    case "JAL":		newJAlAdd=FUInstAdd+4;
					Isource1 = Integer.parseInt(FUDestReg.replaceAll("[^0-9]", "")); 
					Isource2 = Integer.parseInt(FUSource1.replaceAll("[^0-9]", ""));
					Idest = Integer.parseInt(FUSource1.replaceAll("[^0-9]", ""));
					regStatus[Idest]="INVALID";
					jumpAddress1 = registers[Isource1] + Isource2;
					lineNum=(jumpAddress1-4000)/4;
				
					count=lineNum;
					System.out.println("Counter value changed to "+count);
					System.out.println("line number: "+lineNum);
					if(isMulBusy==0 && isDivBusy==0){
						branchFlag=1;
						IntegerFUFlag=1;
						decodeFlag=0;
						intfuMulHalt=0;
						fetchFlag=0;
						System.out.println("INTFU:\t I("+count+")"+FUInstName+" "+FUDestReg+" "+FUSource1+" "+FUSource2);
					}
					else if(isMulBusy==1){
						decodeFlag=0;
						fetchFlag=0;
						intfuMulHalt=1;
						System.out.println("INTFU:\t I("+count+")"+FUInstName+" "+FUDestReg+" "+FUSource1+" "+FUSource2+" Stalled");
					}
					else if(isDivBusy==1) {
						decodeFlag=0;
						fetchFlag=0;
						intfuDivHalt=1;
						System.out.println("INTFU:\t I("+count+")"+FUInstName+" "+FUDestReg+" "+FUSource1+" "+FUSource2+" Stalled");
					}
					break;
    case "MOVC":    Idest = Integer.parseInt(FUDestReg.replaceAll("[^0-9]", ""));
				    regStatus[Idest]="INVALID";
				    FUSource1=FUSource1;
				    if(isMulBusy==0 && isDivBusy==0){
				        decodeFlag=0;
				        intfuMulHalt=0;
				        intfuDivHalt=0;
				        IntegerFUFlag=1;
				         System.out.println("INTFU:\t I("+count+")" + FUInstName + "  " + FUDestReg + "  " + FUSource1);
				    }
				    else if(isMulBusy==1){
				
				        decodeFlag=0;
				        IntegerFUFlag=0;
				        intfuMulHalt=1;
				        System.out.println("INTFU:\t I("+count+")" + FUInstName + "  " + FUDestReg + "  " + FUSource1+" Stalled");
				    }
				    else if(isDivBusy==1) {
				    	 decodeFlag=0;
				         IntegerFUFlag=0;
				         intfuDivHalt=1;
				         System.out.println("INTFU:\t I("+count+")" + FUInstName + "  " + FUDestReg + "  " + FUSource1+" Stalled");
				    }
				   
				    break;
    case "ADD":     answer = registers[temp1] + registers[temp2];
				    Idest = Integer.parseInt(FUDestReg.replaceAll("[^0-9]", ""));
				    if(answer==0)
				        zeroFlag=1;
				    else
				        zeroFlag=0;
				    regStatus[Idest]="INVALID";
				    if(isMulBusy==0 && isDivBusy==0){
				        decodeFlag=0;
				        intfuMulHalt=0;
				        IntegerFUFlag=1;
				        System.out.println("INTFU:\t I("+count+")"+FUInstName+" "+FUDestReg+" "+FUSource1+" "+FUSource2);
				    }else if(isMulBusy==1){
				
				        decodeFlag=0;
				        IntegerFUFlag=0;
				        intfuMulHalt=1;
				        System.out.println("INTFU:\t I("+count+")" + FUInstName + "  " + FUDestReg + "  " + FUSource1+" "+FUSource2+" Stalled");
				    }
				    else if(isDivBusy==1){
				        decodeFlag=0;
				        IntegerFUFlag=0;
				        intfuDivHalt=1;
				        System.out.println("INTFU:\t I("+count+")"+FUInstName+" "+FUDestReg+" "+FUSource1+" "+FUSource2+" Stalled");
				    }
				    
				    break;
    case "SUB":     Idest = Integer.parseInt(FUDestReg.replaceAll("[^0-9]", ""));
				    regStatus[Idest]="INVALID";
				    answer = registers[temp1]-registers[temp2];
				    
				    if(isMulBusy==0 && isDivBusy==0){
				        if(answer == 0)
				            zeroFlag = 1;
				        else
				            zeroFlag = 0;
				        decodeFlag=0;
				        IntegerFUFlag=1;
				        intfuMulHalt=0;
				        System.out.println("INTFU:\t I("+count+")"+FUInstName+" "+FUDestReg+" "+FUSource1+" "+FUSource2);
				    }else if(isMulBusy==1){
				    	
				        decodeFlag=0;
				        IntegerFUFlag=0;
				        intfuMulHalt=1;
				        System.out.println("INTFU:\t I("+count+")" + FUInstName + "  " + FUDestReg + "  " + FUSource1+" "+FUSource2+" Stalled");
				    }
				    else if(isDivBusy==1){
				        decodeFlag=0;
				        IntegerFUFlag=0;
				        intfuDivHalt=1;
				         System.out.println("INTFU:\t I("+count+")"+FUInstName+" "+FUDestReg+" "+FUSource1+" "+FUSource2+" Stalled");
				    }
				    break;
	case "EXOR":
	case "EX-OR":   Idest = Integer.parseInt(FUDestReg.replaceAll("[^0-9]", ""));
				    regStatus[Idest]="INVALID";
				    answer = registers[temp1]^registers[temp2];
				   
				    if(isMulBusy==0 && isDivBusy==0){
				        if(answer == 0)
				            zeroFlag = 1;
				        else
				            zeroFlag = 0;
				        decodeFlag=0;
				        IntegerFUFlag=1;
				        intfuMulHalt=0;
				        System.out.println("INTFU:\t I("+count+")"+FUInstName+" "+FUDestReg+" "+FUSource1+" "+FUSource2);
				    }else if(isMulBusy==1){
				    	
				        decodeFlag=0;
				        IntegerFUFlag=0;
				        intfuMulHalt=1;
				        System.out.println("INTFU:\t I("+count+")" + FUInstName + "  " + FUDestReg + "  " + FUSource1+" "+FUSource2+" Stalled");
				    }
				    else if(isDivBusy==1){
				        decodeFlag=0;
				        IntegerFUFlag=0;
				        intfuDivHalt=1;
				         System.out.println("INTFU:\t I("+count+")"+FUInstName+" "+FUDestReg+" "+FUSource1+" "+FUSource2+" Stalled");
				    }
				    break;
	case "OR":      Idest = Integer.parseInt(FUDestReg.replaceAll("[^0-9]", ""));
				    regStatus[Idest]="INVALID";
				    answer = registers[temp1] | registers[temp2];
				    if(isMulBusy==0 && isDivBusy==0){
				        if(answer == 0)
				            zeroFlag = 1;
				        else
				            zeroFlag = 0;
				        decodeFlag=0;
				        IntegerFUFlag=1;
				        intfuMulHalt=0;
				        System.out.println("INTFU:\t I("+count+")"+FUInstName+" "+FUDestReg+" "+FUSource1+" "+FUSource2);
				    }else if(isMulBusy==1){
				    	
				        decodeFlag=0;
				        IntegerFUFlag=0;
				        intfuMulHalt=1;
				        System.out.println("INTFU:\t I("+count+")" + FUInstName + "  " + FUDestReg + "  " + FUSource1+" "+FUSource2+" Stalled");
				    }
				    else if(isDivBusy==1){
				        decodeFlag=0;
				        IntegerFUFlag=0;
				        intfuDivHalt=1;
				         System.out.println("INTFU:\t I("+count+")"+FUInstName+" "+FUDestReg+" "+FUSource1+" "+FUSource2+" Stalled");
				    }
				    break;
	case "AND":     Idest = Integer.parseInt(FUDestReg.replaceAll("[^0-9]", ""));
				    regStatus[Idest]="INVALID";
				    answer = registers[temp1] & registers[temp2];
				    if(isMulBusy==0 && isDivBusy==0){
				        if(answer == 0)
				            zeroFlag = 1;
				        else
				            zeroFlag = 0;
				        decodeFlag=0;
				        IntegerFUFlag=1;
				        intfuMulHalt=0;
				        System.out.println("INTFU:\t I("+count+")"+FUInstName+" "+FUDestReg+" "+FUSource1+" "+FUSource2);
				    }else if(isMulBusy==1){
				    	
				        decodeFlag=0;
				        IntegerFUFlag=0;
				        intfuMulHalt=1;
				        System.out.println("INTFU:\t I("+count+")" + FUInstName + "  " + FUDestReg + "  " + FUSource1+" "+FUSource2+" Stalled");
				    }
				    else if(isDivBusy==1){
				        decodeFlag=0;
				        IntegerFUFlag=0;
				        intfuDivHalt=1;
				         System.out.println("INTFU:\t I("+count+")"+FUInstName+" "+FUDestReg+" "+FUSource1+" "+FUSource2+" Stalled");
				    }
				    break;
	case "LOAD":    Idest = Integer.parseInt(FUDestReg.replaceAll("[^0-9]", ""));
				    regStatus[Idest]="INVALID";
				    answer = registers[Isource1]+Isource2;
				    if(isMulBusy==0 && isDivBusy==0){
				        if(answer == 0)
				            zeroFlag = 1;
				        else
				            zeroFlag = 0;
				        decodeFlag=0;
				        IntegerFUFlag=1;
				        intfuMulHalt=0;
				        System.out.println("INTFU:\t I("+count+")"+FUInstName+" "+FUDestReg+" "+FUSource1+" "+FUSource2);
				    }else if(isMulBusy==1){
				    	
				        decodeFlag=0;
				        IntegerFUFlag=0;
				        intfuMulHalt=1;
				        System.out.println("INTFU:\t I("+count+")" + FUInstName + "  " + FUDestReg + "  " + FUSource1+" "+FUSource2+" Stalled");
				    }
				    else if(isDivBusy==1){
				        decodeFlag=0;
				        IntegerFUFlag=0;
				        intfuDivHalt=1;
				         System.out.println("INTFU:\t I("+count+")"+FUInstName+" "+FUDestReg+" "+FUSource1+" "+FUSource2+" Stalled");
				    }
				    break;
	case "STORE":   Idest = Integer.parseInt(FUDestReg.replaceAll("[^0-9]", ""));
				    regStatus[Isource1] = "INVALID";  
				    regStatus[Idest]="INVALID";
				    answer = registers[Isource1]+Isource2;
				    if(isMulBusy==0 && isDivBusy==0){
				        if(answer == 0)
				            zeroFlag = 1;
				        else
				            zeroFlag = 0;
				        decodeFlag=0;
				        IntegerFUFlag=1;
				        intfuMulHalt=0;
				        System.out.println("INTFU:\t I("+count+")"+FUInstName+" "+FUDestReg+" "+FUSource1+" "+FUSource2);
				    }else if(isMulBusy==1){
				    	
				        decodeFlag=0;
				        IntegerFUFlag=0;
				        intfuMulHalt=1;
				        System.out.println("INTFU:\t I("+count+")" + FUInstName + "  " + FUDestReg + "  " + FUSource1+" "+FUSource2+" Stalled");
				    }
				    else if(isDivBusy==1){
				        decodeFlag=0;
				        IntegerFUFlag=0;
				        intfuDivHalt=1;
				         System.out.println("INTFU:\t I("+count+")"+FUInstName+" "+FUDestReg+" "+FUSource1+" "+FUSource2+" Stalled");
				    }
				    break;
	case "JUMP":    Isource1 = Integer.parseInt(FUDestReg.replaceAll("[^0-9]", "")); 
				    Isource2 = Integer.parseInt(FUSource1.replaceAll("[^0-9]", "")); 
				    jumpAddress1 = registers[Isource1] + Isource2;
				    lineNum=(jumpAddress1-4000)/4;
				  
				    count=lineNum;
				  //  System.out.println("Counter value changed to "+count);
				    //System.out.println("line number: "+lineNum);
				    if(isMulBusy==0 && isDivBusy==0){
				        branchFlag=1;
				        IntegerFUFlag=1;
				        decodeFlag=0;
				        intfuMulHalt=0;
				        fetchFlag=0;
				        System.out.println("INTFU:\t I("+count+")"+FUInstName+" "+FUDestReg+" "+FUSource1);
				        }
				        else if(isMulBusy==1){
				            decodeFlag=0;
				            fetchFlag=0;
				            intfuMulHalt=1;
				            System.out.println("INTFU:\t I("+count+")"+FUInstName+" "+FUDestReg+" "+FUSource1+" Stalled");
				        }
				        else if(isDivBusy==1) {
				        	decodeFlag=0;
				        	  fetchFlag=0;
				            intfuDivHalt=1;
				            System.out.println("INTFU:\t I("+count+")"+FUInstName+" "+FUDestReg+" "+FUSource1+" Stalled");
				        }
				    break;
	case "BZ":      if(zeroFlag==1){
					    literal=Integer.parseInt(FUDestReg);
					    add1 = FUInstAdd + literal;
					    lineNum =(add1-FUInstAdd)/4;
					    lineNum = lineNum-1;
					    count = lineNum;
					    branchFlag=1;
					    IntegerFUFlag=1;
					    decodeFlag=0;
					    fetchFlag=0;}
					    else{
					        IntegerFUFlag=1;
					        decodeFlag=0;
					 
					    }
					    System.out.println("INTFU:\t I("+count+")"+FUInstName+" "+FUDestReg);
					    break;
	case "BNZ":     if(zeroFlag==0){
					    literal=Integer.parseInt(FUDestReg);
					    add1 = FUInstAdd + literal;
					    lineNum =(add1-FUInstAdd)/4;
					    lineNum = lineNum-1;
					    count = lineNum;
					    branchFlag=1;
					    IntegerFUFlag=1;
					    decodeFlag=0;
					    fetchFlag=0;}
					    else{
					        IntegerFUFlag=1;
					        decodeFlag=0;
					    
					    }
					    System.out.println("INTFU:\t I("+count+")"+FUInstName+" "+FUDestReg);
					    break;
    


					    }
}
   else{
                                IntegerFUFlag=0;
                                System.out.println("INTFU:\t Empty");
    }

    }
}
