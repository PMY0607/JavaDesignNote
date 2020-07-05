package Note.Service;

import Note.Info.User;
/**
 * �û���¼/ע��ӿ�
 * @author PMY
 *
 */
public interface UserDao {
	/**
	 * �û���¼�ӿ�<br>
	 * @param username �û���	
	 * @param password ����
	 * @return	�û��Ƿ��¼�ɹ�
	 * @throws Exception ���ݿ�����쳣	
	 */
	public User Login(String username,String password) throws Exception;
	/**
	 * �û�ע��ӿ�<br>
	 * @param username	�û���
	 * @param password	����
	 * @return	�����û�ע���Ƿ�ɹ�
	 * @throws Exception ���ݿ�����쳣
	 */
	public boolean Regist(String username,String password) throws Exception;
}
