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
 *
 * @author 97798
 */
public class Termloan_Overdue implements IFormListenerFactory
{
  public IFormServerEventHandler getClassInstance(IFormReference iFormObj)
  {
    String processName = iFormObj.getProcessName().toString();
    String activityName = iFormObj.getActivityName().toString();
    System.out.println("processName is: " + processName);
    System.out.println("activityName is: " + activityName);
    return new Termloan_Overdue_Custom();
  }
}
