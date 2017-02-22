// Allen Boynton

// JAV1 - 1702

// BookJSONParser.java

package edu.fullsail.aboynton.googlebookslisting;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

class BookJSONParser {

    static List<BookGridData> parseFeed(String content) {
        try {
            JSONArray array = new JSONArray(content);
            List<BookGridData> dataList = new ArrayList<>();

            for (int i = 0; i < array.length(); i++) {

                JSONObject obj = array.getJSONObject(i);
                BookGridData book = new BookGridData();

                book.setTitle(obj.getString("title"));
                book.setPhoto(obj.getString("photo"));

                dataList.add(book);
            }
            return dataList;

        }
        catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
