package com.example.madfinalproject;

import static com.example.madfinalproject.PasswordCreator.checkPasswordEquality;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {
    FirebaseAuth auth;
    EditText emailTxt, passwordText1, passwordText2;
    Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        auth = FirebaseAuth.getInstance();
        emailTxt = findViewById(R.id.txtSignUpEmail);
        passwordText1 = findViewById(R.id.txtSignUpPassword1);
        passwordText2 = findViewById(R.id.txtSignUpPassword2);
        saveButton = findViewById(R.id.btnSignInSave);

    }
    public void saveUser(View view){
        String email = emailTxt.getText().toString();
        String password = passwordText1.getText().toString();
        String password2 = passwordText2.getText().toString();
        if(email.equals("")||password.equals(""))
            Toast.makeText(this,"Please enter email and password!",Toast.LENGTH_SHORT).show();
        else if(!checkPasswordEquality(password, password2)){
            Toast.makeText(this,"Passwords are not equal!",Toast.LENGTH_SHORT).show();
        }
        else{
            auth.createUserWithEmailAndPassword(email,password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                @Override
                public void onSuccess(AuthResult authResult) {
                    Toast.makeText(SignUp.this, "User saved!",Toast.LENGTH_SHORT).show();
                    finish();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(SignUp.this,e.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
}