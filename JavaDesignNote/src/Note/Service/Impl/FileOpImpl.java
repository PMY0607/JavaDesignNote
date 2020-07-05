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
 * �ļ�����ʵ��
 * @author PMY
 *
 */
public class FileOpImpl implements FileOp {

	 @Override
	public String OpenFileToArea(JTextArea text) throws Exception {
		JFileChooser chooser;
		int result;
		File file = null;
		// ���ô�ʱ��Ĭ��Ŀ¼�����ַ�ʽ
		chooser = new JFileChooser("C:\\Users\\PMY\\Desktop");
		chooser.setFileFilter(new filter());
		result = chooser.showOpenDialog(null); // �����򿪴��ڻ�ð�ť���
		if (result == JFileChooser.APPROVE_OPTION) {// �򿪰�ť
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
			// �����ȡ����ť
			return null;
		}
		return null;
	}

	 @Override
	public String SaveFile(JTextArea text, File file) throws Exception {
		int result;
		JFileChooser chooser;
		if (file == null) { // ��ǰ�����ļ�Ϊ��
			chooser = new JFileChooser("C:\\Users\\PMY\\Desktop"); // ���ļ�ѡ�����
			chooser.setFileFilter(new filter());
			result = chooser.showSaveDialog(null);

			if (result == JFileChooser.APPROVE_OPTION) { // ѡ�񱣴�
				File selectFile = chooser.getSelectedFile();
				String end = chooser.getFileFilter().getDescription();
				File newFile = null;
				if (selectFile.getAbsolutePath().toLowerCase().endsWith(end.toLowerCase())) {
					// ����ļ�����ѡ����չ�������ģ���ֱ�ӱ��浽ѡ���ļ���
					newFile = selectFile;
				} else {
					// ������չ��
					newFile = new File(selectFile.getAbsoluteFile() + end);
				}

				if (newFile.exists() == false) {
					newFile.createNewFile();
				}
				// ��ʼд���ļ�
				// BufferedWriter br = new BufferedWriter(new FileWriter(newFile));
				OutputStreamWriter br = new OutputStreamWriter(new FileOutputStream(newFile));
				char chs[] = text.getText().toCharArray();
				br.write(chs);
				br.flush();
				br.close();
				file = newFile;
			}else return null;
		} else { // �Ѵ��ڱ༭���ĵ�ֱ�ӱ��漴��
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
			File selectfile = chooser.getSelectedFile(); // ����ļ���
			// ��ɸѡ�������ļ���׺��
			String end = chooser.getFileFilter().getDescription();
			File newFile = null;
			if (selectfile.getAbsolutePath().toUpperCase().endsWith(end.toUpperCase())) {
				// ����ļ���׺һ�£���ʹ��ԭ��������
				newFile = selectfile;
			} else {
				// �������ѡ���ĺ�׺
				newFile = new File(selectfile.getAbsolutePath() + end);
			}
			// �ж��Ƿ����
			if (newFile.exists() == false) {
				newFile.createNewFile();
			} // ��ʼд���ļ�
				// FileWriter writer = new FileWriter(newFile);
			OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(newFile));
			char[] arry = text.getText().toCharArray();
			writer.write(arry);
			writer.flush();
			writer.close();
			return newFile.getAbsolutePath();
		} else if (result == JFileChooser.CANCEL_OPTION) {
			// �����ȡ����ť
			return null;
		}
		return null;
	}

	/**
	 * �ļ�ѡ���������ѡ����txt��β���ļ�
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
