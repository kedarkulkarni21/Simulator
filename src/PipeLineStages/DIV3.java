package PipeLineStages;

import static PipeLineStages.DIV1.*;
import static PipeLineStages.Decode.*;
import static simulator.Simulator.*;
import static PipeLineStages.DIV2.*;

public class DIV3 {
	public static String div3InstName;
	public static String div3DestReg;
	public static String div3Source1;
	public static String div3Source2;
	public static int div3Flag=0;
	
public static int d3Answer;
	
	public static void div3() {
	div3InstName=div2InstName;
	div3DestReg=div2DestReg;
	div3Source1=div2Source1;
	div3Source2=div2Source2;
	
	
	if(div2Flag==1 && div3Flag==0) {
		if(div3InstName!=null) {
			isDivBusy=1;
			isDivBusyForMul=1;
			switch(div3InstName) {
			case "DIV":	Idest = Integer.parseInt(div3DestReg.replaceAll("[^0-9]", ""));
	            		d3Answer=d2Answer;
	            		regStatus[Idest] = "INVALID";
	            		System.out.println("DIV3:\t I("+count+")"+div3InstName+" "+div3DestReg+" "+div3Source1+" "+div3Source2);
	            		div2Flag = 0;
	            		div3Flag = 1;
	            		//    isBusy=1;
	            		break;
	        case "HALT":System.out.println("DIV3:\t I("+count+")"+div3InstName);
	        			div2Flag=0;
	        			div3Flag=1;
						/*if(isBusy==0){
							div2Flag=0;
							div3Flag=1;
						}
						else{
							div2Flag=0;
							div3Flag=0; 
						}
*/
						break;
		}
		}
		
	}
	else {
		System.out.println("DIV3:\t Empty");
	}
	}
}
