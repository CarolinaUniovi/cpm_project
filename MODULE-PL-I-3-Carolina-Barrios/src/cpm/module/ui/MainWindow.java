package cpm.module.ui;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;

import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cpm.module.model.LoggedUser;
import cpm.module.model.book.Booking;
import cpm.module.model.book.Castle;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public final static String APPLICATION_NAME = "Let’s go on… Holidays";
	public final static String START_PANEL = "Start";
	public final static String GAME_PANEL = "Game";
	public final static String CASTLE_LIST_PANEL = "Castle list";
	public final static String CASTLE_FORM_PANEL = "Booking form";
	public final static String BOOKING_CONFIRMATION_PANEL = "Booking confirmation";
	public final static String CLIENT_DATA_PANEL = "Client's data";

	private PnBookingForm form = null;
	private PnBookingConfirmation confirmation = null;
	private PnClientData clientData = null;
	private JMenuBar menuBar;
	private JMenu mnHome;
	private JMenu mnHelp;
	private JMenuItem mntmHome;
	private JMenuItem mntnExit;
	private JMenuItem mntmAbout;
	private JMenuItem mntmContents;
	private JMenu mnGame;
	private JMenuItem mntmGameInstructions;

	/**
	 * Create the frame.
	 */
	public MainWindow() {
		setBackground(Color.BLACK);
		setMinimumSize(new Dimension(1400, 800));
		setTitle(APPLICATION_NAME + ": Start");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setJMenuBar(getMenuBar_1());
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new CardLayout(0, 0));
		contentPane.add(new PnStart(this), START_PANEL);
		contentPane.add(new PnCastleList(this), CASTLE_LIST_PANEL);
		loadHelp();
	}

	// Add this method to the main window and call it from the constructor
	private void loadHelp() {
		URL hsURL;
		HelpSet hs;

		try {
			File fichero = new File("help/Help.hs");
			hsURL = fichero.toURI().toURL();
			hs = new HelpSet(null, hsURL);
		}

		catch (Exception e) {
			System.out.println("Help not found!");
			return;
		}

		HelpBroker hb = hs.createHelpBroker();
		hb.initPresentation(); // Preloads the help support
		hb.enableHelpKey(getRootPane(), "intro", hs);
		hb.enableHelpOnButton(getMntmContents(), "intro", hs);
		// hb.enableHelp(getListLibrary(), "add", hs);
	}

	public void showNext(String layoutName) {
		if (layoutName.equals(START_PANEL)) {
			contentPane.removeAll();
			contentPane.add(new PnStart(this), START_PANEL);
			contentPane.add(new PnCastleList(this), CASTLE_LIST_PANEL);
			form = null;
			confirmation = null;
			LoggedUser.restartInstance();
			validate();
		}
		CardLayout layout = (CardLayout) contentPane.getLayout();
		layout.show(contentPane, layoutName);
		setTitle(APPLICATION_NAME + ": " + layoutName);
	}

	public void showBookingForm(Castle castle) {
		if (form == null) {
			form = new PnBookingForm(this, castle);
			contentPane.add(form, CASTLE_FORM_PANEL);
		} else {
			form.update(castle);
		}
		showNext(CASTLE_FORM_PANEL);
	}

	public void showPlayPanel(boolean playOnly) {
		contentPane.add(new PnGame(this, playOnly), GAME_PANEL);
		showNext(GAME_PANEL);
		showInstructionsDialog();
	}

	private void showInstructionsDialog() {
		try {
			InstructionsDialog dialog = new InstructionsDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void showBookingConfirmation(Booking booking) {
		if (confirmation == null) {
			confirmation = new PnBookingConfirmation(this, booking);
			contentPane.add(confirmation, BOOKING_CONFIRMATION_PANEL);
		} else {
			confirmation.update(booking);
		}
		showNext(BOOKING_CONFIRMATION_PANEL);
	}

	public void showClientData(Booking booking) {
		if (clientData == null) {
			clientData = new PnClientData(this, booking);
			contentPane.add(clientData, CLIENT_DATA_PANEL);
		} else {
			clientData.update(booking);
		}
		showNext(CLIENT_DATA_PANEL);

	}

	private JMenuBar getMenuBar_1() {
		if (menuBar == null) {
			menuBar = new JMenuBar();
			menuBar.setForeground(Color.WHITE);
			menuBar.setBackground(Color.BLACK);
			menuBar.add(getMnHome());
			menuBar.add(getMnGame());
			menuBar.add(getMnHelp());
		}
		return menuBar;
	}

	private JMenu getMnHome() {
		if (mnHome == null) {
			mnHome = new JMenu("Home");
			mnHome.setFont(new Font("Segoe UI", Font.PLAIN, 13));
			mnHome.add(getMntmHome());
			mnHome.add(getMntnExit());
		}
		return mnHome;
	}

	private JMenu getMnHelp() {
		if (mnHelp == null) {
			mnHelp = new JMenu("Help");
			mnHelp.setMnemonic('c');
			mnHelp.setFont(new Font("Segoe UI", Font.PLAIN, 13));
			mnHelp.add(getMntmContents());
			mnHelp.add(getMntmAbout());
		}
		return mnHelp;
	}

	private JMenuItem getMntmHome() {
		if (mntmHome == null) {
			mntmHome = new JMenuItem("Home");
			mntmHome.setMnemonic('h');
			mntmHome.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					showNext(START_PANEL);
				}
			});
			mntmHome.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		}
		return mntmHome;
	}

	private JMenuItem getMntnExit() {
		if (mntnExit == null) {
			mntnExit = new JMenuItem("Exit");
			mntnExit.setMnemonic('e');
			mntnExit.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					showExit();

				}
			});
			mntnExit.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		}
		return mntnExit;
	}

	private void showExit() {
		if (JOptionPane.showConfirmDialog(this, "Are you sure you want to exit?") == JOptionPane.YES_OPTION)
			dispose();

	}

	private JMenuItem getMntmAbout() {
		if (mntmAbout == null) {
			mntmAbout = new JMenuItem("About");
			mntmAbout.setMnemonic('a');
			mntmAbout.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					showAboutDialog();
				}
			});
			mntmAbout.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		}
		return mntmAbout;
	}

	private void showAboutDialog() {
		JOptionPane.showMessageDialog(this, "Carolina Barrios González\nUO275673\n71044875Z", "About",
				JOptionPane.INFORMATION_MESSAGE);

	}

	private JMenuItem getMntmContents() {
		if (mntmContents == null) {
			mntmContents = new JMenuItem("Contents");
			mntmContents.setFont(new Font("Segoe UI", Font.PLAIN, 13));
		}
		return mntmContents;
	}

	private JMenu getMnGame() {
		if (mnGame == null) {
			mnGame = new JMenu("Game");
			mnGame.add(getMntmGameInstructions());
		}
		return mnGame;
	}

	private JMenuItem getMntmGameInstructions() {
		if (mntmGameInstructions == null) {
			mntmGameInstructions = new JMenuItem("Instructions");
			mntmGameInstructions.setMnemonic('i');
			mntmGameInstructions.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					showInstructionsDialog();
				}
			});
		}
		return mntmGameInstructions;
	}
}
