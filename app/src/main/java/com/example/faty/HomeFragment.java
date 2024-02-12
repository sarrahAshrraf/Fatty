package com.example.faty;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.faty.Presenter.CountryAdapter;
import com.example.faty.Presenter.MealCategoryAdapter;
import com.example.faty.Presenter.MealContract;
import com.example.faty.Presenter.MealPresenter;

import com.example.faty.pojo.Category;
import com.example.faty.pojo.Country;
import com.example.faty.pojo.Meal;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;

import java.util.List;


public class HomeFragment extends Fragment implements MealContract.View {

    private MealContract.Presenter presenter;
    private ImageView mealImageView;
    private TextView mealNameTextView;
    private TextView mealCatTxt;
    private String mealId;
    private RecyclerView categoryRecyclerView;
    private MealCategoryAdapter categoryAdapter;
    private RecyclerView countryRecyclerView;
    private CountryAdapter countryAdapter;
    private String country;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        mealImageView = view.findViewById(R.id.dailyMealImg);
        mealNameTextView = view.findViewById(R.id.tvMealName);
        mealCatTxt = view.findViewById(R.id.tvRandomCat);
        presenter = new MealPresenter(this);
        presenter.getRandomMeal();
        onRandomClick();

        categoryRecyclerView = view.findViewById(R.id.categoriesItems);
        categoryRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
       //  presenter.getMealsByCountry();
      //  presenter = new MealPresenter(this);

        presenter.getMealCategoriesList();
        return view;
    }


    @Override
    public void displayMeal(Meal meal) {
      //  Toast.makeText(getActivity(), "Meal: " + meal.getStrCategory(), Toast.LENGTH_SHORT).show();
        Glide.with(requireContext())
                .load(meal.getStrMealThumb())
                .transform(new CenterCrop(), new RoundedCornersTransformation(1, 0))
                .into(mealImageView);

        mealNameTextView.setText(meal.getStrMeal());
        mealCatTxt.setText(meal.getStrCategory());
        mealId = meal.getIdMeal();
    }

    @Override
    public void displayError(String message) {
       Toast.makeText(getActivity(), "Error: " + message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public String getMealId() {
        return mealId;
    }

    public void onRandomClick(){
        mealImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putString("mealId", mealId);
                Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_mealFragment, bundle);            }
        });


    }


    @Override
    public void showCategories(List<Category> categoryList) {
        categoryAdapter = new MealCategoryAdapter(categoryList);
        categoryRecyclerView.setAdapter(categoryAdapter);
    }

    @Override
    public void showCountries(List<Country> countryList) {
        countryAdapter = new CountryAdapter(countryList);
        countryRecyclerView.setAdapter(countryAdapter);
    }
}