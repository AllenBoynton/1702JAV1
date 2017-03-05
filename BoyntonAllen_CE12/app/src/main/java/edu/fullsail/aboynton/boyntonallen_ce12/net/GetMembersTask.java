package edu.fullsail.aboynton.boyntonallen_ce12.net;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import edu.fullsail.aboynton.boyntonallen_ce12.MainActivity;
import edu.fullsail.aboynton.boyntonallen_ce12.object.Member;


public class GetMembersTask extends AsyncTask<Void, Void, ArrayList<Member>> {
	
	private static final String API_URL = "https://www.govtrack.us/api/v2/role?current=true";
	
	private MainActivity mActivity = new MainActivity();

	private Member member = new Member();
	
	public GetMembersTask(MainActivity _activity) {
		this.mActivity = _activity;
	}
	
	@Override
	protected ArrayList<Member> doInBackground(Void... _params) {
		
		String data = edu.fullsail.aboynton.boyntonallen_ce12.net.NetworkUtils.getNetworkData(API_URL);
		
		try {
			
			JSONObject outerMostObject = new JSONObject(data);
			
			JSONArray membersJson = outerMostObject.getJSONArray("objects");
			
			ArrayList<Member> members = new ArrayList<>();
			
			for(int i = 0; i < membersJson.length(); i++) {
				JSONObject obj = membersJson.getJSONObject(i);
				JSONObject person = obj.getJSONObject("person");

				int id = person.getInt("id");
				String name = person.getString("name");
				String party = obj.getString("party");
				
				members.add(new Member(id, name, party));
			}

			// Update the UI
			mActivity.showMembersListScreen(members);
			mActivity.showMemberDetailsScreen(member.getId());

			return members;
			
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	@Override
	protected void onPostExecute(ArrayList<Member> _result) {
		super.onPostExecute(_result);
	}
}