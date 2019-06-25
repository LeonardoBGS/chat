package com.google.firebase.udacity.friendlychat;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MessageAdapter extends ArrayAdapter<FriendlyMessage> {
    private String usuario;
    private LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.MATCH_PARENT);

    public MessageAdapter(Context context, int resource, List<FriendlyMessage> objects) {
        super(context, resource, objects);
    }

    public void setUsuario(String name){
        usuario = name;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.item_message, parent, false);
        }

        LinearLayout FLL = (LinearLayout) convertView.findViewById(R.id.FrontLinearLayout);

        ImageView photoImageView = (ImageView) convertView.findViewById(R.id.photoImageView);
        TextView messageTextView = (TextView) convertView.findViewById(R.id.messageTextView);
        TextView authorTextView = (TextView) convertView.findViewById(R.id.nameTextView);

        FriendlyMessage message = getItem(position);

        boolean isPhoto = message.getPhotoUrl() != null;
        if (isPhoto) {
            messageTextView.setVisibility(View.GONE);
            photoImageView.setVisibility(View.VISIBLE);
            Glide.with(photoImageView.getContext())
                    .load(message.getPhotoUrl())
                    .into(photoImageView);
        } else {
            messageTextView.setVisibility(View.VISIBLE);
            photoImageView.setVisibility(View.GONE);
            messageTextView.setText(message.getText());
        }

        if(message.getName().equals(this.usuario)){
            FLL.setGravity(Gravity.RIGHT);
            //Color.rgb(169,247,144));
            messageTextView.setMinWidth(350);
            messageTextView.setGravity(Gravity.RIGHT);
        }
        else{
            FLL.setGravity(Gravity.LEFT);
            //messageTextView.setBackgroundColor(Color.TRANSPARENT);
            messageTextView.setGravity(Gravity.LEFT);
        }

        /*
        Log.d("MA","------------Mess Adap-----------");
        Log.d("M Name",message.getName());
        Log.d("user",this.usuario);
        Log.d("MSN", message.getText());
        if(message.getName().equals(this.usuario)){
            Log.d("MA","Aplicando settings");
            params.gravity=Gravity.RIGHT;//alinea el view a la derecha desde el parent
            messageTextView.setLayoutParams(params);
            messageTextView.setBackgroundColor(Color.rgb(169,247,144));
            messageTextView.setMinWidth(300);
            messageTextView.setGravity(Gravity.RIGHT);//alinea dentro del elemento

            authorTextView.setLayoutParams(params);
        }
        else{
            params.gravity=Gravity.LEFT;
            messageTextView.setLayoutParams(params);
            messageTextView.setBackgroundColor(Color.TRANSPARENT);
            messageTextView.setGravity(Gravity.LEFT);

            authorTextView.setLayoutParams(params);
        }
        */
        authorTextView.setText(message.getName());


        return convertView;
    }
}
