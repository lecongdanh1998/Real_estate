package vn.edu.poly.realestate.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import vn.edu.poly.realestate.Model.ModelFilter.ContructoFilterChonhuong;
import vn.edu.poly.realestate.Model.ModelNotification.ModelContructor;
import vn.edu.poly.realestate.R;

public class AdapterNotification extends BaseAdapter {
    LayoutInflater inflater ;
    Context context;
    ArrayList<ModelContructor> arrayList;

    public AdapterNotification(Context context, ArrayList<ModelContructor> arrayList) {
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
            convertView = inflater.inflate(R.layout.custom_lst_notication,null);
            viewHolder.imageView = convertView.findViewById(R.id.img_avata);
            viewHolder.txttitle = convertView.findViewById(R.id.txt_title);
            viewHolder.txttime = convertView.findViewById(R.id.txt_time);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ModelContructor contructor = arrayList.get(position);
        viewHolder.txttitle.setText(contructor.getTitle());
        viewHolder.txttime.setText(contructor.getTime());
        Picasso.get()
                .load(contructor.getImage())
                .into(viewHolder.imageView);
        return convertView;
    }
    class ViewHolder{
        CircleImageView imageView;
        TextView txttitle,txttime;
    }
}
