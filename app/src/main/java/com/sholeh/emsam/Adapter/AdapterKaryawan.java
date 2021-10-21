package com.sholeh.emsam.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.sholeh.emsam.Model.ItemKaryawan;
import com.sholeh.emsam.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class AdapterKaryawan extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<ItemKaryawan> karyawan_models;
    private Context mContext;
    private String TAG = "karyawan_adapter";

    private ArrayList<CardView> addrlayoutsList=  new ArrayList<>();
    private ArrayList<ImageView> imagelist = new ArrayList<>();

    public AdapterKaryawan(Context context, List<ItemKaryawan> adapterKaryawans) {
        this.karyawan_models = adapterKaryawans;
        this.mContext = context;
    }

    private class KaryawanItemView extends RecyclerView.ViewHolder {
        TextView tvxNama,tvxJabatan, tvxNoHP;
        ImageView image_profil;
        CardView cvKaryawan;


        public KaryawanItemView(View itemView) {
            super(itemView);
            tvxNama = itemView.findViewById(R.id.tv_namap);
            tvxJabatan =  itemView.findViewById(R.id.tv_jabatan);
            tvxNoHP =  itemView.findViewById(R.id.tv_nohp);
            image_profil = itemView.findViewById(R.id.imgp);
            cvKaryawan = itemView.findViewById(R.id.card_karyawan);


        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_karyawan, parent,false);
        //  Log.e(TAG, "  view created ");
        return new KaryawanItemView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull RecyclerView.ViewHolder holder, int position) {

        final ItemKaryawan model =  karyawan_models.get(position);

        String nama = model.getNamaPekerja();
        String jabatan = model.getJabatan();
        String kontak = model.getNoHp();

        ((KaryawanItemView) holder).tvxNama.setText(nama);
        ((KaryawanItemView) holder).tvxJabatan.setText(jabatan);
        ((KaryawanItemView) holder).tvxNoHP.setText(kontak);

        ((KaryawanItemView) holder).cvKaryawan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "klik", Toast.LENGTH_SHORT).show();
//                Intent intent1 = new Intent(mContext, DetailPenerimaBansos.class);
//                intent1.putExtra("id_warga", model.getIdWarga());
//                mContext.startActivity(intent1);
            }
        });


    }

    @Override
    public int getItemCount() {
        return karyawan_models.size();
    }
}
