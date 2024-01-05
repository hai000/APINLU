package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import controller.CacMonDKController;

public class CacMonDKView extends ViewData implements ActionListener {
	JButton loadData = new JButton("Làm mới");
	private JTable table;
	private DefaultTableModel model;
	String[] columnNames = { "Mã môn", "Tên môn", "Tổ", "Ngày đăng ký", "TKB" };

	public CacMonDKView() {
		super();
		loadData.addActionListener(this);
	}

	public void loadData() {
		String[][] data = CacMonDKController.getInstance().loadData();
		model = new DefaultTableModel(data, columnNames);
		table = new JTable(data, columnNames);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(700, 400));
		table.setPreferredSize(new Dimension(700, 400));
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(JLabel.CENTER);

		int columnCount = table.getColumnCount();
		table.getColumnModel().getColumn(2).setPreferredWidth(50);
		for (int i = 0; i < columnCount; i++) {

			table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
		}

		this.removeAll();
		add(scrollPane, BorderLayout.CENTER);
		add(loadData);
		MainApp.loadView();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource().equals(loadData)) {
			loadData();
			MainApp.loadView();
		}

	}

}
