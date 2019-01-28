package vn.edu.poly.realestate.Model.ModelMaps;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.maps.android.clustering.Cluster;
import com.google.maps.android.clustering.ClusterManager;
import com.google.maps.android.clustering.view.ClusterRenderer;
import com.google.maps.android.clustering.view.DefaultClusterRenderer;
import com.google.maps.android.ui.IconGenerator;

import java.util.ArrayList;
import java.util.List;

import vn.edu.poly.realestate.Model.ModelMain.ModelMain;
import vn.edu.poly.realestate.R;


public class CustomClusterRenderer extends DefaultClusterRenderer<ModelMaps> {
    private IconGenerator mClusterIconGenerator;
    private final Context mContext;
    private GoogleMap map;
    private List<Circle> circleList = new ArrayList<>();

    public CustomClusterRenderer(Context context, GoogleMap map,
                                 ClusterManager<ModelMaps> clusterManager) {
        super(context, map, clusterManager);
        this.map = map;
        mContext = context;
    }

    @Override
    protected void onBeforeClusterItemRendered(ModelMaps item,
                                               MarkerOptions markerOptions) {
        View marker = ((LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.custom_marker_layout, null);
        TextView numTxt = (TextView) marker.findViewById(R.id.num_txt);
        numTxt.setText(item.getSnippet());
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) mContext).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        marker.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        marker.measure(displayMetrics.widthPixels, displayMetrics.heightPixels);
        marker.layout(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels);
        marker.buildDrawingCache();
        Bitmap bitmap = Bitmap.createBitmap(marker.getMeasuredWidth(), marker.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        marker.draw(canvas);
        final BitmapDescriptor markerDescriptor = BitmapDescriptorFactory.fromBitmap(bitmap);
        markerOptions.icon(markerDescriptor);

    }

    @Override
    protected void onBeforeClusterRendered(Cluster<ModelMaps> cluster,
                                           MarkerOptions markerOptions) {
//        mClusterIconGenerator = new IconGenerator(mContext.getApplicationContext());
//        mClusterIconGenerator.setBackground(
//                ContextCompat.getDrawable(mContext, R.drawable.background_circle));
//        mClusterIconGenerator.setTextAppearance(R.style.AppTheme_WhiteTextAppearance);
//        final Bitmap icon = mClusterIconGenerator.makeIcon(String.valueOf(cluster.getSize()));

        View marker = ((LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).inflate(R.layout.custom_cluster_layout, null);
        TextView numTxt = (TextView) marker.findViewById(R.id.num_txt);
        numTxt.setText(String.valueOf(cluster.getSize()));
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity) mContext).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        marker.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
        marker.measure(displayMetrics.widthPixels, displayMetrics.heightPixels);
        marker.layout(0, 0, displayMetrics.widthPixels, displayMetrics.heightPixels);
        marker.buildDrawingCache();
        Bitmap bitmap = Bitmap.createBitmap(marker.getMeasuredWidth(), marker.getMeasuredHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        marker.draw(canvas);


        final BitmapDescriptor markerDescriptor = BitmapDescriptorFactory.fromBitmap(bitmap);


        markerOptions.icon(markerDescriptor);

    }

    @Override
    protected boolean shouldRenderAsCluster(Cluster<ModelMaps> cluster) {
        return cluster.getSize() > 3;
    }


    @Override
    protected void onClusterRendered(Cluster<ModelMaps> cluster, Marker marker) {
        super.onClusterRendered(cluster, marker);
        for (Circle circle : circleList) {
            circle.remove();
        }
        circleList.clear();
    }

//    private void drawRadius(Cluster<ModelMaps> point) {
//        CircleOptions circleOptions = new CircleOptions()
//                .center(point.getPosition())
//                .strokeColor(Color.argb(50, 70, 70, 70))
//                .fillColor(Color.argb(100, 150, 150, 150))
//                .radius(500);
//        Circle circle = map.addCircle(circleOptions);
//        circleList.add(circle);
//    }


}

