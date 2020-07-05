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
	 * ��¼���ж��û��������Ƿ���ȷ<br>
	 * @param username	�û���
	 * @param password	����
	 * @return ��¼�ɹ����ش��û���Ϣ�����򷵻�null
	 */
	public User Login(String username, String password) throws Exception {
		User user = op.doFindUser(username);
		if(user!=null && password.equals(user.getPasswold())) {
			return new User(user.getUsername(),user.getPasswold(),user.getLastFileName());
		}
		return null;
	}

	/**
	  * �û�ע��<br>
	 * @param username	�û���
	 * @param password	����
	 * @return ����ע���Ƿ�ɹ�
	 */
	public boolean Regist(String username, String password) throws Exception {
		if(op.doFindUser(username)==null) {
			op.doCreateUser(new User(username,password));
			return true;
		}
		return false;
	}
	
}