package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainApp extends JFrame implements ActionListener {
	List<ViewData> views = new ArrayList<>();
	int currentView = 0;// 0 is login view
	ViewData loginView = new LoginView();
	ViewData lichThiView = new LichThiView();
	ViewData dkmhView = new DKMHView();
	ViewData tkbView = new TKBView();
	ViewData cacMonDKView = new CacMonDKView();
	ViewData tkbHocKyView = new TKBHocKiView();
	JPanel sidebar = new JPanel();
	JButton btnLogin = new JButton("Đăng nhập");
	JButton btnTKB = new JButton("Thời khóa biểu");
	JButton btnLichThi = new JButton("Lịch thi");
	JButton btnDangKyMH = new JButton("ĐKMH");
	JButton btnCacMonDangKy = new JButton("Các môn ĐK");
	JButton btnTKBHocKy = new JButton("TKB Hoc Ky");
	static MainApp instance = new MainApp();

	public MainApp() {
		this.setSize(800, 600);
		this.setTitle("NLU-Tool");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.add(loginView, BorderLayout.CENTER);

		sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS));
		sidebar.add(btnLogin);
		sidebar.add(btnLichThi);
		sidebar.add(btnTKB);
		sidebar.add(btnTKBHocKy);
		sidebar.add(btnDangKyMH);
		sidebar.add(btnCacMonDangKy);

		this.add(sidebar, BorderLayout.EAST);
		this.setVisible(true);

		btnLogin.addActionListener(this);
		btnLichThi.addActionListener(this);
		btnDangKyMH.addActionListener(this);
		btnTKB.addActionListener(this);
		btnCacMonDangKy.addActionListener(this);
		btnTKBHocKy.addActionListener(this);
		views.add(loginView);
		views.add(lichThiView);
		views.add(dkmhView);
		views.add(cacMonDKView);
		views.add(tkbView);
		views.add(tkbHocKyView);

	}

	public static void main(String[] args) {
//		MainApp.getInstance();
	}

	public static MainApp getInstance() {
		return instance;
	}

	public static void loadView() {
		instance.setSize(779, 600);
		instance.setSize(800, 600);
		instance.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		this.getContentPane().remove(views.get(currentView));
		if (e.getSource().equals(btnLogin)) {
			this.getContentPane().add(views.get(0), BorderLayout.CENTER);
			currentView = 0;
		} else if (e.getSource().equals(btnLichThi)) {

			this.getContentPane().add(views.get(1), BorderLayout.CENTER);
			try {
				((LichThiView) lichThiView).loadData();
			} catch (Exception e1) {
				// TODO: handle exception
			}

			currentView = 1;
		} else if (e.getSource().equals(btnDangKyMH)) {
			this.getContentPane().add(views.get(2), BorderLayout.CENTER);
			currentView = 2;
			((DKMHView) dkmhView).loadData();
		} else if (e.getSource().equals(btnCacMonDangKy)) {
			this.getContentPane().add(views.get(3), BorderLayout.CENTER);
			currentView = 3;
			((CacMonDKView) cacMonDKView).loadData();
		} else if (e.getSource().equals(btnTKB)) {
			this.getContentPane().add(views.get(4), BorderLayout.CENTER);
			currentView = 4;
			((TKBView) tkbView).loadData();
		} else if (e.getSource().equals(btnTKBHocKy)) {
			this.getContentPane().add(views.get(5), BorderLayout.CENTER);
			currentView = 5;
			((TKBHocKiView) tkbHocKyView).loadData();
		}

		instance.setSize(779, 600);
		instance.setSize(800, 600);
		instance.setVisible(true);

	}
}
