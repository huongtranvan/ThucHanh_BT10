package com.example.bt10sqlite.Control;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bt10sqlite.Model.Computer;
import com.example.bt10sqlite.R;

import java.util.List;

public class ComputerAdapter extends RecyclerView.Adapter<ComputerAdapter.ViewHolder> {
    //Dữ liệu hiện thị là danh sách sinh viên
    private List<Computer> mComputer;
    // Lưu Context để dễ dàng truy cập
    private Context mContext;

    public ComputerAdapter(List<Computer> computers, Context mContext) {
        this.mComputer = computers;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ComputerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView;
        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_computeritem, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ComputerAdapter.ViewHolder holder, int position) {
        Computer computer =mComputer.get(position);
        holder.studentname.setText(computer.getIdComputer());
        holder.birthyear.setText(computer.getNameComputer());
    }

    @Override
    public int getItemCount() {
        if (mComputer!=null)
        return mComputer.size();
        else return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private View itemview;
        public TextView studentname;
        public TextView birthyear;
        public Button detail_button;

        public ViewHolder(View itemView) {
            super(itemView);
            itemview = itemView;
            studentname = itemView.findViewById(R.id.studentname);
            birthyear = itemView.findViewById(R.id.birthyear);
            detail_button = itemView.findViewById(R.id.detail_button);

            //Xử lý khi nút Chi tiết được bấm
            detail_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(view.getContext(),
                                    studentname.getText() +" | "
                                            + " Demo function", Toast.LENGTH_SHORT)
                            .show();
                }
            });
        }
    }


}
