package com.example.uza;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by Stephen Muraya on 12/31/2018.
 */

public class CustomSettingsAdapter extends ArrayAdapter<String> {
    private final Activity context;
    private final String[] settings_text;
    private final Integer[] images;

    public CustomSettingsAdapter(Activity context, String[] settings_text, Integer[] images){
        super(context, R.layout.settings_design_view, settings_text);

        this.context = context;
        this.settings_text = settings_text;
        this.images = images;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater= context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.settings_design_view, null, true);

        TextView settings_text_x = rowView.findViewById(R.id.tv_settings_text);
        settings_text_x.setText(settings_text[position]);

        ImageView settings_image_x = rowView.findViewById(R.id.settings_text_image);

        Picasso.get()
                .load(images[position])
                .error(images[position])
                .into(settings_image_x);

        return rowView;
    }
}
