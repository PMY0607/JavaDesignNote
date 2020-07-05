package Note.Service.Impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileFilter;

import Note.Service.FileOp;
/**
 * 文件操作实现
 * @author PMY
 *
 */
public class FileOpImpl implements FileOp {

	 @Override
	public String OpenFileToArea(JTextArea text) throws Exception {
		JFileChooser chooser;
		int result;
		File file = null;
		// 设置打开时的默认目录，两种方式
		chooser = new JFileChooser("C:\\Users\\PMY\\Desktop");
		chooser.setFileFilter(new filter());
		result = chooser.showOpenDialog(null); // 弹出打开窗口获得按钮结果
		if (result == JFileChooser.APPROVE_OPTION) {// 打开按钮
			file = chooser.getSelectedFile();
			int length = (int) file.length();
			// BufferedReader reader = new BufferedReader(new FileReader(file));
			InputStreamReader reader = new InputStreamReader(new FileInputStream(file));
			char[] ch = new char[length];
			reader.read(ch);
			reader.close();
			text.setText(new String(ch).trim());
			return file.getAbsolutePath();
		} else if (result == JFileChooser.CANCEL_OPTION) {
			// 点击了取消按钮
			return null;
		}
		return null;
	}

	 @Override
	public String SaveFile(JTextArea text, File file) throws Exception {
		int result;
		JFileChooser chooser;
		if (file == null) { // 当前操作文件为空
			chooser = new JFileChooser("C:\\Users\\PMY\\Desktop"); // 打开文件选择存入
			chooser.setFileFilter(new filter());
			result = chooser.showSaveDialog(null);

			if (result == JFileChooser.APPROVE_OPTION) { // 选择保存
				File selectFile = chooser.getSelectedFile();
				String end = chooser.getFileFilter().getDescription();
				File newFile = null;
				if (selectFile.getAbsolutePath().toLowerCase().endsWith(end.toLowerCase())) {
					// 如果文件是以选定扩展名结束的，则直接保存到选定文件内
					newFile = selectFile;
				} else {
					// 加上扩展名
					newFile = new File(selectFile.getAbsoluteFile() + end);
				}

				if (newFile.exists() == false) {
					newFile.createNewFile();
				}
				// 开始写入文件
				// BufferedWriter br = new BufferedWriter(new FileWriter(newFile));
				OutputStreamWriter br = new OutputStreamWriter(new FileOutputStream(newFile));
				char chs[] = text.getText().toCharArray();
				br.write(chs);
				br.flush();
				br.close();
				file = newFile;
			}else return null;
		} else { // 已存在编辑的文档直接保存即可
			char chs[] = text.getText().toCharArray();

			// BufferedWriter br = new BufferedWriter(new FileWriter(file));
			OutputStreamWriter br = new OutputStreamWriter(new FileOutputStream(file));
			br.write(chs);
			br.flush();
			br.close();
		}
		return file.getAbsolutePath();
	}

	 @Override
	public String SaveOtherFile(JTextArea text) throws Exception {
		JFileChooser chooser;
		int result;
		chooser = new JFileChooser("C:\\Users\\xiaozhx\\Desktop");
		chooser.setFileFilter(new filter());
		result = chooser.showSaveDialog(null);
		if (result == JFileChooser.APPROVE_OPTION) {
			File selectfile = chooser.getSelectedFile(); // 获得文件名
			// 被筛选出来的文件后缀名
			String end = chooser.getFileFilter().getDescription();
			File newFile = null;
			if (selectfile.getAbsolutePath().toUpperCase().endsWith(end.toUpperCase())) {
				// 如果文件后缀一致，则使用原来的名字
				newFile = selectfile;
			} else {
				// 否则加上选定的后缀
				newFile = new File(selectfile.getAbsolutePath() + end);
			}
			// 判断是否存在
			if (newFile.exists() == false) {
				newFile.createNewFile();
			} // 开始写入文件
				// FileWriter writer = new FileWriter(newFile);
			OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(newFile));
			char[] arry = text.getText().toCharArray();
			writer.write(arry);
			writer.flush();
			writer.close();
			return newFile.getAbsolutePath();
		} else if (result == JFileChooser.CANCEL_OPTION) {
			// 点击了取消按钮
			return null;
		}
		return null;
	}

	/**
	 * 文件选择过滤器，选择以txt结尾的文件
	 * @author PMY
	 *
	 */
	private class filter extends FileFilter {
		public boolean accept(File file) {
			String name = file.getName();
			// name.toLowerCase();
			if (name.endsWith(".txt") || file.isDirectory())
				return true;
			else
				return false;
		}

		public String getDescription() {
			return ".txt";
		}
	}

}
