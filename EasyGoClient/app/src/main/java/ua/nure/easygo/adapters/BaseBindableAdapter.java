package ua.nure.easygo.adapters;

import android.content.Context;
import android.database.DataSetObserver;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;

import java.util.List;

/**
 * Created by Oleg on 19.10.2016.
 */

public class BaseBindableAdapter<T> implements ListAdapter {
    protected LayoutInflater layoutInflater;
    private final List<T> items;
    private final int layoutId;
    private final int bindVariableId;

    public BaseBindableAdapter(Context context, List<T> items, @LayoutRes int layoutId, int bindVariableId) {
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.items = items;

        this.layoutId = layoutId;
        this.bindVariableId = bindVariableId;
    }

    @Override
    public boolean areAllItemsEnabled() {
        return true;
    }

    @Override
    public boolean isEnabled(int position) {
        return true;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public T getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        ViewDataBinding binding;
        if (v == null) {
            binding = DataBindingUtil.inflate(layoutInflater, layoutId, parent, false);
            v = binding.getRoot();

        } else {
            binding = DataBindingUtil.getBinding(v);
        }

        binding.setVariable(bindVariableId, getItem(position));

        return v;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return items.isEmpty();
    }
}
