package com.newgen.iforms.user;

import com.newgen.iforms.custom.IFormListenerFactory;
import com.newgen.iforms.custom.IFormReference;
import com.newgen.iforms.custom.IFormServerEventHandler;

public class BPMLOS implements IFormListenerFactory {

    public IFormServerEventHandler getClassInstance(IFormReference iFormObj) {
        return new LoansCustom();
    }
}
