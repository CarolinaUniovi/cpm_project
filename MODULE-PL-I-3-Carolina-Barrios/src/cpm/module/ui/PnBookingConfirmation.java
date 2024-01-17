package cpm.module.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import cpm.module.fileUtils.DiscountFileUtil;
import cpm.module.model.LoggedUser;
import cpm.module.model.book.Booking;
import cpm.module.model.book.Discount;
import cpm.module.model.book.User;

public class PnBookingConfirmation extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MainWindow mainWindow;
	private Booking booking;
	private JPanel panel;
	private JPanel pnDiscount;
	private JPanel pnInfo;
	private JPanel pnButtons;
	private JLabel lblNightsTitle;
	private JLabel lblNPplTitle;
	private JLabel lblHotelNameTitle;
	private JLabel lblHotelName;
	private JLabel lblArrivalDateTitle;
	private JLabel lblPriceTitle;
	private JLabel lblPeople;
	private JLabel lblDate;
	private JLabel lblNights;
	private JButton btnNewButton;
	private JButton btnCancel;
	private JLabel lblFinalPrice;
	private JLabel fillingLbl;
	private JButton btnApplyDiscount;
	private JLabel fillingLbl2;
	private JLabel fillingLbl3;
	private JLabel fillingLbl4;
	private JLabel fillingLbl5;
	private JLabel fillingLbl6;
	private JLabel lblNewLabel;

	/**
	 * Create the panel.
	 */
	public PnBookingConfirmation(MainWindow mainWindow, Booking booking) {
		this.mainWindow = mainWindow;
		this.booking = booking;
		setBackground(Color.BLACK);
		setLayout(new BorderLayout(0, 20));
		add(getPanel(), BorderLayout.WEST);
		add(getPnDiscount(), BorderLayout.EAST);
		add(getPnInfo(), BorderLayout.CENTER);
		add(getPnButtons(), BorderLayout.SOUTH);

	}

	public void update(Booking booking) {
		this.booking = booking;
		getLblDate().setText("  " + booking.getDate());
		getLblFinalPrice().setText("  " + booking.getFinalPrice());
		getLblHotelName().setText("  " + booking.getCastleName());
		getLblNights().setText("  " + booking.getDays());
		getLblPeople().setText("  " + booking.getPeople());

	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBackground(Color.BLACK);
			panel.setLayout(new GridLayout(1, 0, 0, 0));
			panel.add(getLblNewLabel());
		}
		return panel;
	}

	private JPanel getPnDiscount() {
		if (pnDiscount == null) {
			pnDiscount = new JPanel();
			pnDiscount.setBackground(Color.BLACK);
			pnDiscount.setLayout(new GridLayout(0, 1, 0, 0));
			pnDiscount.add(getFillingLbl());
			pnDiscount.add(getFillingLbl2());
			pnDiscount.add(getFillingLbl3());
			pnDiscount.add(getFillingLbl4());
			pnDiscount.add(getFillingLbl5());
			pnDiscount.add(getFillingLbl6());
			pnDiscount.add(getBtnApplyDiscount());
		}
		return pnDiscount;
	}

	private JPanel getPnInfo() {
		if (pnInfo == null) {
			pnInfo = new JPanel();
			pnInfo.setBackground(Color.BLACK);
			pnInfo.setLayout(new GridLayout(0, 2, 0, 0));
			pnInfo.add(getLblHotelNameTitle());
			pnInfo.add(getLblHotelName());
			pnInfo.add(getLblNPplTitle());
			pnInfo.add(getLblPeople());
			pnInfo.add(getLblArrivalDateTitle());
			pnInfo.add(getLblDate());
			pnInfo.add(getLblNightsTitle());
			pnInfo.add(getLblNights());
			pnInfo.add(getLblPriceTitle());
			pnInfo.add(getLblFinalPrice());
		}
		return pnInfo;
	}

	private JPanel getPnButtons() {
		if (pnButtons == null) {
			pnButtons = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnButtons.getLayout();
			flowLayout.setAlignment(FlowLayout.RIGHT);
			pnButtons.setBackground(Color.BLACK);
			pnButtons.add(getBtnNewButton());
			pnButtons.add(getBtnCancel());
		}
		return pnButtons;
	}

	private JLabel getLblNightsTitle() {
		if (lblNightsTitle == null) {
			lblNightsTitle = new JLabel("Number of nights:          ");
			lblNightsTitle.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNightsTitle.setBackground(Color.BLACK);
			lblNightsTitle.setForeground(Color.WHITE);
			lblNightsTitle.setFont(new Font("Tahoma", Font.PLAIN, 17));
		}
		return lblNightsTitle;
	}

	private JLabel getLblNPplTitle() {
		if (lblNPplTitle == null) {
			lblNPplTitle = new JLabel("Number of people:          ");
			lblNPplTitle.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNPplTitle.setBackground(Color.BLACK);
			lblNPplTitle.setForeground(Color.WHITE);
			lblNPplTitle.setFont(new Font("Tahoma", Font.PLAIN, 17));
		}
		return lblNPplTitle;
	}

	private JLabel getLblHotelNameTitle() {
		if (lblHotelNameTitle == null) {
			lblHotelNameTitle = new JLabel("Hotel name:          ");
			lblHotelNameTitle.setHorizontalAlignment(SwingConstants.RIGHT);
			lblHotelNameTitle.setBackground(Color.BLACK);
			lblHotelNameTitle.setForeground(Color.WHITE);
			lblHotelNameTitle.setFont(new Font("Tahoma", Font.PLAIN, 17));
		}
		return lblHotelNameTitle;
	}

	private JLabel getLblHotelName() {
		if (lblHotelName == null) {
			lblHotelName = new JLabel("  " + booking.getCastleName());
			lblHotelName.setBackground(Color.BLACK);
			lblHotelName.setForeground(Color.WHITE);
			lblHotelName.setFont(new Font("Tahoma", Font.PLAIN, 17));
		}
		return lblHotelName;
	}

	private JLabel getLblArrivalDateTitle() {
		if (lblArrivalDateTitle == null) {
			lblArrivalDateTitle = new JLabel("Arrival date:          ");
			lblArrivalDateTitle.setHorizontalAlignment(SwingConstants.RIGHT);
			lblArrivalDateTitle.setBackground(Color.BLACK);
			lblArrivalDateTitle.setForeground(Color.WHITE);
			lblArrivalDateTitle.setFont(new Font("Tahoma", Font.PLAIN, 17));
		}
		return lblArrivalDateTitle;
	}

	private JLabel getLblPriceTitle() {
		if (lblPriceTitle == null) {
			lblPriceTitle = new JLabel("Final price:          ");
			lblPriceTitle.setHorizontalAlignment(SwingConstants.RIGHT);
			lblPriceTitle.setBackground(Color.BLACK);
			lblPriceTitle.setForeground(Color.WHITE);
			lblPriceTitle.setFont(new Font("Tahoma", Font.PLAIN, 17));
		}
		return lblPriceTitle;
	}

	private JLabel getLblPeople() {
		if (lblPeople == null) {
			lblPeople = new JLabel("  " + booking.getPeople());
			lblPeople.setBackground(Color.BLACK);
			lblPeople.setForeground(Color.WHITE);
			lblPeople.setFont(new Font("Tahoma", Font.PLAIN, 17));
		}
		return lblPeople;
	}

	private JLabel getLblDate() {
		if (lblDate == null) {
			lblDate = new JLabel("  " + booking.getDate());
			lblDate.setBackground(Color.BLACK);
			lblDate.setForeground(Color.WHITE);
			lblDate.setFont(new Font("Tahoma", Font.PLAIN, 17));
		}
		return lblDate;
	}

	private JLabel getLblNights() {
		if (lblNights == null) {
			lblNights = new JLabel("  " + booking.getDays());
			lblNights.setBackground(Color.BLACK);
			lblNights.setForeground(Color.WHITE);
			lblNights.setFont(new Font("Tahoma", Font.PLAIN, 17));
		}
		return lblNights;
	}

	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("Confirm");
			btnNewButton.setMnemonic('f');
			btnNewButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					mainWindow.showClientData(booking);
				}
			});
			btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 15));
		}
		return btnNewButton;
	}

	private JButton getBtnCancel() {
		if (btnCancel == null) {
			btnCancel = new JButton("Cancel");
			btnCancel.setMnemonic('n');
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

	private JLabel getLblFinalPrice() {
		if (lblFinalPrice == null) {
			lblFinalPrice = new JLabel("  " + booking.getFinalPrice());
			lblFinalPrice.setBackground(Color.BLACK);
			lblFinalPrice.setForeground(Color.WHITE);
			lblFinalPrice.setFont(new Font("Tahoma", Font.PLAIN, 17));
		}
		return lblFinalPrice;
	}

	private JLabel getFillingLbl() {
		if (fillingLbl == null) {
			fillingLbl = new JLabel("");
			fillingLbl.setBackground(Color.BLACK);
		}
		return fillingLbl;
	}

	private JButton getBtnApplyDiscount() {
		if (btnApplyDiscount == null) {
			btnApplyDiscount = new JButton("Apply discount");
			btnApplyDiscount.setMnemonic('p');
			btnApplyDiscount.setBackground(Color.WHITE);
			btnApplyDiscount.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					User user = LoggedUser.getInstance();
					if (user == null) {
						showIdentificationDialog();
					} else {
						applyDiscount();
					}
				}
			});
			btnApplyDiscount.setFont(new Font("Tahoma", Font.PLAIN, 15));
		}
		return btnApplyDiscount;
	}

	private void applyDiscount() {
		User user = LoggedUser.getInstance();
		if (user != null) {
			booking.setUser(user);
			List<Discount> discounts = DiscountFileUtil.loadFile();
			discounts.removeIf(d -> !d.getId().equals(user.getId()));
			if (discounts.size() > 0) {
				booking.applyDiscount(discounts.get(0));
				getLblFinalPrice().setText("  " + booking.getFinalPrice());
				showDiscountAppliedDialog("Discount successfully applied", "Success");
			} else {
				showDiscountAppliedDialog("No discount found", "Error");
			}
		}
		validate();

	}

	private void showIdentificationDialog() {
		try {
			IdentificationDialog dialog = new IdentificationDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
			dialog.addWindowListener(new WindowAdapter() {
				@Override
				public void windowDeactivated(WindowEvent e) {
					String id = dialog.getTxtId().getText();
					if (id != null && !id.isEmpty()) {
						LoggedUser.getInstance(id);
					}
					applyDiscount();
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void showDiscountAppliedDialog(String message, String title) {
		JOptionPane.showMessageDialog(this, message, title, JOptionPane.INFORMATION_MESSAGE);

	}

	private JLabel getFillingLbl2() {
		if (fillingLbl2 == null) {
			fillingLbl2 = new JLabel("");
			fillingLbl2.setBackground(Color.BLACK);
		}
		return fillingLbl2;
	}

	private JLabel getFillingLbl3() {
		if (fillingLbl3 == null) {
			fillingLbl3 = new JLabel("");
			fillingLbl3.setBackground(Color.BLACK);
		}
		return fillingLbl3;
	}

	private JLabel getFillingLbl4() {
		if (fillingLbl4 == null) {
			fillingLbl4 = new JLabel("");
			fillingLbl4.setBackground(Color.BLACK);
		}
		return fillingLbl4;
	}

	private JLabel getFillingLbl5() {
		if (fillingLbl5 == null) {
			fillingLbl5 = new JLabel("");
			fillingLbl5.setBackground(Color.BLACK);
		}
		return fillingLbl5;
	}

	private JLabel getFillingLbl6() {
		if (fillingLbl6 == null) {
			fillingLbl6 = new JLabel("");
			fillingLbl6.setBackground(Color.BLACK);
		}
		return fillingLbl6;
	}

	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("");
			lblNewLabel.setIcon(
					new ImageIcon(PnBookingConfirmation.class.getResource("/img/bookingConfirmationGhost.png")));
		}
		return lblNewLabel;
	}
}
