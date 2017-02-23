// Allen Boynton

// JAV1 - 1702

// GridViewAdapter.java

package edu.fullsail.aboynton.googlebookslisting;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

// Class for Grid view build
class GridViewAdapter extends ArrayAdapter<BookGridData> {

    private final Context context;
    private final ArrayList<BookGridData> mBookItems;

    GridViewAdapter(Context context, ArrayList<BookGridData> mBookItems) {
        super(context, R.layout.grid_item_layout, mBookItems);
        this.context = context;
        this.mBookItems = mBookItems;
    }

    @Override
    public int getCount() {
        return mBookItems.size();
    }

    @Override
    public BookGridData getItem(int position) {
        return mBookItems.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    @NonNull // Did not know how to fix this
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.grid_item_layout, parent, false);

        BookGridData bookData = getItem(position);

        ImageView bookPhoto = (ImageView) rowView.findViewById(R.id.grid_item_image);
        TextView bookTitle = (TextView) rowView.findViewById(R.id.grid_item_title);

        if (bookData != null) {
            bookTitle.setText(bookData.getTitle());
        }
        String imageUrl = null;
        if (bookData != null) {
            imageUrl = bookData.getPhoto();
        }

        Picasso.with(context)
                .load(imageUrl)
                .resize(200,200)
                .into(bookPhoto);

        return rowView;
    }
}
