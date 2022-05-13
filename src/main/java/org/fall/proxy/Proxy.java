package org.fall.proxy;

import org.fall.proxy.aspect.MethodAspect;
import org.fall.proxy.enhance.ASMWeaver;
import org.fall.util.FallReflectUtility;
import org.fall.util.FallClassLoader;
import org.fall.intercept.manage.InterceptorChain;
import org.fall.intercept.manage.FallInterceptorManager;
import org.fall.core.Fallable;
import org.fall.core.policy.ClassNamingPolicy;
import org.fall.core.policy.MethodNamingPolicy;
import org.aopalliance.intercept.MethodInterceptor;


import java.util.List;
import java.util.ArrayList;
import java.lang.reflect.Method;

/**
 * Copyright 2009 by
 *
 * @author : /\/\[]|-|/-\/\/\/\/\/-\|)
 *         Date: Oct 16, 2009
 *         Time: 4:54:56 PM
 * @version : 1.0
 */
public class Proxy
{
    List<MethodAspect> methodAspects;
    FallClassLoader fallLoader = new FallClassLoader();


    public Proxy(List<MethodAspect> methodAspects)
    {
        this.methodAspects = methodAspects;
    }

    public <T> T newInstance(Class<T> type)
    {
//        Class<? extends T> clazz = get(type);
//        return clazz.newInstance();
        return createProxyInstance(type);
    }

    public <T> Class<? extends T> get(Class<T> type)
    {
        return createProxy(type, null);
    }

    private <T> T createProxyInstance(Class<T> type)
    {
        try
        {
            FallInterceptorManager manager = new FallInterceptorManager();
            Class<T> clazz = createProxy(type, manager);

            T t = clazz.getDeclaredConstructor().newInstance();

            if (t instanceof Fallable) ((Fallable) t).setInterceptorManager(manager);
            return t;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }

    private <T> Class<T> createProxy(Class<T> type, FallInterceptorManager manager)
    {
        List<InterceptorChain> chains = filter(type);

        if (chains == null) return type;

        ASMWeaver weaver = new ASMWeaver(type, chains);
        weaver.setClassNamingPolicy(new ClassNamingPolicy());
        weaver.setMethodNamingPolicy(new MethodNamingPolicy());
        byte[] byteCodes = weaver.generate();
/*
        try
        {
            FileOutputStream outputStream=new FileOutputStream(new File("f://text.class"),false);
            outputStream.write(byteCodes);
            outputStream.close();
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
*/

        Class<T> clazz = fallLoader.defineClass(weaver.newName(), byteCodes);


        if (manager != null) for (InterceptorChain chain : chains) manager.register(chain.getMethod(), chain);

        return clazz;
    }

    private List<InterceptorChain> filter(Class type)
    {
        List<MethodAspect> applicableAspects = new ArrayList<MethodAspect>();

        for (MethodAspect methodAspect : methodAspects)
        {
            if (methodAspect.matches(type)) applicableAspects.add(methodAspect);
        }

        if (applicableAspects.isEmpty()) return null;

        List<Method> methods = new ArrayList<Method>();
        FallReflectUtility.getMethods(type, null, methods, null, null);

        List<MethodInterceptorsPair> methodInterceptorsPairs
                = new ArrayList<MethodInterceptorsPair>();
        for (Method method : methods)
        {
            methodInterceptorsPairs.add(new MethodInterceptorsPair(method));
        }


        boolean anyMatched = false;
        for (MethodAspect methodAspect : applicableAspects)
        {
            for (MethodInterceptorsPair pair : methodInterceptorsPairs)
            {
                if (methodAspect.matches(pair.method))
                {
                    pair.addAll(methodAspect.getInterceptors());
                    anyMatched = true;
                }
            }
        }

        if (!anyMatched) return null;

        ArrayList<InterceptorChain> chains = new ArrayList<InterceptorChain>();
        for (MethodInterceptorsPair pair : methodInterceptorsPairs)
        {
            if (pair.hasInterceptors()) chains.add(new InterceptorChain(pair.interceptors, pair.method));
        }

        return chains;
    }

    static class MethodInterceptorsPair
    {

        final Method method;
        List<MethodInterceptor> interceptors;

        public MethodInterceptorsPair(Method method)
        {
            this.method = method;
        }

        void addAll(List<MethodInterceptor> interceptors)
        {
            if (this.interceptors == null)
            {
                this.interceptors = new ArrayList<MethodInterceptor>();
            }
            this.interceptors.addAll(interceptors);
        }

        boolean hasInterceptors()
        {
            return interceptors != null;
        }
    }
}
