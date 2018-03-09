package com.polytechtours.fr.di5.mastermind.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class AutoSolveService {

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
			hit = result.get("hit");
			if(hit == numPlace)break;
			pseudoHit = result.get("pseudoHit");
			indexToDelete.clear();
			
			for(int i=0; i<combinations.size(); i++){
				eval = CalculateService.evaluate(combination, combinations.get(i), numColor);
				if(eval.get("hit").intValue() != hit || eval.get("pseudoHit").intValue() != pseudoHit){
					indexToDelete.add(i);
				}
			}
			
			for(int j=indexToDelete.size() - 1; j>=0; j--){
				combinations.remove(indexToDelete.get(j).intValue());
			}
			
			int next = chooseNext(combinations);
			combination = combinations.get(next);
			steps.add(combination);
			combinations.remove(next);
			
			StringBuilder str = new StringBuilder("");
			for(int k=0; k<combination.length; k++){
				str.append(combination[k]).append(",");
			}
			System.out.println(str.toString());
		}
		while(hit != numPlace && !combinations.isEmpty());

		return steps;
	}
	
	public static int chooseNext(ArrayList<int[]> combinations){
		Random random = new Random();
		int index = random.nextInt(combinations.size());
		return index;
	}
	
	public static void main(String[] args){
		int[] array = {1,2,3,4,5,6};
		ArrayList<int[]> result = AutoSolveService.solve(6, 6, array); 
		for(int i=0; i<result.size(); i++){
			StringBuilder str = new StringBuilder("");
			for(int j=0; j<result.get(i).length; j++){
				str.append(result.get(i)[j]).append(" ");
			}
			System.out.println(str.toString());
		}
	}
}
