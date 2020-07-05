package Factory;

import java.sql.Connection;

import DaoImpl.OpDaoImpl;
/**
 * 数据层工厂类
 * @author PMY
 *
 */
public class DaoFactory {
	/**
	 * 获得数据操作类对象
	 * @param con	数据库连接
	 * @return	数据层实现类对象
	 */
	public static OpDaoImpl getOpDaoImpl(Connection con) {
		return new OpDaoImpl(con);
	}
}
