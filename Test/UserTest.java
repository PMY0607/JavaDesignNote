package Test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import DaoImpl.OpDaoImpl;
import ServiceImpl.UserDaoImpl;
import dbc.DB;

class UserTest {
	OpDaoImpl odi = new OpDaoImpl(DB.getConnection());

	@Test
	void test() {

		for (int i = 1; i <= 10000; i++) {
			String username = "U";
			String pw = "P";
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
			System.out.println(username+"--"+pw);
		}
	}

}
