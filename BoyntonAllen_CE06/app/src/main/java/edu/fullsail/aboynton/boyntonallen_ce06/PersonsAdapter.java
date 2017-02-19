// Allen Boynton

// JAV1 - 1702

// PersonsAdapter.java

package edu.fullsail.aboynton.boyntonallen_ce06;


import android.content.Context;
import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListAdapter;

class PersonsAdapter implements ListAdapter {



    private final Context mContext;

    // Constructor
    PersonsAdapter(Context c) {
        mContext = c;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    public int getCount() {
        return photoIDs.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;

        if (convertView == null) {
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        } else {
            imageView = (ImageView) convertView;
        }
        imageView.setImageResource(photoIDs[position]);
        return imageView;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 0;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    // Keep all Images in array
    private final Integer[] photoIDs = {R.drawable.gw, R.drawable.ja,
            R.drawable.tj, R.drawable.jm, R.drawable.jm2, R.drawable.jqa,
            R.drawable.aj, R.drawable.mvb, R.drawable.wh, R.drawable.dt
    };

    @Override
    public boolean areAllItemsEnabled() {
        return false;
    }

    @Override
    public boolean isEnabled(int position) {
        return false;
    }
}
