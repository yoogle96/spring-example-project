package me.yoogle;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Client {

    static final String HOST = System.getProperty("host", "127.0.0.1");
    static final int PORT = 8888;

    public static void main(String[] args) throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new TelnetClientInitializer());

            Channel ch = b.connect(HOST, PORT).sync().channel();

            ChannelFuture lastWriteFuter = null;
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            for(;;) {
                String line = in.readLine();
                if(line == null) {
                    break;
                }

                lastWriteFuter = ch.writeAndFlush(line + "\r\n");

                if("bye".equals(line.toLowerCase())) {
                    ch.closeFuture().sync();
                    break;
                }
            }
            
            if(lastWriteFuter != null) {
                lastWriteFuter.sync();
            }
        } finally {
            group.shutdownGracefully();
        }
    }
}
