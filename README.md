# UDP and TCP Connections in Java

Here's a little toe-dipping into connecting a server and client object 
via the Internet's two main transport protocols, UDP and TCP.

## The UDP 'Connection'

I hope you know that I'm calling it a connection since that feels 
more natural to say. However, UDP is a connectionless protocol that 
follows a "fire-and-forget" kind of ethos. It aims its data at a port
on another machine, and simply pushes it towards it. This gives it a
very lightweight nature, but does mean data can arrive in any order,
if it does arrive at all. Literally imagine aiming a gun at an object
in the distance. Your aim is for a bulls-eye, but you might not hit
bulls-eye or you might miss completely. That's my analogy for UDP

> Throughout, I'll be using the `java.net` package as well as `java.io`
> , that's pretty much all there is to it.

### The UDP Server

The UDP server that I'll set up here has a simple purpose: echo the user's
input back to them, but in uppercase.

In Java, a UDP socket is set up using a `DatagramSocket` at a given port,
The `DatagramSocket` accepts data as a byte stream to build a
`DatagramPacket` with given buffer and size (`DatagramPacket(buf, size)`).
Next, the socket _blocks_ execution until it `receive`s a packet  through
the socket. Now, we have the raw bytes that came through the socket
and we can do whatever we need with them. We also have the data that
came with the Datagram, namely the IP Address and port of the sender

We extract the data, then just send it back to the client in the [same way](#the-udp-client)
that the client sent it to the server - build a frame, then push it
out of the port.

This is all wrapped up in a `while (true)` loop so that the server can
continue to run and receive more packets from clients.

### The UDP Client

Moving on, the client is more simple, because it doesn't have to parse
much information from the server, nor does it loop to send multiple 
messages to the server.

The client creates a `DatagramSocket` using a random port (it doesn't
matter which, since the server can always tell). I use a `BufferedReader`
object that uses the `System.in` as an input stream so that the user
can send whatever they would like. They generate a `DatagramPacket`
and push it towards the server at port 9876, then they listen for
the server's response. This is received, and printed to the screen.

It should be noted that the client can close the server by sending
`.` as a message. This closes the socket on the server's side.

And that's it. It's really quite simple, and you can even use a 
packet sniffer like Wireshark to show how simple it is. At some
point, I'd like to come back and look into measuring packet loss
and corruption.

## The TCP _Connection_

So, this one I actually can call a connection :)
