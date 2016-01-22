package me.chunyu.spike.springrainnews.uis.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.chunyu.spike.springrainnews.NewsApplication;
import me.chunyu.spike.springrainnews.R;
import me.chunyu.spike.springrainnews.mvp.models.AvengersCharacter;

/**
 * 主页列表的适配器
 * <p>
 * Created by wangchenlong on 16/1/22.
 */
public class MainListAdapter extends RecyclerView.Adapter<MainListAdapter.MainListViewHolder> {

    @Inject Context mAppContext;

    private List<AvengersCharacter> mCharacters;

    public MainListAdapter() {
        mCharacters = new ArrayList<>();
        NewsApplication.component().inject(this);
    }

    public void setCharacters(List<AvengersCharacter> characters) {
        mCharacters = characters;
        notifyDataSetChanged();
    }

    @Override
    public MainListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_main_list, parent, false);
        MainListClickListener listener = new MainListClickListener() {
            @Override public void onClick(int position) {
                Toast.makeText(mAppContext, "序号: " + position, Toast.LENGTH_SHORT).show();
            }
        };
        return new MainListViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(MainListViewHolder holder, int position) {
        holder.bindTo(mCharacters.get(position));
    }

    @Override public int getItemCount() {
        return mCharacters.size();
    }

    public class MainListViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.ml_item_iv_avenger_thumb) ImageView mIvAvengerThumb;
        @Bind(R.id.ml_item_tv_avenger_title) TextView mTvAvengerTitle;

        public MainListViewHolder(View itemView, MainListClickListener listener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            bindListener(itemView, listener);
        }

        public void bindTo(AvengersCharacter character) {
            Glide.with(mAppContext)
                    .load(character.getThumbImage())
                    .placeholder(R.drawable.image_not_available)
                    .crossFade()
                    .into(mIvAvengerThumb);

            mTvAvengerTitle.setText(character.getName());
        }

        private void bindListener(View itemView, final MainListClickListener listener) {
            itemView.setOnClickListener(v -> listener.onClick(getAdapterPosition()));
        }
    }

    // 点击位置监听
    public interface MainListClickListener {
        void onClick(int position);
    }
}
