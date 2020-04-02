package com.test.aspectj.xml;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

//切面类
/**上述代码中，分别定义了几种不同的通知类型方法，
在这些方法中，通过 JoinPoint 参数可以获得目标对象的类名、目标方法名和目标方法参数等。
需要注意的是，环绕通知必须接收一个类型为 ProceedingJoinPoint 的参数，返回值必须是 Object 类型，且必须抛出异常。
异常通知中可以传入 Throwable 类型的参数，用于输出异常信息。
*/
public class MyAspect {

    // 前置通知
    public void myBefore(JoinPoint joinPoint){
        System.out.print("前置通知，目标：");
        System.out.print(joinPoint.getTarget() + "方法名称:");
        System.out.println(joinPoint.getSignature().getName());
    }

    // 后置通知
    public void myAfterReturning(JoinPoint joinPoint) {
        System.out.print("后置通知，方法名称：" + joinPoint.getSignature().getName());
    }

    // 环绕通知
    public Object myAround(ProceedingJoinPoint proceedingJoinPoint)
            throws Throwable {
        System.out.println("环绕开始"); // 开始
        Object obj = proceedingJoinPoint.proceed(); // 执行当前目标方法
        System.out.println("环绕结束"); // 结束
        return obj;
    }

    // 异常通知
    public void myAfterThrowing(JoinPoint joinPoint, Throwable e) {
        System.out.println("异常通知" + "出错了" + e.getMessage());
    }

    // 最终通知
    public void myAfter() {
        System.out.println("最终通知");
    }
}
