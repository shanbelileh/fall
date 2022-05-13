package org.fall;

import org.fall.proxy.Proxy;
import org.fall.proxy.ProxyModule;
import org.fall.proxy.MethodBinder;
import org.fall.proxy.aspect.MethodAspect;
import org.fall.util.CollectionUtils;

import java.util.List;
import java.util.LinkedList;

/**
 * Copyright 2009 by
 *
 * @author : /\/\[]|-|/-\/\/\/\/\/-\|)
 *         Date: Oct 9, 2009
 *         Time: 6:48:31 PM
 * @version : 1.0
 */
public final class Fall
{
    public static Proxy createProxy(ProxyModule... proxyModules)
    {
        List<MethodBinder> binders = new LinkedList<MethodBinder>();
        for (ProxyModule module : proxyModules)
        {
            module.config();
            binders.addAll(module.getBindings());
        }

        return new Proxy(CollectionUtils.<MethodAspect, MethodBinder>cast(binders));
    }

/*
    public static void main(String[] args)
    {
        List<MethodBinder>  binders=new ArrayList<MethodBinder>();
        binders.add(new MethodAspectBinder(null));
        binders.add(new MethodAspectBinder(null));
        binders.add(new MethodAspectBinder(null));
        binders.add(new MethodAspectBinder(null));

        List<MethodAspect> aspects = CollectionUtils.<MethodAspect, MethodBinder>cast(binders);
        System.out.println("");
    }
*/
}
