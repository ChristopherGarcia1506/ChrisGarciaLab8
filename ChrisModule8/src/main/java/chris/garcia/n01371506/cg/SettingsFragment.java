package chris.garcia.n01371506.cg;

import static android.Manifest.permission.READ_MEDIA_IMAGES;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class SettingsFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final String Permission = Manifest.permission.READ_MEDIA_IMAGES;;//manifest permission
    private static final int PermissionCode = 1;
    private ActivityResultLauncher<Intent> galleryLauncher;
    ImageView imageView;

    private String mParam1;
    private String mParam2;

    public SettingsFragment() {
        // Required empty public constructor
    }

    public static SettingsFragment newInstance(String param1, String param2) {
        SettingsFragment fragment = new SettingsFragment();
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

        // Initialize the ActivityResultLauncher
        galleryLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == getActivity().RESULT_OK && result.getData() != null) {
                        // Get the selected image URI
                        Uri selectedImageUri = result.getData().getData();

                        // Update the ImageView with the selected image
                        imageView.setImageURI(selectedImageUri);
                    } else {
                        // Handle the case where the user didn't pick an image
                        Toast.makeText(requireContext(), "No image selected", Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_settings, container, false);

        imageView = view.findViewById(R.id.imageView);
        Button button2 = view.findViewById(R.id.button2);//Button

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestRuntimePerm();
            }
        });




        return view;
    }

    private void RequestRuntimePerm() {
        Context context = getContext();

        if (ActivityCompat.checkSelfPermission(context, Permission) == PackageManager.PERMISSION_GRANTED) {
           openGallery();
        } else {//requests permission if it has not been granted yet.
            ActivityCompat.requestPermissions(requireActivity(), new String[]{Permission}, PermissionCode);
        }
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if (requestCode == PermissionCode) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(getContext(),"Permission Allowed", Toast.LENGTH_SHORT).show();
                openGallery();
            } else {
                Toast.makeText(getContext(),"Permission Denied \tAPI: ", Toast.LENGTH_SHORT).show();
            }
        }
    }

    //---open Gallery function---
    private void openGallery() {
        // Create an Intent to open the gallery
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        galleryLauncher.launch(intent);
    }
}