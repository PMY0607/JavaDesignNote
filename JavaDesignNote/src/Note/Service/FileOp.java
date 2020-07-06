package Note.Service;

import java.io.File;

import javax.swing.JTextArea;
/**
 * �ļ������ӿ�
 * @author PMY
 *
 */
public interface FileOp {
	/**
	 * ���ļ���text�ı�����<br>
	 * @param text	Ҫ¼����ı���
	 * @return	�򿪵��ļ�����·��
	 * @throws Exception	�������쳣
	 */
	public String OpenFileToArea(JTextArea text) throws Exception;
	
	/**
	 * �����ļ���file���У���file�����ļ������ڣ�����ļ�ѡ����ѡ���ļ�����<br>
	 * @param text	�ı���Դ
	 * @param file	¼��Ŀ���ļ�
	 * @return		�������ļ�·��
	 * @throws Exception	������쳣
	 */
	public String SaveFile(JTextArea text, File file) throws Exception;
	
	/**
	 * ��text�ı��������Ϊ�����ļ���
	 * @param text ԭ�ı���
	 * @return	���Ϊ�ļ���·��
	 * @throws Exception	������쳣
	 */
	public String SaveOtherFile(JTextArea text) throws Exception;
}
