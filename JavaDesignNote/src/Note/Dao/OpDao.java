package Note.Dao;

import java.util.List;

import Note.Info.History;
import Note.Info.User;
/**
 * 基本操作接口
 * @author PMY
 *
 */
public interface OpDao {
	
	/**
	 * 增加用户信息<br>
	 * @param user	要增加的用户信息
	 * @return	返回增加是否成功
	 * @throws Exception 数据库操作异常
	 */
	public boolean doCreateUser(User user)throws Exception;
	
	/**
	 * 删除用户信息<br>
	 * @param username 要删除的用户名
	 * @return 返回是否删除成功
	 * @throws Exception 数据库操作异常
	 */
	public boolean doRemoveUser(String username)throws Exception;
	
	/**
	 * 更新用户信息<br>
	 * @param user 更新的用户信息
	 * @return 返回是否更新成功
	 * @throws Exception 数据库操作异常
	 */
	public boolean doUpdateUser(User user) throws Exception;
	
	/**
	 * 根据提供的用户名查找用户信息<br>	 
	 * @param username	要查找的用户名
	 * @return	查找成功则返回对应用户，否则返回空
	 * @throws Exception 数据库操作异常
	 */
	public User doFindUser(String username) throws Exception;
	
	/**
	 * 设置用户最后操作的文件路径<br>
	 * @param username	用户名
	 * @param fileName	文件路径
	 * @return	返回是否设置成功
	 * @throws Exception 数据库操作异常
	 */
	public boolean setUserFile(String username,String fileName) throws Exception;
	
	/**
	 * 获得文件操作历史纪录
	 * @return	返回历史纪录对象集合
	 * @throws Exception 数据库操作异常
	 */
	public List<History> getHistoryList() throws Exception;
	
}
