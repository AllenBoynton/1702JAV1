package edu.fullsail.aboynton.boyntonallen_ce12;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import edu.fullsail.aboynton.boyntonallen_ce12.object.Member;

class MembersAdapter extends BaseAdapter {
	
	private static final long ID_CONSTANT = 269488144;
	
	private final Context mContext;

	MembersAdapter(Context _context) {
		this.mContext = _context;
	}

	@Override
	public int getCount() {
		return 0;
	}

	@Override
	public Object getItem(int _position) {
		return null;
	}

	@Override
	public long getItemId(int _position) {
		return 0;
	}

	@Override
	public View getView(int _position, View _convertView, ViewGroup _parent) {
		
		if(_convertView == null) {
			_convertView = LayoutInflater.from(mContext).inflate(android.R.layout.simple_list_item_1, _parent, false);
		}

		Member member = getItem(_position);

		((TextView)_convertView).setText(member.getName());
		
		return _convertView;
	}
}