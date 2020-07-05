package Note.Frame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import Note.Service.Impl.ReplImpl;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 * ���ҡ��滻����
 * @author PMY
 *
 */
public class ReplaceFrame extends JFrame {

	private JPanel contentPane;
	private JTextField findTf;
	private JTextField ReplTf;
	private int WIDTH = 450, HEIGHT = 300;
	private ReplImpl fr = new ReplImpl();

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					ReplaceFrame frame = new ReplaceFrame();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * �����滻����
	 * @param text �����ı���
	 */
	public ReplaceFrame(JTextArea text) {

		init();

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JLabel label = new JLabel("\u67E5\u627E");
		label.setHorizontalAlignment(SwingConstants.RIGHT);
		label.setFont(new Font("����", Font.BOLD, 27));
		label.setBounds(38, 40, 85, 39);
		contentPane.add(label);

		JLabel label_1 = new JLabel("\u66FF\u6362");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setFont(new Font("����", Font.BOLD, 27));
		label_1.setBounds(38, 112, 85, 39);
		contentPane.add(label_1);

		findTf = new JTextField();
		findTf.setBounds(147, 45, 195, 39);
		contentPane.add(findTf);
		findTf.setColumns(10);

		ReplTf = new JTextField();
		ReplTf.setColumns(10);
		ReplTf.setBounds(147, 117, 195, 39);
		contentPane.add(ReplTf);

		JButton findBt = new JButton("\u67E5\u627E");
		findBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String finds = findTf.getText();
				if (finds == null || finds.length() == 0) {
					JOptionPane.showMessageDialog(null, "��������ҵ�����");
				} else {
					String texts = text.getText();
					int start = fr.find(texts, finds);
					if (start >= 0) {
						int end = start + finds.length();
						text.setSelectionStart(start);
						text.setSelectionEnd(end);
					} else if (start == -2) {
						JOptionPane.showMessageDialog(null, "��ѯ�������ַ���");
					}
				}

			}
		});
		findBt.setFont(new Font("������κ", Font.BOLD, 23));
		findBt.setBounds(21, 182, 102, 47);
		contentPane.add(findBt);

		//�����滻
		JButton ReBt = new JButton("\u66FF\u6362");
		ReBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String finds = findTf.getText();				//����-�滻��ѡ��-�滻
				String rel = ReplTf.getText();
				int start = text.getSelectionStart();
				int end = text.getSelectionEnd();
				if (finds == null || finds.length() == 0) {
					JOptionPane.showMessageDialog(null, "����Ҫ���ҵ�����");
				} else {
					String texts = text.getText();
					 start = fr.find(texts, finds);
					if (start >= 0) {
						 end = start + finds.length();
						 text.replaceRange(rel, start, end);
						 text.select(start,start+rel.length());
						 JOptionPane.showMessageDialog(null, "�滻�ɹ�!");
					} else if (start == -2) {
						JOptionPane.showMessageDialog(null, "��ѯ�������ַ���");
					}
				}
			}

		});
		ReBt.setFont(new Font("������κ", Font.BOLD, 23));
		ReBt.setBounds(147, 182, 102, 47);
		contentPane.add(ReBt);

		//ȫ���滻
		JButton ReAllBt = new JButton("\u5168\u90E8\u66FF\u6362");
		ReAllBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String finds = findTf.getText();
				String rel = ReplTf.getText();
				if (finds == null || finds.length() <= 0) {
					JOptionPane.showMessageDialog(null, "��������ҵ��ַ�");
				} else {
					int count = fr.replaceAll(text, finds, rel);
					if (count > 0)
						JOptionPane.showMessageDialog(null, "�滻�ɹ�,���滻" + count + "��");
					else {
						JOptionPane.showMessageDialog(null, "�滻ʧ�ܣ�����û����Ҫ�滻���ַ�");
					}
				}
			}
		});
		ReAllBt.setFont(new Font("������κ", Font.BOLD, 18));
		ReAllBt.setBounds(273, 184, 123, 47);
		contentPane.add(ReAllBt);
	}

	private void init() {
		// ��ǰϵͳ���ڷ��
		setTitle("\u67E5\u627E\u4E0E\u66FF\u6362");
		String lookAndFeel = UIManager.getSystemLookAndFeelClassName();
		try {
			UIManager.setLookAndFeel(lookAndFeel);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.toString());
		}
		// Ĭ�Ϲرղ���
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// ����λ�õ���Ϊ��Ļ����
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(screenSize.width / 2 - WIDTH / 2, screenSize.height / 2 - HEIGHT / 2);
		// ���ڴ�С
		setSize(WIDTH, HEIGHT);
		setResizable(false); // �����Ƿ�ɱ�
		setVisible(true); // �����Ƿ�ɼ�
	}
}
