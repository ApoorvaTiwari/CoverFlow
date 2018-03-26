package com.example.apoorva.coverflow;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

import com.mahfa.dnswitch.DayNightSwitch;
import com.mahfa.dnswitch.DayNightSwitchListener;

import java.util.ArrayList;
import java.util.List;

import it.moondroid.coverflow.components.ui.containers.FeatureCoverFlow;

public class MainActivity extends AppCompatActivity {

    DayNightSwitch dayNightSwitch;
    View background_view;

    private FeatureCoverFlow coverFlow;
    private MovieAdapter movieAdapter;
    private List<Movie> movieList = new ArrayList<>();
    private TextSwitcher mTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dayNightSwitch = (DayNightSwitch)findViewById(R.id.dayNight);
        background_view = findViewById(R.id.background_view);

        dayNightSwitch.setDuration(450);
        dayNightSwitch.setListener(new DayNightSwitchListener() {
            @Override
            public void onSwitch(boolean isNight) {
                if(isNight)
                {
                    Toast.makeText(MainActivity.this,"Night mode", Toast.LENGTH_SHORT).show();
                    background_view.setAlpha(1f);
                }
                else
                {
                    Toast.makeText(MainActivity.this,"Day mode", Toast.LENGTH_SHORT).show();
                    background_view.setAlpha(0f);
                }

            }
        });

        initData();
        mTitle = (TextSwitcher)findViewById(R.id.title);
        mTitle.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                LayoutInflater inflator = LayoutInflater.from(MainActivity.this);
                TextView txt = (TextView)inflator.inflate(R.layout.layout_title,null);
                return txt;
            }
        });
        Animation in = AnimationUtils.loadAnimation(this,R.anim.slide_in_top);
        Animation out = AnimationUtils.loadAnimation(this,R.anim.slide_out_bottom);
        mTitle.setInAnimation(in);
        mTitle.setOutAnimation(out);

        movieAdapter = new MovieAdapter(movieList,this);
        coverFlow = (FeatureCoverFlow)findViewById(R.id.coveFlow);
        coverFlow.setAdapter(movieAdapter);

        coverFlow.setOnScrollPositionListener(new FeatureCoverFlow.OnScrollPositionListener() {
            @Override
            public void onScrolledToPosition(int position) {
                mTitle.setText(movieList.get(position).getName());
            }

            @Override
            public void onScrolling() {

            }
        });
    }

    private void initData() {

        movieList.add(new Movie("The Incredibles","https://i.pinimg.com/originals/46/aa/97/46aa970cfba1b5a1586da4600c2488e5.jpg"));
        movieList.add(new Movie("The Boss Baby","https://fanart.tv/fanart/movies/295693/movieposter/the-boss-baby-585c52a748f3a.jpg"));
        movieList.add(new Movie("Zootopia","https://lumiere-a.akamaihd.net/v1/images/movie_poster_zootopia_866a1bf2.jpeg?region=0%2C0%2C300%2C450"));
        movieList.add(new Movie("Kung Fu Panda 3","https://i.pinimg.com/originals/eb/f6/85/ebf6859b002ed5773682f22e8e1dd704.jpg"));
        movieList.add(new Movie("Moana","https://images-na.ssl-images-amazon.com/images/I/71Vq7L0ZBwL._SY550_.jpg"));
        movieList.add(new Movie("Despicable Me 3","http://ventsmagazine.com/wp-content/uploads/2017/06/dp3-1.jpg"));
        movieList.add(new Movie("Finding Dory","https://artsentercapecharles.org/wp-content/uploads/2016/07/dory.jpg"));
        movieList.add(new Movie("Megamind","http://www.wallcoo.net/movie/2010_11_Megamind/wallpapers/1600x1200/1_06_group_02.jpg"));
        movieList.add(new Movie("Smurfs","http://www.filmico.tv/wp-content/uploads/2017/07/170809_front.jpg"));
        movieList.add(new Movie("Bolt","https://image.tmdb.org/t/p/original/WkDi33wUCHWqdgFrH2uR1POhYR.jpg"));

    }
}
