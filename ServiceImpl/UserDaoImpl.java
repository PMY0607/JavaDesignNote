package ServiceImpl;

import java.sql.Connection;

import DaoImpl.OpDaoImpl;
import Service.UserDao;
import UserInfo.User;

public class UserDaoImpl implements UserDao{
	private OpDaoImpl op = null;
	
	public UserDaoImpl(Connection con) {
		super();
		this.op = new OpDaoImpl(con);
	}

	/**
	 * 登录并判断用户名密码是否正确<br>
	 * @param username	用户名
	 * @param password	密码
	 * @return 登录成功返回此用户信息，否则返回null
	 */
	public User Login(String username, String password) throws Exception {
		User user = op.doFindUser(username);
		if(user!=null && password.equals(user.getPasswold())) {
			return new User(user.getUsername(),user.getPasswold(),user.getLastFileName());
		}
		return null;
	}

	/**
	  * 用户注册<br>
	 * @param username	用户名
	 * @param password	密码
	 * @return 返回注册是否成功
	 */
	public boolean Regist(String username, String password) throws Exception {
		if(op.doFindUser(username)==null) {
			op.doCreateUser(new User(username,password));
			return true;
		}
		return false;
	}
	
}