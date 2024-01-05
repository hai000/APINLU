package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class ToolSortTKB {
	public static Map<Integer, Lesson> data = new HashMap<Integer, Lesson>();
	public static int SCORE_BAD = 2;
	public static int SCORE_MID = 5;
	public static int SCORE_GOOD = 10;
	TKB tkb;
	public static List<String> isCheck = new ArrayList<>();

	public void addLesson(int score, Lesson sub) {
		data.put(score, sub);
	}

	public void removeLesson(String mamon) {
		List<Integer> keys = new ArrayList<>();
		for (Map.Entry<Integer, Lesson> entry : data.entrySet()) {
			Integer key = entry.getKey();
			Lesson val = entry.getValue();
			if (val.getMa_mon().equals(mamon)) {
				keys.add(key);
			}
		}
		for (Integer key : keys) {
			data.remove(key);
		}

	}

	public TKB sort() {
		tkb = new TKB();

		DFS dfs = new DFS();
		int size = 0;
		for (Map.Entry<Integer, Lesson> entry : data.entrySet()) {
			Lesson val = entry.getValue();
			if (!isCheck.contains(val.getMa_mon())) {
				isCheck.add(val.getMa_mon());

				size++;
			}

		}
		Node node = dfs.dfsUsingStack(new Node(size), size);
		Week week = new LocalSearch().run(node);
		tkb.addWeek(0, week);

		return tkb;
	}

}
