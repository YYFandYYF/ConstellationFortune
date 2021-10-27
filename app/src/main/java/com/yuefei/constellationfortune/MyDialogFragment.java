package com.yuefei.constellationfortune;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyDialogFragment extends BottomSheetDialogFragment {

    private RecyclerView recyclerView;
    private List<Constellation> constellationList = new ArrayList<>();
    public static Constellation constellation1 = new Constellation("白羊座", R.drawable.baiyang);
    public static Constellation constellation2 = new Constellation("金牛座", R.drawable.jinniu);
    public static Constellation constellation3 = new Constellation("双子座", R.drawable.shuangzi);
    public static Constellation constellation4 = new Constellation("巨蟹座", R.drawable.juxie);
    public static Constellation constellation5 = new Constellation("狮子座", R.drawable.shizi);
    public static Constellation constellation6 = new Constellation("处女座", R.drawable.chunv);
    public static Constellation constellation7 = new Constellation("天秤座", R.drawable.tiancheng);
    public static Constellation constellation8 = new Constellation("天蝎座", R.drawable.tianxie);
    public static Constellation constellation9 = new Constellation("射手座", R.drawable.sheshou);
    public static Constellation constellation10 = new Constellation("摩羯座", R.drawable.mojie);
    public static Constellation constellation11 = new Constellation("水瓶座", R.drawable.shuipin);
    public static Constellation constellation12 = new Constellation("双鱼座", R.drawable.shuangyu);

    private Dialog dialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置原有背景透明
        setStyle(DialogFragment.STYLE_NORMAL,R.style.TransBottomSheet);
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        dialog = super.onCreateDialog(savedInstanceState);
        //设置动画
        dialog.getWindow().setWindowAnimations(R.style.myStyle);
//        dialog.getWindow().getDecorView().setPadding(0, 0, 0, 0);
//        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
//        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
//        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
//        dialog.getWindow().setAttributes(lp);
        return dialog;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pop_window, container, false);
        recyclerView = view.findViewById(R.id.constellation_recyclerView);
        initRecyclerView();
        return view;
    }

    private void initRecyclerView() {
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 3);
        recyclerView.setLayoutManager(layoutManager);
        constellationList.add(constellation1);
        constellationList.add(constellation2);
        constellationList.add(constellation3);
        constellationList.add(constellation4);
        constellationList.add(constellation5);
        constellationList.add(constellation6);
        constellationList.add(constellation7);
        constellationList.add(constellation8);
        constellationList.add(constellation9);
        constellationList.add(constellation10);
        constellationList.add(constellation11);
        constellationList.add(constellation12);
        ConstellationAdapter constellationAdapter = new ConstellationAdapter(constellationList);
        recyclerView.setAdapter(constellationAdapter);
    }

    public interface OnDialogFragmentClickListener {
        void onDialogFragmentClickListener(String name, int image);
    }

    private OnDialogFragmentClickListener onDialogFragmentClickListener;

    public void setOnDialogFragmentClickListener(OnDialogFragmentClickListener onDialogFragmentClickListener) {
        this.onDialogFragmentClickListener = onDialogFragmentClickListener;
    }

    class ConstellationAdapter extends RecyclerView.Adapter<ConstellationAdapter.ViewHolder> {

        private List<Constellation> constellationList;
        private Context context;

        public ConstellationAdapter(List<Constellation> constellationList) {
            super();
            this.constellationList = constellationList;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (context == null) {
                context = parent.getContext();
            }
            View view = LayoutInflater.from(context).inflate(R.layout.constellation_item, parent, false);
            ViewHolder viewHolder = new ViewHolder(view);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Constellation constellation = constellationList.get(viewHolder.getAdapterPosition());
                    //通过接口回调和activity进行通信
                    onDialogFragmentClickListener.onDialogFragmentClickListener(constellation.getName(), constellation.getImage());
                    dismiss();
                }
            });
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            Constellation constellation = constellationList.get(position);
            Glide.with(context).load(constellation.getImage()).into(holder.circleImageView);
            holder.textView.setText(constellation.getName());
        }

        @Override
        public int getItemCount() {
            return constellationList.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {

            CircleImageView circleImageView;
            TextView textView;

            public ViewHolder(View itemView) {
                super(itemView);
                circleImageView = itemView.findViewById(R.id.constellation_item_image);
                textView = itemView.findViewById(R.id.constellation_item_name);
            }
        }

    }
}