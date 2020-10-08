package com.github.zuul.enhance.service;

import com.github.zuul.enhance.listener.RefreshListner;
import com.github.zuul.enhance.name.Named;

import java.util.List;

public interface BlackListService extends RefreshListner, Named {

    boolean checkBlackList(String ip);

}
