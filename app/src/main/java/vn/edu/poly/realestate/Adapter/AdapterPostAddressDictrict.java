package vn.edu.poly.realestate.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import vn.edu.poly.realestate.Model.RetrofitClient.InfodataProvincesDropdown.DataAddress;
import vn.edu.poly.realestate.Model.RetrofitClient.InfodataProvincesDropdown.DataAddressDictrict;
import vn.edu.poly.realestate.R;

public class AdapterPostAddressDictrict extends BaseAdapter {
    LayoutInflater inflater ;
    Context context;
    ArrayList<DataAddressDictrict> arrayList;

    public AdapterPostAddressDictrict(Context context, ArrayList<DataAddressDictrict> arrayList) {
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
            convertView = inflater.inflate(R.layout.custom_spinner,null);
            viewHolder.txttitle = convertView.findViewById(R.id.txt_title_issueby);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        DataAddressDictrict contructor = arrayList.get(position);
        viewHolder.txttitle.setText(contructor.getTitle());

        return convertView;
    }
    class ViewHolder{
        TextView txttitle;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        View view = super.getDropDownView(position,convertView,parent);
        LinearLayout.LayoutParams lastTxtParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout ll  = (LinearLayout) view;
        TextView txttitle = ll.findViewById(R.id.txt_title_issueby);
        lastTxtParams.setMargins(32, 16, 32, 16);
        txttitle.setLayoutParams(lastTxtParams);
        return  view;
    }
}