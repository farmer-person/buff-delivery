/*
    ***************************************
    * Author: Farmer                      *
    * Mail:   iceyee.studio@qq.com        *
    * Git:    https://github.com/iceyee   *
    ***************************************
*/
package farmer;

/**
文件操作<br>
@author Farmer
@version 2019.11.19
*/
public class Io {

    /** 
    清空文件 <br>
    @param stringFileName 目标文件名
    @return 正常返回true, 异常返回false
    */
    public static boolean clear (String stringFileName) {
        /*{{{*/
        try {
            java.io.FileOutputStream outputStreamFile = new java.io.FileOutputStream (stringFileName);
            outputStreamFile.close ();
            return true;
        }
        catch (java.io.FileNotFoundException e) {
            try {
                // 创建文件夹, 然后继续操作
                boolean booleanCreated
                    = new java.io.File (stringFileName)
                                 .getAbsoluteFile ()
                                 .getParentFile ()
                                 .mkdirs ();
                if (! booleanCreated) {
                    return false;
                }
                //
                java.io.FileOutputStream outputStreamFile = new java.io.FileOutputStream (stringFileName);
                outputStreamFile.close ();
                return true;
            }
            catch (java.io.IOException e1) {
                e1.printStackTrace ();
                farmer.Log.error ("/tmp/farmer.log", e1);
                return false;
            }
            finally {
                //
            }
        }
        catch (java.io.IOException e) {
            e.printStackTrace ();
            farmer.Log.error ("/tmp/farmer.log", e);
            return false;
        }
        finally {
            //
        }
        /*}}}*/
    }

    /** 
    写入文件, 覆盖原来内容 <br>
    @param stringFileName 目标文件名
    @param stringContent 欲写入的内容
    @return 正常返回true, 异常返回false
    */
    public static boolean write (String stringFileName,
                                 String stringContent) {
        /*{{{*/
        try {
            java.io.FileOutputStream outputStreamFile = new java.io.FileOutputStream (stringFileName);
            outputStreamFile.write (stringContent.getBytes ());
            outputStreamFile.close ();
            outputStreamFile = null;
            return true;
        }
        catch (java.io.FileNotFoundException e) {
            try {
                // 创建文件夹, 然后继续操作
                boolean booleanCreated
                    = new java.io.File (stringFileName)
                                 .getAbsoluteFile ()
                                 .getParentFile ()
                                 .mkdirs ();
                if (! booleanCreated) {
                    return false;
                }
                //
                java.io.FileOutputStream outputStreamFile = new java.io.FileOutputStream (stringFileName);
                outputStreamFile.write (stringContent.getBytes ());
                outputStreamFile.close ();
                outputStreamFile = null;
                return true;
            }
            catch (java.io.IOException e1) {
                e1.printStackTrace ();
                farmer.Log.error ("/tmp/farmer.log", e1);
                return false;
            }
            finally {
                //
            }
        }
        catch (java.io.IOException e) {
            e.printStackTrace ();
            farmer.Log.error ("/tmp/farmer.log", e);
            return false;
        }
        finally {
            //
        }
        /*}}}*/
    }

    /** 
    写入文件, 在末尾追加 <br>
    @param stringFileName 目标文件名
    @param stringContent 欲写入的内容
    @return 正常返回true, 异常返回false
    */
    public static boolean append (String stringFileName,
                                  String stringContent) {
        /*{{{*/
        try {
            java.io.FileOutputStream outputStreamFile = new java.io.FileOutputStream (stringFileName,
                                                                                      true);
            outputStreamFile.write (stringContent.getBytes ());
            outputStreamFile.close ();
            outputStreamFile = null;
            return true;
        }
        catch (java.io.FileNotFoundException e) {
            try {
                // 创建文件夹, 然后继续操作
                boolean booleanCreated
                    = new java.io.File (stringFileName)
                                 .getAbsoluteFile ()
                                 .getParentFile ()
                                 .mkdirs ();
                if (! booleanCreated) {
                    return false;
                }
                //
                java.io.FileOutputStream outputStreamFile = new java.io.FileOutputStream (stringFileName);
                outputStreamFile.write (stringContent.getBytes ());
                outputStreamFile.close ();
                outputStreamFile = null;
                return true;
            }
            catch (java.io.IOException e1) {
                e1.printStackTrace ();
                farmer.Log.error ("/tmp/farmer.log", e1);
                return false;
            }
            finally {
                //
            }
        }
        catch (java.io.IOException e) {
            e.printStackTrace ();
            farmer.Log.error ("/tmp/farmer.log", e);
            return false;
        }
        finally {
            //
        }
        /*}}}*/
    }

    /** 
    读取文件 <br>
    @param stringFileName 目标文件名
    @return 正常返回文件内容一, 异常返回空字符串""
    */
    public static String read (String stringFileName) {
        /*{{{*/
        try {
            java.io.File file = new java.io.File (stringFileName);
            java.io.FileInputStream inputStreamFile = new java.io.FileInputStream (file);
            java.io.ByteArrayOutputStream byteArrayOutputStream = new java.io.ByteArrayOutputStream ();
            while (true) {
                byte[] bytesBuffer = new byte[0x3ff];
                int intReaded = inputStreamFile.read (bytesBuffer);
                if (-1 == intReaded) {
                    break;
                }
                //
                byteArrayOutputStream.write (bytesBuffer, 0, intReaded);
            }
            byte[] bytesOutput = byteArrayOutputStream.toByteArray ();
            byteArrayOutputStream.close ();
            inputStreamFile.close ();
            return new String (bytesOutput);
        }
        catch (java.io.IOException e) {
            e.printStackTrace ();
            farmer.Log.error ("/tmp/farmer.log", e);
            return "";
        }
        finally {
            //
        }
        /*}}}*/
    }

    /** 
    读取xml格式文件, 返回document对象 <br>
    @param stringFileName 目标文件名
    @return 正常返回{@link org.w3c.dom.Document}对象, 异常返回null
    */
    public static org.w3c.dom.Document xmlRead (String stringFileName) {
        /*{{{*/
        try {
            return javax.xml.parsers.DocumentBuilderFactory.newInstance ()
                                                           .newDocumentBuilder ()
                                                           .parse (stringFileName);
        }
        catch (javax.xml.parsers.ParserConfigurationException e) {
            e.printStackTrace ();
            farmer.Log.error ("/tmp/farmer.log", e);
            return null;
        }
        catch (org.xml.sax.SAXException e) {
            e.printStackTrace ();
            farmer.Log.error ("/tmp/farmer.log", e);
            return null;
        }
        catch (java.io.IOException e) {
            e.printStackTrace ();
            farmer.Log.error ("/tmp/farmer.log", e);
            return null;
        }
        finally {
            //
        }
        /*}}}*/
    }

    /** 
    写入xml格式文件 <br>
    @param stringFileName 目标文件名
    @param document 想要写入的{@link org.w3c.dom.Document}对象
    @return 正常返回true, 异常返回false
    */
    public static boolean xmlWrite (String stringFileName,
                                    org.w3c.dom.Document document) {
        /*{{{*/
        try {
            if (null == stringFileName
                  || null == document) {
                throw new farmer.exception.FarmerException (new IllegalArgumentException ());
            }
            //
            javax.xml.transform.Transformer transformer
                = javax.xml.transform.TransformerFactory.newInstance ()
                                                        .newTransformer ();
            transformer.setOutputProperty (javax.xml.transform.OutputKeys.INDENT, "yes");
            transformer.setOutputProperty (javax.xml.transform.OutputKeys.METHOD, "xml");
            transformer.setOutputProperty (javax.xml.transform.OutputKeys.STANDALONE,
                                           "yes");
            transformer.setOutputProperty (javax.xml.transform.OutputKeys.VERSION, "1.0");
            javax.xml.transform.dom.DOMSource domSource = new javax.xml.transform.dom.DOMSource (document);
            javax.xml.transform.stream.StreamResult streamResult
                = new javax.xml.transform.stream.StreamResult (stringFileName);
            transformer.transform (domSource, streamResult);
            return true;
        }
        catch (farmer.exception.FarmerException e) {
            e.printStackTrace ();
            farmer.Log.error ("/tmp/farmer.log", e);
            return false;
        }
        catch (javax.xml.transform.TransformerConfigurationException e) {
            e.printStackTrace ();
            farmer.Log.error ("/tmp/farmer.log", e);
            return false;
        }
        catch (javax.xml.transform.TransformerException e) {
            e.printStackTrace ();
            farmer.Log.error ("/tmp/farmer.log", e);
            return false;
        }
        finally {
            //
        }
        /*}}}*/
    }

    /** 
    格式化xml文件 <br>
    @param stringFileName 目标文件名
    @return 正常返回true, 异常返回false
    */
    public static boolean xmlFormat (String stringFileName) {
        /*{{{*/
        try {
            if (null == stringFileName) {
                throw new farmer.exception.FarmerException ("无效文件名");
            }
            //
            String stringContent = read (stringFileName);
            if (0 == stringContent.length ()) {
                throw new farmer.exception.FarmerException ("空文件");
            }
            //
            stringContent = stringContent.replaceAll (">\\s*<", "><");
            stringContent += "\n";
            write (stringFileName, stringContent);
            org.w3c.dom.Document document = xmlRead (stringFileName);
            if (null == document) {
                throw new farmer.exception.FarmerException ("无效内容");
            }
            else {
                xmlWrite (stringFileName, document);
            }
            return true;
        }
        catch (farmer.exception.FarmerException e) {
            e.printStackTrace ();
            farmer.Log.error ("/tmp/farmer.log", e);
            return false;
        }
        finally {
            //
        }
        /*}}}*/
    }

    /** 
    执行Xpath <br>
    @param document 欲执行xpath的{@link org.w3c.dom.Document}目标对象
    @param stringXpath XPath语句
    @return 正常返回目标内容, 异常返回空字符串""
    */
    public static String xmlXpath (org.w3c.dom.Document document,
                                   String stringXpath) {
        /*{{{*/
        try {
            javax.xml.xpath.XPath xpath = javax.xml.xpath.XPathFactory.newInstance ()
                                                                      .newXPath ();
            return xpath.evaluate (stringXpath, document);
        }
        catch (javax.xml.xpath.XPathExpressionException e) {
            e.printStackTrace ();
            farmer.Log.error ("/tmp/farmer.log", e);
            return "";
        }
        finally {
            //
        }
        /*}}}*/
    }
}
