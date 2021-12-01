package id.ac.umn.digi_pam;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class DaftarDataPamAdapter extends RecyclerView.Adapter<DaftarDataPamAdapter.PamDataViewHolder> {

    private ArrayList<ListPam> listDataPam;
    private LayoutInflater inflater;
    private Context currContext;

    public DaftarDataPamAdapter(Context context
        , ArrayList<ListPam> listDataPam) {
        currContext = context;
        this.listDataPam = listDataPam;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public PamDataViewHolder onCreateViewHolder(@NonNull ViewGroup parent
            , int viewType) {
        View view = inflater.inflate(R.layout.data_pam_item_layout, parent
            , false);
        return new PamDataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PamDataViewHolder holder, int position) {
        ListPam listPam = listDataPam.get(position);
        holder.tvDataPamItemNoPam.setText(listPam.getNomorPam());

        holder.tvDataPamItemJumlahPemakaian.setText(
            "Jumlah pemakaian (m3): " + listPam.getJumlahPemakaian());

        holder.tvDataPamItemAlamat.setText(
            "Alamat: " + listPam.getAlamat());
    }

    @Override
    public int getItemCount() {
        return listDataPam.size();
    }

    public class PamDataViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener{

        private TextView tvDataPamItemNoPam;
        private TextView tvDataPamItemJumlahPemakaian;
        private TextView tvDataPamItemAlamat;
        private Button btnDataPamItemDetail;

        public PamDataViewHolder(@NonNull View itemView) {
            super(itemView);
            tvDataPamItemNoPam
                = itemView.findViewById(R.id.tvDataPamItemNoPam);
            tvDataPamItemJumlahPemakaian
                = itemView.findViewById(R.id.tvDataPamItemJumlahPemakaian);
            tvDataPamItemAlamat
                = itemView.findViewById(R.id.tvDataPamItemAlamat);
            btnDataPamItemDetail
                = itemView.findViewById(R.id.btnDataPamItemDetail);

            btnDataPamItemDetail.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            // Pindah ke halaman detail data PAM:
            int posisi = getLayoutPosition();
            ListPam listPam = listDataPam.get(posisi);

            Intent detailDataPam = new Intent(currContext
                , DetailDataPamActivity.class);

            Bundle bundle = new Bundle();
            bundle.putSerializable("dataPam", listPam);
            detailDataPam.putExtras(bundle);
            currContext.startActivity(detailDataPam);
        }
    }
}
