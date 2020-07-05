package Note.Frame;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Note.Service.Impl.UserDaoImpl;
import Note.dbc.DB;
/**
 * ע�����
 * @author PMY
 *
 */
public class RegistFrame extends JFrame {

	private int WIDTH =430;
	private int HEIGHT =300;
	private JPanel contentPane;
	private JTextField usernameTf;
	private JPasswordField passwordTf1;
	private JPasswordField passwordTf2;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					RegistFrame frame = new RegistFrame();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public RegistFrame() {
		init();
	
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel label = new JLabel("\u7528\u6237\u540D\uFF1A");
		label.setFont(new Font("����", Font.BOLD, 24));
		label.setBounds(56, 13, 100, 41);
		contentPane.add(label);
		
		usernameTf = new JTextField();
		usernameTf.setColumns(10);
		usernameTf.setBounds(170, 21, 209, 33);
		contentPane.add(usernameTf);
		
		JLabel label_1 = new JLabel("\u5BC6\u7801\uFF1A");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setFont(new Font("����", Font.BOLD, 24));
		label_1.setBounds(56, 67, 100, 41);
		contentPane.add(label_1);
		
		JLabel label_1_1 = new JLabel("\u786E\u8BA4\u5BC6\u7801\uFF1A");
		label_1_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1_1.setFont(new Font("����", Font.BOLD, 24));
		label_1_1.setBounds(14, 121, 142, 41);
		contentPane.add(label_1_1);
		
		JButton RegistBt = new JButton("\u6CE8\u518C");
		RegistBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = usernameTf.getText();
				String password = new String(passwordTf1.getPassword());
				String password2 = new String(passwordTf2.getPassword());
//				String regex1 = "\\D\\w{2,}";
//				String regex2 = "\\w{2,}";
				String regex1 = "\\D[A-Za-z0-9_+]{2,}";
				String regex2 = "[A-Za-z0-9_+]{4,}";
				if(username.length()==0||password.length()==0||password2.length()==0) {				//�ж��Ƿ�������
					JOptionPane.showMessageDialog(null, "�������û�������","ע��ʧ��",JOptionPane.WARNING_MESSAGE);
				}else if(password.equals(password2)) {					//�ж����������Ƿ�ȷ��
					
					if(username.matches(regex1)&&password.matches(regex2)) {		//�ж��û��������Ƿ�ϸ�
						
						UserDaoImpl udi = new UserDaoImpl(DB.getConnection());
						try {
							if(udi.Regist(username, password)) {		//ע��ɹ����ص�¼����
								JOptionPane.showMessageDialog(null, "ע��ɹ�","ע��ɹ�",JOptionPane.PLAIN_MESSAGE);
								dispose();
								new LogFrame();
							}else {
								JOptionPane.showMessageDialog(null, "ע��ʧ�ܣ��Ѵ��ڴ��û�!","ע��ʧ��",JOptionPane.WARNING_MESSAGE);
								usernameTf.setText(null);
								passwordTf1.setText(null);
								passwordTf2.setText(null);
							}
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(null, e.toString());
						}
						
					}else {
						JOptionPane.showMessageDialog(null, "�û��������ʽ����\n�û�����ʽ�������ֿ�ͷӢ�������»���\n�����ʽ��Ӣ�������»���"
								, "��½ʧ��", JOptionPane.WARNING_MESSAGE);
						usernameTf.setText(null);
						passwordTf1.setText(null);
						passwordTf2.setText(null);
					}
				}else {
					JOptionPane.showMessageDialog(null, "������������벻һ��","ע��ʧ��",JOptionPane.WARNING_MESSAGE);
					passwordTf1.setText(null);
					passwordTf2.setText(null);
				}
				
			}
		});
		RegistBt.setFont(new Font("��������", Font.BOLD, 23));
		RegistBt.setBounds(69, 186, 111, 41);
		contentPane.add(RegistBt);
		
		JButton canerBt = new JButton("\u53D6\u6D88");
		canerBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new LogFrame();
				dispose();				
			}
		});
		canerBt.setFont(new Font("��������", Font.BOLD, 23));
		canerBt.setBounds(245, 186, 111, 41);
		contentPane.add(canerBt);
		
		passwordTf1 = new JPasswordField();
		passwordTf1.setBounds(170, 75, 209, 33);
		contentPane.add(passwordTf1);
		
		passwordTf2 = new JPasswordField();
		passwordTf2.setBounds(170, 129, 209, 33);
		contentPane.add(passwordTf2);
	}
	
	private void init() {
		setTitle("\u6CE8\u518C");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
// ����ʾ���ϱ�Եx���أ�����ʾ�����Եy����
		setLocation(screenSize.width / 2 - WIDTH / 2, screenSize.height / 2 - HEIGHT / 2);
		setSize(WIDTH, HEIGHT);
		setVisible(true);
		setResizable(false);
	}

}
