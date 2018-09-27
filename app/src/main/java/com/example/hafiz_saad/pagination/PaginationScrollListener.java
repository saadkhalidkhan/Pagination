package com.example.hafiz_saad.pagination;

import android.content.Context;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

public abstract class PaginationScrollListener extends RecyclerView.OnScrollListener{
    private LinearLayoutManager linearLayoutManager;
    private Context context;
    public PaginationScrollListener(Context context,LinearLayoutManager linearLayoutManager) {
        this.linearLayoutManager = linearLayoutManager;
        this.context = context;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        int visibleItemcount = linearLayoutManager.getChildCount();
        int totalItemcount = linearLayoutManager.getItemCount();
        int firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
        if(!isLoading() && !isLastPage()){
            if((visibleItemcount + firstVisibleItemPosition) >= totalItemcount && firstVisibleItemPosition >= 0){
                loadMoreItems();
            }
        }
        else if( isLastPage()){
//            Toast.makeText(context, "no more data!", Toast.LENGTH_SHORT).show();
        }

    }

    protected abstract void loadMoreItems();
    public abstract int getTotalPageCount();
    public abstract boolean isLastPage();
    public abstract boolean isLoading();
}

