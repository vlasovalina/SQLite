package com.example.user.sqlite.UI.Adapter;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.user.sqlite.Model.Person;
import com.example.user.sqlite.R;

import java.util.ArrayList;

public class AdapterRecyclerView extends RecyclerView.Adapter<Item_View_Holder>
{
    private Context mContext;
    private ArrayList<Person> listPerson;

    @Override
    public Item_View_Holder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_list_item, parent, false);
        return new Item_View_Holder(view);
    }

    public AdapterRecyclerView(ArrayList<Person> listPerson, Context mContext)
    {
        this.listPerson = listPerson;
        this.mContext = mContext;
    }

    @Override
    public void onBindViewHolder(Item_View_Holder holder, int position)
    {
        Person person = (Person)listPerson.get(position);
        holder.getTextViewId().setText(String.valueOf(person.getId()));
        holder.getImageViewPhoto().setImageBitmap(BitmapFactory.decodeByteArray( person.getImg(), 0, person.getImg().length));
        holder.getTextViewName().setText(person.getName());
        holder.getTextViewSurName().setText(person.getSurname());
        holder.getTextViewAge().setText(String.valueOf(person.getAge()));
    }

    @Override
    public int getItemCount()
    {
        return listPerson.size();
    }
}
