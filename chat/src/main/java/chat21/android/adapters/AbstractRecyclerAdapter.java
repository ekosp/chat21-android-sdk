package chat21.android.adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Custom abstrac adapter.
 * It requires just to create the viewholder and the methods "onBindViewHolder()" and "onCreateViewHolder".
 * It has been designed to support only the recyclerview pattern.
 * TESTED SUCCESSFULLY with RecyclerView v7:26.0.2
 * <p/>
 * Created by stefanodp91 on 26/08/2015.
 */
public abstract class AbstractRecyclerAdapter<T extends Object, U extends RecyclerView.ViewHolder>
        extends RecyclerView.Adapter<U> {
    private Context context;
    private Activity activity;
    private List<T> items;
    private OnRecyclerItemClickListener onRecyclerItemClickListener;
    private OnRecyclerItemLongClickListener onRecyclerItemLongClickListener;

    public AbstractRecyclerAdapter(Context context, List<T> items) {
        this.context = context;
        this.items = items;
    }

    public AbstractRecyclerAdapter(Activity activity, List<T> items) {
        this.activity = activity;
        this.items = items;
    }

    public void setList(List<T> mList) {
        this.items = mList;
    }

    public OnRecyclerItemClickListener getOnRecyclerItemClickListener() {
        return onRecyclerItemClickListener;
    }

    public void setOnRecyclerItemClickListener(OnRecyclerItemClickListener onRecyclerItemClickListener) {
        this.onRecyclerItemClickListener = onRecyclerItemClickListener;
    }

    public OnRecyclerItemLongClickListener getOnRecyclerItemLongClickListener() {
        return onRecyclerItemLongClickListener;
    }

    public void setOnRecyclerItemLongClickListener(OnRecyclerItemLongClickListener onRecyclerItemLongClickListener) {
        this.onRecyclerItemLongClickListener = onRecyclerItemLongClickListener;
    }

    @Override
    public abstract U onCreateViewHolder(ViewGroup parent, int viewType);

    @Override
    public abstract void onBindViewHolder(U holder, final int position);

    /**
     * Return the item in the selected position
     *
     * @param position the item's position
     * @return
     */
    public T getItem(int position) {
        return items.get(position);
    }

    @Override
    public int getItemCount() {
        if (items == null)
            return 0;

        return items.size();
    }

    public Context getContext() {
        return context;
    }

    public Activity getActivity() {
        return activity;
    }

    /**
     * @return the list of items
     */
    public List<T> getItems() {
        List<T> mList = new ArrayList<>();

        if (items != null && items.size() != 0) {
            mList = items;
        }

        return mList;
    }

    /**
     * Remove the item into a specific position
     *
     * @param position the position
     */
    public void remove(int position) {
        if (items != null && items.size() > 0) {
            items.remove(position);
            notifyItemRemoved(position);
        }
    }

    /**
     * add an item into at the top of the list
     *
     * @param item the item to add
     */
    public void insertTop(T item) {
        int position = 0;
        if (item != null) {
            items.add(position, item);
            notifyItemInserted(position);
        }
    }

    /**
     * add an item into at the bottom of the list
     *
     * @param item the item to add
     */
    public void insertBottom(T item) {
        if (items != null) {
            int position = items.size();
            if (item != null) {
                items.add(position, item);
                notifyItemInserted(position);
            }
        }
    }

    /**
     * add an item into at a specific position
     *
     * @param item the item to add
     */
    public void insert(T item, int position) {
        if (items != null) {
            if (item != null) {
                items.add(position, item);
                notifyItemInserted(position);
            }
        }
    }

    /**
     * Clear the list
     */
    public void clear() {
        if (items != null) {
            if (items.size() > 0) {
                items.clear();
            }
            notifyDataSetChanged();
        }
    }

    /**
     * Update an item with a new value
     *
     * @param item     the item
     * @param position the position
     */
    public void update(T item, int position) {
        if (item != null && position >= 0) {
            remove(position);
            insertTop(item);

            // no need to notifyDataSetChanged() because is done by remove and insert
        }
    }

    public interface OnRecyclerItemClickListener<T> extends Serializable {
        void onRecyclerItemClicked(T item, int position);
    }

    public interface OnRecyclerItemLongClickListener<T> extends Serializable {
        void onRecyclerItemLongClicked(T item, int position);
    }
}