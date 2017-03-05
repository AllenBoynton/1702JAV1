// Allen Boynton

// JAV1 - 1702

// BooksAdapter.java

package edu.fullsail.aboynton.boyntonallen_ce09;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import edu.fullsail.aboynton.boyntonallen_ce09.object.Book;

// Class for Grid view build
class BooksAdapter extends BaseAdapter {

    private static final String TAG = "BooksAdapter.TAG";

    private Context context;
    private ArrayList<Book> bookList;

    BooksAdapter(Activity context, ArrayList<Book> books) {
        this.context = context;
        this.bookList = books;
    }

    @Override
    public int getCount() {
        Log.i(TAG, " ----> getCount(): " + bookList.size());
        if (bookList.size() >= 0) {
            return bookList.size();
        }
        return 0;
    }

    @Override
    public Book getItem(int pos) {
        return bookList.get(pos);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    // create a new ImageView for each item referenced by the Adapter
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.i(TAG, " ----> getView()");

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.grid_item_layout, parent, false);

        // Display image and title in grid view
        Book book = bookList.get(position);

        ImageView bookPhoto = (ImageView) convertView.findViewById(R.id.grid_item_image);
        TextView bookTitle = (TextView) convertView.findViewById(R.id.grid_item_title);

        if (book != null) {
            bookTitle.setText(book.getTitle());
        }

        String imageUrl = null;
        if (book != null) {
            imageUrl = book.getThumbnail();
        }

        Picasso.with(context)
                .load(imageUrl)
                .into(bookPhoto);

        Log.i(TAG, " <---- getView()");

        return convertView;
    }
}
