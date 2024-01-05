package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import controller.LichThiController;

public class LichThiView extends ViewData implements ActionListener {
	DefaultTableModel model = null;
	String[] columnNames = { "Ngày thi", "Thứ", "Mã môn", "Tên môn", "Tiết bắt đầu", "Số tiết" };
	Font font = new Font("Arial", Font.PLAIN, 13);
	String[][] dataRow = {};
	JTable table = null;
	JButton btnLoadData = new JButton("Làm mới");
	JTextField txtHocKi = new JTextField(10);

	public LichThiView() {
		model = new DefaultTableModel(dataRow, columnNames);
		JTable table = new JTable(model);
		btnLoadData.addActionListener(this);
	}

	public void loadData() {
		String[][] data = LichThiController.getInstance().loadData(txtHocKi.getText());
		table = new JTable(data, columnNames);

		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		JScrollPane scrollPane = new JScrollPane(table);
		table.setFont(font);
		scrollPane.setPreferredSize(new Dimension(700, 400));
		table.setPreferredSize(new Dimension(700, 400));
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		int columnCount = table.getColumnCount();
		for (int i = 0; i < columnCount; i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}
//
		this.removeAll();
		this.add(txtHocKi);
		this.add(scrollPane);
		this.add(btnLoadData);
		MainApp.loadView();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource().equals(btnLoadData)) {
			loadData();

		}

	}
}
