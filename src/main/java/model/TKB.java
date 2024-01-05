package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class TKB {
	Map<Integer, Week> weeks = new TreeMap();

	public void addWeek(int index, Week week) {
		weeks.put(index, week);
	}

	public Week getWeek(int week) {
		return weeks.get(week);
	}

	public Map<Integer, Week> getWeeks() {
		return weeks;
	}

	@Override
	public String toString() {
		String res = "";
		for (Map.Entry<Integer, Week> entry : weeks.entrySet()) {
			Integer key = entry.getKey();
			Week val = entry.getValue();
			res += "tuan:" + key + val + "\n";

		}
		return res;
	}

}
