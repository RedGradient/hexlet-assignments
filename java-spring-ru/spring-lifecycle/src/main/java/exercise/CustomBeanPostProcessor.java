// Класс для самостоятельной работы

package exercise;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Configuration;

// BEGIN
@Configuration
public class CustomBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("\nCalled postProcessBeforeInitialization for bean: " + beanName);
        return BeanPostProcessor.super.postProcessAfterInitialization(bean, beanName);
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("Called postProcessAfterInitialization for bean: " + beanName + "\n");
        return BeanPostProcessor.super.postProcessBeforeInitialization(bean, beanName);
    }
}
// END
