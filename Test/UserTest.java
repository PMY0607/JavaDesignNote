package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import Note.Dao.Impl.OpDaoImpl;
import Note.Info.User;
import Note.Service.Impl.UserDaoImpl;
import Note.dbc.DB;
/**
 * 用户数据测试
 * @author PMY
 *
 */
class UserTest {
	OpDaoImpl odi = new OpDaoImpl(DB.getConnection());

	@Test
	void test() throws Exception {
		long start = System.currentTimeMillis();
		for (int i = 1; i <= 10000; i++) {
			String username = "User";
			String pw = "PW";
			if (i < 10) {
				username = username + "0000";
			} else if (i < 100) {
				username = username + "000";
			} else if (i < 1000) {
				username = username + "00";
			} else if (i < 10000) {
				username = username + "0";
			}
			username = username + i;
			pw = pw+username.substring(1);
			odi.doCreateUser(new User(username, pw));
		}
		System.out.println("User operator time:"+(System.currentTimeMillis()-start));		
	}

}
