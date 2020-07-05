package Note.Service.Impl;

import javax.swing.JTextArea;

import Note.Service.FindRepl;
/**
 * ����/�滻ʵ����
 * @author PMY
 *
 */
public class ReplImpl implements FindRepl{
	private static String lasts = null;
	private static int index;
	
	@Override
	public  int find(String text, String str) {
		// ���µ��ַ����ʹ�ͷ��ʼ��ѯ
		if (lasts == null || !lasts.equals(str)) {
			index = 0;
		}
		int flag = text.indexOf(str, index);

		// �������ַ����򱣴���ַ�����¼
		if (flag != -1) {
			lasts = new String(str);
			index = text.indexOf(str.charAt(0), flag + 1); // ��һ�β�ѯ��ʼλ��
		} else {
			if (lasts == null || !lasts.equals(text)) { // һ��ʼ�Ͳ鲻�����ַ���
				return -2;
			} else {
				return find(text, str); // �鵽ĩβ�������¿�ʼ��
			}
		}
		return flag;
	}

	@Override
	public boolean replace(JTextArea are, String finds, String rel) {
		String text = are.getText();
		int start, end;
		start = find(text, finds);
		boolean flag;
		// �ܹ��滻
		if (start >= 0)
			flag = true;
		else
			flag = false;
		// �����滻
		if (flag) {
			end = start + finds.length();
			are.replaceRange(rel, start, end);
			are.setSelectionStart(start);
			are.setSelectionEnd(end);
		}
		return flag;
	}

	@Override
	public int replaceAll(JTextArea are, String finds, String rel) {
	
		String text = are.getText();
		int start, end;		
		boolean flag = (start = find(text, finds))>=0;
		
		// �ܹ��滻
		int count =0;
		if (flag) {
			int begin = -1;
			while (begin !=start) {
				if(begin ==-1) {
					begin = start;
				}
				//ѡ���滻����ַ�
				end = start + finds.length();			//Ҫ�滻���ַ�ĩβ
				are.replaceRange(rel, start, end);		//�滻�ַ�
				are.select(start,start+rel.length());	//ѡ���滻�õ��ַ�
				start = find(text, finds);
				count ++;
			}
		}
		return count;
	}
}
