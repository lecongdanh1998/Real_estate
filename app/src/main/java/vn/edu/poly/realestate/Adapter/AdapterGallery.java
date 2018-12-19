package vn.edu.poly.realestate.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import vn.edu.poly.realestate.Model.ContructorGallery;
import vn.edu.poly.realestate.Model.RetrofitClient.InfodataProvincesDropdown.DataAddress;
import vn.edu.poly.realestate.Model.RetrofitClient.RatingContructor.RatingContructor;
import vn.edu.poly.realestate.Model.RetrofitClient.UpLoadImage.InfodataUploadImage;
import vn.edu.poly.realestate.R;

public class AdapterGallery extends BaseAdapter {
    LayoutInflater inflater ;
    Context context;
    ArrayList<ContructorGallery> arrayList;

    public AdapterGallery(Context context, ArrayList<ContructorGallery> arrayList) {
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.custom_gallery,null);
            viewHolder.imageView = convertView.findViewById(R.id.imgThumbGirdview);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ContructorGallery contructor = arrayList.get(position);
        viewHolder.imageView.setImageBitmap(contructor.getImageView());
        return convertView;
    }
    class ViewHolder{
        ImageView imageView;

    }

}