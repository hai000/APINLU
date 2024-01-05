package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.PatternSyntaxException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableRowSorter;

import controller.DKMHController;
import model.APINLU;
import model.Lesson;
import model.Subject;
import model.TKB;
import model.ToolSortTKB;
import model.Week;

public class DKMHView extends ViewData implements ActionListener {
	private JTable table;
	private DefaultTableModel model;
	private JButton searchButton = new JButton("Tìm");
	private JTextField searchField;
	private JPanel panelDKI = new JPanel();
	JTextField textTo = new JTextField(12);
	JButton btnDangKy = new JButton("Đăng Ký");
	JButton btnHuyMon = new JButton("Hủy môn đky");
	int[] selectedRows = {};
	ToolSortTKB toolTKB;
	int i = 0;
	TableRowSorter<DefaultTableModel> sorter;
	JPanel searchPanel = new JPanel();
	String[] columnNames = { "Mã MH", "Tên môn", "Nhóm MH", "STC","SL_CL", "TKB", "TBD","ID"};

	public DKMHView() {
		super();

		// Tạo TableRowSorter để sắp xếp và tìm kiếm

//		textMamh.setText("Mã môn học..");
//		panelDKI.add(textMamh);
		panelDKI.add(textTo);
		panelDKI.add(btnDangKy);
		panelDKI.add(btnHuyMon);
//		panelSortTKB.add(btnThemTKBSort);
//		panelSortTKB.add(btnThemLab);
//		panelSortTKB.add(btnSort);
//		panelSortTKB.add(btnRemoveSub);

		searchField = new JTextField(15);
		searchPanel.add(new JLabel("Tìm kiếm:"));
		searchPanel.add(searchField);
		searchPanel.add(searchButton);
		btnDangKy.addActionListener(this);

		btnHuyMon.addActionListener(this);
		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String searchText = searchField.getText();
				if (searchText.trim().length() == 0) {
					sorter.setRowFilter(null); // Xóa bộ lọc nếu không có từ khóa tìm kiếm
				} else {
					try {
						// Sử dụng Regular Expression để tìm kiếm
						sorter.setRowFilter(RowFilter.regexFilter("(?i)" + searchText));
					} catch (PatternSyntaxException ex) {
						ex.printStackTrace();
					}
				}
			}
		});

		// Tạo JPanel chứa JTextField và JButton

	}

	public void loadData() {
		String[][] data = DKMHController.getInstance().loadDataMH();
		toolTKB = new ToolSortTKB();
		i = 0;
		model = new DefaultTableModel(data, columnNames);
		table = new JTable(data, columnNames);
		sorter = new TableRowSorter<>(model);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.setRowSorter(sorter);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(680, 400));
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		table.setPreferredScrollableViewportSize(new Dimension(500, 400));
		for (int i = 0; i < 5; i++) {
			TableColumn column = table.getColumnModel().getColumn(i);
			column.setPreferredWidth(110);
			if (i == 3 || i == 2) {
				column.setPreferredWidth(55);
			} else if (i == 1) {
				column.setPreferredWidth(200);
			} else if (i == 4) {
				column.setPreferredWidth(260);
			}

		}
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);
		int columnCount = table.getColumnCount();
		for (int i = 0; i < columnCount; i++) {
			table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}
		ListSelectionModel selectionModel = table.getSelectionModel();
		selectionModel.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

		// Lắng nghe sự kiện khi người dùng chọn dòng
		selectionModel.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				selectedRows = table.getSelectedRows();

			}
		});

		this.removeAll();

		// Tạo JScrollPane để chứa JTable
		add(searchPanel, BorderLayout.NORTH);
		add(scrollPane, BorderLayout.CENTER);
		add(panelDKI, BorderLayout.SOUTH);
//		add(panelSortTKB, BorderLayout.SOUTH);
		MainApp.loadView();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(btnDangKy)) {
			if (textTo.getText().isEmpty()) {
				System.out.println("Bạn đang đki với  table");

				for (Integer i : selectedRows) {

					JOptionPane.showMessageDialog(null,
							DKMHController.getInstance().dkmh(String.valueOf(table.getValueAt(i, 5))));

				}
			} else {
				JOptionPane.showMessageDialog(null, DKMHController.getInstance().dkmh(textTo.getText().trim()));

			}
		}
		if (e.getSource().equals(btnHuyMon)) {

			if (textTo.getText().isEmpty()) {
				System.out.println("Bạn đang hủy môn với  table");

				for (Integer i : selectedRows) {

					JOptionPane.showMessageDialog(null,
							DKMHController.getInstance().huyDkmh(String.valueOf(table.getValueAt(i, 5))));

				}
			} else {
				JOptionPane.showMessageDialog(null, DKMHController.getInstance().huyDkmh(textTo.getText().trim()));

			}
		}

	}

}
