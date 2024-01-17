package cpm.module.ui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class InstructionsDialog extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final String imagePath = "/img/";
	private JPanel pnButtons;
	private JButton btnLast;
	private JButton btnNext;
	private JPanel pnTutorial;
	private JPanel pnStartBoard;
	private JPanel pnGhostTypes;
	private JPanel pnLeaderGhost;
	private JPanel pnGhostBusters;
	private JPanel pnRollDice;
	private JLabel lblStartBoard;
	private JLabel lblImageStartBoard;
	private JLabel lblGhostBusters;
	private JLabel lblImageGhostBusters;
	private JLabel lblGhostTypes;
	private JPanel pnImagesGhostTypes;
	private JLabel lblGhostType0;
	private JLabel lblGhostType1;
	private JLabel lblGhostType2;
	private JLabel lblGhostTypes2;
	private JLabel lblGhostType3;
	private JLabel lblGhostTypes4;
	private JLabel lblLeaderGhost;
	private JLabel lblLeaderGhost2;
	private JLabel lblNewLabel;
	private JLabel lblRollDice;
	private JLabel lblRollDice2;
	private JLabel lblImageRollDice;
	private JPanel pnSelectGhostBuster;
	private JPanel pnHowToWin;
	private JLabel lblHowToWin;
	private JLabel lblHowToWin2;
	private JLabel lblHowToWin3;
	private JLabel lblHowToWin5;
	private JLabel lblHowToWin4;
	private JLabel lblSelectGhostBuster;
	private JLabel lblSelectGhostBuster2;
	private JLabel lblImageSelectGhostBuster;
	private JPanel pnSelectGhost;
	private JLabel lblSelectGhost;
	private JLabel lblSelectGhost2;
	private JLabel lblImageSelectGhost;
	private JPanel pnEndTurn;
	private JLabel lblEndTurn;
	private JLabel lblImageEndTurn;
	private JPanel pnGoodLuck;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;

	/**
	 * Create the dialog.
	 */
	public InstructionsDialog() {
		setModal(true);
		BorderLayout borderLayout = (BorderLayout) getContentPane().getLayout();
		borderLayout.setVgap(10);
		setResizable(false);
		setBackground(Color.BLACK);
		getContentPane().setBackground(Color.BLACK);
		getContentPane().add(getPnButtons(), BorderLayout.SOUTH);
		getContentPane().add(getPnTutorial(), BorderLayout.CENTER);
		setBounds(100, 100, 759, 494);

	}

	private JPanel getPnButtons() {
		if (pnButtons == null) {
			pnButtons = new JPanel();
			pnButtons.setBackground(Color.BLACK);
			pnButtons.add(getBtnLast());
			pnButtons.add(getBtnNext());
		}
		return pnButtons;
	}

	private JButton getBtnLast() {
		if (btnLast == null) {
			btnLast = new JButton("<");
			btnLast.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					CardLayout layout = (CardLayout) getPnTutorial().getLayout();
					layout.previous(getPnTutorial());
				}
			});
			btnLast.setBackground(Color.WHITE);
			btnLast.setFont(new Font("Tahoma", Font.PLAIN, 20));
		}
		return btnLast;
	}

	private JButton getBtnNext() {
		if (btnNext == null) {
			btnNext = new JButton(">");
			btnNext.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					CardLayout layout = (CardLayout) getPnTutorial().getLayout();
					layout.next(getPnTutorial());
				}
			});
			btnNext.setBackground(Color.WHITE);
			btnNext.setFont(new Font("Tahoma", Font.PLAIN, 20));
		}
		return btnNext;
	}

	private JPanel getPnTutorial() {
		if (pnTutorial == null) {
			pnTutorial = new JPanel();
			pnTutorial.setBackground(Color.BLACK);
			pnTutorial.setLayout(new CardLayout(0, 0));
			pnTutorial.add(getPnStartBoard(), "name_1626218245515000");
			pnTutorial.add(getPnGhostBusters(), "name_1626276968861300");
			pnTutorial.add(getPnGhostTypes(), "name_1626241780667000");
			pnTutorial.add(getPnLeaderGhost(), "name_1626258483895500");
			pnTutorial.add(getPnHowToWin(), "name_1628842923623900");
			pnTutorial.add(getPnRollDice(), "name_1626808302230900");
			pnTutorial.add(getPnSelectGhostBuster(), "name_1628460685728900");
			pnTutorial.add(getPnSelectGhost(), "name_1629901207716400");
			pnTutorial.add(getPnEndTurn(), "name_1630330809954900");
			pnTutorial.add(getPnGoodLuck(), "name_1630440090183300");

		}
		return pnTutorial;
	}

	private JPanel getPnStartBoard() {
		if (pnStartBoard == null) {
			pnStartBoard = new JPanel();
			pnStartBoard.setBackground(Color.BLACK);
			pnStartBoard.setLayout(new BorderLayout(0, 0));
			pnStartBoard.add(getLblStartBoard(), BorderLayout.NORTH);
			pnStartBoard.add(getLblImageStartBoard(), BorderLayout.CENTER);
		}
		return pnStartBoard;
	}

	private JPanel getPnGhostTypes() {
		if (pnGhostTypes == null) {
			pnGhostTypes = new JPanel();
			pnGhostTypes.setBackground(Color.BLACK);
			pnGhostTypes.setLayout(new BorderLayout(0, 30));
			pnGhostTypes.add(getLblGhostTypes(), BorderLayout.NORTH);
			pnGhostTypes.add(getPnImagesGhostTypes(), BorderLayout.CENTER);
			pnGhostTypes.add(getLblGhostTypes2(), BorderLayout.SOUTH);
		}
		return pnGhostTypes;
	}

	private JPanel getPnLeaderGhost() {
		if (pnLeaderGhost == null) {
			pnLeaderGhost = new JPanel();
			pnLeaderGhost.setBackground(Color.BLACK);
			pnLeaderGhost.setLayout(new BorderLayout(0, 0));
			pnLeaderGhost.add(getLblLeaderGhost(), BorderLayout.NORTH);
			pnLeaderGhost.add(getLblLeaderGhost2(), BorderLayout.SOUTH);
			pnLeaderGhost.add(getLblNewLabel(), BorderLayout.CENTER);
		}
		return pnLeaderGhost;
	}

	private JPanel getPnGhostBusters() {
		if (pnGhostBusters == null) {
			pnGhostBusters = new JPanel();
			pnGhostBusters.setBackground(Color.BLACK);
			pnGhostBusters.setLayout(new BorderLayout(0, 0));
			pnGhostBusters.add(getLblGhostBusters(), BorderLayout.NORTH);
			pnGhostBusters.add(getLblImageGhostBusters(), BorderLayout.CENTER);
		}
		return pnGhostBusters;
	}

	private JPanel getPnRollDice() {
		if (pnRollDice == null) {
			pnRollDice = new JPanel();
			pnRollDice.setBackground(Color.BLACK);
			pnRollDice.setLayout(new BorderLayout(0, 0));
			pnRollDice.add(getLblRollDice(), BorderLayout.NORTH);
			pnRollDice.add(getLblRollDice2(), BorderLayout.SOUTH);
			pnRollDice.add(getLblImageRollDice(), BorderLayout.CENTER);
		}
		return pnRollDice;
	}

	private JLabel getLblStartBoard() {
		if (lblStartBoard == null) {
			lblStartBoard = new JLabel("This is the board of the game");
			lblStartBoard.setHorizontalAlignment(SwingConstants.CENTER);
			lblStartBoard.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblStartBoard.setForeground(Color.WHITE);
			lblStartBoard.setBackground(Color.BLACK);
		}
		return lblStartBoard;
	}

	private JLabel getLblImageStartBoard() {
		if (lblImageStartBoard == null) {
			lblImageStartBoard = new JLabel("");
			lblImageStartBoard.setHorizontalAlignment(SwingConstants.CENTER);
			lblImageStartBoard
					.setIcon(new ImageIcon(InstructionsDialog.class.getResource(imagePath + "fullBoard.png")));
		}
		return lblImageStartBoard;
	}

	private JLabel getLblGhostBusters() {
		if (lblGhostBusters == null) {
			lblGhostBusters = new JLabel("This are the characters you control");
			lblGhostBusters.setHorizontalAlignment(SwingConstants.CENTER);
			lblGhostBusters.setForeground(Color.WHITE);
			lblGhostBusters.setFont(new Font("Tahoma", Font.PLAIN, 16));
		}
		return lblGhostBusters;
	}

	private JLabel getLblImageGhostBusters() {
		if (lblImageGhostBusters == null) {
			lblImageGhostBusters = new JLabel("");
			lblImageGhostBusters.setIcon(
					new ImageIcon(InstructionsDialog.class.getResource(imagePath + "tutorialGhostBuster.png")));
			lblImageGhostBusters.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblImageGhostBusters;
	}

	private JLabel getLblGhostTypes() {
		if (lblGhostTypes == null) {
			lblGhostTypes = new JLabel("These are the 5 different types of ghost you can find.");
			lblGhostTypes.setHorizontalAlignment(SwingConstants.CENTER);
			lblGhostTypes.setForeground(Color.WHITE);
			lblGhostTypes.setFont(new Font("Tahoma", Font.PLAIN, 16));
		}
		return lblGhostTypes;
	}

	private JPanel getPnImagesGhostTypes() {
		if (pnImagesGhostTypes == null) {
			pnImagesGhostTypes = new JPanel();
			FlowLayout flowLayout = (FlowLayout) pnImagesGhostTypes.getLayout();
			flowLayout.setHgap(30);
			flowLayout.setVgap(50);
			pnImagesGhostTypes.setBackground(Color.BLACK);
			pnImagesGhostTypes.add(getLblGhostType0());
			pnImagesGhostTypes.add(getLblGhostType1());
			pnImagesGhostTypes.add(getLblGhostType2());
			pnImagesGhostTypes.add(getLblGhostType3());
			pnImagesGhostTypes.add(getLblGhostTypes4());
		}
		return pnImagesGhostTypes;
	}

	private JLabel getLblGhostType0() {
		if (lblGhostType0 == null) {
			lblGhostType0 = new JLabel("");
			lblGhostType0.setIcon(new ImageIcon(InstructionsDialog.class.getResource(imagePath + "ghostType0.png")));
		}
		return lblGhostType0;
	}

	private JLabel getLblGhostType1() {
		if (lblGhostType1 == null) {
			lblGhostType1 = new JLabel("");
			lblGhostType1.setIcon(new ImageIcon(InstructionsDialog.class.getResource(imagePath + "ghostType1.png")));
		}
		return lblGhostType1;
	}

	private JLabel getLblGhostType2() {
		if (lblGhostType2 == null) {
			lblGhostType2 = new JLabel("");
			lblGhostType2.setIcon(new ImageIcon(InstructionsDialog.class.getResource(imagePath + "ghostType2.png")));
		}
		return lblGhostType2;
	}

	private JLabel getLblGhostTypes2() {
		if (lblGhostTypes2 == null) {
			lblGhostTypes2 = new JLabel("You'll find 3 of each in the board.");
			lblGhostTypes2.setHorizontalAlignment(SwingConstants.CENTER);
			lblGhostTypes2.setForeground(Color.WHITE);
			lblGhostTypes2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		}
		return lblGhostTypes2;
	}

	private JLabel getLblGhostType3() {
		if (lblGhostType3 == null) {
			lblGhostType3 = new JLabel("");
			lblGhostType3.setIcon(new ImageIcon(InstructionsDialog.class.getResource(imagePath + "ghostType3.png")));
		}
		return lblGhostType3;
	}

	private JLabel getLblGhostTypes4() {
		if (lblGhostTypes4 == null) {
			lblGhostTypes4 = new JLabel("");
			lblGhostTypes4.setIcon(new ImageIcon(InstructionsDialog.class.getResource(imagePath + "ghostType4.png")));
		}
		return lblGhostTypes4;
	}

	private JLabel getLblLeaderGhost() {
		if (lblLeaderGhost == null) {
			lblLeaderGhost = new JLabel("And this is the ghost leader.");
			lblLeaderGhost.setHorizontalAlignment(SwingConstants.CENTER);
			lblLeaderGhost.setForeground(Color.WHITE);
			lblLeaderGhost.setFont(new Font("Tahoma", Font.PLAIN, 16));
		}
		return lblLeaderGhost;
	}

	private JLabel getLblLeaderGhost2() {
		if (lblLeaderGhost2 == null) {
			lblLeaderGhost2 = new JLabel("There is only one in the board");
			lblLeaderGhost2.setForeground(Color.WHITE);
			lblLeaderGhost2.setHorizontalAlignment(SwingConstants.CENTER);
			lblLeaderGhost2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		}
		return lblLeaderGhost2;
	}

	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("");
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setIcon(
					new ImageIcon(InstructionsDialog.class.getResource(imagePath + "tutorialLeaderGhost.png")));
		}
		return lblNewLabel;
	}

	private JLabel getLblRollDice() {
		if (lblRollDice == null) {
			lblRollDice = new JLabel("To start each turn, press the \"roll\" button");
			lblRollDice.setHorizontalAlignment(SwingConstants.CENTER);
			lblRollDice.setForeground(Color.WHITE);
			lblRollDice.setFont(new Font("Tahoma", Font.PLAIN, 16));
		}
		return lblRollDice;
	}

	private JLabel getLblRollDice2() {
		if (lblRollDice2 == null) {
			lblRollDice2 = new JLabel("You can get a 1 or a 2");
			lblRollDice2.setHorizontalAlignment(SwingConstants.CENTER);
			lblRollDice2.setForeground(Color.WHITE);
			lblRollDice2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		}
		return lblRollDice2;
	}

	private JLabel getLblImageRollDice() {
		if (lblImageRollDice == null) {
			lblImageRollDice = new JLabel("");
			lblImageRollDice
					.setIcon(new ImageIcon(InstructionsDialog.class.getResource(imagePath + "tutorialRollDice.png")));
			lblImageRollDice.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblImageRollDice;
	}

	private JPanel getPnSelectGhostBuster() {
		if (pnSelectGhostBuster == null) {
			pnSelectGhostBuster = new JPanel();
			pnSelectGhostBuster.setBackground(Color.BLACK);
			pnSelectGhostBuster.setLayout(new BorderLayout(0, 0));
			pnSelectGhostBuster.add(getLblSelectGhostBuster(), BorderLayout.NORTH);
			pnSelectGhostBuster.add(getLblSelectGhostBuster2(), BorderLayout.SOUTH);
			pnSelectGhostBuster.add(getLblImageSelectGhostBuster(), BorderLayout.CENTER);
		}
		return pnSelectGhostBuster;
	}

	private JPanel getPnHowToWin() {
		if (pnHowToWin == null) {
			pnHowToWin = new JPanel();
			pnHowToWin.setBackground(Color.BLACK);
			pnHowToWin.setLayout(new GridLayout(0, 1, 0, 0));
			pnHowToWin.add(getLblHowToWin());
			pnHowToWin.add(getLblHowToWin2());
			pnHowToWin.add(getLblHowToWin3());
			pnHowToWin.add(getLblNewLabel_3());
			pnHowToWin.add(getLblHowToWin4());
			pnHowToWin.add(getLblHowToWin5());
		}
		return pnHowToWin;
	}

	private JLabel getLblHowToWin() {
		if (lblHowToWin == null) {
			lblHowToWin = new JLabel("How to win? You may ask");
			lblHowToWin.setHorizontalAlignment(SwingConstants.CENTER);
			lblHowToWin.setForeground(Color.WHITE);
			lblHowToWin.setFont(new Font("Tahoma", Font.PLAIN, 16));
		}
		return lblHowToWin;
	}

	private JLabel getLblHowToWin2() {
		if (lblHowToWin2 == null) {
			lblHowToWin2 = new JLabel("If you remove one of each type of ghost, you win a 10% discount");
			lblHowToWin2.setHorizontalAlignment(SwingConstants.CENTER);
			lblHowToWin2.setForeground(Color.WHITE);
			lblHowToWin2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		}
		return lblHowToWin2;
	}

	private JLabel getLblHowToWin3() {
		if (lblHowToWin3 == null) {
			lblHowToWin3 = new JLabel("If you remove one of each type AND the leader, you get a 25%  discount");
			lblHowToWin3.setHorizontalAlignment(SwingConstants.CENTER);
			lblHowToWin3.setForeground(Color.WHITE);
			lblHowToWin3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		}
		return lblHowToWin3;
	}

	private JLabel getLblHowToWin5() {
		if (lblHowToWin5 == null) {
			lblHowToWin5 = new JLabel("Ok but... How to play?");
			lblHowToWin5.setHorizontalAlignment(SwingConstants.CENTER);
			lblHowToWin5.setForeground(Color.WHITE);
			lblHowToWin5.setFont(new Font("Tahoma", Font.PLAIN, 16));
		}
		return lblHowToWin5;
	}

	private JLabel getLblHowToWin4() {
		if (lblHowToWin4 == null) {
			lblHowToWin4 = new JLabel("To do so, you have a total of 7 turns");
			lblHowToWin4.setHorizontalAlignment(SwingConstants.CENTER);
			lblHowToWin4.setForeground(Color.WHITE);
			lblHowToWin4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		}
		return lblHowToWin4;
	}

	private JLabel getLblSelectGhostBuster() {
		if (lblSelectGhostBuster == null) {
			lblSelectGhostBuster = new JLabel("Now, select one of the available ghost busters");
			lblSelectGhostBuster.setHorizontalAlignment(SwingConstants.CENTER);
			lblSelectGhostBuster.setForeground(Color.WHITE);
			lblSelectGhostBuster.setFont(new Font("Tahoma", Font.PLAIN, 16));
			lblSelectGhostBuster.setBackground(Color.WHITE);
		}
		return lblSelectGhostBuster;
	}

	private JLabel getLblSelectGhostBuster2() {
		if (lblSelectGhostBuster2 == null) {
			lblSelectGhostBuster2 = new JLabel(
					"The number of available ghost busters depend on how many ghost are left and the dice");
			lblSelectGhostBuster2.setHorizontalAlignment(SwingConstants.CENTER);
			lblSelectGhostBuster2.setForeground(Color.WHITE);
			lblSelectGhostBuster2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		}
		return lblSelectGhostBuster2;
	}

	private JLabel getLblImageSelectGhostBuster() {
		if (lblImageSelectGhostBuster == null) {
			lblImageSelectGhostBuster = new JLabel("");
			lblImageSelectGhostBuster
					.setIcon(new ImageIcon(InstructionsDialog.class.getResource(imagePath + "selecGhostBuster.png")));
			lblImageSelectGhostBuster.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblImageSelectGhostBuster;
	}

	private JPanel getPnSelectGhost() {
		if (pnSelectGhost == null) {
			pnSelectGhost = new JPanel();
			pnSelectGhost.setBackground(Color.BLACK);
			pnSelectGhost.setLayout(new BorderLayout(0, 0));
			pnSelectGhost.add(getLblSelectGhost(), BorderLayout.NORTH);
			pnSelectGhost.add(getLblSelectGhost2(), BorderLayout.SOUTH);
			pnSelectGhost.add(getLblImageSelectGhost(), BorderLayout.CENTER);
		}
		return pnSelectGhost;
	}

	private JLabel getLblSelectGhost() {
		if (lblSelectGhost == null) {
			lblSelectGhost = new JLabel(
					"When selecting a ghost buster, you can then select the ghost at the dice position");
			lblSelectGhost.setHorizontalAlignment(SwingConstants.CENTER);
			lblSelectGhost.setForeground(Color.WHITE);
			lblSelectGhost.setFont(new Font("Tahoma", Font.PLAIN, 16));
		}
		return lblSelectGhost;
	}

	private JLabel getLblSelectGhost2() {
		if (lblSelectGhost2 == null) {
			lblSelectGhost2 = new JLabel(
					"If you wanted to select other ghostbuster, you can select the other one still");
			lblSelectGhost2.setHorizontalAlignment(SwingConstants.CENTER);
			lblSelectGhost2.setForeground(Color.WHITE);
			lblSelectGhost2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		}
		return lblSelectGhost2;
	}

	private JLabel getLblImageSelectGhost() {
		if (lblImageSelectGhost == null) {
			lblImageSelectGhost = new JLabel("");
			lblImageSelectGhost.setIcon(
					new ImageIcon(InstructionsDialog.class.getResource(imagePath + "ghostBusterSelected.png")));
			lblImageSelectGhost.setHorizontalAlignment(SwingConstants.CENTER);
			lblImageSelectGhost.setForeground(Color.WHITE);
			lblImageSelectGhost.setFont(new Font("Tahoma", Font.PLAIN, 16));
		}
		return lblImageSelectGhost;
	}

	private JPanel getPnEndTurn() {
		if (pnEndTurn == null) {
			pnEndTurn = new JPanel();
			pnEndTurn.setBackground(Color.BLACK);
			pnEndTurn.setLayout(new BorderLayout(0, 0));
			pnEndTurn.add(getLblEndTurn(), BorderLayout.NORTH);
			pnEndTurn.add(getLblImageEndTurn(), BorderLayout.CENTER);
		}
		return pnEndTurn;
	}

	private JLabel getLblEndTurn() {
		if (lblEndTurn == null) {
			lblEndTurn = new JLabel("When you select the ghost, the turn ends and the ghost buster moves.");
			lblEndTurn.setForeground(Color.WHITE);
			lblEndTurn.setHorizontalAlignment(SwingConstants.CENTER);
			lblEndTurn.setFont(new Font("Tahoma", Font.PLAIN, 16));
		}
		return lblEndTurn;
	}

	private JLabel getLblImageEndTurn() {
		if (lblImageEndTurn == null) {
			lblImageEndTurn = new JLabel("");
			lblImageEndTurn
					.setIcon(new ImageIcon(InstructionsDialog.class.getResource(imagePath + "movedFinished.png")));
			lblImageEndTurn.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblImageEndTurn;
	}

	private JPanel getPnGoodLuck() {
		if (pnGoodLuck == null) {
			pnGoodLuck = new JPanel();
			pnGoodLuck.setBackground(Color.BLACK);
			pnGoodLuck.setLayout(new BorderLayout(0, 0));
			pnGoodLuck.add(getLblNewLabel_1(), BorderLayout.CENTER);
			pnGoodLuck.add(getLblNewLabel_2(), BorderLayout.SOUTH);
		}
		return pnGoodLuck;
	}

	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("Good luck!!");
			lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 40));
			lblNewLabel_1.setForeground(Color.WHITE);
			lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return lblNewLabel_1;
	}

	private JLabel getLblNewLabel_2() {
		if (lblNewLabel_2 == null) {
			lblNewLabel_2 = new JLabel(
					"(You can close this in the 'X' button in the upper-right corner of the window)");
			lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_2.setForeground(Color.WHITE);
			lblNewLabel_2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		}
		return lblNewLabel_2;
	}

	private JLabel getLblNewLabel_3() {
		if (lblNewLabel_3 == null) {
			lblNewLabel_3 = new JLabel(
					"If you have won already a discount, no new one would be saved until the former is used up");
			lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_3.setForeground(Color.WHITE);
			lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD, 15));
		}
		return lblNewLabel_3;
	}
}
