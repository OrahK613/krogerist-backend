package krogerist.domain;

import java.util.List;

/**
 * Created by dcpdev on 9/27/15.
 */
public class Science {

    private List<YellowTag> yellowTags;

    public Science()
    {

    }

    public Science(List<YellowTag> yellowTags) {

        this.yellowTags = yellowTags;
    }

    public List<YellowTag> getYellowTags()
    {
        return yellowTags;
    }

    public void setYellowTags(List<YellowTag> yellowTags)
    {
        this.yellowTags = yellowTags;
    }




}
