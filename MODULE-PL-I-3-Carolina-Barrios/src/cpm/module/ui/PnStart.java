package cpm.module.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class PnStart extends JPanel {

	private static final long serialVersionUID = 1L;
	private MainWindow mainWindow;
	private JButton btnPlayNBook;
	private JButton btnBook;
	private JButton btnPlay;
	private JPanel panel_2;
	private JLabel lblNewLabel_1;
	private JPanel panel_1;
	private JLabel lblNewLabel;
	private JPanel panel;

	/**
	 * Create the panel.
	 */
	public PnStart(MainWindow mainWindow) {
		this.mainWindow = mainWindow;
		setBackground(Color.BLACK);
		setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		setLayout(new BorderLayout(70, 0));

		add(getPanel(), BorderLayout.WEST);
		add(getPanel_1(), BorderLayout.EAST);
		add(getPanel_2(), BorderLayout.CENTER);

	}

	private JPanel getPanel() {
		if (panel == null) {
			panel = new JPanel();
			panel.setBackground(Color.BLACK);
			panel.setLayout(new GridLayout(0, 1, 0, 0));
			panel.add(getLblNewLabel());
		}
		return panel;
	}

	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("");
			lblNewLabel.setHorizontalTextPosition(SwingConstants.LEFT);
			lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
			lblNewLabel.setVerticalAlignment(SwingConstants.BOTTOM);
			lblNewLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
			lblNewLabel.setIcon(new ImageIcon(PnStart.class.getResource("/img/welcomeGhost.png")));

		}
		return lblNewLabel;
	}

	private JPanel getPanel_1() {
		if (panel_1 == null) {
			panel_1 = new JPanel();
			panel_1.setBackground(Color.BLACK);
			panel_1.setLayout(new GridLayout(0, 1, 0, 0));
			panel_1.add(getLblNewLabel_1());
		}
		return panel_1;
	}

	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("");
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
			lblNewLabel_1.setHorizontalTextPosition(SwingConstants.RIGHT);
			lblNewLabel_1.setVerticalTextPosition(SwingConstants.TOP);
			lblNewLabel_1.setVerticalAlignment(SwingConstants.TOP);
			lblNewLabel_1.setIcon(new ImageIcon(PnStart.class.getResource("/img/welcomeGhost2.png")));

		}
		return lblNewLabel_1;
	}

	private JPanel getPanel_2() {
		if (panel_2 == null) {
			panel_2 = new JPanel();
			panel_2.setBackground(Color.BLACK);
			panel_2.setLayout(new GridLayout(0, 1, 0, 50));
			panel_2.add(getBtnPlay());
			panel_2.add(getBtnBook());
			panel_2.add(getBtnPlayNBook());
		}
		return panel_2;
	}

	private JButton getBtnBook() {
		if (btnBook == null) {
			btnBook = new JButton("");
			btnBook.setIcon(new ImageIcon(PnStart.class.getResource("/img/bookButton.png")));
			btnBook.setMnemonic('b');
			btnBook.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					mainWindow.showNext(MainWindow.CASTLE_LIST_PANEL);
				}
			});
			btnBook.setBackground(Color.WHITE);
			btnBook.setFont(new Font("Tahoma", Font.PLAIN, 22));
		}
		return btnBook;
	}

	private JButton getBtnPlay() {
		if (btnPlay == null) {
			btnPlay = new JButton("");
			btnPlay.setIcon(new ImageIcon(PnStart.class.getResource("/img/playButton.png")));
			btnPlay.setMnemonic('p');
			btnPlay.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					mainWindow.showPlayPanel(true);
				}
			});
			btnPlay.setBackground(Color.WHITE);
			btnPlay.setFont(new Font("Tahoma", Font.PLAIN, 22));
		}
		return btnPlay;
	}

	private JButton getBtnPlayNBook() {
		if (btnPlayNBook == null) {
			btnPlayNBook = new JButton("");
			btnPlayNBook.setIcon(new ImageIcon(PnStart.class.getResource("/img/playNBookButton.png")));
			btnPlayNBook.setMnemonic('n');
			btnPlayNBook.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					mainWindow.showPlayPanel(false);
				}
			});
			btnPlayNBook.setBackground(Color.WHITE);
			btnPlayNBook.setFont(new Font("Tahoma", Font.PLAIN, 22));
			panel_2.add(btnPlayNBook);
		}
		return btnPlayNBook;
	}

}
