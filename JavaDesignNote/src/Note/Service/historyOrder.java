package Note.Service;

import java.util.Vector;

import Note.Info.History;
/**
 * 历史纪录操作接口
 * @author PMY
 *
 */
public interface historyOrder {
	/**
	 * 为历史纪录按照by指定的字段排序
	 * @param t	历史纪录表
	 * @param by	排序字段
	 * @return	排序好的历史纪录表
	 */
	public <T> T orderBy(T t,int by);
}
