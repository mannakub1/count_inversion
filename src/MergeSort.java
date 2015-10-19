import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class MergeSort {
	private int numberMax ; 
	private int NumberArr[];
	private int count ;
	private ArrayList<HashMap<String,Integer>> numberInversion ;
	
	public MergeSort() throws IOException{
		ReadFile();
		numberInversion =  new ArrayList<HashMap<String,Integer>>();
	}
	
	public void ReadFile() throws IOException{
		FileReader read = new FileReader("test.txt");
		BufferedReader br = new BufferedReader(read);
		 String input = br.readLine();
		 numberMax = Integer.parseInt(input);
		 NumberArr = new int[numberMax];
		 input = br.readLine();
		 String strArr[] = input.split(" ");
		 System.out.println(numberMax);
		 for(int index = 0 ; index < numberMax; index++){
				NumberArr[index] =  Integer.parseInt(strArr[index]);
				System.out.print(NumberArr[index]+ " ");
			}
		 System.out.println();
		 }
	
	
	
	public void Merge(int indexStart , int indexEnd , int[] NumberArr){
		int indexMiddle ;
		
		if (indexEnd > indexStart){
			indexMiddle  = (indexStart + indexEnd) / 2 ;
			Merge(indexStart , indexMiddle , NumberArr);
			Merge(indexMiddle+1 , indexEnd, NumberArr );
			
			Combince(indexStart , indexMiddle+1 , indexEnd, NumberArr );
		}
	}
	public void Combince(int indexStart , int indexMiddle , int indexEnd , int[] NumberArr){
		int tempArr[] = new int[numberMax];
		int lastIndexStart = indexMiddle-1;
		int indexTemp = indexStart;
		int index = indexEnd - indexStart + 1 ;
		int countInversion = 0;
		int halfNumberArr = indexMiddle;
		//System.out.println(halfNumberArr);
		while((indexStart  <= lastIndexStart) && (indexMiddle <= indexEnd)){
			if(NumberArr[indexStart] <= NumberArr[indexMiddle]){
				tempArr[indexTemp++] = NumberArr[indexStart++];
				countInversion++;
			}else {
				int mid = halfNumberArr - indexStart;
				for(int left = 0; left < mid ; left++){
					count++;
					HashMap<String, Integer> map = new HashMap<String,Integer>();
					map.put("left", NumberArr[indexStart+left]);
					map.put("right",NumberArr[indexMiddle]);
					numberInversion.add(map);
				}
				tempArr[indexTemp++] = NumberArr[indexMiddle++];	
			}
			
		}
		while(indexStart <= lastIndexStart){
			tempArr[indexTemp++] = NumberArr[indexStart++];
		}
		while(indexMiddle <= indexEnd){
			tempArr[indexTemp++] = NumberArr[indexMiddle++];	
		}
		for(int i = 0 ; i < index ; i++){
			NumberArr[indexEnd] = tempArr[indexEnd];
			indexEnd--;
		}
	}
	public void printArr(int[] Arr){
		System.out.print("Sort => ");
		for(int index = 0 ; index < Arr.length ; index++){
			System.out.print(Arr[index]+" ");
		}
	}
	public void printNumberInversion(){
		for(int index = 0 ; index < numberInversion.size() ; index++){
			HashMap<String, Integer> map = numberInversion.get(index); 
			int left = map.get("left");
			int right = map.get("right");
			String str = String.format("%d. => (%d,%d)", index+1,left,right);
			System.out.println(str);
		}
	}
	
	public static void main(String[] args) throws IOException{
		MergeSort user = new MergeSort();
		user.Merge(0, user.numberMax-1, user.NumberArr);
		user.printArr(user.NumberArr);
		System.out.println();
		System.out.println("Count Inversion => "+user.count);
		user.printNumberInversion();
	}
/* 
6
5 4 3 2 1 0
8
6 3 5 2 8 1 4 7
8
1 5 4 8 2 6 3 7 
12
1 5 4 8 10 2 6 9 12 11 3 7
*/
}
