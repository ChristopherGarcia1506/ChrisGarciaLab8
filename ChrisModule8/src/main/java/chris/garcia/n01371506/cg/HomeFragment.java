//Chris Garcia n01371506
package chris.garcia.n01371506.cg;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import android.widget.ImageView;

import com.google.android.material.snackbar.Snackbar;

public class HomeFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private  int[] images = {//image links
            R.drawable.maradona,
            R.drawable.graduation,
            R.drawable.mbdtf,
            R.drawable.trilogy,
            R.drawable.tna
    };


    private int i = 0; //image count
    private int z = 0; // click count

    private String mParam1;
    private String mParam2;

    public HomeFragment() {
        // Required empty public constructor
    }
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        View view = inflater.inflate(R.layout.fragment_home, container, false);



        //---ImageButton ---
        ImageView imageView = view.findViewById(R.id.ChrimageView);

        Button button = view.findViewById(R.id.Chrbutton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                i++;
                z++;
                if(i >= images.length){
                    i = 0;
                }
                imageView.setImageResource(images[i]);

               DisplaySnackBar(view,R.string.chris_garcia +" "+z);
            }
        });

        return view;
    }

    private void DisplaySnackBar(View view, String msg){
        Snackbar snackbar = Snackbar.make(view,msg,Snackbar.LENGTH_SHORT);
        snackbar.show();
    }
}