package com.sourcey.materiallogindemo;

/**
 * Created by F_ALI on 10/08/2015.
 */
import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;


public class ContactsActivity extends Activity {
    private ContactsAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        // Find RecyclerView and bind to adapter
        final RecyclerView rvContacts = (RecyclerView) findViewById(R.id.rvContacts);

        // allows for optimizations
        rvContacts.setHasFixedSize(true);

        // Define 2 column grid layout
        final GridLayoutManager layout = new GridLayoutManager(ContactsActivity.this, 2);

        // Unlike ListView, you have to explicitly give a LayoutManager to the RecyclerView to position items on the screen.
        // There are three LayoutManager provided at the moment: GridLayoutManager, StaggeredGridLayoutManager and LinearLayoutManager.
        rvContacts.setLayoutManager(layout);

        // get data
        List<Contact> contacts = getContacts();

        // Create an adapter
        mAdapter = new ContactsAdapter(ContactsActivity.this, contacts);

        // Bind adapter to list
        rvContacts.setAdapter(mAdapter);
    }

    private List<Contact> getContacts() {
        List<Contact> contacts = new ArrayList<>();
        contacts.add(new Contact(1, "TT customer service ", R.drawable.contact_one, "1298"));
        contacts.add(new Contact(2, "Police", R.drawable.contact_two, "197"));
        contacts.add(new Contact(3, "Civil Protection", R.drawable.contact_three, "198"));
        contacts.add(new Contact(4, "National Guard", R.drawable.contact_four, "193"));
        contacts.add(new Contact(5, "SAMU", R.drawable.contact_five, "190"));
        contacts.add(new Contact(6, "SONED", R.drawable.contact_six, "71512000"));
        contacts.add(new Contact(7, "STEG", R.drawable.contact_seven, "71792500"));

        return contacts;
    }
}
