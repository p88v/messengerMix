package com.example.messangermix;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationViewModel extends ViewModel {

    private MutableLiveData<String> error = new MutableLiveData();
    private MutableLiveData<FirebaseUser> user = new MutableLiveData();
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference userReference;



    private FirebaseAuth auth;
    public RegistrationViewModel() {
        auth = FirebaseAuth.getInstance();
        auth.addAuthStateListener(new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (firebaseAuth.getCurrentUser() != null){
                    user.setValue(firebaseAuth.getCurrentUser());
                }
            }
        });
        firebaseDatabase = FirebaseDatabase.getInstance();
        userReference = firebaseDatabase.getReference("Users");
    }

    public LiveData<FirebaseUser> getUser() {
        return user;
    }

    public
    LiveData getError() {
        return error;
    }

    public void singUp(
            String email,
            String password,
            String name,
            String lastName,
            int age
    ){
            auth.createUserWithEmailAndPassword(email, password)
                    .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                           FirebaseUser firebaseUser = authResult.getUser();
                            if (firebaseUser == null){
                                return;
                            }
                            User user = new User(
                                    firebaseUser.getUid(),
                                    name,
                                    lastName,
                                    age,
                                    false

                            );
                            userReference.child(user.getId()).setValue(user);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            error.setValue(e.getMessage());
                        }
                    });
    }
}
