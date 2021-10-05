package com.educapyoficial.educapy.adapters;

import android.content.Context;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.educapyoficial.educapy.R;
import com.educapyoficial.educapy.models.CursosModel;
import com.educapyoficial.educapy.models.EducapyModelUser;
import com.educapyoficial.educapy.models.EducapyModelUserProfesor;

import java.util.ArrayList;
import java.util.List;

public class AdapterListInbox2 extends RecyclerView.Adapter<AdapterListInbox2.ViewHolder> {
    private Context ctx;

    public List<CursosModel> getItems() {
        return items;
    }

    public void setItems(List<CursosModel> items) {
        this.items = items;
    }

    private List<CursosModel> items;
    private OnClickListener onClickListener = null;

    private SparseBooleanArray selected_items;
    private int current_selected_idx = -1;

    public EducapyModelUserProfesor uidProfesor;
    private boolean primeraVez = true;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView from, email, message, date, image_letter;
        public ImageView image;
        public RelativeLayout lyt_checked, lyt_image;
        public View lyt_parent;

        public ViewHolder(View view) {
            super(view);
            from = (TextView) view.findViewById(R.id.from);
            email = (TextView) view.findViewById(R.id.email);
            message = (TextView) view.findViewById(R.id.message);
            //date = (TextView) view.findViewById(R.id.date);
            image_letter = (TextView) view.findViewById(R.id.image_letter);
            image = (ImageView) view.findViewById(R.id.image);
            lyt_checked = (RelativeLayout) view.findViewById(R.id.lyt_checked);
            lyt_image = (RelativeLayout) view.findViewById(R.id.lyt_image);
            lyt_parent = (View) view.findViewById(R.id.lyt_parent);
        }
    }

    public AdapterListInbox2(Context mContext, List<CursosModel> items, EducapyModelUserProfesor uidProfesor) {
        this.ctx = mContext;
        this.items = items;
        selected_items = new SparseBooleanArray();
        this.uidProfesor = uidProfesor;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_inbox, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final CursosModel item = items.get(position);
        // displaying text view data
        holder.from.setText(item.getCursos());
        holder.email.setText("");
        holder.message.setText("");
        //holder.date.setText(inbox.date);
        //holder.image_letter.setText(inbox.from.substring(0, 1));

        holder.lyt_parent.setActivated(selected_items.get(position, false));
        holder.lyt_parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (onClickListener == null) return;
                onClickListener.onItemClick(v, item, position);
            }
        });

        holder.lyt_parent.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (onClickListener == null) return false;
                onClickListener.onItemLongClick(v, item, position);
                return true;
            }
        });

        toggleCheckedIcon(holder, position);


        displayImage(holder, item);



    }

    private void displayImage(ViewHolder holder, CursosModel inbox) {
        //if (inbox.image != null) {
        //     Tools.displayImageRound(ctx, holder.image, inbox.image);
        //     holder.image.setColorFilter(null);
        //     holder.image_letter.setVisibility(View.GONE);
        // } else {
        holder.image.setImageResource(R.drawable.shape_circle);
        //holder.image.setColorFilter(inbox.color);
        holder.image_letter.setVisibility(View.VISIBLE);
        // }
    }

    private void toggleCheckedIcon(ViewHolder holder, int position) {
        if (selected_items.get(position, false)) {
            holder.lyt_image.setVisibility(View.GONE);
            holder.lyt_checked.setVisibility(View.VISIBLE);
            if (current_selected_idx == position) resetCurrentIndex();
        } else {
            holder.lyt_checked.setVisibility(View.GONE);
            holder.lyt_image.setVisibility(View.VISIBLE);
            if (current_selected_idx == position) resetCurrentIndex();
        }
      }

    public CursosModel getItem(int position) {
        return items.get(position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void toggleSelection(int pos) {
        current_selected_idx = pos;
        if (selected_items.get(pos, false)) {
            selected_items.delete(pos);
        } else {
            selected_items.put(pos, true);
        }
        notifyItemChanged(pos);
    }

    public void clearSelections() {
        selected_items.clear();
        notifyDataSetChanged();
    }

    public int getSelectedItemCount() {
        return selected_items.size();
    }

    public List<Integer> getSelectedItems() {
        List<Integer> items = new ArrayList<>(selected_items.size());
        for (int i = 0; i < selected_items.size(); i++) {
            items.add(selected_items.keyAt(i));
        }
        return items;
    }

    public void setItemChecked(SparseBooleanArray sparseBooleanArray){
        this.selected_items = sparseBooleanArray;
    }

    public void removeData(int position) {
        items.remove(position);
        resetCurrentIndex();
    }

    private void resetCurrentIndex() {
        current_selected_idx = -1;
    }

    public interface OnClickListener {
        void onItemClick(View view, CursosModel obj, int pos);

        void onItemLongClick(View view, CursosModel obj, int pos);
    }
}
