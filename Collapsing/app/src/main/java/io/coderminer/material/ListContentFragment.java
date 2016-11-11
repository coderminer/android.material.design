package io.coderminer.material;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


public class ListContentFragment extends Fragment{
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        RecyclerView recyclerView = (RecyclerView)inflater.inflate(R.layout.recycler_view,container,false);
        ContentAdapter adapter = new ContentAdapter(recyclerView.getContext());
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return recyclerView;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView avatar;
        public TextView name;
        public TextView des;
        public ViewHolder(LayoutInflater inflater,ViewGroup parent){
            super(inflater.inflate(R.layout.item_list,parent,false));
            avatar = (ImageView)itemView.findViewById(R.id.list_avatar);
            name = (TextView)itemView.findViewById(R.id.list_title);
            des = (TextView)itemView.findViewById(R.id.list_desc);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent();
                    intent.putExtra(DetailActivity.EXTRA_POSITION,getAdapterPosition());
                    intent.setClass(v.getContext(),DetailActivity.class);
                    v.getContext().startActivity(intent);
                }
            });
        }
    }

    public static class ContentAdapter extends RecyclerView.Adapter<ViewHolder>{
        private static final int LENGTH = 18;
        private String[] names;
        private String[] des;
        private Drawable[] avatars;

        public ContentAdapter(Context context) {
            Resources resources = context.getResources();
            names = resources.getStringArray(R.array.places);
            des = resources.getStringArray(R.array.place_desc);
            TypedArray a = resources.obtainTypedArray(R.array.place_avator);
            avatars = new Drawable[a.length()];
            for(int i = 0;i<a.length();i++){
                avatars[i] = a.getDrawable(i);
            }
            a.recycle();
        }

        @Override
        public int getItemCount() {
            return LENGTH;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            holder.avatar.setImageDrawable(avatars[position % avatars.length]);
            holder.name.setText(names[position % names.length]);
            holder.des.setText(des[position % des.length]);
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new ViewHolder(LayoutInflater.from(parent.getContext()),parent);
        }
    }
}