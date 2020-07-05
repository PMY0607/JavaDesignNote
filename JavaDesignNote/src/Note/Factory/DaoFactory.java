package Note.Factory;

import java.sql.Connection;

import Note.Dao.Impl.OpDaoImpl;
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
