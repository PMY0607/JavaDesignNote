package Service;

import javax.swing.JTextArea;

public interface FindRepl {
	/**
	 * ��text�ı����в����ִ�str<br>
	 * @param text	Դ�ı���
	 * @param str	�����ִ�
	 * @return		�ҵ��򷵻�true��û�ҵ��򷵻�-2
	 */
	public  int find(String text, String str);
	/**
	 * ���ı���are�н�����finds�滻��rel
	 * @param are		ԭ�ı���
	 * @param finds		���滻�ַ�
	 * @param rel		�滻�ַ�
	 * @return	�Ƿ��滻�ɹ�
	 */
	public boolean replace(JTextArea are, String finds, String rel);
	/**
	 * ���ı���are�н�����finds�ַ��滻��rel
	 * @param are		ԭ�ı���
	 * @param finds		���滻�ַ�
	 * @param rel		�滻�ַ�
	 * @return			�滻�ַ���
	 */
	public int replaceAll(JTextArea are, String finds, String rel);
}