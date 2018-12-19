package vn.edu.poly.realestate.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import vn.edu.poly.realestate.Model.RetrofitClient.Infodatadeposit.Data;
import vn.edu.poly.realestate.R;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

public class ListViewMainActivityAdapter extends BaseAdapter {
    ArrayList<Data> arrayList;
    Context context;
    LayoutInflater inflater;
    int Sell;
    long Deposit, DepositBuy;
    String DepositSubString, DepositBuySubString;
    DecimalFormat decimalFormat;
    int requesthideshow;
    private View.OnClickListener clickNe;
    ViewHolder viewHolder = null;

    public ListViewMainActivityAdapter(ArrayList<Data> arrayList, Context context, View.OnClickListener clickNe) {
        this.arrayList = arrayList;
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.clickNe = clickNe;
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
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.custom_lst_mainactivity, null);
            viewHolder.avatar = convertView.findViewById(R.id.avatar_user_main_listview);
            viewHolder.username = convertView.findViewById(R.id.txt_name_listview_custom);
            viewHolder.title = convertView.findViewById(R.id.txt_title_lisivew_custom);
            viewHolder.address = convertView.findViewById(R.id.txt_address_lisivew_custom);
            viewHolder.deposit = convertView.findViewById(R.id.txt_deposit_lisivew_custom);
            viewHolder.Email = convertView.findViewById(R.id.txt_email_lisivew_custom);
            viewHolder.Phone = convertView.findViewById(R.id.txt_hotline_lisivew_custom);
            viewHolder.depositBuy = convertView.findViewById(R.id.txt_depositBuy_lisivew_custom);
            viewHolder.Sell = convertView.findViewById(R.id.txt_Sell_lisivew_custom);
            viewHolder.images = convertView.findViewById(R.id.img_imagesBackgound_listview);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final Data contructor = arrayList.get(position);
//        viewHolder.username.setText(contructor.getUsername());
//        viewHolder.Email.setText(contructor.getEmail());
//        viewHolder.Phone.setText(contructor.getPhone());
        viewHolder.title.setText(contructor.getTitle());
        viewHolder.address.setText(contructor.getAddress());
        double se = Double.valueOf(contructor.getPrice_sell());
        Sell = (int) se;
        decimalFormat = new DecimalFormat("###,###,###.#");
        //Sell
        double selldouble = Double.valueOf(Sell);
        String sellStr = decimalFormat.format(selldouble).replace(".", ",");
        viewHolder.Sell.setText(sellStr);
//        Deposit
        Deposit = (long) (Double.parseDouble(contructor.getPrice_deposit()));
        double Depositdouble = Double.valueOf((Double.parseDouble(contructor.getPrice_deposit()) * 100) / (Double.parseDouble(contructor.getPrice_buy())));
        String DepositStr = decimalFormat.format(Depositdouble).replace(".", ",");
        double Depositdouble1 = Double.valueOf(Deposit);
        String DepositStr1 = decimalFormat.format(Depositdouble1).replace(".", ",");
        viewHolder.deposit.setText(DepositStr1 + " (" + DepositStr + "%)");
        //DepositBuy
        double DepositBuydouble = Double.valueOf((Double.parseDouble(contructor.getPrice_buy()) * 100) / (Double.parseDouble(contructor.getPrice_sell())));
        String DepositBuyStr = decimalFormat.format(DepositBuydouble).replace(".", ",");
        DepositBuy = (long) ((Double.parseDouble(contructor.getPrice_buy())));
        double DepositBuydouble1 = Double.valueOf(DepositBuy);
        String DepositBuyStr1 = decimalFormat.format(DepositBuydouble1).replace(".", ",");
        viewHolder.depositBuy.setText(DepositBuyStr1);
//        Picasso.get()
//                .load(contructor.getAvatar())
//                .into(viewHolder.avatar);
//
        JSONArray cast = null;
        try {
            cast = new JSONArray(contructor.getImage());
            for (int i = 0; i < cast.length(); i++) {
                String street = cast.getString(i);
                if (!cast.getString(i).toString().equals("")) {
                    Picasso.get()
                            .load(cast.getString(i))
                            .into(viewHolder.images);
                    break;
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return convertView;
    }


    class ViewHolder {
        CircleImageView avatar;
        TextView username, title, address, deposit, depositBuy, Sell, Email, Phone;
        ImageView images;
        LinearLayout LNTshow, LNThide;
        RelativeLayout RLTintent;
    }
}
