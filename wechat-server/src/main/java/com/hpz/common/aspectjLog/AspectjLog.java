package com.hpz.common.aspectjLog;


import com.hpz.common.CommonUtil;
import com.hpz.common.ServiceException;
import com.hpz.out.model.Result;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.aspectj.lang.ProceedingJoinPoint;

/**
 * 日志格式  类名.方法名#参数(多个参数 param1#param2)#结果#调用时间#状态码(目前就两个)
 * Created by mao on 2015/6/24.
 */
public class AspectjLog {

    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = null;
        long times = 0;
        try {

            result = pjp.proceed(pjp.getArgs());
            return result;
        } catch (ServiceException e) {
            e.printStackTrace();
            Result exResult1 = new Result();
            exResult1.getMsg().add(e.getLocalizedMessage());
            exResult1.setSuccess(false);
            return exResult1;
        } catch (Exception e) {
            e.printStackTrace();
            Result exResult2 = new Result();
            exResult2.getMsg().add("系统异常");
            exResult2.setSuccess(false);
            return exResult2;
        }finally {
            times = System.currentTimeMillis() - startTime;
            Object[] x=pjp.getArgs();
            StringBuffer sb = new StringBuffer();
            for(Object xx:x){
                if(xx instanceof java.lang.String){
                    sb.append(xx + "-");
                }else{
                    sb.append(ToStringBuilder.reflectionToString(xx) + "-");
                }
            }
            CommonUtil.info("\n ===> Service input:["+pjp.getTarget()+"#"+pjp.getSignature().getName()+"#{"+sb.toString()+"}#"+times+"ms#result:"+ToStringBuilder.reflectionToString(result)+"]");
        }

    }


}
