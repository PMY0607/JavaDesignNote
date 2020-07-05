package Note.dbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class DB {
	private static final String DBDIRVER = "com.mysql.cj.jdbc.Driver";
	private static final String DBURL = "jdbc:mysql://localhost:3306/note?useUnicode=true"
			+ "&characterEncoding=UTF-8&serverTimezone=UTC";
	private static final String DBUSER = "root";
	private static final String DBPASS = "pmy0607+";
	private static Connection con = null;

	/**
	 * 构造私有化
	 */
	private DB() {}

	/**
	 * 获得当前数据库的连接<br>
	 * @return 当前的数据库连接
	 */
	public static Connection getConnection () {
		if (con == null) {
			try {
			Class.forName(DBDIRVER);
			con = DriverManager.getConnection(DBURL, DBUSER, DBPASS);
			}catch (Exception e) {
				JOptionPane.showMessageDialog(null, e.toString());
			}
		}
		return con;
		
	}
	
	/**
	 * 关闭当前数据库连接
	 */
	public static void closeConnection() {
		if(con!=null) {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(null, e.toString());
			}
		}
	}

}
