package fga.mds.gpp.trezentos.Model;

import fga.mds.gpp.trezentos.Exception.UserException;

public class About {

    private String title;
    private String subTitle;

    public About(){

    }

    public About(String title, String subTitle) throws UserException{
        this.title = title;
        this.subTitle = subTitle;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public String getTitle(){
        return title;
    }

    public void setSubTitle(String Subtitle){
        this.subTitle = subTitle;
    }

    public String getSubTitle(){
        return subTitle;
    }
}
