package com.prabhu.indexwriter.model;

public class ListPracticeDetails
{
    private String location;

    private String timings;

    private String practiceInfo;

    private String fees;

    public String getLocation ()
    {
        return location;
    }

    public void setLocation (String location)
    {
        this.location = location;
    }

    public String getTimings ()
    {
        return timings;
    }

    public void setTimings (String timings)
    {
        this.timings = timings;
    }

    public String getPracticeInfo ()
    {
        return practiceInfo;
    }

    public void setPracticeInfo (String practiceInfo)
    {
        this.practiceInfo = practiceInfo;
    }

    public String getFees ()
    {
        return fees;
    }

    public void setFees (String fees)
    {
        this.fees = fees;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [location = "+location+", timings = "+timings+", practiceInfo = "+practiceInfo+", fees = "+fees+"]";
    }
}
