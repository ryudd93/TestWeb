package TestWeb.user.model;

public class UserVO {
	
	private String id;
	private String password;
	private String name;
	private String ph_number;
	private String email;
	private String addr_basic;
	private String addr_detail;
	private String regdate;
	
	public UserVO() {
		// TODO Auto-generated constructor stub
	}

	public UserVO(String id, String password, String name, String ph_number, String email, String addr_basic,
			String addr_detail, String regdate) {
		super();
		this.id = id;
		this.password = password;
		this.name = name;
		this.ph_number = ph_number;
		this.email = email;
		this.addr_basic = addr_basic;
		this.addr_detail = addr_detail;
		this.regdate = regdate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPh_number() {
		return ph_number;
	}

	public void setPh_number(String ph_number) {
		this.ph_number = ph_number;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddr_basic() {
		return addr_basic;
	}

	public void setAddr_basic(String addr_basic) {
		this.addr_basic = addr_basic;
	}

	public String getAddr_detail() {
		return addr_detail;
	}

	public void setAddr_detail(String addr_detail) {
		this.addr_detail = addr_detail;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}
	
	
	
	
}