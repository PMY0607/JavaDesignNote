package Factory;

import java.sql.Connection;

import DaoImpl.OpDaoImpl;
/**
 * ���ݲ㹤����
 * @author PMY
 *
 */
public class DaoFactory {
	/**
	 * ������ݲ��������
	 * @param con	���ݿ�����
	 * @return	���ݲ�ʵ�������
	 */
	public static OpDaoImpl getOpDaoImpl(Connection con) {
		return new OpDaoImpl(con);
	}
}
