package com.example.rajdeeprao.inclass13;

/**
 * Created by rajdeeprao on 4/24/17.
 */

;import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;

class MyRecyclerViewAdapter extends RealmRecyclerViewAdapter<Note, MyRecyclerViewAdapter.MyViewHolder> {

    public MyRecyclerViewAdapter(@Nullable OrderedRealmCollection<Note> data, boolean autoUpdate) {
        super(data, autoUpdate);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView TitleTV;
        TextView TimeTV;
        TextView PriorityTv;
        CheckBox checkBox;
        public Note data;

        MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.textview);
            deletedCheckBox = (CheckBox) view.findViewById(R.id.checkBox);
        }
    }
}
