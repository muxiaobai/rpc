/**
 * Project Name:io-tomcat
 * File Name:XmlUtils.java
 * Package Name:io.github.muxiaobai.io_tomcat.util
 * Date:2019年3月21日下午1:25:26
 * Copyright (c) 2019, All Rights Reserved.
 *
*/

package io.github.muxiaobai.io_tomcat.util;
import java.io.InputStream;
/**
 * ClassName:XmlUtils 
 * Function: TODO 
 * Reason:	 TODO 
 * Date:     2019年3月21日 下午1:25:26 
 * @author   Mu Xiaobai
 * @version  
 * @since    JDK 1.8	 
 */
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

    public class XmlUtils {
        //定义解析器和文档对象
        public SAXReader saxReader;
        public Document document;

        public  XmlUtils(String path){
            //获取解析器
            saxReader = new SAXReader();
            try {
                //获取文档对象
                document = saxReader.read(path);
            } catch (DocumentException e) {
                e.printStackTrace();
            }
        }

        /**
         * 根据节点名称获取内容
         * @param name 节点名称
         * @return 节点内容
         */
        public String getElementText(String name){
            //定位根节点
            Element root = document.getRootElement();
            
            List<Element> mapp = root.elements("servlet-mapping");
            
            List<Element> servlet = root.elements("servlet");
            
            String serveltName = "";
            String classpath = "";
            
            for (Element e : mapp) {
                if(e.element("url-pattern").getText().equals(name)){
                    serveltName = e.element("servlet-name").getText();
                    break;
                }
            }
            for (Element e : servlet) {
                if(e.element("servlet-name").getText().equals(serveltName)){
                    classpath = e.element("servlet-class").getText();
                    break;
                }
            }

            return classpath;
//            //根据名称定位节点
//            Element element = root.element(name);
//            //返回节点内容
//            return element.getText();
        }
        
        /**
         * 获取节点下的所有节点
         * @param root
         * @param name
         * @return
         */
        public  List<Element> getNodes(String name){
             Element root = document.getRootElement();
             return root.elements(name);
        }
        
        public static void main(String[] args) {
            XmlUtils xml = new XmlUtils(XmlUtils.class.getResource("/")+"web.xml");
            //System.out.println(xml.getElementText("/myhtml.html"));
            List<Element> listmapping = xml.getNodes("servlet-mapping");
            for (Element element : listmapping) {
                System.out.println(element.element("servlet-name").getText() );
                System.out.println(element.element("url-pattern").getText() );
            }
            List<Element> list = xml.getNodes("servlet");
            for (Element element : list) {
                System.out.println(element.element("servlet-name").getText() );
                System.out.println(element.element("servlet-class").getText() );
            }
        }
        public void init(Map<String, Object> servlet, Map<String, Object> servletMapping){
            InputStream io = null;
            try {
                //读取web.xml 
                //讲所有的类都存储到容器中 并且创造对象
                List<Element> list = this.getNodes("servlet");
                for (Element element : list) {
                    servlet.put(element.element("servlet-name").getText(), element.element("servlet-class").getText());                    
                }
                List<Element> listmapping = this.getNodes("servlet-mapping");
                for (Element element : listmapping) {
                    servletMapping.put(element.element("url-pattern").getText(),element.element("servlet-name").getText() );                    
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                if (io != null) {
                    try {
                        io.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
 }
