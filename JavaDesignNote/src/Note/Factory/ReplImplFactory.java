package Note.Factory;

import Note.Service.Impl.ReplImpl;
/**
 * �����滻�๤��
 * @author PMY
 *
 */
public class ReplImplFactory {
	private ReplImplFactory() {}
	/**
	 * 	��ò����滻�����
	 * @return ���ز����滻����
	 */
	public static ReplImpl getReplImpl() {
		return new ReplImpl();
	}
}
