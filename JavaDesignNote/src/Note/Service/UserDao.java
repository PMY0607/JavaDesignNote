package Note.Service;

import Note.Info.User;
/**
 * 用户登录/注册接口
 * @author PMY
 *
 */
public interface UserDao {
	/**
	 * 用户登录接口<br>
	 * @param username 用户名	
	 * @param password 密码
	 * @return	用户是否登录成功
	 * @throws Exception 数据库操作异常	
	 */
	public User Login(String username,String password) throws Exception;
	/**
	 * 用户注册接口<br>
	 * @param username	用户名
	 * @param password	密码
	 * @return	返回用户注册是否成功
	 * @throws Exception 数据库操作异常
	 */
	public boolean Regist(String username,String password) throws Exception;
}
