package Test;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;

import org.junit.jupiter.api.Test;

import Note.Dao.Impl.OpDaoImpl;
import Note.Factory.DaoFactory;
import Note.Info.User;
import Note.dbc.DB;
import junit.framework.TestCase;

class OpDaoImplTest {
	OpDaoImpl odi = new OpDaoImpl(DB.getConnection());

	@Test
	void testDoCreateUser() {
		User user = new User();
		user.setUsername("Test1");
		user.setPasswold("PMY2018401312");
		user.setLastFileName("java.doc");
		try {
			TestCase.assertTrue(DaoFactory.getOpDaoImpl(DB.getConnection()).doCreateUser(user));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void testDoRemoveUser() {
		try {
			TestCase.assertTrue(DaoFactory.getOpDaoImpl(DB.getConnection()).doRemoveUser("Test1"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void testDoUpdateUser() {
		User user = new User();
		user.setUsername("Test1");
		user.setPasswold("PMY2018401312");
		user.setLastFileName("JavaDesign.doc");
		try {
			TestCase.assertTrue(DaoFactory.getOpDaoImpl(DB.getConnection()).doUpdateUser(user));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void testDoFindUser() {
		try {
			TestCase.assertNotNull(DaoFactory.getOpDaoImpl(DB.getConnection()).doFindUser("Test1"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void testSetUserFile() {
		try {
			TestCase.assertTrue(DaoFactory.getOpDaoImpl(DB.getConnection()).setUserFile("Test1", "abc.txt"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void testGetHistoryList() {
		try {
			TestCase.assertNotNull(DaoFactory.getOpDaoImpl(DB.getConnection()).getHistoryList());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void testSetHistory() {
		try {
			TestCase.assertTrue(DaoFactory.getOpDaoImpl(DB.getConnection()).setHistory("Test1", "history.txt", new Date(System.currentTimeMillis())));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	void testDelHistory() {
		try {
			TestCase.assertTrue(DaoFactory.getOpDaoImpl(DB.getConnection()).delHistory("history.txt"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
