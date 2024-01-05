package view;

import java.util.Arrays;
import java.util.Map;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import model.APINLU;

public class TKBHocKiView extends ViewData {
	Map<Integer, String> doiTuongTKB = null;
	JTextField txtHocKy = new JTextField(10);
	JComboBox<String> comboBoxLop = new JComboBox<>();

	public TKBHocKiView() {
		super();

		this.add(txtHocKy);
		this.add(comboBoxLop);

	}

	public void loadData() {
		doiTuongTKB = APINLU.getInstance().getDSDoiTuongTKB();
		String[] data = new String[doiTuongTKB.size()];
		int i = 0;
		for (Map.Entry<Integer, String> entry : doiTuongTKB.entrySet()) {
			String val = entry.getValue();
			data[i] = val;
			i++;

		}
		DefaultComboBoxModel<String> comboBoxModel = new DefaultComboBoxModel<>(data);
		comboBoxLop = new JComboBox<>(comboBoxModel);
		this.removeAll();
		this.add(comboBoxLop);
		MainApp.loadView();
	}

}
