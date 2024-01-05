package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Week {
	Map<Integer, List<Lesson>> data = new HashMap();

	public List<Lesson> getLessonsOfDay(int day) {
		return data.get(day);
	}

	public void setLessonOfDay(int day, Lesson lesson) {
		try {
			data.get(day).add(lesson);
		} catch (Exception e) {
			List<Lesson> dataa = new ArrayList();
			dataa.add(lesson);
			// TODO: handle exception
			data.put(day, dataa);
		}

	}

	@Override
	public String toString() {
		String res = "";
		for (Map.Entry<Integer, List<Lesson>> entry : data.entrySet()) {
			Integer key = entry.getKey();
			List<Lesson> val = entry.getValue();
			res += "\nthu:" + key + val;

		}
		return res;
	}

	public int size() {
		int size = 0;
		for (Map.Entry<Integer, List<Lesson>> entry : data.entrySet()) {
			List<Lesson> val = entry.getValue();
			size += val.size();

		}
		return size;
	}

}
