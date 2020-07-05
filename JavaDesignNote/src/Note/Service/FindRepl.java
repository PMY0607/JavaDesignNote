package Note.Service;

import javax.swing.JTextArea;
/**
 * 查找/替换接口
 * @author PMY
 *
 */
public interface FindRepl {
	/**
	 * 从text文本域中查找字串str<br>
	 * @param text	源文本域
	 * @param str	查找字串
	 * @return		找到则返回字串位置，没找到则返回-2
	 */
	public  int find(String text, String str);
	/**
	 * 在文本域are中将单个finds替换成rel
	 * @param are		原文本域
	 * @param finds		被替换字符
	 * @param rel		替换字符
	 * @return	是否替换成功
	 */
	public boolean replace(JTextArea are, String finds, String rel);
	/**
	 * 从文本域are中将所有finds字符替换成rel
	 * @param are		原文本域
	 * @param finds		被替换字符
	 * @param rel		替换字符
	 * @return			替换字符数
	 */
	public int replaceAll(JTextArea are, String finds, String rel);
}
