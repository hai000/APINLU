package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class LichThi {
	Map<String, List<Lesson>> data = new TreeMap<>();

	public void addMonThi(String day, Lesson sub) {
		try {
			data.get(day).add(sub);

		} catch (Exception e) {
			// TODO: handle exception
			List<Lesson> subject = new ArrayList<>();
			subject.add(sub);
			data.put(day, subject);
		}

	}

	public List<Lesson> getValue(String key) {
		return data.get(key);
	}

	public int sizeLesson() {
		int size = 0;
		for (Map.Entry<String, List<Lesson>> entry : data.entrySet()) {
			size += entry.getValue().size();

		}
		return size;
	}

	public Set<String> getKeys() {
		return data.keySet();
	}

	@Override
	public String toString() {
		return "LichThi " + data;
	}

}
