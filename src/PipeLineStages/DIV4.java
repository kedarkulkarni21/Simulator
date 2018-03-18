package PipeLineStages;

import static PipeLineStages.DIV2.*;
import static PipeLineStages.DIV3.*;
import static PipeLineStages.Decode.*;
import static simulator.Simulator.*;

public class DIV4 {
	public static String div4InstName;
	public static String div4DestReg;
	public static String div4Source1;
	public static String div4Source2;
	public static int div4Flag=0;
	public static int d4Answer=0;
	
	public static void div4() {
		div4InstName=div3InstName;
		div4DestReg=div3DestReg;
		div4Source1=div3Source1;
		div4Source2=div3Source2;
	if(div3Flag==1 && div4Flag==0) {
		if(div4InstName!=null) {
			isDivBusyForMul=1;
			switch(div4InstName) {
			case "DIV":	Idest = Integer.parseInt(div4DestReg.replaceAll("[^0-9]", ""));
	            		d4Answer=d3Answer;
	            		regStatus[Idest] = "INVALID";
	            		System.out.println("DIV4:\t I("+count+")"+div4InstName+" "+div4DestReg+" "+div4Source1+" "+div4Source2);
	            		div3Flag = 0;
	            		div4Flag = 1;
	            		//    isBusy=1;
	            		break;
			case "HALT":System.out.println("DIV4:\t I("+count+")"+div4InstName);
						div3Flag=0;
						div4Flag=1;
//						if(isBusy==0){
//							div3Flag=0;
//							div4Flag=1;
//						}
//						else{
//							div3Flag=0;
//							div4Flag=0; 
//						}

						break;
			}
		}
		isDivBusy=1;
		
	}
	else {
		System.out.println("DIV4:\t Empty");
	}
}
}