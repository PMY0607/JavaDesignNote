package Note.Factory;

import Note.Service.Impl.FileOpImpl;
/**
 * 文件操作类工厂
 * @author PMY
 *
 */
public class FileOpFactory {
	/**
	 * 获得文件操作对象
	 * @return 返回一个文件操作类
	 */
	public static FileOpImpl getFileOpImpl() {
		return new FileOpImpl();
	}
}
