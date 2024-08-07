
package com.newgen.iforms.user;

import com.newgen.iforms.custom.IFormListenerFactory;
import com.newgen.iforms.custom.IFormReference;
import com.newgen.iforms.custom.IFormServerEventHandler;
import java.io.PrintStream;

public class LOS implements IFormListenerFactory
{
  public IFormServerEventHandler getClassInstance(IFormReference iFormObj)
  {
    String processName = iFormObj.getProcessName().toString();
    String activityName = iFormObj.getActivityName().toString();
    System.out.println("processName is: " + processName);
    System.out.println("activityName is: " + activityName);
    return new Loans_Custom();
  }
}
