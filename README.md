````
 _____  _              _         _  _  _       _   
|   __|| |_  ___  ___ | |_  ___ | ||_|| | ___ | |_ 
|__   ||   || .'||   || . || -_|| || || || -_||   |
|_____||_|_||__,||_|_||___||___||_||_||_||___||_|_|

````

# Fall

Fall is A simple **AOP** framework built top of [*ASM*](https://asm.ow2.io/).

Fall is good for learning about Bytecode proxying and how jvm work

# Example

```java
public class TransactionModule extends ProxyModule
{
    @Override
    public void config()
    {
        TransactionInterceptor interceptor = new TransactionInterceptor();

        bind(Matchers.annotatedWith(Transactional.class)).of(Matchers.any()).to(interceptor);

        bind(Matchers.any()).of(Matchers.subclassesOf(Repository.class)).to(interceptor);

    }
}
```

```java
    Proxy proxy=Fall.createProxy(new TransactionModule());
    BarDao dao=proxy.newInstance(BarDao.class);
    assertEquals(2,dao.createBar("Chocolate bar"));
```

# Conclusion

ASM is an all purpose Java bytecode manipulation and analysis framework. It can be used to modify existing classes or to
dynamically generate classes, directly in binary form. ASM provides some common bytecode transformations and analysis
algorithms from which custom complex transformations and code analysis tools can be built. ASM offers similar
functionality as other Java bytecode frameworks, but is focused on performance. Because it was designed and implemented
to be as small and as fast as possible, it is well suited for use in dynamic systems (but can of course be used in a
static way too, e.g. in compilers).


- [ASM](https://asm.ow2.io/)
- [Dynamic Proxies in Java](https://www.baeldung.com/java-dynamic-proxies)
- [cglib ](https://github.com/cglib/cglib)

