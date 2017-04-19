package fga.mds.gpp.trezentos.View;

import fga.mds.gpp.trezentos.Model.About;
import fga.mds.gpp.trezentos.Model.UserClass;

import org.junit.Before;
import org.junit.Test;

import static java.lang.Double.valueOf;
import static org.junit.Assert.*;

public class AboutUnitTest {

    @Test
    public void About_TitleTest(){
        About about = new About();
        about.setTitle("Title");
        assertEquals("Title",about.getTitle());
    }

    @Test
    public void About_SubtitleTest(){
        About about = new About();
        about.setSubTitle("Subtitle");
        about.equals(about.getSubTitle());
    }
}
