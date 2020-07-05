package Test;

import static org.junit.jupiter.api.Assertions.*;

import javax.swing.JTextArea;

import org.junit.jupiter.api.Test;

import Note.Factory.ReplImplFactory;
import Note.Factory.ServecFactory;
import Note.dbc.DB;
import junit.framework.TestCase;

class ReplImplTest {

	@Test
	void testFind() {
		TestCase.assertTrue(ReplImplFactory.getReplImpl().find("asdfsafsafsdfasf", "as")!=-2);
	}

	@Test
	void testReplace() {
		JTextArea text = new JTextArea();
		text.append("asfasdfsafsafsadfasdf");
		TestCase.assertTrue(ReplImplFactory.getReplImpl().replace(text, "as", "a"));
	}

	@Test
	void testReplaceAll() {
		JTextArea text = new JTextArea();
		text.append("asfasdfsafsafsadfasdf");
		TestCase.assertTrue(ReplImplFactory.getReplImpl().replaceAll(text, "as", "a")>0);
	}

}
