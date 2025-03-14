//Chris Garcia n01371506
package chris.garcia.n01371506.cg;

import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.view.MenuItem;
import android.widget.Toast;
import com.google.android.material.navigation.NavigationView;

public class GarciaActivity8 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawerLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.Chrtoolbar); //Ignore red line errors
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.Chrnav_view);
        navigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) this);;
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav,
                R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.Chrfragment_container, new HomeFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_home);
        }
    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_home) {
            getSupportFragmentManager().beginTransaction().replace(R.id.Chrfragment_container, new HomeFragment()).commit();
            alertPopUp();
        } else if (id == R.id.nav_settings) {
            getSupportFragmentManager().beginTransaction().replace(R.id.Chrfragment_container, new SettingsFragment()).commit();
            alertPopUp();
        } else if (id == R.id.nav_share) {
            getSupportFragmentManager().beginTransaction().replace(R.id.Chrfragment_container, new ShareFragment()).commit();
            alertPopUp();
        } else if (id == R.id.nav_about) {
            getSupportFragmentManager().beginTransaction().replace(R.id.Chrfragment_container, new AboutFragment()).commit();
            alertPopUp();
        } else if (id == R.id.nav_logout) {
            Toast.makeText(this, R.string.logout, Toast.LENGTH_SHORT).show();
            alertPopUp();
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    private void alertPopUp() {  // alert popup settings
        new AlertDialog.Builder(this)
                .setTitle(R.string.chris_garcia)
                .setMessage(R.string.do_you_want_to_exit_the_app)
                .setPositiveButton(R.string.yes, (dialog, which) -> {

                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(getString(R.string.https_www_youtube_com_watch_v_r4l9bfqgmaq)));
                    startActivity(intent);
                })
                .setNegativeButton(R.string.no, (dialog, which) -> {

                })
                .setCancelable(false)
                .show();


    }
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}