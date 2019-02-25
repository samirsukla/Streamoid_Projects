package com.TextSearch.utility;

import java.io.IOException;
import java.text.DecimalFormat;
import org.json.simple.parser.ParseException;

public class CalculateNDCG {

	static CalculateDCG caldcg;
	static CalculateIDCG calidcg;
	Double NDCG = 0.0;
	static Double DCG;
	static Double IDCG;

	public Double calculateNDCG(int rowNum) throws IOException, ParseException {

		caldcg = new CalculateDCG();
		calidcg = new CalculateIDCG();

		DCG = caldcg.calculateDCG(rowNum);
		IDCG = calidcg.calculateIDCG(rowNum);

		NDCG = DCG / IDCG;
		DecimalFormat df = new DecimalFormat("#.##");
		NDCG = Double.valueOf(df.format(NDCG));

		// System.out.println(NDCG);

		return NDCG;

	}

}
