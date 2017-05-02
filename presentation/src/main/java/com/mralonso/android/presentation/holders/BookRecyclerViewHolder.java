package com.mralonso.android.presentation.holders;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mralonso.android.domain.data.Book;
import com.mralonso.android.presentation.R;
import com.mralonso.android.presentation.listeners.BookClickListener;
import com.mralonso.android.presentation.utils.ImageUtils;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;

public class BookRecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    @Bind(R.id.row)
    CardView mHouseRow;

    @Bind(R.id.title)
    TextView mTitle;

    @Bind(R.id.author)
    TextView mAuthor;

    @Bind(R.id.image)
    ImageView mImage;

    private Context mContext;
    private Book mBook;
    private BookClickListener mBookClickListener;

    public BookRecyclerViewHolder(View itemView, BookClickListener bookClickListener) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        mContext = itemView.getContext();
        mBookClickListener = bookClickListener;
        setOnRowClickListener(itemView);
    }

    private void setOnRowClickListener(View itemView) {
        itemView.setOnClickListener(this);
    }

    public void bindBook(final Book book) {
        mBook = book;
        mTitle.setText(book.getTitle());
        mAuthor.setText(getAuthorsSeparatedByComma(book.getAuthors()));
        ImageUtils.setImage(mContext, book.getImage(), mImage);
    }

    private String getAuthorsSeparatedByComma(ArrayList<String> authors){

        if(authors==null) return "";

        String authorsSeparatedByComma = "";
        for(int i=0; i<authors.size(); i++){
            authorsSeparatedByComma += authors.get(i);
            if(i<authors.size()-1) {
                authorsSeparatedByComma += ", ";
            }
        }

        return authorsSeparatedByComma;
    }

    //region OnClickListener

    @Override
    public void onClick(View v) {
        mBookClickListener.onBookSelected(mBook);
    }

    //endregion OnClickListener

}
