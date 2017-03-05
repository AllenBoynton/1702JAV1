// Allen Boynton

// JAV1 - 1702

// MembersAdapter.java

package edu.fullsail.aboynton.boyntonallen_ce12;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import edu.fullsail.aboynton.boyntonallen_ce12.object.Member;


class MembersAdapter extends BaseAdapter {

	private final Context mContext;
	private ArrayList<Member> mMembers = new ArrayList<>();

	MembersAdapter(Context _context, ArrayList<Member> _members) {
		this.mContext = _context;
		this.mMembers = _members;
	}

	@Override
	public int getCount() {
		if (mMembers.size() > 0) {
			return this.mMembers.size();
		}
		return -1;
	}

	@Override
	public Object getItem(int _pos) {
		return mMembers.get(_pos);
	}

	@Override
	public long getItemId(int _position) {
		return _position;
	}

	@Override
	public View getView(int _position, View _convertView, ViewGroup _parent) {
		View inflate = _convertView == null ? LayoutInflater.from(mContext).inflate(android.R.layout.simple_list_item_1, _parent, false) : _convertView;
		Member member = mMembers.get(_position);
		((TextView)inflate).setText(member.getParty());
		return _convertView;
	}
}