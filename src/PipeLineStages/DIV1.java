package PipeLineStages;

import static PipeLineStages.Decode.*;


public class DIV1 {
public static String div1InstName;
public static String div1DestReg;
public static String div1Source1;
public static String div1Source2;
public static int div1Flag=0;
public static int d1Answer;

public static void div1() {
	div1InstName = decodeInstName;
	div1DestReg = decodeDestReg;
	div1Source1 = decodeSource1;
	div1Source2 = decodeSource2;
	
	if(decodeFlag==1 && div1Flag==0 && goToDiv==1) {
		if(div1InstName!=null) {
			isDivBusy=1;
			goToDiv=0;
			isDivBusyForMul=1;
			switch(div1InstName) {
			case "DIV":	Idest = Integer.parseInt(div1DestReg.replaceAll("[^0-9]", ""));
                		regStatus[Idest] = "INVALID";
                		msource1 = Integer.parseInt(div1Source1.replaceAll("[^0-9]", ""));
                		msource2 = Integer.parseInt(div1Source2.replaceAll("[^0-9]", ""));
                		d1Answer = registers[msource1]/registers[msource2];
                		if(d1Answer==0)
                			zeroFlag=1;
                		else
                			zeroFlag=0;
                		System.out.println("DIV1:\t I("+count+")"+div1InstName+" "+div1DestReg+" "+div1Source1+" "+div1Source2);
                		div1Flag=1;
                		decodeFlag=0;
                		break;
			case "HALT":System.out.println("DIV1:\t I("+count+")"+div1InstName);
			div1Flag=1;
				decodeFlag=0;

/*          				if(isBusy==0){
          					div1Flag=1;
          					decodeFlag=0;
          				}
          				else{
          					decodeFlag=0;
          					div1Flag=0; 
          				}
          				*/
            			break;
				
			}
			}
		}
	
	else {
		System.out.println("DIV1:\t Empty");
	}
}

}
