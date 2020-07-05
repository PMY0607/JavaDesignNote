package Test;

import java.util.ArrayList;
import java.util.Vector;

import Note.Dao.Impl.OpDaoImpl;
import Note.Frame.LogFrame;
import Note.Info.History;
import Note.Service.Impl.HistoryOrderImpl;
import Note.dbc.DB;
/**
 * µÇÂ¼²âÊÔ
 * @author PMY
 *
 */
public class Test {
	public static void main(String[] args) throws Exception {
		//new LogFrame();
		OpDaoImpl odi = new OpDaoImpl(DB.getConnection());
		HistoryOrderImpl his = new HistoryOrderImpl();
		
	Vector<History> vo = new Vector<History>(odi.getHistoryList());
		for(History h :his.orderBy(vo, 0)) {
			System.out.println(h);
		}
		System.out.println("******************************");
		for(History h :his.orderBy(vo, 0)) {
			System.out.println(h);
		}
		
	}
}
