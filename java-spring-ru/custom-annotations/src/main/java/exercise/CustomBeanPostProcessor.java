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
    private HashMap<String, String> beans = new HashMap<>();
    private final Logger LOGGER = LoggerFactory.getLogger(CustomBeanPostProcessor.class);

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean.getClass().isAnnotationPresent(Inspect.class)) {
            var loggingLevel = bean.getClass().getAnnotation(Inspect.class).level();
            beans.put(beanName, loggingLevel);
        }
        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (beans.containsKey(beanName)) {
            return Proxy.newProxyInstance(
                bean.getClass().getClassLoader(),
                bean.getClass().getInterfaces(),
                (proxy, method, args) -> {
                    var log = String.format(
                            "Was called method: %s() with arguments: %s",
                            method.getName(),
                            Arrays.toString(args)
                    );

                    var loggingLevel = beans.get(beanName);
                    switch (loggingLevel) {
                        case "info" -> LOGGER.info(log);
                        case "debug" -> LOGGER.debug(log);
                    }

                    return method.invoke(bean, args);
                }
            );
        }
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }
}
// END
