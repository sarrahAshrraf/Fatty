package com.example.faty.view.home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.example.faty.Presenter.MealContract;
import com.example.faty.Presenter.MealPresenter;
import com.example.faty.R;
import com.example.faty.pojo.Meal;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;

public class MealFragment extends Fragment implements MealContract.View {

    private MealContract.Presenter presenter;
    private ImageView mealImageView;
    private TextView tvMealCategory;
    private TextView tvMealInstructions;
    private TextView tvMealCountry;

    private String mealId;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_meal, container, false);
        mealImageView = view.findViewById(R.id.mealImg);
        tvMealCategory = view.findViewById(R.id.tvcatMeal);
        tvMealInstructions = view.findViewById(R.id.instructionTxt);
        tvMealCountry = view.findViewById(R.id.tvcountryMeal);

        presenter = new MealPresenter(this);
        presenter.getRandomMeal();


        Bundle bundle = getArguments();
        if (bundle != null) {
            mealId = bundle.getString("mealId");
            presenter.getMealDetails(mealId);
        }
        return view;
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
    }

    @Override
    public void displayError(String message) {
        Toast.makeText(getActivity(), "Error: " + message, Toast.LENGTH_SHORT).show();
    }
    @Override
    public String getMealId() {
        return mealId;
    }
}