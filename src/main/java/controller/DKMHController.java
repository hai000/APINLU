package controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.util.EntityUtils;

import model.APINLU;
import model.Lesson;
import model.Subject;

public class DKMHController {
	static DKMHController instance = new DKMHController();
	List<Subject> subjects = new ArrayList<>();

	public String[][] loadDataMH() {
		subjects = APINLU.getInstance().getDSNhomTo();
		String[][] res = new String[subjects.size()][6];
		int i = 0;
		for (Subject subject : subjects) {
			res[i] = new String[] { subject.getMa_MH(), subject.getName_subject(), subject.getNhom_to(),
					subject.getNgayDangKyOrSLCL(), subject.getTkb(), subject.getIdMon() };

			i++;
		}
		return res;
	}

	public String dkmh(String id_To) {

		return APINLU.getInstance().dkmh(searchSubject(id_To));

	}
	public String huyDkmh(String id_To) {
		
		return APINLU.getInstance().huyDkmh(searchSubject(id_To));
		
	}

	public Subject searchSubject(String idmon) {
		Subject sub = null;
		for (Subject subject : subjects) {
			if (subject.getIdMon().equalsIgnoreCase(idmon)) {
				sub = subject;
				break;
			}
		}
		return sub;

	}

	public static DKMHController getInstance() {
		return instance;
	}
}
