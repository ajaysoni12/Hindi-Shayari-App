package com.devdroid.hindishayariapp.Adapter;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.devdroid.hindishayariapp.BuildConfig;
import com.devdroid.hindishayariapp.Model.ShayariModal;
import com.devdroid.hindishayariapp.R;

import java.util.ArrayList;

public class AllShayariAdapter extends RecyclerView.Adapter<AllShayariAdapter.ViewHolder> {

    Context context;
    ArrayList<ShayariModal> shayariList;

    public AllShayariAdapter(Context context, ArrayList<ShayariModal> shayariList) {
        this.context = context;
        this.shayariList = shayariList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_shayari, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.txtItemShayari.setText(shayariList.get(position).getData().toString());

        if (position % 6 == 0)
            holder.llBackground.setBackgroundResource(R.drawable.gradient_1);
        else if (position % 6 == 1)
            holder.llBackground.setBackgroundResource(R.drawable.gradient_2);
        else if (position % 6 == 2)
            holder.llBackground.setBackgroundResource(R.drawable.gradient_3);
        else if (position % 6 == 3)
            holder.llBackground.setBackgroundResource(R.drawable.gradient_4);
        else if (position % 6 == 4)
            holder.llBackground.setBackgroundResource(R.drawable.gradient_5);


        holder.btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    Intent shareIntent = new Intent(Intent.ACTION_SEND);
                    shareIntent.setType("text/plain");
                    shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Hindi Shayari App");
                    String shareMessage = "\n" + shayariList.get(position).getData() + "\n";
                    shareMessage = shareMessage + "https://play.google.com/store/apps/details?id=" + BuildConfig.APPLICATION_ID + "\n\n";
                    shareIntent.putExtra(Intent.EXTRA_TEXT, shareMessage);
                    context.startActivity(Intent.createChooser(shareIntent, "choose one"));
                } catch (Exception e) {
                    //e.toString();
                }
            }
        });

        holder.btnCopy.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                ClipboardManager clipboard = null;
                clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip = ClipData.newPlainText("label", shayariList.get(position).getData().toString());
                clipboard.setPrimaryClip(clip);
                Toast.makeText(context, "Copied Successfully", Toast.LENGTH_SHORT).show();
            }
        });
//
        holder.btnWhatsApp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, shayariList.get(position).getData().toString());
                sendIntent.setType("text/plain");
                sendIntent.setPackage("com.whatsapp");
                context.startActivity(Intent.createChooser(sendIntent, shayariList.get(position).getData().toString()));
                context.startActivity(sendIntent);
                //opens the portfolio details class
            }
        });
//
    }

    @Override
    public int getItemCount() {
        return shayariList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtItemShayari;
        LinearLayout llBackground;
        ImageView btnCopy, btnShare, btnWhatsApp;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtItemShayari = itemView.findViewById(R.id.txtItemShayari);
            llBackground = itemView.findViewById(R.id.llBackground);
            btnCopy = itemView.findViewById(R.id.copy_btn);
            btnShare = itemView.findViewById(R.id.share_btn);
            btnWhatsApp = itemView.findViewById(R.id.whatsApp_btn);

        }
    }
}
