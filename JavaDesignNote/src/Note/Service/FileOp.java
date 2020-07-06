package Note.Service;

import java.io.File;

import javax.swing.JTextArea;
/**
 * 文件操作接口
 * @author PMY
 *
 */
public interface FileOp {
	/**
	 * 打开文件到text文本域中<br>
	 * @param text	要录入的文本域
	 * @return	打开的文件完整路径
	 * @throws Exception	输入流异常
	 */
	public String OpenFileToArea(JTextArea text) throws Exception;
	
	/**
	 * 保存文件到file当中，若file或者文件不存在，则打开文件选择器选择文件保存<br>
	 * @param text	文本域源
	 * @param file	录入目的文件
	 * @return		保存后的文件路径
	 * @throws Exception	输出流异常
	 */
	public String SaveFile(JTextArea text, File file) throws Exception;
	
	/**
	 * 将text文本域内另存为其他文件内
	 * @param text 原文本域
	 * @return	另存为文件的路径
	 * @throws Exception	输出流异常
	 */
	public String SaveOtherFile(JTextArea text) throws Exception;
}
