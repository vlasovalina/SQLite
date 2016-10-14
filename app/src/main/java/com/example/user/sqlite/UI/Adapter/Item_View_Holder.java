package com.example.user.sqlite.UI.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.user.sqlite.R;

public class Item_View_Holder extends RecyclerView.ViewHolder
{
    private ImageView imageViewPhoto;
    private TextView  textViewId;
    private TextView  textViewName;
    private TextView  textViewSurname;
    private TextView  textViewAge;

    public Item_View_Holder(View itemView)
    {
        super(itemView);
        imageViewPhoto  = (ImageView) itemView.findViewById(R.id.imageViewPhoto);
        textViewId      = (TextView)  itemView.findViewById(R.id.textViewId);
        textViewName    = (TextView)  itemView.findViewById(R.id.textViewName);
        textViewSurname = (TextView)  itemView.findViewById(R.id.textViewSurName);
        textViewAge     = (TextView)  itemView.findViewById(R.id.textViewAge);
    }

    public ImageView getImageViewPhoto() { return imageViewPhoto; }

    public TextView getTextViewId()
    {
        return textViewId;
    }

    public TextView getTextViewName()
    {
        return textViewName;
    }

    public TextView getTextViewSurName()
    {
        return textViewSurname;
    }

    public TextView getTextViewAge()
    {
        return textViewAge;
    }
}
