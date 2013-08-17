mandrill-tiny
=============

A tiny library to deliver emails over the Mandrill scalable mailing service with comfort and without need of J2EE

Current condition: [![Build Status](https://travis-ci.org/littleli/mandrill-tiny.png)](https://travis-ci.org/littleli/mandrill-tiny)
[![Coverage Status](https://coveralls.io/repos/littleli/mandrill-tiny/badge.png?branch=master)](https://coveralls.io/r/littleli/mandrill-tiny?branch=master)

dependencies
------------

This library takes dependency very seriously. It's respectful to target project or platform needs.
There are compile time dependencies on a set of popular libraries:

* [JacksonJson](http://jackson.codehaus.org)
* [Gson](http://code.google.com/p/google-gson)
* [org.json](http://json.org/java)
* [Apache HTTP Client](http://hc.apache.org)
* [Jetty HTTP Client](http://download.eclipse.org/jetty/)

But don't panic! It's for a very good reason. This project is very flexible in terms of runtime selection
of dependencies. It depends only on you which json serializer you want to use or which http client library
gives you enough comfort. Use exclude in your app pom.xml.

This library even is very friendly to your platform or application. For example on Android you can even exclude all
json serializers dependencies from your project since it can leverage Java SE standard HttpsUrlConnection class and
org.json already available on Android platform.

It is entirely on you. Isn't it amazing? :)

Steps to use
------------

For examples please see the couple of tests. It's not that awesome, but the philosophy is simple.
There are some simple steps to follow:

* create instance of JsonHandler of your choice
* create instance of HttpHandler of your choice
* create instance of MandrillServiceFactory and pass your API key to it
* ask for the implementation of service described as static inner interface from the Category class
* use the calls as needed

TODO
----

Library is almost finished, but I would like to introduce few additional Builder classes just to increse comfort
with Structs and StructArrays when creating specific Structs like recipient(list) and so on.

Contribute
----------

Don't hesitate to write me, send patches or contact me. I'm keen to ideas.

License
-------

This library uses LGPL v3 license. You can use it without limitations if you use it as it is for both free and
commercial use. You can fork, it's completely fine, but please make your fork public. Thank you.