package cpm.module.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import cpm.module.fileUtils.BookingsFileUtil;
import cpm.module.fileUtils.DiscountFileUtil;
import cpm.module.model.LoggedUser;
import cpm.module.model.book.Booking;
import cpm.module.model.book.Discount;
import cpm.module.model.book.User;

public class PnClientData extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private User user;
	private MainWindow mainWindow;
	private Booking booking;
	private JButton btnFinish;
	private JButton btnCancel;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JPanel panel_1;
	private JLabel lblName;
	private JLabel lblId;
	private JLabel lblSurname;
	private JLabel lblEmail;
	private JTextField txtName;
	private JTextField txtSurname;
	private JTextField txtId;
	private JTextField txtEmail;
	private JLabel lblComents;
	private JTextField txtComents;

	/**
	 * Create the panel.
	 */
	public PnClientData(MainWindow mainWindow, Booking booking) {
		user = LoggedUser.getInstance();
		this.booking = booking;
		this.mainWindow = mainWindow;
		setBackground(Color.BLACK);
		setLayout(new BorderLayout(0, 0));
		add(getPanel(), BorderLayout.SOUTH);
		add(getLblNewLabel(), BorderLayout.WEST);
		add(getLblNewLabel_1(), BorderLayout.EAST);
		add(getPanel_1(), BorderLayout.CENTER);
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBackground(Color.BLACK);
			FlowLayout flowLayout = (FlowLayout) panel.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			panel.add(getBtnFinish());
			panel.add(getBtnCancel());
		}
		return panel;
	}

	public void update(Booking booking) {
		this.booking = booking;
	}

	private JButton getBtnFinish() {
		if (btnFinish == null) {
			btnFinish = new JButton("Finish");
			btnFinish.setBackground(new Color(60, 179, 113));
			btnFinish.setMnemonic('f');
			btnFinish.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (isAllDataOk()) {
						saveData();
						showMessageDialog("Booking has been saved.", "Success");
						mainWindow.showNext(MainWindow.CASTLE_LIST_PANEL);
					}
				}
			});
			btnFinish.setFont(new Font("Tahoma", Font.PLAIN, 15));
		}
		return btnFinish;
	}

	private void showMessageDialog(String message, String title) {
		JOptionPane.showMessageDialog(this, message, title, JOptionPane.INFORMATION_MESSAGE);

	}

	private void saveData() {
		if (user == null) {
			user = LoggedUser.getInstance(getTxtId().getText());
		}
		user.setEmail(getTxtEmail().getText());
		user.setName(getTxtName().getText());
		user.setSurname(getTxtSurname().getText());
		booking.setComments(getTxtComents().getText());
		booking.setUser(user);

		BookingsFileUtil.saveToFile(booking);

		if (booking.isDiscountApplied()) {
			List<Discount> discounts = DiscountFileUtil.loadFile();
			discounts.removeIf(d -> d.getId().equals(user.getId()));
			DiscountFileUtil.saveToFile(discounts);
		}
	}

	private boolean isAllDataOk() {
		if (getTxtName().getText().isEmpty()) {
			showMessageDialog("Please insert a name.", "Error");
			return false;
		}
		if (getTxtSurname().getText().isEmpty()) {
			showMessageDialog("Please insert a surname.", "Error");
			return false;
		}
		if (getTxtId().getText().isEmpty()) {
			showMessageDialog("Please insert an id.", "Error");
			return false;
		}

		// email checking
		String email = getTxtEmail().getText();
		if (email.isEmpty()) {
			showMessageDialog("Please insert an email.", "Error");
			return false;
		}
		String[] splittedEmail = email.split("@");
		if (splittedEmail.length < 2) {
			showMessageDialog("Please insert a valid email.", "Error");
			return false;
		}
		String secondPEmail = splittedEmail[1];
		String[] splitted2ndPEmail = secondPEmail.split("\\.");
		if (splitted2ndPEmail.length < 2) {
			showMessageDialog("Please insert a valid email.", "Error");
			return false;
		}

		return true;
	}

	private JButton getBtnCancel() {
		if (btnCancel == null) {
			btnCancel = new JButton("Cancel");
			btnCancel.setMnemonic('l');
			btnCancel.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					mainWindow.showNext(MainWindow.CASTLE_FORM_PANEL);
				}
			});
			btnCancel.setBackground(new Color(178, 34, 34));
			btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		}
		return btnCancel;
	}

	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("");
			lblNewLabel.setIcon(new ImageIcon(PnClientData.class.getResource("/img/clientsDataGhost2.png")));
		}
		return lblNewLabel;
	}

	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("");
			lblNewLabel_1.setIcon(new ImageIcon(PnClientData.class.getResource("/img/clientsDataGhost.png")));
		}
		return lblNewLabel_1;
	}

	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setBackground(Color.BLACK);
			panel_1.setLayout(new GridLayout(0, 2, 0, 40));
			panel_1.add(getLblName());
			panel_1.add(getTxtName());
			panel_1.add(getLblSurname());
			panel_1.add(getTxtSurname());
			panel_1.add(getLblId());
			panel_1.add(getTxtId());
			panel_1.add(getLblEmail());
			panel_1.add(getTxtEmail());
			panel_1.add(getLblComents());
			panel_1.add(getTxtComents());
		}
		return panel_1;
	}

	private JLabel getLblName() {
		if (lblName == null) {
			lblName = new JLabel("    Name:");
			lblName.setLabelFor(getTxtName());
			lblName.setDisplayedMnemonic('m');
			lblName.setBackground(Color.BLACK);
			lblName.setForeground(Color.WHITE);
			lblName.setFont(new Font("Tahoma", Font.PLAIN, 17));
		}
		return lblName;
	}

	private JLabel getLblId() {
		if (lblId == null) {
			lblId = new JLabel("     Id:");
			lblId.setDisplayedMnemonic('d');
			lblId.setLabelFor(getTxtId());
			lblId.setBackground(Color.BLACK);
			lblId.setForeground(Color.WHITE);
			lblId.setFont(new Font("Tahoma", Font.PLAIN, 17));
		}
		return lblId;
	}

	private JLabel getLblSurname() {
		if (lblSurname == null) {
			lblSurname = new JLabel("    Surname:");
			lblSurname.setDisplayedMnemonic('s');
			lblSurname.setLabelFor(getTxtSurname());
			lblSurname.setBackground(Color.BLACK);
			lblSurname.setForeground(Color.WHITE);
			lblSurname.setFont(new Font("Tahoma", Font.PLAIN, 17));
		}
		return lblSurname;
	}

	private JLabel getLblEmail() {
		if (lblEmail == null) {
			lblEmail = new JLabel("     Contact email:");
			lblEmail.setLabelFor(getTxtEmail());
			lblEmail.setDisplayedMnemonic('t');
			lblEmail.setBackground(Color.BLACK);
			lblEmail.setForeground(Color.WHITE);
			lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 17));
		}
		return lblEmail;
	}

	private JTextField getTxtName() {
		if (txtName == null) {
			txtName = new JTextField();
			txtName.setFont(new Font("Tahoma", Font.PLAIN, 17));
			txtName.setColumns(10);
		}
		return txtName;
	}

	private JTextField getTxtSurname() {
		if (txtSurname == null) {
			txtSurname = new JTextField();
			txtSurname.setFont(new Font("Tahoma", Font.PLAIN, 17));
			txtSurname.setColumns(10);
		}
		return txtSurname;
	}

	private JTextField getTxtId() {
		if (txtId == null) {
			txtId = new JTextField();
			txtId.setFont(new Font("Tahoma", Font.PLAIN, 17));
			txtId.setColumns(10);
			if (user != null) {
				txtId.setText(user.getId());
				txtId.setEditable(false);
				txtId.setEnabled(false);
			}
		}
		return txtId;
	}

	private JTextField getTxtEmail() {
		if (txtEmail == null) {
			txtEmail = new JTextField();
			txtEmail.setFont(new Font("Tahoma", Font.PLAIN, 17));
			txtEmail.setColumns(10);
		}
		return txtEmail;
	}

	private JLabel getLblComents() {
		if (lblComents == null) {
			lblComents = new JLabel("     Coments:");
			lblComents.setDisplayedMnemonic('o');
			lblComents.setLabelFor(getTxtComents());
			lblComents.setFont(new Font("Tahoma", Font.PLAIN, 17));
			lblComents.setBackground(Color.BLACK);
			lblComents.setForeground(Color.WHITE);
		}
		return lblComents;
	}

	private JTextField getTxtComents() {
		if (txtComents == null) {
			txtComents = new JTextField();
			txtComents.setFont(new Font("Tahoma", Font.PLAIN, 17));
			txtComents.setColumns(10);
		}
		return txtComents;
	}
}
