package com.thinking.in.spring.ioc.domain;

import com.thinking.in.spring.ioc.annotation.Super;

@Super
public class SuperUser  extends User{

    private String address;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "SuperUser{" +
                "address='" + address + '\'' +
                "} " + super.toString();
    }
}
