package com.example.farkas.laundryapp;

        import android.app.Activity;
        import android.content.Context;
        import android.support.v7.widget.RecyclerView;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.TextView;
        import android.widget.Toast;

        import com.example.farkas.laundryapp.database.DataSource;
        import com.example.farkas.laundryapp.model.DataItem;

        import java.io.IOException;
        import java.util.List;

public class DataItemAdapter extends RecyclerView.Adapter<DataItemAdapter.ViewHolder> {

    private List<DataItem> mItems;
    private Context mContext;

    public DataItemAdapter(Context context, List<DataItem> items) {
        this.mContext = context;
        this.mItems = items;
    }

    @Override
    public DataItemAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View itemView = inflater.inflate(R.layout.list_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final DataItemAdapter.ViewHolder holder, final int position)  {
        final DataItem item = mItems.get(position);
        holder.tvName.setText(item.getItemName());
        holder.tvCount.setText(item.getCurrentCount() + "");

        //Short Click
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "" + item.getItemName(), Toast.LENGTH_SHORT).show();
            }
        });

        //Long Click
        //onlongclick -> prompt y/n for delete -> delete -> setuplist
        holder.mView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
               deleteItem(item, position);
                //((Activity)mContext).finish(); //closes app

                return false;
            }
        });



    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvName;
        public TextView tvCount;
        View mView;
        public ViewHolder(View itemView) {
            super(itemView);

            tvName = (TextView) itemView.findViewById(R.id.itemName);
            tvCount = (TextView) itemView.findViewById(R.id.currentCountEntry);
            mView = itemView;
        }
    }

    private void deleteItem(DataItem item, int position){
        DataSource mDataSource;
        mDataSource = new DataSource(mContext);
        mDataSource.open();
        mDataSource.deleteItem(item);
        mDataSource.close();
        Toast.makeText(mContext, "hi", Toast.LENGTH_SHORT).show();
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, getItemCount());
        notifyDataSetChanged();
    }
}