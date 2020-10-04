
public class CSVClass {

	private String techname;
	private String lable;
	private String hint;
	private String type;
	private String disp;
	private String vizible;
	private String srchbl;

	public String gettechname() {
		return techname;
	}

	public void settechname(String techname) {
		this.techname = techname;
	}

	public String getlable() {
		return lable;
	}

	public void setlable(String lable) {
		this.lable = lable;
	}

	public String gethint() {
		return hint;
	}

	public void sethint(String hint) {
		this.hint = hint;
	}

	public String gettype() {
		return type;
	}

	public void settype(String type) {
		this.type = type;
	}

	public String getdispe() {
		return disp;
	}

	public void setdisp(String disp) {
		this.disp = disp;
	}

	public String getencypted(String isencypted) {
		return isencypted;
	}

	public void setisencypted(String isencypted) {
	}

	public String getvizible() {
		return vizible;
	}

	public void setvizible(String vizible) {
		this.vizible = vizible;
	}

	public String getsrchbl() {
		return srchbl;
	}

	public void setsrchbl(String srchbl) {
		this.srchbl = srchbl;
	}

	// @Override
	// public String toString() {
	// return "{" + techname + "::" + lable + "::" + hint + "" + type + disp + "::"
	// + isencypted + "::" + vizible
	// + "::" + srchbl + "}";
}
