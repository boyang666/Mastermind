package com.polytechtours.fr.di5.mastermind.service;

import java.util.ArrayList;

public class CombineService {  
	
	private ArrayList<int[]> combinations = new ArrayList<int[]>();
    
    public ArrayList<int[]> combine(int[] a, int n) {  
          
        if(null == a || a.length == 0 || n <= 0 || n > a.length)  
            return null;  
                
        int[] b = new int[n];//to keep the combination
        getCombination(a, n , 0, b, 0); 
        return combinations;
    }  
  
    private void getCombination(int[] a, int n, int begin, int[] b, int index) {  
          
        if(n == 0){//if size equals n   
            int[] combination = new int[b.length];
            for(int k=0; k<combination.length; k++){
            	combination[k] = b[k];
            }
            combinations.add(combination);
            return;  
        }  
              
        for(int i = 0; i < a.length; i++){  
            b[index] = a[i];  
            getCombination(a, n-1, 0, b, index+1);   
        }  
          
    }  
      
    public static void main(String[] args){  
          
        CombineService robot = new CombineService();  
          
        int[] a = {1,2,3,4};  
        int n = 3;  
        robot.combine(a,n);  
  
    }  
  
}  
