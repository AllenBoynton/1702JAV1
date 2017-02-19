// Allen Boynton

// JAV1 - 1702

// MainActivity.java

package edu.fullsail.aboynton.boyntonallen_ce06;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private PersonsAdapter personsAdapter = new PersonsAdapter(this);
    private CustomOnItemSelectedListener customOnItemSelectedListener =
            new CustomOnItemSelectedListener();

    private String name;
    private String image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView listView = (ListView) findViewById(R.id.listView);

        // Adding all elements from each array to the final array list
        List<String> fullName = Collections.singletonList(name);


        // Displaying elements of the joined ArrayList
        for (String temp: fullName){
            System.out.println(temp);
        }

        ArrayAdapter adapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, fullName);

        listView.setAdapter(adapter);

//        GridView gridview = (GridView) findViewById(R.id.gridview);
//        gridview.setAdapter(new PersonsAdapter(this));

        // Call methods to name spinners
        addListenerOnSpinnerItemSelection();

        /********************** Incompleted *****************/
        personsAdapter.getCount();
        customOnItemSelectedListener.getClass();
//        if (name.equals(image)) {
//            System.out.println(name);
//        }
    }

    private void addListenerOnSpinnerItemSelection() {
        Spinner spinner1 = (Spinner) findViewById(R.id.spinner1);
        Spinner spinner2 = (Spinner) findViewById(R.id.spinner2);
        spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());
        spinner2.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }
}
