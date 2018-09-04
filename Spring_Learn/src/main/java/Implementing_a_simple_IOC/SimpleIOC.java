package Implementing_a_simple_IOC;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.FileInputStream;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * implement simper  IOC
 */
public class SimpleIOC {

    /**bean容器*/
    private Map<String,Object> beansMap=new HashMap();

    /**
     * 构造器初始化文件
     * @param loacaion
     * @throws Exception
     */
    public SimpleIOC(String loacaion)  throws Exception{
        loadBenas(loacaion);
    }

    /**
     * 加载xml配置文件
     * @param loacaion 文件路径
     * @throws Exception
     */
    private void loadBenas(String loacaion) throws Exception{

          //根据路径读取Bean配置文件_即把XML文档转化为输入流
        InputStream ins = new FileInputStream(loacaion);
          //创建dom解析器工厂
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
           //得到dom解析器
        DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();
           //解析输入流得到整个Document对象。————这样可以利用DOM特性对整个xml文档进行操作
        Document doc = documentBuilder.parse(ins);
           //得到xml文档的根节点
        Element element=doc.getDocumentElement();
           //的到节点的子节点
        NodeList nodeList=element.getChildNodes();

        if (nodeList == null){
            throw new RuntimeException("Beans为空");
        }
           //遍历<bean>标签
        for (int i = 0; i <nodeList.getLength() ; i++) {
            Node node = nodeList.item(i);
            if (node instanceof Element){
                Element ele = (Element) node;
                String id = ele.getAttribute("id");
                String classname = ele.getAttribute("class");

                //加载beanClass
                Class beanClass = null;
                try {
                      //返回与给定字符串名称的类或接口相关联的类对象
                    beanClass = Class.forName(classname);
                }catch (ClassNotFoundException e){
                     e.printStackTrace();
                     return;
                }

                 //创建bean 即创建新的类实例
                Object bean = beanClass.newInstance();

                 //遍历property标签
                NodeList propertyNodes = ele.getElementsByTagName("property");
                for (int j = 0; j < propertyNodes.getLength(); j++) {
                    Node propertyNode = propertyNodes.item(i);
                    if (propertyNode instanceof Element){
                        Element propertyEle = (Element) propertyNode;
                        String propertyName = propertyEle.getAttribute("name");
                        String propertyVal = propertyEle.getAttribute("value");

                          //返回一个Field对象，它反映此表示的类或接口的指定已声明字段类对象。
                        Field field = bean.getClass().getDeclaredField(propertyName);
                         //利用反射将bean相关字段设置为可访问 <note:利用反射可以访问一个类的私有属性>
                        field.setAccessible(true);

                         //将属性值填充到相关字段中
                        if (propertyVal != null && propertyVal.length() > 0){
                            field.set(bean,propertyVal);
                        }else {
                            String ref = propertyEle.getAttribute("ref");
                            if(ref == null && ref.length() == 0){
                                throw new IllegalArgumentException("ref not config");
                            }
                             //将引用填充到相关字段中
                            field.set(bean,getBean(ref));
                        }
                        //将bean注册到bean容器中
                        registerBean(id,bean);
                    }
                }
            }
        }
    }

    /**
     * 从bean容器中获取bean
     * @param name
     * @return
     */
    public Object getBean(String name) {
        Object bean = beansMap.get(name);
        if (bean == null){
            throw new IllegalStateException("there is no bean with name:"+name);
        }
        return bean;
    }

    /**
     * 向bean容器中注册bean
     * @param id
     * @param bean
     */
    private void registerBean(String id, Object bean) {
        beansMap.put(id,bean);
    }
}
