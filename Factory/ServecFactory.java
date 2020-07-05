package Factory;

import java.sql.Connection;

import ServiceImpl.UserDaoImpl;
/**
 * 服务层工程类
 * @author PMY
 *
 */
public class ServecFactory {
	/**
	 * 获得用户操作类对象
	 * @param con	数据库连接
	 * @return		数据库操作类对象
	 */
	public static UserDaoImpl getUserDaoImpl(Connection con) {
		return new UserDaoImpl(con);
	}
}
