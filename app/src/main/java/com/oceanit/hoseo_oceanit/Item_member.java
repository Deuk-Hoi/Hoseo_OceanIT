package com.oceanit.hoseo_oceanit;

public class Item_member {

    int member_image;
    String name;
    String position;
    String email;

    int getMember_image()
    {
        return this.member_image;
    }

    String getName()
    {
        return this.name;
    }

    String getPosition()
    {
        return this.position;
    }
    String getEmail()
    {
        return this.email;
    }

    Item_member(int member_image, String name, String position, String email)
    {
        this.member_image = member_image;
        this.name = name;
        this.position = position;
        this.email= email;
    }
}
