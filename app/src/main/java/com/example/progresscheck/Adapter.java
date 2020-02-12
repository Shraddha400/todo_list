package com.example.progresscheck;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.Viewholder> {
    //implements methods
    private List<ModelClass>modelClassList;

    public Adapter(List<ModelClass> modelClassList) {
        this.modelClassList = modelClassList;
    }

    @NonNull
    @Override
    //layout creats frist

    public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent,false);
        //view created from above and then send to viewholder class
        return new Viewholder(view);
    }

    @Override
    //sets data layout
    public void onBindViewHolder(@NonNull Viewholder holder, int position) {
        int resource= modelClassList.get(position).getImageResource();
        String title=modelClassList.get(position).getTitle();
        String body=modelClassList.get(position).getBody();

        Button btn=modelClassList.get(position).getBtn();
        holder.setData(resource,title,body,btn);

    }

    @Override
    //number of layout to show the number of items
    public int getItemCount() {
        return modelClassList.size();
    }



    class Viewholder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView title;
        private TextView body;
        private Button btn;

        public Viewholder(@NonNull View itemView) {
            super(itemView);
            //access all variables  from intem we extract all data
            imageView=itemView.findViewById(R.id.image_view);
            title=itemView.findViewById(R.id.title);
            body=itemView.findViewById(R.id.body);

            btn=itemView.findViewById(R.id.done);

        }
        //creat method to set data to above variables
        private void setData(int resource,String titletext,String bodytext,Button btnn ){
            imageView.setImageResource(resource);
            title.setText(titletext);
            body.setText(bodytext);
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    btn.setText("finished");
                    btn.getText();
                    btn.setBackgroundColor(1);
                    return;

                }
            });
        }
    }
}
