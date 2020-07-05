package Test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;

import org.junit.jupiter.api.Test;

import Note.Dao.Impl.OpDaoImpl;
import Note.dbc.DB;
/**
 * ¿˙ ∑ºÕ¬º≤‚ ‘
 * @author PMY
 *
 */
class HistoryTest {
	OpDaoImpl odi = new OpDaoImpl(DB.getConnection());
	@Test
	void test() throws Exception {
		long start =	System.currentTimeMillis();
		for(int i = 1;i<10001;i++) {
			String username  = "User";
			if(i<10) {
				username = username+"0000";
			}else if(i<100) {
				username = username+"000";
			}else if (i<1000) {
				username = username+"00";
			}else if(i<10000) {
				username = username+"0";
			}
			username = username +i;
			String path = System.currentTimeMillis()+".txt";
			Date d = new Date(System.currentTimeMillis());
			odi.setHistory(username, path, d);
		}
		System.out.println("history opeartor time:"+(System.currentTimeMillis()-start));
	}

}
