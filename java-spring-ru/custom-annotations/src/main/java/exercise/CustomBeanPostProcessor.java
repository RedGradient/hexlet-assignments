package exercise;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.HashMap;

// BEGIN
@Configuration
public class CustomBeanPostProcessor implements BeanPostProcessor {
    private HashMap<String, Class> annotatedBeans = new HashMap<>();
    private HashMap<String, String> loggingLevels = new HashMap<>();
    private final Logger LOGGER = LoggerFactory.getLogger(CustomBeanPostProcessor.class);

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass().isAnnotationPresent(Inspect.class)) {
            var loggingLevel = bean.getClass().getAnnotation(Inspect.class).level();
            annotatedBeans.put(beanName, bean.getClass());
            loggingLevels.put(beanName, loggingLevel);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (!annotatedBeans.containsKey(beanName)) {
            return bean;
        }

        var beanClass = annotatedBeans.get(beanName);
        return Proxy.newProxyInstance(
                beanClass.getClassLoader(),
                beanClass.getInterfaces(),

                (proxy, method, args) -> {
                    var log = String.format(
                            "Was called method: %s() with arguments: %s",
                            method.getName(),
                            Arrays.toString(args)
                    );

                    switch (loggingLevels.get(beanName)) {
                        case "info" -> LOGGER.info(log);
                        case "debug" -> LOGGER.debug(log);
                    }

                    return method.invoke(bean, args);
                }
        );
    }
}
// END
