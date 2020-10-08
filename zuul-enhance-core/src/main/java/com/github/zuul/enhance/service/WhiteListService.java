package com.github.zuul.enhance.service;

import com.github.zuul.enhance.listener.RefreshListner;
import com.github.zuul.enhance.name.Named;

public interface WhiteListService extends RefreshListner, Named {

    boolean checkWhiteList(String ip);

}
