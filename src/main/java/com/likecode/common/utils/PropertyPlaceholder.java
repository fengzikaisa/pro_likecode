package com.likecode.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.function.Consumer;

/**
 * 加载参数配置文件
 */
public class PropertyPlaceholder extends PropertyPlaceholderConfigurer {

    private static Map<String, String> propertyMap;

    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props)
            throws BeansException {
        super.processProperties(beanFactoryToProcess, props);
        setPropertyMap(new HashMap<>());
        for (Object key : props.keySet()) {
            String keyStr = key.toString();
            String value = props.getProperty(keyStr);
            propertyMap.put(keyStr, value);
        }
    }

    /**
     * 获取配置
     *
     * @param name
     * @return
     */
    public static Object getProperty(String name) {
        return propertyMap.get(name);
    }

    /**
     * 返回字符串
     *
     * @param name
     * @return
     */
    public static String getPropertyString(String name) {
        return propertyMap.get(name);
    }

    private static void setPropertyMap(Map<String, String> propertyMap) {
        PropertyPlaceholder.propertyMap = propertyMap;
    }

    public static Map<String, String> getCustomInfo() {
        Map<String, String> map = new HashMap<>();
        String prefix = "system.";
        propertyMap.keySet().forEach(new Consumer<String>() {
            @Override
            public void accept(String key) {
                if (key.startsWith(prefix)) {
                    String val = propertyMap.get(key);
                    map.put(key.replace(prefix, ""), val);
                }
            }
        });
        return map;
    }
}
