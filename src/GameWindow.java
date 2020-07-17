import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.Rectangle;
import javax.swing.border.LineBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.Timer;
import java.awt.Font;
import javax.swing.JComboBox;
import javax.swing.JDialog;

/*
 * Game Window
 * Class is responsible for displaying 
 * graphical user interface of the game
 *
 * author: Alexander Goryaynov
 */

public class GameWindow {

	private JPanel panel;
	private ArrayField arrayField;
	private JButton btnRed1;
	private JButton btnOrange1;
	private JButton btnYellow1;
	private JButton btnGreen1;
	private JButton btnBlue1;
	private JButton btnViolet1;
	private JButton btnRed2;
	private JButton btnOrange2;
	private JButton btnYellow2;
	private JButton btnGreen2;
	private JButton btnBlue2;
	private JButton btnViolet2;
	private JButton btnPause;
	private JButton btnReset;
	private JButton btnStart;
	private JComboBox<String> comboBox;
	private javax.swing.Timer timer;
	private JLabel labelChangeSize;
	private int currentPlayer = 1;
	// design colors storage
	private int[][][] d = {
			{{255,85,74},{252,175,66},{251,255,72},
				{63,196,79},{83,168,206},{80,79,169}},
			{{104,118,132},{88,216,200},{204,249,104},
				{255,119,119},{182,82,138},{255,189,104}},
			{{146,86,134},{255,102,155},{255,166,46},
				{255,223,31},{166,194,63},{76,155,158}},
			{{0,191,211},{137,98,80},{252,62,76},
				{255,131,59},{255,210,61},{102,226,61}},
			{{115,92,76},{255,128,25},{154,216,44},
				{247,244,153},{96,208,168},{255,81,107}},
			{{255,61,86},{56,50,47},{36,126,134},
				{0,219,214},{255,251,182},{231,154,0}}
	}; 
	private int isPaused = 0;
	// game design switcher
	private int k = 0;
	private int period = 15;
	private int seconds = period;

	private JFrame frame;
	private JTextField textFieldScore;
	private JTextField textFieldTimer;
	private JTextField textFieldPlayer;
	private JLabel label1;
	private JLabel label2;
	private JLabel lblColorFloodV;
	private JButton btnGameInfo;
	private JButton btnChangeDesign;
	private JTextField textFieldBestResult;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameWindow window = new GameWindow();
					window.frame.setVisible(true);
					window.frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GameWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	//@SuppressWarnings({ "unchecked" })
	private void initialize() {
		
		frame = new JFrame();
		frame.getContentPane().setBackground(new Color(238, 232, 170));
		frame.getContentPane().setLayout(null);
		
		arrayField = new ArrayField();
		panel = new ArrayPanel(arrayField);
		
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel.setBounds(114, 97, 360, 360);
		frame.getContentPane().add(panel);
		
		timer = new Timer(1000, new ActionListener() {
			public void actionPerformed(ActionEvent ev) {
				seconds--;
				if (seconds < 0) {
					seconds = period;
					changePlayer();
				}
				updateTimerInfo();
			}
		});
		
		btnReset = new JButton("\u0421\u0411\u0420\u041E\u0421");
		btnReset.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reset();
				timer.stop();
			}
		});
		btnReset.setBounds(666, 150, 89, 50);
		frame.getContentPane().add(btnReset);
		
		btnRed1 = new JButton("");
		btnRed1.setEnabled(false);
		btnRed1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (arrayField.unequalToMainColors(0) == 1) {
					arrayField.changeToColor1(0);
					panel.repaint();
					changePlayer();
					updateScore();
					if (arrayField.checkWin()) {
						endGame();
					}
				}
			}
		});
		btnRed1.setBounds(new Rectangle(0, 0, 45, 45));
		btnRed1.setBackground(new Color(d[k][0][0], d[k][0][1], d[k][0][2]));
		btnRed1.setForeground(new Color(d[k][0][0], d[k][0][1], d[k][0][2]));
		btnRed1.setBounds(20, 97, 45, 45);
		frame.getContentPane().add(btnRed1);
		
		btnOrange1 = new JButton("");
		btnOrange1.setEnabled(false);
		btnOrange1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (arrayField.unequalToMainColors(1) == 1) {
					arrayField.changeToColor1(1);
					panel.repaint();
					changePlayer();
					updateScore();
					if (arrayField.checkWin()) {
						endGame();
					}
				}
			}
		});
		btnOrange1.setBounds(new Rectangle(0, 0, 45, 45));
		btnOrange1.setBackground(new Color(d[k][1][0], d[k][1][1], d[k][1][2]));
		btnOrange1.setForeground(new Color(d[k][1][0], d[k][1][1], d[k][1][2]));
		btnOrange1.setBounds(20, 155, 45, 45);
		frame.getContentPane().add(btnOrange1);
		
		btnYellow1 = new JButton("");
		btnYellow1.setEnabled(false);
		btnYellow1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (arrayField.unequalToMainColors(2) == 1) {
					arrayField.changeToColor1(2);
					panel.repaint();
					changePlayer();
					updateScore();
					if (arrayField.checkWin()) {
						endGame();
					}
				}
			}
		});
		btnYellow1.setBounds(new Rectangle(0, 0, 45, 45));
		btnYellow1.setForeground(new Color(d[k][2][0], d[k][2][1], d[k][2][2]));
		btnYellow1.setBackground(new Color(d[k][2][0], d[k][2][1], d[k][2][2]));
		btnYellow1.setBounds(20, 221, 45, 45);
		frame.getContentPane().add(btnYellow1);
		
		btnGreen1 = new JButton("");
		btnGreen1.setEnabled(false);
		btnGreen1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (arrayField.unequalToMainColors(3) == 1) {
					arrayField.changeToColor1(3);
					panel.repaint();
					changePlayer();
					updateScore();
					if (arrayField.checkWin()) {
						endGame();
					}
				}
			}
		});
		btnGreen1.setBounds(new Rectangle(0, 0, 45, 45));
		btnGreen1.setForeground(new Color(d[k][3][0], d[k][3][1], d[k][3][2]));
		btnGreen1.setBackground(new Color(d[k][3][0], d[k][3][1], d[k][3][2]));
		btnGreen1.setBounds(20, 288, 45, 45);
		frame.getContentPane().add(btnGreen1);
		
		btnBlue1 = new JButton("");
		btnBlue1.setEnabled(false);
		btnBlue1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (arrayField.unequalToMainColors(4) == 1) {
					arrayField.changeToColor1(4);
					panel.repaint();
					changePlayer();
					updateScore();
					if (arrayField.checkWin()) {
						endGame();
					}
				}
			}
		});
		btnBlue1.setBounds(new Rectangle(0, 0, 45, 45));
		btnBlue1.setForeground(new Color(d[k][4][0], d[k][4][1], d[k][4][2]));
		btnBlue1.setBackground(new Color(d[k][4][0], d[k][4][1], d[k][4][2]));
		btnBlue1.setBounds(20, 350, 45, 45);
		frame.getContentPane().add(btnBlue1);
		
		btnViolet1 = new JButton("");
		btnViolet1.setEnabled(false);
		btnViolet1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (arrayField.unequalToMainColors(5) == 1) {
					arrayField.changeToColor1(5);
					panel.repaint();
					changePlayer();
					updateScore();
					if (arrayField.checkWin()) {
						endGame();
					}
				}
			}
		});
		btnViolet1.setBounds(new Rectangle(0, 0, 45, 45));
		btnViolet1.setForeground(new Color(d[k][5][0], d[k][5][1], d[k][5][2]));
		btnViolet1.setBackground(new Color(d[k][5][0], d[k][5][1], d[k][5][2]));
		btnViolet1.setBounds(20, 412, 45, 45);
		frame.getContentPane().add(btnViolet1);
		
		btnRed2 = new JButton("");
		btnRed2.setEnabled(false);
		btnRed2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (arrayField.unequalToMainColors(0) == 1) {
					arrayField.changeToColor2(0);
					panel.repaint();
					changePlayer();
					updateScore();
					if (arrayField.checkWin()) {
						endGame();
					}
				}
			}
		});
		btnRed2.setBounds(new Rectangle(0, 0, 45, 45));
		btnRed2.setForeground(new Color(d[k][0][0], d[k][0][1], d[k][0][2]));
		btnRed2.setBackground(new Color(d[k][0][0], d[k][0][1], d[k][0][2]));
		btnRed2.setBounds(524, 97, 45, 45);
		frame.getContentPane().add(btnRed2);
		
		btnOrange2 = new JButton("");
		btnOrange2.setEnabled(false);
		btnOrange2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (arrayField.unequalToMainColors(1) == 1) {
					arrayField.changeToColor2(1);
					panel.repaint();
					changePlayer();
					updateScore();
					if (arrayField.checkWin()) {
						endGame();
					}
				}
			}
		});
		btnOrange2.setBounds(new Rectangle(0, 0, 45, 45));
		btnOrange2.setBackground(new Color(d[k][1][0], d[k][1][1], d[k][1][2]));
		btnOrange2.setForeground(new Color(d[k][1][0], d[k][1][1], d[k][1][2]));
		btnOrange2.setBounds(524, 155, 45, 45);
		frame.getContentPane().add(btnOrange2);
		
		btnYellow2 = new JButton("");
		btnYellow2.setEnabled(false);
		btnYellow2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (arrayField.unequalToMainColors(2) == 1) {
					arrayField.changeToColor2(2);
					panel.repaint();
					changePlayer();
					updateScore();
					if (arrayField.checkWin()) {
						endGame();
					}
				}
			}
		});
		btnYellow2.setBounds(new Rectangle(0, 0, 45, 45));
		btnYellow2.setForeground(new Color(d[k][2][0], d[k][2][1], d[k][2][2]));
		btnYellow2.setBackground(new Color(d[k][2][0], d[k][2][1], d[k][2][2]));
		btnYellow2.setBounds(524, 221, 45, 45);
		frame.getContentPane().add(btnYellow2);
		
		btnGreen2 = new JButton("");
		btnGreen2.setEnabled(false);
		btnGreen2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (arrayField.unequalToMainColors(3) == 1) {
					arrayField.changeToColor2(3);
					panel.repaint();
					changePlayer();
					updateScore();
					if (arrayField.checkWin()) {
						endGame();
					}
				}
			}
		});
		btnGreen2.setBounds(new Rectangle(0, 0, 45, 45));
		btnGreen2.setForeground(new Color(d[k][3][0], d[k][3][1], d[k][3][2]));
		btnGreen2.setBackground(new Color(d[k][3][0], d[k][3][1], d[k][3][2]));
		btnGreen2.setBounds(524, 288, 45, 45);
		frame.getContentPane().add(btnGreen2);
		
		btnBlue2 = new JButton("");
		btnBlue2.setEnabled(false);
		btnBlue2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (arrayField.unequalToMainColors(4) == 1) {
					arrayField.changeToColor2(4);
					panel.repaint();
					changePlayer();
					updateScore();
					if (arrayField.checkWin()) {
						endGame();
					}
				}
			}
		});
		btnBlue2.setBounds(new Rectangle(0, 0, 45, 45));
		btnBlue2.setForeground(new Color(d[k][4][0], d[k][4][1], d[k][4][2]));
		btnBlue2.setBackground(new Color(d[k][4][0], d[k][4][1], d[k][4][2]));
		btnBlue2.setBounds(524, 350, 45, 45);
		frame.getContentPane().add(btnBlue2);
		
		btnViolet2 = new JButton("");
		btnViolet2.setEnabled(false);
		btnViolet2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (arrayField.unequalToMainColors(5) == 1) {
					arrayField.changeToColor2(5);
					panel.repaint();
					changePlayer();
					updateScore();
					if (arrayField.checkWin()) {
						endGame();
					}
				}
			}
		});
		btnViolet2.setBounds(new Rectangle(0, 0, 45, 45));
		btnViolet2.setForeground(new Color(d[k][5][0], d[k][5][1], d[k][5][2]));
		btnViolet2.setBackground(new Color(d[k][5][0], d[k][5][1], d[k][5][2]));
		btnViolet2.setBounds(524, 412, 45, 45);
		frame.getContentPane().add(btnViolet2);
		
		btnChangeDesign = new JButton("\u0418\u0417\u041C\u0415\u041D\u0418\u0422\u042C"
				+ " \u041F\u0410\u041B\u0418\u0422\u0420\u0423");
		btnChangeDesign.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnChangeDesign.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				arrayField.changeFieldDesign();
				panel.repaint();
				k++;
				if(k > 5) {
					k = 0;
				}
				btnRed1.setForeground(new Color(d[k][0][0], d[k][0][1], 
						d[k][0][2]));
				btnRed1.setBackground(new Color(d[k][0][0], d[k][0][1], 
						d[k][0][2]));
				btnOrange1.setBackground(new Color(d[k][1][0], d[k][1][1], 
						d[k][1][2]));
				btnOrange1.setForeground(new Color(d[k][1][0], d[k][1][1], 
						d[k][1][2]));
				btnYellow1.setBackground(new Color(d[k][2][0], d[k][2][1], 
						d[k][2][2]));
				btnYellow1.setForeground(new Color(d[k][2][0], d[k][2][1], 
						d[k][2][2]));
				btnGreen1.setBackground(new Color(d[k][3][0], d[k][3][1], 
						d[k][3][2]));
				btnGreen1.setForeground(new Color(d[k][3][0], d[k][3][1], 
						d[k][3][2]));
				btnBlue1.setForeground(new Color(d[k][4][0], d[k][4][1], 
						d[k][4][2]));
				btnBlue1.setBackground(new Color(d[k][4][0], d[k][4][1], 
						d[k][4][2]));
				btnViolet1.setBackground(new Color(d[k][5][0], d[k][5][1], 
						d[k][5][2]));
				btnViolet1.setForeground(new Color(d[k][5][0], d[k][5][1], 
						d[k][5][2]));
				btnRed2.setForeground(new Color(d[k][0][0], d[k][0][1], 
						d[k][0][2]));
				btnRed2.setBackground(new Color(d[k][0][0], d[k][0][1], 
						d[k][0][2]));
				btnOrange2.setBackground(new Color(d[k][1][0], d[k][1][1], 
						d[k][1][2]));
				btnOrange2.setForeground(new Color(d[k][1][0], d[k][1][1], 
						d[k][1][2]));
				btnYellow2.setBackground(new Color(d[k][2][0], d[k][2][1], 
						d[k][2][2]));
				btnYellow2.setForeground(new Color(d[k][2][0], d[k][2][1], 
						d[k][2][2]));
				btnGreen2.setBackground(new Color(d[k][3][0], d[k][3][1], 
						d[k][3][2]));
				btnGreen2.setForeground(new Color(d[k][3][0], d[k][3][1], 
						d[k][3][2]));
				btnBlue2.setForeground(new Color(d[k][4][0], d[k][4][1], 
						d[k][4][2]));
				btnBlue2.setBackground(new Color(d[k][4][0], d[k][4][1], 
						d[k][4][2]));
				btnViolet2.setBackground(new Color(d[k][5][0], d[k][5][1], 
						d[k][5][2]));
				btnViolet2.setForeground(new Color(d[k][5][0], d[k][5][1], 
						d[k][5][2]));
				if (currentPlayer == 1) {
					enableButtons1();
					disableButtons2();
				} else if (currentPlayer == 2) {
					enableButtons2();
					disableButtons1();
				}
			}
		});
		
		btnChangeDesign.setBounds(615, 97, 193, 30);
		frame.getContentPane().add(btnChangeDesign);
		
		btnStart = new JButton("\u0421\u0422\u0410\u0420\u0422");
		btnStart.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timer.start();
				btnStart.setVisible(false);
				btnReset.setVisible(false);
				comboBox.setVisible(false);
				btnPause.setVisible(true);
				labelChangeSize.setVisible(false);
				updatePlayerInfo();
				if (isPaused == 0) {
					currentPlayer = 1;
					seconds = period;
					textFieldScore.setText("0 - 0");
				} else {
					isPaused = 0;
				}
				updateTimerInfo();
			}
		});
		btnStart.setBounds(662, 384, 124, 98);
		frame.getContentPane().add(btnStart);
		
		textFieldScore = new JTextField();
		textFieldScore.setFont(new Font("Times New Roman", Font.BOLD, 32));
		textFieldScore.setEditable(false);
		textFieldScore.setBackground(new Color(238, 232, 170));
		textFieldScore.setBorder(null);
		textFieldScore.setBounds(262, 32, 152, 38);
		frame.getContentPane().add(textFieldScore);
		textFieldScore.setColumns(10);
		
		textFieldTimer = new JTextField();
		textFieldTimer.setBorder(null);
		textFieldTimer.setBackground(new Color(238, 232, 170));
		textFieldTimer.setFont(new Font("Times New Roman", Font.BOLD, 24));
		textFieldTimer.setEditable(false);
		textFieldTimer.setBounds(266, 478, 96, 20);
		frame.getContentPane().add(textFieldTimer);
		textFieldTimer.setColumns(10);
		
		textFieldPlayer = new JTextField();
		textFieldPlayer.setBackground(new Color(238, 232, 170));
		textFieldPlayer.setFont(new Font("Times New Roman", Font.BOLD, 24));
		textFieldPlayer.setBorder(null);
		textFieldPlayer.setEditable(false);
		textFieldPlayer.setBounds(238, 509, 124, 38);
		frame.getContentPane().add(textFieldPlayer);
		textFieldPlayer.setColumns(10);
		
		label1 = new JLabel("1");
		label1.setFont(new Font("Tahoma", Font.BOLD, 13));
		label1.setBounds(100, 72, 48, 14);
		frame.getContentPane().add(label1);
		
		label2 = new JLabel("2");
		label2.setFont(new Font("Tahoma", Font.BOLD, 13));
		label2.setBounds(486, 468, 48, 14);
		frame.getContentPane().add(label2);
		
		btnPause = new JButton("\u041F\u0410\u0423\u0417\u0410");
		btnPause.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnPause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timer.stop();
				isPaused = 1;
				btnStart.setVisible(true);
				btnReset.setVisible(true);
				btnPause.setVisible(false);
			}
		});
		btnPause.setBounds(666, 222, 89, 55);
		btnPause.setVisible(false);
		frame.getContentPane().add(btnPause);
		
		lblColorFloodV = new JLabel("Color Flood v 2.0 for 2 players");
		lblColorFloodV.setFont(new Font("Times New Roman", Font.ITALIC, 13));
		lblColorFloodV.setBounds(628, 11, 193, 14);
		frame.getContentPane().add(lblColorFloodV);
	
		comboBox = new JComboBox<String>();
		comboBox.addItem("Малое (12x12)");
		comboBox.addItem("Среднее (18x18)");
		comboBox.addItem("Большое (24x24)");
		comboBox.setSelectedIndex(0);
		comboBox.setBounds(646, 334, 162, 22);
		frame.getContentPane().add(comboBox);
		comboBox.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent event) {
				int m = comboBox.getSelectedIndex();
				switch (m) {
				case 0:
					arrayField.initSmall();
					break;
				case 1: 
					arrayField.initMedium();
					break;
				case 2:
					arrayField.initBig();
					break;
				}
				panel.repaint();
			}
		});
		
		labelChangeSize = new JLabel("\u0420\u0430\u0437\u043C\u0435\u0440 "
				+ "\u043F\u043E\u043B\u044F:");
		labelChangeSize.setBounds(662, 309, 93, 14);
		frame.getContentPane().add(labelChangeSize);
		
		btnGameInfo = new JButton("\u041F\u0420\u0410\u0412\u0418\u041B\u0410");
		btnGameInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Info info = new Info();
				info.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				info.setVisible(true);
			}
		});
		btnGameInfo.setFont(new Font("Times New Roman", Font.BOLD, 14));
		btnGameInfo.setBounds(646, 520, 162, 23);
		frame.getContentPane().add(btnGameInfo);
		
		textFieldBestResult = new JTextField();
		textFieldBestResult.setBorder(null);
		textFieldBestResult.setBackground(new Color(238, 232, 170));
		textFieldBestResult.setFont(new Font("Times New Roman", Font.PLAIN, 16));
		textFieldBestResult.setEditable(false);
		textFieldBestResult.setBounds(725, 52, 96, 20);
		frame.getContentPane().add(textFieldBestResult);
		textFieldBestResult.setColumns(10);
		arrayField.readBestScore();
		updateBestResult();
		
		JLabel label = new JLabel("\u041B\u0443\u0447\u0448\u0438\u0439 \u0440"
				+ "\u0435\u0437\u0443\u043B\u044C\u0442\u0430\u0442:");
		label.setBounds(605, 54, 122, 14);
		frame.getContentPane().add(label);
		
		frame.setBounds(225, 75, 847, 616);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		reset();
	}
	
	private void changePlayer() {
		if (currentPlayer == 1) {
			currentPlayer++;
			disableButtons1();
			enableButtons2();
		} else if (currentPlayer == 2) {
			currentPlayer--;
			disableButtons2();
			enableButtons1();
		}
		seconds = period;
		updatePlayerInfo();
	}
	
	private void reset() {
		arrayField.reset();
		panel.repaint();
		currentPlayer = 1;
		updatePlayerInfo();
		enableButtons1();
		disableButtons2();
		seconds = period;
		updateTimerInfo();
		textFieldScore.setText("0 - 0");
		labelChangeSize.setVisible(true);
		comboBox.setVisible(true);
	}
	
	private void updateBestResult() {
		textFieldBestResult.setText(Integer.toString(arrayField.getBestScore()));
	}
	
	private void updatePlayerInfo() {
		String outPlayer;
		outPlayer = "" + currentPlayer + "-й игрок";
		textFieldPlayer.setText(outPlayer);
	}
	
	private void updateScore() {
		String outScore;
		outScore = "" + arrayField.getPlayer1Score() + " - " + 
				arrayField.getPlayer2Score();
		textFieldScore.setText(outScore);
	}
	
	private void updateTimerInfo() {
		String outTimer;
		if (seconds > 9) {
			outTimer = "" + "0 : " + seconds;
		} else {
			outTimer = "" + "0 : 0" + seconds;
		}
		
		textFieldTimer.setText(outTimer);
	}
	
	private void enableButtons1 () {
		btnRed1.setForeground(new Color(d[k][0][0], d[k][0][1], d[k][0][2]));
		btnRed1.setBackground(new Color(d[k][0][0], d[k][0][1], d[k][0][2]));
		btnOrange1.setBackground(new Color(d[k][1][0], d[k][1][1], d[k][1][2]));
		btnOrange1.setForeground(new Color(d[k][1][0], d[k][1][1], d[k][1][2]));
		btnYellow1.setBackground(new Color(d[k][2][0], d[k][2][1], d[k][2][2]));
		btnYellow1.setForeground(new Color(d[k][2][0], d[k][2][1], d[k][2][2]));
		btnGreen1.setBackground(new Color(d[k][3][0], d[k][3][1], d[k][3][2]));
		btnGreen1.setForeground(new Color(d[k][3][0], d[k][3][1], d[k][3][2]));
		btnBlue1.setForeground(new Color(d[k][4][0], d[k][4][1], d[k][4][2]));
		btnBlue1.setBackground(new Color(d[k][4][0], d[k][4][1], d[k][4][2]));
		btnViolet1.setBackground(new Color(d[k][5][0], d[k][5][1], d[k][5][2]));
		btnViolet1.setForeground(new Color(d[k][5][0], d[k][5][1], d[k][5][2]));
		btnRed1.setEnabled(true);
		btnOrange1.setEnabled(true);
		btnYellow1.setEnabled(true);
		btnGreen1.setEnabled(true);
		btnBlue1.setEnabled(true);
		btnViolet1.setEnabled(true);
	}
		
	private void enableButtons2() {
		btnRed2.setForeground(new Color(d[k][0][0], d[k][0][1], d[k][0][2]));
		btnRed2.setBackground(new Color(d[k][0][0], d[k][0][1], d[k][0][2]));
		btnOrange2.setBackground(new Color(d[k][1][0], d[k][1][1], d[k][1][2]));
		btnOrange2.setForeground(new Color(d[k][1][0], d[k][1][1], d[k][1][2]));
		btnYellow2.setBackground(new Color(d[k][2][0], d[k][2][1], d[k][2][2]));
		btnYellow2.setForeground(new Color(d[k][2][0], d[k][2][1], d[k][2][2]));
		btnGreen2.setBackground(new Color(d[k][3][0], d[k][3][1], d[k][3][2]));
		btnGreen2.setForeground(new Color(d[k][3][0], d[k][3][1], d[k][3][2]));
		btnBlue2.setForeground(new Color(d[k][4][0], d[k][4][1], d[k][4][2]));
		btnBlue2.setBackground(new Color(d[k][4][0], d[k][4][1], d[k][4][2]));
		btnViolet2.setBackground(new Color(d[k][5][0], d[k][5][1], d[k][5][2]));
		btnViolet2.setForeground(new Color(d[k][5][0], d[k][5][1], d[k][5][2]));
		btnRed2.setEnabled(true);
		btnOrange2.setEnabled(true);
		btnYellow2.setEnabled(true);
		btnGreen2.setEnabled(true);
		btnBlue2.setEnabled(true);
		btnViolet2.setEnabled(true);
	}
	
	private void disableButtons1() {
		btnRed1.setForeground(new Color(255, 255, 255));
		btnRed1.setBackground(new Color(255, 255, 255));
		btnOrange1.setBackground(new Color(255, 255, 255));
		btnOrange1.setForeground(new Color(255, 255, 255));
		btnYellow1.setBackground(new Color(255, 255, 255));
		btnYellow1.setForeground(new Color(255, 255, 255));
		btnGreen1.setBackground(new Color(255, 255, 255));
		btnGreen1.setForeground(new Color(255, 255, 255));
		btnBlue1.setForeground(new Color(255, 255, 255));
		btnBlue1.setBackground(new Color(255, 255, 255));
		btnViolet1.setBackground(new Color(255, 255, 255));
		btnViolet1.setForeground(new Color(255, 255, 255));
		btnRed1.setEnabled(false);
		btnOrange1.setEnabled(false);
		btnYellow1.setEnabled(false);
		btnGreen1.setEnabled(false);
		btnBlue1.setEnabled(false);
		btnViolet1.setEnabled(false);
	}
	
	private void disableButtons2() {
		btnRed2.setForeground(new Color(255, 255, 255));
		btnRed2.setBackground(new Color(255, 255, 255));
		btnOrange2.setBackground(new Color(255, 255, 255));
		btnOrange2.setForeground(new Color(255, 255, 255));
		btnYellow2.setBackground(new Color(255, 255, 255));
		btnYellow2.setForeground(new Color(255, 255, 255));
		btnGreen2.setBackground(new Color(255, 255, 255));
		btnGreen2.setForeground(new Color(255, 255, 255));
		btnBlue2.setForeground(new Color(255, 255, 255));
		btnBlue2.setBackground(new Color(255, 255, 255));
		btnViolet2.setBackground(new Color(255, 255, 255));
		btnViolet2.setForeground(new Color(255, 255, 255));
		btnRed2.setEnabled(false);
		btnOrange2.setEnabled(false);
		btnYellow2.setEnabled(false);
		btnGreen2.setEnabled(false);
		btnBlue2.setEnabled(false);
		btnViolet2.setEnabled(false);
	}
	
	private void endGame() {
		arrayField.writeBestScore();
		updateBestResult();
		timer.stop();
		disableButtons1();
		disableButtons2();
		arrayField.showWin();
		btnReset.setVisible(true);
		btnStart.setVisible(true);
		btnPause.setVisible(false);
		comboBox.setVisible(true);
		labelChangeSize.setVisible(true);
	}
}
