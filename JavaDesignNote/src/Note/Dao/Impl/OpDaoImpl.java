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
	 * 数据库添加user信息数据<br>
	 * 
	 * @param user 要创建的用户信息
	 * @return 返回创建是否成功
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
	 * 从数据库内删除用户名为username的数据<br>
	 * 
	 * @param username 被删除数据的用户名
	 * @return 返回是否删除成功
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
	 * 更新关于user的信息内容<br>
	 * 
	 * @param user 要更新的数据内容
	 * @return 返回数据更新是否更新成功
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
	 * 查找关于username的信息<br>
	 * 
	 * @param username 要查找的用户名
	 * @return 返回查找的用户基本信息
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
	 * 添加历史文件操作<br>
	 * @param username 操作者
	 * @param fileName 被操作文件完整路径
	 * @return 返回是否添加成功
	 */
	@Override
	public boolean setUserFile(String username, String fileName) throws Exception {
		if (doFindUser(username) != null) {
			//用户表插入历史纪录
			String sql = "update user set lastFileName = ?  where username= ?;";
			PreparedStatement pst = con.prepareStatement(sql);
			pst.setString(1, fileName);
			pst.setString(2, username);
			pst.executeUpdate();
			pst.close();
	
			//更新历史纪录表
			return setHistory(username, fileName,new Date(System.currentTimeMillis()) );
			
		}

		return false;
	}

	/**
	 * 返回文件操作历史纪录<br>
	 * @return 返回文件历史操作History集合
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
	 * 添加文件操作历史<br>
	 * @param username 文件操作者
	 * @param fileName 被操作的文件
	 * @param d 最后操作日期
	 * @return	是否添加成功
	 * @throws Exception	数据库操作异常
	 */
	public boolean setHistory(String username,String fileName,Date d)throws Exception {
		String sql = "select *from history where BINARY lastFileName =? ;";		//判断是否已经存在该用户的数据
		PreparedStatement pst =con.prepareStatement(sql);
		pst.setString(1, fileName);
		ResultSet rs = pst.executeQuery();
		if(rs.next()) {		//已存在历史纪录
			String sql1 = "update history set username=?,lastDate = ? where lastFileName = ?;";
			pst = con.prepareStatement(sql1);
			pst.setString(1, username);
			pst.setDate(2, new Date(System.currentTimeMillis()));
			pst.setString(3, fileName);
			pst.executeUpdate();
			pst.close();
			return true;
		}else {				//不存在历史纪录
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
	 * 删除历史纪录<br>
	 * @param fileName 要删除的历史纪录文件名 <br>
	 * @return	删除是否成功
	 * @throws Exception 数据库操作异常
	 */
	public boolean delHistory(String fileName)  throws Exception{
		String sql = "select *from history where BINARY lastFileName =? ;";		//判断是否已经存在该用户的数据
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








