package com.sc.act.api.commons.web.util;

import javax.validation.*;
import javax.validation.executable.ExecutableValidator;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Set;

/**

 * @ClassName BeanValidatorHelper
 * @Description 参数校验类
 * @date 2018年01月08日 下午15:15:49
 */
public class ValidatorHelper {

    /**
     * 获得验证器
     */
    private static final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();

    /**
     * 验证某个bean的参数
     *
     * @param object 被校验的参数
     * @throws ValidationException 如果参数校验不成功则抛出此异常
     */
    public static <T> void validateBean(T object) {
        Validator validator = factory.getValidator();
        // 执行验证
        Set<ConstraintViolation<T>> constraintViolations = validator.validate(object);
        // 如果有验证信息，则将第一个取出来包装成异常返回
        ConstraintViolation<T> constraintViolation = getFirst(constraintViolations);
        if (constraintViolation != null) {
            throw new ValidationException(constraintViolation.getMessage());
        }
    }

    public static <T> void validateMethodParameters(T object, Method method, Object[] parameterValues) {
        Validator validator = factory.getValidator();
        ExecutableValidator executableValidator = validator.forExecutables();
        // 执行验证
        Set<ConstraintViolation<T>> constraintViolations = executableValidator.validateParameters(object, method,
                parameterValues);
        // 如果有验证信息，则将第一个取出来包装成异常返回
        ConstraintViolation<T> constraintViolation = getFirst(constraintViolations);
        if (constraintViolation != null) {
            throw new ValidationException(constraintViolation.getMessage());
        }

    }

    // 进行返回值的校验
    public static <T> void validMethodReturnValue(T obj, Method method, Object returnValue) {
        Validator validator = factory.getValidator();
        ExecutableValidator validatorParam = validator.forExecutables();

        Set<ConstraintViolation<T>> constraintViolations = validatorParam.validateReturnValue(obj, method, returnValue);
        ConstraintViolation<T> constraintViolation = getFirst(constraintViolations);
        if (constraintViolation != null) {
            throw new ValidationException(constraintViolation.getMessage());
        }
    }

    // 进行构造参数校验
    public <T> void validConstructorParameters(Constructor<T> constructor, Object[] constructorsParams) {
        Validator validator = factory.getValidator();
        ExecutableValidator validatorParam = validator.forExecutables();

        Set<ConstraintViolation<T>> constraintViolationSet = validatorParam.validateConstructorParameters(constructor,
                constructorsParams);
        ConstraintViolation<T> constraintViolation = getFirst(constraintViolationSet);
        if (constraintViolation != null) {
            throw new ValidationException(constraintViolation.getMessage());
        }
    }

    private static <T> ConstraintViolation<T> getFirst(Set<ConstraintViolation<T>> constraintViolations) {
        ConstraintViolation<T> constraintViolationRet = null;

        for (ConstraintViolation<T> constraintViolation : constraintViolations) {
            constraintViolationRet = constraintViolation;
            break;
        }
        return constraintViolationRet;
    }

}
