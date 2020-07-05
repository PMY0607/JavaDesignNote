package Note.Service.Impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.Vector;

import Note.Info.History;
import Note.Service.historyOrder;
/**
 * ��ʷ��¼����ʵ����
 * @author PMY
 *
 */
public class HistoryOrderImpl implements historyOrder {
	private static Map<Integer, Integer> map;
	static {
		map = new TreeMap<Integer, Integer>();
		map.put(0, 0);
		map.put(1, 0);
		map.put(2, 0);
		System.out.println("a");
	}


	@Override
	public <T> T orderBy(T t, int by) {
		Vector<History> list = (Vector<History>) t;
		//��list����
		Collections.sort(list, new Comparator<History>() {
			@Override
			public int compare(History h1, History h2) {
				//��������л�
				int flag = 1;
				//ÿ���ֶ��������Ϊż�����������򣬷���Ϊ��������
				if (map.get(by) % 2 != 0) {
					flag = -1;
				}
				if (by == 0) {
					return h1.getLastFileName().compareTo(h2.getLastFileName()) * flag;
				} else if (by == 1) {
					return h1.getUserName().compareTo(h2.getUserName()) * flag;
				} else {
					return h1.getLastDate().compareTo(h2.getLastDate()) * flag;
				}
			}
		});
		//System.out.println(map);
		//�ۼ��ֶ��������
		map.put(by, map.get(by) + 1);
	
		return (T) list;
	}

}
