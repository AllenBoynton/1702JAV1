package edu.fullsail.aboynton.boyntonallen_ce12.object;

public class Member {

	private int mId;
	private String mName;
	private String mParty;

	private Member() {
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

	public void setId(int _id) {
		mId = _id;
	}

	public int getId() {
		return mId;
	}

	public void setName(String _name) {
		mName = _name;
	}

	public String getName() {
		return mName;
	}

	public void setParty(String _party) {
		mParty = _party;
	}

	public String getParty() {
		return mParty;
	}
}