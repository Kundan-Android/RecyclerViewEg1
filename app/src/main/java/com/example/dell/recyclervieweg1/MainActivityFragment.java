package com.example.dell.recyclervieweg1;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {
    //11.  declare all requere varibles
    EditText et,et2;
    Button b;
    RecyclerView recyclerView;
    ArrayList<Movie>al;
    MyAdapter m;
    int count = 0;
    // 8. create an inner class for fragment adapter
    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>
    {

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            //load row.xml and pass xml to below holder
            View v = getActivity().getLayoutInflater().inflate(R.layout.row,parent,false);
            //parent of row --> recyclerView
            // false--> dont again load and pass  row;
            ViewHolder viewHolder = new ViewHolder(v);
           // return viewHolder
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            //based on position load data from array list(source)
            Movie movie = al.get(position);
            //fill data onto above view holder
            holder.tv1.setText(movie.getSno());
            holder.tv2.setText(movie.getActor());
            holder.tv3.setText(movie.getMovie());

        }

        @Override
        public int getItemCount() {
            return al.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder
        {
            // contains row.xml
            public TextView tv1,tv2,tv3;
            public ViewHolder(View itemView) {
                super(itemView);
                tv1 = (TextView) itemView.findViewById(R.id.textView10);
                tv2 = (TextView) itemView.findViewById(R.id.textView20);
                tv3 = (TextView) itemView.findViewById(R.id.textView30);
            }
        }
    }
    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v =  inflater.inflate(R.layout.fragment_main, container, false);
        et = (EditText) v.findViewById(R.id.textview1);
        et2 = (EditText) v.findViewById(R.id.textview2);
        b = (Button) v.findViewById(R.id.button1);
        recyclerView = (RecyclerView) v.findViewById(R.id.recyclerview1);
        al =  new ArrayList<Movie>();
        m = new MyAdapter();
        recyclerView.setAdapter(m);// link b/t recyclerview and adapter
     /*   //prepare a layout manager and link b/t recycler view
        //i wil prepare LinearLayoutManager for recyclerview
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(),
                                        LinearLayoutManager.HORIZONTAL,
                                        false);// true means last and right hand side way  */
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(),
                                            2);

        // link recyclerview with linear layout manager
        recyclerView.setLayoutManager(gridLayoutManager);
        //implement button cleck listner for adding movie object to arraylist
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                count++;
                String actor = et.getText().toString();
                String moviewname = et2.getText().toString();
                Movie myMovie = new Movie();
                myMovie.setActor(actor);
                myMovie.setMovie(moviewname);
                myMovie.setSno(""+count);
                al.add(myMovie);
                m.notifyItemInserted(count-1);
                et.setText("");
                et2.setText("");
                et.requestFocus();
            }
        });
        return v;
    }
}
