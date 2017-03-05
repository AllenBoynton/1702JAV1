// Allen Boynton

// JAV1 - 1702

// Member.java

package edu.fullsail.aboynton.boyntonallen_ce12.object;

public class Member {
	private int mId;
	private String mName;
	private String mParty;

	public Member() {
		this.mId = 0;
		this.mName = mParty = "";
	}

	private Member(int _id) {
		this();
		this.mId = _id;
	}

	private Member(int _id, String _name) {
		this(_id);
		this.mName = _name;
	}

	public Member(int _id, String _name, String _party) {
		this(_id, _name);
		this.mParty = _party;
	}

	public void setId(int mId) {
		this.mId = mId;
	}

	public int getId() {
		return mId;
	}

	public void setName(String _name) {
		this.mName = _name;
	}

	public String getName() {
		return mName;
	}

	public String getParty() {
		return mParty;
	}
}