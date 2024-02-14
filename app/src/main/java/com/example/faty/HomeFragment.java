package com.example.faty;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
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

import com.example.faty.Presenter.OnCategoryClickListner;
import com.example.faty.pojo.Category;
import com.example.faty.pojo.Country;
import com.example.faty.pojo.Meal;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;

import java.util.List;


public class HomeFragment extends Fragment implements MealContract.View, OnCategoryClickListner, MealCategoryAdapter.OnCategoryClickListener {

    private MealContract.Presenter presenter;
    OnCategoryClickListner listner;
    private ImageView mealImageView;
    private TextView mealNameTextView;
    private TextView mealCatTxt;
    private String mealId;
    private RecyclerView categoryRecyclerView;
    private MealCategoryAdapter categoryAdapter;
    private RecyclerView countryRecyclerView;
    private CountryAdapter countryAdapter;
    private String country;

    private  String categoryName;


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

        countryRecyclerView = view.findViewById(R.id.countryItems);
        countryRecyclerView.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false));
        presenter.getMealsByCountry();

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

    @Override
    public String getCategoryName() {
        Log.i("SDFGGREW$T%", "getCategoryName: "+categoryName);
        return categoryName;
    }

    public void onRandomClick(){
        mealImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle = new Bundle();
                bundle.putString("mealId", mealId);
                Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_mealFragment, bundle);

            }
        });


    }


    @Override
    public void showCategories(List<Category> categoryList) {
        categoryAdapter = new MealCategoryAdapter(categoryList,this);
        Log.i("showCategoris fun in home", "showCategories: "+categoryName);
        categoryRecyclerView.setAdapter(categoryAdapter);
    }

    @Override
    public void showCountries(List<Country> countryList) {
        countryAdapter = new CountryAdapter(countryList);
        countryRecyclerView.setAdapter(countryAdapter);
    }

    @Override
    public void onItemClick(int position, String categoryName) {
        this.categoryName = categoryName;
        categoryAdapter.getItemId(position);
        Log.d("MyFragment", "Clicked item at position: " + position + ", text: ");
        Bundle bundle = new Bundle();
        bundle.putString("catName", categoryName);


        presenter.getMealsByCategory(categoryName); // Move this line below setting the categoryName
        Navigation.findNavController(requireView()).navigate(R.id.action_homeFragment_to_catigoriesFragment, bundle);
      //  Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_catigoriesFragment, bundle);
       // Navigation.findNavController(requireActivity(), R.id.categoriesItems).navigate(R.id.action_homeFragment_to_catigoriesFragment, bundle);
    }


    @Override
    public void onCategoryClick(int position, Category category, String categoryName) {
        this.categoryName = categoryName;
        categoryAdapter.getItemId(position);
        Log.d("MyFragment", "Clicked item at position: " + position + ", text: ");
        Bundle bundle = new Bundle();
        bundle.putString("catName", categoryName);


        presenter.getMealsByCategory(categoryName); // Move this line below setting the categoryName
        Navigation.findNavController(requireView()).navigate(R.id.action_homeFragment_to_catigoriesFragment, bundle);
        Log.i("TAG", "onCategoryClick: "+category.getStrCategory());
    }
}