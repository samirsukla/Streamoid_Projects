package com.TextSearch.utility;

import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.json.simple.parser.ParseException;

public class CalculateIDCG {

	static GetFinalScores getfscore;
	static List<Double> finalScores;

	static int rank;

	public Double calculateIDCG(int rowNum) throws IOException, ParseException {

		getfscore = new GetFinalScores();
		finalScores = getfscore.getScores(rowNum);
		Double IDCG = 0.0;
		int i = 1;

		Collections.sort(finalScores, Collections.reverseOrder());

		Iterator it = finalScores.iterator();
		while (it.hasNext()) {

			Double value = (Double) it.next();

			rank = i++;
			// System.out.println("The Index of " + value + "is " + rank);
			IDCG = IDCG + (value * (1 / (Math.log(rank + 1) / Math.log(2))));

		}

		// System.out.println("IDCG is " + IDCG);
		return IDCG;
	}
}
