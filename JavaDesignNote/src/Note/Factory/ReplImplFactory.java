package Note.Factory;

import Note.Service.Impl.ReplImpl;
/**
 * 查找替换类工厂
 * @author PMY
 *
 */
public class ReplImplFactory {
	private ReplImplFactory() {}
	/**
	 * 	获得查找替换类对象
	 * @return 返回查找替换对象
	 */
	public static ReplImpl getReplImpl() {
		return new ReplImpl();
	}
}
