package gabe.hosanna.gadsleaderboard.views.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import gabe.hosanna.gadsleaderboard.R;
import gabe.hosanna.gadsleaderboard.databinding.ActivityHomepageBinding;
import gabe.hosanna.gadsleaderboard.views.fragments.LearningFragment;
import gabe.hosanna.gadsleaderboard.views.fragments.SkillFragment;

public class HomepageActivity extends AppCompatActivity {
    ActivityHomepageBinding binding;
    //Tab Titles
    private String[] titles = new String[]{"Learning Leaders", "Skill IQ Leaders"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_homepage);

        initTabs();

        binding.btnSubmit.setOnClickListener(view -> startActivity(new Intent(HomepageActivity.this, SubmissionActivity.class)));
    }

    public void initTabs() {
        binding.viewPager.setAdapter(new ViewPagerFragmentAdapter(this));

        //Attach Tab Mediator
        new TabLayoutMediator(binding.tabLayout, binding.viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(titles[position]);
            }
        }).attach();
    }


    private class ViewPagerFragmentAdapter extends FragmentStateAdapter {

        public ViewPagerFragmentAdapter(@NonNull FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            switch (position) {
                case 0:
                    return new LearningFragment();
                case 1:
                    return new SkillFragment();
            }
            return new LearningFragment();
        }

        @Override
        public int getItemCount() {
            return titles.length;
        }
    }
}