package Note.Service.Impl;

import javax.swing.JTextArea;

import Note.Service.FindRepl;
/**
 * 查找/替换实现类
 * @author PMY
 *
 */
public class ReplImpl implements FindRepl{
	private static String lasts = null;
	private static int index;
	
	@Override
	public  int find(String text, String str) {
		// 来新的字符串就从头开始查询
		if (lasts == null || !lasts.equals(str)) {
			index = 0;
		}
		int flag = text.indexOf(str, index);

		// 若存在字符串则保存此字符串纪录
		if (flag != -1) {
			lasts = new String(str);
			index = text.indexOf(str.charAt(0), flag + 1); // 下一次查询开始位置
		} else {
			if (lasts == null || !lasts.equals(text)) { // 一开始就查不到此字符串
				return -2;
			} else {
				return find(text, str); // 查到末尾，则重新开始查
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
		// 能够替换
		if (start >= 0)
			flag = true;
		else
			flag = false;
		// 单个替换
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
		
		// 能够替换
		int count =0;
		if (flag) {
			int begin = -1;
			while (begin !=start) {
				if(begin ==-1) {
					begin = start;
				}
				//选中替换后的字符
				end = start + finds.length();			//要替换的字符末尾
				are.replaceRange(rel, start, end);		//替换字符
				are.select(start,start+rel.length());	//选中替换好的字符
				start = find(text, finds);
				count ++;
			}
		}
		return count;
	}
}
