package com.czmud.camcram.diagnostics;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryUsage;

public class MemoryUseTest {
	public MemoryUsage testHeapMemory() {
		final MemoryUsage heapMemoryUsage = ManagementFactory.getMemoryMXBean().getHeapMemoryUsage();		
		System.out.println("---heap---");
		System.out.println("Allocated memory for JVM :"+heapMemoryUsage.getCommitted());
		System.out.println("Initially requested memory :"+heapMemoryUsage.getInit());
		System.out.println("Maximum memory can be used :"+heapMemoryUsage.getMax());
		System.out.println("Memory used by the JVM :"+heapMemoryUsage.getUsed());
		return heapMemoryUsage;
	}
	public MemoryUsage testNonHeapMemory() {
		final MemoryUsage nonHeapMemoryUsage = ManagementFactory.getMemoryMXBean().getNonHeapMemoryUsage();
		System.out.println("---non-heap---");
		System.out.println("Allocated memory for JVM :"+nonHeapMemoryUsage.getCommitted());
		System.out.println("Initially requested memory :"+nonHeapMemoryUsage.getInit());
		System.out.println("Maximum memory can be used :"+nonHeapMemoryUsage.getMax());
		System.out.println("Memory used by the JVM :"+nonHeapMemoryUsage.getUsed());
		return nonHeapMemoryUsage;
	}
}
