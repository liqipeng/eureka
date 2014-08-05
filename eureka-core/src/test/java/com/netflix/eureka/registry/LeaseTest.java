package com.netflix.eureka.registry;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author David Liu
 * TODO more concurrency tests
 */
public class LeaseTest {

    @Test
    public void testLeaseNotYetExpired() {
        Lease<String> lease = new Lease<String>("abc", 5000);
        assertFalse(lease.hasExpired());
    }

    @Test
    public void testLeaseHasExpired() throws Exception {
        Lease<String> lease = new Lease<String>("abc", 1);
        Thread.sleep(10);  // sleep a tiny bit for time to progress
        assertTrue(lease.hasExpired());
    }

    @Test
    public void testCompareAndSetLeaseHolder() {
        Lease<String> lease = new Lease<String>("abc", 5000);
        String current = lease.getHolder();
        assertTrue(current.equals("abc"));

        String str1 = "foo";
        String str2 = "bar";
        assertTrue(lease.compareAndSet(current, str1));
        assertFalse(lease.compareAndSet(current, str2));
    }

    @Test
    public void testRenewalWithDuration() throws Exception {
        Lease<String> lease = new Lease<String>("abc", 1);
        Thread.sleep(10);
        assertTrue(lease.hasExpired());

        lease.renew(2000);
        Thread.sleep(10);
        assertFalse(lease.hasExpired());
    }

}