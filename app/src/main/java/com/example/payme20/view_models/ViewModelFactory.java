package com.example.payme20.view_models;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import java.util.HashMap;
import java.util.Map;

public enum ViewModelFactory implements ViewModelProvider.Factory {
    INSTANCE;
    private Map<String, ViewModel> viewModelMap;

    ViewModelFactory() {
        this.viewModelMap = new HashMap<>();
    }

    public void add(ViewModel viewModel) {
        viewModelMap.put(viewModel.getClass().getCanonicalName(), viewModel);
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        for (Map.Entry<String, ViewModel> viewModel : viewModelMap.entrySet()) {
            if (viewModel.getKey().equals(modelClass.getCanonicalName())) {
                return (T) viewModel.getValue();
            }
        }
        return null;
    }
}
