package com.TextSearch.utility;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import org.json.simple.parser.ParseException;

public class CalculateDCG {
	static GetFinalScores getfscore;
	static List<Double> finalScores;

	static int rank;

	public Double calculateDCG(int rowNum) throws IOException, ParseException {

		getfscore = new GetFinalScores();
		finalScores = getfscore.getScores(rowNum);
		Double DCG = 0.0;
		int i = 1;

		Iterator it = finalScores.iterator();
		while (it.hasNext()) {

			Double value = (Double) it.next();

			rank = i++;
			// System.out.println("The Index of " + value + "is " + rank);
			DCG = DCG + (value * (1 / (Math.log(rank + 1) / Math.log(2))));

		}
		// System.out.println("DCG is " + DCG);
		return DCG;
	}

}
