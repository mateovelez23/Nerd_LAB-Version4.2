package com.jhonlopera.nerd30;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import android.widget.FrameLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class PuntajeActivity extends PrincipalActivity implements NavigationView.OnNavigationItemSelectedListener{

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;
    SharedPreferences preferencias;
    String correoR, ident, puntos4im[]=new String[10], puntoscon[]=new String[10], puntostopo[]=new String[10];
    int i;
    DatabaseReference myRef1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_puntajes);

        FrameLayout contentFrameLayout = (FrameLayout) findViewById(R.id.frameprincipal);
        getLayoutInflater().inflate(R.layout.activity_puntajes, contentFrameLayout);

        ft.remove(fragment1); //se remueve el fragment que se inicia por defecto en el oncreate de principal
        getSupportActionBar().setTitle("Puntajes");
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        preferencias=getSharedPreferences("Preferencias", Context.MODE_PRIVATE);
        correoR = preferencias.getString("correo",correoR);
        ident = correoR.replace(".","");
        final FirebaseDatabase database = FirebaseDatabase.getInstance();

        myRef1 = database.getReference("Puntajes4im");
        myRef1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Puntajes4im puntajes4im = dataSnapshot.getValue(Puntajes4im.class);
                for(i=0;i<=9;i++){
                    puntos4im[i]=puntajes4im.getPuntos4im();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        myRef1 = database.getReference("Puntajes4im");
        myRef1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Puntajes4im puntajes4im = dataSnapshot.getValue(Puntajes4im.class);
                for(i=0;i<=9;i++){
                    puntos4im[i]=puntajes4im.getPuntos4im();

                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });






    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            switch (position){
                case 0:
                    return (new CreditosTpFragment());
                case 1:
                    return (new CreditosCtFragment());
                case 2:
                    return (new CreditosCPULFragment());
                default:return null;
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Aplasta al topo";
                case 1:
                    return "Concentrese";
                case 2:
                    return "4 palabras 1 letra";
            }
            return null;
        }
    }
}
