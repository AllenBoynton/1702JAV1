// Allen Boynton

// JAV1 - 1702

// BookGridData.java

package edu.fullsail.aboynton.googlebookslisting;


import android.graphics.Bitmap;

class BookGridData {

    private String name;
    private String photo;
    private Bitmap bitmap;

    BookGridData() {
    }

    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    public String getPhoto() {
        return photo;
    }

    void setPhoto(String photo) {
        this.photo = photo;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }
}
