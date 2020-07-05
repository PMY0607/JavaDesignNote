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
 * 主界面
 * 
 * @author PMY
 *
 */
public class NoteFrame extends JFrame {

	private JPanel contentPane;
	private final static int HEIGHT = 450; // 窗口宽高
	private final static int WIDTH = 750;
	private UndoManager manager = new UndoManager();
	private JTextArea text;
	private JMenuBar Mb; // 菜单栏
	private JMenu m1, m2, m3, m33, m4; // 菜单项
	private JMenuItem m11, m12, m13, m14, m15, m16, m21, m23, m24, m25, m26, m27, m32, m331, m332, m333, m334, m335,
			m35, m34, m41; // 菜单选项
	private JCheckBoxMenuItem m31; // 自动换行
	private MenuItem m51, m52, m53, m54, m55, m56, m57; // 右键菜单选项
	private PopupMenu pMenu; // 右键菜单
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
	 * 新建一个记事本窗口
	 * 
	 * @param use 当前操作用户对象
	 */
	public NoteFrame(User use) {
		this.user = use;
		if (user != null)
			setTitle(user.getUsername());

		text = new JTextArea();

		text.getDocument().addUndoableEditListener(manager);// 设置文本框编辑监听
		text.setFont(new Font("宋体", Font.PLAIN, 14));
		text.setLineWrap(true); // 是否换行
		text.setWrapStyleWord(true); // 是否单词边界换行（即有空白）
		text.setMargin(new Insets(3, 5, 3, 5));// 文本区与边框的间距，四个参数分别为上、左、下、右
		text.setDragEnabled(true); // 开启或关闭自动拖动处理
		getContentPane().add(new JScrollPane(text, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));// 垂直和水平的滚动条，需要的时候才显示
		init();
		Mb = new JMenuBar();
		this.setJMenuBar(Mb);

		// 文件菜单
		JMenu m1 = new JMenu("\u6587\u4EF6");

		// 打开
		m11 = new JMenuItem("\u6253\u5F00");
		m11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					// 设置打开时的默认目录，两种方式
					chooser = new JFileChooser("C:\\Users\\PMY\\Desktop");
					chooser.setFileFilter(new filter());
					result = chooser.showOpenDialog(null); // 弹出打开窗口获得按钮结果
					if (result == JFileChooser.APPROVE_OPTION) {// 打开按钮
						file = chooser.getSelectedFile();
						int length = (int) file.length();
						//BufferedReader reader = new BufferedReader(new FileReader(file));
						InputStreamReader reader = new InputStreamReader(new FileInputStream(file));
						char[] ch = new char[length];
						reader.read(ch);
						reader.close();
						text.setText(new String(ch).trim());
						setTitle(user.getUsername() + " 正在编辑:" + file.getName());

					} else if (result == JFileChooser.CANCEL_OPTION) {
						// 点击了取消按钮
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, e2);
				}
			}

		});

		// 新建
		m12 = new JMenuItem("\u65B0\u5EFA");
		m12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				text.setText("");
				setTitle(user.getUsername() + " 正在编辑：新建文本文档");
				file = null;
			}
		});

		// 保存
		m13 = new JMenuItem("\u4FDD\u5B58");
		m13.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (file == null) { // 当前操作文件为空
					chooser = new JFileChooser("C:\\Users\\PMY\\Desktop"); // 打开文件选择存入
					chooser.setFileFilter(new filter());
					result = chooser.showSaveDialog(null);

					if (result == JFileChooser.APPROVE_OPTION) {
						File selectFile = chooser.getSelectedFile();
						String end = chooser.getFileFilter().getDescription();
						File newFile = null;
						if (selectFile.getAbsolutePath().toLowerCase().endsWith(end.toLowerCase())) {
							// 如果文件是以选定扩展名结束的，则直接保存到选定文件内
							newFile = selectFile;
						} else {
							// 加上扩展名
							newFile = new File(selectFile.getAbsoluteFile() + end);
						}
						try {
							if (newFile.exists() == false) {
								newFile.createNewFile();
							}
							// 开始写入文件
							//BufferedWriter br = new BufferedWriter(new FileWriter(newFile));
							OutputStreamWriter br = new OutputStreamWriter(new FileOutputStream(newFile));
							char chs[] = text.getText().toCharArray();
							br.write(chs);
							br.flush();
							br.close();
							setTitle(user.getUsername() + " 正在编辑:" + newFile.getName());
							file = newFile;
							if (user != null)
								odi.setUserFile(user.getUsername(), file.getAbsolutePath());
						} catch (Exception ee) {
							JOptionPane.showMessageDialog(null, ee.toString());
						}
					}

				} else { // 已存在编辑的文档直接保存即可
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

		// 另存为
		m14 = new JMenuItem("\u53E6\u5B58\u4E3A");
		m14.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					chooser = new JFileChooser("C:\\Users\\xiaozhx\\Desktop");
					chooser.setFileFilter(new filter());
					result = chooser.showSaveDialog(null);
					if (result == JFileChooser.APPROVE_OPTION) {
						File selectfile = chooser.getSelectedFile(); // 获得文件名
						// 被筛选出来的文件后缀名
						String end = chooser.getFileFilter().getDescription();
						File newFile = null;
						if (selectfile.getAbsolutePath().toUpperCase().endsWith(end.toUpperCase())) {
							// 如果文件后缀一致，则使用原来的名字
							newFile = selectfile;
						} else {
							// 否则加上选定的后缀
							newFile = new File(selectfile.getAbsolutePath() + end);
						}
						try { // 判断是否存在
							if (newFile.exists() == false) {
								newFile.createNewFile();
							} // 开始写入文件
							//FileWriter writer = new FileWriter(newFile);
							OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(newFile));
							char[] arry = text.getText().toCharArray();
							writer.write(arry);
							writer.flush();
							writer.close();
							setTitle(user.getUsername() + " 正在编辑:" + newFile.getName());
							file = newFile;
							if (user != null)
								odi.setUserFile(user.getUsername(), file.getAbsolutePath());
						} catch (IOException e1) {
						}
					} else if (result == JFileChooser.CANCEL_OPTION) {
						// 点击了取消按钮asfasfasf
					}
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, e2);
				}
			}

		});

		// 退出
		m15 = new JMenuItem("\u9000\u51FA");
		m15.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int saveResult = JOptionPane.showConfirmDialog(null, "是否保存当前文件？");
				if (saveResult == 0) { // 保存当前文件
					if (file == null) { // 当前操作文件为空
						chooser = new JFileChooser("C:\\Users\\PMY\\Desktop"); // 打开文件选择存入
						chooser.setFileFilter(new filter());
						result = chooser.showSaveDialog(null);

						if (result == JFileChooser.APPROVE_OPTION) {
							File selectFile = chooser.getSelectedFile();
							String end = chooser.getFileFilter().getDescription();
							File newFile = null;
							if (selectFile.getAbsolutePath().toLowerCase().endsWith(end.toLowerCase())) {
								// 如果文件是以选定扩展名结束的，则直接保存到选定文件内
								newFile = selectFile;
							} else {
								// 加上扩展名
								newFile = new File(selectFile.getAbsoluteFile() + end);
							}
							try {
								if (newFile.exists() == false) {
									newFile.createNewFile();
								}
								// 开始写入文件
								//BufferedWriter br = new BufferedWriter(new FileWriter(newFile));
								OutputStreamWriter br = new OutputStreamWriter(new FileOutputStream(newFile));
								char chs[] = text.getText().toCharArray();
								br.write(chs);
								br.flush();
								br.close();
								setTitle(user.getUsername() + " 正在编辑:" + newFile.getName());
								file = newFile;
								if (user != null)
									odi.setUserFile(user.getUsername(), file.getAbsolutePath());
							} catch (Exception ee) {
								JOptionPane.showMessageDialog(null, ee.toString());
							}

						}

					} else { // 已存在编辑的文档直接保存即可
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
					JOptionPane.showMessageDialog(null, "感谢使用Java记事本，祝您每天拥有好心情!", "谢谢使用", JOptionPane.PLAIN_MESSAGE);
					dispose();
				} else if (saveResult == 1) {
					JOptionPane.showMessageDialog(null, "感谢使用Java记事本，祝您每天拥有好心情!", "谢谢使用", JOptionPane.PLAIN_MESSAGE);
					dispose();
				}
				DB.closeConnection();
			}

		});

		// 编辑菜单
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

		// 撤销
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
		
		
		//撤销
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

		// 复制
		m23 = new JMenuItem("\u590D\u5236");
		m23.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				text.copy();
			}
		});

		// 粘贴
		m24 = new JMenuItem("\u7C98\u8D34");
		m24.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				text.paste();
			}
		});

		// 剪切
		m25 = new JMenuItem("\u526A\u5207");
		m25.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				text.cut();
			}
		});

		// 删除
		m26 = new JMenuItem("\u5220\u9664");
		m26.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				text.replaceRange(null, text.getSelectionStart(), text.getSelectionEnd());
			}
		});

		// 插入当前时间
		m27 = new JMenuItem("\u5F53\u524D\u65F6\u95F4");
		m27.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
				text.append(time);
			}
		});

		// 格式菜单
		m3 = new JMenu("\u683C\u5F0F");

		// 自动换行
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

		// 字体选择
		m32 = new JMenuItem("\u5B57\u4F53\u9009\u62E9");
		m32.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
				// 获取系统字体
				JList<String> fontNames = new JList<String>(ge.getAvailableFontFamilyNames());
				int response = JOptionPane.showConfirmDialog(null, new JScrollPane(fontNames), "请选择字体",
						JOptionPane.OK_CANCEL_OPTION);
				Object selectedFont = fontNames.getSelectedValue();
				if (response == JOptionPane.OK_OPTION && selectedFont != null)
					text.setFont(
							new Font(fontNames.getSelectedValue().toString(), Font.PLAIN, text.getFont().getSize()));
			}
		});

		// 查找/替换
		m35 = new JMenuItem("\u67E5\u627E/\u66FF\u6362");
		m35.setMnemonic(KeyEvent.VK_F);
		m35.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ReplaceFrame(text);
			}
		});

		// 帮助菜单
		m4 = new JMenu("\u5E2E\u52A9");

		// 关于记事本
		m41 = new JMenuItem("\u5173\u4E8E\u8BB0\u4E8B\u672C");
		m41.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "记事本\n开发语言：JAVA\n开发者：彭明源\n开发时间：2020/6/28", "关于", JOptionPane.PLAIN_MESSAGE);
			}
		});

		m16 = new JMenuItem("\u5386\u53F2\u6587\u4EF6");
		m16.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new HistoryFrame(getFrame(), user, text);
			}

		});

		// 颜色选择子菜单
		m33 = new JMenu("\u989C\u8272\u8BBE\u7F6E");
		// 字体颜色
		m331 = new JMenuItem("\u5B57\u4F53\u989C\u8272");
		m331.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color color = JColorChooser.showDialog(null, "文字颜色选择", Color.BLACK);
				text.setForeground(color);
			}
		});
		// 背景颜色
		m332 = new JMenuItem("\u80CC\u666F\u989C\u8272");
		m332.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color color = JColorChooser.showDialog(null, "背景颜色选择", Color.WHITE);
				text.setBackground(color);
			}
		});
		// 光标颜色
		m333 = new JMenuItem("\u5149\u6807\u989C\u8272");
		m333.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color color = JColorChooser.showDialog(null, "光标颜色选择", Color.BLACK);
				text.setCaretColor(color);
			}
		});
		// 选择字体颜色
		m334 = new JMenuItem("\u9009\u4E2D\u5B57\u4F53\u989C\u8272");
		m334.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color color = JColorChooser.showDialog(null, "选择字体背景颜色选择", Color.WHITE);
				text.setSelectedTextColor(color);
			}
		});
		// 选择字体颜色
		m335 = new JMenuItem("\u9009\u4E2D\u5B57\u4F53\u80CC\u666F\u989C\u8272");
		m335.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color color = JColorChooser.showDialog(null, "选择字体背景颜色选择", Color.WHITE);
				text.setSelectionColor(color);
			}
		});

		// 字号选择
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
				int response = JOptionPane.showConfirmDialog(null, new JScrollPane(fontSize), "请选择字体",
						JOptionPane.OK_CANCEL_OPTION);
				Integer selectedSize = fontSize.getSelectedValue();
				if (response == JOptionPane.OK_OPTION && selectedSize != null) {
					text.setFont(new Font(text.getFont().toString(), text.getFont().getStyle(), selectedSize));
				}

			}
		});

		pMenu = new PopupMenu(); // 创建弹出式菜单，下面三项是菜单项
		m51 = new MenuItem("复制");
		m52 = new MenuItem("粘贴");
		m53 = new MenuItem("剪切");
		m54 = new MenuItem("撤销");
		m55 = new MenuItem("删除");
		m56 = new MenuItem("全选");
		m57 = new MenuItem("恢复");
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

		// 右击菜单弹出
		pMenu.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String s = e.getActionCommand();
				// 复制
				if (s.equals(m51.getActionCommand())) {
					text.copy();
				} else // 粘贴
				if (s.equals(m52.getActionCommand())) {
					text.paste();
				} else // 剪切
				if (s.equals(m53.getActionCommand())) {
					text.cut();
				} else // 撤销
				if (s.equals(m54.getActionCommand())) {
					if (manager.canUndo()) {
						manager.undo();
					}
				} else // 删除
				if (s.equals(m55.getActionCommand())) {
					text.replaceRange(null, text.getSelectionStart(), text.getSelectionEnd());
				} else // 全选
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
		// 当前系统窗口风格
		String lookAndFeel = UIManager.getSystemLookAndFeelClassName();
		try {
			UIManager.setLookAndFeel(lookAndFeel);
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			JOptionPane.showMessageDialog(null, e.toString());
		}
		// 默认关闭操作
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		// 窗口位置调整为屏幕中心
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setLocation(screenSize.width / 2 - WIDTH / 2, screenSize.height / 2 - HEIGHT / 2);
		// 窗口大小
		setSize(WIDTH, HEIGHT);
		setResizable(true); // 窗体是否可变
		setVisible(true); // 窗体是否可见

		if (user != null && user.getLastFileName() != null) { // 存在用户并且有最后编辑过的文档
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
						setTitle(user.getUsername() + " 正在编辑:" + f.getName());
						file = f;
					} catch (IOException ie) {
						JOptionPane.showMessageDialog(null, ie.toString());
					}
				}
			}

		}
	}

	/** 文件后缀过滤器 **/
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
