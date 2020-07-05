package Note.Info;

import java.sql.Date;

/**
 * ��ʷ�ļ���Ա
 * @author PMY
 *
 */
public class History {
	
	private String userName;
	private String lastFileName;	
	private Date lastDate;
	
	public History() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * ��ʼ����ʷ�����ļ���¼
	 * @param userName		���һ�β�������
	 * @param lastFileName	�ļ�����·��
	 * @param lastDate		���һ���޸�ʱ��
	 */
	public History( String userName,String lastFileName ,Date lastDate) {
		super();
		this.userName = userName;
		this.lastFileName = lastFileName;
		this.lastDate = lastDate;
	}

	public String getLastFileName() {
		return lastFileName;
	}
	public void setLastFileName(String lastFileName) {
		this.lastFileName = lastFileName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getLastDate() {
		return lastDate;
	}
	public void setLastDate(Date lastDate) {
		this.lastDate = lastDate;
	}
	
	
	
}
