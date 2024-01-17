package cpm.module.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import cpm.module.model.LoggedUser;
import cpm.module.model.book.Booking;
import cpm.module.model.book.Castle;
import cpm.module.model.book.Enchantment;

public class PnBookingForm extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MainWindow mainWindow;
	private Castle castle = null;
	private JLabel lblCastleImage;
	private JPanel panel;
	private JPanel pnInfo;
	private JPanel pnBottom;
	private JPanel pnForm;
	private JButton btnCancel;
	private JButton btnNext;
	private JLabel lblTitleName;
	private JLabel lblName;
	private JLabel lblTitleDescription;
	private JLabel lblDescription;
	private JLabel lblTitleCountry;
	private JLabel lblCountry;
	private JLabel lblTitlePrice;
	private JLabel lblPrice;
	private JLabel lblTitleEnchantments;
	private JLabel lblEnchantments;
	private JPanel panel_2;
	private JLabel lblTitleInformation;
	private JLabel lblNPpl;
	private JSpinner spNPpl;
	private JLabel lblDate;
	private JLabel lblNights;
	private JSpinner spNights;
	private JLabel lblRooms;
	private JSpinner spRooms;
	private JPanel panel_1;
	private JPanel pnButtons;
	private JPanel pnGhosts;
	private JSpinner spDate;

	/**
	 * Create the panel.
	 */
	public PnBookingForm(MainWindow mainWindow, Castle castle) {
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				adaptImage();
			}
		});
		setBackground(Color.BLACK);
		this.mainWindow = mainWindow;
		this.castle = castle;
		setLayout(new BorderLayout(0, 0));
		add(getLblCastleImage(), BorderLayout.WEST);
		add(getPanel(), BorderLayout.CENTER);
		add(getPnBottom(), BorderLayout.SOUTH);
	}

	public void update(Castle castle) {
		this.castle = castle;
		getLblName().setText(castle.getName());
		getLblDescription().setText(castle.getDescription());
		getLblDescription().setToolTipText(castle.getDescription());
		getLblCountry().setText(castle.getCountry());
		getLblPrice().setText(castle.getPrice() + "€/night/room");
		showEnchantments();
		adaptImage();
	}

	private void showEnchantments() {
		List<Enchantment> eList = castle.getEnchantments();
		String enchantments = "";
		for (int i = 0; i < eList.size(); i++) {
			if (i == eList.size() - 1) {
				enchantments += eList.get(i).toString();
			} else {
				enchantments += eList.get(i).toString() + ", ";
			}
		}
		getLblEnchantments().setText(enchantments);
	}

	private JLabel getLblCastleImage() {
		if (lblCastleImage == null) {
			lblCastleImage = new JLabel("");
		}
		return lblCastleImage;
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setForeground(Color.WHITE);
			panel.setBackground(Color.BLACK);
			panel.setLayout(new BorderLayout(0, 0));
			panel.add(getPnInfo(), BorderLayout.NORTH);
			panel.add(getPnForm(), BorderLayout.CENTER);
		}
		return panel;
	}

	private JPanel getPnInfo() {
		if (pnInfo == null) {
			pnInfo = new JPanel();
			pnInfo.setForeground(Color.WHITE);
			pnInfo.setBackground(Color.BLACK);
			pnInfo.setBorder(new LineBorder(new Color(112, 128, 144), 4));
			pnInfo.setLayout(new BorderLayout(0, 0));
			pnInfo.add(getPanel_2(), BorderLayout.CENTER);
			pnInfo.add(getLblTitleInformation(), BorderLayout.NORTH);
			pnInfo.add(getPanel_1(), BorderLayout.WEST);
		}
		return pnInfo;
	}

	private JPanel getPnBottom() {
		if (pnBottom == null) {
			pnBottom = new JPanel();
			pnBottom.setForeground(Color.WHITE);
			pnBottom.setBackground(Color.BLACK);
			pnBottom.setLayout(new BorderLayout(0, 0));
			pnBottom.add(getPnButtons(), BorderLayout.EAST);
			pnBottom.add(getPnGhosts(), BorderLayout.CENTER);
		}
		return pnBottom;
	}

	private JLabel getLblGhost() {
		JLabel lblGhost = new JLabel("");
		lblGhost.setIcon(new ImageIcon(PnBookingForm.class.getResource("/img/bookingFormGhost.png")));
		return lblGhost;
	}

	private JPanel getPnForm() {
		if (pnForm == null) {
			pnForm = new JPanel();
			pnForm.setForeground(Color.WHITE);
			pnForm.setBackground(Color.BLACK);
			pnForm.setLayout(new GridLayout(0, 2, 10, 10));
			pnForm.add(getLabel_1());
			pnForm.add(getSpNPpl());
			pnForm.add(getLabel_2());
			pnForm.add(getSpDate());
			pnForm.add(getLabel_3());
			pnForm.add(getSpNights());
			pnForm.add(getLabel_4());
			pnForm.add(getSpRooms());
		}
		return pnForm;
	}

	private JButton getBtnCancel() {
		if (btnCancel == null) {
			btnCancel = new JButton("Cancel");
			btnCancel.setMnemonic('n');
			btnCancel.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					mainWindow.showNext(MainWindow.CASTLE_LIST_PANEL);
				}
			});
			btnCancel.setBackground(new Color(178, 34, 34));
			btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		}
		return btnCancel;
	}

	private JButton getBtnNext() {
		if (btnNext == null) {
			btnNext = new JButton("Next");
			btnNext.setMnemonic('x');
			btnNext.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (isAllInfoCorrect()) {
						showNextPanel();
					}
				}
			});
			btnNext.setFont(new Font("Tahoma", Font.PLAIN, 15));
		}
		return btnNext;
	}

	/**
	 * Gets all the necesary values to create the booking and passes the information
	 * to the confirmation panel through mainWindow.
	 */
	private void showNextPanel() {
		Date date = (Date) getSpDate().getValue();
		int days = (int) getSpNights().getValue();
		int rooms = (int) getSpRooms().getValue();
		int people = (int) getSpNPpl().getValue();
		double finalPrice = days * castle.getPrice() * rooms;
		Booking booking = null;
		booking = new Booking(LoggedUser.getInstance(), castle.getCode(), castle.getName(), date, days, rooms, people,
				finalPrice);

		mainWindow.showBookingConfirmation(booking);

	}

	private boolean isAllInfoCorrect() {
		if (((int) getSpNPpl().getValue()) < 1) {
			showErrorDialog("The number of people should be higher than 0.");
			return false;
		}

		if (((int) getSpNights().getValue()) < 1) {
			showErrorDialog("The number of nights should be higher than 0.");
			return false;
		}

		if (((int) getSpRooms().getValue()) < 1) {
			showErrorDialog("The number of rooms should be higher than 0.");
			return false;
		}

		return true;
	}

	private void showErrorDialog(String message) {
		JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);

	}

	private JLabel getLblTitleName() {
		if (lblTitleName == null) {
			lblTitleName = new JLabel("    Name:");
			lblTitleName.setForeground(Color.WHITE);
			lblTitleName.setHorizontalAlignment(SwingConstants.LEFT);
			lblTitleName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		}
		return lblTitleName;
	}

	private JLabel getLblName() {
		if (lblName == null) {
			lblName = new JLabel(castle.getName());
			lblName.setForeground(Color.WHITE);
			lblName.setFont(new Font("Tahoma", Font.PLAIN, 15));
		}
		return lblName;
	}

	private JLabel getLblTitleDescription() {
		if (lblTitleDescription == null) {
			lblTitleDescription = new JLabel("    Description:");
			lblTitleDescription.setForeground(Color.WHITE);
			lblTitleDescription.setHorizontalAlignment(SwingConstants.LEFT);
			lblTitleDescription.setFont(new Font("Tahoma", Font.PLAIN, 15));
		}
		return lblTitleDescription;
	}

	private JLabel getLblDescription() {
		if (lblDescription == null) {
			lblDescription = new JLabel(castle.getDescription());
			lblDescription.setForeground(Color.WHITE);
			lblDescription.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblDescription.setToolTipText(castle.getDescription());

		}
		return lblDescription;
	}

	private JLabel getLblTitleCountry() {
		if (lblTitleCountry == null) {
			lblTitleCountry = new JLabel("    Country:");
			lblTitleCountry.setForeground(Color.WHITE);
			lblTitleCountry.setHorizontalAlignment(SwingConstants.LEFT);
			lblTitleCountry.setFont(new Font("Tahoma", Font.PLAIN, 15));
		}
		return lblTitleCountry;
	}

	private JLabel getLblCountry() {
		if (lblCountry == null) {
			lblCountry = new JLabel(castle.getCountry());
			lblCountry.setForeground(Color.WHITE);
			lblCountry.setFont(new Font("Tahoma", Font.PLAIN, 15));
		}
		return lblCountry;
	}

	private JLabel getLblTitlePrice() {
		if (lblTitlePrice == null) {
			lblTitlePrice = new JLabel("    Room price:");
			lblTitlePrice.setForeground(Color.WHITE);
			lblTitlePrice.setHorizontalAlignment(SwingConstants.LEFT);
			lblTitlePrice.setFont(new Font("Tahoma", Font.PLAIN, 15));
		}
		return lblTitlePrice;
	}

	private JLabel getLblPrice() {
		if (lblPrice == null) {
			lblPrice = new JLabel(Integer.toString(castle.getPrice()));
			lblPrice.setForeground(Color.WHITE);
			lblPrice.setFont(new Font("Tahoma", Font.PLAIN, 15));
		}
		return lblPrice;
	}

	private JLabel getLblTitleEnchantments() {
		if (lblTitleEnchantments == null) {
			lblTitleEnchantments = new JLabel("    Enchantments:");
			lblTitleEnchantments.setForeground(Color.WHITE);
			lblTitleEnchantments.setHorizontalAlignment(SwingConstants.LEFT);
			lblTitleEnchantments.setFont(new Font("Tahoma", Font.PLAIN, 15));
		}
		return lblTitleEnchantments;
	}

	private JLabel getLblEnchantments() {
		if (lblEnchantments == null) {
			lblEnchantments = new JLabel();
			lblEnchantments.setForeground(Color.WHITE);
			lblEnchantments.setFont(new Font("Tahoma", Font.PLAIN, 15));
			showEnchantments();
		}
		return lblEnchantments;
	}

	private JPanel getPanel_2() {
		if (panel_2 == null) {
			panel_2 = new JPanel();
			panel_2.setForeground(Color.WHITE);
			panel_2.setBackground(Color.BLACK);
			panel_2.setLayout(new GridLayout(0, 1, 0, 7));

			panel_2.add(getLblName());
			panel_2.add(getLblDescription());
			panel_2.add(getLblCountry());
			panel_2.add(getLblPrice());
			panel_2.add(getLblEnchantments());
		}
		return panel_2;
	}

	private JLabel getLblTitleInformation() {
		if (lblTitleInformation == null) {
			lblTitleInformation = new JLabel("CASTLE'S INFORMATION");
			lblTitleInformation.setForeground(Color.WHITE);
			lblTitleInformation.setHorizontalAlignment(SwingConstants.CENTER);
			lblTitleInformation.setFont(new Font("Tahoma", Font.BOLD, 14));
		}
		return lblTitleInformation;
	}

	private JLabel getLabel_1() {
		if (lblNPpl == null) {
			lblNPpl = new JLabel("    Number of people:");
			lblNPpl.setDisplayedMnemonic('p');
			lblNPpl.setLabelFor(getSpNPpl());
			lblNPpl.setForeground(Color.WHITE);
			lblNPpl.setFont(new Font("Tahoma", Font.PLAIN, 15));
		}
		return lblNPpl;
	}

	private JSpinner getSpNPpl() {
		if (spNPpl == null) {
			spNPpl = new JSpinner();
			spNPpl.setModel(new SpinnerNumberModel(1, 1, null, 1));
			spNPpl.setBackground(Color.WHITE);
			spNPpl.setForeground(Color.BLACK);
			spNPpl.setFont(new Font("Dialog", Font.PLAIN, 15));
		}
		return spNPpl;
	}

	private JLabel getLabel_2() {
		if (lblDate == null) {
			lblDate = new JLabel("    Arrival date:");
			lblDate.setLabelFor(getSpDate());
			lblDate.setDisplayedMnemonic('d');
			lblDate.setForeground(Color.WHITE);
			lblDate.setFont(new Font("Tahoma", Font.PLAIN, 15));
		}
		return lblDate;
	}

	private JLabel getLabel_3() {
		if (lblNights == null) {
			lblNights = new JLabel("    Number of nights:");
			lblNights.setDisplayedMnemonic('g');
			lblNights.setLabelFor(getSpNights());
			lblNights.setForeground(Color.WHITE);
			lblNights.setFont(new Font("Tahoma", Font.PLAIN, 15));
		}
		return lblNights;
	}

	private JSpinner getSpNights() {
		if (spNights == null) {
			spNights = new JSpinner();
			spNights.setModel(new SpinnerNumberModel(1, 1, null, 1));
			spNights.setBackground(Color.WHITE);
			spNights.setForeground(Color.BLACK);
			spNights.setFont(new Font("Dialog", Font.PLAIN, 15));
		}
		return spNights;
	}

	private JLabel getLabel_4() {
		if (lblRooms == null) {
			lblRooms = new JLabel("    Number of rooms");
			lblRooms.setLabelFor(getSpRooms());
			lblRooms.setDisplayedMnemonic('r');
			lblRooms.setForeground(Color.WHITE);
			lblRooms.setFont(new Font("Tahoma", Font.PLAIN, 15));
		}
		return lblRooms;
	}

	private JSpinner getSpRooms() {
		if (spRooms == null) {
			spRooms = new JSpinner();
			spRooms.setModel(new SpinnerNumberModel(1, 1, null, 1));
			spRooms.setBackground(Color.WHITE);
			spRooms.setForeground(Color.BLACK);
			spRooms.setFont(new Font("Dialog", Font.PLAIN, 15));
		}
		return spRooms;
	}

	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setBackground(Color.BLACK);
			panel_1.setLayout(new GridLayout(0, 1, 0, 7));
			panel_1.add(getLblTitleName());
			panel_1.add(getLblTitleDescription());
			panel_1.add(getLblTitleCountry());
			panel_1.add(getLblTitlePrice());
			panel_1.add(getLblTitleEnchantments());
		}
		return panel_1;
	}

	private void adaptImage() {
		ImageIcon tmpImagen = new ImageIcon(PnBookingForm.class.getResource("/img/" + castle.getCode() + ".png"));

		float width = getWidth() / 3;

		float delta = ((width * 100) / tmpImagen.getIconWidth()) / 100f;
		int ancho = (int) (tmpImagen.getIconWidth() * delta);
		int alto = (int) (tmpImagen.getIconHeight() * delta);

		getLblCastleImage()
				.setIcon(new ImageIcon(tmpImagen.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH)));
	}

	private JPanel getPnButtons() {
		if (pnButtons == null) {
			pnButtons = new JPanel();
			pnButtons.setBackground(Color.BLACK);
			FlowLayout fl_pnButtons = (FlowLayout) pnButtons.getLayout();
			fl_pnButtons.setAlignment(FlowLayout.LEADING);
			pnButtons.add(getBtnNext());
			pnButtons.add(getBtnCancel());
		}
		return pnButtons;
	}

	private JPanel getPnGhosts() {
		if (pnGhosts == null) {
			pnGhosts = new JPanel();
			pnGhosts.setBackground(Color.BLACK);
			for (int i = 0; i < 10; i++) {
				pnGhosts.add(getLblGhost());
			}
		}
		return pnGhosts;
	}

	private JSpinner getSpDate() {
		if (spDate == null) {
			spDate = new JSpinner();
			spDate.setModel(new SpinnerDateModel(new Date(), new Date(), null, Calendar.DAY_OF_YEAR));
		}
		return spDate;
	}
}
