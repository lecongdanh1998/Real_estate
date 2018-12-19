package vn.edu.poly.realestate.Adapter;

import android.content.Context;
import android.media.Rating;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import vn.edu.poly.realestate.Model.ModelThamDinh.ContructorThamdinh;
import vn.edu.poly.realestate.Model.ModelThamDinh.Model.ContructorPending;
import vn.edu.poly.realestate.Model.ModelThamDinh.Model.ContructorPendingData;
import vn.edu.poly.realestate.Model.RetrofitClient.APIUtils;
import vn.edu.poly.realestate.Model.RetrofitClient.DataClient;
import vn.edu.poly.realestate.Model.RetrofitClient.Infodatadeposit.Data;
import vn.edu.poly.realestate.Model.RetrofitClient.RatingContructor.RatingContructor;
import vn.edu.poly.realestate.Model.RetrofitClient.RatingContructor.RatingData;
import vn.edu.poly.realestate.R;

public class AdapterThamdinh extends BaseAdapter {

    ArrayList<ContructorPendingData> arrayList;
    Context context;
    LayoutInflater inflater;
    int Sell, Buy;
    String DepositSubString, DepositBuySubString;
    DecimalFormat decimalFormat;

    public AdapterThamdinh(ArrayList<ContructorPendingData> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
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
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.custom_lst_thamdinh, null);
            viewHolder.avatar = convertView.findViewById(R.id.avatar_user_main_listview);
            viewHolder.title = convertView.findViewById(R.id.txt_title_lisivew_custom);
            viewHolder.address = convertView.findViewById(R.id.txt_address_lisivew_custom);
            viewHolder.SellBuy = convertView.findViewById(R.id.txt_deposit_lisivew_custom);
            viewHolder.images = convertView.findViewById(R.id.img_imagesBackgound_listview);
            viewHolder.star = convertView.findViewById(R.id.rb_rating);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        final ContructorPendingData contructor = arrayList.get(position);
        initFetchDataId(contructor.getId(), viewHolder.star);
        viewHolder.title.setText(contructor.getTitle());
        viewHolder.address.setText(contructor.getAddress());
        double se = Double.valueOf(contructor.getPriceSell());
        Sell = (int) se;
        decimalFormat = new DecimalFormat("###,###,###.#");
        //Sell
        double selldouble = Double.valueOf(Sell);
        String sellStr = decimalFormat.format(selldouble).replace(".", ",");

        double by = Double.valueOf(contructor.getPriceBuy());
        Buy = (int) by;
        decimalFormat = new DecimalFormat("###,###,###.#");
        //Sell
        double buydouble = Double.valueOf(Buy);
        String buyStr = decimalFormat.format(buydouble).replace(".", ",");
        viewHolder.SellBuy.setText(context.getResources().getString(R.string.txt_price_sell) + ": " + sellStr + "  "
                + context.getResources().getString(R.string.txt_price_buy) + ": " + buyStr);
//        Picasso.get()
//                .load(contructor.getImage())
//                .into(viewHolder.avatar);

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

    private void initFetchDataId(Integer id, final RatingBar rating) {
        DataClient dataClient = APIUtils.getData();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("id", String.valueOf(id));
        final Call<RatingContructor> callback1 = dataClient.RatingContructor(hashMap);
        callback1.enqueue(new Callback<RatingContructor>() {
            @Override
            public void onResponse(Call<RatingContructor> call, Response<RatingContructor> response) {
                ArrayList<RatingData> ratingData = response.body().getData();
                if (response.body() == null) {
                } else {
                    float tong = 0;
                    for (int i = 0; i < ratingData.size(); i++) {
                        tong = tong + Float.parseFloat(ratingData.get(i).getRating());
                    }
                    rating.setRating(tong / ratingData.size());
                }
            }

            @Override
            public void onFailure(Call<RatingContructor> call, Throwable t) {
                Toast.makeText(context, "Vui lòng kiểm tra kết nối Internet Rating", Toast.LENGTH_SHORT).show();
            }
        });


    }


    class ViewHolder {
        CircleImageView avatar;
        TextView title, address, SellBuy;
        ImageView images;
        RatingBar star;
    }
}
