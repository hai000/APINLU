package view;

import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import controller.LoginController;
import model.APINLU;

public class LoginView extends ViewData implements ActionListener {
	JTextField username = new JTextField(10);
	JPasswordField password = new JPasswordField(10);
	JButton btnDangNhap = new JButton("Đăng nhập");
	JLabel notify = new JLabel("Đăng nhập thất bại");

	public LoginView() {
		this.setLayout(null);
		JLabel lbUsername = new JLabel("Tên người dùng:");
		JLabel lbPassword = new JLabel("Mật khẩu:");
		this.add(lbUsername);
		lbUsername.setBounds(200, 200, 100, 25);
		username.setBounds(300, 200, 100, 25);
		lbPassword.setBounds(200, 250, 100, 25);
		password.setBounds(300, 250, 100, 25);
		btnDangNhap.setBounds(250, 300, 100, 25);
		notify.setBounds(225, 350, 200, 25);
		notify.setVisible(false);

		this.add(username);
		this.add(lbPassword);
		this.add(password);
		this.add(btnDangNhap);
		this.add(notify);
		btnDangNhap.addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnDangNhap)) {
			if (LoginController.getInstance().checkLogin(username.getText(), password.getText())) {
				notify.setText("Đăng nhập thành công");
				notify.setVisible(true);
			} else {
				notify.setText("Đăng nhập thất bại");
				notify.setVisible(true);
			}

		}

	}
}