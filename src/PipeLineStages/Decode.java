/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PipeLineStages;

import static PipeLineStages.Fetch.*;
import static PipeLineStages.IntegerFU.branchFlag;
import simulator.Simulator;

/**
 *
 * @author pooja
 */
public class Decode extends Simulator {
    public static String decodeInstName;
    public static String decodeDestReg;
    public static String decodeSource1;
    public static String decodeSource2;
    public static int decodeInstNum;
    public static  int decodeInstAdd;
    
    public static int result;
    public static int temp1;
    public static int temp2;
    public static int flagBit;
    public static int Isource1;
    public static int Isource2;
    public static int literal;
    public static int Idest;
    
    public static int msource1;
    public static int msource2;
    public static int goToMul=0;
    
    public static int goToDiv=0;
    public static int dsource1=0;
    public static int dsource2=0;
    
     
      
    public static void Decode() {
        if(flushFlag==0 ){
        //	System.out.println("fetchFlag "+fetchFlag+" decodeFlag "+decodeFlag+" isHalt "+isHalt+" stall "+stall+" decodeStall "+decodeStall);
        	
        
        if(fetchFlag == 1 && decodeFlag == 0 && isHalt==0){  
     
            decodeInstAdd = fetchInstAdd;
            decodeInstNum = fetchInstNum;
            decodeInstName = fetchInstName;
            decodeDestReg = fetchDestReg;
            decodeSource1 = fetchSource1;
            decodeSource2 = fetchSource2;
            
            fetchInstName = "";
            fetchDestReg = "";
            fetchSource1 = "";
            fetchSource2 = "";
            
          
            if(decodeInstName!=null){
                
            
            switch(decodeInstName){
                case "MOVC": 
                                Idest = Integer.parseInt(decodeDestReg.replaceAll("[^0-9]", ""));
                               //System.out.println("Destn of movc"+Idest);
                                if(regStatus[Idest]=="VALID"){
                                    System.out.println("DRF:\t I("+decodeInstNum+")" + decodeInstName + "  " + decodeDestReg + "  " + decodeSource1);
                                    if(intfuMulHalt==1 || intfuDivHalt==1) {
                                    	fetchFlag=0;
                                    	decodeFlag=0;
                                    	stall=0;
                                    	decodeStall=1;
                                    	stallFetchForDecode=1;
                                    }
                                    else {
                                    	fetchFlag=0;
                                    	decodeFlag=1;
                                    	stall=0;
                                    	decodeStall=0;
                                    	stallFetchForDecode=0;
                                    }}
                                    else{ 
                                           fetchFlag=0;
                                            decodeFlag=0;
                                            stall = 1;
                                            System.out.println("DRF:\t I("+decodeInstNum+")" + decodeInstName + "  " + decodeDestReg + "  " + decodeSource1+" Stalled");
                                            }
                                break;
                              
                            
                case "ADD":
                case "SUB":
                case "OR":
                case "EX-OR":
                case "EXOR":
                case "AND":    
                                Idest = Integer.parseInt(decodeDestReg.replaceAll("[^0-9]", ""));
                                Isource1 = Integer.parseInt(decodeSource1.replaceAll("[^0-9]", ""));
                                Isource2 = Integer.parseInt(decodeSource2.replaceAll("[^0-9]", ""));
                                if(regStatus[Isource1]=="VALID" && regStatus[Isource2]=="VALID"){  
                                
                                	
                                    result =Idest;
                                    temp1 = Isource1;
                                    temp2 = Isource2;
                                  //  decodStall = 0;
                                    System.out.println("DRF:\t I("+decodeInstNum+")" + decodeInstName + "  " + decodeDestReg + "  " + decodeSource1 + "  " + decodeSource2);
                                    if(intfuMulHalt==1 || intfuDivHalt==1) {
                                        fetchFlag=0;
                                        decodeFlag=0;
                                        stall=0;
                                        decodeStall=1;
                                        stallFetchForDecode=1;
                            //        	System.out.println("in add fetchFlag "+fetchFlag+" decodeFlag "+decodeFlag+" isHalt "+isHalt+" stall "+stall+" decodeStall "+decodeStall);

                                        }
                                        else {
                                        	fetchFlag=0;
                                        	decodeFlag=1;
                                        	stall=0;
                                        	decodeStall=0;
                                        	stallFetchForDecode=0;
                                        }
                                
                                }
                                else{
                                    fetchFlag=0;
                                    decodeFlag=0;
                                    stall = 1;
                                    System.out.println("DRF:\t I("+decodeInstNum+")" + decodeInstName + "  " + decodeDestReg + "  " + decodeSource1 + "  " + decodeSource2+" Stalled");
                                    
                                }                       
                                 break;
                case "LOAD":    Idest = Integer.parseInt(decodeDestReg.replaceAll("[^0-9]", ""));
                                Isource1 = Integer.parseInt(decodeSource1.replaceAll("[^0-9]", ""));
                                Isource2 = Integer.parseInt(decodeSource2);
                                if(regStatus[Isource1]=="VALID" && regStatus[Isource2]=="VALID"){
                            
                                        result =Idest;
                                        temp1 = Isource1;
                                        temp2 = Isource2;
                                        System.out.println("DRF:\t I("+count+")" + decodeInstName + "  " + decodeDestReg + "  " + decodeSource1 + "  " + decodeSource2);
                                        if(intfuMulHalt==1 || intfuDivHalt==1) {
                                            fetchFlag=0;
                                            decodeFlag=0;
                                            stall=0;
                                            decodeStall=1;
                                            stallFetchForDecode=1;}
                                        else {
                                        fetchFlag=0;
                                        decodeFlag=1;
                                        stall=0;
                                        decodeStall=0;
                                        stallFetchForDecode=0;
                                               
                                }}
                                else {
                                    
                                    fetchFlag=0;
                                    decodeFlag=0;
                                    stall = 1;
                                    System.out.println("DRF:\t I("+count+")" + decodeInstName + "  " + decodeDestReg + "  " + decodeSource1 + "  " + decodeSource2+" Stalled");
                                }
                                break;
                case "STORE":   Idest = Integer.parseInt(decodeDestReg.replaceAll("[^0-9]", ""));
                                Isource1 = Integer.parseInt(decodeSource1.replaceAll("[^0-9]", ""));
                                Isource2 = Integer.parseInt(decodeSource2);
                                if(regStatus[Isource1]== "VALID" && regStatus[Idest]=="VALID"){
                                     
                                    System.out.println("DRF:\t I("+count+")" + decodeInstName + "  " + decodeDestReg + "  " + decodeSource1 + "  " + decodeSource2);
                                    
                                    if(intfuMulHalt==1 || intfuDivHalt==1) {
                                        fetchFlag=0;
                                        decodeFlag=0;
                                        stall=0;
                                        decodeStall=1;
                                        stallFetchForDecode=1;}
                                    else {
                                    fetchFlag=0;
                                    decodeFlag=1;
                                    stall=0;
                                    decodeStall=0;
                                    stallFetchForDecode=0;
                                           
                            }}
                                
                                else{ 
                                    fetchFlag=0;
                                    decodeFlag=0;
                                  
                                    stall = 1;
                                  System.out.println("DRF:\t I("+count+")"+ decodeInstName + "  " + decodeDestReg + "  " + decodeSource1 + "  " + decodeSource2+" Stalled");
                                }
                                break;
                case "MUL":     Idest = Integer.parseInt(decodeDestReg.replaceAll("[^0-9]", ""));
                                msource1 = Integer.parseInt(decodeSource1.replaceAll("[^0-9]", ""));
                                msource2 = Integer.parseInt(decodeSource2.replaceAll("[^0-9]", ""));
                                if(regStatus[msource1]=="VALID" && regStatus[msource2]=="VALID"){
                                   System.out.println("DRF:\t I("+count+")"+ decodeInstName + "  " + decodeDestReg + "  " + decodeSource1 + "  " + decodeSource2); 
                                   if(intfuMulHalt==1 || intfuDivHalt==1) {
                                       fetchFlag=0;
                                       decodeFlag=0;
                                       stall=0;
                                       decodeStall=1;
                                       stallFetchForDecode=1;}
                                   else {
                                   fetchFlag=0;
                                   decodeFlag=1;
                                   stall=0;
                                   goToMul=1;
                                   decodeStall=0;
                                   stallFetchForDecode=0;
                                          
                           }}
                                else{
                                    stall = 1;
                                    fetchFlag=0;
                                    decodeFlag=0;
                                    System.out.println("DRF:\t I("+count+")" + decodeInstName + "  " + decodeDestReg + "  " + decodeSource1 + "  " + decodeSource2+" Stalled"); 
                                    }
                               
                                break;
                case "JUMP":    Idest = Integer.parseInt(decodeDestReg.replaceAll("[^0-9]", ""));
                                if(regStatus[Idest]=="VALID"){
                                	System.out.println("DRF:\t I("+count+")"+ decodeInstName + "  " + decodeDestReg + "  " + decodeSource1 ); 
                                	if(intfuMulHalt==1 || intfuDivHalt==1) {
                                        fetchFlag=0;
                                        decodeFlag=0;
                                        stall=0;
                                        decodeStall=1;
                                        stallFetchForDecode=1;}
                                    else {
                                    fetchFlag=0;
                                    decodeFlag=1;
                                    stall=0;
                                    decodeStall=0;
                                    stallFetchForDecode=0;
                                           
                            }}
                                    
                                else{
                                stall=1;  
                                 fetchFlag=0;
                                    decodeFlag=0;
                                    System.out.println("DRF:\t I("+count+")"+ decodeInstName + "  " + decodeDestReg + "  " + decodeSource1 +" Stalled"); 
                                }
                                
                                break; 
                
                case "HALT":    System.out.println("DRF:\t I("+count+")"+decodeInstName);
                                fetchFlag=0;
                                decodeFlag=1;
                                goToDiv=1;
                                isHalt=1;
                                break;
                case "BZ":
                case "BNZ":     System.out.println("DRF:\t I("+count+")"+decodeInstName+" "+decodeDestReg); 
                                fetchFlag=0;
                                decodeFlag=1;
                                break;
                case "DIV": 	Idest = Integer.parseInt(decodeDestReg.replaceAll("[^0-9]", ""));
                				dsource1 = Integer.parseInt(decodeSource1.replaceAll("[^0-9]", ""));
                				dsource2 = Integer.parseInt(decodeSource2.replaceAll("[^0-9]", ""));
                				if(regStatus[dsource1]=="VALID" && regStatus[dsource2]=="VALID"){
                					System.out.println("DRF:\t I("+count+")" + decodeInstName + "  " + decodeDestReg + "  " + decodeSource1 + "  " + decodeSource2); 
                					   if(intfuMulHalt==1 || intfuDivHalt==1) {
                                           fetchFlag=0;
                                           decodeFlag=0;
                                           stall=0;
                                           decodeStall=1;
                                           stallFetchForDecode=1;}
                                       else {
                                       fetchFlag=0;
                                       decodeFlag=1;
                                       stall=0;
                                       goToDiv=1;
                                       decodeStall=0;
                                       stallFetchForDecode=0;
                                              
                               }}
                				else{
                					stall = 1;
                					fetchFlag=0;
                					decodeFlag=0;
                					System.out.println("DRF:\t I("+count+")" + decodeInstName + "  " + decodeDestReg + "  " + decodeSource1 + "  " + decodeSource2+" Stalled");
                					
                				}
                				break;
                case "JAL":		Idest = Integer.parseInt(decodeDestReg.replaceAll("[^0-9]", ""));
								dsource1 = Integer.parseInt(decodeSource1.replaceAll("[^0-9]", ""));
								if(regStatus[dsource1]=="VALID") {
									System.out.println("DRF:\t I("+count+")" + decodeInstName + "  " + decodeDestReg + "  " + decodeSource1 + "  " + decodeSource2); 
                					
									if(intfuMulHalt==1 || intfuDivHalt==1) {
                                        fetchFlag=0;
                                        decodeFlag=0;
                                        stall=0;
                                        decodeStall=1;
                                        stallFetchForDecode=1;}
                                    else {
                                    fetchFlag=0;
                                    decodeFlag=1;
                                    stall=0;
                                    decodeStall=0;
                                    stallFetchForDecode=0;
                                           
                            }}
                				else{
                					stall = 1;
                					fetchFlag=0;
                					decodeFlag=0;
                					System.out.println("DRF:\t I("+count+")" + decodeInstName + "  " + decodeDestReg + "  " + decodeSource1 + "  " + decodeSource2+" Stalled");
                				
                				}
								
								break;
                    
            }
            
        }
        
    }else if(fetchFlag==0 && decodeFlag == 0 && stall == 1){

    	if(decodeInstName!=null) {
        switch(decodeInstName){
            case "AND":
            case "OR":
            case "SUB":
            case "EX-OR":
            
            case "EXOR":
            case "ADD": Idest = Integer.parseInt(decodeDestReg.replaceAll("[^0-9]", ""));
                                Isource1 = Integer.parseInt(decodeSource1.replaceAll("[^0-9]", ""));
                                Isource2 = Integer.parseInt(decodeSource2.replaceAll("[^0-9]", ""));
                                if(regStatus[Isource1]=="VALID" && regStatus[Isource2]=="VALID"){   
                                    result =Idest;
                                    temp1 = Isource1;
                                    temp2 = Isource2;
                                  //  decodStall = 0;
                                    System.out.println("DRF:\t I("+count+")" + decodeInstName + "  " + decodeDestReg + "  " + decodeSource1 + "  " + decodeSource2);
                                    decodeFlag=1;
                                    fetchFlag=0;
                                    stall=0;}
                                else{
                                    decodeFlag=0;
                                    fetchFlag=0;
                                    stall=1;
                                    System.out.println("DRF:\t I("+count+")" + decodeInstName + "  " + decodeDestReg + "  " + decodeSource1 + "  " + decodeSource2+"  Stalled");
                                }
                                break;
             case "MOVC": 
                          
                                Idest = Integer.parseInt(decodeDestReg.replaceAll("[^0-9]", ""));
                               // System.out.println("Destn of movc"+Idest);
                                if(regStatus[Idest]=="VALID"){
                                    System.out.println("DRF:\t I("+count+")" + decodeInstName + "  " + decodeDestReg + "  " + decodeSource1);
                                    fetchFlag=0;
                                    decodeFlag=1;
                                    stall=0;}
                                    else{ 
                                           
                                            decodeFlag=0;
                                            fetchFlag=0;
                                            stall = 1;
                                            System.out.println("DRF:\t I("+count+")" + decodeInstName + "  " + decodeDestReg + "  " + decodeSource1 +"  Stalled");
                                            }
                                break;
             case "MUL":     Idest = Integer.parseInt(decodeDestReg.replaceAll("[^0-9]", ""));
                                msource1 = Integer.parseInt(decodeSource1.replaceAll("[^0-9]", ""));
                                msource2 = Integer.parseInt(decodeSource2.replaceAll("[^0-9]", ""));
                                if(regStatus[msource1]=="VALID" && regStatus[msource2]=="VALID"){
                                   System.out.println("DRF:\t I("+count+")" + decodeInstName + "  " + decodeDestReg + "  " + decodeSource1 + "  " + decodeSource2); 
                                   fetchFlag=0;
                                   decodeFlag=1;
                                   stall=0;
                                   goToMul = 1;
                                }
                                else{
                                    stall = 1;
                                decodeFlag=0;
                                            fetchFlag=0;
                                            System.out.println("DRF:\t I("+count+")"+ decodeInstName + "  " + decodeDestReg + "  " + decodeSource1 +" "+decodeSource2+" Stalled");
                                }
                                break;
            case "LOAD":    Idest = Integer.parseInt(decodeDestReg.replaceAll("[^0-9]", ""));
                                Isource1 = Integer.parseInt(decodeSource1.replaceAll("[^0-9]", ""));
                                Isource2 = Integer.parseInt(decodeSource2);
                                if(regStatus[Isource1]=="VALID"){   
                                    result =Idest;
                                        temp1 = Isource1;
                                        temp2 = Isource2;
                                        fetchFlag=0;
                                    decodeFlag=1;
                                    stall=0;
                                        System.out.println("DRF:\t I("+count+")"+ decodeInstName + "  " + decodeDestReg + "  " + decodeSource1 + "  " + decodeSource2);
                                }
                                else{ 
                                    decodeFlag=0;
                                            fetchFlag=0;
                                            stall = 1;
                                            System.out.println("DRF\t I("+count+")"+ decodeInstName + "  " + decodeDestReg + "  " + decodeSource1 + "  " + decodeSource2+" Stalled");
                                }
                                break;
             case "STORE":      Idest = Integer.parseInt(decodeDestReg.replaceAll("[^0-9]", ""));
                                Isource1 = Integer.parseInt(decodeSource1.replaceAll("[^0-9]", ""));
                                Isource2 = Integer.parseInt(decodeSource2);
                                if(regStatus[Isource1]== "VALID" && regStatus[Idest]=="VALID"){
                                    System.out.println("DRF:\t I("+count+")" + decodeInstName + "  " + decodeDestReg + "  " + decodeSource1 + "  " + decodeSource2);
                                      fetchFlag=0;
                                    decodeFlag=1;
                                    stall=0;
                                }
                                else{
                                    decodeFlag=0;
                                            fetchFlag=0;
                                            stall = 1;
                                    stall = 1;
                                    System.out.println("DRF:\t I("+count+")" + decodeInstName + "  " + decodeDestReg + "  " + decodeSource1 + "  " + decodeSource2+" Stalled");
                                }
                                break;
            case "JUMP":    	Idest = Integer.parseInt(decodeDestReg.replaceAll("[^0-9]", ""));
                               
                                if(regStatus[Idest]=="VALID"){
                                    fetchFlag=0;
                                    decodeFlag=1;   
                                    stall=0;
                                    System.out.println("DRF:\t I("+count+")"+ decodeInstName + "  " + decodeDestReg + "  " + decodeSource1 ); 
                                }else{
                                fetchFlag=0;
                                decodeFlag=0;
                                stall=1;
                                System.out.println("DRF:\t I("+count+")" + decodeInstName + "  " + decodeDestReg + "  " + decodeSource1 +" Stalled"); }
                                break; 
            case "DIV": 		Idest = Integer.parseInt(decodeDestReg.replaceAll("[^0-9]", ""));
								dsource1 = Integer.parseInt(decodeSource1.replaceAll("[^0-9]", ""));
								dsource2 = Integer.parseInt(decodeSource2.replaceAll("[^0-9]", ""));
								if(regStatus[dsource1]=="VALID" && regStatus[dsource2]=="VALID"){
									System.out.println("DRF:\t I("+count+")" + decodeInstName + "  " + decodeDestReg + "  " + decodeSource1 + "  " + decodeSource2); 
									fetchFlag=0;
									decodeFlag=1;
									stall=0;
									goToDiv = 1;
								}
								else{
									stall = 1;
									fetchFlag=0;
									decodeFlag=0;
									System.out.println("DRF:\t I("+count+")" + decodeInstName + "  " + decodeDestReg + " " + decodeSource1 + "  " + decodeSource2+" Stalled"); 
                                
								}
								break;
            case "JAL":		Idest = Integer.parseInt(decodeDestReg.replaceAll("[^0-9]", ""));
								dsource1 = Integer.parseInt(decodeSource1.replaceAll("[^0-9]", ""));
								if(regStatus[dsource1]=="VALID") {
									System.out.println("DRF:\t I("+count+")" + decodeInstName + " " + decodeDestReg + " " + decodeSource1 + " " + decodeSource2); 
									fetchFlag=0;
									decodeFlag=1;
									stall=0;
								}
								else{
									stall = 1;
									fetchFlag=0;
									decodeFlag=0;
									System.out.println("DRF:\t I("+count+")" + decodeInstName + " " + decodeDestReg + " " + decodeSource1 + " " + decodeSource2+" Stalled");
				
								}
			
								break;
    }
    }
    }
    else if(fetchFlag==0 && decodeFlag==0 && decodeStall==1) {
    	System.out.println("here");
    	if(decodeInstName!=null) {
    		System.out.println("C");
        switch(decodeInstName){
            case "MOVC": 
                            Idest = Integer.parseInt(decodeDestReg.replaceAll("[^0-9]", ""));
                           //System.out.println("Destn of movc"+Idest);
                            if(regStatus[Idest]=="VALID"){
                                System.out.println("DRF:\t I("+count+")" + decodeInstName + "  " + decodeDestReg + "  " + decodeSource1);
                                if(intfuMulHalt==1 || intfuDivHalt==1) {
                                fetchFlag=0;
                                decodeFlag=0;
                                stall=0;
                                decodeStall=1;
                                stallFetchForDecode=1;
                                }
                                else {
                                	fetchFlag=0;
                                	decodeFlag=1;
                                	stall=0;
                                	decodeStall=0;
                                	stallFetchForDecode=0;
                                }}
                                else{ 
                                       fetchFlag=0;
                                        decodeFlag=0;
                                        stall = 1;
                                        System.out.println("DRF:\t I("+count+")" + decodeInstName + "  " + decodeDestReg + "  " + decodeSource1+" Stalled");
                                        }
                            break;
                          
                        
            case "ADD":
            case "SUB":
            case "OR":
            case "EX-OR":
            case "EXOR":
            case "AND":    
                            Idest = Integer.parseInt(decodeDestReg.replaceAll("[^0-9]", ""));
                            Isource1 = Integer.parseInt(decodeSource1.replaceAll("[^0-9]", ""));
                            Isource2 = Integer.parseInt(decodeSource2.replaceAll("[^0-9]", ""));
                            if(regStatus[Isource1]=="VALID" && regStatus[Isource2]=="VALID"){  
                            
                            	
                                result =Idest;
                                temp1 = Isource1;
                                temp2 = Isource2;
                              //  decodStall = 0;
                                System.out.println("DRF:\t I("+count+")" + decodeInstName + "  " + decodeDestReg + "  " + decodeSource1 + "  " + decodeSource2);
                                if(intfuMulHalt==1 || intfuDivHalt==1) {
                                    fetchFlag=0;
                                    decodeFlag=0;
                                    stall=0;
                                    decodeStall=1;
                                    stallFetchForDecode=1;
                                    }
                                    else {
                                    	fetchFlag=0;
                                    	decodeFlag=1;
                                    	stall=0;
                                    	stallFetchForDecode=0;
                                    }
                            
                            }
                            else{
                                fetchFlag=0;
                                decodeFlag=0;
                                stall = 1;
                                System.out.println("DRF:\t I("+count+")" + decodeInstName + "  " + decodeDestReg + "  " + decodeSource1 + "  " + decodeSource2+" Stalled");
                                
                            }                       
                             break;
            case "LOAD":    Idest = Integer.parseInt(decodeDestReg.replaceAll("[^0-9]", ""));
				            Isource1 = Integer.parseInt(decodeSource1.replaceAll("[^0-9]", ""));
				            Isource2 = Integer.parseInt(decodeSource2);
				            if(regStatus[Isource1]=="VALID" && regStatus[Isource2]=="VALID"){
				        
				                    result =Idest;
				                    temp1 = Isource1;
				                    temp2 = Isource2;
				                    System.out.println("DRF:\t I("+count+")" + decodeInstName + "  " + decodeDestReg + "  " + decodeSource1 + "  " + decodeSource2);
				                    if(intfuMulHalt==1 || intfuDivHalt==1) {
				                        fetchFlag=0;
				                        decodeFlag=0;
				                        stall=0;
				                        decodeStall=1;
				                        stallFetchForDecode=1;}
				                    else {
				                    fetchFlag=0;
				                    decodeFlag=1;
				                    stall=0;
				                    decodeStall=0;
				                    stallFetchForDecode=0;
				                           
				            }}
				            else {
				                
				                fetchFlag=0;
				                decodeFlag=0;
				                stall = 1;
				                System.out.println("DRF:\t I("+count+")" + decodeInstName + "  " + decodeDestReg + "  " + decodeSource1 + "  " + decodeSource2+" Stalled");
				            }
				            break;
            case "STORE":   Idest = Integer.parseInt(decodeDestReg.replaceAll("[^0-9]", ""));
				            Isource1 = Integer.parseInt(decodeSource1.replaceAll("[^0-9]", ""));
				            Isource2 = Integer.parseInt(decodeSource2);
				            if(regStatus[Isource1]== "VALID" && regStatus[Idest]=="VALID"){
				                 
				                System.out.println("DRF:\t I("+count+")" + decodeInstName + "  " + decodeDestReg + "  " + decodeSource1 + "  " + decodeSource2);
				                
				                if(intfuMulHalt==1 || intfuDivHalt==1) {
				                    fetchFlag=0;
				                    decodeFlag=0;
				                    stall=0;
				                    decodeStall=1;
				                    stallFetchForDecode=1;}
				                else {
				                fetchFlag=0;
				                decodeFlag=1;
				                stall=0;
				                decodeStall=0;
				                stallFetchForDecode=0;
				                       
				        }}
				            
				            else{ 
				                fetchFlag=0;
				                decodeFlag=0;
				              
				                stall = 1;
				              System.out.println("DRF:\t I("+count+")"+ decodeInstName + "  " + decodeDestReg + "  " + decodeSource1 + "  " + decodeSource2+" Stalled");
				            }
				            break;
            case "MUL":     Idest = Integer.parseInt(decodeDestReg.replaceAll("[^0-9]", ""));
				            msource1 = Integer.parseInt(decodeSource1.replaceAll("[^0-9]", ""));
				            msource2 = Integer.parseInt(decodeSource2.replaceAll("[^0-9]", ""));
				            if(regStatus[msource1]=="VALID" && regStatus[msource2]=="VALID"){
				               System.out.println("DRF:\t I("+count+")"+ decodeInstName + "  " + decodeDestReg + "  " + decodeSource1 + "  " + decodeSource2); 
				               if(intfuMulHalt==1 || intfuDivHalt==1) {
				                   fetchFlag=0;
				                   decodeFlag=0;
				                   stall=0;
				                   decodeStall=1;
				                   stallFetchForDecode=1;}
				               else {
				               fetchFlag=0;
				               decodeFlag=1;
				               stall=0;
				               decodeStall=0;
				               goToMul=1;
				               stallFetchForDecode=0;
				                      
				       }}
				            else{
				                stall = 1;
				                fetchFlag=0;
				                decodeFlag=0;
				                System.out.println("DRF:\t I("+count+")" + decodeInstName + "  " + decodeDestReg + "  " + decodeSource1 + "  " + decodeSource2+" Stalled"); 
				                }
				           
				            break;
            case "JUMP":    Idest = Integer.parseInt(decodeDestReg.replaceAll("[^0-9]", ""));
				            if(regStatus[Idest]=="VALID"){
				            	System.out.println("DRF:\t I("+count+")"+ decodeInstName + "  " + decodeDestReg + "  " + decodeSource1 ); 
				            	if(intfuMulHalt==1 || intfuDivHalt==1) {
				                    fetchFlag=0;
				                    decodeFlag=0;
				                    stall=0;
				                    decodeStall=1;
				                    stallFetchForDecode=1;}
				                else {
				                fetchFlag=0;
				                decodeFlag=1;
				                stall=0;
				                decodeStall=0;
				                stallFetchForDecode=0;
				                       
				        }}
				                
				            else{
				            stall=1;  
				             fetchFlag=0;
				                decodeFlag=0;
				                System.out.println("DRF:\t I("+count+")"+ decodeInstName + "  " + decodeDestReg + "  " + decodeSource1 +" Stalled"); 
				            }
				            
				            break; 

            case "HALT":    System.out.println("DRF:\t I("+count+")"+decodeInstName);
					            fetchFlag=0;
					            decodeFlag=1;
					            goToDiv=1;
					            isHalt=1;
					            break;
            case "BZ":
            case "BNZ":     System.out.println("DRF:\t I("+count+")"+decodeInstName+" "+decodeDestReg); 
				            fetchFlag=0;
				            decodeFlag=1;
				            break;
            case "DIV": 	Idest = Integer.parseInt(decodeDestReg.replaceAll("[^0-9]", ""));
							dsource1 = Integer.parseInt(decodeSource1.replaceAll("[^0-9]", ""));
							dsource2 = Integer.parseInt(decodeSource2.replaceAll("[^0-9]", ""));
							if(regStatus[dsource1]=="VALID" && regStatus[dsource2]=="VALID"){
								System.out.println("DRF:\t I("+count+")" + decodeInstName + "  " + decodeDestReg + "  " + decodeSource1 + "  " + decodeSource2); 
								   if(intfuMulHalt==1 || intfuDivHalt==1) {
				                       fetchFlag=0;
				                       decodeFlag=0;
				                       stall=0;
				                       decodeStall=1;
				                       stallFetchForDecode=1;}
				                   else {
				                   fetchFlag=0;
				                   decodeFlag=1;
				                   stall=0;
				                   goToDiv=1;
				                   decodeStall=0;
				                   stallFetchForDecode=0;
				                          
				           }}
							else{
								stall = 1;
								fetchFlag=0;
								decodeFlag=0;
								System.out.println("DRF:\t I("+count+")" + decodeInstName + "  " + decodeDestReg + "  " + decodeSource1 + "  " + decodeSource2+" Stalled");
								
							}
							break;
            case "JAL":		Idest = Integer.parseInt(decodeDestReg.replaceAll("[^0-9]", ""));
							dsource1 = Integer.parseInt(decodeSource1.replaceAll("[^0-9]", ""));
							if(regStatus[dsource1]=="VALID") {
								System.out.println("DRF:\t I("+count+")" + decodeInstName + "  " + decodeDestReg + "  " + decodeSource1 + "  " + decodeSource2); 
								
								if(intfuMulHalt==1 || intfuDivHalt==1) {
				                    fetchFlag=0;
				                    decodeFlag=0;
				                    stall=0;
				                    decodeStall=1;
				                    stallFetchForDecode=1;}
				                else {
				                fetchFlag=0;
				                decodeFlag=1;
				                stall=0;
				                decodeStall=0;
				                stallFetchForDecode=0;
				                       
				        }}
							else{
								stall = 1;
								fetchFlag=0;
								decodeFlag=0;
								System.out.println("DRF:\t I("+count+")" + decodeInstName + "  " + decodeDestReg + "  " + decodeSource1 + "  " + decodeSource2+" Stalled");
							
							}
							
							break;
				

    }}}
    else 
    	System.out.println("DRF:\t Empty");
        }
    }
}