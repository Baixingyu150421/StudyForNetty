# Study netty project
服务端的socket在哪里初始化的？

服务端在哪里accept连接的？

默认情况下Netty服务端启动多少线程？何时启动？

Netty是如何解决jdk空轮询的bug的？

Netty如何保证异步串行无锁化？

Netty是在哪里检测新连接接入的？

新连接是怎样注册到NioEventLoop线程的？

Netty是如何判断channelHandler类型的？

对于channelHandler的添加应该遵循什么样的顺序？

用户手动触发事件传播，不同的触发方式的区别？

Netty中的内存类别有哪些？

如何减少多线程内存分配之间的竞争？

不同大小的内存是如何进行分配的？

解码器的解码过程？

Netty中的解码器

如何把对象变成字节流，最终写到Socket底层？

编码器的处理逻辑？

Netty中使用到的设计模式有哪些？

Netty中有哪些优化操作？

# Netty的核心组件

Channel

NioEventLoop

ChannelHandler

ByteBuf

Pipeline

Encoder

Decoder

# 优化工具类

FastThreadLocal

Recycler

# goal
技术的出现势必是要解决实际问题的，但在学习一门新技术时更重要的是去学习它的思想。

对框架的使用，源码的分析可能是一个枯燥漫长的过程，但是我觉得收获会更多。

如有学习上的指导，请您e-mail我：SYNU_B_X_Y@163.com
# File structure      
      --java
         --com.netty
                  --atomicclass: test jdk atomicClass
              
                  --bytebuf: the examples for using bytebuf in netty
                  
                  --fifthexample: the webSocket example 
                  
                  --firstexample: the socket example
                  
                  --fourthexample: the heartCheck example
                  
                  --httpexample: the httpServer example
                  
                  --nio: some examples for jdk's NIO 
                  
                  --secondexample: a simple chat application 
                  
                  --sixthexample: netty support for google protobuf 
                  
# Summary

一定要坚持
   
   

