package krogerist.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dcpdev on 9/27/15.
 */
@Data
public class Science {

    private List<Map<String, Object>> science;
   

    public Science()
    {

    }

    public Science(List<Map<String, Object>> science) {

        this.science = science;

    }


    //public void setYellowTags(List<YellowTag> yellowTags) {
    //    this.yellowTags = yellowTags;
   // }
}
