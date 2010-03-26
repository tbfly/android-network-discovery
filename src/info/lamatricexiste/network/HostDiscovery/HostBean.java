/*
 * Copyright (C) 2009-2010 Aubort Jean-Baptiste (Rorist)
 * Licensed under GNU's GPL 2, see README
 */
 
// Inspired by http://connectbot.googlecode.com/svn/trunk/connectbot/src/org/connectbot/bean/HostBean.java
package info.lamatricexiste.network.HostDiscovery;

public class HostBean {
    public String ipAddress = null;
    public String hostname = null;
    public String hardwareAddress = "00:00:00:00:00:00";
    public String nicVendor = "Unknown";
    //public String os = "Unknown";
    public float responseTime = 0;
    public int position = 0;
    public long[] portsOpen = null;
    public long[] portsClosed = null;
    public final static String EXTRA_POSITION = "position";
    public final static String EXTRA_HOST = "host";
    public final static String EXTRA_HOSTNAME = "hostname";
    public final static String EXTRA_PORTSO = "ports_o";
    public final static String EXTRA_PORTSC = "ports_c";
}
