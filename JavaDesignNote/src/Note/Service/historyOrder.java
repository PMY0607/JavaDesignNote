package Note.Service;

import java.util.Vector;

import Note.Info.History;
/**
 * ��ʷ��¼�����ӿ�
 * @author PMY
 *
 */
public interface historyOrder {
	/**
	 * Ϊ��ʷ��¼����byָ�����ֶ�����
	 * @param t	��ʷ��¼��
	 * @param by	�����ֶ�
	 * @return	����õ���ʷ��¼��
	 */
	public <T> T orderBy(T t,int by);
}
