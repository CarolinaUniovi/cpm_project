package cpm.module.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import cpm.module.model.book.Castle;
import cpm.module.model.book.Enchantment;

public class PnCastle extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String IMAGEPATH = "/img/";
	private Castle castle;
	private JPanel pnInfo;
	private JLabel lblImage;
	private JLabel lblName;
	private JLabel lblDescripcion;
	private JLabel lblCountry;
	private JLabel lblRoomPrice;

	/**
	 * Create the panel.
	 */
	public PnCastle(MainWindow mainWindow, Castle castle) {
		setBorder(new LineBorder(new Color(105, 105, 105), 2));
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				mainWindow.showBookingForm(castle);
			}
		});

		this.castle = castle;
		setBackground(Color.BLACK);
		setLayout(new BorderLayout(0, 0));
		add(getPnInfo(), BorderLayout.CENTER);
		add(getLblImage(), BorderLayout.WEST);
	}

	public Castle getCastle() {
		return castle;
	}

	private JPanel getPnInfo() {
		if (pnInfo == null) {
			pnInfo = new JPanel();
			pnInfo.setBackground(Color.BLACK);
			pnInfo.setLayout(new GridLayout(0, 1, 0, 0));
			pnInfo.add(getLblName());
			pnInfo.add(getLblDescripcion());
			pnInfo.add(getLblCountry());
			pnInfo.add(getLblRoomPrice());
			addEnchantments();
		}
		return pnInfo;
	}

	public JLabel getLblImage() {
		if (lblImage == null) {
			lblImage = new JLabel("");
			lblImage.setBackground(Color.BLACK);
		}
		return lblImage;
	}

	private JLabel getLblName() {
		if (lblName == null) {
			lblName = new JLabel(castle.getName());
			lblName.setHorizontalAlignment(SwingConstants.LEFT);
			lblName.setForeground(Color.WHITE);
			lblName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		return lblName;
	}

	private JLabel getLblDescripcion() {
		if (lblDescripcion == null) {
			lblDescripcion = new JLabel(castle.getDescription().subSequence(0, 50) + "...");
			lblDescripcion.setForeground(Color.WHITE);
			lblDescripcion.setHorizontalAlignment(SwingConstants.LEFT);
			lblDescripcion.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		return lblDescripcion;
	}

	private JLabel getLblCountry() {
		if (lblCountry == null) {
			lblCountry = new JLabel(castle.getCountry());
			lblCountry.setForeground(Color.WHITE);
			lblCountry.setHorizontalAlignment(SwingConstants.LEFT);
			lblCountry.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		return lblCountry;
	}

	private JLabel getLblRoomPrice() {
		if (lblRoomPrice == null) {
			lblRoomPrice = new JLabel(castle.getPrice() + "€/night/room");
			lblRoomPrice.setForeground(Color.WHITE);
			lblRoomPrice.setHorizontalAlignment(SwingConstants.LEFT);
			lblRoomPrice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		return lblRoomPrice;
	}

	private void addEnchantments() {
		List<Enchantment> list = castle.getEnchantments();
		for (int i = 0; i <= (list.size() / 2); i++) {
			String enchantments = "";
			JLabel lblEnchantments = new JLabel();
			lblEnchantments.setForeground(Color.WHITE);
			lblEnchantments.setHorizontalAlignment(SwingConstants.LEFT);
			lblEnchantments.setFont(new Font("Tahoma", Font.PLAIN, 14));
			for (int j = i * 2; j < (i + 1) * 2 && j < list.size(); j++) {
				if (j == list.size() - 1) {
					enchantments += list.get(j).toString();
				} else
					enchantments += list.get(j).toString() + ", ";
			}
			lblEnchantments.setText(enchantments);
			getPnInfo().add(lblEnchantments);
		}
	}

}
