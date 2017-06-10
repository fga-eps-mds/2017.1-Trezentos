package fga.mds.gpp.trezentos.Model;

import org.junit.Test;
import fga.mds.gpp.trezentos.Model.Groups;
import org.junit.Before;
import org.junit.Test;

import static java.lang.Double.valueOf;
import static org.junit.Assert.*;

public class GroupsUnitTest {

    @Test
    public void shouldGroupNumber() {

        Groups groups = new Groups("10", "leader");
        groups.setGroupNumber("10");
        assertEquals("10", groups.getGroupNumber());
    }

    @Test
    public void ShouldValidateLeaderName(){
        Groups groups = new Groups("10", "leader");
        groups.setLeaderName("leader");
        assertEquals("leader", groups.getLeaderName());
    }
}
