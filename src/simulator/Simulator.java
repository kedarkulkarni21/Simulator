
package simulator;

import Memory.DataAndCodeMemory;
import PipeLineStages.*;
import Register.Register;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


public class Simulator {
public static Fetch fetch = new Fetch(); 
public static Decode decode = new Decode();
public static IntegerFU iFU = new IntegerFU();
public static Memory mem = new Memory();
public static WriteBack wb = new WriteBack();
public static MUL1 mul1 = new MUL1();
public static MUL2 mul2 = new MUL2();
public static DIV1 div1 = new DIV1();
public static DIV2 div2 = new DIV2();
public static DIV3 div3 = new DIV3();
public static DIV4 div4 = new DIV4();



public static Register reg = new Register();
public static DataAndCodeMemory datamem = new DataAndCodeMemory();

public static int stall = 0;
public static int isHalt=0;
public static int isMulBusy=0;
public static int isDivBusy=0;
public static int intfuMulHalt=0;
public static int intfuDivHalt=0;
public static int decodeStall=0;
public static int isDivBusyForMul=0;
public static int mulStall=0;
public static int stallFetchForDecode=0;

private static final String FILENAME = "C:\\Users\\Pooja Parmar\\Documents\\notes 2017\\COA\\Simulator\\new.txt";
public static int instructionNum[]=new int[100];
public static int cycleCount=0;
public static int address[]=new int[100];
public static int PC;
public static int initInstno=1;
public static String instructionName[] = new String[100];
public static String destinationReg[] = new String[100];
public static String source1[] = new String[100];
public static String source2[] = new String[100];
public static String regStatus[] = new String[16];
public static int registers[] = new int[16];
public static int memory[] = new int[3999]; 
public static int counter=0;
public static int count=0;
public static int i=0, y=1;


public static int fetchFlag=0;
public static int decodeFlag=0; 
public static int zeroFlag=0;
public static int flushFlag=0;

public static int icount = 0;

public static int newJAlAdd=0;

    public static void main(String[] args) throws IOException{
        Scanner sc = new Scanner(System.in);
        int choice;
        while(true){
        System.out.println("Enter your choice:");
        System.out.println("1. Initialize \t 2. Simulate \t 3. Display \t 4. Exit");
        choice = sc.nextInt();
        switch(choice){
            case 1: Initialize();
                    ReadFile();
                    break;
            case 2: System.out.println("Enter the number of cycles to be simulated");
                    int n=sc.nextInt();
                    n = cycleCount + n;
				for (; cycleCount < n; cycleCount++) {
					System.out.println("\nCycle "+ y+":");
					y++;
					System.out.println();
					wb.Writeback();
					mem.Memory();
					div4.div4();
					div3.div3();
					div2.div2();
					div1.div1();
                    mul2.mul2();
                    mul1.mul1();
                    iFU.IntegerFU();
					decode.Decode();
					fetch.Fetch(count);
					System.out.println();
					System.out.print("Registers : \t");
					for (int j = 0; j < 16; j++)
						System.out.print("[" + j + "]\t");
					System.out.println();

					System.out.print("Values : \t");
					for (int j = 0; j < 16; j++)
						System.out.print(" " + registers[j] + "\t");
					System.out.println("\n");
        }
                                         break;
            case 3: Display();
                    break;
            case 4: System.exit(0);
                    break;
            default: System.out.println("Invalid choice");
                    System.exit(0);
                    break;
        }}
    }
   
    public static void Initialize(){
       
        cycleCount=0;
        PC = 4000;
        initInstno=1;
        reg.initializeRegister();
        datamem.initializeCodeLine();
        datamem.initializeCodeMemory();
        
        
    }
    
    public static void ReadFile(){
      BufferedReader br = null;
      int i=0;
		FileReader fr = null;
                String line = null;

		try {

			//br = new BufferedReader(new FileReader(FILENAME));
			fr = new FileReader(FILENAME);
			br = new BufferedReader(fr);
                    
			while ((line = br.readLine()) != null) {
                            //System.out.println("value of i before using it " +i);
                            counter++;
                           // System.out.println("inside while");
                            String inst[] = line.split("[ ,#]+");
                            //System.out.println(inst[0]+"\t"+inst[1]+"\t"+inst[2]);
                            if(inst.length>3){
                             //   System.out.println("inside 1st if");
                                if(inst[0].compareTo("ADD")==0 || inst[0].compareTo("SUB")==0 || inst[0].compareTo("LOAD")==0 
                                        || inst[0].compareTo("STORE")==0 || inst[0].compareTo("MUL")==0 || inst[0].compareTo("DIV")==0  || inst[0].compareTo("JAL")==0){
                               //         System.out.println("inside comapre if");
                                       
                                        address[i] = PC;
                                      //  System.out.println("address"+address[i]);
                                        instructionNum[i] = initInstno++;
                                        //System.out.println("inst num "+instructionNum[i]);
                                        instructionName[i] = inst[0];
                                       // System.out.println("inst name "+instructionName[i]);
                                        destinationReg[i] = inst[1];
                                       // System.out.println("dest "+destinationReg[i]);
                                        source1[i] = inst[2];
                                       // System.out.println("source 1 "+source1[i]);
                                        source2[i] = inst[3];
                                       // System.out.println("source 2 "+source2[i]);
                                
                                }
                            }else if (inst.length > 2 && inst.length <= 3) {
					if (inst[0].compareTo("MOVC") == 0 || inst[0].compareTo("JUMP") == 0) {
                                         //       System.out.println("jump or movc");
						address[i] = PC;
                                           //     System.out.println("Address is "+address[i]);
						instructionNum[i] = initInstno++;
						instructionName[i] = inst[0];
						destinationReg[i] = inst[1];
                                              //  System.out.println("dest reg"+destinationReg[i]);
						source1[i] = inst[2];
                                        }
                            }else if (inst.length > 1 && inst.length <= 2) {
					if (inst[0].compareTo("BZ") == 0 || inst[0].compareTo("BNZ") == 0) {
                                            //System.out.println("bz or bnz");
						address[i] = PC;
						instructionNum[i] = initInstno++;
						instructionName[i] = inst[0];
						destinationReg[i] = inst[1];
						source1[i] = "0";
						source2[i] = "0";
					}
				}else if (inst.length > 0 && inst.length <= 1) {
					if (inst[0].compareTo("HALT") == 0) {
						address[i] = PC;
						instructionNum[i] = initInstno++;
						instructionName[i] = inst[0];
						destinationReg[i] = "0";
						source1[i] = "0";
						source2[i] = "0";
					}
				}
                            else
                                    System.out.println("Unknown command");
                             PC=PC+4;
                            i++;
			}

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				if (br != null)
					br.close();

				if (fr != null)
					fr.close();

			} catch (IOException ex) {

				ex.printStackTrace();

			}

		}

	}
    public static void Display(){
        System.out.print("Registers : \t");
		for (int j = 0; j < 16; j++)
			System.out.print("[" + j + "]\t");
		System.out.println();

		System.out.print("Values : \t");
		for (int j = 0; j < 16; j++)
			System.out.print("" + registers[j] + "\t");
		System.out.println();
		for (int i = 0; i < 100; i++) {
			if (i % 10 == 0)
				System.out.println();
			System.out.print("Memory[" + i + "]" + memory[i] + "\t");
		}
    }
    
    
//  public static boolean isNumeric(String str)
//  {
//    try
//    {
//      double d = Integer.parseInt(str);
//    }
//    catch(NumberFormatException nfe)
//    {
//      return false;
//    }
//    return true;
//  }
}
    
   

    

