package id.ac.umn.digi_pam;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class DaftarDataPamAdapter extends RecyclerView.Adapter<DaftarDataPamAdapter.PamDataViewHolder> {
    @NonNull
    @Override
    public PamDataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull PamDataViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class PamDataViewHolder extends RecyclerView.ViewHolder {
        public PamDataViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
