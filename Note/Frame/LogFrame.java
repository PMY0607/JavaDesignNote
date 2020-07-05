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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import Note.Info.User;
import Note.Service.Impl.UserDaoImpl;
import Note.dbc.DB;

import javax.swing.JPasswordField;

/**
 * 登录界面
 * @author PMY
 *
 */
public class LogFrame extends JFrame {

	private JPanel contentPane;
	private JTextField usernameTf;
	private JButton LoginBt;
	private JButton RegistBt;
	private int WIDTH =430,HEIGHT =300;
	private JPasswordField passwordTf;

	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LogFrame frame = new LogFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LogFrame() {
		init();
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);

		JLabel label = new JLabel("\u7528\u6237\u540D\uFF1A");
		label.setFont(new Font("楷体", Font.BOLD, 24));
		label.setBounds(35, 45, 100, 41);
		contentPane.add(label);

		JLabel label_1 = new JLabel("\u5BC6\u7801\uFF1A");
		label_1.setHorizontalAlignment(SwingConstants.RIGHT);
		label_1.setFont(new Font("楷体", Font.BOLD, 24));
		label_1.setBounds(35, 99, 100, 41);
		contentPane.add(label_1);

		usernameTf = new JTextField();
		usernameTf.setBounds(149, 53, 209, 33);
		contentPane.add(usernameTf);
		usernameTf.setColumns(10);
		
		passwordTf = new JPasswordField();
		passwordTf.setBounds(149, 107, 209, 33);
		contentPane.add(passwordTf);
		// 登录按钮
		LoginBt = new JButton("\u767B\u5F55");
		LoginBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String username = usernameTf.getText();
				String password = new String(passwordTf.getPassword());
				String regex1 = "\\D\\w{2,}";
				String regex2 = "\\w{2,}";
				
				if (username.length() == 0 || password.length() == 0) {					//是否有填入
					JOptionPane.showMessageDialog(null, "请输入用户名密码", "登录失败", JOptionPane.PLAIN_MESSAGE);
				} else if (username.matches(regex1) && password.matches(regex2)) {			//用户名密码格式正确
					UserDaoImpl udi = new UserDaoImpl(DB.getConnection());
					try {
						User user = udi.Login(username, password);
						if (user != null) {	//登录成功
							JOptionPane.showMessageDialog(null, "登录成功!","登录成功",JOptionPane.PLAIN_MESSAGE);
							dispose();
							new NoteFrame(user);
							
						}else {				//登录失败
							JOptionPane.showMessageDialog(null, "用户名密码错误", "登陆失败", JOptionPane.WARNING_MESSAGE);
							passwordTf.setText(null);
						}
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, e1.toString());
					}
				} else {
					JOptionPane.showMessageDialog(null, "用户名密码格式错误\n用户名格式：非数字开头英文数字下划线\n密码格式：英文数字下划线"
							, "登陆失败", JOptionPane.WARNING_MESSAGE);
					usernameTf.setText(null);
					passwordTf.setText(null);
				}
			}
		});

		LoginBt.setFont(new Font("方正舒体", Font.BOLD, 23));
		LoginBt.setBounds(66, 167, 111, 41);
		contentPane.add(LoginBt);

		RegistBt = new JButton("\u6CE8\u518C");
		RegistBt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				new RegistFrame();
			}
		});
		RegistBt.setFont(new Font("方正舒体", Font.BOLD, 23));
		RegistBt.setBounds(245, 167, 111, 41);
		contentPane.add(RegistBt);
		
		
	}

	private void init() {
		setTitle("\u767B\u5F55");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
// 离显示屏上边缘x像素，里显示屏左边缘y像素
		setLocation(screenSize.width / 2 - WIDTH / 2, screenSize.height / 2 - HEIGHT / 2);
		setSize(WIDTH, HEIGHT);
		setVisible(true);
		setResizable(false);
		
	}
}
