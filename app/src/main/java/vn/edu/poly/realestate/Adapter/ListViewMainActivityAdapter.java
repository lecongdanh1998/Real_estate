package vn.edu.poly.realestate.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.text.DecimalFormat;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import vn.edu.poly.realestate.Model.ListViewMainActivityContructor;
import vn.edu.poly.realestate.R;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.L;

public class ListViewMainActivityAdapter extends BaseAdapter {
    ArrayList<ListViewMainActivityContructor> arrayList;
    Context context;
    LayoutInflater inflater;
    int Sell;
    long Deposit, DepositBuy;
    String DepositSubString, DepositBuySubString;
    DecimalFormat decimalFormat;

    public ListViewMainActivityAdapter(ArrayList<ListViewMainActivityContructor> arrayList, Context context) {
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
            convertView = inflater.inflate(R.layout.custom_lst_mainactivity, null);
            viewHolder.avatar = convertView.findViewById(R.id.avatar_user_main_listview);
            viewHolder.username = convertView.findViewById(R.id.txt_name_listview_custom);
            viewHolder.title = convertView.findViewById(R.id.txt_title_lisivew_custom);
            viewHolder.address = convertView.findViewById(R.id.txt_address_lisivew_custom);
            viewHolder.deposit = convertView.findViewById(R.id.txt_deposit_lisivew_custom);
            viewHolder.depositBuy = convertView.findViewById(R.id.txt_depositBuy_lisivew_custom);
            viewHolder.Sell = convertView.findViewById(R.id.txt_Sell_lisivew_custom);
            viewHolder.images = convertView.findViewById(R.id.img_imagesBackgound_listview);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        ListViewMainActivityContructor contructor = arrayList.get(position);
        viewHolder.username.setText(contructor.getUsername());
        viewHolder.title.setText(contructor.getTitle());
        viewHolder.address.setText(context.getResources().getString(R.string.txt_address) + " : " + contructor.getAddress());
        Sell = Integer.parseInt(contructor.getSell());
        //Sell
        if (Sell >= 0 && Sell < 1000) {
            viewHolder.Sell.setText(context.getResources().getString(R.string.txt_dautu) + " - " + context.getResources().getString(R.string.txt_tienmua) + " : " + contructor.getSell()+ " Xu");
        } else if (Sell >= 1000 && Sell < 10000) {
            String So = contructor.getSell().substring(0, 1);
            String So1 = contructor.getSell().substring(1, 2);
            if (So1.toString().equals("0")) {
                viewHolder.Sell.setText(context.getResources().getString(R.string.txt_dautu) + " - " + context.getResources().getString(R.string.txt_tienmua) + " : " + So + " Nghìn Xu");
            } else {
                viewHolder.Sell.setText(context.getResources().getString(R.string.txt_dautu) + " - " + context.getResources().getString(R.string.txt_tienmua) + " : " + So + "," + So1 + " Nghìn Xu");
            }
        } else if (Sell >= 10000 && Sell < 100000) {
            String So = contructor.getSell().substring(0, 2);
            String So1 = contructor.getSell().substring(2, 3);
            if (So1.toString().equals("0")) {
                viewHolder.Sell.setText(context.getResources().getString(R.string.txt_dautu) + " - " + context.getResources().getString(R.string.txt_tienmua) + " : " + So + " Nghìn Xu");
            } else {
                viewHolder.Sell.setText(context.getResources().getString(R.string.txt_dautu) + " - " + context.getResources().getString(R.string.txt_tienmua) + " : " + So + "," + So1 + " Nghìn Xu");
            }
        } else if (Sell >= 100000 && Sell < 1000000) {
            String So = contructor.getSell().substring(0, 3);
            String So1 = contructor.getSell().substring(3, 4);
            if (So1.toString().equals("0")) {
                viewHolder.Sell.setText(context.getResources().getString(R.string.txt_dautu) + " - " + context.getResources().getString(R.string.txt_tienmua) + " : " + So + " Nghìn Xu");
            } else {
                viewHolder.Sell.setText(context.getResources().getString(R.string.txt_dautu) + " - " + context.getResources().getString(R.string.txt_tienmua) + " : " + So + "," + So1 + " Nghìn Xu");
            }
        } else if (Sell >= 1000000 && Sell < 10000000) {
            String So = contructor.getSell().substring(0, 1);
            String So1 = contructor.getSell().substring(1, 2);
            if (So1.toString().equals("0")) {
                viewHolder.Sell.setText(context.getResources().getString(R.string.txt_dautu) + " - " + context.getResources().getString(R.string.txt_tienmua) + " : " + So + " Triệu Xu");
            } else {
                viewHolder.Sell.setText(context.getResources().getString(R.string.txt_dautu) + " - " + context.getResources().getString(R.string.txt_tienmua) + " : " + So + "," + So1 + " Triệu Xu");
            }
        } else if (Sell >= 10000000 && Sell < 100000000) {
            String So = contructor.getSell().substring(0, 2);
            String So1 = contructor.getSell().substring(2, 3);
            if (So1.toString().equals("0")) {
                viewHolder.Sell.setText(context.getResources().getString(R.string.txt_dautu) + " - " + context.getResources().getString(R.string.txt_tienmua) + " : " + So + " Triệu Xu");
            } else {
                viewHolder.Sell.setText(context.getResources().getString(R.string.txt_dautu) + " - " + context.getResources().getString(R.string.txt_tienmua) + " : " + So + "," + So1 + " Triệu Xu");
            }
        } else if (Sell >= 100000000 && Sell < 1000000000) {
            String So = contructor.getSell().substring(0, 3);
            String So1 = contructor.getSell().substring(3, 4);
            if (So1.toString().equals("0")) {
                viewHolder.Sell.setText(context.getResources().getString(R.string.txt_dautu) + " - " + context.getResources().getString(R.string.txt_tienmua) + " : " + So + " Triệu Xu");
            } else {
                viewHolder.Sell.setText(context.getResources().getString(R.string.txt_dautu) + " - " + context.getResources().getString(R.string.txt_tienmua) + " : " + So + "," + So1 + " Triệu Xu");
            }
        } else if (Sell >= 1000000000 && Sell < 10000000000L) {
            String So = contructor.getSell().substring(0, 1);
            String So1 = contructor.getSell().substring(1, 2);
            if (So1.toString().equals("0")) {
                viewHolder.Sell.setText(context.getResources().getString(R.string.txt_dautu) + " - " + context.getResources().getString(R.string.txt_tienmua) + " : " + So + " Tỷ Xu");
            } else {
                viewHolder.Sell.setText(context.getResources().getString(R.string.txt_dautu) + " - " + context.getResources().getString(R.string.txt_tienmua) + " : " + So + "," + So1 + " Tỷ Xu");
            }
        } else if (Sell >= 10000000000L && Sell < 100000000000L) {
            String So = contructor.getSell().substring(0, 2);
            String So1 = contructor.getSell().substring(2, 3);
            if (So1.toString().equals("0")) {
                viewHolder.Sell.setText(context.getResources().getString(R.string.txt_dautu) + " - " + context.getResources().getString(R.string.txt_tienmua) + " : " + So + " Tỷ Xu");
            } else {
                viewHolder.Sell.setText(context.getResources().getString(R.string.txt_dautu) + " - " + context.getResources().getString(R.string.txt_tienmua) + " : " + So + "," + So1 + " Tỷ Xu");
            }
        }else if (Sell >= 100000000000L && Sell < 1000000000000L) {
            String So = contructor.getSell().substring(0, 3);
            String So1 = contructor.getSell().substring(3, 4);
            if (So1.toString().equals("0")) {
                viewHolder.Sell.setText(context.getResources().getString(R.string.txt_dautu) + " - " + context.getResources().getString(R.string.txt_tienmua) + " : " + So + " Tỷ Xu");
            } else {
                viewHolder.Sell.setText(context.getResources().getString(R.string.txt_dautu) + " - " + context.getResources().getString(R.string.txt_tienmua) + " : " + So + "," + So1 + " Tỷ Xu");
            }
        }else if (Sell >= 1000000000000L && Sell < 10000000000000L) {
            String So = contructor.getSell().substring(0, 1);
            String So1 = contructor.getSell().substring(1, 4);
            if (So1.toString().equals("0")) {
                viewHolder.Sell.setText(context.getResources().getString(R.string.txt_dautu) + " - " + context.getResources().getString(R.string.txt_tienmua) + " : " + So + " Tỷ Xu");
            } else {
                viewHolder.Sell.setText(context.getResources().getString(R.string.txt_dautu) + " - " + context.getResources().getString(R.string.txt_tienmua) + " : " + So + "," + So1 + " Tỷ Xu");
            }
        }

        //Deposit
        Deposit = (long) (Sell * Double.parseDouble(contructor.getDeposit()));
        decimalFormat = new DecimalFormat("###,###,###.#");
        double Depositdouble = Double.valueOf(Double.parseDouble(contructor.getDeposit())*100);
        String DepositStr = decimalFormat.format(Depositdouble).replace(".", ",");
        DepositSubString = String.valueOf(Deposit);
        if (Deposit >= 0 && Deposit < 1000) {
            viewHolder.deposit.setText(context.getResources().getString(R.string.txt_tiencoc) + " : " + DepositSubString + " Xu" +" ("+DepositStr+"%)");
        } else if (Deposit >= 1000 && Deposit < 10000) {
            String So = DepositSubString.substring(0, 1);
            String So1 = DepositSubString.substring(1, 2);
            if (So1.toString().equals("0")) {
                viewHolder.deposit.setText(context.getResources().getString(R.string.txt_tiencoc) + " : " + So + " Nghìn Xu" +" ("+DepositStr+"%)");
            } else {
                viewHolder.deposit.setText(context.getResources().getString(R.string.txt_tiencoc) + " : " + So + "," + So1 + " Nghìn Xu" +" ("+DepositStr+"%)");
            }
        } else if (Deposit >= 10000 && Deposit < 100000) {
            String So = DepositSubString.substring(0, 2);
            String So1 = DepositSubString.substring(2, 3);
            if (So1.toString().equals("0")) {
                viewHolder.deposit.setText(context.getResources().getString(R.string.txt_tiencoc) + " : " + So + " Nghìn Xu" +" ("+DepositStr+"%)");
            } else {
                viewHolder.deposit.setText(context.getResources().getString(R.string.txt_tiencoc) + " : " + So + "," + So1 + " Nghìn Xu" +" ("+DepositStr+"%)");
            }
        } else if (Deposit >= 100000 && Deposit < 1000000) {
            String So = DepositSubString.substring(0, 3);
            String So1 = DepositSubString.substring(3, 4);
            if (So1.toString().equals("0")) {
                viewHolder.deposit.setText(context.getResources().getString(R.string.txt_tiencoc) + " : " + So + " Nghìn Xu"+" ("+DepositStr+"%)");
            } else {
                viewHolder.deposit.setText(context.getResources().getString(R.string.txt_tiencoc) + " : " + So + "," + So1 + " Nghìn Xu"+" ("+DepositStr+"%)");
            }
        } else if (Deposit >= 1000000 && Deposit < 10000000) {
            String So = DepositSubString.substring(0, 1);
            String So1 = DepositSubString.substring(1, 2);
            if (So1.toString().equals("0")) {
                viewHolder.deposit.setText(context.getResources().getString(R.string.txt_tiencoc) + " : " + So + " Triệu Xu"+" ("+DepositStr+"%)");
            } else {
                viewHolder.deposit.setText(context.getResources().getString(R.string.txt_tiencoc) + " : " + So + "," + So1 + " Triệu Xu"+" ("+DepositStr+"%)");
            }
        } else if (Deposit >= 10000000 && Deposit < 100000000) {
            String So = DepositSubString.substring(0, 2);
            String So1 = DepositSubString.substring(2, 3);
            if (So1.toString().equals("0")) {
                viewHolder.deposit.setText(context.getResources().getString(R.string.txt_tiencoc) + " : " + So + " Triệu Xu"+" ("+DepositStr+"%)");
            } else {
                viewHolder.deposit.setText(context.getResources().getString(R.string.txt_tiencoc) + " : " + So + "," + So1 + " Triệu Xu"+" ("+DepositStr+"%)");
            }
        } else if (Deposit >= 100000000 && Deposit < 1000000000) {
            String So = DepositSubString.substring(0, 3);
            String So1 = DepositSubString.substring(3, 4);
            if (So1.toString().equals("0")) {
                viewHolder.deposit.setText(context.getResources().getString(R.string.txt_tiencoc) + " : " + So + " Triệu Xu"+" ("+DepositStr+")");
            } else {
                viewHolder.deposit.setText(context.getResources().getString(R.string.txt_tiencoc) + " : " + So + "," + So1 + " Triệu Xu"+" ("+DepositStr+"%)");
            }
        } else if (Deposit >= 1000000000 && Deposit < 10000000000L) {
            String So = DepositSubString.substring(0, 1);
            String So1 = DepositSubString.substring(1, 2);
            if (So1.toString().equals("0")) {
                viewHolder.deposit.setText(context.getResources().getString(R.string.txt_tiencoc) + " : " + So + " Tỷ Xu"+" ("+DepositStr+"%)");
            } else {
                viewHolder.deposit.setText(context.getResources().getString(R.string.txt_tiencoc) + " : " + So + "," + So1 + " Tỷ Xu"+" ("+DepositStr+"%)");
            }
        } else if (Deposit >= 10000000000L && Deposit < 100000000000L) {
            String So = DepositSubString.substring(0, 2);
            String So1 = DepositSubString.substring(2, 3);
            if (So1.toString().equals("0")) {
                viewHolder.deposit.setText(context.getResources().getString(R.string.txt_tiencoc) + " : " + So + " Tỷ Xu"+" ("+DepositStr+"%)");
            } else {
                viewHolder.deposit.setText(context.getResources().getString(R.string.txt_tiencoc) + " : " + So + "," + So1 + " Tỷ Xu"+" ("+DepositStr+"%)");
            }
        }else if (Deposit >= 100000000000L && Deposit < 1000000000000L) {
            String So = DepositSubString.substring(0, 3);
            String So1 = DepositSubString.substring(3, 4);
            if (So1.toString().equals("0")) {
                viewHolder.deposit.setText(context.getResources().getString(R.string.txt_tiencoc) + " : " + So + " Tỷ Xu"+" ("+DepositStr+"%)");
            } else {
                viewHolder.deposit.setText(context.getResources().getString(R.string.txt_tiencoc) + " : " + So + "," + So1 + " Tỷ Xu"+" ("+DepositStr+"%)");
            }
        }else if (Deposit >= 1000000000000L && Deposit < 10000000000000L) {
            String So = DepositSubString.substring(0, 1);
            String So1 = DepositSubString.substring(1, 4);
            if (So1.toString().equals("000")) {
                viewHolder.deposit.setText(context.getResources().getString(R.string.txt_tiencoc) + " : " + So + " Tỷ Xu"+" ("+DepositStr+"%)");
            } else {
                viewHolder.deposit.setText(context.getResources().getString(R.string.txt_tiencoc) + " : " + So + "," + So1 + " Tỷ Xu"+" ("+DepositStr+"%)");
            }
        }
        //DepositBuy
        double DepositBuydouble = Double.valueOf(Double.parseDouble(contructor.getDepositBuy())*100);
        String DepositBuyStr = decimalFormat.format(DepositBuydouble).replace(".", ",");
        DepositBuy = (long) (Sell * (Double.parseDouble(contructor.getDepositBuy())));
        DepositBuySubString = String.valueOf(DepositBuy);
        if (DepositBuy >= 0 && DepositBuy < 1000) {
            viewHolder.depositBuy.setText(context.getResources().getString(R.string.txt_dautu) + " - " + context.getResources().getString(R.string.txt_tiencoc) + " : " + DepositBuySubString + " Xu"+" ("+DepositBuyStr+"%)");
        } else if (DepositBuy >= 1000 && DepositBuy < 10000) {
            String So = DepositBuySubString.substring(0, 1);
            String So1 = DepositBuySubString.substring(1, 2);
            if (So1.toString().equals("0")) {
                viewHolder.depositBuy.setText(context.getResources().getString(R.string.txt_dautu) + " - " + context.getResources().getString(R.string.txt_tiencoc) + " : " + So + " Nghìn Xu" +" ("+DepositBuyStr+"%)");
            } else {
                viewHolder.depositBuy.setText(context.getResources().getString(R.string.txt_dautu) + " - " + context.getResources().getString(R.string.txt_tiencoc) + " : " + So + "," + So1 + " Nghìn Xu" +" ("+DepositBuyStr+"%)");
            }
        } else if (DepositBuy >= 10000 && DepositBuy < 100000) {
            String So = DepositBuySubString.substring(0, 2);
            String So1 = DepositBuySubString.substring(2, 3);
            if (So1.toString().equals("0")) {
                viewHolder.depositBuy.setText(context.getResources().getString(R.string.txt_dautu) + " - " + context.getResources().getString(R.string.txt_tiencoc) + " : " + So + " Nghìn Xu" +" ("+DepositBuyStr+"%)");
            } else {
                viewHolder.depositBuy.setText(context.getResources().getString(R.string.txt_dautu) + " - " + context.getResources().getString(R.string.txt_tiencoc) + " : " + So + "," + So1 + " Nghìn Xu" +" ("+DepositBuyStr+"%)");
            }
        } else if (DepositBuy >= 100000 && DepositBuy < 1000000) {
            String So = DepositBuySubString.substring(0, 3);
            String So1 = DepositBuySubString.substring(3, 4);
            if (So1.toString().equals("0")) {
                viewHolder.depositBuy.setText(context.getResources().getString(R.string.txt_dautu) + " - " + context.getResources().getString(R.string.txt_tiencoc) + " : " + So + " Nghìn Xu" +" ("+DepositBuyStr+"%)");
            } else {
                viewHolder.depositBuy.setText(context.getResources().getString(R.string.txt_dautu) + " - " + context.getResources().getString(R.string.txt_tiencoc) + " : " + So + "," + So1 + " Nghìn Xu" +" ("+DepositBuyStr+"%)");
            }
        } else if (DepositBuy >= 1000000 && DepositBuy < 10000000) {
            String So = DepositBuySubString.substring(0, 1);
            String So1 = DepositBuySubString.substring(1, 2);
            if (So1.toString().equals("0")) {
                viewHolder.depositBuy.setText(context.getResources().getString(R.string.txt_dautu) + " - " + context.getResources().getString(R.string.txt_tiencoc) + " : " + So + " Triệu Xu" +" ("+DepositBuyStr+"%)");
            } else {
                viewHolder.depositBuy.setText(context.getResources().getString(R.string.txt_dautu) + " - " + context.getResources().getString(R.string.txt_tiencoc) + " : " + So + "," + So1 + " Triệu Xu" +" ("+DepositBuyStr+"%)");
            }
        } else if (DepositBuy >= 10000000 && DepositBuy < 100000000) {
            String So = DepositBuySubString.substring(0, 2);
            String So1 = DepositBuySubString.substring(2, 3);
            if (So1.toString().equals("0")) {
                viewHolder.depositBuy.setText(context.getResources().getString(R.string.txt_dautu) + " - " + context.getResources().getString(R.string.txt_tiencoc) + " : " + So + " Triệu Xu" +" ("+DepositBuyStr+"%)");
            } else {
                viewHolder.depositBuy.setText(context.getResources().getString(R.string.txt_dautu) + " - " + context.getResources().getString(R.string.txt_tiencoc) + " : " + So + "," + So1 + " Triệu Xu" +" ("+DepositBuyStr+"%)");
            }
        } else if (DepositBuy >= 100000000 && DepositBuy < 1000000000) {
            String So = DepositBuySubString.substring(0, 3);
            String So1 = DepositBuySubString.substring(3, 4);
            if (So1.toString().equals("0")) {
                viewHolder.depositBuy.setText(context.getResources().getString(R.string.txt_dautu) + " - " + context.getResources().getString(R.string.txt_tiencoc) + " : " + So + " Triệu Xu" +" ("+DepositBuyStr+"%)");
            } else {
                viewHolder.depositBuy.setText(context.getResources().getString(R.string.txt_dautu) + " - " + context.getResources().getString(R.string.txt_tiencoc) + " : " + So + "," + So1 + " Triệu Xu" +" ("+DepositBuyStr+"%)");
            }
        } else if (DepositBuy >= 1000000000 && DepositBuy < 10000000000L) {
            String So = DepositBuySubString.substring(0, 1);
            String So1 = DepositBuySubString.substring(1, 4);
            if (So1.toString().equals("0")) {
                viewHolder.depositBuy.setText(context.getResources().getString(R.string.txt_dautu) + " - " + context.getResources().getString(R.string.txt_tiencoc) + " : " + So + " Tỷ Xu" +" ("+DepositBuyStr+"%)");
            } else {
                viewHolder.depositBuy.setText(context.getResources().getString(R.string.txt_dautu) + " - " + context.getResources().getString(R.string.txt_tiencoc) + " : " + So + "," + So1 + " Tỷ Xu" +" ("+DepositBuyStr+"%)");
            }
        } else if (DepositBuy >= 10000000000L && DepositBuy < 100000000000L) {
            String So = DepositBuySubString.substring(0, 2);
            String So1 = DepositBuySubString.substring(2, 3);
            if (So1.toString().equals("0")) {
                viewHolder.depositBuy.setText(context.getResources().getString(R.string.txt_dautu) + " - " + context.getResources().getString(R.string.txt_tiencoc) + " : " + So + " Tỷ Xu" +" ("+DepositBuyStr+"%)");
            } else {
                viewHolder.depositBuy.setText(context.getResources().getString(R.string.txt_dautu) + " - " + context.getResources().getString(R.string.txt_tiencoc) + " : " + So + "," + So1 + " Tỷ Xu" +" ("+DepositBuyStr+"%)");
            }
        }else if (DepositBuy >= 100000000000L && DepositBuy < 1000000000000L) {
            String So = DepositBuySubString.substring(0, 3);
            String So1 = DepositBuySubString.substring(3, 4);
            if (So1.toString().equals("0")) {
                viewHolder.depositBuy.setText(context.getResources().getString(R.string.txt_dautu) + " - " + context.getResources().getString(R.string.txt_tiencoc) + " : " + So + " Tỷ Xu" +" ("+DepositBuyStr+"%)");
            } else {
                viewHolder.depositBuy.setText(context.getResources().getString(R.string.txt_dautu) + " - " + context.getResources().getString(R.string.txt_tiencoc) + " : " + So + "," + So1 + " Tỷ Xu" +" ("+DepositBuyStr+"%)");
            }
        }else if (DepositBuy >= 100000000000L && DepositBuy < 1000000000000L) {
            String So = DepositBuySubString.substring(0, 1);
            String So1 = DepositBuySubString.substring(1, 4);
            if (So1.toString().equals("000")) {
                viewHolder.depositBuy.setText(context.getResources().getString(R.string.txt_dautu) + " - " + context.getResources().getString(R.string.txt_tiencoc) + " : " + So + " Tỷ Xu" +" ("+DepositBuyStr+"%)");
            } else {
                viewHolder.depositBuy.setText(context.getResources().getString(R.string.txt_dautu) + " - " + context.getResources().getString(R.string.txt_tiencoc) + " : " + So + "," + So1 + " Tỷ Xu" +" ("+DepositBuyStr+"%)");
            }
        }
        Picasso.get()
                .load(contructor.getAvatar())
                .into(viewHolder.avatar);
        Picasso.get()
                .load(contructor.getImages())
                .into(viewHolder.images);
        return convertView;
    }

    class ViewHolder {
        CircleImageView avatar;
        TextView username, title, address, deposit, depositBuy, Sell;
        ImageView images;
    }
}
