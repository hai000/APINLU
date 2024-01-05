package controller;

import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.Map;
import java.util.TreeMap;

import model.APINLU;
import model.Lesson;
import model.TKB;
import model.Week;

public class TKBController {
	static TKBController instance = new TKBController();
	public static int tuanCurrent = 1;

	public Map<Integer, String[][]> loadData() {
		Map<Integer, String[][]> res = new TreeMap<Integer, String[][]>();
		TKB tkb = APINLU.getInstance().getTKB("20231");
		Map<Integer, Week> weeks = tkb.getWeeks();

		for (Map.Entry<Integer, Week> entry : weeks.entrySet()) {
			Week val = entry.getValue();
			String[][] data = new String[7][5];
			Integer index = entry.getKey();
			for (int i = 0; i < 7; i++) {
				String[] tiet = new String[5];
				try {

					for (Lesson lesson : val.getLessonsOfDay(i + 2)) {
						if (index == 1) {
							String[] datadate = lesson.getNgay_hoc().split("T")[0].split("-");
							LocalDate date = LocalDate.of(Integer.valueOf(datadate[0]), Integer.valueOf(datadate[1]),
									Integer.valueOf(datadate[2]));

							int weekOfYear = date.get(WeekFields.ISO.weekOfYear());
							date = LocalDate.now();

							int currentWeekOfYear = date.get(WeekFields.ISO.weekOfYear());
							tuanCurrent = currentWeekOfYear - weekOfYear + 1;

						}

						tiet[(int) lesson.getTiet_bat_dau() / 3] = lesson.getTen_mon();

					}

				} catch (Exception e) {

				}
				data[i] = tiet;
			}
			res.put(index, data);
		}

		return res;

	}

	public static TKBController getInstance() {
		return instance;
	}
}
