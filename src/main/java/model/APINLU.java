package model;

import java.io.IOException;
import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

public class APINLU {
	private final String PATH_LOGIN = "https://dkmh.hcmuaf.edu.vn/api/auth/login";
	private final String PATH_DSMHDangKy = "https://dkmh.hcmuaf.edu.vn/api/dkmh/w-locdskqdkmhsinhvien";
	private final String PATH_DSNhomTo = "https://dkmh.hcmuaf.edu.vn/api/dkmh/w-locdsnhomto";
	private final String PATH_TKB = "https://dkmh.hcmuaf.edu.vn/api/sch/w-locdstkbtuanusertheohocky";
	private final String PATH_LICHTHI = "https://dkmh.hcmuaf.edu.vn/api/epm/w-locdslichthisvtheohocky";
	private final String PATH_DSDOITUONGTKB = "https://dkmh.hcmuaf.edu.vn/api/sch/w-locdsdoituongthoikhoabieuhocky";
	private final String PATH_DSLOPTKB = "https://dkmh.hcmuaf.edu.vn/api/sch/w-locdslopcotkb";
	private final String PATH_DSTKBTHEODOITUONG = "https://dkmh.hcmuaf.edu.vn/api/sch/w-locdstkbhockytheodoituong";
	private final String PATH_DKMH = "https://dkmh.hcmuaf.edu.vn/api/dkmh/w-xulydkmhsinhvien";
	private final CloseableHttpClient client = HttpClients.createDefault();
	static APINLU instance = new APINLU();
	static String token = "";

	public static APINLU getInstance() {
		return instance;
	}


	public String dkmh(Subject sub) {
		HttpPost post = new HttpPost(PATH_DKMH);
		post.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);
		post.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
		String payload = "{filter: {id_to_hoc: \"" + sub.getIdMon() + "\", is_checked: true, sv_nganh: 1}}";
		System.out.println("Ban dang dki mon: " + sub);
		try {
			post.setEntity(new StringEntity(payload));
			HttpEntity entity = client.execute(post).getEntity();
			JSONObject data = new JSONObject(EntityUtils.toString(entity)).getJSONObject("data");
			String thongBaoLoi = data.getString("thong_bao_loi");
			boolean success = data.getBoolean("is_thanh_cong");
			if (success == true) {
				thongBaoLoi = "Đăng ký thành công!";
			}
			return thongBaoLoi;
		} catch (Exception e) {
			return "Đăng ký thất bại";
		}
	}

	public TKB getTKB(String accesstoken, String hocky) {
		TKB tkb = new TKB();
		HttpPost post = new HttpPost(PATH_TKB);
		post.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + accesstoken);
		String payload = "{\"filter\":{\"hoc_ky\":" + hocky
				+ ",\"ten_hoc_ky\":\"\"},\"additional\":{\"paging\":{\"limit\":100,\"page\":1},\"ordering\":[{\"name\":null,\"order_type\":null}]}}";
		post.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");

		try {
			post.setEntity(new StringEntity(payload));
			HttpEntity entity = client.execute(post).getEntity();
			JSONArray data = new JSONObject(EntityUtils.toString(entity, "UTF-8")).getJSONObject("data")
					.getJSONArray("ds_tuan_tkb");
			for (int i = 0; i < data.length(); i++) {
				JSONObject week = data.getJSONObject(i);
				int indexWeek = week.getInt("tuan_hoc_ky");
				Week weekdata = new Week();
				JSONArray lesson = week.getJSONArray("ds_thoi_khoa_bieu");
				for (int j = 0; j < lesson.length(); j++) {
					JSONObject lessonI = lesson.getJSONObject(j);

					weekdata.setLessonOfDay(lessonI.getInt("thu_kieu_so"), new Lesson(lessonI.getString("ma_lop"),
							lessonI.getString("ma_mon"), lessonI.getString("ma_nhom"), lessonI.getString("ma_phong"),
							lessonI.getString("ngay_hoc"), lessonI.getInt("so_tiet"), lessonI.getString("so_tin_chi"),
							lessonI.getString("ten_mon"), lessonI.getString("ten_giang_vien"),
							lessonI.getInt("thu_kieu_so"), lessonI.getInt("tiet_bat_dau")));
				}
				tkb.addWeek(indexWeek, weekdata);

			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return tkb;
	}

	public TKB getTKB(String hocky) {
		TKB tkb = new TKB();
		HttpPost post = new HttpPost(PATH_TKB);
		post.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);
		String payload = "{\"filter\":{\"hoc_ky\":" + hocky
				+ ",\"ten_hoc_ky\":\"\"},\"additional\":{\"paging\":{\"limit\":100,\"page\":1},\"ordering\":[{\"name\":null,\"order_type\":null}]}}";
		post.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");

		try {
			post.setEntity(new StringEntity(payload));
			HttpEntity entity = client.execute(post).getEntity();
			JSONArray data = new JSONObject(EntityUtils.toString(entity, "UTF-8")).getJSONObject("data")
					.getJSONArray("ds_tuan_tkb");
			for (int i = 0; i < data.length(); i++) {
				JSONObject week = data.getJSONObject(i);
				int indexWeek = week.getInt("tuan_hoc_ky");
				Week weekdata = new Week();
				JSONArray lesson = week.getJSONArray("ds_thoi_khoa_bieu");
				for (int j = 0; j < lesson.length(); j++) {
					JSONObject lessonI = lesson.getJSONObject(j);

					weekdata.setLessonOfDay(lessonI.getInt("thu_kieu_so"), new Lesson(lessonI.getString("ma_lop"),
							lessonI.getString("ma_mon"), lessonI.getString("ma_nhom"), lessonI.getString("ma_phong"),
							lessonI.getString("ngay_hoc"), lessonI.getInt("so_tiet"), lessonI.getString("so_tin_chi"),
							lessonI.getString("ten_mon"), lessonI.getString("ten_giang_vien"),
							lessonI.getInt("thu_kieu_so"), lessonI.getInt("tiet_bat_dau")));
				}
				tkb.addWeek(indexWeek, weekdata);

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return tkb;
	}

	public List<Subject> getDSNhomTo(String accesstoken) {
		List<Subject> ds = new ArrayList();
		HttpPost post = new HttpPost(PATH_DSNhomTo);
		post.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + accesstoken);
		String payload = "{is_CVHT: false, additional: {paging: {limit: 8000, page: 1}, ordering: [{name: \"\", order_type: \"\"}]}}\r\n"
				+ "additional\n: \n{paging: {limit: 8000, page: 1}, ordering: [{name: \"\", order_type: \"\"}]}\nis_CVHT: false";
		post.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");

		try {
			post.setEntity(new StringEntity(payload));
			HttpEntity entity = client.execute(post).getEntity();
			JSONArray data = new JSONObject(EntityUtils.toString(entity, "UTF-8")).getJSONObject("data")
					.getJSONArray("ds_nhom_to");
			for (int i = 0; i < data.length(); i++) {
				JSONObject sub = data.getJSONObject(i);
				ds.add(new Subject(sub.getString("ma_mon"), sub.getString("nhom_to"), sub.getString("so_tc"),
						sub.getString("ten_mon"), sub.getString("tkb"), ""));

			}
		} catch (Exception e) {
		}

		return ds;
	}

	public List<Subject> getDSNhomTo() {
		List<Subject> ds = new ArrayList();
		HttpPost post = new HttpPost(PATH_DSNhomTo);
		post.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);
		String payload = "{is_CVHT: false, additional: {paging: {limit: 8000, page: 1}, ordering: [{name: \"\", order_type: \"\"}]}}\r\n"
				+ "additional\n: \n{paging: {limit: 8000, page: 1}, ordering: [{name: \"\", order_type: \"\"}]}\nis_CVHT: false";
		post.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");

		try {
			post.setEntity(new StringEntity(payload));
			HttpEntity entity = client.execute(post).getEntity();
			JSONObject dataToal = new JSONObject(EntityUtils.toString(entity, "UTF-8"));
			JSONArray data = dataToal.getJSONObject("data").getJSONArray("ds_nhom_to");
			JSONArray dataMon = dataToal.getJSONObject("data").getJSONArray("ds_mon_hoc");
			for (int i = 0; i < data.length(); i++) {
				JSONObject sub = data.getJSONObject(i);
				for (int j = 0; j < dataMon.length(); j++) {
					JSONObject mon = dataMon.getJSONObject(j);
					if (sub.getString("ma_mon").equalsIgnoreCase(mon.getString("ma"))) {
						ds.add(new Subject(sub.getString("ma_mon"), sub.getString("nhom_to"), sub.getString("so_tc"),
								mon.getString("ten"), sub.getString("tkb"), "" + sub.getInt("sl_cl"))
								.setIDMon(sub.getString("id_to_hoc")));
						break;
					}
				}

			}
		} catch (Exception e) {
		}

		return ds;
	}

	public LichThi getLichThi(String accesstoken, String hocky) {
		LichThi lichthi = new LichThi();
		HttpPost post = new HttpPost(PATH_LICHTHI);
		post.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + accesstoken);
		String payload = "{\"filter\":{\"hoc_ky\":" + hocky
				+ "},\"additional\":{\"paging\":{\"limit\":100,\"page\":1},\"ordering\":[{\"name\":null,\"order_type\":null}]}}";
		post.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");

		try {
			post.setEntity(new StringEntity(payload));
			HttpEntity entity = client.execute(post).getEntity();
			JSONArray data = new JSONObject(EntityUtils.toString(entity, "UTF-8")).getJSONObject("data")
					.getJSONArray("ds_lich_thi");
			for (int i = 0; i < data.length(); i++) {
				JSONObject sub = data.getJSONObject(i);
				String[] thu = sub.getString("ngay_thi").split("/");
				LocalDate ngayKiểmTra = LocalDate.of(Integer.valueOf(thu[2]), Integer.valueOf(thu[1]),
						Integer.valueOf(thu[0]));

				// Lấy thứ của ngày kiểm tra
				DayOfWeek thudata = ngayKiểmTra.getDayOfWeek();

				int dayOfWeek = thudata.getValue() + 1;
				Lesson subject = new Lesson("", sub.getString("ma_mon"), "", sub.getString("ma_phong"),
						sub.getString("ngay_thi"), Integer.valueOf(sub.getString("so_tiet")), "",
						sub.getString("ten_mon"), "", dayOfWeek, Integer.valueOf(sub.getString("tiet_bat_dau")));
				lichthi.addMonThi(sub.getString("ngay_thi"), subject);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return lichthi;
	}

	public LichThi getLichThi(String hocky) {
		LichThi lichthi = new LichThi();
		HttpPost post = new HttpPost(PATH_LICHTHI);
		post.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);
		String payload = "{\"filter\":{\"hoc_ky\":" + hocky
				+ "},\"additional\":{\"paging\":{\"limit\":100,\"page\":1},\"ordering\":[{\"name\":null,\"order_type\":null}]}}";
		post.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");

		try {
			post.setEntity(new StringEntity(payload));
			HttpEntity entity = client.execute(post).getEntity();
			JSONArray data = new JSONObject(EntityUtils.toString(entity, "UTF-8")).getJSONObject("data")
					.getJSONArray("ds_lich_thi");
			for (int i = 0; i < data.length(); i++) {
				JSONObject sub = data.getJSONObject(i);
				String[] thu = sub.getString("ngay_thi").split("/");
				LocalDate ngayKiểmTra = LocalDate.of(Integer.valueOf(thu[2]), Integer.valueOf(thu[1]),
						Integer.valueOf(thu[0]));

				// Lấy thứ của ngày kiểm tra
				DayOfWeek thudata = ngayKiểmTra.getDayOfWeek();

				int dayOfWeek = thudata.getValue() + 1;
				Lesson subject = new Lesson("", sub.getString("ma_mon"), "", sub.getString("ma_phong"),
						sub.getString("ngay_thi"), Integer.valueOf(sub.getString("so_tiet")), "",
						sub.getString("ten_mon"), "", dayOfWeek, Integer.valueOf(sub.getString("tiet_bat_dau")));
				lichthi.addMonThi(sub.getString("ngay_thi"), subject);
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return lichthi;
	}

	public List<Subject> getDSMHDangKi(String accessToken) {
		List<Subject> ds = new ArrayList();

		HttpPost post = new HttpPost(PATH_DSMHDangKy);
		post.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken);
		String payload = "{is_CVHT: false, is_Clear: false}\nis_CVHT:false \nis_Clear: false";
		post.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
		try {
			post.setEntity(new StringEntity(payload));
			HttpEntity entity = client.execute(post).getEntity();
			JSONArray data = new JSONObject(EntityUtils.toString(entity, "UTF-8")).getJSONObject("data")
					.getJSONArray("ds_kqdkmh");
			for (int i = 0; i < data.length(); i++) {
				JSONObject sb = data.getJSONObject(i);
				JSONObject tohoc = sb.getJSONObject("to_hoc");
				ds.add(new Subject(tohoc.getString("ma_mon"), tohoc.getString("nhom_to"), tohoc.getString("so_tc"),
						tohoc.getString("ten_mon"), tohoc.getString("tkb"), sb.getString("ngay_dang_ky")));
			}
		} catch (Exception e) {
		}
		return ds;
	}

	public List<Subject> getDSMHDangKi() {
		List<Subject> ds = new ArrayList();

		HttpPost post = new HttpPost(PATH_DSMHDangKy);
		post.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);
		String payload = "{is_CVHT: false, is_Clear: false}\nis_CVHT:false \nis_Clear: false";
		post.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
		try {
			post.setEntity(new StringEntity(payload));
			HttpEntity entity = client.execute(post).getEntity();
			JSONArray data = new JSONObject(EntityUtils.toString(entity, "UTF-8")).getJSONObject("data")
					.getJSONArray("ds_kqdkmh");
			for (int i = 0; i < data.length(); i++) {
				JSONObject sb = data.getJSONObject(i);
				JSONObject tohoc = sb.getJSONObject("to_hoc");
				ds.add(new Subject(tohoc.getString("ma_mon"), tohoc.getString("nhom_to"), tohoc.getString("so_tc"),
						tohoc.getString("ten_mon"), tohoc.getString("tkb"), sb.getString("ngay_dang_ky"))
						.setIDMon(tohoc.getString("id_to_hoc")));
			}
		} catch (Exception e) {
		}
		return ds;
	}

	public String getToken(String username, String password) {
		HttpPost post = new HttpPost(PATH_LOGIN);
		List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
		params.add(new BasicNameValuePair("username", username));
		params.add(new BasicNameValuePair("password", password));
		params.add(new BasicNameValuePair("grant_type", "password"));
		try {
			post.setEntity(new UrlEncodedFormEntity(params));
			HttpEntity entity = client.execute(post).getEntity();
			String responseString = EntityUtils.toString(entity, "UTF-8");
			return new JSONObject(responseString).getString("access_token");
		} catch (Exception e) {
			// TODO: handle exception
			return "";
		}

	}

	public Map<Integer, String> getDSDoiTuongTKB(String accessToken) {
		Map<Integer, String> res = new TreeMap<Integer, String>();
		HttpPost post = new HttpPost(PATH_DSDOITUONGTKB);
		post.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken);
		HttpEntity entity;
		try {
			entity = client.execute(post).getEntity();
			JSONArray data = new JSONObject(EntityUtils.toString(entity, "UTF-8")).getJSONObject("data")
					.getJSONArray("ds_doi_tuong_tkb");
			for (int i = 0; i < data.length(); i++) {
				JSONObject o = data.getJSONObject(i);
				res.put(o.getInt("loai_doi_tuong"), o.getString("ten_doi_tuong"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// TODO Auto-generated catch block
		}
		return res;
	}

	public Map<Integer, String> getDSDoiTuongTKB() {
		Map<Integer, String> res = new TreeMap<Integer, String>();
		HttpPost post = new HttpPost(PATH_DSDOITUONGTKB);
		post.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);
		HttpEntity entity;
		try {
			entity = client.execute(post).getEntity();
			JSONArray data = new JSONObject(EntityUtils.toString(entity, "UTF-8")).getJSONObject("data")
					.getJSONArray("ds_doi_tuong_tkb");
			for (int i = 0; i < data.length(); i++) {
				JSONObject o = data.getJSONObject(i);
				res.put(o.getInt("loai_doi_tuong"), o.getString("ten_doi_tuong"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			// TODO Auto-generated catch block
		}
		return res;
	}

	public Map<String, String> getDSLopTKB(String hocki) {
		Map<String, String> res = new TreeMap<String, String>();
		HttpPost post = new HttpPost(PATH_DSLOPTKB);
		post.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);
		String payload = "{filter: {hoc_ky: 20232}}";

		post.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
		try {
			post.setEntity(new StringEntity(payload));
			HttpEntity entity = client.execute(post).getEntity();
			JSONArray data = new JSONObject(EntityUtils.toString(entity, "UTF-8")).getJSONObject("data")
					.getJSONArray("ds_du_lieu");
			for (int i = 0; i < data.length(); i++) {
				JSONObject o = data.getJSONObject(i);
				res.put(o.getString("id_du_lieu"), o.getString("ten_du_lieu"));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}

		return res;
	}

	public List<Subject> loadTKBTheoLop(String id, int doituong, String hocky) {
		List<Subject> ds = new ArrayList<>();
		HttpPost post = new HttpPost(PATH_DSLOPTKB);
		post.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);
		String payload = "{hoc_ky: " + hocky + ", loai_doi_tuong: " + doituong + ", id_du_lieu: " + id + "}";
		HttpEntity entity;
		try {
			entity = client.execute(post).getEntity();
			JSONArray data = new JSONObject(EntityUtils.toString(entity, "UTF-8")).getJSONObject("data")
					.getJSONArray("ds_nhom_to");
			for (int i = 0; i < data.length(); i++) {
				JSONObject sub = data.getJSONObject(i);
				ds.add(new Subject(sub.getString("ma_mon"), sub.getString("nhom_to"), sub.getString("so_tc"),
						sub.getString("ten_mon"), "thứ:" + sub.getString("thu") + ",từ:" + sub.getString("tu_gio") + ","
								+ sub.getString("phong") + ",GV:" + sub.getString("gv"),
						""));
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return ds;
	}

	public static void setToken(String token) {
		APINLU.token = token;
	}

	public String huyDkmh(Subject sub) {
		HttpPost post = new HttpPost(PATH_DKMH);
		post.setHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);
		post.setHeader(HttpHeaders.CONTENT_TYPE, "application/json");
		String payload = "{filter: {id_to_hoc: \"" + sub.getIdMon() + "\", is_checked: false, sv_nganh: 1}}";
		try {
			post.setEntity(new StringEntity(payload));
			HttpEntity entity = client.execute(post).getEntity();
			JSONObject data = new JSONObject(EntityUtils.toString(entity)).getJSONObject("data");
			String thongBaoLoi = data.getString("thong_bao_loi");
			boolean success = data.getBoolean("is_thanh_cong");
			if (success == true) {
				thongBaoLoi = "Hủy đăng ký thành công!";
			}
			return thongBaoLoi;
		} catch (Exception e) {
			return "Hủy đăng ký thất bại";
		}

	}
}
