package com.livingworld.ui.fragment.ecash;


import im.delight.android.webview.*;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;

import com.livingworld.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class EcashVerifyFragment extends Fragment implements AdvancedWebView.Listener {

    OnVerifyListener listener;

    public EcashVerifyFragment(){

    }

    public void setListener(OnVerifyListener listener) {
        this.listener = listener;
    }

    public interface OnVerifyListener{
        void onVerifySuccess();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.ecash_verify_fragment, container, false);

        AdvancedWebView webView = view.findViewById(R.id.webview);
        webView.setListener(getActivity(), this);
        Log.d("getArguments():","getArguments()():"+getArguments());
        String ticket = (String) getArguments().get("ticket");
        webView.loadUrl("http://188.166.220.62/h2h-register/Main/register?ticket="+ticket);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }

    private static final String TAG = EcashVerifyFragment.class.toString();

    @Override
    public void onPageStarted(String s, Bitmap bitmap) {
        Log.d(TAG, "url:"+s);
        if (s.contains("")) {
            listener.onVerifySuccess();
        }else{
            
        }
    }

    @Override
    public void onPageFinished(String s) {

    }

    @Override
    public void onPageError(int i, String s, String s1) {

    }

    @Override
    public void onDownloadRequested(String s, String s1, String s2, long l, String s3, String s4) {

    }

    @Override
    public void onExternalPageRequest(String s) {

    }
}
