package View;

import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.payme20.Group;
import com.example.payme20.Member;
import com.example.payme20.R;

public class MemberView extends AppCompatActivity{

    private Member member;
    private Group group;

    public void onAddMemberBtnClick(View view){
        TextView TxtName = findViewById(R.id.edtTxtName);
        TextView TxtPhoneNumber = findViewById(R.id.editTextPhone);
    }

}
