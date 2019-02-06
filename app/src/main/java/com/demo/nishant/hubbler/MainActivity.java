package com.demo.nishant.hubbler;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;




public class MainActivity extends AppCompatActivity {

    public DatabaseReference reference;
    LinearLayout linearLayout,linearLayoutsecond;
    Button postbutton;
    ArrayList<String> genderdata = new ArrayList<>();
    String NameHolder,Ageholder,Addressholder;
    EditText editTextname,editTextage,editTextadd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        reference = FirebaseDatabase.getInstance().getReference();
        linearLayout = (LinearLayout) findViewById(R.id.firstcontainer);
        linearLayoutsecond = (LinearLayout) findViewById(R.id.secondcontainer);
        postbutton = (Button)findViewById(R.id.postbutton);
        reference.addValueEventListener(new ValueEventListener() {

            @SuppressLint("ResourceType")
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                drawUi(dataSnapshot);
                postbutton.setVisibility(View.VISIBLE);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });


        postbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                GetDataFromEditText();
                if (NameHolder.matches("") || Ageholder.matches("") || Addressholder.matches(""))
                {
                    Toast.makeText(MainActivity.this, "Please fill mandatory filed", Toast.LENGTH_SHORT).show();
                    return;
                }

                Singleton.getInstance().addToArray(NameHolder);


                Intent intent = new Intent(getApplicationContext(),Firstactivity.class);
                startActivity(intent);
            }
        });
    }



    public void GetDataFromEditText(){


        NameHolder = editTextname.getText().toString().trim();
        Ageholder = editTextage.getText().toString().trim();
        Addressholder = editTextadd.getText().toString().trim();





        JSONObject student1 = new JSONObject();
        try {
            student1.put("Name", NameHolder);
            student1.put("Age", Ageholder);
            student1.put("Gender", "3r");
            student1.put("Address", Addressholder);


        JSONArray jsonArray = new JSONArray();

        jsonArray.put(student1);

        JSONObject studentsObj = new JSONObject();
        studentsObj.put("UserInform", jsonArray);

        String jsonStr = jsonArray.toString();

        Toast.makeText(this, jsonStr, Toast.LENGTH_LONG).show();

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }


    public  void drawUi(DataSnapshot dataSnapshot)
    {
        for (DataSnapshot childDataSnapshot : dataSnapshot.getChildren()) {
            Log.v("Data fetched",""+ dataSnapshot.getChildren().toString() );

            if (childDataSnapshot.child("type").getValue().equals("text")) {
                TextView txtView = new TextView(MainActivity.this);
                editTextname = new EditText(MainActivity.this);
                txtView.setText("Name");

                txtView.setTypeface(txtView.getTypeface(), Typeface.BOLD);

                editTextname.setHint("Enter your Name");

                linearLayout.addView(txtView);
                linearLayout.addView(editTextname);


            }


            if (childDataSnapshot.child("type").getValue().equals("number"))
            {

                TextView txtView = new TextView(MainActivity.this);

                editTextage = new EditText(MainActivity.this);
                txtView.setText("Age");
                txtView.setTypeface(txtView.getTypeface(), Typeface.BOLD);

                editTextage.setHint("Enter your age");
                editTextage.setInputType(InputType.TYPE_CLASS_NUMBER);

                linearLayout.addView(txtView);
                linearLayout.addView(editTextage);


            }

            if (childDataSnapshot.child("type").getValue().equals("dropdown")) {
                Spinner spinner = new Spinner(MainActivity.this);
                TextView txtView = new TextView(MainActivity.this);

                for (DataSnapshot dataSnapshot1 :childDataSnapshot.child("options").getChildren())
                {
                    genderdata.add((String) dataSnapshot1.getValue());
                }
                txtView.setText("Gender");
                txtView.setTypeface(txtView.getTypeface(), Typeface.BOLD);

                ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>
                        (getApplicationContext(), android.R.layout.simple_spinner_item,
                                genderdata); //selected item will look like a spinner set from XML
                spinnerArrayAdapter.setDropDownViewResource(android.R.layout
                        .simple_spinner_dropdown_item);
                spinner.setAdapter(spinnerArrayAdapter);
                linearLayout.addView(txtView);
                linearLayout.addView(spinner);




            }

            if (childDataSnapshot.child("type").getValue().equals("multiline")) {
                TextView txtView = new TextView(MainActivity.this);

                editTextadd = new EditText(MainActivity.this);
                txtView.setText("Address");
                txtView.setTypeface(txtView.getTypeface(), Typeface.BOLD);
                editTextadd.setHint("Enter your address");
                editTextadd.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);
                editTextadd.setLines(3);
                linearLayoutsecond.addView(txtView);
                linearLayoutsecond.addView(editTextadd);

            }

        }
    }


}
