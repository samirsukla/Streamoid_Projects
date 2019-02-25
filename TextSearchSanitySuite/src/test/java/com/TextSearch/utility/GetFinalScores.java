package com.TextSearch.utility;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.json.simple.parser.ParseException;

public class GetFinalScores {

	static CompareTwoMaps comp;
	static ExtractInfoFromExcelSheet ext;
	static Map<String, Double> finalMap;
	static List<Double> finalScores;

	public List<Double> getScores(int rowNum) throws IOException, ParseException {
		ext = new ExtractInfoFromExcelSheet();
		comp = new CompareTwoMaps();
		finalScores = new ArrayList<Double>();

		finalMap = comp.compareMaps(rowNum);

		Iterator it1 = finalMap.entrySet().iterator();
		while (it1.hasNext()) {
			Map.Entry pair = (Entry) it1.next();
			Double score = (Double) pair.getValue();
			finalScores.add(score);
		}
		// System.out.println(finalScores);
		return finalScores;
	}

}
