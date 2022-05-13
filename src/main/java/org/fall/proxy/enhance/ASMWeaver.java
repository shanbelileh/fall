package org.fall.proxy.enhance;

import org.fall.intercept.manage.InterceptorChain;
import org.fall.intercept.manage.FallInterceptorManager;
import org.fall.util.TypeUtils;
import org.fall.core.Fallable;

import static org.fall.util.WeaverUtility.*;

import org.fall.proxy.MethodProxy;
import org.objectweb.asm.*;
import org.objectweb.asm.commons.GeneratorAdapter;
import org.objectweb.asm.commons.Method;

import static org.objectweb.asm.Opcodes.*;

import java.util.List;
import java.lang.reflect.Constructor;

/**
 * Copyright 2009 by
 *
 * @author : /\/\[]|-|/-\/\/\/\/\/-\|)
 * Date: Oct 30, 2009
 * Time: 1:27:58 PM
 * @version : 1.0
 */
public class ASMWeaver extends AbstractWeaver
{
    Class source;
    Type sourceType;
    Type newType;
    List<InterceptorChain> chains;
    Type fallable = Type.getType(Fallable.class);
    private static Method CLINIT = Method.getMethod("void <clinit>()");
    private static Type METHOD = Type
            .getType(java.lang.reflect.Method.class);
    private static Type CLASS = Type.getType(Class.class);
    private static Method GET_METHOD = Method
            .getMethod("java.lang.reflect.Method getMethod(java.lang.String, java.lang.Class[])");
    private static Type MANAGER = Type.getType(FallInterceptorManager.class);
    private static Method GO;
    private static Method setInterceptorManager;
    private static Method getInterceptorManager;
    private static Method getInterceptors;

    private static Type CHAIN = Type.getType(InterceptorChain.class);
    private static Method intercept;

    static
    {
        try
        {
            GO = getMethod(Fallable.class.getMethod("go", java.lang.reflect.Method.class, Object[].class));
            setInterceptorManager = getMethod(Fallable.class.getMethod("setInterceptorManager", FallInterceptorManager.class));
            getInterceptorManager = getMethod(Fallable.class.getMethod("getInterceptorManager"));
            getInterceptors = getMethod(FallInterceptorManager.class.getMethod("getInterceptors", java.lang.reflect.Method.class));
            intercept = getMethod(InterceptorChain.class.getMethod("intercept", Object.class, Object[].class));
        } catch (NoSuchMethodException e)
        {
            e.printStackTrace();
        }
    }

    public ASMWeaver(Class source, List<InterceptorChain> chains)
    {
        this.source = source;
        sourceType = Type.getType(source);
        this.chains = chains;
    }

    public byte[] generate()
    {

        if (source.isInterface())
        {
            String newName = getClassNamingPolicy().getName(source.getName(), classPredicate);
            newType = Type.getType("L" + newName.replace('.', '/') + ";");
            ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
            String[] interfaces = new String[]{Type.getInternalName(source), fallable.getInternalName()};
            writer.visit(TypeUtils.getJdkVersion(), ACC_PUBLIC | ACC_SUPER, newType.getInternalName(), null, "java/lang/Object"
                    , interfaces);

            weaveConstructors(writer);
            weaveFields(writer);
            weaveIntializer(writer);
            weaveRedirectors4Interface(writer);
            weaveProxyMethodsThrow(writer);
            weaveFallInterface(writer);

            writer.visitEnd();

            return writer.toByteArray();
        } else
        {
            String newName = getClassNamingPolicy().getName(source.getName(), classPredicate);
            newType = Type.getType("L" + newName.replace('.', '/') + ";");
            ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_FRAMES);
            writer.visit(TypeUtils.getJdkVersion(), source.getModifiers(), newType.getInternalName(), null, Type.getInternalName(source)
                    , getInterfaces(source, fallable));

            weaveConstructors(writer);
            weaveFields(writer);
            weaveIntializer(writer);
            weaveRedirectors(writer);
            weaveProxyMethods(writer);
            weaveFallInterface(writer);

            writer.visitEnd();

            return writer.toByteArray();
        }


    }

    private void weaveFallInterface(ClassWriter writer)
    {
        MethodVisitor mv = writer.visitMethod(ACC_PUBLIC, setInterceptorManager.getName()
                , setInterceptorManager.getDescriptor(), null, null);
        if (mv != null)
        {
            GeneratorAdapter adapter = new GeneratorAdapter(ACC_PUBLIC, setInterceptorManager, mv);
            adapter.visitCode();
            adapter.loadThis();
            adapter.loadArg(0);
            adapter.putField(newType, FALL_MANAGER_NAME, MANAGER);
            adapter.returnValue();
            adapter.endMethod();
        }

        mv = writer.visitMethod(ACC_PUBLIC, getInterceptorManager.getName(), getInterceptorManager.getDescriptor(), null, null);
        if (mv != null)
        {
            GeneratorAdapter adapter = new GeneratorAdapter(ACC_PUBLIC, getInterceptorManager, mv);
            adapter.visitCode();
            adapter.loadThis();
            adapter.getField(newType, FALL_MANAGER_NAME, MANAGER);
            adapter.returnValue();
            adapter.endMethod();
        }

        mv = writer.visitMethod(ACC_PUBLIC + ACC_VARARGS, GO.getName(), GO.getDescriptor(), null, null);
        if (mv != null)
        {
            GeneratorAdapter adapter = new GeneratorAdapter(ACC_PUBLIC + ACC_VARARGS, GO, mv);
            adapter.visitCode();
            adapter.loadThis();
            adapter.invokeVirtual(newType, getInterceptorManager);
            adapter.loadArg(0);
            adapter.invokeVirtual(MANAGER, getInterceptors);
            adapter.loadThis();
            adapter.loadArg(1);
            adapter.invokeVirtual(CHAIN, intercept);
            adapter.returnValue();
            adapter.endMethod();
        }
    }

    private void weaveProxyMethods(ClassWriter writer)
    {
        for (InterceptorChain chain : chains)
        {
            Method method = getMethod(chain.getMethod());
            String proxyName = getMethodNamingPolicy().getName(chain.getMethod().getName(), methodPredicate);
            MethodVisitor mv = writer.visitMethod(ACC_PUBLIC, proxyName, method.getDescriptor(), null, getExceptions(chain.getMethod()));
            if (mv != null)
            {
                GeneratorAdapter adapter = new GeneratorAdapter(mv, chain.getMethod().getModifiers(), proxyName, method.getDescriptor());
                adapter.visitCode();
                adapter.loadThis();
                adapter.loadArgs();
                adapter.invokeConstructor(sourceType, method);
                adapter.returnValue();
                adapter.endMethod();
            }
            MethodProxy proxy = new MethodProxy(proxyName);
            chain.setProxy(proxy);
        }
    }

    private void weaveRedirectors4Interface(ClassWriter writer)
    {
        for (InterceptorChain chain : chains)
        {
            Method method = getMethod(chain.getMethod());
            System.out.println("redirected : " + method.getName());
            MethodVisitor mv = writer.visitMethod(ACC_PUBLIC, method.getName()
                    , method.getDescriptor(), null, getExceptions(chain.getMethod()));
            if (mv != null)
            {
                GeneratorAdapter adapter = new GeneratorAdapter(chain.getMethod().getModifiers(), method, mv);
                adapter.visitCode();
                adapter.loadThis();
                adapter.getStatic(newType, method.getName() + FIELD_SUFFIX, METHOD);
                adapter.loadArgArray();
                adapter.invokeVirtual(newType, GO);
                if (method.getReturnType() != Type.VOID_TYPE)
                {
                    adapter.unbox(method.getReturnType());
                }
                adapter.returnValue();
                adapter.visitMaxs(0, 0);
                adapter.endMethod();
            }
        }
    }

    private void weaveProxyMethodsThrow(ClassWriter writer)
    {
        for (InterceptorChain chain : chains)
        {
            Method method = getMethod(chain.getMethod());
            Type returnType = method.getReturnType();
            String proxyName = getMethodNamingPolicy().getName(chain.getMethod().getName(), methodPredicate);
            MethodVisitor mv = writer.visitMethod(ACC_PUBLIC, proxyName, method.getDescriptor(), null, getExceptions(chain.getMethod()));
            if (mv != null)
            {
                GeneratorAdapter adapter = new GeneratorAdapter(mv, chain.getMethod().getModifiers(), proxyName, method.getDescriptor());
                adapter.visitCode();
                adapter.visitTypeInsn(NEW, "java/lang/RuntimeException");
                adapter.visitInsn(DUP);
                adapter.visitLdcInsn("not implemented");
                adapter.visitMethodInsn(INVOKESPECIAL, "java/lang/RuntimeException", "<init>", "(Ljava/lang/String;)V", false);
                adapter.visitInsn(ATHROW);
                adapter.returnValue();
                adapter.visitMaxs(0, 0);
                adapter.endMethod();
            }
            MethodProxy proxy = new MethodProxy(proxyName);
            chain.setProxy(proxy);
        }
    }

    private void weaveRedirectors(ClassWriter writer)
    {
        for (InterceptorChain chain : chains)
        {
            Method method = getMethod(chain.getMethod());
            System.out.println("redirected : " + method.getName());
            MethodVisitor mv = writer.visitMethod(chain.getMethod().getModifiers(), method.getName()
                    , method.getDescriptor(), null, getExceptions(chain.getMethod()));
            if (mv != null)
            {
                GeneratorAdapter adapter = new GeneratorAdapter(chain.getMethod().getModifiers(), method, mv);
                adapter.visitCode();
                adapter.loadThis();
                adapter.getStatic(newType, method.getName() + FIELD_SUFFIX, METHOD);
                adapter.loadArgArray();
                adapter.invokeVirtual(newType, GO);
                if (method.getReturnType() != Type.VOID_TYPE)
                {
                    adapter.unbox(method.getReturnType());
                }
                adapter.returnValue();
                adapter.endMethod();
            }
        }
    }


    private void weaveIntializer(ClassWriter writer)
    {
        GeneratorAdapter clinit = null;
        MethodVisitor mv = writer.visitMethod(ACC_STATIC, CLINIT.getName(), CLINIT
                .getDescriptor(), null, null);
        if (mv != null)
        {
            clinit = new GeneratorAdapter(ACC_STATIC, CLINIT, mv);
            clinit.visitCode();
        }
        for (InterceptorChain chain : chains)
        {
            Method method = getMethod(chain.getMethod());
            if (clinit != null)
            {
                Type[] formals = method.getArgumentTypes();
                clinit.push(sourceType);
                clinit.push(method.getName());
                clinit.push(formals.length);
                clinit.newArray(CLASS);
                for (int j = 0; j < formals.length; ++j)
                {
                    clinit.dup();
                    clinit.push(j);
                    clinit.push(formals[j]);
                    clinit.arrayStore(CLASS);
                }
                clinit.invokeVirtual(CLASS, GET_METHOD);
                clinit.putStatic(newType, method.getName() + FIELD_SUFFIX, METHOD);
            }
        }
        if (clinit != null)
        {
            clinit.returnValue();
            clinit.endMethod();
        }

    }

    private void weaveFields(ClassWriter writer)
    {
        FieldVisitor fv = writer.visitField(ACC_PRIVATE, FALL_MANAGER_NAME, MANAGER.getDescriptor(), null, null);
        if (fv != null) fv.visitEnd();

        for (InterceptorChain chain : chains)
        {
            fv = writer.visitField(ACC_PRIVATE + ACC_STATIC, chain.getMethod().getName() + FIELD_SUFFIX
                    , METHOD.getDescriptor(), null, null);
            if (fv != null) fv.visitEnd();
        }

    }

    private void weaveConstructors(ClassWriter writer)
    {
        if (source.getConstructors().length > 0)
        {
            for (Constructor constructor : source.getConstructors())
            {
                if (!TypeUtils.isPrivate(constructor.getModifiers()))
                {
                    final String desc = Type.getConstructorDescriptor(constructor);
                    final Method INIT = new Method("<init>", desc);
                    MethodVisitor mv = writer.visitMethod(constructor.getModifiers(), INIT.getName(), desc
                            , null, getExceptions(constructor));
                    if (mv != null)
                    {
                        GeneratorAdapter init = new GeneratorAdapter(constructor.getModifiers(), INIT, mv);
                        init.visitCode();
                        init.loadThis();
                        init.loadArgs();
                        init.invokeConstructor(sourceType, INIT);
                        init.returnValue();
                        init.endMethod();
                    }
                }
            }
        } else
        {
            final String desc = "()V";
            final Method INIT = new Method("<init>", desc);
            MethodVisitor mv = writer.visitMethod(ACC_PUBLIC, INIT.getName(), desc, null, null);
            if (mv != null)
            {
                GeneratorAdapter init = new GeneratorAdapter(ACC_PUBLIC, INIT, mv);
                init.visitCode();
                init.loadThis();
                init.loadArgs();
                init.invokeConstructor(Type.getType(Object.class), INIT);
                init.returnValue();
                init.endMethod();
            }


        }
    }

    public String newName()
    {
        return newType.getClassName();
    }

}
