package com.newgen.iforms.user;

import java.io.Serializable;

public class XMLParser
  implements Serializable
{
  private String parseString;
  private String copyString;
  private int IndexOfPrevSrch;

  public XMLParser()
  {
  }

  public XMLParser(String parseThisString)
  {
    this.copyString = new String(parseThisString);
    this.parseString = toUpperCase(this.copyString, 0, 0);
  }

  public void setInputXML(String ParseThisString)
  {
    if (ParseThisString != null) {
      this.copyString = new String(ParseThisString);
      this.parseString = toUpperCase(this.copyString, 0, 0);
      this.IndexOfPrevSrch = 0;
    } else {
      this.parseString = null;
      this.copyString = null;
      this.IndexOfPrevSrch = 0;
    }
  }

  public String getServiceName()
  {
    try
    {
      return new String(this.copyString.substring(this.parseString.indexOf(toUpperCase("<Option>", 0, 0)) + new String(toUpperCase("<Option>", 0, 0)).length(), this.parseString.indexOf(toUpperCase("</Option>", 0, 0))));
    } catch (StringIndexOutOfBoundsException e) {
      throw e;
    }
  }

  public String getServiceName(char chr)
  {
    try
    {
      if (chr == 'A') {
        return new String(this.copyString.substring(this.parseString.indexOf("<AdminOption>".toUpperCase()) + new String("<AdminOption>".toUpperCase()).length(), this.parseString.indexOf("</AdminOption>".toUpperCase())));
      }
      return "";
    } catch (StringIndexOutOfBoundsException localStringIndexOutOfBoundsException) {
    }
    return "NoServiceFound";
  }

  public boolean validateXML()
  {
    try
    {
      return this.parseString.indexOf("<?xml version=\"1.0\"?>".toUpperCase()) == -1;
    } catch (StringIndexOutOfBoundsException localStringIndexOutOfBoundsException) {
    }
    return false;
  }

  public String getValueOf(String valueOf)
  {
    try
    {
      return new String(this.copyString.substring(this.parseString.indexOf("<" + toUpperCase(valueOf, 0, 0) + ">") + valueOf.length() + 2, this.parseString.indexOf("</" + toUpperCase(valueOf, 0, 0) + ">")));
    } catch (StringIndexOutOfBoundsException localStringIndexOutOfBoundsException) {
    }
    return "";
  }

  public String getValueOf(String valueOf, String type)
  {
    try
    {
      if (type.equalsIgnoreCase("Binary")) {
        int startPos = this.copyString.indexOf("<" + valueOf + ">");
        if (startPos == -1) {
          return "";
        }
        int endPos = this.copyString.lastIndexOf("</" + valueOf + ">");
        startPos += new String("<" + valueOf + ">").length();
        return this.copyString.substring(startPos, endPos);
      }
      return "";
    } catch (StringIndexOutOfBoundsException localStringIndexOutOfBoundsException) {
    }
    return "";
  }

  public String getValueOf(String valueOf, boolean fromlast)
  {
    try
    {
      if (fromlast) {
        return new String(this.copyString.substring(this.parseString.indexOf("<" + toUpperCase(valueOf, 0, 0) + ">") + valueOf.length() + 2, this.parseString.lastIndexOf("</" + toUpperCase(valueOf, 0, 0) + ">")));
      }
      return new String(this.copyString.substring(this.parseString.indexOf("<" + toUpperCase(valueOf, 0, 0) + ">") + valueOf.length() + 2, this.parseString.indexOf("</" + toUpperCase(valueOf, 0, 0) + ">")));
    } catch (StringIndexOutOfBoundsException localStringIndexOutOfBoundsException) {
    }
    return "";
  }

  public String getValueOf(String valueOf, int start, int end)
  {
    try
    {
      if (start >= 0) {
        int endIndex = this.parseString.indexOf("</" + toUpperCase(valueOf, 0, 0) + ">", start);
        if ((endIndex > start) && ((end == 0) || (end >= endIndex))) {
          return new String(this.copyString.substring(this.parseString.indexOf("<" + toUpperCase(valueOf, 0, 0) + ">", start) + valueOf.length() + 2, endIndex));
        }
      }
      return "";
    } catch (StringIndexOutOfBoundsException localStringIndexOutOfBoundsException) {
    }
    return "";
  }

  public int getStartIndex(String tag, int start, int end)
  {
    try
    {
      if (start >= 0) {
        int startIndex = this.parseString.indexOf("<" + toUpperCase(tag, 0, 0) + ">", start);
        if ((startIndex >= start) && ((end == 0) || (end >= startIndex))) {
          return startIndex + tag.length() + 2;
        }
      }
      return -1;
    } catch (StringIndexOutOfBoundsException localStringIndexOutOfBoundsException) {
    }
    return -1;
  }

  public int getEndIndex(String tag, int start, int end)
  {
    try
    {
      if (start >= 0) {
        int endIndex = this.parseString.indexOf("</" + toUpperCase(tag, 0, 0) + ">", start);
        if ((endIndex > start) && ((end == 0) || (end >= endIndex))) {
          return endIndex;
        }
      }
      return -1;
    } catch (StringIndexOutOfBoundsException localStringIndexOutOfBoundsException) {
    }
    return -1;
  }

  public int getTagStartIndex(String tag, int start, int end)
  {
    try
    {
      if (start >= 0) {
        int startIndex = this.parseString.indexOf("<" + toUpperCase(tag, 0, 0) + ">", start);
        if ((startIndex >= start) && ((end == 0) || (end >= startIndex))) {
          return startIndex;
        }
      }
      return -1;
    } catch (StringIndexOutOfBoundsException localStringIndexOutOfBoundsException) {
    }
    return -1;
  }

  public int getTagEndIndex(String tag, int start, int end)
  {
    try
    {
      if (start >= 0) {
        int endIndex = this.parseString.indexOf("</" + toUpperCase(tag, 0, 0) + ">", start);
        if ((endIndex > start) && ((end == 0) || (end >= endIndex))) {
          return endIndex + tag.length() + 3;
        }
      }
      return -1;
    } catch (StringIndexOutOfBoundsException localStringIndexOutOfBoundsException) {
    }
    return -1;
  }

  public String getFirstValueOf(String valueOf)
  {
    try
    {
      this.IndexOfPrevSrch = this.parseString.indexOf("<" + toUpperCase(valueOf, 0, 0) + ">");
      return new String(this.copyString.substring(this.IndexOfPrevSrch + valueOf.length() + 2, this.parseString.indexOf("</" + toUpperCase(valueOf, 0, 0) + ">")));
    } catch (StringIndexOutOfBoundsException localStringIndexOutOfBoundsException) {
    }
    return "";
  }

  public String getFirstValueOf(String valueOf, int start)
  {
    try
    {
      this.IndexOfPrevSrch = this.parseString.indexOf("<" + toUpperCase(valueOf, 0, 0) + ">", start);
      return new String(this.copyString.substring(this.IndexOfPrevSrch + valueOf.length() + 2, this.parseString.indexOf("</" + toUpperCase(valueOf, 0, 0) + ">", start)));
    } catch (StringIndexOutOfBoundsException localStringIndexOutOfBoundsException) {
    }
    return "";
  }

  public String getNextValueOf(String valueOf)
  {
    try
    {
      this.IndexOfPrevSrch = this.parseString.indexOf("<" + toUpperCase(valueOf, 0, 0) + ">", this.IndexOfPrevSrch + valueOf.length() + 2);
      return new String(this.copyString.substring(this.IndexOfPrevSrch + valueOf.length() + 2, this.parseString.indexOf("</" + toUpperCase(valueOf, 0, 0) + ">", this.IndexOfPrevSrch)));
    } catch (StringIndexOutOfBoundsException localStringIndexOutOfBoundsException) {
    }
    return "";
  }

  public int getNoOfFields(String tag)
  {
    int noOfFields = 0;
    int beginPos = 0;
    try {
      tag = toUpperCase(tag, 0, 0) + ">";
      while (this.parseString.indexOf("<" + tag, beginPos) != -1) {
        noOfFields++;
        beginPos = this.parseString.indexOf("</" + tag, beginPos);
        if (beginPos == -1) {
          break;
        }
        beginPos += tag.length() + 2;
      }
    } catch (StringIndexOutOfBoundsException localStringIndexOutOfBoundsException) {
    }
    return noOfFields;
  }

  public int getNoOfFields(String tag, int startPos, int endPos)
  {
    int noOfFields = 0;
    int beginPos = startPos;
    try {
      tag = toUpperCase(tag, 0, 0) + ">";
      do
      {
        beginPos = this.parseString.indexOf("</" + tag, beginPos) + tag.length() + 2;
        if ((beginPos != -1) && ((beginPos <= endPos) || (endPos == 0))) {
          noOfFields++;
        }

        if (this.parseString.indexOf("<" + tag, beginPos) == -1)
          break;
      }
      while ((beginPos < endPos) || (endPos == 0));
    }
    catch (StringIndexOutOfBoundsException localStringIndexOutOfBoundsException) {
    }
    return noOfFields;
  }

  public String convertToSQLString(String strName)
  {
    try
    {
      int count = strName.indexOf("[");
      while (count != -1) {
        strName = strName.substring(0, count) + "[[]" + strName.substring(count + 1, strName.length());
        count = strName.indexOf("[", count + 2);
      }
    } catch (Exception localException) {
    }
    try {
      int e = strName.indexOf("_");
      while (e != -1) {
        strName = strName.substring(0, e) + "[_]" + strName.substring(e + 1, strName.length());
        e = strName.indexOf("_", e + 2);
      }
    } catch (Exception localException1) {
    }
    try {
      int e = strName.indexOf("%");
      while (e != -1) {
        strName = strName.substring(0, e) + "[%]" + strName.substring(e + 1, strName.length());
        e = strName.indexOf("%", e + 2);
      }
    } catch (Exception localException2) {
    }
    strName = strName.replace('?', '_');
    return strName;
  }

  public String getValueOf(String valueOf, String type, int from, int end)
  {
    try
    {
      if (type.equalsIgnoreCase("Binary")) {
        int startPos = this.copyString.indexOf("<" + valueOf + ">", from);
        if (startPos == -1) {
          return "";
        }
        int endPos = this.copyString.indexOf("</" + valueOf + ">", from);
        if (endPos > end) {
          return "";
        }
        startPos += new String("<" + valueOf + ">").length();
        return this.copyString.substring(startPos, endPos);
      }
      return "";
    } catch (StringIndexOutOfBoundsException localStringIndexOutOfBoundsException) {
    }
    return "";
  }

  public String toUpperCase(String valueOf, int begin, int end)
    throws StringIndexOutOfBoundsException
  {
    String returnStr = "";
    try {
      int count = valueOf.length();
      char[] strChar = new char[count];
      valueOf.getChars(0, count, strChar, 0);
      while (count-- > 0) {
        strChar[count] = Character.toUpperCase(strChar[count]);
      }
      returnStr = new String(strChar);
    } catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException) {
    }
    return returnStr;
  }

  public String changeValue(String ParseString, String TagName, String NewValue)
  {
    try
    {
      String ParseStringTmp = ParseString.toUpperCase();
      String StrTag = new String("<" + TagName + ">").toUpperCase();

      int StartIndex = ParseStringTmp.indexOf(StrTag) + StrTag.length();
      int EndIndex = ParseStringTmp.indexOf(new String("</" + TagName + ">").toUpperCase());

      String RetStr = ParseString.substring(0, StartIndex);
      return RetStr + NewValue + ParseString.substring(EndIndex);
    }
    catch (Exception localException) {
    }
    return "";
  }

  public void changeValue(String TagName, String NewValue)
  {
    try
    {
      String StrTag = "<" + TagName + ">".toUpperCase();

      int StartIndex = this.parseString.indexOf(StrTag);
      if (StartIndex > -1) {
        StartIndex += StrTag.length();
        int EndIndex = this.parseString.indexOf("</" + TagName + ">".toUpperCase());

        String RetStr = this.copyString.substring(0, StartIndex);
        this.copyString = (RetStr + NewValue + this.copyString.substring(EndIndex));
      } else {
        int EndIndex = StartIndex = this.parseString.lastIndexOf("</");
        String RetStr = this.copyString.substring(0, StartIndex);
        this.copyString = (RetStr + "<" + TagName + ">" + NewValue + "</" + TagName + ">" + this.copyString.substring(EndIndex));
      }
      this.parseString = toUpperCase(this.copyString, 0, 0);
    } catch (Exception localException) {
    }
  }

  public String toString() {
    return this.copyString;
  }

  public String ParseFieldValue(String pField, String pNode, String pNodeValue)
  {
    try
    {
      int iStartIndex = this.parseString.indexOf("<" + toUpperCase(pField, 0, 0) + "><" + toUpperCase(pNode, 0, 0) + ">" + toUpperCase(pNodeValue, 0, 0) + "</" + toUpperCase(pNode, 0, 0) + ">");

      if (iStartIndex != -1) {
        iStartIndex += pField.length() + 2;
        return new String(this.copyString.substring(iStartIndex, this.parseString.indexOf("</" + toUpperCase(pField, 0, 0) + ">", iStartIndex)));
      }

      return "";
    } catch (StringIndexOutOfBoundsException e) {
      e.printStackTrace();
    }
    return "";
  }

  public String getValueListXml(String pFieldName)
  {
    try
    {
      int iOffset = this.parseString.indexOf("<NAME>" + pFieldName.toUpperCase() + "</NAME>");
      if (iOffset != -1) {
        int iValueListIndex = this.parseString.indexOf("<VALUELIST>", iOffset);
        if (iValueListIndex != -1) {
          int iEndIndex = this.parseString.indexOf("</FIELD>", iOffset);
          if ((iEndIndex != -1) && (iEndIndex > iValueListIndex))
            return this.copyString.substring(iValueListIndex, iEndIndex);
        }
      }
    }
    catch (StringIndexOutOfBoundsException e) {
      e.printStackTrace();
    }
    return null;
  }

  public String getFieldValue(String pFieldName)
  {
    try
    {
      StringBuffer sbFieldName = new StringBuffer("<NAME>");
      sbFieldName.append(pFieldName.toUpperCase());
      sbFieldName.append("</NAME>");
      int iIndex = this.parseString.indexOf(sbFieldName.toString());
      if (iIndex != -1) {
        iIndex = this.parseString.indexOf("<VALUE>", iIndex);
        return this.copyString.substring(iIndex + 7, this.parseString.indexOf("</VALUE>", iIndex));
      }
    }
    catch (StringIndexOutOfBoundsException e) {
      e.printStackTrace();
    }
    return null;
  }
}
