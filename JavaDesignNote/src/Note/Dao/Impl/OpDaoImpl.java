package Note.Dao.Impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JFrame;

import Note.Dao.OpDao;
import Note.Frame.NoteFrame;
import Note.Info.History;
import Note.Info.User;

public class OpDaoImpl implements OpDao {
	private Connection con = null;

	public OpDaoImpl(Connection conn) {
		this.con = conn;
	}

	/**
	 * ���ݿ����user��Ϣ����<br>
	 * 
	 * @param user Ҫ�������û���Ϣ
	 * @return ���ش����Ƿ�ɹ�
	 */
	public boolean doCreateUser(User user) throws Exception {
		if (doFindUser(user.getUsername()) == null) {
			String sql = "insert into user(username,password,lastFileName) values(?,?,?);";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, user.getUsername());
			pst.setString(2, user.getPasswold());
			pst.setString(3, user.getLastFileName());
			int n = pst.executeUpdate();
			pst.close();
			if (n > 0) {
				return true;
			} else
				return false;
		}
		return false;
	}

	/**
	 * �����ݿ���ɾ���û���Ϊusername������<br>
	 * 
	 * @param username ��ɾ�����ݵ��û���
	 * @return �����Ƿ�ɾ���ɹ�
	 */
	public boolean doRemoveUser(String username) throws Exception {
		if (doFindUser(username) != null) {
			String sql = "delete from user where username =?;";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, username);
			int n = pst.executeUpdate();
			pst.close();
			if (n > 0) {
				return true;
			} else
				return false;
		}
		return false;
	}

	/**
	 * ���¹���user����Ϣ����<br>
	 * 
	 * @param user Ҫ���µ���������
	 * @return �������ݸ����Ƿ���³ɹ�
	 */
	public boolean doUpdateUser(User user) throws Exception {
		if (doFindUser(user.getUsername()) != null) {
			String sql = "update user set username = ?,password = ?,lastFileName = ? where username=? ";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, user.getUsername());
			pst.setString(2, user.getPasswold());
			pst.setString(3, user.getLastFileName());
			pst.setString(4, user.getUsername());
			int n = pst.executeUpdate();
			pst.close();
			if (n > 0) {
				return true;
			} else
				return false;
		}
		return false;
	}

	/**
	 * ���ҹ���username����Ϣ<br>
	 * 
	 * @param username Ҫ���ҵ��û���
	 * @return ���ز��ҵ��û�������Ϣ
	 */
	public User doFindUser(String username) throws Exception {
		String sql = "select *from user where BINARY username = ?;";
		PreparedStatement pst = con.prepareStatement(sql);
		pst.setString(1, username);
		ResultSet rs = pst.executeQuery();
		if (rs.next()) {
			return new User(rs.getString(1), rs.getString(2), rs.getString(3));
		}
		rs.close();
		pst.close();
		return null;
	}

	/**
	 * �����ʷ�ļ�����<br>
	 * @param username ������
	 * @param fileName �������ļ�����·��
	 * @return �����Ƿ���ӳɹ�
	 */
	@Override
	public boolean setUserFile(String username, String fileName) throws Exception {
		if (doFindUser(username) != null) {
			//�û��������ʷ��¼
			String sql = "update user set lastFileName = ?  where username= ?;";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, fileName);
			pst.setString(2, username);
			pst.executeUpdate();
			pst.close();
	
			//������ʷ��¼��
			return setHistory(username, fileName,new Date(System.currentTimeMillis()) );
			
		}

		return false;
	}

	/**
	 * �����ļ�������ʷ��¼<br>
	 * @return �����ļ���ʷ����History����
	 */
	@Override
	public ArrayList<History> getHistoryList() throws Exception {
		String sql = "select *from history  ORDER by lastDate desc;";
		PreparedStatement pst = con.prepareStatement(sql);
		ResultSet rs = pst.executeQuery();
		ArrayList <History>list = new ArrayList<History>();
		while(rs.next()) {
			list.add(new History(rs.getString(1), rs.getString(2), rs.getDate(3)));
		}

		rs.close();
		pst.close();
		return list;
	}

	/**
	 * ����ļ�������ʷ<br>
	 * @param username �ļ�������
	 * @param fileName ���������ļ�
	 * @param d ����������
	 * @return	�Ƿ���ӳɹ�
	 * @throws Exception	���ݿ�����쳣
	 */
	public boolean setHistory(String username,String fileName,Date d)throws Exception {
		String sql = "select *from history where BINARY lastFileName =? ;";		//�ж��Ƿ��Ѿ����ڸ��û�������
		PreparedStatement pst =con.prepareStatement(sql);
		pst.setString(1, fileName);
		ResultSet rs = pst.executeQuery();
		if(rs.next()) {		//�Ѵ�����ʷ��¼
			String sql1 = "update history set username=?,lastDate = ? where lastFileName = ?;";
			pst = con.prepareStatement(sql1);
			pst.setString(1, username);
			pst.setDate(2, new Date(System.currentTimeMillis()));
			pst.setString(3, fileName);
			pst.executeUpdate();
			pst.close();
			return true;
		}else {				//��������ʷ��¼
			String sql1 ="insert into history (username,lastFileName,lastDate) values(?,?,?);";
			pst = con.prepareStatement(sql1);
			pst.setString(1, username);
			pst.setString(2, fileName);
			pst.setDate(3, d);
			boolean flag = pst.executeUpdate()>0;
			pst.close();
			return flag;
		}
	}

	/**
	 * ɾ����ʷ��¼<br>
	 * @param fileName Ҫɾ������ʷ��¼�ļ��� <br>
	 * @return	ɾ���Ƿ�ɹ�
	 * @throws Exception ���ݿ�����쳣
	 */
	public boolean delHistory(String fileName)  throws Exception{
		String sql = "select *from history where BINARY lastFileName =? ;";		//�ж��Ƿ��Ѿ����ڸ��û�������
		PreparedStatement pst =con.prepareStatement(sql);
		pst.setString(1, fileName);
		System.out.println(fileName);
		ResultSet rs = pst.executeQuery();
		if(rs.next()) {
			System.out.println(3);
			String delsql = "delete from history where lastFileName = ?;";
			pst = con.prepareStatement(delsql);
			pst.setString(1, fileName);
			int result =pst.executeUpdate();
			String username = rs.getString(1);
			pst.close();
			if(result>0){
				String sql1 = "update user set lastfileName = NULL  where username = ?;";
				pst = con.prepareStatement(sql1);
				pst.setString(1, username);
				pst.executeUpdate();
				return true;
			}
			else {
				System.out.println(4);
				return false;
			}
		}else {
			System.out.println(5);
			return false;
		}
		
	}
	
}








