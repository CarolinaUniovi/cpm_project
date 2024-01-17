package cpm.module.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import cpm.module.fileUtils.DiscountFileUtil;
import cpm.module.model.LoggedUser;
import cpm.module.model.book.Discount;
import cpm.module.model.book.DiscountType;
import cpm.module.model.book.User;
import cpm.module.model.game.Cell;
import cpm.module.model.game.GhostBusterCell;
import cpm.module.service.GhostBusters;

public class PnGame extends JPanel {

	private static final long serialVersionUID = 1L;
	private boolean playOnly = false;
	private static final String IMAGEPATH = "/img/";
	private GhostBusters ghostBusters;
	private MainWindow mainWindow;
	private List<List<JComponent>> buttonsList;
	private JPanel panel;
	private JPanel pnBoard;
	private JPanel panel_2;
	private JButton btnRoll;
	private JPanel panel_3;
	private JLabel lblRoundsLeft;
	private JLabel lblDice;
	private JPanel panel_4;
	private JButton btnContinue;
	private JButton btnRetry;

	/**
	 * Create the panel.
	 */
	public PnGame(MainWindow mainWindow, boolean playOnly) {
		setBackground(Color.BLACK);
		this.playOnly = playOnly;
		this.ghostBusters = new GhostBusters();
		this.mainWindow = mainWindow;
		setLayout(new BorderLayout(10, 10));
		add(getPanel(), BorderLayout.NORTH);
		add(getPnBoard(), BorderLayout.CENTER);
		add(getPanel_2(), BorderLayout.WEST);
	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBackground(Color.BLACK);
			panel.setLayout(new BorderLayout(0, 0));
			panel.add(getLblRoundsLeft(), BorderLayout.WEST);
			panel.add(getPanel_4(), BorderLayout.EAST);
		}
		return panel;
	}

	private JPanel getPnBoard() {
		if (pnBoard == null) {
			pnBoard = new JPanel();
			pnBoard.setBackground(Color.BLACK);
			pnBoard.setLayout(new GridLayout(5, ghostBusters.getBoard().get(0).size(), 0, 0));
			addButtonsToBoard();
		}
		return pnBoard;
	}

	private void addButtonsToBoard() {
		buttonsList = new ArrayList<>();
		getPnBoard().removeAll();
		List<List<Cell>> board = ghostBusters.getBoard();
		for (int i = 0; i < board.size(); i++) {
			buttonsList.add(new ArrayList<>());
			for (int j = 0; j < board.get(i).size(); j++) {
				JComponent gbCell;
				Cell cell = board.get(i).get(j);
				if (cell.getCode() == Cell.GHOST_BUSTER_CELL) {
					gbCell = new GhostBusterButton(cell.getName(), i, j);
				} else if (cell.getCode() == Cell.EMPTY_CELL) {
					gbCell = new JLabel();
				} else {
					gbCell = new GhostButton(cell.getName(), i, j);
				}
				buttonsList.get(i).add(gbCell);
				pnBoard.add(gbCell);
			}
		}
		getLblRoundsLeft().setText("  " + ghostBusters.getRoundsLeft() + " rounds left!");
		validate();
	}

	private class GhostButton extends JButton {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public GhostButton(String imageName, int row, int column) {
			this.setBackground(Color.BLACK);
			this.setEnabled(false);
			setIcon(new ImageIcon(PnGame.class.getResource(IMAGEPATH + imageName + ".png")));
			addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					ghostBusters.moveTo(row, column);
					addButtonsToBoard();
					if (ghostBusters.getRoundsLeft() > 0) {
						getBtnRoll().setEnabled(true);
					} else {
						finish();
					}
				}

			});
		}
	}

	private void finish() {
		showDiscountMessage();
		getBtnContinue().setEnabled(true);
		getBtnRetry().setEnabled(true);
	}

	private void showDiscountMessage() {
		DiscountType discount = ghostBusters.getDiscount();
		if (!discount.getCode().equals(DiscountType.NONE.getCode())) {
			String discountLeft = "\n";
			if (discount.getCode().equals(DiscountType.EXTRA25.getCode())) {
				discountLeft += "You won the largest discount!";
			} else {
				discountLeft += "You can still win a larger discount!";
			}
			JOptionPane.showMessageDialog(this, discount.getPercentage() + " discount obtained!" + discountLeft,
					"Congrats!", JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(this, "No discount obtained!", "Oh, no...", JOptionPane.INFORMATION_MESSAGE);
		}

	}

	private class GhostBusterButton extends JButton {
		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;

		public GhostBusterButton(String imageName, int row, int column) {
			this.setBackground(Color.WHITE);
			this.setEnabled(false);
			setIcon(new ImageIcon(PnGame.class.getResource(IMAGEPATH + imageName + ".png")));
			addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					deselect();
					setSelected(true);
					enableGhost(row, column);
				}
			});
		}

	}

	private void enableGhost(int row, int column) {
		buttonsList.get(row - ghostBusters.getLastToss()).get(column).setEnabled(true);
	}

	private void deselect() {
		JButton button;
		Cell cell;
		List<List<Cell>> board = ghostBusters.getBoard();
		for (int i = 0; i < buttonsList.size(); i++) {
			for (int j = 0; j < buttonsList.get(i).size(); j++) {
				if (buttonsList.get(i).get(j) instanceof JButton) {
					button = (JButton) buttonsList.get(i).get(j);
					if (button.isSelected()) {
						button.setSelected(false);
					}
					if (button.isEnabled()) {
						cell = board.get(i).get(j);
						if (cell.getCode() != Cell.GHOST_BUSTER_CELL) {
							button.setEnabled(false);
						}
					}
				}
			}
		}
	}

	private JPanel getPanel_2() {
		if (panel_2 == null) {
			panel_2 = new JPanel();
			panel_2.setBackground(Color.BLACK);
			panel_2.setLayout(new BorderLayout(10, 10));
			panel_2.add(getPanel_3(), BorderLayout.CENTER);
			panel_2.add(getLblDice(), BorderLayout.NORTH);
		}
		return panel_2;
	}

	private JButton getBtnRoll() {
		if (btnRoll == null) {
			btnRoll = new JButton("Roll");
			btnRoll.setMnemonic('r');
			btnRoll.setBackground(Color.WHITE);
			btnRoll.setFont(new Font("Tahoma", Font.PLAIN, 14));
			btnRoll.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					int rolled = ghostBusters.rollDice();
					enableGhostBusters(rolled);
					setDiceIcon(Integer.toString(rolled));
					btnRoll.setEnabled(false);
				}
			});
			btnRoll.setHorizontalAlignment(SwingConstants.LEADING);
		}
		return btnRoll;
	}

	/**
	 * Checks what cells of the board are ghost busters and if they have enough
	 * moves left, the corresponding buttons are enabled.
	 * 
	 * @param rolled
	 */
	private void enableGhostBusters(int rolled) {
		List<List<Cell>> board = ghostBusters.getBoard();
		for (int i = 0; i < board.size(); i++) {
			for (int j = 0; j < board.get(i).size(); j++) {
				if (board.get(i).get(j).getCode() == Cell.GHOST_BUSTER_CELL) {
					GhostBusterCell ghostBuster = (GhostBusterCell) board.get(i).get(j);
					if (ghostBuster.getGhostLeftInRow() >= rolled) {
						buttonsList.get(i).get(j).setEnabled(true);
					}
				}
			}
		}

	}

	private void setDiceIcon(String string) {
		getLblDice().setIcon(new ImageIcon(PnGame.class.getResource(IMAGEPATH + "dice" + string + ".png")));
	}

	private JPanel getPanel_3() {
		if (panel_3 == null) {
			panel_3 = new JPanel();
			panel_3.setBackground(Color.BLACK);
			panel_3.add(getBtnRoll());
		}
		return panel_3;
	}

	private JLabel getLblRoundsLeft() {
		if (lblRoundsLeft == null) {
			lblRoundsLeft = new JLabel("X rounds left!");
			lblRoundsLeft.setHorizontalAlignment(SwingConstants.CENTER);
			lblRoundsLeft.setForeground(Color.WHITE);
			lblRoundsLeft.setFont(new Font("Tahoma", Font.PLAIN, 14));
			lblRoundsLeft.setBackground(Color.BLACK);
		}
		return lblRoundsLeft;
	}

	private JLabel getLblDice() {
		if (lblDice == null) {
			lblDice = new JLabel("");
			lblDice.setIcon(new ImageIcon(PnGame.class.getResource(IMAGEPATH + "dice0.png")));
		}
		return lblDice;
	}

	private JPanel getPanel_4() {
		if (panel_4 == null) {
			panel_4 = new JPanel();
			panel_4.setBackground(Color.BLACK);
			panel_4.setLayout(new GridLayout(1, 0, 0, 0));
			panel_4.add(getBtnRetry());
			panel_4.add(getBtnContinue());
		}
		return panel_4;
	}

	private JButton getBtnContinue() {
		if (btnContinue == null) {
			btnContinue = new JButton("Continue");
			btnContinue.setMnemonic('t');
			btnContinue.setEnabled(false);
			btnContinue.setBackground(new Color(100, 149, 237));
			btnContinue.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					if (!ghostBusters.getDiscount().getCode().equals(DiscountType.NONE.getCode())) {
						showIdentificationDialog();
					} else {
						showNextPanel();
					}
				}
			});
			btnContinue.setFont(new Font("Tahoma", Font.PLAIN, 14));
		}
		return btnContinue;
	}

	private void showNextPanel() {
		if (playOnly) {
			mainWindow.showNext(MainWindow.START_PANEL);
		} else {
			mainWindow.showNext(MainWindow.CASTLE_LIST_PANEL);
		}
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
						User user;
						if (playOnly) {
							user = LoggedUser.getInstance(id);
						} else {
							user = new User(id);
						}
						Discount discount = new Discount(ghostBusters.getDiscount(), user.getId());
						List<Discount> discounts = DiscountFileUtil.loadFile();
						if (!discounts.stream().anyMatch(d -> d.getId().equals(user.getId()))) {
							discounts.add(discount);
							DiscountFileUtil.saveToFile(discounts);
							showSavedDiscountDialog();

						} else {
							showAlreadyHasDiscount();
						}
						showNextPanel();
					}
				}
			});

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void showAlreadyHasDiscount() {
		JOptionPane.showMessageDialog(this,
				"You already have a discount saved. Until it's used up, no other discount would be saved",
				"Discount not saved", JOptionPane.INFORMATION_MESSAGE);
	}

	private void showSavedDiscountDialog() {
		JOptionPane.showMessageDialog(this, "Discount has been saved succesfully", "Discount saved",
				JOptionPane.INFORMATION_MESSAGE);

	}

	private JButton getBtnRetry() {
		if (btnRetry == null) {
			btnRetry = new JButton("Retry");
			btnRetry.setBackground(new Color(233, 150, 122));
			btnRetry.setMnemonic('y');
			btnRetry.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					ghostBusters.init();
					addButtonsToBoard();
					getBtnRoll().setEnabled(true);
					btnRetry.setEnabled(false);
					getBtnContinue().setEnabled(false);
				}
			});
			btnRetry.setFont(new Font("Tahoma", Font.PLAIN, 14));
			btnRetry.setEnabled(false);
		}
		return btnRetry;
	}
}
