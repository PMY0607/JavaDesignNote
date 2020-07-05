package Note.Info;
/**
 * 用户成员
 * @author PMY
 *
 */
public class User {
	private String username;
	private String passwold;
	private String lastFileName=null;
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(String username, String passwold) {
		super();
		this.username = username;
		this.passwold = passwold;
	}
	
	public User(String username, String passwold, String lastFileName) {
		super();
		this.username = username;
		this.passwold = passwold;
		this.lastFileName = lastFileName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPasswold() {
		return passwold;
	}

	public void setPasswold(String passwold) {
		this.passwold = passwold;
	}

	public String getLastFileName() {
		return lastFileName;
	}

	public void setLastFileName(String lastFileName) {
		this.lastFileName = lastFileName;
	}
	
}
