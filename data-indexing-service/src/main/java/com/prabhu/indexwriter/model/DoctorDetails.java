package com.prabhu.indexwriter.model;

public class DoctorDetails
{
    private String name;

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [name = "+name+"]";
    }
}
