package vn.edu.poly.realestate.View.Menu.Menu_nguoidangtinbatdongsan.Dangtinbatdongsan;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;

import vn.edu.poly.realestate.Adapter.AdapterGallery;
import vn.edu.poly.realestate.Component.BaseActivity;
import vn.edu.poly.realestate.Model.ContructorGallery;
import vn.edu.poly.realestate.Presenter.PresenterPost.PresenterPost;
import vn.edu.poly.realestate.Presenter.PresenterPost.PresenterReponsetoViewPost;
import vn.edu.poly.realestate.R;
import vn.edu.poly.realestate.View.Menu.Menu_nguoidangtinbatdongsan.Dangtinbatdongsan.Gallery.Custom_galleryActivity;

public class PostActivity extends BaseActivity implements PresenterReponsetoViewPost, View.OnClickListener {
    PresenterPost presenterPost;
    ImageView img_back_post, btn_camera_post, btn_gallery_post, img_background_post;
    Spinner spin_Wards, spin_District, spin_city;
    EditText edt_user_arearong, edt_user_areadai, edt_address, edt_description, edt_user_area, edt_price_buy, edt_price_deposit, edt_price_sell, edt_title;
    DecimalFormat decimalFormat;
    private int PIC_CROP = 3;
    private Uri picUri;
    private int CAMERA_REQUEST = 1;
    GridView gridview;
    Button btn_Submid_post;
    private int CAMERA_REQUEST_MAX = 1999;
    String AREA, BUY, DEPOSIT, SELL;
    String image = "";
    TextInputLayout textInput_title, textInput_address, textInput_area, textInput_price_buy, textInput_price_deposit,
            textInput_price_sell, textInput_description;
    private final int PICK_IMAGE_MULTIPLE = 2;
    private ArrayList<String> imagesPathList;
    private Bitmap yourbitmap;
    ArrayList<ContructorGallery> arrayList;
    AdapterGallery adapterGallery;
    int idCity, idDictrict, idWard;
    ArrayList<String> arrayListiImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);
        initControl();
        initData();
        initOnClick();
    }

    private void initControl() {
        gridview = findViewById(R.id.gridview);
        edt_description = findViewById(R.id.edt_description);
        edt_address = findViewById(R.id.edt_address);
        edt_title = findViewById(R.id.edt_title);
        spin_Wards = findViewById(R.id.pioedittxt5_ward);
        spin_District = findViewById(R.id.pioedittxt5_district);
        spin_city = findViewById(R.id.pioedittxt5_city);
        img_back_post = findViewById(R.id.img_back_post);
        edt_user_arearong = findViewById(R.id.edt_user_arearong);
        edt_user_areadai = findViewById(R.id.edt_user_areadai);
        edt_user_area = findViewById(R.id.edt_user_area);
        edt_price_buy = findViewById(R.id.edt_price_buy);
        edt_price_deposit = findViewById(R.id.edt_price_deposit);
        edt_price_sell = findViewById(R.id.edt_price_sell);
        btn_camera_post = findViewById(R.id.btn_camera_post);
        btn_gallery_post = findViewById(R.id.btn_gallery_post);
//        img_background_post = findViewById(R.id.img_background_post);
        btn_Submid_post = findViewById(R.id.btn_Submid_post);
        textInput_title = findViewById(R.id.textInput_title);
        textInput_address = findViewById(R.id.textInput_address);
        textInput_area = findViewById(R.id.textInput_area);
        textInput_price_buy = findViewById(R.id.textInput_price_buy);
        textInput_price_deposit = findViewById(R.id.textInput_price_deposit);
        textInput_price_sell = findViewById(R.id.textInput_price_sell);
        textInput_description = findViewById(R.id.textInput_description);
    }

    private void initData() {
        presenterPost = new PresenterPost(this, this);
        presenterPost.initSpinnerAddress(spin_Wards, spin_District, spin_city);
        presenterPost.initEditTextArea(edt_user_arearong, edt_user_areadai);
        presenterPost.initEditTextPrice(edt_price_buy, edt_price_deposit, edt_price_sell, edt_title, edt_address, edt_description);
        arrayList = new ArrayList<>();
        arrayListiImage = new ArrayList<>();
    }

    private void initOnClick() {
        img_back_post.setOnClickListener(this);
        btn_camera_post.setOnClickListener(this);
        btn_gallery_post.setOnClickListener(this);
        btn_Submid_post.setOnClickListener(this);
    }

    @Override
    public void onIntentView() {

    }

    @Override
    public void onSpinnerAddress(int requestAddress, int requestcode) {
        if (requestcode == 0) {
            idCity = requestAddress;
        }
        if (requestcode == 1) {
            idDictrict = requestAddress;
        }
        if (requestcode == 2) {
            idWard = requestAddress;
        }
    }

    @Override
    public void onEditTextArea(String area, int requestcode) {
        if (requestcode == 0) {
            edt_user_area.setText(area);
            textInput_area.setHint(getResources().getString(R.string.txt_Errorarea));
            textInput_area.setError(getResources().getString(R.string.txt_Errorarea));
        } else if (requestcode == 1) {
            edt_user_area.setText(area);
            textInput_area.setHint(getResources().getString(R.string.txt_area));
            textInput_area.setError("");
        }
    }

    @Override
    public void onEditTextPrice(String error, int requestcode) {
        if (requestcode == 0) {
            if (edt_price_buy.getText().toString().equals("")) {
                textInput_price_buy.setError(getResources().getString(R.string.txt_Errorprice_buy));
                textInput_price_buy.setHint(getResources().getString(R.string.txt_Errorprice_buy));
            } else {
                textInput_price_buy.setError("");
                textInput_price_buy.setHint(error);
            }
        }
        if (requestcode == 1) {
            if (edt_price_deposit.getText().toString().equals("")) {
                textInput_price_deposit.setHint(getResources().getString(R.string.txt_Errorprice_buy));
                textInput_price_deposit.setError(getResources().getString(R.string.txt_Errorprice_buy));
            } else {
                textInput_price_deposit.setHint(error);
                textInput_price_deposit.setError("");
            }
        }
        if (requestcode == 2) {
            if (edt_price_sell.getText().toString().equals("")) {
                textInput_price_sell.setHint(getResources().getString(R.string.txt_Errorprice_sell));
                textInput_price_sell.setError(getResources().getString(R.string.txt_Errorprice_sell));
            } else {
                textInput_price_sell.setHint(error);
                textInput_price_sell.setError("");
            }
        }
        if (requestcode == 3) {
            if (edt_title.getText().toString().equals("")) {
                textInput_title.setHint(getResources().getString(R.string.txt_Errortitle));
                textInput_title.setError(getResources().getString(R.string.txt_Errortitle));
            } else {
                textInput_title.setHint(error);
                textInput_title.setError("");
            }
        }
        if (requestcode == 4) {
            if (edt_address.getText().toString().equals("")) {
                textInput_address.setHint(getResources().getString(R.string.txt_ErrorAddress));
                textInput_address.setError(getResources().getString(R.string.txt_ErrorAddress));
            } else {
                textInput_address.setHint(error);
                textInput_address.setError("");
            }
        }
        if (requestcode == 5) {
            if (edt_description.getText().toString().equals("")) {
                textInput_description.setHint(getResources().getString(R.string.txt_Errordescription));
                textInput_description.setError(getResources().getString(R.string.txt_Errordescription));
            } else {
                textInput_description.setHint(error);
                textInput_description.setError("");
            }
        }
    }

    @Override
    public void onFetchCamera(Intent takePictureIntent, int camera) {
        startActivityForResult(takePictureIntent, camera);
    }

    @Override
    public void onFetchGallery(Intent imageDownload) {
        startActivityForResult(imageDownload, 2);
    }

    @Override
    public void onFetchInsertData(String str, String error, int requestcode) {
        if (!str.toString().equals("")) {
            if (requestcode == 0) {
                if (!error.toString().equals("")) {
                    Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
                    for (int i = 0; i < arrayListiImage.size(); i++) {
                        presenterPost.initUpLoadImages(error, arrayListiImage.get(i));
                    }
                    Intent intent = new Intent(this, PostActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.stay_still, R.anim.stay_still);
                    finish();
                    Toast.makeText(this, "Đã Upload Image xong", Toast.LENGTH_SHORT).show();
                } else if (error.toString().equals("")) {
                    Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
                }


            }
        }
        if (!error.toString().equals("")) {
            if (requestcode == 1) {
                textInput_title.setError(error);
                textInput_title.setHint(error);
            } else if (requestcode == 2) {
                textInput_address.setError(error);
                textInput_address.setHint(error);
            } else if (requestcode == 3) {
                textInput_price_buy.setError(error);
                textInput_price_buy.setHint(error);
            } else if (requestcode == 4) {
                textInput_price_deposit.setError(error);
                textInput_price_deposit.setHint(error);
            } else if (requestcode == 5) {
                textInput_price_sell.setError(error);
                textInput_price_sell.setHint(error);
            } else if (requestcode == 6) {
                textInput_description.setError(error);
                textInput_description.setHint(error);
            } else if (requestcode == 7) {
                Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
            } else if (requestcode == 8) {
                textInput_area.setError(error);
                textInput_area.setHint(error);
            }
        }

    }

    @Override
    public void onFetchUpLoad(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back_post:
                onBackPressed();
                break;
            case R.id.btn_camera_post:
                presenterPost.SDK_Camera();
                break;
            case R.id.btn_gallery_post:
                Intent intent = new Intent(PostActivity.this, Custom_galleryActivity.class);
                startActivityForResult(intent, PICK_IMAGE_MULTIPLE);
//                presenterPost.galleryUpload();
                break;
            case R.id.btn_Submid_post:
                if (edt_user_arearong.getText().toString().equals("") || edt_user_areadai.getText().toString().equals("")) {
                    AREA = "";
                } else {
                    AREA = edt_user_arearong.getText().toString() + " x "
                            + edt_user_areadai.getText().toString()
                            + "(" + edt_user_area.getText().toString() + ")";
                }
                BUY = edt_price_buy.getText().toString().replace(",", "");
                DEPOSIT = edt_price_deposit.getText().toString().replace(",", "");
                SELL = edt_price_sell.getText().toString().replace(",", "");
                presenterPost.initInsert(edt_title.getText().toString()
                        , edt_address.getText().toString()
                        , String.valueOf(idWard)
                        , String.valueOf(idDictrict)
                        , String.valueOf(idCity)
                        , AREA
                        , BUY
                        , DEPOSIT
                        , SELL
                        , edt_description.getText().toString()
                        , image
                );
                break;
        }
    }

    @Override
    public void onBackPressed() {
        presenterPost.initIntentView(0);
        super.onBackPressed();
    }

    public String decodeImage(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 80, outputStream);
        byte[] byteImage = outputStream.toByteArray();
        String encodeImage = Base64.encodeToString(byteImage, Base64.NO_WRAP);
        return encodeImage;
    }

    public void setGridViewHeightBasedOnChildren(GridView gridView, int columns) {
        ListAdapter listAdapter = gridView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }

        int totalHeight = 0;
        int items = listAdapter.getCount();
        int rows = 0;

        View listItem = listAdapter.getView(0, null, gridView);
        listItem.measure(0, 0);
        totalHeight = listItem.getMeasuredHeight();

        float x = 1;
        if (items > columns) {
            x = items / columns;
            rows = (int) (x + 1);
            totalHeight *= rows;
        }

        ViewGroup.LayoutParams params = gridView.getLayoutParams();
        params.height = totalHeight;
        gridView.setLayoutParams(params);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

//
        if (resultCode == RESULT_OK) {
            if (requestCode == PICK_IMAGE_MULTIPLE) {
                imagesPathList = new ArrayList<String>();
                String[] imagesPath = data.getStringExtra("data").split("\\|");
                try {
                } catch (Throwable e) {
                    e.printStackTrace();
                }
                for (int i = 0; i < imagesPath.length; i++) {
                    imagesPathList.add(imagesPath[i]);
                    yourbitmap = BitmapFactory.decodeFile(imagesPath[i]);
                    image = decodeImage(yourbitmap);
                    arrayListiImage.add(image);
                    arrayList.add(new ContructorGallery(yourbitmap));

                }

            }
        }
        if (resultCode == this.RESULT_OK) {
            if (requestCode == CAMERA_REQUEST) {
                // get the Uri for the captured image
                picUri = data.getData();
                performCrop();
            } else if (requestCode == CAMERA_REQUEST_MAX) {
                Bundle extras = data.getExtras();
                Bitmap imageBitmap = (Bitmap) extras.get("data");
                image = decodeImage(imageBitmap);
                arrayList.add(new ContructorGallery(imageBitmap));
                arrayListiImage.add(image);
            } else if (requestCode == PIC_CROP) {
                Bundle extras = data.getExtras();
                Bitmap thePic = extras.getParcelable("data");
            }
        }
        if (arrayList.size() > 0) {
            adapterGallery = new AdapterGallery(this, arrayList);
            gridview.setAdapter(adapterGallery);
            setGridViewHeightBasedOnChildren(gridview, 4);
            adapterGallery.notifyDataSetChanged();
        }


    }

    private void performCrop() {
        try {
            Intent cropIntent = new Intent("com.android.camera.action.CROP");
            cropIntent.setDataAndType(picUri, "image/*");
            cropIntent.putExtra("crop", "true");
            cropIntent.putExtra("aspectX", 200);
            cropIntent.putExtra("aspectY", 200);
            cropIntent.putExtra("outputX", 200);
            cropIntent.putExtra("outputY", 200);
            cropIntent.putExtra("return-data", true);
            startActivityForResult(cropIntent, PIC_CROP);
        } catch (ActivityNotFoundException anfe) {

        }
    }

}
