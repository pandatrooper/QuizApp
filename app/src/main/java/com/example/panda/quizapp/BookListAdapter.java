package com.example.panda.quizapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by pandatrooper on 6/11/17.
 */

public class BookListAdapter extends BaseAdapter {
    private Context context;
    private  int layout;
    private ArrayList<Book> BooksList;

    public BookListAdapter(Context context, int layout, ArrayList<Book> booksList) {
        this.context = context;
        this.layout = layout;
        this.BooksList = booksList;
    }

    @Override
    public int getCount() {
        return BooksList.size();
    }

    @Override
    public Object getItem(int position) {
        return BooksList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder{
        ImageView imageView;
        TextView txtName, txtPrice;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        View row = view;
        ViewHolder holder = new ViewHolder();

        if(row == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(layout, null);

            holder.txtName = (TextView) row.findViewById(R.id.txtName);
            holder.txtPrice = (TextView) row.findViewById(R.id.txtPrice);
            holder.imageView = (ImageView) row.findViewById(R.id.imgBook);
            row.setTag(holder);
        }
        else {
            holder = (ViewHolder) row.getTag();
        }

        Book book = BooksList.get(position);

        holder.txtName.setText(book.getName());
        holder.txtPrice.setText(book.getPrice());

        byte[] bookImage = book.getImage();
        Bitmap bitmap = BitmapFactory.decodeByteArray(bookImage, 0, bookImage.length);
        holder.imageView.setImageBitmap(bitmap);

        return row;
    }
}
