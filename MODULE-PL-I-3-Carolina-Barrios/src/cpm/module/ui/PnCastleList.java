package cpm.module.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.List;

import javax.swing.ButtonGroup;
import javax.swing.ButtonModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import cpm.module.fileUtils.DiscountFileUtil;
import cpm.module.model.LoggedUser;
import cpm.module.model.book.Castle;
import cpm.module.model.book.Discount;
import cpm.module.model.book.Enchantment;
import cpm.module.model.book.User;
import cpm.module.service.CastlesService;

public class PnCastleList extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String ENCHANTMENTS_PANEL = "Enchantment";
	private static final String PRICES_PANEL = "Price";

	private MainWindow mainWindow;
	private CastlesService service;
	private JPanel panel;
	private JButton btnNewButton;
	private JScrollPane scrollPane;
	private JPanel pnCastles;
	private JPanel panel_1;
	private JPanel pnFilters;
	private JLabel lblEnchantmentsFilter;
	private JPanel pnFilterEnchantments;
	private List<Castle> castles;
	private ButtonGroup bGEnchantments;
	private ButtonGroup bGPrices;
	private JPanel pnPrices;
	private JPanel panel_2;
	private JLabel lblDiscount;
	private Enchantment selectedEn = null;
	private int selectedPrice = -1;
	private JLabel lblPricesFilter;

	/**
	 * Create the panel.
	 */
	public PnCastleList(MainWindow mainWindow) {
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				Component[] components = getPnCastles().getComponents();
				for (Component pn : components) {
					PnCastle castle = (PnCastle) pn;
					adaptImage(castle, castle.getCastle().getCode());
				}
			}
		});
		setBackground(Color.BLACK);
		this.mainWindow = mainWindow;
		service = new CastlesService();
		bGEnchantments = new MyButtonGroup();
		bGPrices = new MyButtonGroup();
		setLayout(new BorderLayout(0, 0));
		add(getPanel(), BorderLayout.NORTH);
		add(getScrollPane(), BorderLayout.CENTER);

	}

	private class MyButtonGroup extends ButtonGroup {
		private static final long serialVersionUID = 1L;

		@Override
		public void setSelected(ButtonModel m, boolean b) {
			super.setSelected(m, b);
			if (!b)
				clearSelection();
			else
				super.setSelected(m, b);
		}
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBackground(Color.BLACK);
			panel.setLayout(new BorderLayout(0, 0));
			panel.add(getPanel_1(), BorderLayout.EAST);
			panel.add(getPanel_2(), BorderLayout.WEST);
		}
		return panel;
	}

	private JButton getBtnNewButton() {
		if (btnNewButton == null) {
			btnNewButton = new JButton("<-----");
			btnNewButton.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					mainWindow.showNext(MainWindow.START_PANEL);
				}
			});
			btnNewButton.setBackground(Color.WHITE);
			btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		return btnNewButton;
	}

	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
			scrollPane.setViewportView(getPnCastles());
		}
		return scrollPane;
	}

	private JPanel getPnCastles() {
		if (pnCastles == null) {
			pnCastles = new JPanel();
			pnCastles.setBackground(Color.BLACK);
			pnCastles.setLayout(new GridLayout(0, 2, 0, 0));
			noFilterList(false);
		}
		return pnCastles;
	}

	private void adaptImage(PnCastle pn, String imagePath) {
		ImageIcon tmpImagen = new ImageIcon(getClass().getResource(PnCastle.IMAGEPATH + imagePath + ".png"));

		float width = (getWidth() / 2) / 3.5f;

		float delta = ((width * 100) / tmpImagen.getIconWidth()) / 100f;
		int ancho = (int) (tmpImagen.getIconWidth() * delta);
		int alto = (int) (tmpImagen.getIconHeight() * delta);

		pn.getLblImage()
				.setIcon(new ImageIcon(tmpImagen.getImage().getScaledInstance(ancho, alto, Image.SCALE_SMOOTH)));
	}

	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setBackground(Color.BLACK);
			panel_1.setLayout(new GridLayout(0, 1, 0, 0));
			panel_1.add(getLblEnchantmentsFilter());
			panel_1.add(getPnFilterEnchantments());
			panel_1.add(getLblPricesFilter());
			panel_1.add(getPnPrices());
		}
		return panel_1;
	}

	private JLabel getLblEnchantmentsFilter() {
		if (lblEnchantmentsFilter == null) {
			lblEnchantmentsFilter = new JLabel("Filter by enchantments:");
			lblEnchantmentsFilter.setBackground(Color.BLACK);
			lblEnchantmentsFilter.setForeground(Color.WHITE);
			lblEnchantmentsFilter.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		return lblEnchantmentsFilter;
	}

	private JPanel getPnFilterEnchantments() {
		if (pnFilterEnchantments == null) {
			pnFilterEnchantments = new JPanel();
			pnFilterEnchantments.setBackground(Color.BLACK);
			addEnchantmentsOptions();

		}
		return pnFilterEnchantments;
	}

	private void addEnchantmentsOptions() {
		Enchantment[] enchantments = Enchantment.values();
		for (Enchantment en : enchantments) {
			JRadioButton button = new JRadioButton(en.getFullName());
			button.setFont(new Font("Tahoma", Font.PLAIN, 14));
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (button.isSelected()) {
						selectedEn = en;
						filter();
					} else {
						selectedEn = null;
						filter();
					}
				}
			});

			button.setForeground(Color.WHITE);
			bGEnchantments.add(button);
			pnFilterEnchantments.add(button);
		}

	}

	private void addPricesOptions() {
		int maxPrice = 0;
		noFilterList(false);
		for (Castle c : castles) {
			if (maxPrice < c.getPrice())
				maxPrice = c.getPrice();
		}
		for (int i = 50; i < maxPrice + 50; i += 50) {
			JRadioButton button = new JRadioButton("<" + i);
			button.setFont(new Font("Tahoma", Font.PLAIN, 14));
			button.setActionCommand(Integer.toString(i));
			button.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (button.isSelected()) {
						selectedPrice = Integer.parseInt(((JRadioButton) e.getSource()).getActionCommand());
						filter();
					} else {
						selectedPrice = -1;
						filter();
					}
				}
			});

			button.setForeground(Color.WHITE);
			bGPrices.add(button);
			pnPrices.add(button);
		}

	}

	private void noFilterList(boolean adapt) {
		if (castles == null) {
			castles = service.getCastlesList();
		}
		setPnCastlesList(castles, adapt);
	}

	private void filter() {
		List<Castle> enchantmentFilter = filterByEnchantment();
		List<Castle> priceFilter = filterByPrice();
		List<Castle> filtered = enchantmentFilter.stream().filter(c -> priceFilter.contains(c)).toList();
		setPnCastlesList(filtered, true);
	}

	private List<Castle> filterByEnchantment() {
		if (selectedEn != null)
			return castles.stream().filter(c -> c.getEnchantments().contains(selectedEn)).toList();
		return castles;
	}

	private List<Castle> filterByPrice() {
		if (selectedPrice > 0)
			return castles.stream().filter(c -> c.getPrice() < selectedPrice).toList();
		return castles;
	}

	private void setPnCastlesList(List<Castle> castles, boolean adapt) {
		getPnCastles().removeAll();
		for (Castle c : castles) {
			PnCastle newPn = new PnCastle(mainWindow, c);
			getPnCastles().add(newPn);
			if (adapt)
				adaptImage(newPn, c.getCode());
		}
		getPnCastles().repaint();
		validate();
	}

	private JPanel getPnPrices() {
		if (pnPrices == null) {
			pnPrices = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnPrices.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			pnPrices.setBackground(Color.BLACK);
			addPricesOptions();
		}
		return pnPrices;
	}

	private JPanel getPanel_2() {
		if (panel_2 == null) {
			panel_2 = new JPanel();
			panel_2.setBackground(Color.BLACK);
			panel_2.setLayout(new GridLayout(0, 1, 0, 0));
			panel_2.add(getBtnNewButton());
			panel_2.add(getLblDiscount());
		}
		return panel_2;
	}

	@Override
	public void repaint() {
		super.repaint();
		showDiscount();
	}

	private JLabel getLblDiscount() {
		if (lblDiscount == null) {
			lblDiscount = new JLabel("");
			lblDiscount.setFont(new Font("Tahoma", Font.PLAIN, 15));
			lblDiscount.setForeground(Color.WHITE);
		}
		return lblDiscount;
	}

	public void showDiscount() {
		User user = LoggedUser.getInstance();
		if (user != null) {
			Discount discount = DiscountFileUtil.loadDiscountFor(user.getId());
			if (discount != null) {
				getLblDiscount().setText(discount.getDiscount().getPercentage() + " of discount available!");
			}
		}
	}

	private JLabel getLblPricesFilter() {
		if (lblPricesFilter == null) {
			lblPricesFilter = new JLabel("Filter by prices:");
			lblPricesFilter.setForeground(Color.WHITE);
			lblPricesFilter.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		return lblPricesFilter;
	}
}
