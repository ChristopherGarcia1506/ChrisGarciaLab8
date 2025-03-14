//Chris Garcia n01371506
package chris.garcia.n01371506.cg;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.android.material.snackbar.Snackbar;


public class ShareFragment extends Fragment {


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ShareFragment() {
        // Required empty public constructor
    }

    public static ShareFragment newInstance(String param1, String param2) {
        ShareFragment fragment = new ShareFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_share, container, false);


        //---Inputs---

        CheckBox checkBoxInput = view.findViewById(R.id.ChrcheckBox);
        EditText emailInput = view.findViewById(R.id.ChreditTextEmailAddress);
        EditText idInput = view.findViewById(R.id.ChreditTextNumberDecimal);

        //---On ImageButton Clicked ---
        ImageButton imageButton = view.findViewById(R.id.ChrShareImageButton);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String dummyEmail = "abc@abc.com";
                String email = emailInput.getText().toString().trim();
                String idText= idInput.getText().toString().trim();
                int iD = Integer.parseInt(idText);//converting String to int
                boolean checkbox = checkBoxInput.isChecked();

                //---Conditions---

                if(email.equals(dummyEmail)){
                    saveDataForString(requireContext(),"email",email);
                }else{
                    emailInput.setError("Error: email does not match");
                }

                if (iD< 6) {
                    idInput.setError("Error: Minimum of 6 Digits.");
                } else {
                    //store data
                    saveDataForInt(requireContext(), "id", iD);//storing id
                }
                //store checkbox
                saveDataForBoolean(requireContext(), "checkbox", checkbox);//storing checkbox
                DisplaySnackBar(view,"email: "+getSavedString(getContext(),"email")+"  Checkbox: "+getSavedBoolean(getContext(),
                        "checkbox")+" Student Id: "+ getSavedInt(getContext(),"id"));

                emailInput.setText("");
                idInput.setText("");
            }
        });

        return view;
    }

    //---- Saving Data ----
    public void saveDataForString(Context context, String key, String value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("SavedData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value); // Save a string
        editor.apply(); // Asynchronously save the data
    }

    public void saveDataForBoolean(Context context, String key, boolean value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("SavedData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, value); // Save a string
        editor.apply(); // Asynchronously save the data

    }

    public void saveDataForInt(Context context, String key, int value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("SavedData", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key, value); // Save a string
        editor.apply(); // Asynchronously save the data

    }

    private void DisplaySnackBar(View view, String msg){
        Snackbar snackbar = Snackbar.make(view,msg,Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction("Dismiss", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                snackbar.dismiss();
            }
        });
        snackbar.show();
    }
   // Retrieving Saved Data
    public String getSavedString(Context context, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("SavedData", Context.MODE_PRIVATE);
        return sharedPreferences.getString(key, "");
    }
    public int getSavedInt(Context context, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("SavedData", Context.MODE_PRIVATE);
        return sharedPreferences.getInt(key,0 );
    }
    public Boolean getSavedBoolean(Context context, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("SavedData", Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(key, false);
    }
}