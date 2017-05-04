package fga.mds.gpp.trezentos.Model;

import fga.mds.gpp.trezentos.Exception.UserException;
import fga.mds.gpp.trezentos.R;

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

    public void setSubTitle(String subtitle){
        this.subTitle = subTitle;
    }
    public String getSubTitle(){
        return subTitle;
   }

    public int getShowImage(int position){
        switch(position){
            case 0:
                return (R.drawable.trezentos_icon);
            case 1:
                return (R.drawable.tedx);
            case 2:
                return (R.drawable.about_youtube);
            case 3:
                return (R.drawable.about_youtube);
            case 4:
                return (R.drawable.documents);
            case 5:
                return (R.drawable.about_record);
            default:
                return (R.drawable.about_unb_tv);
        }
    }
}