package com.example.tpfinalquizvrai;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;


import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class ConteneurFragmentsActivity extends AppCompatActivity {

    ViewPager2 viewPager;
    ScreenSlidePagerAdapter adapter;
    private Score s;
    private int nbQuestions = 3;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_conteneur_fragment);
        s = new Score();

//        On va chercher le viewpager dans notre xml activity_main, celui ci s occupe de gerer les
//          fragments et leur position respective
        viewPager = findViewById(R.id.viewPager);

//        On doit creer un adapter pour swiper/slider la page
        adapter = new ScreenSlidePagerAdapter(this);
        // remplir le ViewPager avec un adaptateur
        viewPager.setAdapter(adapter);
//        On doit register pour un call back, losrque les pages sont swiped
        viewPager.registerOnPageChangeCallback(new CallBackPage());

    }

    public Score getS() {
        return s;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_principla, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        //seulement un item
//        lorsqu on appuie sur le bouton suivant, on avance de un fragment
        viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
        return true;
    }

    @Override
    public void onBackPressed() {
//        Si on est a la position 0, on retourne vers l activity de base
        if (viewPager.getCurrentItem() == 0) {

            super.onBackPressed();
        } else {
//            Sinon, on recule d une position dans le viewPager
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);

        }
    }

    public class ScreenSlidePagerAdapter extends FragmentStateAdapter {
        public ScreenSlidePagerAdapter(FragmentActivity fm) {
            super(fm);
        }

        //          On redefini les methodes propres au ScreenSliderPagerAdapter
        @Override
        public Fragment createFragment(int position) {

//            Dependament de la position, on retourne le fragment correspondant

            if ( position < nbQuestions / 3 )
            {
                return new QuestionFollowers();
            }
            else if (position < nbQuestions / 1.5)
            {
                return new QuestionGenres();
            }
            else if (position < nbQuestions){
                return new QuestionPopularity();
            }
            else{
                return new FinFragment();
            }

        }

        @Override
//        Cette fonction doit retourner un int correspondant au nombre de fragments
        public int getItemCount() {

            return nbQuestions + 1;

        }
    }


    private class CallBackPage extends ViewPager2.OnPageChangeCallback
    {
        @Override
        public void onPageSelected(int position) {
            super.onPageSelected(position);
        }
    }

}