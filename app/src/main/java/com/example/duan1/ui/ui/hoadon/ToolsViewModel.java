package com.example.duan1.ui.ui.hoadon;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ToolsViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ToolsViewModel() {

    }

    public LiveData<String> getText() {
        return mText;
    }
}