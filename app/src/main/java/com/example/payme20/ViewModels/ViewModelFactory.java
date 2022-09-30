package com.example.payme20.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.payme20.model.Event;

import java.util.HashMap;
import java.util.Map;

public class ViewModelFactory implements ViewModelProvider.Factory {
    private Map<Object, ViewModel> viewModelMap;

    public ViewModelFactory() {
        this.viewModelMap = new HashMap<>();
    }

    public void add(ViewModel viewModel) {
        viewModelMap.put(viewModel.getClass(), viewModel);
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        for (Map.Entry<Object, ViewModel> viewModel : viewModelMap.entrySet()) {
            if (viewModel.getKey().equals(modelClass.getCanonicalName())) {
                return (T) viewModel.getValue();
            }
        }
        return null;
    }
}
