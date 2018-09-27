package com.example.hafiz_saad.pagination;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.List;

public class PaginationActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
//    private ProgressBar progressBar;
    public PaginationAdapter paginationAdapter;
    private static final int PAGE_START = 0;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    private int TOTAL_PAGES = 5;
    private int currentPage = PAGE_START;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pagination);
        recyclerView = (RecyclerView) findViewById(R.id.recycle);
//        progressBar = (ProgressBar) findViewById(R.id.progress);
        linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(linearLayoutManager);
        paginationAdapter = new PaginationAdapter(getApplicationContext());
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(paginationAdapter);
        recyclerView.addOnScrollListener(new PaginationScrollListener(getApplicationContext(),linearLayoutManager){
            @Override
            protected void loadMoreItems() {
                isLoading = true;
                currentPage += 1; //Increment page index to next one
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadNextPage();
                    }
                },3000);
            }

            @Override
            public int getTotalPageCount() {
                return TOTAL_PAGES;
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }

        } );

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                loadFirstPage();
            }
        },3000);
    }

    private void loadFirstPage(){
        List<Movie> movies = Movie.createMovie(paginationAdapter.getItemCount());
//        progressBar.setVisibility(View.GONE);
        paginationAdapter.addAll(movies);
        if(currentPage <= TOTAL_PAGES) paginationAdapter.addLoadingFooter();
        else {
            isLastPage = true;
//            isLoading = true;
        }
    }

    private void loadNextPage(){
        List<Movie> movies = Movie.createMovie(paginationAdapter.getItemCount());
        paginationAdapter.addLoadingFooter();
        isLoading = false;
        paginationAdapter.addAll(movies);

        if(currentPage != TOTAL_PAGES ) paginationAdapter.addLoadingFooter();
        else {
            isLastPage = true;
//            isLoading = true;
        }
    }
}
