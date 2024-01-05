package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.temporal.WeekFields;
import java.util.Arrays;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import controller.TKBController;
import model.Lesson;
import model.Week;

public class TKBView extends ViewData implements ActionListener {
	JTable table;
	DefaultTableModel tableModel;
	String[] headers = { "Thứ Hai", "Thứ Ba", "Thứ Tư", "Thứ Năm", "Thứ Sáu", "Thứ Bảy", "Chủ Nhật" };
	int initialWeek = 1;
	JLabel lbTuanCurrent;
	JButton btnPrevious = new JButton("Tuần trước");
	JButton btnNext = new JButton("Tuần sau");
	Map<Integer, String[][]> data;

	public TKBView() {
		super();
		tableModel = new DefaultTableModel();
		table = new JTable(tableModel);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(650, 400));

		for (String header : headers) {
			tableModel.addColumn(header);
		}
		this.add(scrollPane);
		lbTuanCurrent = new JLabel("Tuan hien tai: " + initialWeek);
		btnNext.addActionListener(this);
		btnPrevious.addActionListener(this);
		this.add(btnPrevious);
		this.add(lbTuanCurrent);
		this.add(btnNext);
	}

	public void loadData() {
		table.removeAll();
		data = TKBController.getInstance().loadData();
		int week = TKBController.tuanCurrent;
		initialWeek = week;
		updateTimetable(week);

	}

	public void loadData(Week week) {
		table.removeAll();
		data = TKBController.getInstance().loadData();
		initialWeek = 0;
		updateWeek(week);

	}

	public void updateWeek(Week week) {
		tableModel.setRowCount(0);
		lbTuanCurrent.setText("0");
		String[][] data = new String[7][5];
		for (int i = 0; i < 7; i++) {
			String[] tiet = new String[5];
			try {

				for (Lesson lesson : week.getLessonsOfDay(i + 2)) {

					tiet[(int) lesson.getTiet_bat_dau() / 3] = lesson.getTen_mon() + ", tổ:" + lesson.getMa_nhom();
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
			data[i] = tiet;

		}
		data = reverseColumnsToRows(data);
		for (String[] rowData : data) {
			tableModel.addRow(rowData);
		}

		table.setPreferredSize(new Dimension(700, 400));
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		int columnCount = table.getColumnCount();
		for (int i = 0; i < columnCount; i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}
		MainApp.loadView();

	}

	public void updateTimetable(int week) {
		tableModel.setRowCount(0); // Xóa dữ liệu hiện tại trong bảng

		lbTuanCurrent.setText("Tuần hiện tại: " + week);
		try {
			String[][] dataz = reverseColumnsToRows(data.get(week)); // Lấy dữ liệu thời khóa biểu cho tuần hiện tại
			if (dataz != null) {
				for (String[] rowData : dataz) {
					tableModel.addRow(rowData);
				}

				table.setPreferredSize(new Dimension(650, 400));
				DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
				centerRenderer.setHorizontalAlignment(JLabel.CENTER);
				int columnCount = table.getColumnCount();
				for (int i = 0; i < columnCount; i++) {
					table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
				}
				MainApp.loadView();
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	public static String[][] reverseColumnsToRows(String[][] matrix) {
		int numRows = matrix.length;
		int numCols = matrix[0].length;

		String[][] reversedMatrix = new String[numCols][numRows];

		for (int j = 0; j < numCols; j++) {
			for (int i = 0; i < numRows; i++) {
				reversedMatrix[j][i] = matrix[i][j];
			}
		}

		return reversedMatrix;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource().equals(btnPrevious)) {
			if (initialWeek > 1) {
				initialWeek--;
				updateTimetable(initialWeek);
			}
		}
		if (e.getSource().equals(btnNext)) {
			initialWeek++;
			updateTimetable(initialWeek);

		}

	}

}
