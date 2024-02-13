package com.example.faty.view.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.example.faty.Presenter.MealContract;
import com.example.faty.Presenter.MealPresenter;
import com.example.faty.R;
import com.example.faty.database.MealDTO;
import com.example.faty.database.MealDao;
import com.example.faty.database.MealDatabase;
import com.example.faty.pojo.Category;
import com.example.faty.pojo.Country;
import com.example.faty.pojo.Meal;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class MealFragment extends Fragment implements MealContract.View {

    private MealContract.Presenter presenter;
    private ImageView mealImageView;
    private TextView tvMealCategory;
    private TextView tvMealInstructions;
    private TextView tvMealCountry;

    private String mealId;
    private WebView webView;
    CollapsingToolbarLayout collapsingToolbarLayout ;
    private FloatingActionButton btnFavorite;
    MealDTO mealDTO;
    MealDatabase db;
    MealDao dao;
    String strMealName;
    String youtUrl;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_meal, container, false);
        mealImageView = view.findViewById(R.id.mealImg);
        tvMealCategory = view.findViewById(R.id.tvcatMeal);
        tvMealInstructions = view.findViewById(R.id.instructionTxt);
        tvMealCountry = view.findViewById(R.id.tvcountryMeal);
        webView = view.findViewById(R.id.mealVid);
        collapsingToolbarLayout = view.findViewById(R.id.collapsingToolbarLayout);
        btnFavorite = view.findViewById(R.id.btnFavorite);

        presenter = new MealPresenter(this);
        presenter.getRandomMeal();
        mealDTO = new MealDTO();
        db = MealDatabase.getInstance(requireContext());
        dao = db.mealDao();


        Bundle bundle = getArguments();
        if (bundle != null) {
            mealId = bundle.getString("mealId");
            presenter.getMealDetails(mealId);
        }

      //  mealDTO.setStrMeal(strMealName);
      //  mealDTO.setStrYoutube(""); // Set the YouTube URL here
       // mealDTO.setStrMeal(""); // Set the meal name here
        btnFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFav();
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        dao.insertMeal(mealDTO);
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(getActivity(), "Added to favorites", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }).start();
            }
        });
        return view;
    }

    @Override
    public void showCategories(List<Category> categoryList) {

    }

    @Override
    public void displayMeal(Meal meal) {
        Glide.with(requireContext())
                .load(meal.getStrMealThumb())
                .transform(new CenterCrop(), new RoundedCornersTransformation(1, 0))
                .into(mealImageView);

        tvMealCategory.setText(meal.getStrCategory());
        tvMealInstructions.setText(meal.getStrInstructions());
        tvMealCountry.setText(meal.getStrArea());
        collapsingToolbarLayout.setTitle(meal.getStrMeal());
//to pass meal detail to MealDTO and save it in the db
      strMealName = meal.getStrMeal();
      youtUrl= meal.getStrYoutube();


    //    String vid = "<iframe width=\"100%\" height=\"100%\" src=\""+meal.getStrYoutube()+"\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe>";
      //  String vid = "<iframe width=\"560\" height=\"315\" src=\"https://www.youtube.com/embed/MWzxDFRtVbc?si=20a0LoFL5Stzj-1h\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe>";
        String youtubeUrl = meal.getStrYoutube();
        String videoId = youtubeUrl.substring(youtubeUrl.indexOf("v=") + 2, youtubeUrl.length());
        String iframeCode = "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/" + videoId + "\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe>";
        webView.loadData(iframeCode, "text/html", "UTF-8");
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());


    }

    @Override
    public void displayError(String message) {
        Toast.makeText(getActivity(), "Error: " + message, Toast.LENGTH_SHORT).show();
    }
    @Override
    public String getMealId() {
        return mealId;
    }

    @Override
    public void showCountries(List<Country> countryList) {

    }

void addFav(){

    mealDTO.setIdMeal(mealId);
    mealDTO.setStrCategory(tvMealCategory.getText().toString());
    mealDTO.setStrArea(tvMealCountry.getText().toString());
    mealDTO.setStrInstructions(tvMealInstructions.getText().toString());
    //saving from the fragment into db
    mealDTO.setStrMeal(strMealName);
    mealDTO.setStrYoutube(youtUrl);

}



    @Override
    public void onDestroyView() {
        super.onDestroyView();
       // MealDatabase.getInstance(requireContext()).closeDatabase();
    }
}