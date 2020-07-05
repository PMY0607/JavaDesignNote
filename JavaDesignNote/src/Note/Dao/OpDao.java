package Note.Dao;

import java.util.List;

import Note.Info.History;
import Note.Info.User;
/**
 * ���������ӿ�
 * @author PMY
 *
 */
public interface OpDao {
	
	/**
	 * �����û���Ϣ<br>
	 * @param user	Ҫ���ӵ��û���Ϣ
	 * @return	���������Ƿ�ɹ�
	 * @throws Exception ���ݿ�����쳣
	 */
	public boolean doCreateUser(User user)throws Exception;
	
	/**
	 * ɾ���û���Ϣ<br>
	 * @param username Ҫɾ�����û���
	 * @return �����Ƿ�ɾ���ɹ�
	 * @throws Exception ���ݿ�����쳣
	 */
	public boolean doRemoveUser(String username)throws Exception;
	
	/**
	 * �����û���Ϣ<br>
	 * @param user ���µ��û���Ϣ
	 * @return �����Ƿ���³ɹ�
	 * @throws Exception ���ݿ�����쳣
	 */
	public boolean doUpdateUser(User user) throws Exception;
	
	/**
	 * �����ṩ���û��������û���Ϣ<br>	 
	 * @param username	Ҫ���ҵ��û���
	 * @return	���ҳɹ��򷵻ض�Ӧ�û������򷵻ؿ�
	 * @throws Exception ���ݿ�����쳣
	 */
	public User doFindUser(String username) throws Exception;
	
	/**
	 * �����û����������ļ�·��<br>
	 * @param username	�û���
	 * @param fileName	�ļ�·��
	 * @return	�����Ƿ����óɹ�
	 * @throws Exception ���ݿ�����쳣
	 */
	public boolean setUserFile(String username,String fileName) throws Exception;
	
	/**
	 * ����ļ�������ʷ��¼
	 * @return	������ʷ��¼���󼯺�
	 * @throws Exception ���ݿ�����쳣
	 */
	public List<History> getHistoryList() throws Exception;
	
}
