package com.springbook.biz.polymorphism1;

public class TVUser {
	public static void main (String args[]) {
		TV tv = new SamsungTV();
		tv.powerOn();
		tv.volumeUp();
		tv.volumeDown();
		tv.powerOff();
	}
}
