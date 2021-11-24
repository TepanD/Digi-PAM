package id.ac.umn.digi_pam;

import android.util.Log;

import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DAOListPam {
    private DatabaseReference databaseReference;
    public DAOListPam(){
        FirebaseDatabase db =FirebaseDatabase.getInstance();
        databaseReference = db.getReference();
    }
    public Task<Void> add(ListPam catatan){
        Log.d("debug", catatan.toString());
        return databaseReference.push().setValue(catatan);
    }
}

