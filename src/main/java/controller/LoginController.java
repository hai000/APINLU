package controller;

import model.APINLU;

public class LoginController {
	static LoginController instance = new LoginController();

	public boolean checkLogin(String username, String password) {
		String token = APINLU.getInstance().getToken(username, password);
		if (!token.isEmpty()) {
			APINLU.setToken(token);
			return true;
		}
		return false;
	}

	public static LoginController getInstance() {
		return instance;
	}

}
