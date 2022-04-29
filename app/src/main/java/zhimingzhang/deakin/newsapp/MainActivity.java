package zhimingzhang.deakin.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerViewH;
    RecyclerView recyclerViewV;
    RecyclerViewAdapterH RecyclerViewAdapterHorizontal;
    RecyclerViewAdapterV RecyclerViewAdapterVertical;
    List<NewsData> newsDataList = new ArrayList<>();
    Boolean started;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        started = false;

        String[] newsName = {"A MEWS", "B News", "C News", "D NEWS"};

        NewsData news = new NewsData("", findViewById(R.id.genericNewsButton1), 0);
        newsDataList.add(news);

        setContentView(R.layout.activity_main);
        recyclerViewH = findViewById(R.id.recyclerViewH);
        RecyclerViewAdapterHorizontal = new RecyclerViewAdapterH(newsDataList, this);
        recyclerViewH.setAdapter(RecyclerViewAdapterHorizontal);
        recyclerViewH.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    }

    public void selectFragment(View view) {
        Fragment fragment = null;
        Fragment fDefault = new fragment_news_default();
        Fragment fNews1 = new fragment_news1();
        Fragment fNews2 = new fragment_news2();
        Fragment fNews3 = new fragment_news3();
        Fragment fNews4 = new fragment_news4();

        if (started == false){
            newsDataList = new ArrayList<>();
            String[] newsName = {"A MEWS", "B News", "C News", "D NEWS"};
            ImageButton iB1 = findViewById(R.id.genericNewsButton1);
            ImageButton iB2 = findViewById(R.id.genericNewsButton2);
            ImageButton iB3 = findViewById(R.id.genericNewsButton3);
            ImageButton iB4 = findViewById(R.id.genericNewsButton4);
            ImageButton[] imageButtons = {iB1, iB2, iB3, iB4};

            for (int i=0; i< newsName.length; i++)
            {
                NewsData news = new NewsData(newsName[i], imageButtons[i], i);
                newsDataList.add(news);
            }
            recyclerViewH.setVisibility(View.GONE);

            recyclerViewV = findViewById(R.id.recyclerViewV);
            RecyclerViewAdapterVertical = new RecyclerViewAdapterV(newsDataList, this);
            recyclerViewV.setAdapter(RecyclerViewAdapterVertical);
            recyclerViewV.setLayoutManager(new LinearLayoutManager(this));
        }

        started = true;

        switch(view.getId())
        {
            case R.id.newsArticleP1:
            case R.id.genericNewsButton1:
                fragment = fNews1;
                break;
            case R.id.newsArticleP2:
            case R.id.genericNewsButton2:
                fragment = fNews2;
                break;
            case R.id.newsArticleP3:
            case R.id.genericNewsButton3:
                fragment = fNews3;
                break;
            case R.id.newsArticleP4:
            case R.id.genericNewsButton4:
                fragment = fNews4;
                break;
            default:
                fragment = fDefault;
                break;
        }
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainerView, fragment).commit();
        View hiddenView = findViewById ( R.id.textView ) ; //在hidden_view.xml中hidden_layout是root layout
        if ( null != hiddenView ) {
            ViewGroup parent = ( ViewGroup ) hiddenView.getParent() ;
            parent.removeView ( hiddenView ) ;
        }
        View hiddenView2 = findViewById ( R.id.textView2 ) ; //在hidden_view.xml中hidden_layout是root layout
        if ( null != hiddenView2 ) {
            ViewGroup parent = ( ViewGroup ) hiddenView2.getParent() ;
            parent.removeView ( hiddenView2 ) ;
        }
    }
}