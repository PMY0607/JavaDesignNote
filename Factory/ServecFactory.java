package Factory;

import java.sql.Connection;

import ServiceImpl.UserDaoImpl;
/**
 * ����㹤����
 * @author PMY
 *
 */
public class ServecFactory {
	/**
	 * ����û����������
	 * @param con	���ݿ�����
	 * @return		���ݿ���������
	 */
	public static UserDaoImpl getUserDaoImpl(Connection con) {
		return new UserDaoImpl(con);
	}
}
