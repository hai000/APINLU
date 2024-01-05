package controller;

import java.util.Arrays;

import model.APINLU;
import model.Lesson;
import model.LichThi;

public class LichThiController {
	static LichThiController instance = new LichThiController();

	public String[][] loadData(String hocki) {
		LichThi lichthi = APINLU.getInstance().getLichThi(hocki);

		String[][] res = new String[lichthi.sizeLesson()][6];
		int i = 0;
		for (String key : lichthi.getKeys()) {
			for (Lesson lesson : lichthi.getValue(key)) {
				res[i] = new String[] { lesson.getNgay_hoc(), String.valueOf(lesson.getThu_kieu_so()),
						lesson.getMa_mon(), lesson.getTen_mon(), String.valueOf(lesson.getTiet_bat_dau()),
						String.valueOf(lesson.getSo_tiet()) };
				i++;

			}

		}

		return res;
	}

	public static LichThiController getInstance() {
		return instance;
	}
}
