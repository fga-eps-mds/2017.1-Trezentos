package fga.mds.gpp.trezentos.Model;

import fga.mds.gpp.trezentos.Exception.UserException;
import fga.mds.gpp.trezentos.Model.About;
import fga.mds.gpp.trezentos.Model.UserClass;
import fga.mds.gpp.trezentos.R;

import org.junit.Before;
import org.junit.Test;

import static java.lang.Double.valueOf;
import static org.junit.Assert.*;

public class AboutTest {

    @Test
    public void shouldValidateTitle(){
        About about = new About();
        about.setTitle("Title");
        assertEquals("Title",about.getTitle());
    }

    @Test
    public void shouldValidateSubtitle(){
        About about = new About();
        about.setSubTitle("Subtitle");
        assertEquals("Subtitle", about.getSubTitle());
    }

    @Test
    public void shouldValidateClassConstructor() throws UserException {
        About about = new About("Title", "Subtitle");
        assertEquals("Title", about.getTitle());
        assertEquals("Subtitle", about.getSubTitle());
    }

    @Test
    public void shouldValidateAboutImage(){
        About about = new About();
        assertEquals(R.drawable.trezentos_icon, about.getShowImage(0));
        assertEquals(R.drawable.tedx, about.getShowImage(1));
        assertEquals(R.drawable.about_youtube, about.getShowImage(2));
        assertEquals(R.drawable.about_youtube, about.getShowImage(3));
        assertEquals(R.drawable.documents, about.getShowImage(4));
        assertEquals(R.drawable.about_record, about.getShowImage(5));
        assertEquals(R.drawable.about_unb_tv, about.getShowImage(6));
    }
}
