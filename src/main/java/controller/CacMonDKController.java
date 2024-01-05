package controller;

import java.util.List;

import model.APINLU;
import model.Subject;

public class CacMonDKController {
	static CacMonDKController instance = new CacMonDKController();

	public String[][] loadData() {
		List<Subject> data = APINLU.getInstance().getDSMHDangKi();
		String[][] res = new String[data.size()][];
		int i = 0;
		for (Subject sub : data) {
			res[i] = new String[] { sub.getMa_MH(), sub.getName_subject(), sub.getNhom_to(), sub.getNgayDangKyOrSLCL(),
					sub.getTkb() };
			i++;
		}
		return res;
	}

	public static CacMonDKController getInstance() {
		return instance;
	}
}
