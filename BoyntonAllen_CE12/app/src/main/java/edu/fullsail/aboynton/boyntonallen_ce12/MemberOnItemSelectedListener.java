// Allen Boynton

// JAV1 - 1703

// MemberOnItemSelectedListener.java

package edu.fullsail.aboynton.boyntonallen_ce12;


import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

class MemberOnItemSelectedListener implements AdapterView.OnItemSelectedListener {

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        Toast.makeText(parent.getContext(),
                "Item Selected : " + parent.getItemAtPosition(pos).toString(),
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

}
