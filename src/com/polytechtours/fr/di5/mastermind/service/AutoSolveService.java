package com.polytechtours.fr.di5.mastermind.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Class to provide the auto-solve service<br>
 * A method is offered to solve a mastermind problem in less than 10 steps<br>
 * Two strategies are introduced to generate the next combination:
 * <ul>
 * <li>Random a combination in the set to choose as the next one</li>
 * <li>Use max-min strategy to choose the next one</li>
 * </ul> 
 * 
 * Both these two methods give a solution in less than 10 steps.<br>
 * Randomize is more rapid than Max-min.
 * 
 * @author Boyang Wang
 * @version 2.0
 *
 */
public class AutoSolveService {

	/**
	 * method to auto-solve a mastermind problem within 10 steps
	 * 
	 * @param numColor number of colors
	 * @param numPlace number of places within one step
	 * @param sequence the sequence to get at the end
	 * @return list of int[] with all steps int it
	 */
	public static ArrayList<int[]> solve(int numColor, int numPlace, int[] sequence){
		
		// initialize the set of all possible combinations
		int[] colors = new int[numColor];
		for(int i=0; i<numColor; i++){
			colors[i] = (i+1);
		}
		
		CombineService combine = new CombineService();
		ArrayList<int[]> combinations = combine.combine(colors, numPlace);
		
		
		//initialize of steps
		ArrayList<int[]> steps = new ArrayList<int[]>();
		
		// choose one combination
		Random random = new Random();
		int index = random.nextInt(combinations.size());
		int[] combination = combinations.get(index);
		combinations.remove(index);
		steps.add(combination);
		
		
		// initialize of result
		HashMap<String, Integer> result = null;
		int hit = 0;
		int pseudoHit = 0;
		ArrayList<Integer> indexToDelete = new ArrayList<Integer>();
		HashMap<String, Integer> eval;
		// start the loop
		do{
			result = CalculateService.evaluate(sequence, combination, numColor);
			
			//get number of hit and pseudo hit
			hit = result.get("hit");
			if(hit == numPlace)break;
			pseudoHit = result.get("pseudoHit");
			indexToDelete.clear();
			
			// find all combinations which have different compare result with the one chosen
			for(int i=0; i<combinations.size(); i++){
				eval = CalculateService.evaluate(combination, combinations.get(i), numColor);
				if(eval.get("hit").intValue() != hit || eval.get("pseudoHit").intValue() != pseudoHit){
					indexToDelete.add(i);
				}
			}
			
			// delete these combinations
			for(int j=indexToDelete.size() - 1; j>=0; j--){
				combinations.remove(indexToDelete.get(j).intValue());
			}
			
			// choose next combination
			int next = chooseNext(combinations);
			combination = combinations.get(next);
			steps.add(combination);
			combinations.remove(next);
			
		}
		while(hit != numPlace && !combinations.isEmpty());

		return steps;
	}
	
	/**
	 * Random strategy to choose next combination
	 * @param combinations all combinations remaining in the set
	 * @return index of next combination
	 */
	public static int chooseNext(ArrayList<int[]> combinations){
		Random random = new Random();
		int index = random.nextInt(combinations.size());
		return index;
	}
	
	/**
	 * Max-min strategy to choose next combination
	 * @param combinations all combinations remaining in the set
	 * @param numColor number of colors
	 * @return index of next combination
	 */
	public static int chooseNext(ArrayList<int[]> combinations, int numColor){
		
		// to stock all minimum count 
		int[] minEtat = new int[combinations.size()];
		
		//to stock the result matrix
		for(int i=0; i<combinations.size(); i++){
			
			//compute all possible results
			HashMap<Integer, Integer> matrix = new HashMap<Integer, Integer>();
			for(int j=0; j<combinations.size(); j++){
				if(i != j){
					HashMap<String, Integer> eval = CalculateService.evaluate(combinations.get(i), combinations.get(j), numColor);
					int hit = eval.get("hit");
					int pseudoHit = eval.get("pseudoHit");
					int key = hit * 10 + pseudoHit;
					if(matrix.containsKey(key)){
						int count = matrix.get(key);
						count++;
						matrix.put(key, count);
					}else{
						matrix.put(key, 1);
					}
				}
			}
			
			//get the minimum count of all possible results
			int min = Integer.MAX_VALUE;
			for(Integer value : matrix.values()){
				if(value < min){
					min = value;
				}
			}
			minEtat[i] = min;
		}
		
		// choose the maximum of all minimum
		int index = -1;
		int max = Integer.MIN_VALUE;
		for(int i=0; i<minEtat.length; i++){
			if(max < minEtat[i]){
				max = minEtat[i];
				index = i;
			}
		}
		
		return index;
	}
}
