// Allen Boynton

// JAV1 - 1702

// GridViewAdapter.java

package edu.fullsail.aboynton.googlebookslisting;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

// Class for Grid view build
class GridViewAdapter extends ArrayAdapter<BookGridData> {

    private Context context;
    private int layoutResourceId;
    private List<BookGridData> gridDataArrayList;


    GridViewAdapter(Context context, int layoutResourceId, List<BookGridData> objects) {
        super(context, layoutResourceId, objects);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.gridDataArrayList = gridDataArrayList;
    }

//    void setGridData(ArrayList<String> gridDataArrayList) {
//        this.gridDataArrayList = gridDataArrayList;
//        notifyDataSetChanged();
//    }

    public int getCount() {
        return gridDataArrayList.size();
    }

    public String getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return layoutResourceId;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView = (ImageView) convertView;
        if (imageView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(context);

        }
        String url = "https://www.googleapis.com/books/v1/volumes?q=scorpions&orderBy=newest&key=AIzaSyDA1ZH96CVwdIshPOzfCUsWjKpibS6_JO8";

        Picasso.with(context)
                .load(url)
                .resize(200,200)
                .into(imageView);


        return imageView;
    }
}
