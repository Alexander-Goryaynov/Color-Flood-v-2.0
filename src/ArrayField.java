import java.awt.Color;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Random;
import javax.swing.JOptionPane;

/*
 * ArrayField stores state of the game field
 * 
 * Author: Alexander Goryaynov
 */

public class ArrayField {
	private int mainColor1, mainColor2;
	private int player1Score = 0, player2Score = 0;
	private int bestScore;
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
	// helper array for score counting
	private int[][] isCounted;
	// field design switcher
	private int k = 0; 
	// field sizes
	private int m, n;
	// array of field cells statess
	private int [][] a; 
	
	public ArrayField() {   
		m = 12;
		n = 12;
		a = new int [m][n];
		isCounted = new int [m][n];
		reset();                       
	}	
	
	public void initSmall() {
		m = 12;
		n = 12; 
		a = new int [m][n];
		isCounted = new int [m][n];
		reset();
	}
	
	public void initMedium() {   
		m = 18;
		n = 18;
		a = new int [m][n];
		isCounted = new int [m][n];
		reset();                       
	}
	
	public void initBig() {   
		m = 24;
		n = 24;
		a = new int [m][n];
		isCounted = new int [m][n];
		reset();                       
	}
	
	public void reset() {
		Random rand = new Random();
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				a[i][j] = rand.nextInt(6);
			}
		}
		mainColor1 = a[0][0];
		mainColor2 = a[m - 1][n - 1];
		player1Score = 0;
		player2Score = 0;
	}
	
	private void scoreCount1(int i, int j) {
		isCounted[i][j]++;
		player1Score++;
		if ((i > 0) && (a[i - 1][j] == mainColor1) 
				&& (isCounted [i - 1][j] == 0)) {
			scoreCount1(i - 1, j);
		}
		if ((j > 0) && (a[i][j - 1] == mainColor1) 
				&& (isCounted [i][j - 1] == 0)) {
			scoreCount1(i, j - 1);
		}
		if ((j < n - 1) && (a[i][j + 1] == mainColor1) 
				&& (isCounted [i][j + 1] == 0)) {
			scoreCount1(i, j + 1);
		}
		if ((i < m - 1) && (a[i + 1][j] == mainColor1) 
				&& (isCounted [i + 1][j] == 0)) {
			scoreCount1(i + 1, j);
		}	
	}
	
	private void scoreCount2(int i, int j) {
		isCounted[i][j]++;
		player2Score++;
		if ((i > 0) && (a[i - 1][j] == mainColor2) 
				&& (isCounted [i - 1][j] == 0)) {
			scoreCount2(i - 1, j);
		}
		if ((j > 0) && (a[i][j - 1] == mainColor2) 
				&& (isCounted [i][j - 1] == 0)) {
			scoreCount2(i, j - 1);
		}
		if ((j < n - 1) && (a[i][j + 1] == mainColor2) 
				&& (isCounted [i][j + 1] == 0)) {
			scoreCount2(i, j + 1);
		}
		if ((i < m - 1) && (a[i + 1][j] == mainColor2) 
				&& (isCounted [i + 1][j] == 0)) {
			scoreCount2(i + 1, j);
		}
	}	
	
	public int getPlayer1Score() {
		player1Score = 0;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				isCounted[i][j] = 0;
			}
		}
		scoreCount1(0, 0);
		return player1Score;
	}
	
	public int getPlayer2Score() {
		player2Score = 0;
		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				isCounted[i][j] = 0;
			}
		}
		scoreCount2(m - 1, n - 1);
		return player2Score;
	}
	
	public void changeFieldDesign() {
		k++;
		if(k > 5) {
			k = 0;
		}
	}	
	
	public void recursivePaint1(int i, int j, int color) {  
		
		a[i][j] = color;
		player1Score++;
		
		if ((i > 0) && (a[i - 1][j] == mainColor1)) {
			recursivePaint1(i - 1, j, color);
		}
		if ((j > 0) && (a[i][j - 1] == mainColor1)) {
			recursivePaint1(i, j - 1, color);
		}
		if ((j < n - 1) && (a[i][j + 1] == mainColor1)) {
			recursivePaint1(i, j + 1, color);
		}
		if ((i < m - 1) && (a[i + 1][j] == mainColor1)) {
			recursivePaint1(i + 1, j, color);
		}
	}
	
	public void recursivePaint2(int i, int j, int color) {  
		
		a[i][j] = color;
		player2Score++;
		
		if ((i > 0) && (a[i - 1][j] == mainColor2)) {
			recursivePaint2(i - 1, j, color);
		}
		if ((j > 0) && (a[i][j - 1] == mainColor2)) {
			recursivePaint2(i, j - 1, color);
		}
		if ((j < n - 1) && (a[i][j + 1] == mainColor2)) {
			recursivePaint2(i, j + 1, color);
		}
		if ((i < m - 1) && (a[i + 1][j] == mainColor2)) {
			recursivePaint2(i + 1, j, color);
		}
	}
	
	public void changeToColor1(int color) {
		if ((color != mainColor1) && (color != mainColor2)) {
		mainColor1 = a[0][0];
		recursivePaint1(0, 0, color);
		mainColor1 = a[0][0];
		}
	}
	
	public void changeToColor2(int color) {
		if ((color != mainColor1) && (color != mainColor2)) {
		mainColor2 = a[m - 1][n - 1];
		recursivePaint2(m - 1, n - 1, color);
		mainColor2 = a[m - 1][n - 1];
		}
	}
	
	private boolean isTwoColors() {		
		return ((player1Score + player2Score) == (m * n)) ? true : false;
	}
	
	public int unequalToMainColors(int color) {
		if ((color != mainColor1) && (color != mainColor2)) {
			return 1;
		} else {
			return 0;
		}
	}
	
	public boolean checkWin() {		
		return (isTwoColors()) ? true : false;
	}
	
	public void showWin() {
		if (player1Score > player2Score) {
			JOptionPane.showMessageDialog(null, "Победил игрок 1!",
					null , JOptionPane.INFORMATION_MESSAGE);
		} else if (player2Score > player1Score) {
			JOptionPane.showMessageDialog(null, "Победил игрок 2!", 
					null , JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "Ничья!", 
					null , JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	public void writeBestScore() {
		if (player1Score > player2Score) {
			bestScore = player1Score;
		} else {
			bestScore = player2Score;
		}
		try {
			File fout = new File ("src/storage.txt");
			BufferedWriter bw = new BufferedWriter(new FileWriter(fout));
			bw.write(Integer.toString(bestScore));
			bw.close();
		} catch (Exception except) {
			except.printStackTrace();
		}
	}
	
	public void readBestScore() {
		try {
			File fin = new File ("src/storage.txt");
			BufferedReader br = new BufferedReader(new FileReader(fin));
			bestScore = Integer.parseInt(br.readLine());
			br.close();
		} catch (Exception except) {
			except.printStackTrace();
		}
	}
	
	public int getBestScore() {
		return bestScore;
	}
	
	public void drawArray(Graphics g, int width, int height) {
		int cellHeight =  height / m;
		int cellWidth =  width / n;
		for (int i = 0; i < m; i++) {			
			int top = i * cellHeight;
			for (int j = 0; j < n; j++) {
				int left = j * cellWidth;			
				switch(a[i][j]) {
					case 0: 
						g.setColor(new Color(d[k][0][0], 
								d[k][0][1], d[k][0][2]));	//red
						break;
					case 1: 
						g.setColor(new Color(d[k][1][0], 
								d[k][1][1], d[k][1][2])); 	//orange
						break;
					case 2: 
						g.setColor(new Color(d[k][2][0], 
								d[k][2][1], d[k][2][2])); 	//yellow
						break;
					case 3: 
						g.setColor(new Color(d[k][3][0], 
								d[k][3][1], d[k][3][2]));	//green
						break;
					case 4: 
						g.setColor(new Color(d[k][4][0], 
								d[k][4][1], d[k][4][2]));	//blue
						break;
					case 5: 
						g.setColor(new Color(d[k][5][0], 
								d[k][5][1], d[k][5][2]));	//violet
						break;
				}
				g.fillRect(left, top, cellWidth, cellHeight);
			}
		}
	}
}
