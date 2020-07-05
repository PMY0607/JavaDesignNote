package Note.Info;

import java.sql.Date;

/**
 * 历史文件成员
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
	 * 初始化历史操作文件纪录
	 * @param userName		最后一次操作对象
	 * @param lastFileName	文件完整路径
	 * @param lastDate		最后一次修改时间
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
