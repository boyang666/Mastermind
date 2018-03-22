package com.polytechtours.fr.di5.mastermind.service;

import java.util.HashMap;

/**
 * Class to provide evaluate service to evaluate one step of test<br>
 * 
 * @author Boyang Wang
 * @version 1.0
 *
 */
public class CalculateService {

	/**
	 * function to evaluate one step of test
	 * 
	 * @param sequence the solution sequence to find
	 * @param choice the test sequence of user
	 * @param numColor number of colors
	 * @return hashmap with two entries:
	 * 		   <ul>
	 * 			<li>key:hit to present the number of hit</li>
	 * 			<li>key:pseudoHit to present the number of pseudo hit</li>
	 *         </ul>
	 */
	public static HashMap<String, Integer> evaluate(int[] sequence, int[] choice, int numColor) {

		int len = sequence.length;
		int hit = 0;
		int pseudoHit = 0;
		HashMap<String, Integer> res = new HashMap<String, Integer>();
		// whether the elements are used
		boolean[] markedA = new boolean[len];
		boolean[] markedG = new boolean[len];
		// calculate the exact hits
		for (int i = 0; i < len; i++) {
			int c = sequence[i];
			// if guess is correct
			if (c == choice[i]) {
				markedA[i] = true;
				markedG[i] = true;
				hit++;
			}
		}
		
		// calculate the pseudo hits 
		for (int i = 0; i < len; i++) {
			if (!markedA[i]) {
				for (int j = 0; j < len; j++) {
					if (!markedG[j] && sequence[i] == choice[j]) {
						pseudoHit++;
						markedA[i] = true;
						markedG[j] = true;
						break;
					}
				}
			}
		}
		
		res.put("hit", hit);
		res.put("pseudoHit", pseudoHit);
		
		return res;
	}
}
