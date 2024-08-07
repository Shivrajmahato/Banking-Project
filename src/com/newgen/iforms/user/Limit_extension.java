/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.newgen.iforms.user;

import com.newgen.iforms.custom.IFormListenerFactory;
import com.newgen.iforms.custom.IFormReference;
import com.newgen.iforms.custom.IFormServerEventHandler;

/**
 * @author shivaraj
 */
public class Limit_extension implements IFormListenerFactory {

    public IFormServerEventHandler getClassInstance(IFormReference iFormObj) {
        return new LimitExtensionCustom();
    }
}
