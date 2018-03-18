package PipeLineStages;
import static PipeLineStages.DIV1.*;
import static PipeLineStages.Decode.*;
import static simulator.Simulator.*;

import simulator.Simulator;



public class DIV2 {
	public static String div2InstName;
	public static String div2DestReg;
	public static String div2Source1;
	public static String div2Source2;
	public static int div2Flag=0;
	public static int d2Answer;
	
	public static void div2() {
	div2InstName=div1InstName;
	div2DestReg=div1DestReg;
	div2Source1=div1Source1;
	div2Source2=div1Source2;
	
	
	if(div1Flag==1 && div2Flag==0) {
		if(div2InstName!=null) {
			isDivBusy=1;
			isDivBusyForMul=1;
			switch(div2InstName) {
			case "DIV":	Idest = Integer.parseInt(div2DestReg.replaceAll("[^0-9]", ""));
	            		d2Answer=d1Answer;
	            		regStatus[Idest] = "INVALID";
	            		System.out.println("DIV2:\t I("+count+")"+div2InstName+" "+div2DestReg+" "+div2Source1+" "+div2Source2);
	            		div1Flag = 0;
	            		div2Flag = 1;
	            		goToDiv=0;
	            		//    isBusy=1;
	            		break;
			case "HALT":System.out.println("DIV2:\t I("+count+")"+div2InstName);
						div1Flag=0;
						div2Flag=1;
						/*if(isBusy==0){
							div1Flag=0;
							div2Flag=1;
						}
						else{
							div1Flag=0;
							div2Flag=0; 
							}*/
    
						break;
			}
		}
		
	}
	else {
		System.out.println("DIV2:\t Empty");
	}
	}
}
