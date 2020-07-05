package Note.Factory;

import Note.Service.Impl.FileOpImpl;
/**
 * �ļ������๤��
 * @author PMY
 *
 */
public class FileOpFactory {
	/**
	 * ����ļ���������
	 * @return ����һ���ļ�������
	 */
	public static FileOpImpl getFileOpImpl() {
		return new FileOpImpl();
	}
}
