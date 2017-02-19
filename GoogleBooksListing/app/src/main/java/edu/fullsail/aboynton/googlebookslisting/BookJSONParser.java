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
            List<BookGridData> bookList = new ArrayList<>();

            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                BookGridData bookGridData = new BookGridData();

                bookGridData.setName(object.getString("name"));
                bookGridData.setPhoto(object.getString("photo"));

                bookList.add(bookGridData);
            }
            return bookList;
        }
        catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }
}
