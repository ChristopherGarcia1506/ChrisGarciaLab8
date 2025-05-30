//Chris Garcia n01371506
package chris.garcia.n01371506.cg;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

public class AboutFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AboutFragment() {
        // Required empty public constructor
    }

    public static AboutFragment newInstance(String param1, String param2) {
        AboutFragment fragment = new AboutFragment();
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
        View view = inflater.inflate(R.layout.fragment_about, container, false);

        TextView textView = view.findViewById(R.id.ChrDatatextView);
        String data = textView.getText().toString().trim();

        //Retrieving Data
        SharedPreferences sharedPreferences = requireContext().getSharedPreferences("SavedData",Context.MODE_PRIVATE);
        String email = sharedPreferences.getString("email", ""); // Retrieving email
        int Id = sharedPreferences.getInt("id", 0); // Retrieving email
        boolean checkbox = sharedPreferences.getBoolean("checkbox", false); // Retrieving email

        //assigning data to textview
        data = "email: "+email+ "\n Id: "+ Id + "\n checkbox:" + checkbox;
        textView.setText(data);
        return view;
    }


}