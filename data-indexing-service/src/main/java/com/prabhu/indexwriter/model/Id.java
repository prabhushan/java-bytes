package com.prabhu.indexwriter.model;

public class Id
{
    private String $oid;

    public String get$oid ()
    {
        return $oid;
    }

    public void set$oid (String $oid)
    {
        this.$oid = $oid;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [$oid = "+$oid+"]";
    }
}

