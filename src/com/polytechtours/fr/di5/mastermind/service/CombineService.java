package com.polytechtours.fr.di5.mastermind.service;

import java.util.ArrayList;
/**
 * Class to provide one method for computing all the possible sequences
 * 
 * @author Boyang Wang
 * @version 1.0
 *
 */
public class CombineService {  
	/**
	 * to store all combinations
	 */
	private ArrayList<int[]> combinations = new ArrayList<int[]>();
    
	/**
	 * function to call in order to get all possible combinations
	 * @param a all possible values
	 * @param n the total size of values
	 * @return list of int[] as all possible combinations
	 */
    public ArrayList<int[]> combine(int[] a, int n) {  
          
        if(null == a || a.length == 0 || n <= 0 || n > a.length)  
            return null;  
                
        int[] b = new int[n];//to keep the combination
        getCombination(a, n , 0, b, 0); 
        return combinations;
    }  
  
    /**
     * recursive calls to get all combinations
     * @param a input data
     * @param n size of data
     * @param begin position to start
     * @param b to store a combination
     * @param index to store the position to place one value
     */
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
  
}  
