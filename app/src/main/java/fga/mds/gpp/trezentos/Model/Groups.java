package fga.mds.gpp.trezentos.Model;

public class Groups {
    private String groupNumber;
    private String leaderName;

    public Groups(String groupNumber, String leaderName){
        this.groupNumber = groupNumber;
        this.leaderName = leaderName;
    }

    public void setGroupNumber(String groupNumber){
        this.groupNumber = groupNumber;
    }

    public String getGroupNumber(){
        return groupNumber;
    }

    public void setLeaderName(String leaderName){
        this.leaderName = leaderName;
    }

    public String getLeaderName(){
        return leaderName;
    }
}