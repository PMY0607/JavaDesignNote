package Frame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.Insets;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JColorChooser;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileFilter;
import javax.swing.undo.UndoManager;

import DaoImpl.OpDaoImpl;
import ServiceImpl.ReplImpl;
import UserInfo.User;
import dbc.DB;

/**
 * ������
 * 
 * @author PMY
 *
 */
public class NoteFrame extends JFrame {

	private JPanel contentPane;
	private final static int HEIGHT = 450; // ���ڿ��
	private final static int WIDTH = 750;
	private UndoManager manager = new UndoManager();
	private JTextArea text;
	private JMenuBar Mb; // �˵���
	private JMenu m1, m2, m3, m33, m4; // �˵���
	private JMenuItem m11, m12, m13, m14, m15, m16, m21, m23, m24, m25, m26, m27, m32, m331, m332, m333, m334, m335,
			m35, m34, m41; // �˵�ѡ��
	private JCheckBoxMenuItem m31; // �Զ�����
	private MenuItem m51, m52, m53, m54, m55, m56, m57; // �Ҽ��˵�ѡ��
	private PopupMenu pMenu; // �Ҽ��˵�
	public User user;
	private JFileChooser chooser;
	private int result = 0;
	public static File file;
	private OpDaoImpl odi = new OpDaoImpl(DB.getConnection());
	private JMenuItem m22;
	private String cs = "UTF-8";

//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					NoteFrame frame = new NoteFrame(null);
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * �½�һ�����±�����
	 * 
	 * @param use ��ǰ�����û�����
	 */
	public NoteFrame(User use) {
		this.user = use;
		if (user != null)
			setTitle(user.getUsername());

		text = new JTextArea();

		text.getDocument().addUndoableEditListener(manager);// �����ı���༭����
		text.setFont(new Font("����", Font.PLAIN, 14));
		text.setLineWrap(true); // �Ƿ���
		text.setWrapStyleWord(true); // �Ƿ񵥴ʱ߽绻�У����пհף�
		text.setMargin(new Insets(3, 5, 3, 5));// �ı�����߿�ļ�࣬�ĸ������ֱ�Ϊ�ϡ����¡���
		text.setDragEnabled(true); // ������ر��Զ��϶�����
		getContentPane().add(new JScrollPane(text, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));// ��ֱ��ˮƽ�Ĺ���������Ҫ��ʱ�����ʾ
		init();
		Mb = new JMenuBar();
		this.setJMenuBar(Mb);

		// �ļ��˵�
		JMenu m1 = new JMenu("\u6587\u4EF6");

		// ��
		m11 = new JMenuItem("\u6253\u5F00");
		m11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// ���ô�ʱ��Ĭ��Ŀ¼�����ַ�ʽ
					chooser = new JFileChooser("C:\\Users\\PMY\\Desktop");
					chooser.setFileFilter(new filter());
					result = chooser.showOpenDialog(null); // �����򿪴��ڻ�ð�ť���
					if (result == JFileChooser.APPROVE_OPTION) {// �򿪰�ť
						file = chooser.getSelectedFile();
						int length = (int) file.length();
						//BufferedReader reader = new BufferedReader(new FileReader(file));
						InputStreamReader reader = new InputStreamReader(new FileInputStream(file));
						char[] ch = new char[length];
						reader.read(ch);
						reader.close();
						text.setText(new String(ch).trim());
						setTitle(user.getUsername() + " ���ڱ༭:" + file.getName());

					} else if (result == JFileChooser.CANCEL_OPTION) {
						// �����ȡ����ť
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, e2);
				}
			}

		});

		// �½�
		m12 = new JMenuItem("\u65B0\u5EFA");
		m12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				text.setText("");
				setTitle(user.getUsername() + " ���ڱ༭���½��ı��ĵ�");
				file = null;
			}
		});

		// ����
		m13 = new JMenuItem("\u4FDD\u5B58");
		m13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (file == null) { // ��ǰ�����ļ�Ϊ��
					chooser = new JFileChooser("C:\\Users\\PMY\\Desktop"); // ���ļ�ѡ�����
					chooser.setFileFilter(new filter());
					result = chooser.showSaveDialog(null);

					if (result == JFileChooser.APPROVE_OPTION) {
						File selectFile = chooser.getSelectedFile();
						String end = chooser.getFileFilter().getDescription();
						File newFile = null;
						if (selectFile.getAbsolutePath().toLowerCase().endsWith(end.toLowerCase())) {
							// ����ļ�����ѡ����չ�������ģ���ֱ�ӱ��浽ѡ���ļ���
							newFile = selectFile;
						} else {
							// ������չ��
							newFile = new File(selectFile.getAbsoluteFile() + end);
						}
						try {
							if (newFile.exists() == false) {
								newFile.createNewFile();
							}
							// ��ʼд���ļ�
							//BufferedWriter br = new BufferedWriter(new FileWriter(newFile));
							OutputStreamWriter br = new OutputStreamWriter(new FileOutputStream(newFile));
							char chs[] = text.getText().toCharArray();
							br.write(chs);
							br.flush();
							br.close();
							setTitle(user.getUsername() + " ���ڱ༭:" + newFile.getName());
							file = newFile;
							if (user != null)
								odi.setUserFile(user.getUsername(), file.getAbsolutePath());
						} catch (Exception ee) {
							JOptionPane.showMessageDialog(null, ee.toString());
						}
					}

				} else { // �Ѵ��ڱ༭���ĵ�ֱ�ӱ��漴��
					char chs[] = text.getText().toCharArray();
					try {
						//BufferedWriter br = new BufferedWriter(new FileWriter(file));
						OutputStreamWriter br = new OutputStreamWriter(new FileOutputStream(file));
						br.write(chs);
						br.flush();
						br.close();
						if (user != null)
							odi.setUserFile(user.getUsername(), file.getAbsolutePath());
					} catch (Exception ie) {
						JOptionPane.showMessageDialog(null, ie.toString());
					}
				}

			}
		});

		// ���Ϊ
		m14 = new JMenuItem("\u53E6\u5B58\u4E3A");
		m14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					chooser = new JFileChooser("C:\\Users\\xiaozhx\\Desktop");
					chooser.setFileFilter(new filter());
					result = chooser.showSaveDialog(null);
					if (result == JFileChooser.APPROVE_OPTION) {
						File selectfile = chooser.getSelectedFile(); // ����ļ���
						// ��ɸѡ�������ļ���׺��
						String end = chooser.getFileFilter().getDescription();
						File newFile = null;
						if (selectfile.getAbsolutePath().toUpperCase().endsWith(end.toUpperCase())) {
							// ����ļ���׺һ�£���ʹ��ԭ��������
							newFile = selectfile;
						} else {
							// �������ѡ���ĺ�׺
							newFile = new File(selectfile.getAbsolutePath() + end);
						}
						try { // �ж��Ƿ����
							if (newFile.exists() == false) {
								newFile.createNewFile();
							} // ��ʼд���ļ�
							//FileWriter writer = new FileWriter(newFile);
							OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(newFile));
							char[] arry = text.getText().toCharArray();
							writer.write(arry);
							writer.flush();
							writer.close();
							setTitle(user.getUsername() + " ���ڱ༭:" + newFile.getName());
							file = newFile;
							if (user != null)
								odi.setUserFile(user.getUsername(), file.getAbsolutePath());
						} catch (IOException e1) {
						}
					} else if (result == JFileChooser.CANCEL_OPTION) {
						// �����ȡ����ťasfasfasf
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, e2);
				}
			}

		});

		// �˳�
		m15 = new JMenuItem("\u9000\u51FA");
		m15.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int saveResult = JOptionPane.showConfirmDialog(null, "�Ƿ񱣴浱ǰ�ļ���");
				if (saveResult == 0) { // ���浱ǰ�ļ�
					if (file == null) { // ��ǰ�����ļ�Ϊ��
						chooser = new JFileChooser("C:\\Users\\PMY\\Desktop"); // ���ļ�ѡ�����
						chooser.setFileFilter(new filter());
						result = chooser.showSaveDialog(null);

						if (result == JFileChooser.APPROVE_OPTION) {
							File selectFile = chooser.getSelectedFile();
							String end = chooser.getFileFilter().getDescription();
							File newFile = null;
							if (selectFile.getAbsolutePath().toLowerCase().endsWith(end.toLowerCase())) {
								// ����ļ�����ѡ����չ�������ģ���ֱ�ӱ��浽ѡ���ļ���
								newFile = selectFile;
							} else {
								// ������չ��
								newFile = new File(selectFile.getAbsoluteFile() + end);
							}
							try {
								if (newFile.exists() == false) {
									newFile.createNewFile();
								}
								// ��ʼд���ļ�
								//BufferedWriter br = new BufferedWriter(new FileWriter(newFile));
								OutputStreamWriter br = new OutputStreamWriter(new FileOutputStream(newFile));
								char chs[] = text.getText().toCharArray();
								br.write(chs);
								br.flush();
								br.close();
								setTitle(user.getUsername() + " ���ڱ༭:" + newFile.getName());
								file = newFile;
								if (user != null)
									odi.setUserFile(user.getUsername(), file.getAbsolutePath());
							} catch (Exception ee) {
								JOptionPane.showMessageDialog(null, ee.toString());
							}

						}

					} else { // �Ѵ��ڱ༭���ĵ�ֱ�ӱ��漴��
						char chs[] = text.getText().toCharArray();
						try {
							//BufferedWriter br = new BufferedWriter(new FileWriter(file));
							OutputStreamWriter br = new OutputStreamWriter(new FileOutputStream(file));
							br.write(chs);
							br.flush();
							br.close();
							if (user != null)
								odi.setUserFile(user.getUsername(), file.getAbsolutePath());
						} catch (Exception ie) {
							JOptionPane.showMessageDialog(null, ie.toString());
						}
					}
					JOptionPane.showMessageDialog(null, "��лʹ��Java���±���ף��ÿ��ӵ�к�����!", "ллʹ��", JOptionPane.PLAIN_MESSAGE);
					dispose();
				} else if (saveResult == 1) {
					JOptionPane.showMessageDialog(null, "��лʹ��Java���±���ף��ÿ��ӵ�к�����!", "ллʹ��", JOptionPane.PLAIN_MESSAGE);
					dispose();
				}
				DB.closeConnection();
			}

		});

		// �༭�˵�
		m2 = new JMenu("\u7F16\u8F91");
		m2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (manager.canUndo()) {
					m21.setEnabled(true);
				} else {
					m21.setEnabled(false);
				}
				if (manager.canRedo()) {
					m22.setEnabled(true);
				} else {
					m22.setEnabled(false);
				}
			}
		});

		// ����
		m21 = new JMenuItem("\u64A4\u9500");
		m21.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (manager.canUndo()) {
					manager.undo();
				}
				if (!manager.canUndo()) {
					m21.setEnabled(false);
				}
			}
		});
		
		
		//����
		m22 = new JMenuItem("\u6062\u590D");
		m22.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (manager.canRedo()) {
					manager.redo();
				}
				if (!manager.canRedo()) {
					m22.setEnabled(false);
				}
			}
		});

		// ����
		m23 = new JMenuItem("\u590D\u5236");
		m23.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				text.copy();
			}
		});

		// ճ��
		m24 = new JMenuItem("\u7C98\u8D34");
		m24.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				text.paste();
			}
		});

		// ����
		m25 = new JMenuItem("\u526A\u5207");
		m25.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				text.cut();
			}
		});

		// ɾ��
		m26 = new JMenuItem("\u5220\u9664");
		m26.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				text.replaceRange(null, text.getSelectionStart(), text.getSelectionEnd());
			}
		});

		// ���뵱ǰʱ��
		m27 = new JMenuItem("\u5F53\u524D\u65F6\u95F4");
		m27.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
				text.append(time);
			}
		});

		// ��ʽ�˵�
		m3 = new JMenu("\u683C\u5F0F");

		// �Զ�����
		m31 = new JCheckBoxMenuItem("\u81EA\u52A8\u6362\u884C");
		m31.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (m31.getState()) {
					text.setLineWrap(true);
				} else {
					text.setLineWrap(false);
				}
			}
		});
		m31.setSelected(true);

		// ����ѡ��
		m32 = new JMenuItem("\u5B57\u4F53\u9009\u62E9");
		m32.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
				// ��ȡϵͳ����
				JList<String> fontNames = new JList<String>(ge.getAvailableFontFamilyNames());
				int response = JOptionPane.showConfirmDialog(null, new JScrollPane(fontNames), "��ѡ������",
						JOptionPane.OK_CANCEL_OPTION);
				Object selectedFont = fontNames.getSelectedValue();
				if (response == JOptionPane.OK_OPTION && selectedFont != null)
					text.setFont(
							new Font(fontNames.getSelectedValue().toString(), Font.PLAIN, text.getFont().getSize()));
			}
		});

		// ����/�滻
		m35 = new JMenuItem("\u67E5\u627E/\u66FF\u6362");
		m35.setMnemonic(KeyEvent.VK_F);
		m35.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ReplaceFrame(text);
			}
		});

		// �����˵�
		m4 = new JMenu("\u5E2E\u52A9");

		// ���ڼ��±�
		m41 = new JMenuItem("\u5173\u4E8E\u8BB0\u4E8B\u672C");
		m41.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "���±�\n�������ԣ�JAVA\n�����ߣ�����Դ\n����ʱ�䣺2020/6/28", "����", JOptionPane.PLAIN_MESSAGE);
			}
		});

		m16 = new JMenuItem("\u5386\u53F2\u6587\u4EF6");
		m16.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new HistoryFrame(getFrame(), user, text);
			}

		});

		// ��ɫѡ���Ӳ˵�
		m33 = new JMenu("\u989C\u8272\u8BBE\u7F6E");
		// ������ɫ
		m331 = new JMenuItem("\u5B57\u4F53\u989C\u8272");
		m331.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color color = JColorChooser.showDialog(null, "������ɫѡ��", Color.BLACK);
				text.setForeground(color);
			}
		});
		// ������ɫ
		m332 = new JMenuItem("\u80CC\u666F\u989C\u8272");
		m332.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color color = JColorChooser.showDialog(null, "������ɫѡ��", Color.WHITE);
				text.setBackground(color);
			}
		});
		// �����ɫ
		m333 = new JMenuItem("\u5149\u6807\u989C\u8272");
		m333.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color color = JColorChooser.showDialog(null, "�����ɫѡ��", Color.BLACK);
				text.setCaretColor(color);
			}
		});
		// ѡ��������ɫ
		m334 = new JMenuItem("\u9009\u4E2D\u5B57\u4F53\u989C\u8272");
		m334.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color color = JColorChooser.showDialog(null, "ѡ�����屳����ɫѡ��", Color.WHITE);
				text.setSelectedTextColor(color);
			}
		});
		// ѡ��������ɫ
		m335 = new JMenuItem("\u9009\u4E2D\u5B57\u4F53\u80CC\u666F\u989C\u8272");
		m335.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color color = JColorChooser.showDialog(null, "ѡ�����屳����ɫѡ��", Color.WHITE);
				text.setSelectionColor(color);
			}
		});

		// �ֺ�ѡ��
		m34 = new JMenuItem("\u5B57\u53F7\u9009\u62E9");
		m34.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Integer listData[] = new Integer[100];
				int i = 21;
				while (i <= 120) {
					listData[i - 21] = i;
					i++;
				}

				JList<Integer> fontSize = new JList<Integer>(listData);
				int response = JOptionPane.showConfirmDialog(null, new JScrollPane(fontSize), "��ѡ������",
						JOptionPane.OK_CANCEL_OPTION);
				Integer selectedSize = fontSize.getSelectedValue();
				if (response == JOptionPane.OK_OPTION && selectedSize != null) {
					text.setFont(new Font(text.getFont().toString(), text.getFont().getStyle(), selectedSize));
				}

			}
		});

		pMenu = new PopupMenu(); // ��������ʽ�˵������������ǲ˵���
		m51 = new MenuItem("����");
		m52 = new MenuItem("ճ��");
		m53 = new MenuItem("����");
		m54 = new MenuItem("����");
		m55 = new MenuItem("ɾ��");
		m56 = new MenuItem("ȫѡ");
		m57 = new MenuItem("�ָ�");
		m1.add(m11);
		m1.add(m16);
		m1.add(m12);
		m1.add(m13);
		m1.add(m14);
		m1.addSeparator();
		m1.add(m15);
		Mb.add(m1);
		m2.add(m21);

		
		m2.add(m22);
		m2.addSeparator();
		m2.add(m23);
		m2.add(m24);
		m2.add(m25);
		m2.add(m26);
		m2.add(m27);
		Mb.add(m2);
		m3.add(m31);
		m3.add(m32);
		m33.add(m331);
		m33.add(m332);
		m33.add(m333);
		m33.add(m334);
		m33.add(m335);
		m3.add(m33);
		m3.add(m34);
		m3.add(m35);
		Mb.add(m3);
		m4.add(m41);
		Mb.add(m4);
		pMenu.add(m51);
		pMenu.add(m52);
		pMenu.add(m53);
		pMenu.add(m54);
		pMenu.add(m55);
		pMenu.add(m56);
		pMenu.add(m57);
		text.add(pMenu);

		text.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON3) {
					pMenu.show(text, e.getX(), e.getY());
					// System.out.println(e.getX()+" "+e.getY());
					if(manager.canUndo()) {
						m54.setEnabled(true);
					}else {
						m54.setEnabled(false);
					}
					
					if(manager.canRedo()) {
						m57.setEnabled(true);
					}else {
						m57.setEnabled(false);
					}
				}
			}
		});

		// �һ��˵�����
		pMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String s = e.getActionCommand();
				// ����
				if (s.equals(m51.getActionCommand())) {
					text.copy();
				} else // ճ��
				if (s.equals(m52.getActionCommand())) {
					text.paste();
				} else // ����
				if (s.equals(m53.getActionCommand())) {
					text.cut();
				} else // ����
				if (s.equals(m54.getActionCommand())) {
					if (manager.canUndo()) {
						manager.undo();
					}
				} else // ɾ��
				if (s.equals(m55.getActionCommand())) {
					text.replaceRange(null, text.getSelectionStart(), text.getSelectionEnd());
				} else // ȫѡ
				if (s.equals(m56.getActionCommand())) {
					text.selectAll();
				} else if (s.equals(m57.getActionCommand())) {
					if (manager.canRedo()) {
						manager.redo();
					}
				}
			}
		});
	}

	private void init() {
		// ��ǰϵͳ���ڷ��
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
		setResizable(true); // �����Ƿ�ɱ�
		setVisible(true); // �����Ƿ�ɼ�

		if (user != null && user.getLastFileName() != null) { // �����û����������༭�����ĵ�
			String LastFileName = user.getLastFileName();
			if (LastFileName != null) {
				File f = new File(LastFileName);
				if (f.exists()) {
					try {
						//BufferedReader br = new BufferedReader(new FileReader(f));
						InputStreamReader br = new InputStreamReader(new FileInputStream(f));
						char chs[] = new char[(int) f.length()];
						br.read(chs);
						text.setText(new String(chs).trim());
						setTitle(user.getUsername() + " ���ڱ༭:" + f.getName());
						file = f;
					} catch (IOException ie) {
						JOptionPane.showMessageDialog(null, ie.toString());
					}
				}
			}

		}
	}

	/** �ļ���׺������ **/
	private class filter extends FileFilter {
		public boolean accept(File file) {
			String name = file.getName();
			// name.toLowerCase();
			if (name.endsWith(".txt") || file.isDirectory())
				return true;
			else
				return false;
		}

		public String getDescription() {
			return ".txt";
		}
	}

	public User getUser() {
		return this.user;
	}

	private JFrame getFrame() {
		return this;
	}
}
