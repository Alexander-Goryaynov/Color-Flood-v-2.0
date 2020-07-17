import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JTextArea;
/**
 * Info
 * 
 * Class is responsible for displaying
 * rules of the game
 * 
 * @author Alexander Goryajnov
 *
 */

public class Info extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Info dialog = new Info();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setLocationRelativeTo(null);
					dialog.setVisible(true);					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public Info() {
		getContentPane().setBackground(new Color(144, 238, 144));
		String out = "";
		out = out + "������� ����: \n" 
				+ "�������� ������� ������ ��������� ����� ������� "
				+ "���� ���� � ��� ������ ���� \n"
				+ "�� �����, ����������� � ���� �� ����������� "
				+ "� ���������.\r\n" + 
				"������� ������� ������ ������������ ���������� "
				+ "�� ������� ������� ���� \n"
				+ "����.\r\n" + 
				"������ ����� ���������. \n" +
				"�������� �� ������� ������ ����� ������ ���� "
				+ "����� �������.\r\n" + 
				"����� ���� ������ ���������� ��������.\r\n" + 
				"����� �� ����� ������� ���� ������� ������� "
				+ "������.\r\n" + 
				"����� ���� ����� �������� �� ���� ��������, "
				+ "��������� �����, ��������� \n"
				+ "������� �� ���.";
				
		setBounds(300, 200, 524, 254);
		getContentPane().setLayout(null);
		
		JTextArea txt = new JTextArea();
		txt.setEditable(false);
		txt.setBackground(new Color(144, 238, 144));
		txt.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		txt.setText(out);
		txt.setBounds(10, 11, 488, 200);
		getContentPane().add(txt);
	}

}
