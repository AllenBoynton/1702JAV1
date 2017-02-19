// Allen Boynton

// JAV1 - 1702

// GridViewAdapter.java

package edu.fullsail.aboynton.googlebookslisting;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

// Class for Grid view build
class GridViewAdapter extends ArrayAdapter<BookGridData> {

    private Context context;
    private ArrayList<BookGridData> gridDataArrayList;


    GridViewAdapter(Context context, int layoutResourceId, ArrayList<BookGridData> objects) {
        super(context, layoutResourceId, objects);
        this.context = context;
        this.gridDataArrayList = objects;
    }

    public void setGridData(ArrayList<BookGridData> gridDataArrayList) {
        this.gridDataArrayList = gridDataArrayList;
        notifyDataSetChanged();
    }
//    public int getCount() {
//        return gridDataArrayList.size();
//    }
//
//    public String getItem(int position) {
//        return null;
//    }
//
//    public long getItemId(int position) {
//        return layoutResourceId;
//    }

    // create a new ImageView for each item referenced by the Adapter
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.grid_item_layout, parent, false);

        // Display book title
        BookGridData bookGridData = gridDataArrayList.get(position);
        TextView textView = (TextView) view.findViewById(R.id.grid_item_title);
        textView.setText(bookGridData.getName());

        //Display flower photo in ImageView widget
        ImageView image = (ImageView) view.findViewById(R.id.grid_item_image);
        image.setImageBitmap(bookGridData.getBitmap());

        return view;
    }
//        ImageView imageView = (ImageView) convertView;
//        if (imageView == null) {
//            // if it's not recycled, initialize some attributes
//            imageView = new ImageView(context);
//
//        }
//        String url = "https://www.googleapis.com/books/v1/volumes?q=scorpions&orderBy=newest&key=AIzaSyDA1ZH96CVwdIshPOzfCUsWjKpibS6_JO8";
//
//        Picasso.with(context)
//                .load(url)
//                .resize(200,200)
//                .into(imageView);
//
//
//        return imageView;
//    }
}
