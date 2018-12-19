package vn.edu.poly.realestate.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import vn.edu.poly.realestate.Model.ContructorGallery;
import vn.edu.poly.realestate.Model.RetrofitClient.RatingContructor.RatingData;
import vn.edu.poly.realestate.R;

public class AdapterDetailsThamdinhUserRating extends BaseAdapter {
    LayoutInflater inflater;
    Context context;
    ArrayList<RatingData> arrayList;

    public AdapterDetailsThamdinhUserRating(Context context, ArrayList<RatingData> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, final ViewGroup parent) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.custom_lst_details_thamdinh, null);
            viewHolder.imageView = convertView.findViewById(R.id.avatar_user_main);
            viewHolder.name = convertView.findViewById(R.id.txt_user_details_thamdinh);
            viewHolder.time = convertView.findViewById(R.id.txt_time);
            viewHolder.ratingBar = convertView.findViewById(R.id.rb_rating);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        RatingData contructor = arrayList.get(position);
        viewHolder.name.setText(contructor.getName());
        String str = contructor.getCreatedAt().replace("-", "/");
        String str1 = str.substring(0, 10);
        viewHolder.time.setText(str1);
        viewHolder.ratingBar.setRating(Float.parseFloat(contructor.getRating()));
        Picasso.get()
                .load("https://image-us.24h.com.vn/upload/4-2018/images/2018-12-15/qh-1-660-1544887171-646-width660height476.jpg")
                .into(viewHolder.imageView);
        return convertView;
    }

    class ViewHolder {
        CircleImageView imageView;
        TextView name, time;
        RatingBar ratingBar;

    }

}