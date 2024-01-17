package cpm.module.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class IdentificationDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panel;
	private JPanel panel_1;
	private JTextField txtId;
	private JLabel lblId;
	private JButton btnContinue;
	private JButton btnBack;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;

	/**
	 * Create the dialog.
	 */
	public IdentificationDialog() {
		setBackground(Color.BLACK);
		setModal(true);
		getContentPane().setBackground(Color.BLACK);
		setResizable(false);
		setTitle("GhostBusters: Identification");
		setBounds(100, 100, 676, 395);
		getContentPane().setLayout(new BorderLayout(0, 0));
		getContentPane().add(getPanel(), BorderLayout.SOUTH);
		getContentPane().add(getPanel_1(), BorderLayout.CENTER);
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBackground(Color.BLACK);
			for (int i = 0; i < 7; i++) {
				JLabel label = new JLabel();
				label.setIcon(new ImageIcon(IdentificationDialog.class.getResource("/img/identificationGhost.png")));
				panel.add(label);
			}
		}
		return panel;
	}

	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setBackground(Color.BLACK);
			panel_1.setLayout(null);
			panel_1.add(getTxtId());
			panel_1.add(getLblId());
			panel_1.add(getBtnContinue());
			panel_1.add(getBtnBack());
			panel_1.add(getLblNewLabel());
			panel_1.add(getLblNewLabel_1());
		}
		return panel_1;
	}

	public JTextField getTxtId() {
		if (txtId == null) {
			txtId = new JTextField();
			txtId.setBounds(79, 135, 311, 30);
			txtId.setColumns(10);
		}
		return txtId;
	}

	private JLabel getLblId() {
		if (lblId == null) {
			lblId = new JLabel("Enter your ID:");
			lblId.setForeground(Color.WHITE);
			lblId.setDisplayedMnemonic('i');
			lblId.setLabelFor(getTxtId());
			lblId.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblId.setBounds(79, 108, 311, 17);
		}
		return lblId;
	}

	private JButton getBtnContinue() {
		if (btnContinue == null) {
			btnContinue = new JButton("Continue");
			btnContinue.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					String id = getTxtId().getText();
					if (id != null && !id.isEmpty()) {
						dispose();
					} else {
						showErrorMessage();
					}
				}
			});
			btnContinue.setMnemonic('c');
			btnContinue.setBackground(new Color(100, 149, 237));
			btnContinue.setFont(new Font("Tahoma", Font.PLAIN, 14));
			btnContinue.setBounds(433, 135, 115, 30);
		}
		return btnContinue;
	}

	private void showErrorMessage() {
		JOptionPane.showMessageDialog(this, "No id inserted", "Ups...", JOptionPane.ERROR_MESSAGE);
	}

	private JButton getBtnBack() {
		if (btnBack == null) {
			btnBack = new JButton("Back");
			btnBack.setBackground(new Color(178, 34, 34));
			btnBack.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					dispose();
				}
			});
			btnBack.setMnemonic('b');
			btnBack.setFont(new Font("Tahoma", Font.PLAIN, 14));
			btnBack.setBounds(433, 175, 115, 30);
		}
		return btnBack;
	}

	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("If you don't provide your ID, the discount would be lost");
			lblNewLabel.setForeground(Color.WHITE);
			lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblNewLabel.setBounds(79, 175, 344, 17);
		}
		return lblNewLabel;
	}

	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("If you already have a discount, the new one won't be saved");
			lblNewLabel_1.setForeground(Color.WHITE);
			lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 13));
			lblNewLabel_1.setBounds(79, 202, 353, 17);
		}
		return lblNewLabel_1;
	}
}
