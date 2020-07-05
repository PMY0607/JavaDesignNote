package Note.Frame;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import org.omg.CORBA.DATA_CONVERSION;

import Note.Dao.Impl.OpDaoImpl;
import Note.Info.History;
import Note.Info.User;
import Note.Service.Impl.HistoryOrderImpl;
import Note.dbc.DB;

import javax.swing.ListSelectionModel;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * ��ʷ�ļ�����
 * 
 * @author PMY
 *
 */
public class HistoryFrame extends JFrame {

	private JPanel contentPane;
	private int lableW = 20;
	private int lableH = 20;
	private int ButW = 20;
	private int ButH = 20;
	private JScrollPane scrollPane;
	private JTable table;
	private JButton opBt;
	private JButton delBt;
	private ArrayList<History> list;
	private OpDaoImpl odi = new OpDaoImpl(DB.getConnection());
	private HistoryOrderImpl hoi = new HistoryOrderImpl();
	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					HistoryFrame frame = new HistoryFrame();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * �½�һ����ʷ��¼����
	 * 
	 * @param frame ԭ���ڶ���
	 * @param user  ����ʹ�õ��û�
	 * @param text  �����ı���
	 */
	public HistoryFrame(JFrame frame, User user, JTextArea text) {

		init();
		setBounds(100, 100, 771, 443);
		setTitle("\u5386\u53F2\u7EAA\u5F55");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		scrollPane = new JScrollPane();

		scrollPane.setBounds(0, 0, 765, 254);
		contentPane.add(scrollPane);

		scrollPane.setViewportView(table);

		opBt = new JButton("\u6253\u5F00\u9009\u4E2D\u6587\u4EF6");
		opBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				int row = table.getSelectedRow();
				OpDaoImpl odi = new OpDaoImpl(DB.getConnection());
				if (row != -1) {
					String path = (String) table.getValueAt(row, 0);
					File f = new File(path);
					if (f.exists()) { // �����ļ���򿪵��ı���
						try {
							FileReader fr = new FileReader(f);
							char chs[] = new char[(int) f.length()];
							fr.read(chs);
							text.setText(new String(chs));
							fr.close();
							JOptionPane.showMessageDialog(null, "�򿪳ɹ�");
							try {
								odi.setUserFile(user.getUsername(), path);
								frame.setTitle(user.getUsername() + "���ڱ༭��" + f.getName());
							} catch (Exception e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							JOptionPane.showMessageDialog(null, e1.toString());
						}
					} else { // ������ʾ�Ƿ�ɾ���˼�¼
						if (JOptionPane.showConfirmDialog(null, "�ļ������ڣ��Ƿ�ɾ���˼�¼��") == 0) {
							try {
								if (odi.delHistory(path)) {
									DefaultTableModel mode = (DefaultTableModel) table.getModel();
									mode.removeRow(row);

									JOptionPane.showMessageDialog(null, "ɾ���ɹ�!");
								} else {
									JOptionPane.showMessageDialog(null, "ɾ��ʧ��");
								}
							} catch (Exception e1) {
								JOptionPane.showMessageDialog(null, e1.toString());
							}
						}
					}
				}
			}

		});

		opBt.setBounds(181, 284, 123, 64);
		contentPane.add(opBt);

		delBt = new JButton("\u5220\u9664\u9009\u4E2D\u7EAA\u5F55");
		delBt.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				int row = table.getSelectedRow();
				//System.out.println(row);
				if (row != -1) {
					String path = (String) table.getValueAt(row, 0);
					try {
						if (odi.delHistory(path)) {
							DefaultTableModel mode = (DefaultTableModel) table.getModel();
							mode.removeRow(row);
							JOptionPane.showMessageDialog(null, "ɾ���ɹ�!");
						} else {

							DefaultTableModel mode = (DefaultTableModel) table.getModel();
							mode.removeRow(row);
							JOptionPane.showMessageDialog(null, "ɾ���ɹ�");
						}
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1.toString());
					}
				} else {
					JOptionPane.showMessageDialog(null, "��ѡ������");
				}
			}

		});
		delBt.setBounds(392, 284, 133, 64);
		contentPane.add(delBt);

		//����
		JButton button = new JButton("\u6392\u5E8F");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int col = table.getSelectedColumn();
				//System.out.println(col);
				if (col == -1)  col =2;
					DefaultTableModel mode = (DefaultTableModel) table.getModel();
					//mode.getDataVector();
					Vector<History> list = null;
					try {
						list = new Vector<History>(odi.getHistoryList());
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					list = hoi.orderBy(list, col);
					
					Object data[][] = new Object[list.size()][3];
					for (int i = 0; i < list.size(); i++) {
						History his = list.get(i);
						// File f = new File(his.getLastFileName());
						String date = new SimpleDateFormat("yyyy-MM-dd ").format(his.getLastDate());
						data[i] = new Object[] { his.getLastFileName(), his.getUserName(), date }; // д�뵽���
					}
					// ��ͷ
					table.setModel(new DefaultTableModel(data, new String[] { "�ļ���", "���һ�β�����", "������ʱ��" }));

			}
		});
		button.setBounds(585, 333, 113, 27);
		contentPane.add(button);
	}

	private void init() {
		table = new JTable() {
			public boolean isCellEditable(int row, int column) {
				return false;
			}// ��������༭
		};

		table.setFont(new Font("����", Font.PLAIN, 20));
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setToolTipText("\u6587\u4EF6\u64CD\u4F5C\u5386\u53F2\u7EAA\u5F55\u8868");

		try {
			list = odi.getHistoryList(); // ��ȡ�ļ�������¼
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			// e1.printStackTrace();
			JOptionPane.showMessageDialog(null, e1.toString());
		}
		// ����
		Object data[][] = new Object[list.size()][3];
		for (int i = 0; i < list.size(); i++) {
			History his = list.get(i);
			// File f = new File(his.getLastFileName());
			String date = new SimpleDateFormat("yyyy-MM-dd ").format(his.getLastDate());
			data[i] = new Object[] { his.getLastFileName(), his.getUserName(), date }; // д�뵽���
		}
		// ��ͷ
		table.setModel(new DefaultTableModel(data, new String[] { "�ļ���", "���һ�β�����", "������ʱ��" }));

		setResizable(false); // �����Ƿ�ɱ�
		setVisible(true); // �����Ƿ�ɼ�
	}
}
