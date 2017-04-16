package com.whatfix;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class sudoko {
	public static FileInputStream fileInput = null;
	static int[][] arr = null;
	public static void main(String[] args) {
		try {
			fileInput = new FileInputStream(args[0]);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInput));	
		try {
			String str = bufferedReader.readLine();
			while (str != null){
			String[] temp = str.split(";");	
			int N = Integer.parseInt(temp[0]);
			String[] strarr = temp[1].split(",");
			int[] intarr  = new int[strarr.length];
			
			for(int count = 0; count < strarr.length ; count++) {
	            intarr[count] = Integer.parseInt(strarr[count]);
	        }
			arr = new int[N][N];
			int k=0;
			for(int i =0;i<N;i++){
				for(int j=0;j<N;j++){
					arr[i][j] = intarr[k++];
				}
			}			
			int a = (int) Math.sqrt(N);
			if(a*a == N && numberCheck(N,intarr) && N*N == intarr.length)
				System.out.println(validateSudoko(a,N,arr));
			else 
				System.out.println( N+" is not Perfect Square or the number exist which is lessthen 1 or greater then "+N + " or total number of elements are eighter less or more then the required");
			str = bufferedReader.readLine();	
			}	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	static boolean validateSudoko(int a,int N,int arr[][]){
		boolean exist[] = new boolean[9];
		Arrays.fill(exist, false);
		
		if(N==9){
			for(int i =0;i<N;i++){
				for(int j=0;j<N;j++){
					if(exist[arr[i][j]-1]==false){
						exist[arr[i][j]-1]=true;
					}
					else{
						return false;
					}
				}
				for(boolean b : exist){
					if(b==false){
						return false;
					}
				}
				Arrays.fill(exist, false);			
			}
			for(int i=0;i<N;i++){
				for(int j=0;j<N;j++){
					if(exist[arr[j][i]-1]==false){
						exist[arr[j][i]-1]=true;
					}
					else{
						return false;
					}	
				}
				for(boolean b : exist){
					if(b==false){
						return false;
					}
				}
				Arrays.fill(exist, false);	
			}
			Arrays.fill(exist, false);
			for (int k=0;k<N;k++) {
				for (int i=k/a *a; i<k/a*a+a;i++) {
					for (int j=k%a*a;j<k%a*a+a;j++) {
						if(exist[arr[i][j]-1]==false){
							exist[arr[i][j]-1]=true;
						}
						else{
							return false;
						}	
					}
				}
				for(boolean b : exist){
					if(b==false){
						return false;
					}
				}
				Arrays.fill(exist, false);	
				}
		}
		return true;
	}
	static boolean numberCheck(int N,int arr[]){
		for (int i : arr) {
			if(i>N && i<1)
				return false; 
		}
		return true;
	}
}
