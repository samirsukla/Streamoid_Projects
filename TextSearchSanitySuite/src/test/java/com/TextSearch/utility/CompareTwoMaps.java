package com.TextSearch.utility;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.json.simple.parser.ParseException;
import com.TextSearch.utility.TriggerTextSearchAPI;

public class CompareTwoMaps {
	static TriggerTextSearchAPI ttsa;
	static ExtractInfoFromExcelSheet ext;

	static Map<String, Double> actualMap;
	static Map<String, Double> expectedMap;

	public Map<String, Double> compareMaps(int rowNum) throws IOException, ParseException {

		ttsa = new TriggerTextSearchAPI();
		ext = new ExtractInfoFromExcelSheet();

		expectedMap = ext.extractPIDScore(rowNum);
		actualMap = ttsa.triggerSearchAPI(rowNum);
		Set<String> expectedKeySet = expectedMap.keySet();
		Set<String> actualKeySet = actualMap.keySet();

		List<String> expectedKeyList = new ArrayList<String>();
		expectedKeyList.addAll(expectedKeySet);

		List<String> actualKeyList = new ArrayList<String>();
		actualKeyList.addAll(actualKeySet);

		if (expectedKeySet.equals(actualKeySet)) {
			actualMap.putAll(expectedMap);
			// Iterator it1 = actualMap.entrySet().iterator();
			// while(it1.hasNext()) {
			// Map.Entry pair = (Entry) it1.next();
			// System.out.println(pair.getKey() +" = " + pair.getValue());
			// }
		} else {
			for (int i = 0; i < actualKeyList.size(); i++) {
				for (int j = 0; j < expectedKeyList.size(); j++) {
					if (!(actualKeyList.get(i)).equals((expectedKeyList).get(j))) {
						actualMap.put(actualKeyList.get(i), 0.0);
						continue;

					} else {
						Double newScore = (Double) expectedMap.get(expectedKeyList.get(j));
						actualMap.put(actualKeyList.get(i), newScore);

						break;
					}

				}

			}

		}
		// Iterator it1 = actualMap.entrySet().iterator();
		// while(it1.hasNext()) {
		// Map.Entry pair = (Entry) it1.next();
		// System.out.println(pair.getKey() +" = " + pair.getValue());
		// }

		return actualMap;
	}

}
